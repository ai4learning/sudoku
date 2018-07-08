package com.goldfish.vo.statistics;

import com.alibaba.fastjson.annotation.JSONField;
import com.goldfish.vo.BasicVO;

import java.util.List;

/**
 * Created by Administrator on 2018/6/20 0020.
 {
 "data": [{
 "date": "2018-05-23",
 "voccount": 0
 }],
 "success": true,
 "msg": "完成加载"
 }
 */
public class VocStudyResultVO extends BasicVO{

    @JSONField(name="data")
    private List<VocCountVO> vocCountVOList;

    public List<VocCountVO> getVocCountVOList() {
        return vocCountVOList;
    }

    public void setVocCountVOList(List<VocCountVO> vocCountVOList) {
        this.vocCountVOList = vocCountVOList;
    }
}
