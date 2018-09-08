package com.goldfish.common.log;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import java.util.UUID;

/**
 * 给每个线程增加UUid标示，用来打印日志输出
 * @author yizhao
 *
 */
public class LogBusinessId {
    private static final ThreadLocal<String> businessLocal = new ThreadLocal<String>();
    private static final String KEY_TID = "TID";

    /**
     * 返回当前线程对应的uuid
     * @return
     */
    public static String getCurBusinessId(){
        String uuid = businessLocal.get();
        if(StringUtils.isEmpty(uuid)){
            uuid = UUID.randomUUID().toString().replace(MarkConstant.SPLIT_CODE, "");
            businessLocal.set(uuid);
        }
        return uuid;
    }


    public static String requestId(){
        return MDC.get(KEY_TID);
    }
    public static String requestInfoStr(){
        String tid = MDC.get(KEY_TID);
        if(StringUtils.isBlank(tid)){
            return "[requestId="+tid+"]";
        }
        return "";
    }


    /**
     * 给当前线程重新设置
     */
    public static void clear(){
        businessLocal.set(null);
    }
}
