package samlen.tsoi.showcase.cloud.feign.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import samlen.tsoi.showcase.cloud.feign.dto.FeignDTO;
import samlen.tsoi.showcase.cloud.feign.dto.User;

/**
 * 用户feign接口
 *
 * @author samlen_tsoi
 * @date 2018/9/10
 */
public interface UserFeignService {
    /**
     * 获取
     *
     * @return
     */
    @GetMapping("get")
    User get();

    /**
     * 传递
     *
     * @param feignDTO
     */
    @PostMapping("post")
    void post(@RequestBody FeignDTO feignDTO);
}
