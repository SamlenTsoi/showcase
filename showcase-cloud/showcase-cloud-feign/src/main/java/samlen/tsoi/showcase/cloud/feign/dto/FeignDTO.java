package samlen.tsoi.showcase.cloud.feign.dto;

import lombok.Data;

/**
 * @author samlen_tsoi
 * @date 2019-04-04 09:57
 **/
@Data
public class FeignDTO<T extends User> {
    private T obj;
}
