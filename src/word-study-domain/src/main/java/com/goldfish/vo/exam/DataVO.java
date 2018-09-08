package com.goldfish.vo.exam;

/**
 * Created by Administrator on 2018/6/13 0013.
 */

import java.util.List;

/**
 * "data":{
         "dataEn2Ch":Array[15],
         "dataCh2En":Array[15],
         "dataListen2Ch":Array[10],
         "msg":"成功！",
         "success":true,
         "condition":0
     }
 */
public class DataVO {
    private List<QuestionVO> dataEn2Ch;
    private List<QuestionVO> dataCh2En;
    private List<QuestionVO> dataListen2Ch;
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
