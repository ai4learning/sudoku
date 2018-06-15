package com.goldfish.vo.course;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by John on 2018/5/25 0025.
 */
public class CourseUnitVO {
    @JSONField( name="Id")
    private Long id;
    private String courseModuleCode;
    private String unit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCourseModuleCode() {
        return courseModuleCode;
    }

    public void setCourseModuleCode(String courseModuleCode) {
        this.courseModuleCode = courseModuleCode;
    }
}
