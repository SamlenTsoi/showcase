package samlen.tsoi.showcase.cloud.feign.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author samlen_tsoi
 * @date 2018/9/10
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 2849185510334087382L;
    /**
     * 昵称
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;
}
