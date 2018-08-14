package samlen.tsoi.showcase.topology;

import org.apache.storm.Config;
import org.apache.storm.StormSubmitter;
import org.apache.storm.mqtt.common.MqttOptions;
import org.apache.storm.mqtt.mappers.StringMessageMapper;
import org.apache.storm.mqtt.spout.MqttSpout;
import org.apache.storm.topology.TopologyBuilder;

import java.util.Arrays;

/**
 * @author samlen_tsoi
 * @date 2018/8/13
 */
public class TopologyApplication {

    public static void main(String[] args) {
        //mqtt配置
        MqttOptions options = new MqttOptions();
        //订阅的topic
        options.setTopics(Arrays.asList("**/**"));
        //mqtt密码
        options.setPassword("public");
        //mqtt账号
        options.setUserName("admin");
        //mqtt地址
        options.setUrl("tcp://127.0.0.1:1883");
        options.setCleanConnection(false);
        MqttSpout mqttSpout = new MqttSpout(new StringMessageMapper(), options);
        //实例化topologyBuilder类。
        TopologyBuilder mqttTopologyBuilder = new TopologyBuilder();
        //设置spout即数据来源，parallelism_hint数即初始化线程数/实例数，会导致重复读数据，默认为1
        mqttTopologyBuilder.setSpout("mqtt-spout", mqttSpout, 1).setNumTasks(1);
        //设置bolt即数据处理，parallelism_hint数即初始化线程数/实例数，共享数据spout流入数据（不重复），默认为1
        mqttTopologyBuilder.setBolt("mqtt-bolt", new MqttBolt(), 1).shuffleGrouping("mqtt-spout");
        mqttTopologyBuilder.setBolt("mysql-bolt", new MysqlBolt(), 1).shuffleGrouping("mqtt-bolt");
        Config config = new Config();
        config.setDebug(false);
        //本地运行
//        LocalCluster cluster = new LocalCluster();
//        //提交拓扑到storm
//        cluster.submitTopology("mqtt-topology", config, mqttTopologyBuilder.createTopology());
        //部署到集群运行
        try {
            StormSubmitter.submitTopology("mqtt-topology", config, mqttTopologyBuilder.createTopology());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
