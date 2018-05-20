package com.goldfish.constant;

/**
 * Created by John on 2018/5/6 0006.
 */
public enum UserState {
    NORMAL(1, "正常"),
    ABNORMAL(2, "欠费"),
    INVALID(3,"不可用");

    private int state;
    private String desc;

    UserState(int state, String desc) {
        this.state = state;
        this.desc = desc;
    }

    public int getState() {
        return state;
    }
}
