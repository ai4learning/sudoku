package com.goldfish.vo;

/**
 * Created by John on 2018/5/21 0021.
 */
public class CourseVO {

    protected Integer Id;
    protected String moduleCode;
    protected String bookName;
    protected String coverImageUrl;
    protected Integer totalUnitNbr;
    private boolean outDate;
    private String unitType;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
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

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }
}
