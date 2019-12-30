package samlen.tsoi.showcase.web.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import samlen.tsoi.showcase.web.entity.Node;
import samlen.tsoi.showcase.web.entity.Parent;

/**
 * @author samlen_tsoi
 * @date 2019/12/27
 **/
@Slf4j
public class NodeTest {

    @Test
    public void json() {
        Node node = new Node();
        node.setAge(10);
        node.setName("tsoi");

        Parent<Node> nodeParent = new Parent<>();
        nodeParent.setSon(node);
        nodeParent.setName("parent");

        log.info(JSON.toJSONString(nodeParent));
    }

    @Test
    public void bean() {
        String json = "{\"name\":\"parent\",\"son\":{\"age\":10,\"name\":\"tsoi\"}}";

        Parent<Node> nodeParent = JSON.parseObject(json, new TypeReference<Parent<Node>>() {
        });
        log.info("son : {}", nodeParent.getSon());
    }


}
