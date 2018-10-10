package com.goldfish.api;

import com.goldfish.vo.exam.ExamVO;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Administrator on 2018/6/15 0015.
 */
public class AjaxExamControllerTest {

    private AjaxExamController ajaxExamController;

    @Before
    public void before()
    {
        ajaxExamController = new AjaxExamController();
    }

    //?testArea=0&questionTypes=0,1,2&questionNbr=5
    @Test
    public void doGetExamTest01()
    {
        int testArea = 0;
        String questionTypes = "0,1,2";
        int questionNbr = 5;
        ExamVO examVO = ajaxExamController.doGetExam(testArea,questionNbr,questionTypes,null);
        System.out.println(examVO);
    }
}
