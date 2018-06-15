package com.goldfish.vo.unit;

import com.alibaba.fastjson.JSON;
import com.goldfish.vo.BasicVO;

import java.util.List;

/**
 * Created by John on 2018/6/2 0002.
 */
public class SaveFinishUnitStudyVO extends BasicVO {

    private String latestStudyPosition;
    private Integer cashPoint = 1;

    public String getLatestStudyPosition() {
        return latestStudyPosition;
    }

    public void setLatestStudyPosition(List<WordStudyVO> latestStudyPosition) {
        this.latestStudyPosition = JSON.toJSONString(latestStudyPosition);
    }

    public Integer getCashPoint() {
        return cashPoint;
    }

    public void setCashPoint(Integer cashPoint) {
        this.cashPoint = cashPoint;
    }
}
