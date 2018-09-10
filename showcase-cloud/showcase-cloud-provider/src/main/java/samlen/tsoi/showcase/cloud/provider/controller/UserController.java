package samlen.tsoi.showcase.cloud.provider.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import samlen.tsoi.showcase.cloud.feign.api.UserFeignService;
import samlen.tsoi.showcase.cloud.feign.dto.User;

/**
 * 用户Controller
 *
 * @author samlen_tsoi
 * @date 2018/9/10
 */
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
}
