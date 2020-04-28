package samlen.tsoi.showcase.web.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author samlen_tsoi
 * @date 2019/12/26
 **/
@Data
@Slf4j
public class Son extends Parent {
    static {
        log.info("son static 方法");
    }

    public Son() {
        log.info("son 构造方法");
    }
}
