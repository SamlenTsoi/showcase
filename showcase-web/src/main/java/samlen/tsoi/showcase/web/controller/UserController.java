package samlen.tsoi.showcase.web.controller;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import samlen.tsoi.showcase.entity.po.User;
import samlen.tsoi.showcase.service.UserReadService;
import samlen.tsoi.showcase.service.UserWriteService;

/**
 * @author samlen_tsoi
 * @date 2017/12/1
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserWriteService userWriteService;

    @Autowired
    private UserReadService userReadService;

    /**
     * 创建
     * @param user
     */
    @PostMapping("add")
    public void create(@RequestBody User user) {
        userWriteService.insertOne(user);
    }

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("page")
    public PageInfo<User> page(@RequestParam("pageNum") Integer pageNum,
                               @RequestParam("pageSize") Integer pageSize) {
        return userReadService.page(pageNum, pageSize);
    }
}
