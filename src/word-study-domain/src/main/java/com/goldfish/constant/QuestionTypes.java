package com.goldfish.constant;

/**
 * Created by Administrator on 2018/6/15 0015.
 */
public enum QuestionTypes {
    EN2CH(0,"英译汉","ec","dataEn2Ch"),CH2EN(1,"汉译英","ce","dataCh2En")
    ,LISTEN2CH(2,"听选","lc","dataListen2Ch"),LISTEN2WRITE(3,"听写","lw","dataListen2Write");
    private int number;
    private String describe;
    private String forShort;
    private String fullName;

    QuestionTypes(int number, String describe, String forShort, String fullName) {
        this.number = number;
        this.describe = describe;
        this.forShort = forShort;
        this.fullName = fullName;
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

    public String getFullName() {
        return fullName;
    }

    public static QuestionTypes getQuestionTypesByNumber(int number)
    {
        if (number == 0) {
            return EN2CH;
        }
        if (number == 1) {
            return CH2EN;
        }
        if (number == 2) {
            return LISTEN2CH;
        }
        if (number == 3) {
            return LISTEN2WRITE;
        }
        return EN2CH;
    }

    public static QuestionTypes getQuestionTypesByFullName(String fullName)
    {
        if (fullName.equals(EN2CH.getFullName())) {
            return EN2CH;
        }
        if (fullName.equals(CH2EN.getFullName())) {
            return CH2EN;
        }
        if (fullName.equals(LISTEN2CH.getFullName())) {
            return LISTEN2CH;
        }
        if (fullName.equals(LISTEN2WRITE.getFullName())) {
            return LISTEN2WRITE;
        }
        return EN2CH;
    }
}
