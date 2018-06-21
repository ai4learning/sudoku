package com.goldfish.api;

import com.goldfish.service.ExamService;
import com.goldfish.service.WordStudyService;
import com.goldfish.vo.Statistics.MonthVocStudyResultVO;
import com.goldfish.vo.Statistics.TestResultVO;
import com.goldfish.vo.Statistics.VocStudyResultVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2018/6/20 0020.
 * 学习报告
 * /api/Ajax/AjaxGetVocStudyResult
 最近一周所学单词
 /api/Ajax/AjaxGetMonthVocStudyResult
 本月学习时间统计
 /api/Ajax/AjaxGettestResult
 获取前15条测试成绩
 /api/Ajax/AjaxGetAlltestResult
 获取所有测试成绩（最多300条）
 */
@Controller
@RequestMapping("/api/Ajax")
public class AjaxStatisticsController {

    @Resource
    private WordStudyService wordStudyService;
    @Resource
    private ExamService examService;
    /**
     * /api/Ajax/AjaxGetVocStudyResult
     最近一周所学单词

     入参
     {}
     出参
     {
     "data": [{
     "date": "2018-05-23",
     "voccount": 0
     }, {
     "date": "2018-05-24",
     "voccount": 0
     }],
     "success": true,
     "msg": "完成加载"
     }
     */
    @RequestMapping(value = "AjaxGetVocStudyResult", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    VocStudyResultVO doAjaxGetVocStudyResult(ModelMap context) {
        return null;
    }


    /**
     * /api/Ajax/AjaxGetMonthVocStudyResult
     本月学习时间统计

     入参
     {}
     出参
     {
     "data": [{
     "date": "2018-5",
     "totalReadTimes": 129,
     "totalSpellTimes": 356
     }],
     "success": true,
     "msg": "完成加载"
     }
     */
    @RequestMapping(value = "AjaxGetMonthVocStudyResult", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    MonthVocStudyResultVO doAjaxGetMonthVocStudyResult(ModelMap context) {
        return null;
    }


    /**
     * /api/Ajax/AjaxGettestResult
     获取前15条测试成绩

     入参
     {}
     出参
     {
     "data": [{
     "Id": 0,
     "resultScore": 0,
     "createtime": "2018-05-28 23:12:47",
     "testType": 5,
     "unitNbr": "0",
     "realDuration": 60,
     "bookName": "自主测试"
     }, {
     "Id": 0,
     "resultScore": 0,
     "createtime": "2018-05-28 22:23:19",
     "testType": 1,
     "unitNbr": "1",
     "realDuration": 240,
     "bookName": "高考3500考纲优化四"
     },
     ...
     ],
     "success": true,
     "msg": "完成加载"
     }
     */
    @RequestMapping(value = "AjaxGettestResult", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    TestResultVO doAjaxGettestResult(ModelMap context) {


        return null;
    }

    /**
     * /api/Ajax/AjaxGetAlltestResult
     获取所有测试成绩（最多300条）

     入参
     {}
     出参
     {
     "data": [{
     "Id": 0,
     "resultScore": 0,
     "createtime": "2018-05-28 23:12:47",
     "testType": 5,
     "unitNbr": "0",
     "realDuration": 60,
     "bookName": "自主测试"
     }, {
     "Id": 0,
     "resultScore": 0,
     "createtime": "2018-05-28 22:23:19",
     "testType": 1,
     "unitNbr": "1",
     "realDuration": 240,
     "bookName": "高考3500考纲优化四"
     },
     ...
     ],
     "success": true,
     "msg": "完成加载"
     }
     */
    @RequestMapping(value = "AjaxGetAlltestResult", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    TestResultVO doAjaxGetAlltestResult(ModelMap context) {
        return null;
    }
}
