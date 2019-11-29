package samlen.tsoi.showcase.web.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import samlen.tsoi.showcase.web.entity.po.User;

/**
 * @author samlen_tsoi
 * @date 2017/12/3
 */
public interface UserReadService extends IService<User> {

    /**
     * 分页查询
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<User> page(Integer pageNo, Integer pageSize);
}
