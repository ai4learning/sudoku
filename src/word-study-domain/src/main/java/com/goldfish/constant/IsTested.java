package com.goldfish.constant;

/**
 * Created by Administrator on 2018/6/21 0021.
 */
public enum IsTested {
    FAIL(-1, "未通过"), UNTESTED(0, "未测试"), FULL_MARKS(1, "满分通过"), PASS(2, "通过，未满分");
    private int code;
    private String description;

    IsTested(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static IsTested getIsTestedByScore(int score) {
        if (score >= 0 && score < 90)
            return FAIL;
        else if (score >= 90 && score < 100)
            return PASS;
        else if (score == 100)
            return FULL_MARKS;
        return UNTESTED;
    }
}
