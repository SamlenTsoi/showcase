package samlen.tsoi.showcase.web.resolver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import samlen.tsoi.showcase.web.annotation.UserAnnotation;
import samlen.tsoi.showcase.web.entity.po.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author samlen_tsoi
 * @date 2019-07-10 17:14
 **/
@Slf4j
@Component
public class UserResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(UserAnnotation.class) && parameter.getParameterType().equals(User.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            log.info("头部为：{}", headerNames.nextElement());
        }
        User user = new User();
        user.setName("samlen");
        return user;
    }
}
