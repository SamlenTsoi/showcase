package samlen.tsoi.showcase.web.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import samlen.tsoi.showcase.web.resolver.UserResolver;

import java.util.List;

/**
 * @author samlen_tsoi
 * @date 2019-07-10 17:13
 **/
@Configuration
public class UserArgResolverConfig implements WebMvcConfigurer {
    @Autowired
    private UserResolver userResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userResolver);
    }
}
