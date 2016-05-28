package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 *
 * @author Robin D'Haese
 */
@Component
@Aspect
public class InternalServiceLogger {

    private static final Logger LOGGER = LoggerFactory.getLogger(InternalServiceLogger.class);

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
        LOGGER.info(logText);
    }

    private void before(JoinPoint joinPoint) {
        String logText = String.format(
                "Method [%s] called",
                getMethod(joinPoint)
        );
        LOGGER.info(logText);

        if (LOGGER.isDebugEnabled()) {
            logArguments(joinPoint);
        }
    }

    private void logArguments(JoinPoint joinPoint) {
        LOGGER.debug("Arguments:");
        for (Object o : joinPoint.getArgs()) {
            if (o == null){
                LOGGER.debug("null");
            }else {
                if (o instanceof Object[]) {
                    LOGGER.debug(Arrays.toString((Object[]) o));
                } else {
                    LOGGER.debug(o.toString());
                }
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
        LOGGER.warn(logText, t);
        throw t;
    }

    private String getMethod(JoinPoint joinPoint) {
        return String.format(
                "%s.%s",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName()
        );
    }
}
