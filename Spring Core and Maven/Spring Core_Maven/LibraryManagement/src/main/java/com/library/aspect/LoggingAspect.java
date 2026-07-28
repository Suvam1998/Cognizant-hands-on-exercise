package com.library.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Cross-cutting logging (Exercises 3 & 8):
 *  - @Around logs method execution time,
 *  - @Before / @After log around the call.
 * Applies to every method in com.library.service.
 */
@Aspect
@Component
public class LoggingAspect {

    private static final String POINTCUT = "execution(* com.library.service.*.*(..))";

    @Before(POINTCUT)
    public void beforeAdvice(JoinPoint jp) {
        System.out.println("[AOP] Before  -> " + jp.getSignature().toShortString());
    }

    @After(POINTCUT)
    public void afterAdvice(JoinPoint jp) {
        System.out.println("[AOP] After   -> " + jp.getSignature().toShortString());
    }

    @Around(POINTCUT)
    public Object logExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        long elapsed = System.currentTimeMillis() - start;
        System.out.println("[AOP] Timing  -> " + pjp.getSignature().toShortString()
                + " executed in " + elapsed + " ms");
        return result;
    }
}
