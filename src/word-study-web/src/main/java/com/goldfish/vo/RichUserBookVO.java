package com.goldfish.vo;

import java.util.List;

/**
 * Created by John on 2018/5/25 0025.
 */
public class RichUserBookVO extends UserVO {

    private List<RichCourseStudyVO> books;

    public List<RichCourseStudyVO> getBooks() {
        return books;
    }

    public void setBooks(List<RichCourseStudyVO> books) {
        this.books = books;
    }
}
