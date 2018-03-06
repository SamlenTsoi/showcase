package samlen.tsoi.showcase.service;

import org.springframework.data.domain.Page;
import samlen.tsoi.showcase.entity.ArticleEs;

/**
 * es文章写Service
 *
 * @author samlen_tsoi
 * @date 2018/1/20
 */
public interface ArticleEsService {

    /**
     * 保存
     *
     * @param articleEs
     */
    void save(ArticleEs articleEs);

    /**
     * 获取
     *
     * @param id
     * @return
     */
    ArticleEs get(Long id);

    /**
     * 分页
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<ArticleEs> search(Integer pageNum, Integer pageSize);
}
