package samlen.tsoi.showcase.web.service;


import com.baomidou.mybatisplus.extension.service.IService;
import samlen.tsoi.showcase.web.entity.po.User;

/**
 * @author samlen_tsoi
 * @date 2017/12/1
 */
public interface UserWriteService extends IService<User> {

    /**
     * 创建
     * @param user
     */
    void insertOne(User user);
}
