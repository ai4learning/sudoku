package com.goldfish.constant;

/**
 * Created by John on 2018/5/16 0016.
 */
public enum ActivateCodeState {
    NORMAL(1,"正常"),EXPIRED(2, "超期"),INVALID(3,"无效"),USED(4,"已使用");

    private int state;
    private String desc;

    ActivateCodeState(int state, String desc) {
        this.state = state;
        this.desc = desc;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
