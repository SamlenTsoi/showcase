package samlen.tsoi.showcase.web.entity.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 用户信息po
 *
 * @author samlen_tsoi
 * @date 2018/1/25
 */
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "created_at")
    private Date createdAt;

    /**
     * 更新时间
     */
    @Column(name = "updated_at")
    private Date updatedAt;
}