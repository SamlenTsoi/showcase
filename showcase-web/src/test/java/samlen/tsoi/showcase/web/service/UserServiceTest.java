package samlen.tsoi.showcase.web.service;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import samlen.tsoi.showcase.web.BaseTest;
import samlen.tsoi.showcase.web.entity.po.User;

import java.util.List;

/**
 * UserService测试类
 *
 * @author samlen_tsoi
 * @date 2019/12/26
 **/
@Slf4j
public class UserServiceTest extends BaseTest {
    @Autowired
    private IUserService userService;

    @Test
    public void list() {
        List<User> userList = userService.list();
        userList.forEach(user -> log.info("user -> {}", JSON.toJSONString(user)));
    }
}
