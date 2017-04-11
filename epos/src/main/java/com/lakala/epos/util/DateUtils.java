/*
 * Copyright 2013-2014 the original author or authors.
 */

package com.lakala.epos.util;

import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 * TODO 类描述
 * <p>
 * Created on 2017年4月6日
 * <p>
 * 
 * @author liuyinlong<liuyinlong@lakala.com>
 * @since 2017年4月6日
 */
public class DateUtils {
	private static Logger logger = Logger.getLogger(DateUtils.class);
	public static SimpleDateFormat SDF1 = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat SDF2 = new SimpleDateFormat("yyyy/MM/dd");
	public static SimpleDateFormat SDF3 = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat SDF4 = new SimpleDateFormat("yyyyMMddhhmmss");

	public static boolean isSameDate(Date d1, Date d2) {
		if ((d1 == null) || (d2 == null)) {
			logger.debug("isSameDate : 参数有空值，直接返回false");
			return false;
		}
		Calendar c1 = Calendar.getInstance();
		c1.setTime(d1);

		Calendar c2 = Calendar.getInstance();
		c1.setTime(d2);

		return ((c1.get(1) == c2.get(1)) && (c1.get(2) == c2.get(2)) && (c1
				.get(5) == c2.get(5)));
	}

	public static Date clearTime(Date d) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(d);
		ca.set(11, 0);
		ca.set(12, 0);
		ca.set(13, 0);
		ca.set(14, 0);
		return ca.getTime();
	}

	public static Date addDay(Date d, int dayToAdd) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(d);
		ca.add(5, dayToAdd);
		return ca.getTime();
	}

	public static boolean isToday(Date d) {
		return isSameDate(d, new Date());
	}

	public static String dateToString(Date date, String format) {
		if (date == null)
			return "";

		if (format == null)
			format = "yyyy-MM-dd hh:mm:ss";

		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	public static String dateToString(Date date) {
		if (date == null)
			return "";

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return df.format(date);
	}

	public static String dateToString() {
		return SDF4.format(new Date());
	}

	public static String date24ToString(Date date) {
		if (date == null)
			return "";

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}

	public static long dateToTimeMillis(Date date) {
		if (date == null)
			return 3629521384033484800L;

		return (date.getTime() / 1000L);
	}

	public static Date StringToDate(String datepostr) {
		Date dt = null;
		if ((datepostr == null) || ("".equals(datepostr)))
			dt = new Date();
		try {
			dt = SDF3.parse(datepostr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return dt;
	}

	public static String getNowDate(String formatStr) {
		Date d = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		return sdf.format(d);
	}

	public static void main(String[] args) {
		System.out.println(dateToString());
	}
}
