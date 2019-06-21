package samlen.tsoi.showcase.cloud.feign.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author samlen_tsoi
 * @date 2019-04-03 17:35
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class VipUser extends User {
    private static final long serialVersionUID = 3379996367890820027L;
    /**
     * 失效日期
     */
    private String expireTime;
}
