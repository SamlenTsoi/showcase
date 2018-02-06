package samlen.tsoi.showcase.web.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章实体类
 *
 * @author samlen_tsoi
 * @date 2018/1/18
 */
@Data
@Document(indexName = "es-article", type = "article")
public class ArticleEs implements Serializable {

    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 摘要
     */
    private String abstracts;

    /**
     * 内容
     */
    private String content;

    /**
     * 发表时间
     */
    private Date postTime;

    /**
     * 点击率
     */
    private Long clickCount;
}
