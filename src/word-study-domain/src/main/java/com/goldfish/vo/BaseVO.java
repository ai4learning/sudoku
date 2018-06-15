package com.goldfish.vo;

import com.goldfish.vo.user.LoginVO;

/**
 * Created by John on 2018/5/21 0021.
 */
public class BaseVO extends LoginVO {


    private Integer totalNbr = 0;


    public Integer getTotalNbr() {
        return totalNbr;
    }

    public void setTotalNbr(Integer totalNbr) {
        this.totalNbr = totalNbr;
    }
}
