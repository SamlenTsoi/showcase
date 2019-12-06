package samlen.tsoi.showcase.common.annotation;

import java.lang.annotation.*;

/**
 * 系统日志注解
 *
 * @author samlen_tsoi
 * @date 2019/12/5
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    String value() default "";
}
