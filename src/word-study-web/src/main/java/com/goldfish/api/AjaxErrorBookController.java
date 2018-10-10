package com.goldfish.api;

import com.alibaba.fastjson.JSON;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.LoginRecord;
import com.goldfish.service.SelfWordsService;
import com.goldfish.service.SelfWordsStudyService;
import com.goldfish.vo.BaseVO;
import com.goldfish.vo.error.ErrorBookVO;
import com.goldfish.vo.error.ErrorWordsVO;
import com.goldfish.vo.unit.SaveUnitStudyVO;
import com.goldfish.vo.unit.WordStudyDto;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by John on 2018/5/20 0020.
 */
public class AjaxErrorBookController extends AjaxReviewBookController {

    @Resource(name="selfWordsStudyService")
    private SelfWordsStudyService errorWordsStudyService;

    @Resource(name="selfWordsService")
    private SelfWordsService errorWordsService;

    /**
     * {
         "data":[
             {
                 "Id":1516,
                 "vocCode":"8a108cb7-42aa-d911-0142-ad8121f500d4",
                 "spelling":"dame",
                 "soundMark":"",
                 "meaning":"夫人（女爵）",
                 "soundMarkUs":"[deɪm]",
                 "UnitId":0,
                 "unitNbr":1,
                 "vocIndex":0,
                 "isCollected":false
             },
             {
                 "Id":1518,
                 "vocCode":"8a108cb7-42aa-d911-0142-ad8121f500d6",
                 "spelling":"game",
                 "soundMark":"",
                 "meaning":"游戏;赌博",
                 "soundMarkUs":"[ɡeɪm]",
                 "UnitId":0,
                 "unitNbr":1,
                 "vocIndex":1,
                 "isCollected":false
             }
         ],
         "success":true,
         "condition":0,
         "msg":"搞定！",
         "studytoken":"7b4db6fd-316c-4dc3-8ea8-47e0f560ebce",
         "bookInfo":{
             "Id":0,
             "moduleCode":"00000000-0000-0000-0000-000000000000",
             "bookName":"错词强化学...",
             "totalUnitNbr":0,
             "outDate":false,
             "startFrom":0,
             "studyMode":0
         },
         "totalNbr":0
     }
     */

    /**
     * 用于单词学习
     * @param moduleCode 00000000-0000-0000-0000-000000000000
     * @param unit       1
     * @param context
     * @return
     */
    @RequestMapping(value = "AjaxGetErrorUnit", method = {RequestMethod.GET, RequestMethod.POST})
    public
    @ResponseBody
    ErrorBookVO doAjaxGetErrorUnit(String moduleCode,
                                           Integer unit, ModelMap context) {

        LoginRecord loginRecord = this.getLoginRecord();
        if (loginRecord == null) {
            ErrorBookVO errorBookVO = new ErrorBookVO();
            errorBookVO.setCondition(-1);
            errorBookVO.setMsg("未登录");
            return errorBookVO;
        }
        return errorWordsService.getErrorUnit(loginRecord.getUserId(), loginRecord.getStudyToken(), moduleCode, unit);
    }

    /**
     * {
     *     "data":[
     * <p>
     *     ],
     *     "success":true,
     *     "condition":0,
     *     "studytoken":"00000000-0000-0000-0000-000000000000",
     *     "totalNbr":0
     * }
     *
     * @param context
     * @return
     */
    @RequestMapping(value = "AjaxGetErrorBook", method = {RequestMethod.GET, RequestMethod.POST})
    public
    @ResponseBody
    ErrorWordsVO doAjaxGetErrorBook(String orderType, Integer start, Integer limit, ModelMap context) {
        LoginRecord loginRecord = this.getLoginRecord();
        if (loginRecord == null) {
            ErrorWordsVO vo = new ErrorBookVO();
            vo.setMsg("未登录");
            vo.setCondition(-1);
            return vo;
        }
        // 注意处理start 这里start以上不太对，是pageIndex意思，从0开始
        start = start * limit;
        return errorWordsService.getErrorWordsByConditon(
                loginRecord.getUserId(),loginRecord.getStudyToken(),
                orderType,start,limit);
    }

    /**
     * 错题本计数
     *
     * @param orderType
     * @param context
     * @return
     */
    @RequestMapping(value = "GetErrorBookCount", method = {RequestMethod.GET, RequestMethod.POST})
    public
    @ResponseBody
    BaseVO doGetErrorBookCount(String orderType, ModelMap context) {
        LoginRecord loginRecord = this.getLoginRecord();
        if (loginRecord == null) {
            BaseVO vo = new BaseVO();
            vo.setMsg("未登录");
            vo.setCondition(-1);
            return vo;
        }
        return errorWordsService.countErrorWordsByConditon(
                loginRecord.getUserId(), loginRecord.getStudyToken(),
                orderType);
    }

    /**
     * TODO:错词本学习保存
     *
     * {"success":true,"condition":0,"msg":"完成加载","studytoken":"5918ba12-1b0b-4103-bb3b-dc0d2e036395","totalNbr":0}
     *
     * @param context
     * @return
     */

    /**
     * 保存错词本学习
     *
     * @param moduleCode         00000000-0000-0000-0000-000000000000
     * @param extra              dontdelete
     * @param unitNbr            1
     * @param vocCode            8a108cb7-42aa-d911-0142-ad81218b0093
     * @param studytoken         7013d630-4b89-4c9a-8999-e635c5ca6814
     * @param totalReadingTime   38
     * @param totalWritingTime   0
     * @param vocDataAfterReview []
     * @param context
     * @return {
     * "success":false,
     * "msg":"",
     * "isFinished":2,
     * "latestStudyPosition":"{"Id":0,"studyPositionCode":null,"studytoken":null,"moduleCode":null,"positionType":null,"remark":null,"vocCode":null,"totalReadingTime":null,"totalWritingTime":null,"userCode":null,"unitNbr":null,"wordCount":null,"isContinue":null,"seconds4SpellingLetter":null,"isCurrentPos":false,"isFinished":2,"spelling":null,"isAllFinished":false,"createtime":null,"IsTested":0,"Status":0}"
     * }
     */
    @RequestMapping(value = "AjaxErrorStudySave", method = {RequestMethod.GET, RequestMethod.POST})
    public
    @ResponseBody
    SaveUnitStudyVO doAjaxErrorStudySave(String moduleCode,
                                             String extra,
                                             Integer unitNbr,
                                             String vocCode,
                                             String studytoken,
                                             Long totalReadingTime,
                                             Long totalWritingTime,
                                             String vocDataAfterReview,
                                             ModelMap context) {
        LoginRecord loginRecord = this.getLoginRecord();
        if (loginRecord == null) {
            SaveUnitStudyVO vo = new SaveUnitStudyVO();
            vo.setMsg("未登录");
            return vo;
        }



        return errorWordsStudyService.saveErrorStudy(
                                        loginRecord.getUserId(),
                                        moduleCode,
                                        extra,
                                        unitNbr,
                                        vocCode,
                                        studytoken,
                                        totalReadingTime,
                                        totalWritingTime,
                JSON.parseArray(vocDataAfterReview, WordStudyDto.class));
    }


}
