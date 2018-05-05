package com.goldfish.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangyong on 2015/10/9.
 */
public class CommonResult<T> {
    private boolean success;
    private T result;
    private Map<String, T> resultMap = new HashMap<String, T>();
    private Map<String, Object> otherMap = new HashMap<String, Object>();



    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void addDefaultModel(T object) {
        this.result = object;
    }

    public T getDefaultModel() {
        return result;
    }

    public void addDefaultModel(String key, T value) {
        this.resultMap.put(key, value);
    }

    public Map<String,T> getDefaultMapModel() {
        return this.resultMap;
    }

    public Map<String, Object> getModel() {
        return otherMap;
    }

    public void addModel(String key, Object value) {
        this.otherMap.put(key, value);
    }

    public Map<String, Object> getReturnMap() {
        Map<String,Object> returnMap = new HashMap<String, Object>();
        if(this.resultMap !=null && this.resultMap.size()>0) {
            returnMap.putAll(resultMap);
        }
        if(this.otherMap !=null && this.otherMap.size()>0) {
            returnMap.putAll(otherMap);
        }
        returnMap.put("success",this.isSuccess());
        return returnMap;
    }

}
