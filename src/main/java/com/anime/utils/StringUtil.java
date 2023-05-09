package com.anime.utils;

import java.text.MessageFormat;
import java.util.Collection;

import static com.anime.configure.Const.*;

public class StringUtil {

    public static boolean isNull(Object o){return o == null;}

    public static boolean notNull(Object o){return !isNull(o);}
    public static boolean isBlank(String str) {
        return null == str || "".equals(str.trim());
    }

    public static String replaceBlank(String oldStr) {
        return isNotBlank(oldStr) ? oldStr : "";
    }
    public static String replaceBlank(String oldStr, String newStr) {
        return isNotBlank(oldStr) ? oldStr : newStr;
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static boolean objIsEmpty(Object object) {
        return object == null;
    }

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.size() == 0;
    }
}
