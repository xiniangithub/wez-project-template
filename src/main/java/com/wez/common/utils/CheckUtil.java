package com.wez.common.utils;

import com.wez.common.exception.CheckException;
import org.springframework.context.MessageSource;

import java.util.Locale;

public class CheckUtil {

    private static MessageSource resources;

    private static Locale locale = Locale.ENGLISH;

    public static void setResources(MessageSource resources) {
        CheckUtil.resources = resources;
    }

    public static void check(boolean condition, String msgKey, Object... args) {
        if (!condition) {
            fail(msgKey, args);
        }
    }

    public static void notEmpty(String str, String msgKey, Object... args) {
        if (str == null || str.isEmpty()) {
            fail(msgKey, args);
        }
    }

    public static void notNull(Object obj, String msgKey, Object... args) {
        if (obj == null) {
            fail(msgKey, args);
        }
    }

    private static void fail(String msgKey, Object... args) throws CheckException {
        throw new CheckException(resources.getMessage(msgKey, args, locale));
    }

}
