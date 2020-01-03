package samlen.tsoi.showcase.cloud.provider.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import samlen.tsoi.showcase.cloud.feign.api.ProviderFeignService;
import samlen.tsoi.showcase.cloud.provider.service.IProviderService;
import samlen.tsoi.showcase.common.util.IpUtils;
import samlent.tsoi.showcase.core.dto.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 生产者 前端控制器
 * </p>
 *
 * @author Samlen_Tsoi
 * @since 2020-01-02
 */
@RestController
@RequestMapping("provider")
public class ProviderController implements ProviderFeignService {
    @Autowired
    private IProviderService providerService;

    @Autowired
    private HttpServletRequest request;

    @Override
    public Result failCall() {
        providerService.failCall(IpUtils.getIpAddr(request));
        return Result.ok();
    }

    @Override
    public Result successCall() {
        providerService.successCall(IpUtils.getIpAddr(request));
        return Result.ok();
    }
}
