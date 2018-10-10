package com.goldfish.constant;

/**
 * Created by John on 2018/5/19 0019.
 */
public enum MemoryLevel {
    NEW_WORD(1,"生词"),FAMILIAR_WORD(2,"熟词"),HALF(3,"半生熟");

    private int level;
    private String desc;

    MemoryLevel(int level, String desc) {
        this.level = level;
        this.desc = desc;
    }

    public int getLevel() {
        return level;
    }

    public String getDesc() {
        return desc;
    }
}
