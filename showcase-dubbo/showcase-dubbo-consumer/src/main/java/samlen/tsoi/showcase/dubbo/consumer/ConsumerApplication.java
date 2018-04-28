package samlen.tsoi.showcase.dubbo.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 应用入口
 *
 * @author samlen_tsoi
 * @date 2018/4/28
 */
@SpringBootApplication(scanBasePackages = "samlen.tsoi.showcase.dubbo.consumer.controller")
//@SpringBootApplication
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
