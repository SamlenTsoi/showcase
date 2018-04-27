package samlen.tsoi.showcase.es.service;

import samlen.tsoi.showcase.es.entity.po.User;
import samlen.tsoi.showcase.es.entity.vo.UserQuery;

import java.util.List;

/**
 * 用户Service
 *
 * @author samlen_tsoi
 * @date 2018/4/27
 */
public interface UserService {

    /**
     * 保存用户信心
     *
     * @param user
     */
    void save(User user);

    /**
     * 搜索
     *
     * @param userQuery
     * @return
     */
    List<User> search(UserQuery userQuery);
}
