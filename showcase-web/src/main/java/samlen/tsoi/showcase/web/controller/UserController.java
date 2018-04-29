package samlen.tsoi.showcase.web.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import samlen.tsoi.showcase.web.entity.po.User;
import samlen.tsoi.showcase.web.service.UserReadService;
import samlen.tsoi.showcase.web.service.UserWriteService;

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
    @ApiOperation(value = "分页获取用户", notes = "这是notes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "一页大小", required = true, dataType = "Integer")
    })
    @GetMapping("page")
    public PageInfo<User> page(@RequestParam("pageNum") Integer pageNum,
                               @RequestParam("pageSize") Integer pageSize) {
        return userReadService.page(pageNum, pageSize);
    }
}
