package com.goldfish.vo.course;

import com.goldfish.vo.course.CourseVO;

/**
 * Created by John on 2018/5/25 0025.
 */
public class RichCourseVO extends CourseVO {

    private Integer difficultLevel;
//    private List<CourseUnitVO> courseUnits;

    public Integer getDifficultLevel() {
        return difficultLevel;
    }

    public void setDifficultLevel(Integer difficultLevel) {
        this.difficultLevel = difficultLevel;
    }

//    public List<CourseUnitVO> getCourseUnits() {
//        return courseUnits;
//    }
//
//    public void setCourseUnits(List<CourseUnitVO> courseUnits) {
//        this.courseUnits = courseUnits;
//    }
}
