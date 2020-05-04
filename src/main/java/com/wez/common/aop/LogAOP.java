package com.wez.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 接口日志AOP
 * @Author wez
 * @Date 2020/3/31
 */
public class LogAOP {

    private static final Logger logger = LoggerFactory.getLogger(LogAOP.class);

    public Object handlerLogMethod(ProceedingJoinPoint pjp) {
        long startTime = System.currentTimeMillis();
        Object result = null;

        try {
            result = pjp.proceed();
            
            long elapsedTime = System.currentTimeMillis() - startTime;
            logger.info("[{}] use time: {} ms", pjp.getSignature(), elapsedTime);
        } catch (Throwable e) {
            logger.error("", e);
        }

        return result;
    }

}
