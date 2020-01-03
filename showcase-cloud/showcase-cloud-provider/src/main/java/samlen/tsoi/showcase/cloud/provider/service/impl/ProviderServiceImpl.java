package samlen.tsoi.showcase.cloud.provider.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import samlen.tsoi.showcase.cloud.provider.entity.po.Provider;
import samlen.tsoi.showcase.cloud.provider.mapper.ProviderMapper;
import samlen.tsoi.showcase.cloud.provider.service.IProviderService;

/**
 * <p>
 * 生产者 服务实现类
 * </p>
 *
 * @author Samlen_Tsoi
 * @since 2020-01-02
 */
@Slf4j
@Service
public class ProviderServiceImpl extends ServiceImpl<ProviderMapper, Provider> implements IProviderService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void failCall(String ip) {
        Provider provider = new Provider();
        provider.setIp("失败" + ip);
        this.save(provider);
        // 故意制造异常
        Object object = null;
        log.info(object.toString());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void successCall(String ip) {
        Provider provider = new Provider();
        provider.setIp("成功" + ip);
        this.save(provider);
    }
}
