package com.goldfish.vo.unit;

import com.alibaba.fastjson.JSON;
import com.goldfish.vo.BasicVO;

import java.util.List;

/**
 * Created by John on 2018/6/2 0002.
 */
public class SaveUnitStudyVO extends BasicVO {

    private String latestStudyPosition;
    private Integer isFinished;

    public String getLatestStudyPosition() {
        return latestStudyPosition;
    }

    public void setLatestStudyPosition(LastStudyPositonVO latestStudyPosition) {
        this.latestStudyPosition = JSON.toJSONString(latestStudyPosition);
    }

    public Integer getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(Integer isFinished) {
        this.isFinished = isFinished;
    }
}
