package com.goldfish.vo.word;

/**
 * @author zhangjingtao
 * @date 2019/1/9 0009.
 */
public class SimilarWordVO {
    private String spelling;
    private String meaning;

    public SimilarWordVO() {
    }

    public SimilarWordVO(String spelling, String meaning) {
        this.spelling = spelling;
        this.meaning = meaning;
    }

    public String getSpelling() {
        return spelling;
    }

    public void setSpelling(String spelling) {
        this.spelling = spelling;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}
