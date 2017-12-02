package samlen.tsoi.showcase.service;

import com.github.pagehelper.PageInfo;
import samlen.tsoi.showcase.entity.User;

/**
 * @author samlen_tsoi
 * @date 2017/12/3
 */
public interface UserReadService {

    /**
     * 分页查询
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<User> page(Integer pageNo, Integer pageSize);
}
