package samlen.tsoi.showcase.service;

import samlen.tsoi.showcase.entity.User;

/**
 * @author samlen_tsoi
 * @date 2017/12/1
 */
public interface UserWriteService {

    /**
     * 创建
     * @param user
     */
    void insertOne(User user);
}
