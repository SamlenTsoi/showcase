package samlen.tsoi.showcase.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import samlen.tsoi.showcase.entity.ArticleEs;

/**
 * @author samlen_tsoi
 * @date 2018/1/18
 */
public interface ArticleEsRepository extends ElasticsearchRepository<ArticleEs, Long> {
}
