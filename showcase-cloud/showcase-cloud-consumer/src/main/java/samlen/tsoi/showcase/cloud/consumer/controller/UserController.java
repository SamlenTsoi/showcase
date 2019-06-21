package samlen.tsoi.showcase.cloud.consumer.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import samlen.tsoi.showcase.cloud.consumer.service.UserService;
import samlen.tsoi.showcase.cloud.feign.dto.FeignDTO;
import samlen.tsoi.showcase.cloud.feign.dto.User;
import samlen.tsoi.showcase.cloud.feign.dto.VipUser;

import java.util.Date;

/**
 * @author samlen_tsoi
 * @date 2018/9/10
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("get")
    public User get() {
        return userService.get();
    }

    @PostMapping("post")
    public void post(@RequestBody FeignDTO<VipUser> feignDTO) {
        log.info("feignDTO -> {}", JSON.toJSONString(feignDTO.getObj()));
        userService.post(feignDTO);
    }
}
