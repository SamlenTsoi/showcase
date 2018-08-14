package samlen.tsoi.showcase.storm.listener;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.apache.storm.Config;
import org.apache.storm.StormSubmitter;
import org.apache.storm.kafka.spout.KafkaSpout;
import org.apache.storm.topology.TopologyBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import samlen.tsoi.showcase.storm.bolt.KafkaBolt;


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
        TopologyBuilder kafkaTopologyBuilder = new TopologyBuilder();
        //设置spout即数据来源，parallelism_hint数即初始化线程数/实例数，会导致重复读数据，默认为1
        kafkaTopologyBuilder.setSpout("kafka-spout", kafkaSpout, 1).setNumTasks(1);
        //设置bolt即数据处理，parallelism_hint数即初始化线程数/实例数，共享数据spout流入数据（不重复），默认为1
        kafkaTopologyBuilder.setBolt("kafka-bolt", kafkaBolt, 2).shuffleGrouping("kafka-spout");
        Config config = new Config();
        config.setDebug(false);
//        LocalCluster cluster = new LocalCluster();
//        //提交拓扑到storm
//        cluster.submitTopology("kafka-topology", config, kafkaTopologyBuilder.createTopology());
        try {
            StormSubmitter.submitTopology("kafka-topology", config, kafkaTopologyBuilder.createTopology());
        } catch (Exception e) {
            log.error(Throwables.getStackTraceAsString(e));
        }
    }
}
