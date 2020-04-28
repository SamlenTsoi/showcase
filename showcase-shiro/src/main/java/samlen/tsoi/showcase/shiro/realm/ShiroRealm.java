package samlen.tsoi.showcase.shiro.realm;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import samlen.tsoi.showcase.shiro.pojo.LoginUser;


/**
 * @author samlen_tsoi
 * @date 2020/4/28
 **/
@Slf4j
public class ShiroRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("doGetAuthorizationInfo...begin");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // TODO 自己实现获取权限
        info.addStringPermission("user:get");
        log.info("doGetAuthorizationInfo...end");
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("doGetAuthenticationInfo...begin");
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        LoginUser loginUser = new LoginUser();
        loginUser.setUsername(usernamePasswordToken.getUsername());
        String password = "";
        for (char c : usernamePasswordToken.getPassword()) {
            password = password + c;
        }
        loginUser.setPassword(password);
        log.info("doGetAuthenticationInfo...end");
        return new SimpleAuthenticationInfo(loginUser, loginUser.getPassword(), getName());
    }
}
