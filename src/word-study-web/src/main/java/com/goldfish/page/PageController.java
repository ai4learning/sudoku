package com.goldfish.page;

import com.goldfish.common.CommonResult;
import com.goldfish.web.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by John on 2018/5/20 0020.
 */
@Controller
@RequestMapping("//")
public class PageController extends BaseController {

    /**
     * 单元测试页
     * UnitTest?bookname=%E9%9B%B6%E5%9F%BA%E7%A1%80%E5%85%A5%E9%97%A8%E6%8B%BC%E8%AF%BB&moduleCode=8a108cb7-42ad-314f-0142-ad33a0a70001&unit=1
     * @param bookname
     * @param moduleCode
     * @param unit
     * @param context
     * @return
     */
    @RequestMapping(value="UnitTest",method={RequestMethod.GET,RequestMethod.POST})
    public String unitTest(String bookname,String moduleCode,Integer unit, ModelMap context) {
        CommonResult result = null;
        this.toVm(result, context);
        return "/student/UnitTest";
    }

    /**
     * 自主测试
     * Test?testArea=2&questionNbr=10&questionTypes=0,1,2,3
     * @param context
     * @return
     */
    @RequestMapping(value="Test",method={RequestMethod.GET,RequestMethod.POST})
    public String test(Integer testArea, Integer questionNbr, Integer questionType, ModelMap context) {
        CommonResult result = null;
        this.toVm(result, context);
        return "/student/Test";
    }



}
