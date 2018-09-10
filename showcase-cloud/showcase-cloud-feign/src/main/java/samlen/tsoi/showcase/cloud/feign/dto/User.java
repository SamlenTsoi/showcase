package samlen.tsoi.showcase.cloud.feign.dto;

import lombok.Data;

/**
 * @author samlen_tsoi
 * @date 2018/9/10
 */
@Data
public class User {
    /**
     * 昵称
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;
}
