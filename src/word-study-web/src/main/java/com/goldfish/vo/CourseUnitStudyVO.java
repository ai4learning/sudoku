package com.goldfish.vo;

/**
 * Created by John on 2018/5/25 0025.
 */
public class CourseUnitStudyVO extends CourseUnitVO {

    private Integer isFinished;
    private Integer isTested;

    public Integer getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(Integer isFinished) {
        this.isFinished = isFinished;
    }

    public Integer getIsTested() {
        return isTested;
    }

    public void setIsTested(Integer isTested) {
        this.isTested = isTested;
    }
}
