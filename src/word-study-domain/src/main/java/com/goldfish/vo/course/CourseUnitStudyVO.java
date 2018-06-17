package com.goldfish.vo.course;

import com.alibaba.fastjson.annotation.JSONField;
import com.goldfish.vo.course.CourseUnitVO;

/**
 * Created by John on 2018/5/25 0025.
 */
public class CourseUnitStudyVO extends CourseUnitVO {

    @JSONField(name="IsFinished")
    private Integer isFinished;
    @JSONField(name="IsTested")
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
