package com.goldfish.vo.teacher;

import java.util.List;

/**
 * @author zhangjingtao
 * @date 2018/10/19 0019.
 */
public class BatchAddStudentVO {
    private String prefix;
    private String passwd;
    private List<String> lessonIds;
    private String currentClass;
    private Integer total;
    private Integer userState;
    private Integer state;

    public BatchAddStudentVO() {
    }

    public BatchAddStudentVO(String prefix, String passwd, List<String> lessonIds, String currentClass, Integer total, Integer userState, Integer state) {
        this.prefix = prefix;
        this.passwd = passwd;
        this.lessonIds = lessonIds;
        this.currentClass = currentClass;
        this.total = total;
        this.userState = userState;
        this.state = state;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String password) {
        this.passwd = password;
    }

    public List<String> getLessonIds() {
        return lessonIds;
    }

    public void setLessonIds(List<String> lessonIds) {
        this.lessonIds = lessonIds;
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

    public Integer getUserState() {
        return userState;
    }

    public void setUserState(Integer userState) {
        this.userState = userState;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "BatchAddStudentVO{" +
                "prefix='" + prefix + '\'' +
                ", passwd='" + passwd + '\'' +
                ", lessonIds=" + lessonIds +
                ", currentClass='" + currentClass + '\'' +
                ", total=" + total +
                ", userState=" + userState +
                ", state=" + state +
                '}';
    }
}
