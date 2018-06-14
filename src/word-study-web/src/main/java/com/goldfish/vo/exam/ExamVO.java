package com.goldfish.vo.exam;

import com.goldfish.domain.Question;

import java.util.List;

/**
 * {
 *         "dataEn2Ch":[
 *             {
 *                 "answerIndex":"A",
 *                 "choices":{
 *                     "A":"柠檬",
 *                     "B":"笑话,玩笑",
 *                     "C":"根本不;单单除……之外",
 *                     "D":"v.发嘟嘟声，吼叫"
 *                 },
 *                 "spelling":"lemon",
 *                 "vocCode":"8a108cb7-42aa-d911-0142-ad8121430085",
 *                 "question":"lemon"
 *             }
 *         ],
 *         "dataCh2En":[
 *             {
 *                 "answerIndex":"A",
 *                 "choices":{
 *                     "A":"fat",
 *                     "B":"skillfulness",
 *                     "C":"miscreant",
 *                     "D":"concrete"
 *                 },
 *                 "spelling":"fat",
 *                 "vocCode":"8a108cb7-42aa-d911-0142-ad812032002b",
 *                 "question":"肥的;油腻的"
 *             }
 *         ],
 *         "dataListen2Ch":[
 *             {
 *                 "answerIndex":"B",
 *                 "choices":{
 *                     "A":"让路,让步",
 *                     "B":"账单",
 *                     "C":"农民",
 *                     "D":"愚蠢的"
 *                 },
 *                 "spelling":"bill",
 *                 "vocCode":"8a108cb7-42aa-d911-0142-ad8121ae00a6",
 *                 "question":"bill"
 *             }
 *         ],
 *         "dataListen2Write":[
 *             {
 *                 "answerIndex":"A",
 *                 "spelling":"lent",
 *                 "vocCode":"8a108cb7-42aa-d911-0142-ad81213e0080",
 *                 "question":"借出（过去式）;（基督教）打斋节"
 *             }
 *         ],
 *         "msg":"成功！",
 *         "success":true,
 *         "condition":0
 *     }
 */
public class ExamVO {
    private List<QuestionVO> dataEn2Ch;
    private List<QuestionVO> dataCh2En;
    private List<QuestionVO> dataListen2Ch;
    private List<Listen2WriteVO> dataListen2Write;
    private String msg;
    private boolean success;
    private int condition;

    public List<QuestionVO> getDataEn2Ch() {
        return dataEn2Ch;
    }

    public void setDataEn2Ch(List<QuestionVO> dataEn2Ch) {
        this.dataEn2Ch = dataEn2Ch;
    }

    public List<QuestionVO> getDataCh2En() {
        return dataCh2En;
    }

    public void setDataCh2En(List<QuestionVO> dataCh2En) {
        this.dataCh2En = dataCh2En;
    }

    public List<QuestionVO> getDataListen2Ch() {
        return dataListen2Ch;
    }

    public void setDataListen2Ch(List<QuestionVO> dataListen2Ch) {
        this.dataListen2Ch = dataListen2Ch;
    }

    public List<Listen2WriteVO> getDataListen2Write() {
        return dataListen2Write;
    }

    public void setDataListen2Write(List<Listen2WriteVO> dataListen2Write) {
        this.dataListen2Write = dataListen2Write;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }
}
