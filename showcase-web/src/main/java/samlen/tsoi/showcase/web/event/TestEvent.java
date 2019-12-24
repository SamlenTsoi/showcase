package samlen.tsoi.showcase.web.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 测试事件
 *
 * @author samlen_tsoi
 * @date 2019/12/24
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestEvent  {
    private String name;

    private int age;
}
