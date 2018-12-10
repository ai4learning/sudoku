package com.goldfish.vo.user;

import com.goldfish.vo.BaseVO;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by John on 2018/5/21 0021.
 */
public class UserVO extends BaseVO{

    protected Integer userState = 2;
    protected Long totalLoginTimes = 0L;
    protected String userId;
    protected String nickName;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    protected java.util.Date created;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    protected java.util.Date modified;
    protected Integer roleType;

    public String getUserId() { return userId; }

    public void setUserId(String userId) { this.userId = userId; }

    public String getNickName() { return nickName; }

    public void setNickName(String nickName) { this.nickName = nickName; }

    public java.util.Date getCreated() { return created; }

    public void setCreated(java.util.Date created) { this.created = created; }

    public java.util.Date getModified() {  return modified; }

    public void setModified(java.util.Date modified) { this.modified = modified; }

    public Integer getUserState() {
        return userState;
    }

    public void setUserState(Integer userState) {
        this.userState = userState;
    }

    public Long getTotalLoginTimes() {
        return totalLoginTimes;
    }

    public void setTotalLoginTimes(Long totalLoginTimes) {
        this.totalLoginTimes = totalLoginTimes;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }
}
