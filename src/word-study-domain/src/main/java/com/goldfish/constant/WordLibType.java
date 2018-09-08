package com.goldfish.constant;

/**
 * Created by John on 2018/5/19 0019.
 */
public enum WordLibType {
    REVIEW_BOOK(1,"复习本"),
    ERROR_BOOK(2,"错词本");
    private int type;
    private String desc;

    WordLibType(int type, String desc) {
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
