package samlen.tsoi.showcase.cloud.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import samlen.tsoi.showcase.cloud.consumer.service.UserService;
import samlen.tsoi.showcase.cloud.feign.dto.User;

/**
 * @author samlen_tsoi
 * @date 2018/9/10
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("get")
    public User get() {
        return userService.get();
    }
}
