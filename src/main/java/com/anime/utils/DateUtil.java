package com.anime.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static Date getParseDate(String s) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        // 指定一個日期
        try {
            Date date = dateFormat.parse(s);
            return date;
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date getStartDate(String s, String year) {
        if (s.indexOf("-") != -1) {
            s = s.substring(0, s.indexOf("-")).trim();
        }
        return DateUtil.getParseDate(year + "/" + s.trim());
    }

    public static Date getEndDate(String s, String year) {
        if (s.indexOf("-") == -1) {
            return null;
        }
        s = s.substring(s.indexOf("-") + 1, s.length()).trim();
        if (s.split("/").length ==2){
            s = year + "/" + s;
        }
        return DateUtil.getParseDate(s);
    }

    public static String getRemoveChinese(String s) {
        return s.replaceAll("年", "/")
                .replaceAll("月", "/")
                .replaceAll("日", "");
    }
}
