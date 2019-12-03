package samlen.tsoi.showcase.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import samlen.tsoi.showcase.web.entity.po.User;
import samlen.tsoi.showcase.web.mapper.UserMapper;
import samlen.tsoi.showcase.web.service.IUserService;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Samlen_Tsoi
 * @since 2019-12-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
