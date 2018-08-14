package samlen.tsoi.showcase.topology;

import com.alibaba.fastjson.JSONObject;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author samlen_tsoi
 * @date 2018/7/27
 */
@Slf4j
public class MqttBolt extends BaseRichBolt {

    private OutputCollector outputCollector;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        log.info("mqttBolt --> 准备");
        this.outputCollector = outputCollector;
    }

    @Override
    public void execute(Tuple tuple) {
        Map<String, Integer> wordToCount = new HashMap<>();
        String message = tuple.getStringByField("message");
        log.info("mqttBolt --> 处理:{}", message);
        JSONObject mes = JSONObject.parseObject(message);
        JSONObject content = mes.getJSONObject("content");
        String contentString = content.getString("content");
        if (StringUtils.isNotBlank(contentString)) {
            String words = contentString.replaceAll("\\pP|\\pS|\\s*|\t|\r|\n", "");
            //分词列表
            List<Term> wordList = HanLP.segment(words);
            //循环
            wordList.forEach(term -> {
                //词
                String word = term.word;
                //单字不计算在内
                if(word.length() >= 1) {
                    //次数
                    Integer num = 0;
                    //如果存在，则更新次数OR新增
                    if(wordToCount.containsKey(word)) {
                        //次数
                        num = wordToCount.get(word) + 1;
                    }else {
                        num += 1;
                    }
                    wordToCount.put(word, num);
                }
            });
            outputCollector.emit(tuple, Arrays.asList(wordToCount));
        }
        outputCollector.ack(tuple);
    }
    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("word"));
        log.info("mqtt --> 声明");
    }
}
