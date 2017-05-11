package com.example.mpandroidchart.aosp.mpchart.utils;

import com.brady.coreframe.utils.LogUtils;
import com.brady.coreframe.utils.dataprocess.StringUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Cuckoo
 * @date 2017-03-08
 * @description
 *      和时间相关的工具类
 */

public class DateUtil {

    /**
     * 英文简写如：12:01
     */
    public static String FORMAT_HM = "HH:mm";

    /**
     * 英文简写如：12:01:23
     */
    public static String FORMAT_HMS = "HH:mm:ss";

    /**
     * 英文简写如：12:01:23
     */
    public static String FORMAT_HMSZ = "hhmmsszzz";

    /**
     * 英文简写（默认）如：2010-12-01
     */
    public static String FORMAT_YMD = "yyyy-MM-dd";
    /**
     * 中文简写（默认）如：2010年12月01日
     */
    public static String FORMAT_YMD_CHINA = "yyyy年MM月dd日";

    /**
     * 英文简写（默认）如：20101201
     */
    public static String FORMAT_YYYYMMDD = "yyyyMMdd";

    /**
     * 英文全称  如：2010-12-01 23:15:06
     */
    public static String FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";
    public static String FORMAT_YMDHMS_ALLNUMBER="yyyyMMddHHmmss";

    /***/
    public static String FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    //一天的毫秒数
    private static final long DAY_MS = 24*60*60*1000L;
    /**分钟的毫秒数*/
    public static final long MINUTE = 60*1000L;

    /**
     * 获取当前日期，格式为YYYY-MM-DD
     * @return
     */
    public static String getCurrentDate(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YMD);
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取最近几月日期，格式为YYYY-MM-DD
     * 最近一个月  n=-1
     * @return
     */
    public static String getlatestMouthDate(int n){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,n);
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YMD);
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取最近几天日期，格式为YYYY-MM-DD
     * 最近一天  n=-1
     * @return
     */
    public static String getlatestDayDate(int n){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,n);
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YMD);
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取四月前的时间
     * @return
     */
    public static String getFourMonthAgo(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,-120);
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YMD);
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取当前时间，格式为12:01:23
     * @return
     */
    public static String getCurrentTime(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_HMS);
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取当前时间，格式为yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getCurrentExatTime(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YMDHMS);
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取当前时间，格式为可选
     * @return
     */
    public static String getCurrentTime(String pattern){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(calendar.getTime());
    }


    /**
     * 使用用户格式提取字符串日期
     *
     * @param strDate 日期字符串 示例:2017-01-01
     * @param pattern 日期格式 示例:yyyy-MM-dd
     * @return  提取字符串日期
     */
    public static Date parse(String strDate, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            LogUtils.e("error",e.getMessage());
            return null;
        }
    }

    /**
     * 转换Date日期为指定格式的字符串
     * @param d
     * @param format
     * @return
     */
    public static String date2Str(Date d, String format) {
        if (d == null) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT_YMDHMS;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String s = sdf.format(d);
        return s;
    }

    public static Date str2Date(String str, String format) {
        if (str == null || str.length() == 0) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT_YMDHMS;
        }
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Calendar str2Calendar(String str, String format) {
        Date date = str2Date(str, format);
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c;
    }

    /**
     * 根据小票号获取日期，格式为 MMdd
     * @param payno 小票号
     * @return  月日  MMdd
     */
    public static String getPayDateMonthDay(String payno){
        if(payno==null||payno.length()!=11){
            return "";
        }
        String count = payno.substring(0,4);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, 0, 1);
        calendar.add(Calendar.DATE, Integer.valueOf(count));
        return (new SimpleDateFormat("MMdd")).format(calendar.getTime()) ;
    }

    /**
     * 根据日期获取小票号前4位
     * @param dateStr  日期 20170407
     * @return
     */
    public static String getFirst4ReceiptNo(String dateStr){
        Calendar tradeCalendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        try {
            tradeCalendar.setTime(sdf.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 1);

        long diffDays=(tradeCalendar.getTimeInMillis()-calendar.getTimeInMillis())/(1000 * 60 * 60 * 24);
        String amountStr = (diffDays+1)+"";
        String result="0000";
        int fillNumber = result.length() - amountStr.length();
        result =result.substring(0,fillNumber)+amountStr;
        return result;
    }

}