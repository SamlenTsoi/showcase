package samlen.tsoi.showcase.cloud.provider.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import samlen.tsoi.showcase.cloud.feign.api.UserFeignService;
import samlen.tsoi.showcase.cloud.feign.dto.FeignDTO;
import samlen.tsoi.showcase.cloud.feign.dto.User;

/**
 * 用户Controller
 *
 * @author samlen_tsoi
 * @date 2018/9/10
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserController implements UserFeignService {
    @Override
    public User get() {
        User user = new User();
        user.setAge(22);
        user.setName("Samlen_Tsoi");
        return user;
    }

    @Override
    public void post(@RequestBody FeignDTO feignDTO) {
        log.info("user -> {}", JSON.toJSONString(feignDTO.getObj()));
    }
}
