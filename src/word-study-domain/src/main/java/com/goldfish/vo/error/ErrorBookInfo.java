package com.goldfish.vo.error;

import com.alibaba.fastjson.annotation.JSONField;

/**
 *          "Id":0,
         "moduleCode":"00000000-0000-0000-0000-000000000000",
         "bookName":"错词强化学...",
         "totalUnitNbr":0,
         "outDate":false,
         "startFrom":0,
         "studyMode":0
 * Created by John on 2018/6/16 0016.
 */
public class ErrorBookInfo {

    @JSONField( name="Id")
    protected Integer id = 0;
    protected String moduleCode = "00000000-0000-0000-0000-000000000000";
    protected String bookName = "错词强化学...";
    protected Integer totalUnitNbr = 0;
    private boolean outDate = false;
    protected Integer startFrom=0;
    protected Integer studyMode=0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getTotalUnitNbr() {
        return totalUnitNbr;
    }

    public void setTotalUnitNbr(Integer totalUnitNbr) {
        this.totalUnitNbr = totalUnitNbr;
    }

    public boolean isOutDate() {
        return outDate;
    }

    public void setOutDate(boolean outDate) {
        this.outDate = outDate;
    }

    public Integer getStartFrom() {
        return startFrom;
    }

    public void setStartFrom(Integer startFrom) {
        this.startFrom = startFrom;
    }

    public Integer getStudyMode() {
        return studyMode;
    }

    public void setStudyMode(Integer studyMode) {
        this.studyMode = studyMode;
    }
}
