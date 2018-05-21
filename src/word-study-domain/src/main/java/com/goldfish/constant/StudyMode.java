package com.goldfish.constant;

/**
 * Created by John on 2018/5/19 0019.
 */
public enum  StudyMode {
    REVERSE(1,"逆向模式"),FORWARD(0,"正向模式");

    private int mode;
    private String desc;

    StudyMode(int mode, String desc) {
        this.mode = mode;
        this.desc = desc;
    }

    public int getMode() {
        return mode;
    }

    public String getDesc() {
        return desc;
    }
}
