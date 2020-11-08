package com.spring.basics.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class Logging {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.spring.basics.controller.*.*(..))")
    private void anyInController(){}

    @Pointcut("execution(* com.spring.basics.Service.*.*(..))")
    private void anyInService(){}

    @Pointcut("execution(* com.spring.basics.DAO.*.*(..))")
    private void anyInDAO(){}

    @Pointcut("anyInController() || anyInService() || anyInDAO()")
    private void anyOfTherePackages(){}

    @Before("anyOfTherePackages()")
    public void beforeTherePackages(JoinPoint joinPoint){
        String signature = joinPoint.getSignature().toLongString();
        logger.info("=====================> In the name of @Before " + signature);

        Object args[] = joinPoint.getArgs();
        for(Object temp : args){
            logger.info("=!=!=!=!=!=!=!=!=!====> my param's " + temp);
        }
    }
    @AfterReturning(
            pointcut = "anyOfTherePackages()",
            returning = "result"
    )
    public void afterReturningThreePackages(JoinPoint joinPoint, Object result){
        String signature = joinPoint.getSignature().toShortString();
        logger.info("============== in the name of @AfterReturning " + signature);

        logger.info("========= the result is " + result);
    }
}
