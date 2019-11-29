package samlen.tsoi.showcase.web.entity.po;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 用户信息po
 *
 * @author samlen_tsoi
 * @date 2018/1/25
 */
@Data
@TableName("user")
public class User {
    @TableId(type = IdType.AUTO)
    @Excel(name = "用户ID")
    private Long id;

    /**
     * 用户名
     */
    @TableField("name")
    @Excel(name = "用户名")
    private String name;

    /**
     * 邮件
     */
    @TableField("email")
    @Excel(name = "邮箱")
    private String email;

    /**
     * 手机号码
     */
    @TableField("mobile")
    @Excel(name = "手机号码")
    private String mobile;

    /**
     * 登录密码
     */
    @TableField("password")
    @Excel(name = "登录密码")
    private String password;

    /**
     * 创建时间
     */
    @TableField("create_at")
    @Excel(name = "创建时间", exportFormat = "yyyy-MM-dd HH:mm:ss", importFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date createdAt;

    /**
     * 更新时间
     */
    @TableField("update_at")
    @Excel(name = "进校日期", exportFormat = "yyyy-MM-dd HH:mm:ss", importFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date updatedAt;
}