package samlen.tsoi.showcase.cloud.feign.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import samlent.tsoi.showcase.core.dto.Result;

/**
 * 生产者feign接口
 *
 * @author samlen_tsoi
 * @date 2020/1/2
 **/
@FeignClient(name = "showcase-provider", path = "provider")
public interface ProviderFeignService {
    /**
     * 失败调用
     *
     * @return
     */
    @GetMapping("failCall")
    Result failCall();

    /**
     * 成功的调用
     *
     * @return
     */
    @GetMapping("successCall")
    Result successCall();
}
