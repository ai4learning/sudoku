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
    @JSONField(name="user_id")
    private String userId;
    private String password;
    @JSONField(name="user_code")
    private String userCode;
    @JSONField(name="nick_name")
    private String nickName;
    @JSONField(name="lesson_ids")
    private String lessonIds;
    @JSONField(name="current_class")
    private String currentClass;

    public UpdateStudentVO() {
    }

    public UpdateStudentVO(String userId, String password, String userCode, String nickName, String lessonIds, String currentClass) {
        this.userId = userId;
        this.password = password;
        this.userCode = userCode;
        this.nickName = nickName;
        this.lessonIds = lessonIds;
        this.currentClass = currentClass;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLessonIds() {
        return lessonIds;
    }

    public void setLessonIds(String lessonIds) {
        this.lessonIds = lessonIds;
    }

    public String getCurrentClass() {
        return currentClass;
    }

    public void setCurrentClass(String currentClass) {
        this.currentClass = currentClass;
    }

    @Override
    public String toString() {
        return "UpdateStudentVO{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", userCode='" + userCode + '\'' +
                ", nickName='" + nickName + '\'' +
                ", lessonIds='" + lessonIds + '\'' +
                ", currentClass='" + currentClass + '\'' +
                '}';
    }
}
