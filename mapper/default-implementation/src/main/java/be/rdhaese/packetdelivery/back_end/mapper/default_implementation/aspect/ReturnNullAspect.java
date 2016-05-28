package be.rdhaese.packetdelivery.back_end.mapper.default_implementation.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 *
 * @author Robin D'Haese
 */
@Aspect
@Component
public class ReturnNullAspect {

    @Around("execution(* be.rdhaese.packetdelivery.back_end.mapper.interfaces.Mapper.*(..))")
    public Object returnNullWhenArgumentIsNull(ProceedingJoinPoint joinPoint) throws Throwable {
        if (joinPoint.getArgs().length > 0) {

            boolean oneArgNull = false;
            for (Object arg : joinPoint.getArgs()){
                if (arg == null){
                    oneArgNull = true;
                    break;
                }
            }
            if (oneArgNull) {
                return null;
            }
        }
        return joinPoint.proceed();
    }
}
