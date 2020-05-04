package com.wez.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.wez.common.exception.CheckException;
import com.wez.config.CommonProperties;

@Component
public class CheckUtil {

    private static final LoggerUtil logger = new LoggerUtil();
    
    /** 多语言：当前语言 */
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
                LOCALE = Locale.SIMPLIFIED_CHINESE;
        }
        
        logger.info(String.format("初始化多语言: %s", LOCALE));
    }
    
    public static void check(boolean condition, String msgKey, Object... args) {
        if (condition) {
            fail(msgKey, args);
        }
    }

    public static void isBlank(String str, Object... args) {
        if (StringUtils.isBlank(str)) {
            fail("param.is.empty", args);
        }
    }
    
    public static void isEmpty(List<?> list, Object... args) {
        if (CollectionUtils.isEmpty(list)) {
            fail("param.is.empty", args);
        }
    }
    
    public static void isEmpty(Map<?, ?> params) {
        isEmpty(params, new String[] {});
    }
    
    public static void isEmpty(Map<?, ?> params, String[] validParamNames) {
        if (params == null || params.isEmpty()) {
            fail("param.is.empty");
        }
        
        if (validParamNames.length==0) {
            return;
        }
        
        List<String> emptityParamNames = new ArrayList<String>();
        for (String paramName : validParamNames) {
            if (!params.containsKey(paramName)) {
                emptityParamNames.add(paramName); 
            }
        }
        
        if (emptityParamNames.isEmpty()) {
            return;
        }
        
        fail("some.param.is.empty", StringUtils.join(emptityParamNames, ", "));
    }
    
    public static void isEmpty(Map<?, ?> map, Object... args) {
        if (map == null || map.isEmpty()) {
            fail("param.is.empty", args);
        }
    }

    public static void notNull(Object obj, Object... args) {
        if (obj == null) {
            fail("param.is.empty", args);
        }
    }

    private static void fail(String msgKey, Object... args) throws CheckException {
        throw new CheckException(resources.getMessage(msgKey, args, LOCALE));
    }
    
    public static void setResources(MessageSource resources) {
        CheckUtil.resources = resources;
    }
    
}
