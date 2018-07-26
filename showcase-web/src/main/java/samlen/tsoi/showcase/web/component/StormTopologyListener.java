package samlen.tsoi.showcase.web.component;

import lombok.extern.slf4j.Slf4j;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.kafka.spout.KafkaSpout;
import org.apache.storm.topology.TopologyBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import samlen.tsoi.showcase.web.bolt.KafkaBolt;


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

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        //实例化topologyBuilder类。
        TopologyBuilder topologyBuilder = new TopologyBuilder();
        //设置spout即数据来源
        topologyBuilder.setSpout("kafka-spout", kafkaSpout, 1).setNumTasks(1);
        //设置bolt即数据处理
        topologyBuilder.setBolt("kafka-bolt", kafkaBolt, 1).setNumTasks(1).shuffleGrouping("kafka-spout");
        Config config = new Config();
        config.setDebug(false);
        LocalCluster cluster = new LocalCluster();
        //提交拓扑到storm
        cluster.submitTopology("kafka-spout", config, topologyBuilder.createTopology());
    }
}
