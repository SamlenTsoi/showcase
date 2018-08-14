package samlen.tsoi.showcase.storm.bolt;

import lombok.extern.slf4j.Slf4j;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
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
        log.info("kafkaBolt --> 准备");
        this.outputCollector = outputCollector;
    }

    @Override
    public void execute(Tuple tuple) {
        String keyword = "关键词";
        String value = tuple.getStringByField("value");
        log.info("kafkaBolt --> 处理:{},", this.hashCode() + ":" + value);
        outputCollector.emit(tuple, new Values(keyword));
        //必须调用此方法，告诉storm该tuple已经被正确处理，否则重复消费该tuple
        outputCollector.ack(tuple);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("word"));
        log.info("kafkaBolt --> 声明");
    }
}
