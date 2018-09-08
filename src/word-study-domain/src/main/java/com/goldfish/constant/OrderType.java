package com.goldfish.constant;

/**
 * Created by John on 2018/5/19 0019.
 */
public enum OrderType {
    PRIMARY(1,"复习本"),
    JUNIOR(2,"初中"),
    SENIOR(3,"高中");
    private int type;
    private String desc;

    OrderType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public int getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
