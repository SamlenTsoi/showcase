package samlen.tsoi.showcase.es.entity.vo;

import lombok.Data;

import java.util.Date;

/**
 * 用户搜索
 *
 * @author samlen_tsoi
 * @date 2018/4/27
 */
@Data
public class UserQuery {

    /**
     * id
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄范围开始
     */
    private Integer ageFrom;

    /**
     * 年龄范围结束
     */
    private Integer ageTo;

    /**
     * 出生日期范围开始
     */
    private Date bornTimeFrom;

    /**
     * 出生日期范围结束
     */
    private Date bornTimeTo;

    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 一页大小
     */
    private Integer pageSize;
}
