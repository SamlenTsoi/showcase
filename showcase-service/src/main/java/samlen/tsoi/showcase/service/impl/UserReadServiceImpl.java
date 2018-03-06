package samlen.tsoi.showcase.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import samlen.tsoi.showcase.dao.UserMapper;
import samlen.tsoi.showcase.entity.User;
import samlen.tsoi.showcase.service.UserReadService;

/**
 * @author samlen_tsoi
 * @date 2017/12/3
 */
@Service
public class UserReadServiceImpl implements UserReadService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<User> page(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return new PageInfo<>(userMapper.selectAll());
    }
}
