package com.exmple.corelib.utils;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.text.TextUtils;

import com.orhanobut.logger.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jiangwb on 2016-11-16.
 */

public class DateUtil {

    public static String date2str(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
    public static String date2strNoTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }



    /**
     * 日期变量转成对应的星期字符串
     *
     * @param date
     * @return
     */

    public static final int WEEKDAYS = 7;
    private static final Pattern UNIVERSAL_DATE_PATTERN = Pattern.compile("([0-9]{4})-([0-9]{2})-([0-9]{2})[\\s]+([0-9]{2}):([0-9]{2}):([0-9]{2})");
    //星期字符数组
    public static String[] WEEK = {
        "周日", "周一", "周二", "周三", "周四", "周五", "周六" };

    /**
     * 获取格林威治时间(1970年至今的秒数)
     */
    public static long getGMTime1() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("Etc/Greenwich"));
        String format = sdf.format(new Date());
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date gmDate = null;
        try {
            gmDate = sdf1.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return gmDate.getTime() / 1000;
    }

    /**
     * 获取格林威治时间 即1970年至今的秒数
     */
    public static long getGMTime2() {
        int round = (int) (System.currentTimeMillis() / 1000);
        return round;
    }

    /**
     * 获取时间HH:mm:ss
     */
    public static String getCurrentTime() {
        String time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        //"\\s"以空格截断
        String[] split = date.split("\\s");
        if (split.length > 1) {
            time = split[1];
        }
        return time;
    }

    /**
     * 获取时间yyyy-MM-dd HH:mm:ss
     */
    public static String getCurrentDateTime() {
        String time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());

        return date;
    }

    /**
     * 获取当前时间的年月日时分秒
     */
    public static String current() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        return year + "年" + month + "月" + day + "日" + hour + "时" + minute + "分" + second + "秒";
    }

    /**
     * 得到昨天的日期
     */
    public static String getYesterdayDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String yestoday = sdf.format(calendar.getTime());
        return yestoday;
    }

    /**
     * 得到今天的日期
     */
    public static String getTodayDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    /**
     * 得到今天的日期
     */
    public static int[] getTodayDateArr() {
        Calendar calendar = Calendar.getInstance();
        return new int[] { calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH) };
    }

    /**
     * 得到明天的日期
     */
    public static String getTomorrowDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String tomorrow = sdf.format(calendar.getTime());
        return tomorrow;
    }

    /**
     * 得到明天的日期
     */
    public static int[] getTomorrowArr() {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        long day = date.getTime() + 1000 * 60 * 60 * 24;
        date.setTime(day);
        calendar.setTime(date);
        return new int[] { calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH) };
    }

    /**
     * 得到日期手动加天数
     */
    public static int[] getTomorrowArr(int month) {
        Calendar calendar = Calendar.getInstance();
        return new int[] { calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH) + month };
    }

    /**
     * 时间转化为时间格式
     */
    public static String timeStampToStr(long timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(timeStamp * 1000);
        return date;
    }

    /**
     * 时间转化为时间格式
     */
    public static String timeStampToStr1(long timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date = sdf.format(timeStamp * 1000);
        return date;
    }

    /**
     * 时间转化为时间(几点)
     */
    public static String timeStampToTime(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String date = sdf.format(time * 1000);
        return date;
    }

    /**
     * 秒转化为几月几号
     */
    public static String time2MMdd(String time) {
        if (TextUtils.isEmpty(time)) {
            return "";
        }
        long ms = Long.parseLong(time);
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
        String date = sdf.format(ms * 1000);
        return date;
    }

    /**
     * 将日期格式转化为时间(秒数)
     */
    public static long getStringToDate(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime() / 1000;
    }

    /**
     * 将日期格式转化为时间(秒数)
     */
    public static long getString2Date(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime() / 1000;
    }

    /**
     * 判断是否大于当前时间
     */
    public static boolean judgeCurrTime(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date;
        try {
            date = sdf.parse(time);
            long t = date.getTime();
            long round = System.currentTimeMillis();
            if (t - round > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断是否大于当前时间
     */
    public static boolean judgeCurrTime(long time) {
        long round = System.currentTimeMillis();
        if (time - round > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 比较后面的时间是否大于前面的时间
     */
    public static boolean judgeTime2Time(String time1, String time2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            //转化为时间
            Date date1 = sdf.parse(time1);
            Date date2 = sdf.parse(time2);
            //获取秒数作比较
            long l1 = date1.getTime() / 1000;
            long l2 = date2.getTime() / 1000;
            if (l2 - l1 > 0) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 得到时间 HH:mm:ss
     */
    public static String getTime(long timeStamp) {
        String time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(timeStamp * 1000);
        String[] split = date.split("\\s");
        if (split.length > 1) {
            time = split[1];
        }
        return time;
    }
    /**
     * 得到时间 MM月dd HH:mm
     */
    public static String getMMddHHmm(long timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd HH:mm");
        return  sdf.format(timeStamp);
    }

    /**
     * 将一个时间转换成提示性时间字符串，(多少分钟)
     */
    public static String timeStampToFormat(long timeStamp) {
        long curTime = System.currentTimeMillis() / (long) 1000;
        long time = curTime - timeStamp;
        return time / 60 + "";
    }

    /**
     * 获得当前时间差
     */
    public static int nowCurrentTime(long timeStamp) {
        long curTime = System.currentTimeMillis() / (long) 1000;
        long time = timeStamp - curTime;
        return (int) time;
    }

    /**
     * 获取当前的时 -->flag==true
     * 获取当前的分 -->flag==false
     */
    public static String nowCurrentPoint(boolean flag) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String date = sdf.format(System.currentTimeMillis());
        String[] split = date.split(":");
        String hour = null;
        String minute = null;
        if (flag) {
            if (split.length > 1) {
                hour = split[0];
                return hour;
            }
        } else {
            if (split.length > 1) {
                minute = split[1];
                return minute;
            }
        }
        return null;
    }

    /**
     * 将标准时间格式HH:mm:ss化为当前的时间差值
     */
    public static String standardFormatStr(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d = sdf.parse(str);
            long timeStamp = d.getTime();

            long curTime = System.currentTimeMillis() / (long) 1000;
            long time = curTime - timeStamp / 1000;
            return time / 60 + "";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将一个时间转换成提示性时间字符串，如刚刚，1秒前
     */
    public static String convertTimeToFormat(long timeStamp) {
        long curTime = System.currentTimeMillis() / (long) 1000;
        long time = curTime - timeStamp;

        if (time < 60 && time >= 0) {
            return "刚刚";
        } else if (time >= 60 && time < 3600) {
            return time / 60 + "分钟前";
        } else if (time >= 3600 && time < 3600 * 24) {
            return time / 3600 + "小时前";
        } else if (time >= 3600 * 24 && time < 3600 * 24 * 30) {
            return time / 3600 / 24 + "天前";
        } else if (time >= 3600 * 24 * 30 && time < 3600 * 24 * 30 * 12) {
            return time / 3600 / 24 / 30 + "个月前";
        } else if (time >= 3600 * 24 * 30 * 12) {
            return time / 3600 / 24 / 30 / 12 + "年前";
        } else {
            return "刚刚";
        }
    }

    /**
     * 将一个时间转换成提示性时间字符串，如刚刚，1秒前
     */
    public static String convertTimeToFormat2(long timeStamp) {
        if (timeStamp < 60 && timeStamp >= 0) {
            return "1分钟";
        } else if (timeStamp >= 60 && timeStamp < 3600) {
            return timeStamp / 60 + "分钟";
        } else if (timeStamp >= 3600 && timeStamp < 3600 * 24) {
            return timeStamp / 3600 + "小时";
        } else if (timeStamp >= 3600 * 24 && timeStamp < 3600 * 24 * 30) {
            return timeStamp / 3600 / 24 + "天";
        } else if (timeStamp >= 3600 * 24 * 30 && timeStamp < 3600 * 24 * 30 * 12) {
            return timeStamp / 3600 / 24 / 30 + "个月";
        } else if (timeStamp >= 3600 * 24 * 30 * 12) {
            return timeStamp / 3600 / 24 / 30 / 12 + "年";
        } else {
            return "1分钟";
        }
    }

    /**
     * format time friendly
     *
     * @param sdate YYYY-MM-DD HH:mm:ss
     * @return n分钟前, n小时前, 昨天, 前天, n天前, n个月前
     */
    public static String formatSomeAgo(String sdate) {
        if (sdate == null) {
            return "";
        }
        Calendar calendar = parseCalendar(sdate);
        if (calendar == null) {
            return sdate;
        }

        Calendar mCurrentDate = Calendar.getInstance();
        long crim = mCurrentDate.getTimeInMillis(); // current
        long trim = calendar.getTimeInMillis(); // target
        long diff = crim - trim;

        int year = mCurrentDate.get(Calendar.YEAR);
        int month = mCurrentDate.get(Calendar.MONTH);
        int day = mCurrentDate.get(Calendar.DATE);

        if (diff < 60 * 1000) {
            return "刚刚";
        }
        if (diff >= 60 * 1000 && diff < AlarmManager.INTERVAL_HOUR) {
            return String.format("%s分钟前", diff / 60 / 1000);
        }
        mCurrentDate.set(year, month, day, 0, 0, 0);
        if (trim >= mCurrentDate.getTimeInMillis()) {
            return String.format("%s小时前", diff / AlarmManager.INTERVAL_HOUR);
        }
        //mCurrentDate.set(year, month, day - 1, 0, 0, 0);
        //if (trim >= mCurrentDate.getTimeInMillis()) {
        //    return "昨天";
        //}
        //mCurrentDate.set(year, month, day - 2, 0, 0, 0);
        //if (trim >= mCurrentDate.getTimeInMillis()) {
        //    return "前天";
        //}
        //if (diff < AlarmManager.INTERVAL_DAY * 30) {
        //    return String.format("%s天前", diff / AlarmManager.INTERVAL_DAY);
        //}
        //if (diff < AlarmManager.INTERVAL_DAY * 30 * 12) {
        //    return String.format("%s月前", diff / (AlarmManager.INTERVAL_DAY * 30));
        //}
        //return String.format("%s年前", mCurrentDate.get(Calendar.YEAR) - calendar.get(Calendar.YEAR));
        return sdate;
    }
    /**
     * format time friendly
     *
     * @param sdate 毫秒
     * @return n分钟前, n小时前, 昨天, 前天, n天前, n个月前
     */
    public static String formatMs2SomeAgo(String sdate) {
        if (TextUtils.isEmpty(sdate)) {
            return "";
        }
        String str = date2str(new Date(Long.parseLong(sdate)*1000));
        String someAgo = formatSomeAgo(str);
        return someAgo.equals(str) ? time2MMdd(sdate) : someAgo;
    }
    /**
     * format time friendly
     *
     * @param sdate YYYY-MM-DD HH:mm:ss
     * @return 今天 几点? 昨天几点?
     */
    public static String formatToday(String sdate) {
        if (sdate == null) {
            return "";
        }
        Calendar calendar = parseCalendar(sdate);
        if (calendar == null) {
            return sdate;
        }
        Calendar mCurrentDate = Calendar.getInstance();
        if (calendar.get(Calendar.YEAR) == mCurrentDate.get(Calendar.YEAR)&&calendar.get(Calendar.MONTH)==mCurrentDate.get(Calendar.MONTH)) {
            if (calendar.get(Calendar.DAY_OF_MONTH) == mCurrentDate.get(Calendar.DAY_OF_MONTH)) {
                return "今天 " + sdate.substring(11, 16);
            }
            mCurrentDate.set(Calendar.DAY_OF_MONTH,mCurrentDate.get(Calendar.DAY_OF_MONTH)-1);
            if (calendar.get(Calendar.DAY_OF_MONTH) == mCurrentDate.get(Calendar.DAY_OF_MONTH)) {
                return "昨天 " + sdate.substring(11, 16);
            }
        }
        return sdate;
    }

    /**
     * YYYY-MM-DD HH:mm:ss格式的时间字符串转换为{@link Calendar}类型
     *
     * @param str YYYY-MM-DD HH:mm:ss格式字符串
     * @return {@link Calendar}
     */
    public static Calendar parseCalendar(String str) {
        Matcher matcher = UNIVERSAL_DATE_PATTERN.matcher(str);
        Calendar calendar = Calendar.getInstance();
        if (!matcher.find()) {
            return null;
        }
        calendar.set(matcher.group(1) == null ? 0 : toInt(matcher.group(1)), matcher.group(2) == null ? 0 : toInt(matcher.group(2)) - 1,
            matcher.group(3) == null ? 0 : toInt(matcher.group(3)), matcher.group(4) == null ? 0 : toInt(matcher.group(4)), matcher.group(5) == null ? 0 : toInt(matcher.group(5)),
            matcher.group(6) == null ? 0 : toInt(matcher.group(6)));
        return calendar;
    }

    public static String getFutureTime(String time) {
        Calendar fc = parseCalendar(time);
        if (fc == null) {
            return time;
        }
        Calendar cc = Calendar.getInstance();
        if (fc.get(Calendar.YEAR) == cc.get(Calendar.YEAR)) {
            if (fc.get(Calendar.MONTH) == cc.get(Calendar.MONTH)) {
                int day = fc.get(Calendar.DAY_OF_MONTH) - cc.get(Calendar.DAY_OF_MONTH);
                if (day == 2) {
                    return "后天 " + time.substring(11, time.length());
                } else if (day == 1) {
                    return "明天 " + time.substring(11, time.length());
                } else if (day == 0) {
                    return "今天 " + time.substring(11, time.length());
                }
            }
        }
        return time;
    }

    /**
     * 字符串转整数
     */
    public static int toInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            Logger.d("DateUtil", e.getMessage());
        }
        return 0;
    }

    public static String dateToWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayIndex = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayIndex < 1 || dayIndex > WEEKDAYS) {
            return null;
        }
        return WEEK[dayIndex - 1];
    }

    public static String calendarToWeek(Calendar calendar) {
        int dayIndex = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayIndex < 1 || dayIndex > WEEKDAYS) {
            return null;
        }
        return WEEK[dayIndex - 1];
    }

    public static String calendarToWeek(String str) {
        Calendar calendar = parseCalendar(str);
        return calendarToWeek(calendar);
    }

    /**
     * 转换日期数组到str
     */
    public static String convertToString(int[] dates) {
        if (dates==null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        if (dates.length < 3) {
            return "";
        }
        result.append(dates[0]);
        result.append("-");
        result.append(dates[1] < 10 ? "0" + dates[1] : dates[1]);
        result.append("-");
        result.append(dates[2] < 10 ? "0" + dates[2] : dates[2]);
        return result.toString();
       /* for (int i = 0; i < dates.length; i++) {
            if (i == dates.length - 1) {
                if (i == 2)
                    result.append(dates[i]);
                else result.append("0" + dates[i]);
            } else {
                if (i == 1)
                    result.append("0" + dates[i]).append("-");
                else result.append(dates[i]).append("-");
            }
        }
        return result.toString();*/
    }

    /**
     * 把  2017-2-23 这样的日期转换为int array
     */
    public static int[] str2array(String string) {
        if (string.length() > 10) {
            string = string.split(" ")[0];
        }
        String[] split = string.split("-");
        int[] arr = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            arr[i] = Integer.parseInt(split[i]);
        }
        return arr;
    }

    /**
     * 比较2个日期的大小
     *
     * @return 反回正数 开始时间大于结束时间不合法  相同返回0  返回负数时间合法
     */
    public static int compare(int[] beginDate, int[] endDate) {
        int result = beginDate[0] - endDate[0];
        if (result != 0) {
            return result;
        }
        result = beginDate[1] - endDate[1];
        if (result != 0) {
            return result;
        }
        result = beginDate[2] - endDate[2];
        if (result != 0) {
            return result;
        }
        return result;
    }

    /**
     * 把服务器返回的时间转换为 年月日
     */
    @Deprecated
    public static String toChinese(String string) {
        return parse2Chinese(string);
    }

    /**
     * 把服务器返回的时间转换为 年月日
     */
    public static String parse2Chinese(String string) {
        StringBuilder sb = new StringBuilder();
        Calendar calendar = parseCalendar(string);
        sb.append(calendar.get(Calendar.YEAR));
        sb.append("年");
        int month = calendar.get(Calendar.MONTH) + 1;
        if (month < 10) {
            sb.append("0");
        }
        sb.append(month);
        sb.append("月");
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        if (day < 10) {
            sb.append("0");
        }
        sb.append(day);
        sb.append("日");
        return sb.toString();
    }

    /**
     * 获取本月的日期范围
     */
    public static String getCurMonthRange() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = getDaysByYearMonth(calendar.get(Calendar.YEAR), month);
        return String.format("%d月01日 - %d月%d日", month, month, day);
    }

    /**
     * 根据年 月 获取对应的月份 天数
     */
    public static int getDaysByYearMonth(int year, int month) {

        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        return a.get(Calendar.DATE);
    }

    /**
     * 数字转换为月份
     */
    public static String convert2Chinese(int i) {

        switch (i) {
            case 1:
                return "一月";
            case 2:
                return "二月";
            case 3:
                return "三月";
            case 4:
                return "四月";
            case 5:
                return "五月";
            case 6:
                return "六月";
            case 7:
                return "七月";
            case 8:
                return "八月";
            case 9:
                return "九月";
            case 10:
                return "十月";
            case 11:
                return "十一月";
            case 12:
                return "十二月";
        }
        return null;
    }

    /**
     * 秒提取月日
     */
    public static String parseDate2Chinese(String createTime) {
        if (TextUtils.isEmpty(createTime)) {
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(createTime)*1000);
        return calendar.get(Calendar.MONTH) + 1 + " -" + calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 把年月日转成array
     * @param time  2017-05-30
     * @return
     */
    public static int[] getTodayDateArr(String time) {
        if (TextUtils.isEmpty(time)) {
            return null;
        }
        String[] split = time.split("-");
        return new int[]{Integer.parseInt(split[0]),Integer.parseInt(split[1]),Integer.parseInt(split[2])};
    }

    // a integer to xx:xx:xx
    public static String secToTime(long time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0) {
            return "00:00:00";
        } else {
            minute = (int) (time / 60);
            if (minute < 60) {
                second = (int) (time % 60);
                timeStr = "00:"+unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 999) {
                    return "999:59:59";
                }
                minute = minute % 60;
                second = (int) (time - hour * 3600 - minute * 60);
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10) {
            retStr = "0" + Integer.toString(i);
        } else {
            retStr = String.valueOf(i);
        }
        return retStr;
    }

    public static String formatDate(long mill) {
        if (mill == 0) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
        return sdf.format(new Date(mill));
    }


    public static String formatDate2Min(long mill) {
        if (mill == 0) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
        return sdf.format(new Date(mill));
    }

    public static String formatData2(long mills){
        if (mills == 0) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date(mills));
    }
    public static String formatData3(long mills){
        if (mills == 0) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        return sdf.format(new Date(mills));
    }

    public static String formatDate4(long mill) {
        if (mill == 0) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        return sdf.format(new Date(mill));
    }
    public static String formatDate2Mill(long mill) {
        if (mill == 0) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(mill));
    }

    public static long formatToMill(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm");
        try {
            Date parse = sdf.parse(date);
            return parse.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static long formatToMill2(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        try {
            Date parse = sdf.parse(date);
            return parse.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static long formatToMill3(String date) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
        try {
            Date parse = sdf.parse(date);
            return parse.getTime()/1000L;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
    /**
     * @param date
     * @return
     */
    public static String formatMothDayHourMinute2String(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }


    /**
     * @param date
     * @return
     */
    public static String formatTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(date);
    }
}
