package samlen.tsoi.showcase.web.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import samlen.tsoi.showcase.web.dao.UserMapper;
import samlen.tsoi.showcase.web.entity.po.User;
import samlen.tsoi.showcase.web.service.UserReadService;

/**
 * @author samlen_tsoi
 * @date 2017/12/3
 */
@Slf4j
@Service
public class UserReadServiceImpl extends ServiceImpl<UserMapper, User> implements UserReadService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Page<User> page(Integer pageNo, Integer pageSize) {
        Page<User> page = new Page<>(pageNo, pageSize);
        return userMapper.selectPage(page);
    }
}
