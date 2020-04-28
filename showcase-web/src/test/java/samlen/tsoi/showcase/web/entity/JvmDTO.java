package samlen.tsoi.showcase.web.entity;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author samlen_tsoi
 * @date 2020/1/18
 **/
@Slf4j
public class JvmDTO {

    private static int num;

    static {
//        log.info(num + "");
        num = 0;
//        log.info(num + "");
        log.info("static 方法");
    }
//    private static int num = 1;

    @Test
    public void test() throws Exception {
        log.info(JvmDTO.num + "");
    }
}
