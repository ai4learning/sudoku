package com.goldfish.vo.exam;


/**
 * {
 *  *                 "answerIndex":"A",
 *  *                 "spelling":"lent",
 *  *                 "vocCode":"8a108cb7-42aa-d911-0142-ad81213e0080",
 *  *                 "question":"借出（过去式）;（基督教）打斋节"
 *  *             }
 */
public class Listen2WriteVO {
    private String answerIndex;
    private String spelling;
    private String vocCode;
    private String question;

    public String getAnswerIndex() {
        return answerIndex;
    }

    public void setAnswerIndex(String answerIndex) {
        this.answerIndex = answerIndex;
    }

    public String getSpelling() {
        return spelling;
    }

    public void setSpelling(String spelling) {
        this.spelling = spelling;
    }

    public String getVocCode() {
        return vocCode;
    }

    public void setVocCode(String vocCode) {
        this.vocCode = vocCode;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
