package com.goldfish.constant;

/**
 * Created by Administrator on 2018/6/15 0015.
 */
public enum QuestionTypes {
    EN2CH(0,"英译汉","ec"),CH2EN(1,"汉译英","ce"),LISTEN2CH(2,"听选","lc"),LISTEN2WRITE(3,"听写","lw");
    private int number;
    private String describe;
    private String forShort;

    QuestionTypes(int number, String describe, String forShort) {
        this.number = number;
        this.describe = describe;
        this.forShort = forShort;
    }

    public int getNumber() {
        return number;
    }

    public String getDescribe() {
        return describe;
    }

    public String getForShort() {
        return forShort;
    }

    public static QuestionTypes getQuestionTypesByNumber(int number)
    {
        if (number == 0)
            return EN2CH;
        if (number == 1)
            return CH2EN;
        if (number == 2)
            return LISTEN2CH;
        if (number == 3)
            return LISTEN2WRITE;
        return EN2CH;
    }
}
