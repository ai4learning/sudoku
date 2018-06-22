package com.goldfish.vo.user;

import com.goldfish.vo.BaseVO;

/**
 * Created by John on 2018/5/21 0021.
 */
public class UserVO extends BaseVO{

    protected Integer userState = 2;
    protected Long totalLoginTimes = 0L;
    protected String userId;
    protected String nickName;

    public String getUserId() { return userId; }

    public void setUserId(String userId) { this.userId = userId; }

    public String getNickName() { return nickName; }

    public void setNickName(String nickName) { this.nickName = nickName; }

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
}
