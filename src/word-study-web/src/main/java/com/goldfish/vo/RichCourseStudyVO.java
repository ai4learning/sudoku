package com.goldfish.vo;

import java.util.List;

/**
 * Created by John on 2018/5/25 0025.
 */
public class RichCourseStudyVO extends RichCourseVO{

    private List<CourseUnitStudyVO> courseUnits;
    private boolean currentStudyBook;
    private Integer startFrom;
    private Integer studyMode;
    private Integer completeWordCount;

    public List<CourseUnitStudyVO> getCourseUnits() {
        return courseUnits;
    }

    public void setCourseUnits(List<CourseUnitStudyVO> courseUnits) {
        this.courseUnits = courseUnits;
    }

    public boolean isCurrentStudyBook() {
        return currentStudyBook;
    }

    public void setCurrentStudyBook(boolean currentStudyBook) {
        this.currentStudyBook = currentStudyBook;
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

    public Integer getCompleteWordCount() {
        return completeWordCount;
    }

    public void setCompleteWordCount(Integer completeWordCount) {
        this.completeWordCount = completeWordCount;
    }
}
