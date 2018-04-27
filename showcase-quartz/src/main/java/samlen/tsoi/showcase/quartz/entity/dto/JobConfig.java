package samlen.tsoi.showcase.quartz.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * quart任务配置实体类
 *
 * @author samlen_tsoi
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobConfig {

    /**
     * 任务id
     */
    private String id;

    /**
     * 任务类
     */
    private Class JobClass;

    /**
     * 所属组名
     */
    private String groupName;

    /**
     * 定时触发时间
     */
    private String cronTime;

    /**
     * 定时任务所需的额外数据
     */
    private Map<String, Object> jobData;
}