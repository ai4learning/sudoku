package com.goldfish.constant;

/**
 * Created by John on 2018/5/6 0006.
 */
public enum State {
    VALID(1, "有效"),
    IN_VALID(2, "无效"),
    UNKNOW(-1,"未知"),
    YES(1,"是"),
    NO(0,"否");

    private int state;
    private String desc;

    State(int state, String desc) {
        this.state = state;
        this.desc = desc;
    }

    public int getState() {
        return state;
    }
}
