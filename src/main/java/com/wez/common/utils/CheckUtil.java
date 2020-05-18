package com.wez.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wez.common.exception.CheckException;

@Component
public class CheckUtil {

    @Autowired
    private static I18NUtil i18nUtil;
    
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
        throw new CheckException(i18nUtil.getMessage(msgKey, args));
    }
    
}
