package samlen.tsoi.showcase.es.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import samlen.tsoi.showcase.es.entity.po.User;
import samlen.tsoi.showcase.es.entity.vo.UserQuery;
import samlen.tsoi.showcase.es.service.UserService;
import samlent.tsoi.showcase.core.dto.Result;

import java.util.List;

/**
 * 用户Controller
 *
 * @author samlen_tsoi
 * @date 2018/4/27
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 保存
     *
     * @param user
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody User user) {
        userService.save(user);
        return Result.ok();
    }

    @PostMapping("search")
    public Result search(@RequestBody UserQuery userQuery) {
        List<User> userList = userService.search(userQuery);
        return Result.ok(userList);
    }
}
