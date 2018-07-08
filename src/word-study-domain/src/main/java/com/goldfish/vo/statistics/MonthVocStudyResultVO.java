package com.goldfish.vo.statistics;

import com.alibaba.fastjson.annotation.JSONField;
import com.goldfish.vo.BasicVO;

import java.util.List;

/**
 * Created by Administrator on 2018/6/20 0020.
 */

/**
 * {
 "data": [{
 "date": "2018-5",
 "totalReadTimes": 129,
 "totalSpellTimes": 356
 }],
 "success": true,
 "msg": "完成加载"
 }
 */
public class MonthVocStudyResultVO extends BasicVO {

    @JSONField(name="data")
    private List<MonthReadSpellVO> monthReadSpellVOList;

    public List<MonthReadSpellVO> getMonthReadSpellVOList() {
        return monthReadSpellVOList;
    }

    public void setMonthReadSpellVOList(List<MonthReadSpellVO> monthReadSpellVOList) {
        this.monthReadSpellVOList = monthReadSpellVOList;
    }
}
