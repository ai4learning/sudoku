package com.goldfish.vo.course;

import com.goldfish.domain.Course;

/**
 *         "Id":5,
 *         "moduleCode":"8a108cb7-42ad-314f-0142-ad33a0a70001",
 *         "bookName":"零基础入门拼读",
 *         "coverImageUrl":"",
 *         "totalUnitNbr":17,
 *         "outDate":false,
 *         "startFrom":0,
 *         "studyMode":0
 *
 * Created by John on 2018/6/2 0002.
 */
public class BookStudyVO extends BookVO{
    private Integer startFrom;
    private Integer studyMode;


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

    public static BookStudyVO generateBookVO(Course course){
        BookStudyVO bookVO = new BookStudyVO();
        bookVO.setBookName(course.getBookName());
        bookVO.setCoverImageUrl(course.getCoverImageUrl());
        bookVO.setModuleCode(course.getModuleCode());
        bookVO.setTotalUnitNbr(course.getTotalUnitNbr());
        bookVO.setId(course.getBookNumber());
        return bookVO;
    }
}
