package samlen.tsoi.showcase.web.entity.po;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    @Excel(name = "用户ID")
    private Long id;

    /**
     * 用户名
     */
    @Excel(name = "用户名")
    private String name;

    /**
     * 邮件
     */
    @Excel(name = "邮箱")
    private String email;

    /**
     * 手机号码
     */
    @Excel(name = "手机号码")
    private String mobile;

    /**
     * 登录密码
     */
    @Excel(name = "登录密码")
    private String password;

    /**
     * 创建时间
     */
    @Excel(name = "创建时间", exportFormat = "yyyy-MM-dd HH:mm:ss", importFormat = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date createdAt;

    /**
     * 更新时间
     */
    @Excel(name = "进校日期", exportFormat = "yyyy-MM-dd HH:mm:ss", importFormat = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date updatedAt;
}