package samlen.tsoi.showcase.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import samlen.tsoi.showcase.common.pojo.dto.Result;
import samlen.tsoi.showcase.web.event.TestEvent;

/**
 * @author samlen_tsoi
 * @date 2019/12/24
 **/
@Slf4j
@RestController
@RequestMapping("test")
public class TestEventController {
    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping("publish")
    public Result publish() {
        TestEvent testEvent = new TestEvent("samlen", 26);
        applicationContext.publishEvent(testEvent);
        return Result.ok();
    }

}
