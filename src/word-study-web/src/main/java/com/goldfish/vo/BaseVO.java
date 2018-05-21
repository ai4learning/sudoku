package com.goldfish.vo;

/**
 * Created by John on 2018/5/21 0021.
 */
public class BaseVO {

    protected boolean success;
    protected String msg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
