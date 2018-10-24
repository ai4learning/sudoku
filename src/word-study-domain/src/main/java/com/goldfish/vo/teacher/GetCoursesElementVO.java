package com.goldfish.vo.teacher;

/**
 * @author zhangjingtao
 * @date 2018/10/19 0019.
 */
public class GetCoursesElementVO {
    private Integer id;
    private String bookName;

    public GetCoursesElementVO() {
    }

    public GetCoursesElementVO(Integer id, String bookName) {
        this.id = id;
        this.bookName = bookName;
    }

    @Override
    public String toString() {
        return "GetCoursesElementVO{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
