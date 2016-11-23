package com.levent_j.timetable.utils;

/**
 * Created by levent_j on 16-11-23.
 */
public class TimeUtil {
    public static String format(String time) {
        char[] chars = time.toCharArray();
        String date = ((chars[0]=='0')?"":String.valueOf(chars[0]))
                +String.valueOf(chars[1]);
        String day = ((chars[2]=='0')?"":String.valueOf(chars[2]))
                +String.valueOf(chars[3]);

        return date+"月"+day+"号";
    }
}
