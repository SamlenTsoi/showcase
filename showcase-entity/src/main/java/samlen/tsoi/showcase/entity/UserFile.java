package samlen.tsoi.showcase.entity;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "user_file")
public class UserFile {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 小粉橡用户ID
     */
    private Long owner;

    /**
     * 文件名称
     */
    @Column(name = "file_name")
    private String fileName;

    /**
     * 文件类型，1-压缩包 2-zip压缩包 3-pdf文档 4-镜像文件 5-网页 6-视频文件 7-excel  8-word 9-记事本 10-图片 11-音频 20-其他
     */
    @Column(name = "file_type")
    private Integer fileType;

    /**
     * oss路径
     */
    @Column(name = "oss_path")
    private String ossPath;

    /**
     * 文件额外信息，json形式
     */
    private String extra;

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