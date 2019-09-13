package samlen.tsoi.showcase.mqtt.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ErrorHandler;

/**
 * 微信错误处理
 *
 * @author wxw
 * @date 2017-12-10 11:24
 */
@Slf4j
@Component
public class MqttErrorHandler implements ErrorHandler {
    @Override
    public void handleError(Throwable throwable) {
        log.error("MqttErrorHandler:"+throwable.getMessage());
    }
}
