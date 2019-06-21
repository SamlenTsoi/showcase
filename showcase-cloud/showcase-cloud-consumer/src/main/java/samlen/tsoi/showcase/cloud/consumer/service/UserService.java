package samlen.tsoi.showcase.cloud.consumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import samlen.tsoi.showcase.cloud.feign.api.UserFeignService;

/**
 * @author samlen_tsoi
 * @date 2018/9/10
 */
@FeignClient(name = "showcase-provider", path = "user")
public interface UserService extends UserFeignService {
}
