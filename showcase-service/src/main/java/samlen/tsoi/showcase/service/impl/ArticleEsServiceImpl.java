package samlen.tsoi.showcase.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import samlen.tsoi.showcase.dao.ArticleEsRepository;
import samlen.tsoi.showcase.entity.po.ArticleEs;
import samlen.tsoi.showcase.service.ArticleEsService;


/**
 * es文章写Service实现
 *
 * @author samlen_tsoi
 * @date 2018/1/20
 */
@Slf4j
@Service
public class ArticleEsServiceImpl implements ArticleEsService {

    @Autowired
    private ArticleEsRepository articleEsRepository;

    @Override
    public void save(ArticleEs articleEs) {
        articleEsRepository.save(articleEs);
    }

    @Override
    public ArticleEs get(Long id) {
        return articleEsRepository.findOne(id);
    }

    @Override
    public Page<ArticleEs> search(Integer pageNum, Integer pageSize) {
        Pageable pageable = new PageRequest(pageNum, pageSize);
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
                .withPageable(pageable)
                .withSort(SortBuilders.fieldSort("id").order(SortOrder.DESC))
                .build();
        log.info("DSL >>>>> {}", searchQuery.getQuery().toString());
        Page<ArticleEs> articleEsPage = articleEsRepository.search(searchQuery);
        return articleEsPage;
    }
}
