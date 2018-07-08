package com.goldfish.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author zhangjingtao
 * @date 2018/7/8 0008.
 */
public class ClassGrade {

    private Long id;

    /**  创建时间  */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date start;
    /**  更新时间  */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date end;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "ClassGrade{" +
                "id=" + id +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
