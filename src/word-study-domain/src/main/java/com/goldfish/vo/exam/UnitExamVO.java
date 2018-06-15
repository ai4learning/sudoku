package com.goldfish.vo.exam;

/**
 * Created by Administrator on 2018/6/13 0013.
 */

/**
 *  {
     "data":{
         "dataEn2Ch":Array[15],
         "dataCh2En":Array[15],
         "dataListen2Ch":Array[10],
         "msg":"成功！",
         "success":true,
         "condition":0
     },
     "success":true,
     "condition":2,
     "studytoken":"00000000-0000-0000-0000-000000000000",
     "totalNbr":0
 }
 */
public class UnitExamVO {
    private DataVO data;
    private boolean success;
    private int condition;
    private String studytoken = "00000000-0000-0000-0000-000000000000";
    private int totalNbr;

    public DataVO getData() {
        return data;
    }

    public void setData(DataVO dataVO) {
        this.data = dataVO;
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

    public String getStudytoken() {
        return studytoken;
    }

    public void setStudytoken(String studytoken) {
        this.studytoken = studytoken;
    }

    public int getTotalNbr() {
        return totalNbr;
    }

    public void setTotalNbr(int totalNbr) {
        this.totalNbr = totalNbr;
    }
}
