package samlen.tsoi.showcase.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 提供日志切面
 *
 * @author samlen_tsoi
 * @date 2019/12/5
 **/
@Aspect
@Component
public class SysLogAspect {

    @Pointcut("@annotation(samlen.tsoi.showcase.common.annotation.SysLog)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        //保存日志
//        saveSysLog(point, time);

        return result;
    }
}
