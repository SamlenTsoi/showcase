package samlen.tsoi.showcase.dubbo.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import samlen.tsoi.showcase.dubbo.api.DubboService;

/**
 * @author samlen_tsoi
 * @date 2018/4/28
 */
@Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.name}",
        registry = "${dubbo.registry.id}"
)
public class DuobboServiceImpl implements DubboService {

    @Override
    public String sayHello(String name) {
        return "Hello, " + name + " (from Spring Boot)";
    }
}
