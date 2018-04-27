package samlen.tsoi.showcase.es.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * ES配置类
 *
 * @author samlen_tsoi
 * @date 2018/4/27
 */
@Configuration
@EnableElasticsearchRepositories("samlen.tsoi.showcase.es.dao")
public class EsConfiguration {
}
