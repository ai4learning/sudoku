package com.goldfish.api;

import com.goldfish.common.CommonResult;
import com.goldfish.domain.Paper;
import com.goldfish.domain.Question;
import com.goldfish.service.PaperService;
import com.goldfish.service.QuestionService;
import com.goldfish.vo.exam.ChoicesVO;
import com.goldfish.vo.exam.DataVO;
import com.goldfish.vo.exam.QuestionVO;
import com.goldfish.vo.exam.UnitExamVO;
import com.goldfish.web.base.BaseController;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Base64;
import java.util.List;
import java.util.Map;

/**
 * Created by John on 2018/5/20 0020.
 */
public class AjaxExamController extends AjaxErrorBookController{

    @Resource
    private PaperService paperService;

    @Resource
    private QuestionService questionService;

    /**
     * 生成自主测试试卷
     * @param testArea 0
     * @param questionNbr 20
     * @param questionTypes 0,1,2,3
     * @param context
     * @return
     *
     * {
        "dataEn2Ch":[
            {
                "answerIndex":"A",
                "choices":{
                    "A":"柠檬",
                    "B":"笑话,玩笑",
                    "C":"根本不;单单除……之外",
                    "D":"v.发嘟嘟声，吼叫"
                },
                "spelling":"lemon",
                "vocCode":"8a108cb7-42aa-d911-0142-ad8121430085",
                "question":"lemon"
            },
            {
                "answerIndex":"C",
                "choices":{
                    "A":"vt.1.与...相对,面临,遭遇 2.使面对,使面临,使当面作证",
                    "B":"a.听觉的",
                    "C":"第二;秒钟",
                    "D":"（去）买东西"
                },
                "spelling":"second",
                "vocCode":"8a108cb7-42aa-d911-0142-ad8121450088",
                "question":"second"
            }
        ],
        "dataCh2En":[
            {
                "answerIndex":"A",
                "choices":{
                    "A":"fat",
                    "B":"skillfulness",
                    "C":"miscreant",
                    "D":"concrete"
                },
                "spelling":"fat",
                "vocCode":"8a108cb7-42aa-d911-0142-ad812032002b",
                "question":"肥的;油腻的"
            },
            {
                "answerIndex":"A",
                "choices":{
                    "A":"tenth",
                    "B":"love",
                    "C":"wireless",
                    "D":"cake"
                },
                "spelling":"tenth",
                "vocCode":"8a108cb7-42aa-d911-0142-ad81213d007e",
                "question":"第十;十分之一"
            }
        ],
        "dataListen2Ch":[
            {
                "answerIndex":"B",
                "choices":{
                    "A":"让路,让步",
                    "B":"账单",
                    "C":"农民",
                    "D":"愚蠢的"
                },
                "spelling":"bill",
                "vocCode":"8a108cb7-42aa-d911-0142-ad8121ae00a6",
                "question":"bill"
            },
            {
                "answerIndex":"B",
                "choices":{
                    "A":"n.小虾",
                    "B":"袜子",
                    "C":"n.超级市场",
                    "D":"麻雀"
                },
                "spelling":"sock",
                "vocCode":"8a108cb7-42aa-d911-0142-ad8121cf00b7",
                "question":"sock"
            }
        ],
        "dataListen2Write":[
            {
                "answerIndex":"A",
                "spelling":"lent",
                "vocCode":"8a108cb7-42aa-d911-0142-ad81213e0080",
                "question":"借出（过去式）;（基督教）打斋节"
            },
            {
                "answerIndex":"A",
                "spelling":"think",
                "vocCode":"8a108cb7-42aa-d911-0142-ad8121ae00a5",
                "question":"思考;认为"
            }
        ],
        "msg":"成功！",
        "success":true,
        "condition":0
    }
     */
    @RequestMapping(value="GetExam",method={RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody
    Map<String,Object> doGetExam(Integer testArea, Integer questionNbr, String questionTypes,ModelMap context) {
        CommonResult result = null;
        return result.getReturnMap();
    }

    /**
     * 获取单元测试试卷
     * @param moduleCode 8a108cb7-42ad-314f-0142-ad33a0a70001
     * @param unitNbr 11
     * @param context
     * @return
     *
     * {
        "data":{
            "dataEn2Ch":Array[15],
            "dataCh2En":Array[15],
            "dataListen2Ch":Array[10],
            "msg":"成功！",
            "success":true,
            "condition":0
        },
        "success":true,
        "condition":2,
        "studytoken":"00000000-0000-0000-0000-000000000000",
        "totalNbr":0
    }

    {
        "data":{
            "dataEn2Ch":[
                {
                    "answerIndex":"C",
                    "choices":{
                        "A":"天气，气候",
                        "B":"n.[亦作rumor] 谣言, 传闻v.谣传",
                        "C":"海龟",
                        "D":"任何;每一"
                    },
                    "spelling":"turtle",
                    "vocCode":"8a108cb7-42aa-d911-0142-ad81235301aa",
                    "question":"turtle"
                }
            ],
            "dataCh2En":[
                {
                    "answerIndex":"C",
                    "choices":{
                        "A":"car",
                        "B":"recent",
                        "C":"mouse",
                        "D":"regard"
                    },
                    "spelling":"mouse",
                    "vocCode":"8a108cb7-42aa-d911-0142-ad81237101bc",
                    "question":"老鼠"
                }
            ],
            "dataListen2Ch":[
                {
                    "answerIndex":"B",
                    "choices":{
                        "A":"在…上方prep.",
                        "B":"猪肉",
                        "C":"num./n./a.一千;[pl.]许许多多，成千上万",
                        "D":"adv. 通常地;一般地"
                    },
                    "spelling":"pork",
                    "vocCode":"8a108cb7-42aa-d911-0142-ad81235501ae",
                    "question":"pork"
                }
            ],
            "msg":"成功！",
            "success":true,
            "condition":0
        },
        "success":true,
        "condition":2,
        "studytoken":"00000000-0000-0000-0000-000000000000",
        "totalNbr":0
    }
     *
     */
    @RequestMapping(value="GetUnitExam",method={RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody
    UnitExamVO doGetUnitExam(String moduleCode, Integer unitNbr, ModelMap context) {
        Paper paperCondition = new Paper();
        paperCondition.setUnitNbr(unitNbr);
        //paper.setModuleCode(moduleCode);
        Paper paperResult = paperService.getUnique(paperCondition).getDefaultModel();
        String[] questionIdArray = paperResult.getQuestions().split(",");

        DataVO dataVO = new DataVO();
        dataVO.setMsg("");
        dataVO.setSuccess(true);
        dataVO.setCondition(0);
        for (String questionId : questionIdArray)
        {
            Question question = questionService.getQuestionById(Long.valueOf(questionId)).getDefaultModel();
            ChoicesVO choicesVO = new ChoicesVO(question.getChoices());
            QuestionVO questionVO = new QuestionVO(question.getAnswerIndex(),question.getSpelling()
                    ,question.getVocCode(),question.getQuestion(),choicesVO);
        }


        UnitExamVO unitExamVO = new UnitExamVO();
        unitExamVO.setSuccess(true);
        unitExamVO.setCondition(0);
        unitExamVO.setTotalNbr(0);
        unitExamVO.setData(dataVO);
        return unitExamVO;
    }

    /**
     * 保存测试结果
     * @param moduleCode 8a108cb7-42ad-314f-0142-ad33a0a70001
     * @param resultScore 100
     * @param testType 1（单元测试）   8（每日强化训练）
     * @param unitNbr 1 （当type为8时，该字段为0）
     * @param realDuration 117
     * @param standardDuration 240
     * @param errorNbr 0
     * @param CashPoint 10
     * @param cashPointType 4  6 （当为强化训练时，cashType为6）
     * @param context
     * @return
     *{"success":true,"msg":"记忆已经保存"}
     *
     */
    @RequestMapping(value="AjaxSaveMutiTest",method={RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody
    Map<String,Object> doAjaxSaveMutiTest(String moduleCode, Integer resultScore,
                                          Integer testType, Integer unitNbr,
                                          Long realDuration, Long standardDuration,
                                          Integer errorNbr,
                                          Long CashPoint, Integer cashPointType, ModelMap context) {
        CommonResult result = null;
        return result.getReturnMap();
    }





}
