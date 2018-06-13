package com.goldfish.vo.course;

import com.alibaba.fastjson.annotation.JSONField;

/**
 *         "Id":5,
 *         "moduleCode":"8a108cb7-42ad-314f-0142-ad33a0a70001",
 *         "bookName":"零基础入门拼读",
 *         "coverImageUrl":"",
 *         "totalUnitNbr":17,
 *         "outDate":false,
 * Created by John on 2018/6/2 0002.
 */
public class BookVO {
    @JSONField( name="Id")
    protected Integer id;
    protected String moduleCode;
    protected String bookName;
    protected String coverImageUrl;
    protected Integer totalUnitNbr;
    private boolean outDate;

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

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
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
}
