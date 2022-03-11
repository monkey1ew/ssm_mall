package com.fqh.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author 海盗狗
 * @version 1.0
 */
public class DateUtils {

    private static LocalDateTime nowDateTime;
    private static DateTimeFormatter dateTimeFormatter;
    private static SimpleDateFormat simpleDateFormat;

    static {
        nowDateTime = LocalDateTime.now();
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
    }

//    获取当前时间并格式化
    public static String formatDate() {
        return dateTimeFormatter.format(nowDateTime);
    }

//    计算时间差
    public static double calGapMinute(String time1, String time2) {
        if (time1 == null) {
            time1 = formatDate();
        }
        double minute = 0;
        try {
            Date date1, date2;
            date1 = simpleDateFormat.parse(time1);
            date2 = simpleDateFormat.parse(time2);
            double millisecond = date1.getTime() - date2.getTime();
            minute = millisecond / (60 * 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return minute;
    }
}
