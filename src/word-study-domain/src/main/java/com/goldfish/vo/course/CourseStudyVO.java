package com.goldfish.vo.course;


public class CourseStudyVO extends CourseVO {
    private boolean currentStudyBook;
    private Integer startFrom;
    private Integer studyMode;

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
}
