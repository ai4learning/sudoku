package com.goldfish.api;

import com.alibaba.fastjson.JSON;
import com.goldfish.common.CommonResult;
import com.goldfish.common.PageQuery;
import com.goldfish.common.log.LogTypeEnum;
import com.goldfish.constant.*;
import com.goldfish.domain.*;
import com.goldfish.service.*;
import com.goldfish.vo.*;
import com.goldfish.vo.course.*;
import com.goldfish.vo.unit.*;
import com.goldfish.vo.user.RichUserBookVO;
import com.goldfish.vo.user.UserBookVO;
import com.goldfish.web.base.BaseController;
import com.goldfish.web.interceptor.servlet.context.LoginContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created by John on 2018/5/20 0020.
 */
public class AjaxCourseController extends BaseController {
    @Resource
    private CourseStudyService courseStudyService;
    @Resource
    private UnitStudyService unitStudyService;
    @Resource
    private CourseService courseService;
    @Resource
    private UnitService unitService;
    @Resource
    private UnitWordsService unitWordsService;



    /**
     * 获取学生学习课程
     * <p>
     * {
     *     "books":[
     *         {
     *             "Id":5,
     *             "moduleCode":"8a108cb7-42ad-314f-0142-ad33a0a70001",
     *             "bookName":"零基础入门拼读",
     *             "coverImageUrl":"",
     *             "totalUnitNbr":17,
     *             "currentPosition":{
     *                 "Id":514981,
     *                 "studyPositionCode":"68c7bd11-5ebf-4c67-a8c8-e8d3c3b24840",
     *                 "positionType":0,
     *                 "vocCode":"8a108cb7-42aa-d911-0142-ad8123f50222",
     *                 "unitNbr":14,
     *                 "isCurrentPos":true,
     *                 "isFinished":0,
     *                 "spelling":"three",
     *                 "isAllFinished":true,
     *                 "IsTested":0,
     *                 "Status":0
     *             },
     *             "currentStudyBook":true,
     *             "outDate":false,
     *             "unitType":"Unit",
     *             "startFrom":1,
     *             "studyMode":1
     *         }
     *     ],
     *     "condition":0,
     *     "totalNbr":0,
     *     "msg":"完成加载",
     *     "userState":2,
     *     "totalLoginTimes":0,
     *     "success":true
     * }
     *
     * @param context
     * @return
     */
    @RequestMapping(value = "AjaxGetUserBook", method = {RequestMethod.GET, RequestMethod.POST})
    public
    @ResponseBody
    UserBookVO doAjaxGetUserBook(ModelMap context) {
        UserBookVO userBookVO = new UserBookVO();
        // 1.装填用户信息
        User user = this.fillUserInfo(userBookVO);
        if (user == null) {
            return userBookVO;
        }

        // 2.根据用户ID查询用户课程
        PageQuery pageQuery = new PageQuery(0, 100000);
        pageQuery.addQueryParam("studentId", user.getId());
        pageQuery.addQueryParam("status", State.VALID.getState());
        CommonResult<List<CourseStudy>> studyCourseResult = courseStudyService.getCourseStudyByPage(pageQuery);
        if (studyCourseResult == null || !studyCourseResult.isSuccess()) {
            LogTypeEnum.DEFAULT.error("未获取到学生关联课程");
            return userBookVO;
        }
        List<CourseStudy> studentCourses = studyCourseResult.getDefaultModel();
        if (studentCourses == null || studentCourses.isEmpty()) {
            LogTypeEnum.DEFAULT.info("学生关联课程为空");
            userBookVO.setMsg("学生未关联课程");
            userBookVO.setSuccess(true);
            return userBookVO;
        }
        /*** 设置Book信息 ***/

        // 3.获取课程和课程学习信息
        List<CourseStudyVO> books = new ArrayList<CourseStudyVO>(studentCourses.size());
        for (CourseStudy studentCourse : studentCourses) {
            PositonedCourseStudyVO bookVO = new PositonedCourseStudyVO();
            /*设置课程信息*/
            Course courseQuery = new Course();
            courseQuery.setBookNumber(studentCourse.getLessonId());
            courseQuery.setBookState(State.VALID.getState());
            Course course = this.getCourseInfo(courseQuery, userBookVO);
            if (course == null) {
                return userBookVO;
            }
            // 设置课程信息
            bookVO.setId(course.getBookNumber());
            bookVO.setModuleCode(course.getModuleCode());
            bookVO.setBookName(course.getBookName());
            bookVO.setCoverImageUrl(course.getCoverImageUrl());
            bookVO.setTotalUnitNbr(course.getTotalUnitNbr());
            bookVO.setOutDate(course.isOutDate());
            bookVO.setUnitType(course.getUnitType());
            bookVO.setFinishedUnitNbr(getFinishedUnit(user.getId().intValue(),studentCourse.getLessonId()));

            // 设置课程学习信息
            bookVO.setCurrentStudyBook(studentCourse.isCurrentStudyBook());
            bookVO.setStartFrom(studentCourse.getStartFrom());
            bookVO.setStudyMode(studentCourse.getStudyMode());
            /*设置当前学习位置信息*/
            CurrentStudyPositionVO currentPositionVO =
                    this.getCouseStudyPositionVo(PositionType.COURSE_STUDY_POSITION, studentCourse, null, userBookVO);
            if (currentPositionVO == null) {
                return userBookVO;
            }
            bookVO.setCurrentPosition(currentPositionVO);
            books.add(bookVO);
        }
        userBookVO.setBooks(books);
        userBookVO.setSuccess(true);
        return userBookVO;
    }

    private CurrentStudyPositionVO getCouseStudyPositionVo(PositionType positionType,
                                                           CourseStudy courseStudy,
                                                           UnitStudy unitStudy,
                                                           BaseVO baseVO) {

        CurrentStudyPositionVO currentPositionVO = new CurrentStudyPositionVO();
        StudyPosition studyPosition = null;
        if (PositionType.COURSE_STUDY_POSITION == positionType) {
            currentPositionVO.setId(courseStudy.getId());
            studyPosition = courseStudy;
        } else {
            currentPositionVO.setId(unitStudy.getId());
            studyPosition = unitStudy;
        }
        currentPositionVO.setStudyPositionCode(studyPosition.getStudyPositionCode());
        currentPositionVO.setPositionType(studyPosition.getPositionType());
        currentPositionVO.setVocCode(studyPosition.getVocCode());

        // 查询课程单词获取spell
        UnitWords unitWordsQuery = new UnitWords();
        unitWordsQuery.setVocCode(studyPosition.getVocCode());
        unitWordsQuery.setState(State.VALID.getState());
        CommonResult<UnitWords> unitWordsResult = unitWordsService.getUnique(unitWordsQuery);
        if (unitWordsResult == null || !unitWordsResult.isSuccess()) {
            LogTypeEnum.DEFAULT.error("查询单元内单词异常，vocCode={}", studyPosition.getVocCode());
            baseVO.setMsg("查询单元内单词异常");
            return null;
        }
        UnitWords unitWords = unitWordsResult.getDefaultModel();
        if (unitWords == null) {
            LogTypeEnum.DEFAULT.info("未找到单元内单词，vocCode={}", studyPosition.getVocCode());
            baseVO.setSuccess(false);
            baseVO.setMsg("未找到单元内单词");
            return null;
        }
        currentPositionVO.setSpelling(unitWords.getSpelling());
        currentPositionVO.setUnitNbr(studyPosition.getUnitNbr());
        currentPositionVO.setIsCurrentPos(State.getMap().get(studyPosition.getIsCurrentPos()).getResult());
        currentPositionVO.setIsFinished(studyPosition.getIsFinished());
        currentPositionVO.setIsAllFinished(State.getMap().get(studyPosition.getIsAllFinished()).getResult());
        currentPositionVO.setIsTested(studyPosition.getIsTested());
        currentPositionVO.setStatus(studyPosition.getStatus() - 1);// 原因：VO:0表示有效，DB:1表示有效
        return currentPositionVO;
    }


    /**
     * 载入用户课程
     * <p>
     * {
     * "books":[
     * {
     * "Id":5,
     * "moduleCode":"8a108cb7-42ad-314f-0142-ad33a0a70001",
     * "bookName":"零基础入门拼读",
     * "coverImageUrl":"",
     * "bookState":1,
     * "bookType":1,
     * "bookPrice":0,
     * "currentStudyBook":false,
     * "difficultLevel":1,
     * "introduce":"0基础4个小时训练掌握拼读",
     * "totalUnitNbr":17,
     * "CourseUnits":[
     * {
     * "Id":38,
     * "moduleCode":"8a108cb7-42ad-314f-0142-ad33a0a70001",
     * "Unit":"Unit1",
     * "IsFinished":2,
     * "IsTested":1,
     * "unitNbr":1
     * },
     * {
     * "Id":39,
     * "moduleCode":"8a108cb7-42ad-314f-0142-ad33a0a70001",
     * "Unit":"Unit2",
     * "IsFinished":2,
     * "IsTested":1,
     * "unitNbr":2
     * }
     * ],
     * "outDate":false,
     * "unitType":"Unit",
     * "startFrom":1,
     * "studyMode":1,
     * "CompleteWordCount":0
     * }
     * ],
     * "condition":0,
     * "totalNbr":0,
     * "msg":"完成加载",
     * "userState":2,
     * "totalLoginTimes":0,
     * "success":true
     * }
     *
     * @param moduleCode 课程编号
     * @param context
     * @return
     */
    @RequestMapping(value = "AjaxLoadCourse", method = {RequestMethod.GET, RequestMethod.POST})
    public
    @ResponseBody
    RichUserBookVO doAjaxLoadCourse(String moduleCode, ModelMap context) {
        RichUserBookVO userBookVO = new RichUserBookVO();
        // 1.装填用户信息
        User user = this.fillUserInfo(userBookVO);
        if (user == null) {
            return userBookVO;
        }
        // 2.根据moduleCode查询课程
        if (StringUtils.isEmpty(moduleCode)) {
            LogTypeEnum.DEFAULT.info("moduleCode为空");
            userBookVO.setMsg("moduleCode为空");
            return userBookVO;
        }
        Course courseQuery = new Course();
        courseQuery.setModuleCode(moduleCode);
        courseQuery.setBookState(State.VALID.getState());
        Course course = this.getCourseInfo(courseQuery, userBookVO);
        if (course == null) {
            return userBookVO;
        }
        // 3.根据用户ID查询用户课程信息

        CourseStudy studentCourse =
                this.getCourseStudy(Integer.valueOf(String.valueOf(user.getId())), course.getBookNumber(), userBookVO);
        if (studentCourse == null) {
            return userBookVO;
        }

        /*** 设置Book信息 ***/

        // 3.获取课程和课程学习信息
        List<RichCourseStudyVO> books = new ArrayList<RichCourseStudyVO>(1);
        RichCourseStudyVO bookVO = new RichCourseStudyVO();
        // 设置课程信息
        bookVO.setId(course.getBookNumber());
        bookVO.setModuleCode(course.getModuleCode());
        bookVO.setBookName(course.getBookName());
        bookVO.setCoverImageUrl(course.getCoverImageUrl());
        bookVO.setTotalUnitNbr(course.getTotalUnitNbr());
        bookVO.setOutDate(course.isOutDate());
        bookVO.setUnitType(course.getUnitType());
        // 设置课程难易程度
        bookVO.setDifficultLevel(DifficultLevel.EASY.getLevel());

        // 设置课程学习信息
        bookVO.setCurrentStudyBook(studentCourse.isCurrentStudyBook());
        bookVO.setStartFrom(studentCourse.getStartFrom());
        bookVO.setStudyMode(studentCourse.getStudyMode());
        // 设置课程完成单词数
        bookVO.setCompleteWordCount(studentCourse.getCompleteWordCount());

        // 设置每个单元课程学习情况
        UnitStudy unitStudyQuery = new UnitStudy();
        unitStudyQuery.setStudentId(user.getId().intValue());
        unitStudyQuery.setLessonId(course.getBookNumber());
        unitStudyQuery.setState(State.VALID.getState());

        CommonResult<List<UnitStudy>> unitStudyResult = unitStudyService.getListByExample(unitStudyQuery);
        if (unitStudyResult == null || !unitStudyResult.isSuccess()) {
            LogTypeEnum.DEFAULT.error("查询学生单元学习情况异常，studentId={}, lessonId={}",
                    user.getId(), course.getBookNumber());
            return userBookVO;
        }
        List<UnitStudy> unitsStudy = unitStudyResult.getDefaultModel();
        if (unitsStudy == null || unitsStudy.isEmpty()) {
            LogTypeEnum.DEFAULT.error("未找到学生单词学习记录，studentId={}, lessonId={}",
                    user.getId(), course.getBookNumber());
            return userBookVO;
        }

        List<CourseUnitStudyVO> courseUnitsStudy = new ArrayList<CourseUnitStudyVO>(unitsStudy.size());
        bookVO.setCourseUnits(courseUnitsStudy);

        for (UnitStudy unitStudy : unitsStudy) {
            CourseUnitStudyVO courseUnitStudyVO = new CourseUnitStudyVO();
            // fill Course info
            courseUnitStudyVO.setCourseModuleCode(moduleCode);// 课程moduleCode
            // fill Unit Info
            Unit unitQuery = new Unit();
            unitQuery.setLessonId(Long.valueOf(String.valueOf(course.getBookNumber())));
            unitQuery.setUnitNbr(unitStudy.getUnitNbr());
            unitQuery.setState(State.VALID.getState());
            Unit unit = unitService.getUnique(unitQuery).getDefaultModel();
            if (unit == null) {
                continue;
            }
            courseUnitStudyVO.setId(unit.getId());
            courseUnitStudyVO.setUnit(unit.getUnit());
            courseUnitStudyVO.setUnitNbr(unit.getUnitNbr());
            // fill unit study info
            courseUnitStudyVO.setIsFinished(StateConvert.convertFinishStateDB2VO(unitStudy.getIsFinished()));
            courseUnitStudyVO.setIsTested(StateConvert.convertTestStateDB2VO(unitStudy.getIsTested()));

            courseUnitsStudy.add(courseUnitStudyVO);
        }
        books.add(bookVO);
        userBookVO.setBooks(books);
        userBookVO.setSuccess(true);
        return userBookVO;
    }

    /**
     * 获取课程学习
     *
     * @param studentId
     * @param lessonId
     * @param baseVO
     * @return
     */
    private CourseStudy getCourseStudy(Integer studentId, Integer lessonId, BaseVO baseVO) {

        CourseStudy courseStudyQuery = new CourseStudy();
        courseStudyQuery.setLessonId(lessonId);
        courseStudyQuery.setStudentId(studentId);
        courseStudyQuery.setStatus(State.VALID.getState());
        courseStudyQuery.setCurrentStudyBook(null);
        CommonResult<CourseStudy> studyCourseResult = courseStudyService.getUnique(courseStudyQuery);
        if (studyCourseResult == null || !studyCourseResult.isSuccess()) {
            LogTypeEnum.DEFAULT.error("获取到学生关联课程异常");
            baseVO.setMsg("获取到学生关联课程异常");
            return null;
        }
        CourseStudy studentCourse = studyCourseResult.getDefaultModel();
        if (studentCourse == null) {
            LogTypeEnum.DEFAULT.info("学生关联课程为空");
            baseVO.setMsg("学生未关联课程");
            baseVO.setSuccess(true);
            return null;
        }
        return studentCourse;
    }

    private Course getCourseInfo(Course courseQuery, BaseVO baseVO) {

        CommonResult<Course> courseResult = courseService.getUnique(courseQuery);
        if (courseResult == null || !courseResult.isSuccess()) {
            LogTypeEnum.DEFAULT.error("获取到课程信息失败，bookInfo={}", courseQuery);
            return null;
        }
        Course course = courseResult.getDefaultModel();
        if (course == null) {
            LogTypeEnum.DEFAULT.error("找不到这门课，bookInfo={}", courseQuery);
            baseVO.setSuccess(true);
            baseVO.setMsg("找不到这门课");
            return null;
        }
        return course;
    }



    /**
     * 获取学生单元学习数据
     * <p>
     * {
     *     "data":[
     *         {
     *             "Id":1460,
     *             "vocCode":"8a108cb7-42aa-d911-0142-ad812188008d",
     *             "spelling":"lick",
     *             "meaning":"舔;击败",
     *             "soundMarkUs":"[lɪk]",
     *             "soundMarkUk":"",
     *             "UnitId":0,
     *             "unitNbr":3,
     *             "lessonNbr":5,
     *             "fstClassId":8,
     *             "vocIndex":0,
     *             "isCollected":false
     *         },
     *         {
     *             "Id":1461,
     *             "vocCode":"8a108cb7-42aa-d911-0142-ad812188008e",
     *             "spelling":"pick",
     *             "meaning":"挑选;拾起",
     *             "soundMarkUs":"[pɪk]",
     *             "soundMarkUk":"",
     *             "UnitId":0,
     *             "unitNbr":3,
     *             "lessonNbr":5,
     *             "fstClassId":8,
     *             "vocIndex":1,
     *             "isCollected":false
     *         }
     *     ],
     *     "success":true,
     *     "condition":0,
     *     "msg":"搞定！",
     *     "studytoken":"c06616b5-4ca2-432a-ab94-50261c50baca",
     *     "bookInfo":{
     *         "Id":5,
     *         "moduleCode":"8a108cb7-42ad-314f-0142-ad33a0a70001",
     *         "bookName":"零基础入门拼读",
     *         "coverImageUrl":"",
     *         "totalUnitNbr":17,
     *         "outDate":false,
     *         "startFrom":0,
     *         "studyMode":0
     *     },
     *     "studyPos":{
     *         "Id":513527,
     *         "studyPositionCode":"e2b18f0b-bdd2-4413-9dc1-d305d3efaf7e",
     *         "positionType":0,
     *         "vocCode":"8a108cb7-42aa-d911-0142-ad81218b0093",
     *         "unitNbr":3,
     *         "isCurrentPos":true,
     *         "isFinished":0,
     *         "spelling":"it",
     *         "isAllFinished":false,
     *         "IsTested":0,
     *         "Status":0
     *     },
     *     "totalNbr":0
     * }
     *
     * @param unit       unitNbr
     * @param moduleCode 课程moduleCode
     * @param context
     * @return
     */
    @RequestMapping(value = "AjaxGetUnit", method = {RequestMethod.GET, RequestMethod.POST})
    public
    @ResponseBody
    RichUnitStudyVO doAjaxGetUnit(Integer unit, String moduleCode, ModelMap context) {
        RichUnitStudyVO richUnitStudyVO = new RichUnitStudyVO();
        try {
            // 1.获取登录信息
            String trainingCode = LoginContext.getLoginContext().getTrainingCode();
            String trainingId = LoginContext.getLoginContext().getTrainingId();

            LoginRecord loginRecord = loginRecordService.getLoginRecordByTraining(trainingId, trainingCode);
            richUnitStudyVO.setStudyToken(loginRecord.getStudyToken());

            return unitStudyService.getUnit(loginRecord.getUserId(), moduleCode, unit, richUnitStudyVO);
        } catch (Exception e) {
            LogTypeEnum.DEFAULT.error(e, "获取单元学习信息失败");
        }
        return richUnitStudyVO;
    }


    /**
     * 保存课程单元学习
     * @param context
     * @return
     */
    @RequestMapping(value = "AjaxInterruptSaveUnit", method = {RequestMethod.GET, RequestMethod.POST})
    public
    @ResponseBody
    SaveUnitStudyVO doAjaxInterruptSaveUnit(String moduleCode,
                                            String extra,
                                            Integer unitNbr,
                                            String studyToken,
                                            Integer isContinue,
                                            Long seconds4SpellingLetter,
                                            Long totalReadingTime,
                                            Long totalWritingTime,
                                            String vocDataAfterReview,
                                            Integer totalWordsNbr,
                                            ModelMap context) {
        SaveUnitStudyVO saveUnitStudyVO = new SaveUnitStudyVO();

        String trainingId = LoginContext.getLoginContext().getTrainingId();
        String trainingCode = LoginContext.getLoginContext().getTrainingCode();
        try {
            LoginRecord loginRecord = loginRecordService.getLoginRecordByTraining(trainingId, trainingCode);
            if (loginRecord == null) {
                return saveUnitStudyVO;
            }
            saveUnitStudyVO = unitStudyService.doAjaxInterruptSaveUnit(
                    loginRecord.getUserId(),
                    saveUnitStudyVO,
                    moduleCode,
                    extra,
                    unitNbr,
                    studyToken,
                    isContinue,
                    seconds4SpellingLetter,
                    totalReadingTime,
                    totalWritingTime,
                    JSON.parseArray(vocDataAfterReview, WordStudyDto.class),
                    totalWordsNbr);
        } catch (Exception e) {
            LogTypeEnum.DEFAULT.error(e, "保存单元学习异常");
            saveUnitStudyVO.setMsg("保存单元学习异常");
        }
        return saveUnitStudyVO;
    }




    @RequestMapping(value = "AjaxFinishSaveUnit", method = {RequestMethod.GET, RequestMethod.POST})
    public
    @ResponseBody
    SaveFinishUnitStudyVO doAjaxFinishSaveUnit(String moduleCode,
                                            String extra,
                                            Integer unitNbr,
                                            String studyToken,
                                            Integer isContinue,
                                            Long seconds4SpellingLetter,
                                            Long totalReadingTime,
                                            Long totalWritingTime,
                                            String vocDataAfterReview,
                                            Integer totalWordsNbr,
                                            ModelMap context) {
        SaveFinishUnitStudyVO saveFinishUnitStudyVO = new SaveFinishUnitStudyVO();
        String trainingId = LoginContext.getLoginContext().getTrainingId();
        String trainingCode = LoginContext.getLoginContext().getTrainingCode();
        try {
            // 获取用户登录信息
            LoginRecord loginRecord = loginRecordService.getLoginRecordByTraining(trainingId, trainingCode);
            if (loginRecord == null) {
                return saveFinishUnitStudyVO;
            }
            saveFinishUnitStudyVO = unitStudyService.doAjaxFinishSaveUnit(
                    loginRecord.getUserId(),
                    saveFinishUnitStudyVO,
                    moduleCode,
                    extra,
                    unitNbr,
                    studyToken,
                    isContinue,
                    seconds4SpellingLetter,
                    totalReadingTime,
                    totalWritingTime,
                    JSON.parseArray(vocDataAfterReview, WordStudyDto.class),
                    totalWordsNbr);

        } catch (Exception e) {
            LogTypeEnum.DEFAULT.error(e, "保存完成单元学习异常");
            saveFinishUnitStudyVO.setMsg("保存完成单元学习异常");
        }
        return saveFinishUnitStudyVO;
    }

    private int getFinishedUnit(int studentId,int lessonId)
    {
        PageQuery pageQuery = new PageQuery();
        pageQuery.addQueryParam("studentId",studentId);
        pageQuery.addQueryParam("lessonId",lessonId);
        pageQuery.addQueryParam("isAllFinished",FinishState.COMPLETE.getState());
        pageQuery.addQueryParam("state",State.VALID.getState());
        return unitStudyService.count(pageQuery);
    }
}
