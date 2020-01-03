package samlen.tsoi.showcase.cloud.provider.service;

import com.baomidou.mybatisplus.extension.service.IService;
import samlen.tsoi.showcase.cloud.provider.entity.po.Provider;

/**
 * <p>
 * 生产者 服务类
 * </p>
 *
 * @author Samlen_Tsoi
 * @since 2020-01-02
 */
public interface IProviderService extends IService<Provider> {
    /**
     * 失败调用
     *
     * @param ip 调用者ip
     */
    void failCall(String ip);

    /**
     * 成功调用
     *
     * @param ip 调用者ip
     */
    void successCall(String ip);
}
