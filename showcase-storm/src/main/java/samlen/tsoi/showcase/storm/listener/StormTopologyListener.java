package samlen.tsoi.showcase.storm.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.kafka.spout.KafkaSpout;
import org.apache.storm.mqtt.spout.MqttSpout;
import org.apache.storm.topology.TopologyBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import samlen.tsoi.showcase.storm.bolt.KafkaBolt;
import samlen.tsoi.showcase.storm.bolt.MqttBolt;


/**
 * storm拓扑启动及提交
 *
 * @author samlen_tsoi
 * @date 2018/7/26 
 */
@Slf4j
@Component
public class StormTopologyListener implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private KafkaBolt kafkaBolt;

    @Autowired
    private KafkaSpout kafkaSpout;

    @Autowired
    private MqttSpout mqttSpout;

    @Autowired
    private MqttBolt mqttBolt;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        //实例化topologyBuilder类。
        TopologyBuilder kafkaTopologyBuilder = new TopologyBuilder();
        //设置spout即数据来源
        kafkaTopologyBuilder.setSpout("kafka-spout", kafkaSpout, 1).setNumTasks(1);
        //设置bolt即数据处理
        kafkaTopologyBuilder.setBolt("kafka-bolt", kafkaBolt, 1).setNumTasks(1).shuffleGrouping("kafka-spout");
        Config config = new Config();
        config.setDebug(false);
        LocalCluster cluster = new LocalCluster();
        //提交拓扑到storm
        cluster.submitTopology("kafka-topology", config, kafkaTopologyBuilder.createTopology());
        TopologyBuilder mqttTopologyBuilder = new TopologyBuilder();
        mqttTopologyBuilder.setSpout("mqtt-spout", mqttSpout, 1).setNumTasks(1);
        mqttTopologyBuilder.setBolt("mqtt-bolt", mqttBolt, 1).setNumTasks(1).shuffleGrouping("mqtt-spout");
        cluster.submitTopology("mqtt-topology", config, mqttTopologyBuilder.createTopology());
    }
}
