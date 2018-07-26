package samlen.tsoi.showcase.web.bolt;

import lombok.extern.slf4j.Slf4j;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author samlen_tsoi
 * @date 2018/7/26
 */
@Slf4j
@Component
public class KafkaBolt extends BaseRichBolt {

    private OutputCollector outputCollector;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        log.info("准备");
        this.outputCollector = outputCollector;
    }

    @Override
    public void execute(Tuple tuple) {
        String value = tuple.getStringByField("value");
        log.info("kafka接受到数据:{}",value);
        //必须调用此方法，告诉storm该tuple已经被正确处理，否则重复消费该tuple
        outputCollector.ack(tuple);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        log.info("声明");
    }
}
