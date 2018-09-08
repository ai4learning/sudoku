package com.goldfish.vo.error;

import com.goldfish.vo.BaseVO;
import com.goldfish.vo.word.UnitWordVO;

import java.util.List;

/**
 *      * {
 *     "data":[
 * <p>
 *     ],
 *     "success":true,
 *     "condition":0,
 *     "studytoken":"00000000-0000-0000-0000-000000000000",
 *     "totalNbr":0
 * }
 * Created by John on 2018/6/16 0016.
 */
public class ErrorWordsVO extends BaseVO {
    private String studytoken;
    private List<UnitWordVO> data;

    public String getStudytoken() {
        return studytoken;
    }

    public void setStudytoken(String studytoken) {
        this.studytoken = studytoken;
    }

    public List<UnitWordVO> getData() {
        return data;
    }

    public void setData(List<UnitWordVO> data) {
        this.data = data;
    }
}
