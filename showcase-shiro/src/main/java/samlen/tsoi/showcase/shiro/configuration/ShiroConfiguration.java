package samlen.tsoi.showcase.shiro.configuration;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.util.StringUtils;
import samlen.tsoi.showcase.shiro.realm.ShiroRealm;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author samlen_tsoi
 * @date 2020/4/28
 **/
@Slf4j
@Configuration
@ConditionalOnProperty(prefix="shiro",name = "enable", havingValue = "true")
public class ShiroConfiguration {
    /**
     * redis端口
     */
    @Value("${spring.redis.port}")
    private String port;

    /**
     * redis地址
     */
    @Value("${spring.redis.host}")
    private String host;

    /**
     * redis密码
     */
    @Value("${spring.redis.password}")
    private String redisPassword;

    /**
     * 过滤器
     *
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        // 拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 过滤链定义，从上向下顺序执行，一般将/**放在最为下边
        // 登录
        filterChainDefinitionMap.put("/user/login", "anon");
        filterChainDefinitionMap.put("/**", "perms");
        // 未授权界面返回JSON
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 安全管理器，相当于SpringMVC中的DispatcherServlet或者Struts2中的FilterDispatcher，是Shiro的心脏；
     * 所有具体的交互都通过SecurityManager进行控制；它管理着所有Subject、且负责进行认证和授权、及会话、缓存的管理。
     *
     * @return
     */
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSessionManager(sessionManager());
        securityManager.setCacheManager(redisCacheManager());
        securityManager.setRealm(shiroRealm());
        return securityManager;
    }

    /**
     * 会话管理器
     *
     * @return
     */
    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }

    /**
     * 认证授权器，可以有1个或多个Realm，可以认为是安全实体数据源，即用于获取安全实体的；
     * 可以是JDBC实现，也可以是LDAP实现，或者内存实现等等；由用户提供；
     * 注意：Shiro不知道你的用户/权限存储在哪及以何种格式存储；所以我们一般在应用中都需要实现自己的 Realm；
     *
     * @return
     */
    @Bean
    public ShiroRealm shiroRealm() {
        return new ShiroRealm();
    }

    /**
     * 操作Redis，可自己实现
     *
     * @return
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        // 自定义redis key前缀
        redisSessionDAO.setKeyPrefix("showcase:shiro:session");
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * Redis缓存控制器，来管理如用户、角色、权限等的缓存的；因为这些数据基本上很少去改变，放到缓存中后可以提高访问的性能
     *
     * @return
     */
    @Bean
    public RedisCacheManager redisCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        // 自定义redis key前缀
        redisCacheManager.setKeyPrefix("showcase:shiro:");
        // 识别不同用户，将作为key的一部分
        redisCacheManager.setPrincipalIdFieldName("username");
        return redisCacheManager;
    }

    /**
     * Redis管理器，设置Redis ip、端口等
     *
     * @return
     */
    @Bean
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host + ":" + port);
        redisManager.setTimeout(0);
        if (!StringUtils.isEmpty(redisPassword)) {
            redisManager.setPassword(redisPassword);
        }
        return redisManager;
    }

    /******************************************注解支持******************************************/
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager());
        return advisor;
    }
}
