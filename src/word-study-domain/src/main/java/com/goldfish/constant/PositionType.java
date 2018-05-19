package com.goldfish.constant;

/**
 * Created by John on 2018/5/19 0019.
 */
public enum PositionType {
    WORD(1,"单词位置"),UNIT(2,"单元位置");

    private int type;
    private String desc;

    PositionType(int type, String desc) {
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
