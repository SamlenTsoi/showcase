package samlen.tsoi.showcase.es.service.impl;

import org.apache.commons.lang3.StringUtils;
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
import samlen.tsoi.showcase.es.dao.UserDao;
import samlen.tsoi.showcase.es.entity.po.User;
import samlen.tsoi.showcase.es.entity.vo.UserQuery;
import samlen.tsoi.showcase.es.service.UserService;

import java.util.List;

/**
 * 用户Service实现
 *
 * @author samlen_tsoi
 * @date 2018/4/27
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public List<User> search(UserQuery userQuery) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (userQuery.getId() != null) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("id", userQuery.getId()));
        }
        if (StringUtils.isNotBlank(userQuery.getName())) {
            boolQueryBuilder.filter(QueryBuilders.matchPhraseQuery("name", userQuery.getName()));
        }
        if (userQuery.getAgeFrom() != null) {
            boolQueryBuilder.filter(QueryBuilders.rangeQuery("age").from(userQuery.getAgeFrom()));
        }
        if (userQuery.getAgeTo() != null) {
            boolQueryBuilder.filter(QueryBuilders.rangeQuery("age").from(userQuery.getAgeTo()));
        }
        if (userQuery.getBornTimeFrom() != null) {
            boolQueryBuilder.filter(QueryBuilders.rangeQuery("bornTime").from(userQuery.getBornTimeFrom()));
        }
        if (userQuery.getBornTimeTo() != null) {
            boolQueryBuilder.filter(QueryBuilders.rangeQuery("bornTime").from(userQuery.getBornTimeTo()));
        }
        Pageable pageable = new PageRequest(userQuery.getPageNum() - 1, userQuery.getPageSize());
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        NativeSearchQuery searchQuery = nativeSearchQueryBuilder
                .withFilter(boolQueryBuilder)
                .withPageable(pageable)
                .withSort(SortBuilders.fieldSort("id").order(SortOrder.DESC))
                .build();
        Page<User> search = userDao.search(searchQuery);
        return search.getContent();
    }
}
