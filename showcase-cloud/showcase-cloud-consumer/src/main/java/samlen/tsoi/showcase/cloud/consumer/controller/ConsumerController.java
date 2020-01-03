package samlen.tsoi.showcase.cloud.consumer.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import samlen.tsoi.showcase.cloud.consumer.service.IConsumerService;
import samlent.tsoi.showcase.core.dto.Result;

/**
 * <p>
 * 消费者 前端控制器
 * </p>
 *
 * @author Samlen_Tsoi
 * @since 2020-01-02
 */
@RestController
@RequestMapping("consumer")
public class ConsumerController {
    @Autowired
    private IConsumerService consumerService;

    @GetMapping("call")
    public Result call() {
        consumerService.call();
        return Result.ok();
    }
}
