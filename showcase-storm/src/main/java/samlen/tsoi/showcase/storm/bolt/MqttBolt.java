package samlen.tsoi.showcase.storm.bolt;

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
 * @date 2018/7/27
 */
@Slf4j
@Component
public class MqttBolt extends BaseRichBolt {

    private OutputCollector outputCollector;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        log.info("mqttBolt --> 准备");
        this.outputCollector = outputCollector;
    }

    @Override
    public void execute(Tuple tuple) {
        String message = tuple.getStringByField("message");
        log.info("mqttBolt --> 处理:{}", message);
        outputCollector.ack(tuple);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        log.info("mqtt --> 声明");
    }
}
