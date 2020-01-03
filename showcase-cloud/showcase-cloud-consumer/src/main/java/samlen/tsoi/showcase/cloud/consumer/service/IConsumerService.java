package samlen.tsoi.showcase.cloud.consumer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import samlen.tsoi.showcase.cloud.consumer.entity.po.Consumer;

/**
 * <p>
 * 消费者 服务类
 * </p>
 *
 * @author Samlen_Tsoi
 * @since 2020-01-02
 */
public interface IConsumerService extends IService<Consumer> {
    /**
     * 调用
     */
    void call();
}
