package com.wez.common.aop;

import com.wez.common.exception.CheckException;
import com.wez.common.entity.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @Author wez
 * @Date 2020/3/31
 */
public class ControllerAOP {

    private static final Logger logger = LoggerFactory.getLogger(ControllerAOP.class);

    public Object handlerControllerMethod(ProceedingJoinPoint pjp) {
        long startTime = System.currentTimeMillis();

        Result<?> result;

        try {
            result = (Result<?>) pjp.proceed();

            // 如果需要打印入参，可以从这里取出打印
            // Object[] args = pjp.getArgs();

            // 本次操作用时（毫秒）
            long elapsedTime = System.currentTimeMillis() - startTime;
            logger.info("[{}]use time: {}", pjp.getSignature(), elapsedTime);
        } catch (Throwable e) {
            result = handlerException(pjp, e);
        }

        return result;
    }

    private Result<?> handlerException(ProceedingJoinPoint pjp, Throwable e) {
        Result<?> result = new Result();

        // 已知异常【注意：已知异常不要打印堆栈，否则会干扰日志】
        // 校验出错，参数非法
        if (e instanceof CheckException || e instanceof IllegalArgumentException) {
            result.setRet(Result.ERROR);
            result.setMsg(e.getLocalizedMessage());
        }
        else {
            logger.error(pjp.getSignature() + " error ", e);

            result.setMsg(e.toString());
            result.setRet(Result.ERROR);
        }

        return result;
    }

}
