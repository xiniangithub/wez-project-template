package com.wez.common.aop;

import com.alibaba.fastjson.JSONObject;
import com.wez.common.annotations.Log;
import com.wez.common.utils.SPELUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;

/**
 *
 * @Author wez
 * @Date 2020/3/31
 */
@Slf4j
public class LogAOP {

    public static final String JSON_KEY = "logjson";

    @SneakyThrows
    public Object handlerLogMethod(ProceedingJoinPoint pjp) {
        long startTime = System.currentTimeMillis();

        Object result;

        try {
            putLogInfo2MDC(pjp);

            result = pjp.proceed();

            // 本次操作用时（毫秒）
            long elapsedTime = System.currentTimeMillis() - startTime;
            System.out.println(pjp.getSignature() + "use time: " + elapsedTime);
            log.info("[{}]use time: {}", pjp.getSignature(), elapsedTime);
        } finally {
            clearMDC();
        }

        return result;
    }

    private void clearMDC() {
        MDC.remove(JSON_KEY);
    }

    private void putLogInfo2MDC(ProceedingJoinPoint pjp) {
        // 得到方法上的注解
        MethodSignature signature = (MethodSignature) pjp.getSignature();

        Log logAnnotation = signature.getMethod().getAnnotation(Log.class);


        SPELUtil spel = new SPELUtil(pjp);

        JSONObject json = new JSONObject();

        // 使用单字母而不是全名，是为了节省日志文件大小。

        // 操作
        json.put("A", logAnnotation.action());

        // 对象类型
        json.put("T", logAnnotation.itemType());

        // 对象id，spel表达式
//        json.put("I", spel.cacl(logAnnotation.itemId()));

        // 其他参数，spel表达式
//        json.put("P", spel.cacl(logAnnotation.param()));

        MDC.put(JSON_KEY, json.toJSONString());
    }

}
