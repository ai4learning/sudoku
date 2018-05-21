package com.goldfish.vo;

import com.goldfish.vo.BaseVO;

/**
 * Created by John on 2018/5/21 0021.
 */
public class UserVO extends BaseVO{

    protected Integer userState = 2;
    protected Long totalLoginTimes = 0L;

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
