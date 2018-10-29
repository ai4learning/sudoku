package com.goldfish.vo.teacher;

/**
 * @author zhangjingtao
 * @date 2018/10/19 0019.
 */

import com.alibaba.fastjson.annotation.JSONField;

/**
 * {
 *     user_id: "wcf",
 *     password: "wcf",
 *     user_code: "wcf",
 *     nick_name: "王超飞",
 *     lesson_ids: "1,2,3",
 *     current_class: "1"
 * }
 */
public class UpdateStudentVO {
    private String userId;
    private String passwd;
    private String userCode;
    private String nikeName;
    private String lessonIds;
    private Long currentClass;
    private Integer userState;
    private Integer state;

    public UpdateStudentVO() {
    }

    public UpdateStudentVO(String userId, String passwd, String userCode, String nikeName, String lessonIds, Long currentClass, Integer userState, Integer state) {
        this.userId = userId;
        this.passwd = passwd;
        this.userCode = userCode;
        this.nikeName = nikeName;
        this.lessonIds = lessonIds;
        this.currentClass = currentClass;
        this.userState = userState;
        this.state = state;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getLessonIds() {
        return lessonIds;
    }

    public void setLessonIds(String lessonIds) {
        this.lessonIds = lessonIds;
    }

    public Long getCurrentClass() {
        return currentClass;
    }

    public void setCurrentClass(Long currentClass) {
        this.currentClass = currentClass;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
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
        return "UpdateStudentVO{" +
                "userId='" + userId + '\'' +
                ", password='" + passwd + '\'' +
                ", userCode='" + userCode + '\'' +
                ", nickName='" + nikeName + '\'' +
                ", lessonIds='" + lessonIds + '\'' +
                ", currentClass='" + currentClass + '\'' +
                '}';
    }
}
