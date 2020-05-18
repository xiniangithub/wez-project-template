package com.wez.common.utils;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.wez.config.CommonProperties;

@Component
public class I18NUtil {
    
    private static final LoggerUtil logger = new LoggerUtil();
    
    private static Locale LOCALE;
    private static MessageSource resources;
    
    @Autowired
    private CommonProperties commonProperties;
    
    @PostConstruct
    private void init() {
        String language = commonProperties.getLanguage();
        logger.info(String.format("当前多语言配置: %s", language));
        
        switch (language) {
            case "en_US":
                LOCALE = Locale.US;
                break;
            default:
                LOCALE = Locale.CHINESE;
        }
        
        logger.info(String.format("初始化多语言: %s", LOCALE));
    }
    
    public String getMessage(String msgKey, Object... args) {
        try {
            return new String(resources.getMessage(msgKey, args, LOCALE).getBytes("ISO-8859-1"), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    
    public static void setResources(MessageSource resources) {
        I18NUtil.resources = resources;
    }

}
