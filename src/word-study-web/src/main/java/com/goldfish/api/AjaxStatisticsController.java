package com.goldfish.api;

import com.goldfish.common.DateFormatUtils;
import com.goldfish.common.PageQuery;
import com.goldfish.constant.CommonConstant;
import com.goldfish.constant.State;
import com.goldfish.domain.ClassGrade;
import com.goldfish.domain.Course;
import com.goldfish.domain.Exam;
import com.goldfish.domain.User;
import com.goldfish.service.*;
import com.goldfish.vo.statistic.*;
import com.goldfish.web.base.BaseController;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Administrator
 * @date 2018/6/20 0020
 * 学习报告
 * /api/Ajax/AjaxGetVocStudyResult
 * 最近一周所学单词
 * /api/Ajax/AjaxGetMonthVocStudyResult
 * 本月学习时间统计
 * /api/Ajax/AjaxGettestResult
 * 获取前15条测试成绩
 * /api/Ajax/AjaxGetAlltestResult
 * 获取所有测试成绩（最多300条）
 */
@Controller
@RequestMapping("/api/Ajax")
public class AjaxStatisticsController extends BaseController {

    @Resource
    private WordStudyService wordStudyService;
    @Resource
    private ExamService examService;
    @Resource
    private UnitStudyService unitStudyService;
    @Resource
    private CourseService courseService;
    @Resource
    private UserService userService;
    @Resource
    private ClassGradeService classGradeService;

    /**
     * /api/Ajax/AjaxGetVocStudyResult
     * 最近一周所学单词
     * <p>
     * 入参
     * {}
     * 出参
     * {
     * "data": [{
     * "date": "2018-05-23",
     * "voccount": 0
     * }, {
     * "date": "2018-05-24",
     * "voccount": 0
     * }],
     * "success": true,
     * "msg": "完成加载"
     * }
     */
    @RequestMapping(value = "AjaxGetVocStudyResult", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    VocStudyResultVO doAjaxGetVocStudyResult(ModelMap context) {
        VocStudyResultVO vo = new VocStudyResultVO();
        User user = this.getUserInfo();
        if (user == null) {
            vo.setMsg(CommonConstant.LOAD_FAIL);
            vo.setSuccess(false);
            return vo;
        }

        List<VocCountVO> vocCountVOList = new ArrayList<>(Calendar.DAY_OF_WEEK);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        for (int i=0;i<Calendar.DAY_OF_WEEK;i++)
        {
            String dateString = sdf.format(date);
            int count = countStudentStudiedWordsByDay(user.getId().intValue(),dateString);
            VocCountVO vocCountVO = new VocCountVO();
            vocCountVO.setDate(dateString);
            vocCountVO.setVoccount(count);
            vocCountVOList.add(vocCountVO);

            date = DateFormatUtils.getYesterday(date);
        }
        vo.setVocCountVOList(vocCountVOList);
        vo.setMsg(CommonConstant.LOAD_SUCCESS);
        vo.setSuccess(true);
        return vo;
    }


    /**
     * /api/Ajax/AjaxGetMonthVocStudyResult
     * 本月学习时间统计
     * <p>
     * 入参
     * {}
     * 出参
     * {
     * "data": [{
     * "date": "2018-5",
     * "totalReadTimes": 129,
     * "totalSpellTimes": 356
     * }],
     * "success": true,
     * "msg": "完成加载"
     * }
     */
    @RequestMapping(value = "AjaxGetMonthVocStudyResult", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    MonthVocStudyResultVO doAjaxGetMonthVocStudyResult(ModelMap context) {
        MonthVocStudyResultVO vo = new MonthVocStudyResultVO();
        User user = this.getUserInfo();
        if (user == null) {
            vo.setMsg(CommonConstant.LOAD_FAIL);
            vo.setSuccess(false);
            return vo;
        }
        MonthReadSpellVO monthReadSpellVO = new MonthReadSpellVO();
        List<MonthReadSpellVO> monthReadSpellVOList = new ArrayList<>(1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date date = new Date();
        monthReadSpellVO.setDate(sdf.format(date));

        PageQuery pageQuery = new PageQuery();
        pageQuery.addQueryParam("studentId",user.getId().intValue());
        monthReadSpellVO.setTotalReadTimes(unitStudyService.sumReading(pageQuery));
        monthReadSpellVO.setTotalSpellTimes(unitStudyService.sumWriting(pageQuery));
        monthReadSpellVOList.add(monthReadSpellVO);
        vo.setMonthReadSpellVOList(monthReadSpellVOList);
        vo.setMsg(CommonConstant.LOAD_SUCCESS);
        vo.setSuccess(true);
        return vo;
    }


    /**
     * /api/Ajax/AjaxGettestResult
     * 获取前15条测试成绩
     * <p>
     * 入参
     * {}
     * 出参
     * {
     * "data": [{
     * "Id": 0,
     * "resultScore": 0,
     * "createtime": "2018-05-28 23:12:47",
     * "testType": 5,
     * "unitNbr": "0",
     * "realDuration": 60,
     * "bookName": "自主测试"
     * }, {
     * "Id": 0,
     * "resultScore": 0,
     * "createtime": "2018-05-28 22:23:19",
     * "testType": 1,
     * "unitNbr": "1",
     * "realDuration": 240,
     * "bookName": "高考3500考纲优化四"
     * },
     * ...
     * ],
     * "success": true,
     * "msg": "完成加载"
     * }
     */
    @RequestMapping(value = "AjaxGettestResult", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    TestResultVO doAjaxGetTestResult(ModelMap context) {
        return getExamResult(15);
    }

    /**
     * /api/Ajax/AjaxGetAlltestResult
     * 获取所有测试成绩（最多300条）
     * <p>
     * 入参
     * {}
     * 出参
     * {
     * "data": [{
     * "Id": 0,
     * "resultScore": 0,
     * "createtime": "2018-05-28 23:12:47",
     * "testType": 5,
     * "unitNbr": "0",
     * "realDuration": 60,
     * "bookName": "自主测试"
     * }, {
     * "Id": 0,
     * "resultScore": 0,
     * "createtime": "2018-05-28 22:23:19",
     * "testType": 1,
     * "unitNbr": "1",
     * "realDuration": 240,
     * "bookName": "高考3500考纲优化四"
     * },
     * ...
     * ],
     * "success": true,
     * "msg": "完成加载"
     * }
     */
    @RequestMapping(value = "AjaxGetAlltestResult", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    TestResultVO doAjaxGetAllTestResult(ModelMap context) {
        return getExamResult(300);
    }

    private TestResultVO getExamResult(int limit) {
        TestResultVO testResultVO = new TestResultVO();
        User user = this.getUserInfo();
        if (user == null) {
            testResultVO.setMsg(CommonConstant.LOAD_FAIL);
            testResultVO.setSuccess(false);
            return testResultVO;
        }
        PageQuery pageQuery = new PageQuery();
        pageQuery.addQueryParam("limit", limit);
        pageQuery.addQueryParam("userId", user.getId().intValue());

        List<Exam> examList = examService.getListByUserRecent(pageQuery).getDefaultModel();
        if (examList == null) {
            testResultVO.setMsg(CommonConstant.LOAD_SUCCESS);
            testResultVO.setSuccess(true);
            return testResultVO;
        }
        List<BasicTestResultVO> basicTestResultVOList = new ArrayList<>();
        for (Exam exam : examList) {
            BasicTestResultVO basicTestResultVO = new BasicTestResultVO();
            basicTestResultVO.setId(exam.getId().intValue());
            basicTestResultVO.setCreatetime(exam.getCreated().toString());
            basicTestResultVO.setRealDuration(exam.getRealDuration().intValue());
            basicTestResultVO.setResultScore(exam.getResultScore());
            basicTestResultVO.setTestType(exam.getTestType());
            basicTestResultVO.setUnitNbr(exam.getUnitNbr()==null? null: String.valueOf(exam.getUnitNbr()));
            if (StringUtils.isEmpty(exam.getModuleCode())) {
                basicTestResultVO.setBookName(null);
            } else {
                Course courseQuery = new Course();
                courseQuery.setModuleCode(exam.getModuleCode());
                Course course = courseService.getUnique(courseQuery).getDefaultModel();
                if (course != null) {
                    basicTestResultVO.setBookName(course.getBookName());
                }
            }
            basicTestResultVOList.add(basicTestResultVO);
        }
        testResultVO.setBasicTestResultVOList(basicTestResultVOList);
        testResultVO.setSuccess(true);
        testResultVO.setMsg(CommonConstant.LOAD_SUCCESS);
        return testResultVO;
    }


    @RequestMapping(value = "AjaxGetRealTimeSituation", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    SituationVO doAjaxGetRealTimeSituation(ModelMap context) {
        return generateSituationVO(true);
    }

    @RequestMapping(value = "AjaxGetCumulativeSituation", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    SituationVO doAjaxGetCumulativeSituation(ModelMap context) {
        return generateSituationVO(false);
    }

    private int countStudentStudiedWordsByDay(int studentId, String dateString)
    {
        PageQuery pageQuery = new PageQuery();
        pageQuery.addQueryParam("studentId",studentId);
        pageQuery.addQueryParam("state", State.VALID.getState());
        pageQuery.addQueryParam("date", dateString);
        return wordStudyService.countDay(pageQuery);
    }

    private SituationVO generateSituationVO(boolean realTime) {
        SituationVO situationVO = new SituationVO();
        User user = this.getUserInfo();
        if (user == null) {
            situationVO.setMsg(CommonConstant.LOAD_FAIL);
            situationVO.setSuccess(false);
            return situationVO;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        situationVO.setClassNO(user.getCurrentClass());
        situationVO.setDate(sdf.format(date));
        List<PersonalAchievementVO> paVO = new ArrayList<>();
        //获取同班同学信息
        User userQuery = new User();
        userQuery.setCurrentClass(user.getCurrentClass());
        List<User> classMate = userService.getListByExample(userQuery).getDefaultModel();
        ClassGrade classGrade = classGradeService.getClassGradeById(user.getCurrentClass()).getDefaultModel();
        int dayGap;
        if (classGrade != null) {
            int differenceDayNow = DateFormatUtils.getDateDifference(new Date(), classGrade.getStart());
            int differenceDay = DateFormatUtils.getDateDifference(classGrade.getEnd(), classGrade.getStart());
            dayGap = differenceDayNow < differenceDay ? differenceDayNow : differenceDay;
        }else {
            dayGap = 0;
        }
        situationVO.setDayNO(dayGap);
        if (classMate == null)
        {
            situationVO.setMsg(CommonConstant.LOAD_FAIL);
            situationVO.setSuccess(false);
            return situationVO;
        }
        for (User student : classMate)
        {
            if (realTime) {
                paVO.add(new PersonalAchievementVO(student.getNikeName()
                        , countStudentStudiedWordsByDay(student.getId().intValue(), sdf.format(date))));
            }else {
                Date date1= classGrade == null ? new Date() : classGrade.getStart();
                int count = 0;
                for (int i=0;i<dayGap;i++)
                {
                    count += countStudentStudiedWordsByDay(student.getId().intValue(), sdf.format(date1));
                    date1 = DateFormatUtils.getNextday(date1);
                }
                paVO.add(new PersonalAchievementVO(student.getNikeName(), count));
            }
        }
        situationVO.setAchievements(paVO);
        situationVO.setRealTime(realTime);

        situationVO.setMsg(CommonConstant.LOAD_SUCCESS);
        situationVO.setSuccess(true);
        return situationVO;
    }
}