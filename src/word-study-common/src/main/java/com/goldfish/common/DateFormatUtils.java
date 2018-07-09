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

    public static Date getYesterday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return date;
    }


    /**
     * date1-date2
     * 此处2018年7月9日23:59和2018年7月10日00:01算相差一天
     */
    public static int getDateDifference(Date date1, Date date2)
    {
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(date1);
        calendar2.setTime(date2);
        if (calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)) {
            return calendar1.get(Calendar.DAY_OF_YEAR) - calendar2.get(Calendar.DAY_OF_YEAR);
        }
        else{
            return (int)(calendar1.getTimeInMillis()-calendar2.getTimeInMillis())/(1000*3600*24)+1;
        }
    }
}
