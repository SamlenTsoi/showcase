package samlen.tsoi.showcase.es.entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.util.Date;

/**
 * 用户信息
 *
 * @author samlen_tsoi
 * @date 2018/4/27
 */
@Data
@Document(indexName = "showcase", type = "user")
public class User {

    /**
     * 用户ID
     */
    @Id
    @Field(type = FieldType.Long)
    private Long id;

    /**
     * 年龄
     */
    @Field(type = FieldType.Integer)
    private Integer age;

    /**
     * 名称
     */
    @Field(type = FieldType.String, index = FieldIndex.analyzed)
    private String name;

    /**
     * 出生时间
     */
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date bornTime;

}
