package com.goldfish.vo.teacher;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author zhangjingtao
 * @date 2018/10/19 0019.
 */
public class BatchAddStudentVO {
    private String prefix;
    private String password;
    @JSONField(name="lesson_ids")
    private String lessonIdList;
    @JSONField(name="current_class")
    private String currentClass;
    private Integer total;

    public BatchAddStudentVO() {
    }

    public BatchAddStudentVO(String prefix, String password, String lessonIdList, String currentClass, Integer total) {
        this.prefix = prefix;
        this.password = password;
        this.lessonIdList = lessonIdList;
        this.currentClass = currentClass;
        this.total = total;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLessonIdList() {
        return lessonIdList;
    }

    public void setLessonIdList(String lessonIdList) {
        this.lessonIdList = lessonIdList;
    }

    public String getCurrentClass() {
        return currentClass;
    }

    public void setCurrentClass(String currentClass) {
        this.currentClass = currentClass;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "BatchAddStudentVO{" +
                "prefix='" + prefix + '\'' +
                ", password='" + password + '\'' +
                ", lessonIdList='" + lessonIdList + '\'' +
                ", currentClass='" + currentClass + '\'' +
                ", total=" + total +
                '}';
    }
}
