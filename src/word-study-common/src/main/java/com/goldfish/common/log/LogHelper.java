package com.goldfish.common.log;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Describe: User: BjYangKai Date: 2011-4-21 Time: 12:00:33
 */
public class LogHelper {
    /**
     * stock相关重要日志
     */
    public static final String CHANGE_STOCK = "CHANGE-STOCK";
    public static final long targetVenderId = 10276;
    public static final int flag = 1;
    private static Map<LogTypeEnum, Log> logMap = new HashMap<LogTypeEnum, Log>();

    static {
        for (LogTypeEnum ft : LogTypeEnum.values()) {
            logMap.put(ft, LogFactory.getLog(ft.getKey()));
        }
    }

    public static final Log changeStockLog = LogFactory.getLog(CHANGE_STOCK);

    public static void logStockInfo(String message, long venderId, long skuId) {
        if (venderId == targetVenderId) {
            changeStockLog.info("CHANGE-STOCK:商家编号:" + venderId + ",商品编号:"
                    + skuId + "," + message);
        }

    }
    
    /**
     * 
     * @param str
     * @return
     */
    public static  String addLogbusinessId(String str){
       if(StringUtils.isEmpty(str)){
           return str;
       }else{
           return "业务标示ID["+LogBusinessId.getCurBusinessId()+"],"+str;
       }
    }

    /**
     * 记录DEBUG信息
     *
     * @param type 日志业务类型
     * @param str
     * @param e
     */
    public static void debug(LogTypeEnum type, String str, Throwable e) {
        getLog(type).debug(addLogbusinessId(str), e);
    }

    /**
     * 记录INFO信息
     *
     * @param type 日志业务类型
     * @param str
     * @param e
     */
    public static void info(LogTypeEnum type, String str, Throwable e) {
        getLog(type).info(str, e);
    }

    /**
     * 记录warn信息
     *
     * @param type 日志业务类型
     * @param str
     * @param e
     */
    public static void warn(LogTypeEnum type, String str, Throwable e) {
        getLog(type).warn(addLogbusinessId(str), e);
    }

    /**
     * 记录error信息
     *
     * @param type 日志业务类型
     * @param str
     * @param e
     */
    public static void error(LogTypeEnum type, String str, Throwable e) {
        getLog(type).error(addLogbusinessId(str), e);
    }

    /**
     * 记录fatal信息
     *
     * @param type 日志业务类型
     * @param str  日志信息
     * @param e
     */
    public static void fatal(LogTypeEnum type, String str, Throwable e) {
        getLog(type).fatal(addLogbusinessId(str), e);
    }

    /**
     * 使用默认的文件记录debug信息
     *
     * @param str 日志信息
     * @param e
     */
    public static void debug(String str, Throwable e) {
        debug(LogTypeEnum.DEFAULT, str, e);
    }

    /**
     * 使用默认的文件记录info信息
     *
     * @param str 日志信息
     * @param e
     */
    public static void info(String str, Throwable e) {
        info(LogTypeEnum.DEFAULT, str, e);
    }

    /**
     * 使用默认的文件记录warn信息
     *
     * @param str 日志信息
     * @param e
     */
    public static void warn(String str, Throwable e) {
        warn(LogTypeEnum.DEFAULT, str, e);
    }

    /**
     * 使用默认的文件记录error信息
     *
     * @param str 日志信息
     * @param e
     */
    public static void error(String str, Throwable e) {
        error(LogTypeEnum.DEFAULT, str, e);
    }

    /**
     * 使用默认的文件记录fatal信息
     *
     * @param str 日志信息
     * @param e
     */
    public static void fatal(String str, Throwable e) {
        fatal(LogTypeEnum.DEFAULT, str, e);
    }

    /* added by liqing */

    /**
     * 记录DEBUG信息
     *
     * @param type 日志业务类型
     * @param str
     */
    public static void debug(LogTypeEnum type, String str) {
        getLog(type).debug(addLogbusinessId(str));
    }

    /**
     * 记录INFO信息
     *
     * @param type 日志业务类型
     * @param str
     */
    public static void info(LogTypeEnum type, String str) {
        getLog(type).info(addLogbusinessId(str));
    }

    /**
     * 记录warn信息
     *
     * @param type 日志业务类型
     * @param str
     */
    public static void warn(LogTypeEnum type, String str) {
        getLog(type).warn(addLogbusinessId(str));
    }

    /**
     * 记录error信息
     *
     * @param type 日志业务类型
     * @param str
     */
    public static void error(LogTypeEnum type, String str) {
        getLog(type).error(addLogbusinessId(str));
    }

    /**
     * 记录fatal信息
     *
     * @param type 日志业务类型
     * @param str  日志信息
     */
    public static void fatal(LogTypeEnum type, String str) {
        getLog(type).fatal(addLogbusinessId(str));
    }

    /**
     * 使用默认的文件记录debug信息
     *
     * @param str 日志信息
     */
    public static void debug(String str) {
        debug(LogTypeEnum.DEFAULT, str);
    }

    /**
     * 使用默认的文件记录info信息
     *
     * @param str 日志信息
     */
    public static void info(String str) {
        info(LogTypeEnum.DEFAULT, str);
    }

    /**
     * 使用默认的文件记录warn信息
     *
     * @param str 日志信息
     */
    public static void warn(String str) {
        warn(LogTypeEnum.DEFAULT, str);
    }

    /**
     * 使用默认的文件记录error信息
     *
     * @param str 日志信息
     */
    public static void error(String str) {
        error(LogTypeEnum.DEFAULT, str);
    }

    /**
     * 使用默认的文件记录fatal信息
     *
     * @param str 日志信息
     */
    public static void fatal(String str) {
        fatal(LogTypeEnum.DEFAULT, str);
    }

    /**
     * 记录运行期错误信息
     *
     * @param e
     */
    public static void execption(Throwable e) {
        error(LogTypeEnum.EXCEPTION, getExceptionTrace(e));
    }

    /**
     * 打印错误信息
     *
     * @param e
     * @return
     */
    public static String getExceptionTrace(Throwable e) {
        if (e != null) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            return sw.toString();
        } else {
            return null;
        }
    }

    public static Log getLog(LogTypeEnum type) {
        if (logMap.get(type) == null) {
            return LogFactory.getLog(LogHelper.class);
        } else {
            return logMap.get(type);
        }
    }

    /**
     * 日志消息占位符形式的替换，按照传入参数依次替换"{}"
     *
     * @param str  带占位符的日志消息
     * @param args 需要被替换的参数，除了字符串和基本类型的对象形式外，其他类型需要自己实现toString方法
     * @return
     */
    public static <T extends Object> String replace(String str, T... args) {
    	if (str == null) {
            return addLogbusinessId(str);
        }
        if (args == null || args.length < 1) {
            return  addLogbusinessId(str.replaceAll("\\{\\}", " null"));
        }
        StringBuilder builder = new StringBuilder(str);
        int start = builder.indexOf("{");
        int end = builder.indexOf("}", start);
        for (Object arg : args) {
            if (start == -1) {
                break;
            }
            if(arg == null){
                arg="null";
            }
            builder.replace(start, end + 1, arg.toString());
            start = start+arg.toString().length();
            start = builder.indexOf("{", start);
            end = builder.indexOf("}", start);
        }
        return addLogbusinessId(builder.toString());
    }
    
    

    /**
     * 记录DEBUG信息
     *
     * @param type 日志业务类型
     * @param str
     */
    public static void debug(LogTypeEnum type, String str, Object... args) {
        Log log = getLog(type);
        if (log.isDebugEnabled()) {
            String msg = replace(str, args);
            log.debug(msg);
        }
    }

    /**
     * 记录INFO信息
     *
     * @param type 日志业务类型
     * @param str
     */
    public static void info(LogTypeEnum type, String str, Object... args) {
        Log log = getLog(type);
        if (log.isInfoEnabled()) {
            String msg = replace(str, args);
            log.info(msg);
        }
    }

    /**
     * 记录warn信息
     *
     * @param type 日志业务类型
     * @param str
     */
    public static void warn(LogTypeEnum type, String str, Object... args) {
        Log log = getLog(type);
        if (log.isWarnEnabled()) {
            String msg = replace(str, args);
            log.warn(msg);
        }
    }

    /**
     * 记录error信息
     *
     * @param type 日志业务类型
     * @param str
     */
    public static void error(LogTypeEnum type, String str, Object... args) {
        Log log = getLog(type);
        if (log.isErrorEnabled()) {
            String msg = replace(str, args);
            log.error(msg);
        }
    }

    /**
     * 记录fatal信息
     *
     * @param type 日志业务类型
     * @param str  日志信息
     */
    public static void fatal(LogTypeEnum type, String str, Object... args) {
        Log log = getLog(type);
        if (log.isFatalEnabled()) {
            String msg = replace(str, args);
            log.fatal(msg);
        }
    }

    /**
     * 记录DEBUG信息
     *
     * @param type 日志业务类型
     * @param str
     */
    public static void debug(LogTypeEnum type, Throwable e, String str,
                             Object... args) {
        Log log = getLog(type);
        if (log.isDebugEnabled()) {
            String msg = replace(str, args);
            log.debug(msg, e);
        }
    }

    /**
     * 记录INFO信息
     *
     * @param type 日志业务类型
     * @param str
     */
    public static void info(LogTypeEnum type, Throwable e, String str,
                            Object... args) {
        Log log = getLog(type);
        if (log.isInfoEnabled()) {
            String msg = replace(str, args);
            log.info(msg, e);
        }
    }

    /**
     * 记录warn信息
     *
     * @param type 日志业务类型
     * @param str
     */
    public static void warn(LogTypeEnum type, Throwable e, String str,
                            Object... args) {
        Log log = getLog(type);
        if (log.isWarnEnabled()) {
            String msg = replace(str, args);
            log.warn(msg, e);
        }
    }

    /**
     * 记录error信息
     *
     * @param type 日志业务类型
     * @param str
     */
    public static void error(LogTypeEnum type, Throwable e, String str,
                             Object... args) {
        Log log = getLog(type);
        if (log.isErrorEnabled()) {
            String msg = replace(str, args);
            log.error(msg, e);
        }
    }

    /**
     * 记录fatal信息
     *
     * @param type 日志业务类型
     * @param str  日志信息
     */
    public static void fatal(LogTypeEnum type, Throwable e, String str,
                             Object... args) {
        Log log = getLog(type);
        if (log.isFatalEnabled()) {
            String msg = replace(str, args);
            log.fatal(msg, e);
        }
    }

}
