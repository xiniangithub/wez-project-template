package com.wez.common.utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggerUtil {
    
    /** 系统日志 */
    private static final Logger systemlogger = LoggerFactory.getLogger(LoggerUtil.class);
    /** 审计日志 */
    private static final org.apache.logging.log4j.Logger auditLogger = LogManager.getLogger(LoggerUtil.class);
    private static final Level level = Level.forName("AUDIT", 50);
    
    
    public void info(String message) {
        systemlogger.info(message);
    }
    
    
    public void audit(String message) {
        auditLogger.log(level, message);
    }

}
