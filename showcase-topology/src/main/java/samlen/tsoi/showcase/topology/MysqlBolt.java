package samlen.tsoi.showcase.topology;

import lombok.extern.slf4j.Slf4j;
import org.apache.storm.jdbc.common.Column;
import org.apache.storm.jdbc.common.ConnectionProvider;
import org.apache.storm.jdbc.common.HikariCPConnectionProvider;
import org.apache.storm.jdbc.common.JdbcClient;
import org.apache.storm.shade.com.google.common.collect.Maps;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author samlen_tsoi
 * @date 2018/8/13
 */
@Slf4j
public class MysqlBolt extends BaseRichBolt {

    private OutputCollector collector;

    private JdbcClient jdbcClient;

    private ConnectionProvider connectionProvider;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        collector = outputCollector;
        Map<String, Object> hikariConfigMap = Maps.newHashMap();
        hikariConfigMap.put("dataSourceClassName","com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        hikariConfigMap.put("dataSource.url", "jdbc:mysql://localhost/test");
        hikariConfigMap.put("dataSource.user","root");
        hikariConfigMap.put("dataSource.password","root");
        connectionProvider = new HikariCPConnectionProvider(hikariConfigMap);
        //对数据库连接池进行初始化
        connectionProvider.prepare();
        jdbcClient = new JdbcClient(connectionProvider, 30);
    }

    @Override
    public void execute(Tuple tuple) {
        Map<String, Integer> wordToCount = (Map<String, Integer>) tuple.getValueByField("word");
        if (null != wordToCount) {
            Set<String> words = wordToCount.keySet();
            words.forEach(word -> {
                Integer count = wordToCount.get(word);
                //查询该word是否存在
                List<Column> list = new ArrayList();
                //创建一列将值传入
                list.add(new Column("word", word, Types.VARCHAR));
                List<List<Column>> select = jdbcClient.select("select count from word where word = ?",list);
                if (select.size() > 0) {
                    Integer oldCount = (Integer) select.get(0).get(0).getVal();
                    count = count + oldCount;
                    jdbcClient.executeSql("update word set count = " + count +" where word = '" + word + "'");

                } else {
                    jdbcClient.executeSql("insert into word (word, count) values( '" + word + "'," + count +  ")");

                }
            });
        }
        collector.ack(tuple);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }

    @Override
    public void cleanup() {
        connectionProvider.cleanup();
        super.cleanup();
    }
}
