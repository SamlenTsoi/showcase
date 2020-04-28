package samlen.tsoi.showcase.shiro.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author samlen_tsoi
 * @date 2020/4/28
 **/
@Data
public class LoginUser implements Serializable {
    private String username;

    private String password;
}
