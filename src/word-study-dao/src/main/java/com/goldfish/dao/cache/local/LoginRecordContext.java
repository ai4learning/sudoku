package com.goldfish.dao.cache.local;

import com.goldfish.domain.LoginRecord;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by John on 2018/5/20 0020.
 */
@Component("loginRecordContext")
public class LoginRecordContext {

    private ConcurrentHashMap<String, LoginRecord> context = new ConcurrentHashMap<String, LoginRecord>();

    /**
     *
     * @param traningId
     * @param traningCode
     * @return
     */
    public LoginRecord getByTraning(String traningId, String traningCode) {
        return context.get(traningId+"_"+traningCode);
    }

    /**
     *
     * @param traningId
     * @param traningCode
     * @return
     */
    public void addByTraning(String traningId, String traningCode, LoginRecord loginRecord) {
        context.put(traningId + "_" + traningCode, loginRecord);
    }

    /**
     *
     * @param traningId
     * @param traningCode
     * @return
     */
    public LoginRecord removeByTraning(String traningId, String traningCode) {
        return context.remove(traningId + "_" + traningCode);
    }
}
