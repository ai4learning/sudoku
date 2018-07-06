/*
 * Copyright (c) 2013 www.shareyi.com All rights reserved.
 * 本软件源代码版权归shareyi所有,未经许可不得任意复制与传播.
 */
package com.goldfish.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	private final static Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);

	/**
	 * 格式化日期,默认返回yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String format(Date date) {
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 格式化日期,默认返回yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String formatNoTime(Date date) {
		return format(date, "yyyy-MM-dd");
	}

	/**
	 * 日期格式化
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String format(Date date, String format) {
		try {
			if(date == null) {
                return null;
            }
			
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		} catch (Exception e) {
			LOGGER.warn("日期格式化失败", e.getMessage());
		}
		return null;
	}

	/**
	 * 时间格式化， 传入毫秒
	 * 
	 * @param time
	 * @return
	 */
	public static String dateFormat(long time) {
		return format(new Date(time), "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 当前时间
	 * @return
	 */
	public static String currentDate() {
		return format(new Date(), "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 当前时间
	 * @return
	 */
	public static long currentTime() {
		return System.currentTimeMillis();
	}

}
