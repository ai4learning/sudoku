package com.goldfish.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author zhangjingtao
 * @date 2018/7/8 0008.
 */
public class ClassGrade {

    private Long id;

    private Long teacherId;

    private String name;

    /**  创建时间  */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date start;
    /**  更新时间  */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date end;

    /**  创建时间  */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date created;
    /**  更新时间  */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date modified;

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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "ClassGrade{" +
                "id=" + id +
                ", teacherId=" + teacherId +
                ", name='" + name + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}
