package com.voting.common.utils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.voting.common.exception.BusinessException;

public class DateUtils {

	public static final Long ONE_HOUR = 1000*60*60L;

	public static final Long ONE_DAY = 1000*60*60*24L;

	public static final int ONE_MOUTH = 31;

	public static final int ONE_YEAR = 366;

	/**
	 * 时间格式化对象（yyyy-MM-dd HH:mm:ss）
	 */
	public static final SimpleDateFormat DATE_FORMAT1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 时间格式化对象（yyyy-MM-dd）
	 */
	public static final SimpleDateFormat DATE_FORMAT2 = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 时间格式化对象（yyyy-MM）
	 */
	public static final SimpleDateFormat DATE_FORMAT3 = new SimpleDateFormat("yyyy-MM");
	/**
	 * 时间格式化对象（yyyy/MM）
	 */
	public static final SimpleDateFormat DATE_FORMAT4 = new SimpleDateFormat("yyyy/MM");
	/**
	 * 时间格式化对象（yyyy/MM/dd）
	 */
	public static final SimpleDateFormat DATE_FORMAT5 = new SimpleDateFormat("yyyy/MM/dd");

	public static final SimpleDateFormat DATE_FORMAT6 = new SimpleDateFormat("yyyyMMdd");
	
	/**
	 * 时间格式化对象（yyMMddHHmm）
	 */
	public static final SimpleDateFormat DATE_FORMAT7 = new SimpleDateFormat("yyMMddHHmm");

	public static Date formatStringToDate(String date){
		try {
			return DateUtils.DATE_FORMAT1.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			return DateUtils.DATE_FORMAT2.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			return DateUtils.DATE_FORMAT3.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			return DateUtils.DATE_FORMAT4.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			return DateUtils.DATE_FORMAT5.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		throw new BusinessException("不支持的时间格式");
	}

	public static String getTodayDay(){
		Calendar cal=Calendar.getInstance();
		String mouth = (cal.get(Calendar.MONTH) + 1) > 9 ? (cal.get(Calendar.MONTH) + 1) + "" : "0" + (cal.get(Calendar.MONTH) + 1);
		String day = (cal.get(Calendar.DATE)) > 9 ? (cal.get(Calendar.DATE)) + "" : "0" + (cal.get(Calendar.DATE));
		return mouth + day;
	}

	public static String getTodayDayWithYear(){
		Calendar cal=Calendar.getInstance();
		String year = cal.get(Calendar.YEAR) + "";
		String mouth = (cal.get(Calendar.MONTH) + 1) > 9 ? (cal.get(Calendar.MONTH) + 1) + "" : "0" + (cal.get(Calendar.MONTH) + 1);
		String day = (cal.get(Calendar.DATE)) > 9 ? (cal.get(Calendar.DATE)) + "" : "0" + (cal.get(Calendar.DATE));
		return year + mouth + day;
	}

	public static String getTodayDayWithYear2(){
		Calendar cal=Calendar.getInstance();
		String year = cal.get(Calendar.YEAR) + "";
		String mouth = (cal.get(Calendar.MONTH) + 1) > 9 ? (cal.get(Calendar.MONTH) + 1) + "" : "0" + (cal.get(Calendar.MONTH) + 1);
		String day = (cal.get(Calendar.DATE)) > 9 ? (cal.get(Calendar.DATE)) + "" : "0" + (cal.get(Calendar.DATE));
		return year + "-" + mouth + "-" + day;
	}

	public static String getNow(){
		return new SimpleDateFormat("yyyyMMddhhmmss").format(new Date().getTime());
	}

	public static String getNow(String format){
		return new SimpleDateFormat(format).format(new Date().getTime());
	}

	public static String getNow24(){
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date().getTime());
	}

	public static String getYearMonth(Date date){
		return new SimpleDateFormat("yyyyMM").format(date.getTime());
	}

	public static String format(String format, Date date){
		if(date == null){
			return "";
		}
		return new SimpleDateFormat(format).format(date);
	}

	public static String dealTimeToString(Timestamp time){
		Long t = System.currentTimeMillis() - time.getTime();

		if(t <= ONE_HOUR){
			return "刚刚";
		}
		if(t > ONE_HOUR && t <= ONE_DAY){
			return new SimpleDateFormat("HH:mm:ss").format(time);
		}
		if(t/ONE_DAY <= 30){
			return t/ONE_DAY + "天前";
		}
		if(t/ONE_DAY > 30 && t/ONE_DAY <= ONE_YEAR){
			return (t/ONE_DAY)/ONE_MOUTH + "个月前";
		}
		if(t/ONE_DAY > 365){
			return t/ONE_DAY/365 + "年前";
		}
		return "很久以前";
	}

	public static String dealTimeToStringForDynamic(Timestamp time){
		Long now = System.currentTimeMillis();
		Long t = now - time.getTime();
		long d = t/ONE_DAY;
		if(d == 0){
			int nowDate = Integer.parseInt(new SimpleDateFormat("yyyyMMdd").format(new Date()));
			int tDate = Integer.parseInt(new SimpleDateFormat("yyyyMMdd").format(new Date(time.getTime())));
			if(nowDate > tDate){
				d = 1;
			}
		}
		if(t <= ONE_HOUR){
			return "刚刚";
		}
		if(t > ONE_HOUR && t <= ONE_DAY && d == 0){
			return "今天 " + new SimpleDateFormat("HH:mm").format(time);
		}
		if(t/ONE_DAY <= 7){
			return d + "天前 " + new SimpleDateFormat("HH:mm").format(time);
		}
		if(t/ONE_DAY > 7 && t/ONE_DAY <= ONE_YEAR){
			return new SimpleDateFormat("MM月dd日  HH:mm").format(time);
		}
		if(t/ONE_DAY > 365){
			return new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(time);
		}
		return "很久以前";
	}

	public static Date strToDate(String format, String str){
		SimpleDateFormat sdf =   new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	   return date;
	}

	public static Date addDay(Date date, int days){
		if(date == null){
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}

	/**
	 * 计算两个日期之间相差的天数,不足一天则返回0
	 *
	 * @param sDate 较小的时间
	 * @param bDate 较大的时间
	 *
	 * @return 相差天数
	 */
	public static int daysBetween(Date sDate, Date bDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sDate = sdf.parse(sdf.format(sDate));
		bDate = sdf.parse(sdf.format(bDate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(sDate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bDate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 计算两个日期之间相差的天数,不足一天向上取整
	 *
	 * @param sDate 较小的时间
	 * @param bDate 较大的时间
	 *
	 * @return 相差天数
	 */
	public static int daysBetweenCeil(Date sDate, Date bDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sDate = sdf.parse(sdf.format(sDate));
		bDate = sdf.parse(sdf.format(bDate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(sDate);
		BigDecimal time1 = new BigDecimal(StringUtils.valueOf(cal.getTimeInMillis()));
		cal.setTime(bDate);
		BigDecimal time2 = new BigDecimal(StringUtils.valueOf(cal.getTimeInMillis()));
		BigDecimal between_days = time2.subtract(time1).divide(new BigDecimal(StringUtils.valueOf((1000 * 3600 * 24))), 0, BigDecimal.ROUND_UP);
		return between_days.intValue();
	}

	/**
	 * 计算两个日期之间相差的天数,不足一天则返回0
	 *
	 * @param sDate 较小的时间
	 * @param bDate 较大的时间
	 *
	 * @return 相差时间 秒
	 */
	public static int timesBetween(Date sDate, Date bDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sDate = sdf.parse(sdf.format(sDate));
		bDate = sdf.parse(sdf.format(bDate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(sDate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bDate);
		long time2 = cal.getTimeInMillis();
		long between_times = (time2 - time1) / 1000;
		return Integer.parseInt(String.valueOf(between_times));
	}


	/**
	 * 格式化时间 获取当天最后一刻时间
	 *
	 * @param date 时间对象
	 */
	public static Date getEndOfTheDate(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MILLISECOND, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.HOUR, 23);
		return calendar.getTime();
	}

	/**
	 * 格式化时间，获取当天最初的时间
	 */
	public static Date getStartOfTheDate(Date date){
		if(date == null){
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR, 0);
		return calendar.getTime();
	}

	/**
	 * 比较时间大小
	 * @param date1
	 * @param date2
	 * @return 返回 1: date1 大 ，-1：date2 大  ，0 表示相等
	 * @throws ParseException
	 */
	public static int compare_date(Date date1, Date date2) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dt1 = sdf.parse(sdf.format(date1));
		Date dt2 = sdf.parse(sdf.format(date2));
		if (dt1.getTime() > dt2.getTime()) {
			return 1;
		} else if (dt1.getTime() < dt2.getTime()) {
			return -1;
		} else {
			return 0;
		}

	}
	 public static String getLastDayOfMonth(int year, int month) {     
         Calendar cal = Calendar.getInstance();     
         cal.set(Calendar.YEAR, year);     
         cal.set(Calendar.MONTH, month-1);     
         cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DATE));  
        return  new   SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());  
     }
	 public static void main(String[] args) {
		 Date date = new Date("2017-09");  
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(date);
		 SimpleDateFormat bartDateFormat =       new SimpleDateFormat("yyyy-MM");  
	
         System.out.println(cal);  
	}
	
}
