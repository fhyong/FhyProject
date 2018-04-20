package com.base.util.common;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 日期工具类
 * @author qianzhihe
 */
public final class DateUtils {

    public static final String YEAR_FORMAT = "yyyy";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_TO_SS_FORMAT = "MM-dd HH:mm:ss";
    public static final String DATE_MONTH = "MM-dd";
    public static final String TIME_FORMAT = "HH:mm";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String PART_TIME_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String HHmmss_TIME_FORMAT = "HH:mm:ss";
    public static final String DATE_PARRERN = "\\d{1,4}\\-\\d{1,2}\\-\\d{1,2}";
    public static final String DATE_TIME_PATTERN = "\\d{1,4}\\-\\d{1,2}\\-\\d{1,2}\\s+\\d{1,2}:\\d{1,2}:\\d{1,2}";
    public static final SimpleDateFormat ACTIVITY_FORMAT = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm");

    private DateUtils() {
    }

    public static String msToHHmmss(long s) {
        SimpleDateFormat formatter = new SimpleDateFormat(HHmmss_TIME_FORMAT);//初始化Formatter的转换格式。
        String hms = formatter.format(s * 1000l - TimeZone.getDefault().getRawOffset());
        return hms;
    }

    public static String msToyyMMHHmmss(long s) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_TO_SS_FORMAT);//初始化Formatter的转换格式。
        String hms = formatter.format(s);
        return hms;
    }

    public static String msToyyyyMMHHmmss(long s) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_TIME_FORMAT);//初始化Formatter的转换格式。
        String hms = formatter.format(s);
        return hms;
    }
    public static String msToyyyyMMHHmm(long s) {
        SimpleDateFormat formatter = new SimpleDateFormat(PART_TIME_FORMAT);//初始化Formatter的转换格式。
        String hms = formatter.format(s);
        return hms;
    }

    public static long hHmmssToMsByPoint(String time) {
        String[] my = time.split(":");
        int hour = Integer.parseInt(my[0]);
        int min = Integer.parseInt(my[1]);
        int sec = Integer.parseInt(my[2]);
        long totalSec = hour * 3600 + min * 60 + sec;

        return totalSec;
    }

    public static long hHmmssToMsByLine(String time) {
        String[] my = time.split("-");
        int hour = Integer.parseInt(my[0]);
        int min = Integer.parseInt(my[1]);
        int sec = Integer.parseInt(my[2]);
        long totalSec = hour * 3600 + min * 60 + sec;

        return totalSec;
    }

    /**
     * 获取时间数组
     * 例如2013-3-12 13:13:21 返回2013-3-12 和13:13:21
     * 例如2013/3/12 13:13:21 返回2013/3/12 和13:13:21
     *
     * @param date
     * @return
     */
    public static String[] getDateAry(String date) {
        String[] tempAry = date.split(" ");
        return tempAry;
    }

    public static String[] getYMDAry(String date) {
        String[] tempAry = null;
        if (date.contains("/")) {
            tempAry = date.split("/");
        }
        if (date.contains("-")) {
            tempAry = date.split("-");
        }
        return tempAry;
    }

    /**
     * 获取时间数组
     * 例如13:15:21 返回13和15和21
     *
     * @param date
     * @return
     */
    public static String[] getTimeAry(String date) {
        String[] tempAry = date.split(" ");
        return tempAry;
    }

    public static Date toDate(String dateString) {

        if (StringUtils.matches(dateString, DATE_PARRERN)) {
            return toDate(dateString, DATE_FORMAT);
        }
        if (StringUtils.matches(dateString, DATE_TIME_PATTERN)) {
            return toDate(dateString, DATE_TIME_FORMAT);
        }

        return null;
    }

    public static Date toDate(long milliseconds) {

        if (milliseconds <= 0)
            return null;

        try {
            return new Date(milliseconds);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Long getDaysBetween(Date endDate, Date startDate) {

        if (endDate == null || startDate == null) {
            return -1L;
        }

        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(startDate);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(endDate);
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);

        return (toCalendar.getTime().getTime() - fromCalendar.getTime()
                .getTime()) / (1000 * 60 * 60 * 24);
    }

    public static Date ActivityDateParse(String dateStr) {
        Date date = null;
        try {
            date = ACTIVITY_FORMAT.parse(dateStr);
        } catch (ParseException e) {

        }
        return date;
    }

    public static Date toDate(String dateString, String pattern) {
        if (dateString == null) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        ParsePosition position = new ParsePosition(0);
        Date strtodate = formatter.parse(dateString, position);
        return strtodate;
    }

    public static String toDateString(Date date) {
        return toDateString(date, DATE_FORMAT);
    }

    public static String toDateString(Date date, String pattern) {

        if (date == null) {
            return "";
        }

        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    public static String pad(int string) {
        if (string >= 10) {
            return String.valueOf(string);
        } else {
            return "0" + String.valueOf(string);
        }
    }

    public static Date getCurrtentTimes() {
        Calendar calendar = Calendar.getInstance(TimeZone
                .getTimeZone("GMT+08:00"));
        Date date = calendar.getTime();
        return date;
    }

    public static int[] getDateTime(Date date) {
        Calendar calendar = Calendar.getInstance(TimeZone
                .getTimeZone("GMT+08:00"));
        int year = 0, month = 0, day = 0, hour = 0, min = 0;
        calendar.setTime(date);
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        min = calendar.get(Calendar.MINUTE);
        int[] times = {year, month, day, hour, min};
        return times;
    }

    public static long compare(Date d1, Date d2) {

        if (d1 == null) {
            return -1;
        }
        if (d2 == null) {
            return -1;
        }

        return d1.getTime() - d2.getTime();
    }

    public static Date countDate(Date date, int type, int num) {

        if (num < 1) {
            return date;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(type, num);
        return calendar.getTime();
    }

    public static int getYear(Date date) {

        if (date == null) {
            return getCurrentYear();
        }

        SimpleDateFormat year = new SimpleDateFormat(YEAR_FORMAT);

        try {
            return Integer.valueOf(year.format(date));
        } catch (Exception e) {
            return 2013;
        }
    }

    public static int getMonth(Date date) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        return cl.get(Calendar.MONTH) + 1;
    }

    public static int getDay(Date date) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        return cl.get(Calendar.DAY_OF_MONTH);
    }

    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static Date addSub(Date date, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, num);
        return calendar.getTime();
    }

    /**
     * 计算两个日期的时间间隔
     *
     * @param sDate 开始时间
     * @param eDate 结束时间
     * @param type  间隔类型
     *              ("Y/y"--年 "M/m"--月 "D/d"--日)
     * @return interval时间间隔
     */
    public static int calInterval(Date sDate, Date eDate, String type) {
        // 时间间隔，初始为0
        int interval = 0;

		/* 比较两个日期的大小，如果开始日期更大，则交换两个日期 */
        // 标志两个日期是否交换过
        boolean reversed = false;
        if (compareDate(sDate, eDate) > 0) {
            Date dTest = sDate;
            sDate = eDate;
            eDate = dTest;
            // 修改交换标志
            reversed = true;
        }

		/* 将两个日期赋给日历实例，并获取年、月、日相关字段值 */
        Calendar sCalendar = Calendar.getInstance();
        sCalendar.setTime(sDate);
        int sYears = sCalendar.get(Calendar.YEAR);
        int sMonths = sCalendar.get(Calendar.MONTH);
        int sDays = sCalendar.get(Calendar.DAY_OF_YEAR);

        Calendar eCalendar = Calendar.getInstance();
        eCalendar.setTime(eDate);
        int eYears = eCalendar.get(Calendar.YEAR);
        int eMonths = eCalendar.get(Calendar.MONTH);
        int eDays = eCalendar.get(Calendar.DAY_OF_YEAR);

        // 年
        if (cTrim(type).equals("Y") || cTrim(type).equals("y")) {
            interval = eYears - sYears;
            if (eMonths < sMonths) {
                --interval;
            }
        }
        // 月
        else if (cTrim(type).equals("M") || cTrim(type).equals("m")) {
            interval = 12 * (eYears - sYears);
            interval += (eMonths - sMonths);
        }
        // 日
        else if (cTrim(type).equals("D") || cTrim(type).equals("d")) {
            interval = 365 * (eYears - sYears);
            interval += (eDays - sDays);
            // 除去闰年天数
            while (sYears < eYears) {
                if (isLeapYear(sYears)) {
                    --interval;
                }
                ++sYears;
            }
        }
        // 如果开始日期更大，则返回负值
        if (reversed) {
            interval = -interval;
        }
        // 返回计算结果
        return interval;
    }

    /**
     * 比较两个Date类型的日期大小
     *
     * @param sDate 开始时间
     * @param eDate 结束时间
     * @return result返回结果(0--相同 1--前者大 -1:--后者大)
     */
    public static int compareDate(Date sDate, Date eDate) {

        int result = 0;
        //将开始时间赋给日历实例
        Calendar sC = Calendar.getInstance();
        sC.setTime(sDate);
        //将结束时间赋给日历实例
        Calendar eC = Calendar.getInstance();
        eC.setTime(eDate);
        //比较
        result = sC.compareTo(eC);
        //返回结果
        return result;
    }

    /**
     * 字符串去除两头空格，如果为空，则返回""，如果不空，则返回该字符串去掉前后空格
     *
     * @param tStr 输入字符串
     * @return 如果为空，则返回""，如果不空，则返回该字符串去掉前后空格
     */
    public static String cTrim(String tStr) {
        String ttStr = "";
        if (tStr == null) {
        } else {
            ttStr = tStr.trim();
        }
        return ttStr;
    }

    /**
     * 判定某个年份是否是闰年
     *
     * @param year 待判定的年份
     * @return 判定结果
     */
    private static boolean isLeapYear(int year) {
        return (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0));
    }

    /**
     * 将时间毫秒值格式化为yyyy-MM-dd
     *
     * @param dateMilliSecondsStr
     * @return
     */
    public static String formatDate(String dateMilliSecondsStr) {
        if (StringUtils.isEmpty(dateMilliSecondsStr) || dateMilliSecondsStr.equals("null")){
            return  "";
        }
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd");
        long time= Long.parseLong(dateMilliSecondsStr);
        String dateStr = sdr.format(new Date(time));
        return dateStr;
    }

    /**
     * 将时间毫秒值格式化为yyyy-MM-dd HH:mm
     * @param dateMilliSecondsStr
     * @return
     */
    public static String formatDateToDay(String dateMilliSecondsStr) {
        if (StringUtils.isEmpty(dateMilliSecondsStr) || dateMilliSecondsStr.equals("null")){
            return  "";
        }
        SimpleDateFormat sdr = new SimpleDateFormat(PART_TIME_FORMAT);
        long time= Long.parseLong(dateMilliSecondsStr);
        String dateStr = sdr.format(new Date(time));
        return dateStr;
    }

    /**
     * 获取当前系统时间戳
     * @return
     */
    public static long getCurrentTimeMillis(){
        long time= System.currentTimeMillis();
        return time;
    }

    /**
     * long转换为Date类型
     * @param currentTime
     * @param formatType
     * @return
     * @throws ParseException
     */
    public static Date longToDate(long currentTime, String formatType){
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString(dateOld, formatType); // 把date类型的时间转换为string
        Date date = null; // 把String类型转换为Date类型
        try {
            date = stringToDate(sDateTime, formatType);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * date类型转换为String类型
     * @param data
     * @param formatType
     * @return
     */
    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }

    /**
     * string类型转换为date类型
     * @param strTime
     * @param formatType
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        try {
            date = formatter.parse(strTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取昨天，今天，明天的日期
     * @param formatType 时间格式  例如："yyyy-MM-dd"
     * @return
     */
    public static String[] getYesterdayTodayTomorrowDate(String formatType){
        String[] stringAry=new String[4];
        SimpleDateFormat dateFormat=new SimpleDateFormat(formatType);
        Calendar calendar=dateFormat.getCalendar();
        //昨天
        calendar.roll(Calendar.DAY_OF_YEAR,-1);
        stringAry[0]=dateFormat.format(calendar.getTime());
        //今天
        calendar=Calendar.getInstance();
        stringAry[1]=dateFormat.format(calendar.getTime());
        //明天
        calendar.roll(Calendar.DAY_OF_YEAR,1);
        stringAry[2]=dateFormat.format(calendar.getTime());
        //后天
        calendar.roll(Calendar.DAY_OF_YEAR,2);
        stringAry[3]=dateFormat.format(calendar.getTime());
        return stringAry;
    }

    /**
     * date要转换的date类型的时间
     * @param date
     * @return
     */
    public static long dateToLong(Date date) {
        return date.getTime();
    }

    private static SimpleDateFormat sdf = null;
    public  static String formatUTC(long l, String strPattern) {
        if (TextUtils.isEmpty(strPattern)) {
            strPattern = "yyyy-MM-dd HH:mm:ss";
        }
        if (sdf == null) {
            try {
                sdf = new SimpleDateFormat(strPattern, Locale.CHINA);
            } catch (Throwable e) {
            }
        } else {
            sdf.applyPattern(strPattern);
        }
        return sdf == null ? "NULL" : sdf.format(l);
    }

    /**
     * 长整型毫秒的时间转成年月日：yyyy-MM-dd
     * @param s
     * @return
     */
    public static String msToYyMmDd(long s) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);//初始化Formatter的转换格式。
        String hms = formatter.format(s);
        return hms;
    }
}