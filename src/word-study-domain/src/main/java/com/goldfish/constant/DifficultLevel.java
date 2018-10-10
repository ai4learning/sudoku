package com.goldfish.constant;

/**
 * Created by John on 2018/5/25 0025.
 */
public enum  DifficultLevel {
    EASY(1, "简单"),MID(2,"中等"), HARD(3,"较难");

    private Integer level;
    private String desc;

    DifficultLevel(Integer level, String desc) {
        this.level = level;
        this.desc = desc;
    }

    public Integer getLevel() {
        return level;
    }
}
