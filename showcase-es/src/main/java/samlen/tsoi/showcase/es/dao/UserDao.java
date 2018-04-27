package samlen.tsoi.showcase.es.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import samlen.tsoi.showcase.es.entity.po.User;

/**
 * 用户dao
 *
 * @author samlen_tsoi
 * @date 2018/4/27
 */
public interface UserDao extends ElasticsearchRepository<User, Long> {
}
