package com.goldfish.vo.statistic;

/**
 * Created by Administrator on 2018/6/20 0020.
 */

import com.alibaba.fastjson.annotation.JSONField;

/**
 * {
 "Id": 0,
 "resultScore": 0,
 "createtime": "2018-05-28 23:12:47",
 "testType": 5,
 "unitNbr": "0",
 "realDuration": 60,
 "bookName": "自主测试"
 }
 */
public class BasicTestResultVO {
    @JSONField(name="Id")
    private int id;
    private int resultScore;
    private String createtime;
    private int testType;
    private String unitNbr;
    private int realDuration;
    private String bookName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResultScore() {
        return resultScore;
    }

    public void setResultScore(int resultScore) {
        this.resultScore = resultScore;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public int getTestType() {
        return testType;
    }

    public void setTestType(int testType) {
        this.testType = testType;
    }

    public String getUnitNbr() {
        return unitNbr;
    }

    public void setUnitNbr(String unitNbr) {
        this.unitNbr = unitNbr;
    }

    public int getRealDuration() {
        return realDuration;
    }

    public void setRealDuration(int realDuration) {
        this.realDuration = realDuration;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
