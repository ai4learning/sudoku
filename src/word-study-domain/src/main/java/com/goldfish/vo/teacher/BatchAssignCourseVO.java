package com.goldfish.vo.teacher;

import org.springframework.util.StringUtils;

/**
 * @author zhangjingtao
 * @date 2018/11/19 0019.
 */
public class BatchAssignCourseVO {
    private Integer currentClass;
    private String userIds;
    private String lessonIds;

    public BatchAssignCourseVO(Integer currentClass, String userIds, String lessonIds) {
        this.currentClass = currentClass;
        this.userIds = userIds;
        this.lessonIds = lessonIds;
    }

    public BatchAssignCourseVO() {
    }

    public Integer getCurrentClass() {
        return currentClass;
    }

    public void setCurrentClass(Integer currentClass) {
        this.currentClass = currentClass;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public String getLessonIds() {
        return lessonIds;
    }

    public void setLessonIds(String lessonIds) {
        this.lessonIds = lessonIds;
    }

    @Override
    public String toString() {
        return "BatchAssignCourseVO{" +
                "currentClass=" + currentClass +
                ", userIds='" + userIds + '\'' +
                ", lessonIds='" + lessonIds + '\'' +
                '}';
    }

    public boolean isVaild(){
        return !StringUtils.isEmpty(userIds) && !StringUtils.isEmpty(lessonIds);
    }
}
