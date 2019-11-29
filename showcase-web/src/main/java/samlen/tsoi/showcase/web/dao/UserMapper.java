package samlen.tsoi.showcase.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import samlen.tsoi.showcase.web.entity.po.User;

/**
 * 用户Mapper
 *
 * @author samlen_tsoi
 * @date 2018/1/20
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 分页
     *
     * @param page
     * @return
     */
    Page<User> selectPage(Page page);
}