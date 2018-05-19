package com.goldfish.constant;

/**
 * Created by John on 2018/5/19 0019.
 */
public enum TaskType {
    INIT_STUDY_DATA(1,"用户学习数据初始化"),GEN_ERROR_BOOK(2,"生成错词本"),
    GEN_REVIEW_BOOK(2,"生成复习本");

    private int type;
    private String desc;

    TaskType(int type, String desc) {
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
