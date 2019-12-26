package samlen.tsoi.showcase.web;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试基类
 *
 * @author samlen_tsoi
 * @date 2018/4/26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BaseTestConfiguration.class)
//@ActiveProfiles("test")
public abstract class BaseTest {

}
