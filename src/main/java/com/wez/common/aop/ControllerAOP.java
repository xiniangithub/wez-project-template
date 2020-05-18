package com.wez.common.aop;

import com.wez.common.exception.CheckException;
import com.wez.common.annotations.SystemLog;
import com.wez.common.entity.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 接口异常处理AOP
 * @Author wez
 * @Date 2020/3/31
 */
//@Component("controllerAop")
public class ControllerAOP {

    private static final Logger logger = LoggerFactory.getLogger(ControllerAOP.class);

    public Object handlerControllerMethod(ProceedingJoinPoint pjp) {
        

        Result<?> result;

        try {
            result = (Result<?>) pjp.proceed();

            // 如果需要打印入参，可以从这里取出打印
            // Object[] args = pjp.getArgs();
        } catch (Throwable e) {
            result = handlerException(pjp, e);
        }

        return result;
    }

    private Result<?> handlerException(ProceedingJoinPoint pjp, Throwable e) {
        Result<?> result = new Result<>();

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
    
    /**
     * 记录系统日志
     * @param pjp
     */
    /*private void recordSystemLog(ProceedingJoinPoint pjp, Throwable e) {
        // 得到方法上的注解
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        
        SystemLog systemLog = signature.getMethod().getAnnotation(SystemLog.class);
        System.out.println(String.format("%s,  %s", systemLog.exceptionMessage(), e.getMessage()));
        
//        logger.error("", e);
    }*/

}
