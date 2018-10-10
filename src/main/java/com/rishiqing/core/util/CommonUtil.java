package com.rishiqing.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CommonUtil {


    /**
     * 将字符串分割为两个日期，第一个日期用first存储，第二个用second存储
     * @param dateStr 支持格式“yyyy-MM-dd,”, ",yyyy-MM-dd", “yyyy-MM-dd,yyyy-MM-dd”
     * @return
     */
    public static Map<String, Date> dateSplit(String dateStr){
        //定义返回值
        Map<String, Date> resMap = new HashMap<>();
        resMap.put("first", null);
        resMap.put("second", null);
        if(dateStr == null || dateStr.equals("")){
            return resMap;
        }

        //日期解析
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        //分割字符串
        String[] arr = dateStr.split(",");
        String firstStr = arr[0];
        String secondStr = arr[1];
        try {
            if(firstStr != null && !firstStr.equals("")){
                Date firstDate = sdf.parse(firstStr);
                resMap.put("first", firstDate);
            }
            if(secondStr != null && !secondStr.equals("")){
                Date secondDate = sdf.parse(secondStr);
                resMap.put("second", secondDate);
            }
        } catch (ParseException e) {
            System.out.println("====查询日期格式错误！");
        }
        return resMap;
    }

    /**
     * 当前日期加n天
     */
    public static Date addDays(Date date, int addDay){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, addDay);
        return calendar.getTime();
    }

}
