package com.rishiqing.core.util;

import com.rishiqing.core.constant.RsqSystemConstants;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;
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

    public static Date delHHMMSS(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String s = sdf.format(date);
        Date newDate = null;
        try {
            newDate = sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newDate;
    }


    /**
     * 获取一个随机订单号
     * @return
     * @author codingR
     * @date 2018/8/4 15:43
     */
    public static String getOutTradeNo () {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        StringBuffer buffer = new StringBuffer(sdf.format(date));
        Integer bit = RsqSystemConstants.OUT_TRADE_NO_MAX_BIT - buffer.length();
        buffer.append(randomNum(bit));
        return buffer.toString();
    }

    /**
     * 获得一个指定位数的随机数
     * @param num   位数
     * @return
     */
    public static String randomNum(int num){
        SecureRandom ranGen = new SecureRandom();
        StringBuffer sb = new StringBuffer("");
        for (int i = 0 ; i < num ; i ++) {
            sb.append(ranGen.nextInt(10));
        }
        return sb.toString();
    }

    /**
     * 基本版订单 body
     */
    public static String getBodyForBase(Integer userLimit,Integer days){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.DATE, days);
            Date overDate = cal.getTime();

            StringBuffer sbf = new StringBuffer();
            sbf.append("购买后可容纳");
            if(userLimit == -1){
                sbf.append("无限");
            }else{
                sbf.append(userLimit);
            }
            sbf.append("个成员，");
            sbf.append(days);
            sbf.append("天，");
            sbf.append(sdf.format(overDate));
            sbf.append("到期");
            return sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 试用版订单 body
     */
    public static String getBodyForTrial(Integer userLimit,Integer days){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.DATE, days);
            Date overDate = cal.getTime();

            StringBuffer sbf = new StringBuffer();
            sbf.append("试用版可容纳");
            if(userLimit == -1){
                sbf.append("无限");
            }else{
                sbf.append(userLimit);
            }
            sbf.append("个成员，");
            sbf.append(days);
            sbf.append("天，");
            sbf.append(sdf.format(overDate));
            sbf.append("到期");
            return sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 基本版订单 body
     */
    public static String getBodyForBase(Integer userLimit,Date overDate){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");

            StringBuffer sbf = new StringBuffer();
            sbf.append("购买后可容纳");
            if(userLimit == -1){
                sbf.append("无限");
            }else{
                sbf.append(userLimit);
            }
            sbf.append("个成员，");
            sbf.append(getSubDay(new Date(), overDate));
            sbf.append("天，");
            sbf.append(sdf.format(overDate));
            sbf.append("到期");
            return sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 试用版订单 body
     */
    public static String getBodyForTrial(Integer userLimit,Date overDate){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
                        StringBuffer sbf = new StringBuffer();
            sbf.append("试用版可容纳");
            if(userLimit == -1){
                sbf.append("无限");
            }else{
                sbf.append(userLimit);
            }
            sbf.append("个成员，");
            sbf.append(getSubDay(new Date(), overDate));
            sbf.append("天，");
            sbf.append(sdf.format(overDate));
            sbf.append("到期");
            return sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获得日期相减 返回天数
     */
    public static int getSubDay(Date begin, Date end){
        long timeMil = end.getTime() -  begin.getTime();
        long day = timeMil / (1000*60*60*24);
        return (int)day;
    }
}
