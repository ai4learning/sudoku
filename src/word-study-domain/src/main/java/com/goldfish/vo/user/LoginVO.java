package com.goldfish.vo.user;

import com.goldfish.vo.BasicVO;

/**
 * Created by John on 2018/6/13 0013.
 */
public class LoginVO extends BasicVO{
    /**
     * 0:已登录
     * -1：未登录
     */
    protected Integer condition = 0;

    public Integer getCondition() {
        return condition;
    }

    public void setCondition(Integer condition) {
        this.condition = condition;
    }
}
