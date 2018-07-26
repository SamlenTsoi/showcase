package samlen.tsoi.showcase.storm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/**
 * 应用启动类
 *
 * @author samlen_tsoi
 * @date 2018/7/26
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
public class StormApplication {

    public static void main(String[] args) {
        SpringApplication.run(StormApplication.class, args);
    }
}
