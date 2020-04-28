package samlen.tsoi.showcase.web.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author samlen_tsoi
 * @date 2019/12/26
 **/
@Data
@Slf4j
public class Parent {
    public static int value = 1;

    public final static int num = 10;

    static {
        log.info("parent static方法");
    }

    public Parent() {
        log.info("parent构造方法");
    }
}
