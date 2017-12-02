package samlen.tsoi.showcase.entity;

import java.util.Date;
import lombok.Data;

@Data
public class User {
    /**
     * 
     */
    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 邮件
     */
    private String email;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;
}