package com.goldfish.vo;

/**
 * Created by John on 2018/5/21 0021.
 */
public class BaseVO {

    protected boolean success;
    protected String msg;
    private Integer totalNbr = 0;
    private Integer condition = 0;

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

    public Integer getTotalNbr() {
        return totalNbr;
    }

    public void setTotalNbr(Integer totalNbr) {
        this.totalNbr = totalNbr;
    }

    public Integer getCondition() {
        return condition;
    }

    public void setCondition(Integer condition) {
        this.condition = condition;
    }
}
