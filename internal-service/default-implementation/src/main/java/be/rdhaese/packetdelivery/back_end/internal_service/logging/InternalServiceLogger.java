package be.rdhaese.packetdelivery.back_end.internal_service.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created on 25/04/2016.
 *
 * @author Robin D'Haese
 */
@Component
@Aspect
public class InternalServiceLogger {

    private Logger logger = LoggerFactory.getLogger(InternalServiceLogger.class);

    @Around(value = "execution(* be.rdhaese.packetdelivery.back_end.internal_service.interfaces..*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        before(joinPoint);

        Object result = proceed(joinPoint);

        afterResult(joinPoint, result);
        return result;
    }

    private void afterResult(JoinPoint joinPoint, Object result) {
        String logText = String.format(
                "Returning result [%s] for method [%s]",
                result,
                getMethod(joinPoint)
        );
        logger.info(logText);
    }

    private void before(JoinPoint joinPoint) {
        String logText = String.format(
                "Method [%s] called",
                getMethod(joinPoint)
        );
        logger.info(logText);

        if (logger.isDebugEnabled()) {
            logArguments(joinPoint);
        }
    }

    private void logArguments(JoinPoint joinPoint) {
        logger.debug("Arguments:");
        for (Object o : joinPoint.getArgs()) {
            if (o instanceof Object[]) {
                logger.debug(Arrays.toString((Object[]) o));
            } else {
                logger.debug(o.toString());
            }
        }
    }

    private Object proceed(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable t) {
            exceptionThrown(joinPoint, t);
        }

        return result;
    }

    private void exceptionThrown(JoinPoint joinPoint, Throwable t) throws Throwable {
        String logText = String.format(
                "Exception while performing method [%s]",
                getMethod(joinPoint)
        );
        logger.warn(logText, t);
        throw t;
    }

    private String getMethod(JoinPoint joinPoint){
        return String.format(
                "%s.%s",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName()
        );
    }
}
