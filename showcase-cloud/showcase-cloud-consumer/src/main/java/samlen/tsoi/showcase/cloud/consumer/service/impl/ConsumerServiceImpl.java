package samlen.tsoi.showcase.cloud.consumer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import samlen.tsoi.showcase.cloud.consumer.entity.po.Consumer;
import samlen.tsoi.showcase.cloud.consumer.mapper.ConsumerMapper;
import samlen.tsoi.showcase.cloud.consumer.service.IConsumerService;
import samlen.tsoi.showcase.cloud.feign.api.ProviderFeignService;

/**
 * <p>
 * 消费者 服务实现类
 * </p>
 *
 * @author Samlen_Tsoi
 * @since 2020-01-02
 */
@Service
public class ConsumerServiceImpl extends ServiceImpl<ConsumerMapper, Consumer> implements IConsumerService {
    @Autowired
    private ProviderFeignService providerFeignService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void call() {
        // 成功的调用
        providerFeignService.successCall();
        // 失败的调用
        providerFeignService.failCall();
    }
}
