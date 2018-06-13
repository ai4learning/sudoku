package com.goldfish.vo.unit;

import com.goldfish.vo.BasicVO;

import java.util.List;

/**
 * Created by John on 2018/6/2 0002.
 */
public class SaveUnitStudyVO extends BasicVO {

    private List<WordStudyVO> latestStudyPosition;
    private Integer cashPoint = 1;

    public List<WordStudyVO> getLatestStudyPosition() {
        return latestStudyPosition;
    }

    public void setLatestStudyPosition(List<WordStudyVO> latestStudyPosition) {
        this.latestStudyPosition = latestStudyPosition;
    }

    public Integer getCashPoint() {
        return cashPoint;
    }

    public void setCashPoint(Integer cashPoint) {
        this.cashPoint = cashPoint;
    }
}
