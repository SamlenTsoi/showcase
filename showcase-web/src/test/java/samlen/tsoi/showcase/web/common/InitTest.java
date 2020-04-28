package samlen.tsoi.showcase.web.common;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import samlen.tsoi.showcase.web.entity.Parent;
import samlen.tsoi.showcase.web.entity.Son;

/**
 * @author samlen_tsoi
 * @date 2020/1/14
 **/
@Slf4j
public class InitTest {

    @Test
    public void staticMethod() {
        log.info("value ：{}", Son.value);
    }

    @Test
    public void arrayInit() {
        Parent[] parents = new Parent[10];
    }

    @Test
    public void finalInit() {
        log.info("value ：{}", Son.num);
    }
}
