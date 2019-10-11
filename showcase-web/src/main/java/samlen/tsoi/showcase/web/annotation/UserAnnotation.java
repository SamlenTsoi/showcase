package samlen.tsoi.showcase.web.annotation;

import java.lang.annotation.*;

/**
 * @author samlen_tsoi
 * @date 2019-07-10 17:15
 **/
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserAnnotation {
}
