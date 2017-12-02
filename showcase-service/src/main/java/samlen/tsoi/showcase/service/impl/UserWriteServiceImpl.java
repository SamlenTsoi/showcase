package samlen.tsoi.showcase.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import samlen.tsoi.showcase.entity.User;
import samlen.tsoi.showcase.service.UserWriteService;
import samlen.tsoi.showcase.service.dao.UserMapper;

/**
 * @author samlen_tsoi
 * @date 2017/12/1
 */
@Service
public class UserWriteServiceImpl implements UserWriteService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void insertOne(User user) {
        userMapper.insertSelective(user);
    }
}
