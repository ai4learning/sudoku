package com.goldfish.vo.exam;

/**
 * Created by Administrator on 2018/6/13 0013.
 */

/**
 * {
                 "answerIndex":"B",
                 "choices":{
                     "A":"在…上方prep.",
                     "B":"猪肉",
                     "C":"num./n./a.一千;[pl.]许许多多，成千上万",
                     "D":"adv. 通常地;一般地"
                 },
                 "spelling":"pork",
                 "vocCode":"8a108cb7-42aa-d911-0142-ad81235501ae",
                 "question":"pork"
    }
 */
public class QuestionVO {
    private String answerIndex;
    private String spelling;
    private String vocCode;
    private String question;
    private ChoicesVO choices;
    private long questionId;

    public QuestionVO()
    {
    }

    public QuestionVO(String answerIndexString, String spellingString, String vocCodeSting, String questionString, ChoicesVO choicesVO, long id)
    {
        answerIndex = answerIndexString;
        spelling = spellingString;
        vocCode = vocCodeSting;
        question = questionString;
        choices = choicesVO;
        questionId = id;
    }

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

    public ChoicesVO getChoices() {
        return choices;
    }

    public void setChoices(ChoicesVO choices) {
        this.choices = choices;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }
}
