package samlen.tsoi.showcase.dubbo.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import samlen.tsoi.showcase.dubbo.api.DubboService;

/**
 * 消费Controller
 *
 * @author samlen_tsoi
 * @date 2018/4/28
 */
@RestController
public class ConsumerController {

    @Reference(
            version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:12345")
    private DubboService dubboService;

    @GetMapping("/sayHello")
    public String sayHello(@RequestParam String name) {
        return dubboService.sayHello(name);
    }
}
