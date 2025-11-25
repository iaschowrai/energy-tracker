package com.iaschowrai.user_service.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class ExecutionTimeAspect {

    private final Logger log = LoggerFactory.getLogger(ExecutionTimeAspect.class);

    @Pointcut("execution(* com.iaschowrai.user-service.controller.*.*(..))")
    public void controllerMethods(){}

    @Around("controllerMethods()")
    public Object measureExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.nanoTime();
        try{
            return pjp.proceed();
        }finally{
            long end = System.nanoTime();
            long elapsedNs = end - start;
            long elapsedMs = TimeUnit.NANOSECONDS.toMillis(elapsedNs);
            String signature = pjp.getSignature().toShortString();
            log.info("Controller method {} executed in {} ms", signature, elapsedMs);
        }
    }
}
