
package com.kingfeng.select_date_ranges.util;

import android.annotation.SuppressLint;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


@SuppressLint("SimpleDateFormat")
public class TimeUtil {

    private static final String TAG = "TimeUtil";

    /**一天的毫秒值**/
    public static long dayMills = 24*60*60*1000;
    
    public static String getData(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date(time));
    }
    
    /**
     * 获取带时分秒 的标准日期格式
     * @param date
     * @return
     */
    public static String getDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    
    public static String getTime(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date(time));
    }
    /**不带秒的*/
    public static String getTimeTOStr(long time){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(new Date(time));
    }
    public static String getHourAndMin(long time) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(new Date(time));
    }
    
    public static String getChatTime(long timesamp) {
        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        Date today = new Date(System.currentTimeMillis());
        Date otherDay = new Date(timesamp);
        int temp =
            Integer.parseInt(sdf.format(today))
                - Integer.parseInt(sdf.format(otherDay));
        
        switch (temp) {
            case 0:
                result = "今天 " + getHourAndMin(timesamp);
                break;
            case 1:
                result = "昨天 " + getHourAndMin(timesamp);
                break;
            case 2:
                result = "前天 " + getHourAndMin(timesamp);
                break;
            
            default:
                result = getTime(timesamp);
                break;
        }
        return result;
    }

    /***
     * 判断日期是否今天
     * @param timesamp
     * @return
     */
    public static boolean isToday(long timesamp) {
        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        Date today = new Date(System.currentTimeMillis());
        Date otherDay = new Date(timesamp);
        int temp =
                Integer.parseInt(sdf.format(today))
                        - Integer.parseInt(sdf.format(otherDay));
        if(temp==0){
            return true;
        }
        return false;
    }

    /**
     * 获取当前时间的日期
     * @return
     */
    public static int getDay(long timesamp){
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        Date day = new Date(timesamp);
        return Integer.parseInt(sdf.format(day));
    }

    /**
     * 获取当前时间的月份
     * @return
     */
    public static int getMonth(long timesamp){
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        Date month = new Date(timesamp);
        return Integer.parseInt(sdf.format(month));
    }

    /**
     * 获取当天的毫秒值
     * @return
     */
    public static long getCurrentDayMills(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String showTime = formatter.format(System.currentTimeMillis());
        Date date = null;
        try {
            date = formatter.parse(showTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    /**
     * 获取当天的毫秒值
     * @return
     */
    public static long getCurrent2DayMills(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR,0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    /**
     * 获取今天零点的毫秒值   00:00   上面的那个方法获取的是 当天 12:00对应的毫秒值
     * @return
     */
    public static long getCurrentDayToMills(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    /***
     *获取系统当前的毫秒值
     * @return
     */
    public static long getCurrentTimes(){
    return System.currentTimeMillis();
    }
    /**
     * 获取指定天的毫秒数
     * @param distinactToday:表示离现在的天数
     * @return
     */
    public static long getAppointedDayMills(int distinactToday){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis() + distinactToday*dayMills;
    }
    
    public static String getChatTimeStyle(long timesamp) {
        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        Date today = new Date(System.currentTimeMillis());
        Date otherDay = new Date(timesamp);
        int temp =
            Integer.parseInt(sdf.format(today))
                - Integer.parseInt(sdf.format(otherDay));
        
        switch (temp) {
            case 0:
                result = getHourAndMin(timesamp);
                break;
            case 1:
                result = "昨天 " + getHourAndMin(timesamp);
                break;
            case 2:
            	 result = getTime(timesamp);
                break;
            
            default:
                result = getTime(timesamp);
                break;
        }
        return result;
    }
    
    /**
     * @方法名: twoDateDistance
     * @描述: 比较两个日期相差几天
     * @设定: @param startDate
     * @设定: @param endDate
     * @设定: @return 设定文件
     * @返回: long 返回类型
     * @日期: 2014-7-8 下午3:09:31
     * @throws
     */
    public static long twoDateDistance(long startDate, long endDate) {
        long timeLong = endDate - startDate;
        if (timeLong > 60 * 60 * 24 * 1000) {
            timeLong = timeLong / 1000 / 60 / 60 / 24;
            Log.d(TAG, timeLong + "天前");
            return timeLong;
        }
        
        // if (timeLong < 60 * 60 * 1000) {
        // timeLong = timeLong / 1000 / 60;
        // Logger.d(timeLong + "分钟前");
        // return timeLong;
        // }
        return -1;
    }
    
    /**
     * @Description 指定年月的最后一天日期
     * @param year
     * @param month
     * @param simpleDateFormat
     * @return String
     */
    public static String getMonthLastDayDate(int year, int month,
                                             SimpleDateFormat simpleDateFormat) {
        if (year < 0) return null;
        Calendar cal = Calendar.getInstance();
        if (month < 0) {
            cal.set(Calendar.YEAR, year - 1);
            cal.set(Calendar.MONTH, 12 + month % 11);
        } else if (month > 11) {
            cal.set(Calendar.YEAR, year + 1);
            cal.set(Calendar.MONTH, month % 11);
        } else {
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, month);
        }
        cal.set(Calendar.DAY_OF_MONTH,
            cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        String date = simpleDateFormat.format(cal.getTime());
        return date;
    }
    
    /**
     * @Description 指定年月的第一天日期
     * @param year
     * @param month
     * @param simpleDateFormat
     * @return String
     */
    public static String getMonthFirstDayDate(int year, int month,
                                              SimpleDateFormat simpleDateFormat) {
        if (year < 0) return null;
        Calendar cal = Calendar.getInstance();
        
        if (month < 0) {
            cal.set(Calendar.YEAR, year - 1);
            cal.set(Calendar.MONTH, 12 + month);
        } else if (month > 11) {
            cal.set(Calendar.YEAR, year + 1);
            cal.set(Calendar.MONTH, month % 11);
        } else {
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, month);
        }
        cal.set(Calendar.DAY_OF_MONTH, 1);
        String date = simpleDateFormat.format(cal.getTime());
        return date;
    }
    
    /**
     * @Description: 指定日期当周第一天或最后一天
     * @param cal
     * @param flag
     * @return：String
     */
    public static String getFirstLastWeek(Calendar cal, boolean flag) {
        // flag true周第一天 false 周最后一天
        
        int dw = cal.get(Calendar.DAY_OF_WEEK);
        if (!flag) cal.setTimeInMillis(cal.getTimeInMillis() + (7 - dw) * 24
            * 60 * 60 * 1000);
        else cal.setTimeInMillis(cal.getTimeInMillis() - (dw - 1) * 24 * 60
            * 60 * 1000);
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        
        String showTime = formatter.format(cal.getTime());
        return showTime.toString();
    }
    
    /**
     * @Description: 指定日期当月第一天或最后一天
     * @param cal
     * @param flag
     * @return：String
     */
    public static String getFirstLastMoonth(Calendar cal, boolean flag) {
        // flag true月第一天 false 月最后一天
        cal.set(Calendar.DAY_OF_MONTH, 1);
        if (!flag) {
            cal.roll(Calendar.DAY_OF_MONTH, -1);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String showTime = formatter.format(cal.getTime());
        return showTime.toString();
    }
    
    /**
     * @Description: 指定日期string格式
     * @param cal
     * @return：String
     */
    public static String getDateString(Calendar cal) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String showTime = formatter.format(cal.getTime());
        return showTime.toString();
    }

    public static String getDateString(long mills) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM月dd");
        String showTime = formatter.format(mills);
        return showTime.toString();
    }

    /***
     * long --> String    li.jf
     * @param mills
     * @param formatType 格式化类型   yyy-MM-dd
     * @return
     */
    public static String getDateString(long mills, String formatType) {
//        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
//        String showTime = formatter.format(mills);
//        return showTime.toString();

        SimpleDateFormat sdf = new SimpleDateFormat(formatType);
        return sdf.format(new Date(mills));
    }

    public static Long getLongDate(String strTime, String formatType) {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        try {
            date = formatter.parse(strTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static String getDate(long mills) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        String showTime = formatter.format(mills);
        return showTime.toString();
    }

    /**
     * 获取当前日期的前几天  li.jf
     * @param days（正数）
     * @return 格式诸如：2015-11-17
     */
    public static String getBeforeCurrentDay(int days) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -days);
        return dateFormat.format(c.getTime());
    }

    public static Long getBeforeLongCurrentDay(int days) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -days);
        return c.getTimeInMillis();
    }

}
