package com.goldfish.vo;

import com.goldfish.constant.FinishState;
import com.goldfish.constant.IsTested;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by John on 2018/6/24 0024.
 */
public final class StateConvert {

    private static final Map<Integer, Integer> FinishVO2DB;
    private static final Map<Integer, Integer> FinishDB2VO;
    private static final Map<Integer, Integer> TestVO2DB;
    private static final Map<Integer, Integer> TestDB2VO;

    static {
        FinishVO2DB = new HashMap<Integer, Integer>(8);
        FinishVO2DB.put(-1, FinishState.NOT_START.getState());
        FinishVO2DB.put(0, FinishState.NOT_COMPLETE.getState());
        FinishVO2DB.put(2, FinishState.COMPLETE.getState());

        FinishDB2VO = new HashMap<Integer, Integer>(8);
        FinishDB2VO.put(FinishState.NOT_START.getState(),-1);
        FinishDB2VO.put(FinishState.NOT_COMPLETE.getState(),0);
        FinishDB2VO.put(FinishState.COMPLETE.getState(),2);

        TestVO2DB = new HashMap<Integer, Integer>(8);
        TestVO2DB.put(0, IsTested.UNTESTED.getCode());
        TestVO2DB.put(-1, IsTested.FAIL.getCode());
        TestVO2DB.put(1, IsTested.FULL_MARKS.getCode());
        TestVO2DB.put(2, IsTested.PASS.getCode());

        TestDB2VO = new HashMap<Integer, Integer>(8);
        TestDB2VO.put(IsTested.UNTESTED.getCode(), 0);
        TestDB2VO.put(IsTested.FAIL.getCode(), -1);
        TestDB2VO.put(IsTested.FULL_MARKS.getCode(), 1);
        TestDB2VO.put(IsTested.PASS.getCode(), 2);
    }


    public static Integer convertFinishStateVO2DB(Integer studyState) {
        return FinishVO2DB.get(studyState);
    }
    public static Integer convertFinishStateDB2VO(Integer studyState) {
        return FinishDB2VO.get(studyState);
    }

    public static Integer convertTestStateVO2DB(Integer testState) {
        return TestVO2DB.get(testState);
    }

    public static Integer convertTestStateDB2VO(Integer testState) {
        return TestDB2VO.get(testState);
    }
}
