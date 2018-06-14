package com.goldfish.constant;

/**
 * Created by Administrator on 2018/6/14 0014.
 */
public enum TestArea {
    NEW_WORD(0,"生词测试"),FAMILIAR_WORD(1,"熟词测试");

    private int number;
    private String desc;

    TestArea(int number, String desc) {
        this.number = number;
        this.desc = desc;
    }

    public int getNumber() {
        return number;
    }

    public String getDesc() {
        return desc;
    }
}
