package com.goldfish.constant;

/**
 * Created by John on 2018/5/19 0019.
 */
public enum PositionType {
    WORD(1,"单词位置"),
    UNIT(2,"单元位置"),
    COURSE_STUDY_POSITION(3,"课程学习位置"),
    UNIT_STUDY_POSTION(4,"单元学习位置");

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
