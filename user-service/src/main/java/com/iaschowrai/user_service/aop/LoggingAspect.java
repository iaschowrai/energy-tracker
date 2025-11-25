package com.iaschowrai.user_service.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/*
In short
Global Exception Handling is a specific use case of AOP-like behavior (handling errors centrally).
Spring AOP is general-purpose and can handle anything that crosses multiple modules or methods.
So yes, instead of adding logging in every method, you can create an AOP aspect and automatically
log all methods in the application or a specific package.
 */

@Aspect
@Component

public class LoggingAspect {

    private final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.iaschowrai.user-service.*.*(..))")
    public void serviceMethod(){}

    @Before("serviceMethods()")
    public void logBefore(JoinPoint joinPoint){
        log.info("Called service method: {} with arguments: {}", joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "serviceMethod()", returning = "results")
    public void logAfterReturning(JoinPoint joinPoint, Object result){
        log.info("Service method: {}, returned: {}", joinPoint.getSignature().getName(), result);
    }
}
