package com.goldfish.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 日期格式化处理工具
 * @author wangyong
 * @date 2015年7月30日下午2:04:25
 */
@Component("dateFormatUtils")
public class DateFormatUtils {
	public static String format(Date date) {
		return DateFormatUtils.format(date, null);
	}

	public static String format(Date date, String pattern) {
		SimpleDateFormat sdf = null;
		if (StringUtils.isEmpty(pattern)) {
			sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");			
		} else {
			sdf = new SimpleDateFormat(pattern);			
		}
		return sdf.format(date);
	}

    public static Date getNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return date;
    }
}
