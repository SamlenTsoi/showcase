package samlen.tsoi.showcase.web.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author samlen_tsoi
 * @date 2019/12/19
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "test")
public class TestProperties {
    private String name;

    private int age;
}
