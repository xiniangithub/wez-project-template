package com.wez.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.wez.common.annotations.AuditLog;
import com.wez.common.utils.I18NUtil;
import com.wez.common.utils.LoggerUtil;
import com.wez.config.CommonProperties;

/**
 * 接口日志AOP
 * @Author wez
 * @Date 2020/3/31
 */
public class LogAOP {

    private static final Logger logger = LoggerFactory.getLogger(LogAOP.class);
    
    @Autowired
    private CommonProperties commonProperties;
    @Autowired
    private I18NUtil i18nUtil;
    @Autowired
    private LoggerUtil loggerUtil;
    
    
    public Object handlerLogMethod(ProceedingJoinPoint pjp) {
        long startTime = System.currentTimeMillis();
        Object result = null;

        try {
            result = pjp.proceed();
            
            long elapsedTime = System.currentTimeMillis() - startTime;
            
            if ("1".equals(commonProperties.getOpenAuditLog())) {
                this.recordAuditLog(pjp);
            }
            logger.debug("{} use time: {} ms", pjp.getSignature(), elapsedTime);
        } catch(Throwable e) {
            logger.error(e.getMessage(), e);
        }

        return result;
    }
    
    /**
     * 记录审计日志
     * @param pjp
     */
    private void recordAuditLog(ProceedingJoinPoint pjp) {
        // 得到方法上的注解
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        
        AuditLog auditLog = signature.getMethod().getAnnotation(AuditLog.class);
        loggerUtil.audit(i18nUtil.getMessage(auditLog.messageKey()));
    }

}
