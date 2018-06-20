package com.goldfish.vo.Statistics;

import com.alibaba.fastjson.annotation.JSONField;
import com.goldfish.vo.BasicVO;

import java.util.List;

/**
 * Created by Administrator on 2018/6/20 0020.
 */
public class TestResultVO extends BasicVO{

    @JSONField(name="data")
    private List<BasicTestResultVO> basicTestResultVOList;

    public List<BasicTestResultVO> getBasicTestResultVOList() {
        return basicTestResultVOList;
    }

    public void setBasicTestResultVOList(List<BasicTestResultVO> basicTestResultVOList) {
        this.basicTestResultVOList = basicTestResultVOList;
    }
}
