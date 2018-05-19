package com.goldfish.task;

import com.goldfish.common.CommonResult;
import com.goldfish.common.PageQuery;
import com.goldfish.common.log.LogTypeEnum;
import com.goldfish.constant.*;
import com.goldfish.domain.*;
import com.goldfish.service.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 初始化学生学习数据
 * Created by John on 2018/5/19 0019.
 */
@Component("initStuStudyDataTask")
public class InitStuStudyDataTask extends AbstractTask{

    @Resource
    private CourseStudyService courseStudyService;
    @Resource
    private UnitWordsStudyService unitWordsStudyService;

    @Resource
    private UnitService unitService;
    @Resource
    private WordStudyService wordStudyService;
    @Resource
    private UnitWordsService unitWordsService;


    /**
     * 执行任务
     * @param task
     */
    protected boolean execute(Task task) {
        Integer userId = task.getUserId();
        Long lessonId = task.getBusinessId();

        /** 初始化学生学习数据 **/

        // 1.初始化学生课程学习记录
        if (!initCourseStudyData(userId, lessonId)) {
            return false;
        }

        // 2.初始化学生单元单词学习记录
        if (!initUnitWordStudy(userId, lessonId)) {
            return false;
        }

        // 3.初始化单词学习记录
        if (!initWordStudy(userId, lessonId)) {
            return false;
        }
        LogTypeEnum.DEFAULT.info("学生学习记录初始化成功，userId={}, lessonId={}", userId, lessonId);
        return true;
    }

    /**
     * 初始化学生课程学习数据
     * @param userId
     * @param lessonId
     * @return
     */
    private boolean initCourseStudyData(Integer userId, Long lessonId) {
        LogTypeEnum.DEFAULT.debug("学生课程学习数据处理开始");
        CourseStudy query = new CourseStudy();
        query.setStudentId(userId);
        query.setLessonId(Integer.valueOf(String.valueOf(lessonId)));
        query.setStatus(State.VALID.getState());
        CommonResult<CourseStudy> result = courseStudyService.getUnique(query);
        if (!result.isSuccess()) {
            LogTypeEnum.DEFAULT.error("查询学生课程学习失败，msg={}",result.getMessage());
            return false;
        }
        CourseStudy courseStudy = result.getDefaultModel();
        if (courseStudy == null) {
            LogTypeEnum.DEFAULT.info("课程学习记录不存在，需插入");
            if (!insertCourseStudy(userId, lessonId)) {
                return false;
            }
        }
        LogTypeEnum.DEFAULT.info("学生课程学习数据处理完毕");
        return true;
    }

    private boolean initUnitWordStudy(Integer userId, Long lessonId) {
        PageQuery pageQuery = new PageQuery();
        pageQuery.setPageSize(100000000);
        pageQuery.put("lessonId", lessonId);
        pageQuery.put("state", State.VALID.getState());
        CommonResult<List<Unit>> result = unitService.getUnitByPage(pageQuery);
        if (!result.isSuccess()) {
            LogTypeEnum.DEFAULT.error("查询课程内单元列表失败，msg={}", result.getMessage());
            return false;
        }
        List<Unit> units = result.getDefaultModel();
        if (units == null || units.isEmpty()) {
            LogTypeEnum.DEFAULT.error("单元列表竟然为空");
            return false;
        }
        for (Unit unit : units) {
            insertUnitStudy(userId, unit);
        }
        return true;
    }

    private boolean initWordStudy(Integer userId, Long lessonId) {
        PageQuery pageQuery = new PageQuery();
        pageQuery.setPageSize(100000000);
        pageQuery.put("lessonId", lessonId);
        pageQuery.put("state", State.VALID.getState());
        CommonResult<List<UnitWords>> result = unitWordsService.getUnitWordsByPage(pageQuery);
        if (!result.isSuccess()) {
            LogTypeEnum.DEFAULT.error("查询课程内单元单词失败，msg={}", result.getMessage());
            return false;
        }
        List<UnitWords> unitWords = result.getDefaultModel();
        if (unitWords == null || unitWords.isEmpty()) {
            LogTypeEnum.DEFAULT.error("单元内单词竟然为空");
            return false;
        }
        for (UnitWords unitWord : unitWords) {
            insertWordStudy(userId, unitWord);
        }
        return true;
    }

    private boolean insertCourseStudy(Integer userId, Long lessonId) {
        CourseStudy courseStudy = new CourseStudy();
        courseStudy.setStudentId(userId);

        // 学习位置相关跟踪
        courseStudy.setIsCurrentPos(State.NO.getState());
        courseStudy.setIsTest(FinishState.NOT_COMPLETE.getState());
        courseStudy.setIsFinished(FinishState.NOT_COMPLETE.getState());
        courseStudy.setIsAllFinished(FinishState.NOT_COMPLETE.getState());

        courseStudy.setPositionType(PositionType.WORD.getType());
        courseStudy.setLessonId(Integer.valueOf(String.valueOf(lessonId)));
        courseStudy.setUnitNbr(0);
        courseStudy.setStartFrom(0);

        courseStudy.setCompleteWordCount(0);
        // 默认逆向模式
        courseStudy.setStudyMode(StudyMode.REVERSE.getMode());
        courseStudy.setStatus(State.VALID.getState());
        CommonResult<CourseStudy> insertResult = courseStudyService.addCourseStudy(courseStudy);

        if (!insertResult.isSuccess()) {
            LogTypeEnum.DEFAULT.error("课程学习记录插入数据失败，userId={},lessonId={}", userId,lessonId);
            return false;
        }
        return true;
    }

    private void insertUnitStudy(Integer userId, Unit unit) {
        try {
            UnitWordsStudy unitWordsStudy = new UnitWordsStudy();

            unitWordsStudy.setStudentId(userId);

            // 1.拷贝unit属性
            unitWordsStudy.setLessonId(Integer.valueOf(String.valueOf(unit.getLessonId())));
            unitWordsStudy.setUnitNbr(unit.getUnitNbr());
            unitWordsStudy.setTotalNumber(unit.getTotalWords());

            // 2.初始化学习状态
            unitWordsStudy.setCurrentPhase(StudyPhase.ENHANCE_STUDY.getPhase());
            unitWordsStudy.setTotalReadingTime(0L);
            unitWordsStudy.setTotalWritingTime(0L);
            unitWordsStudy.setIsFinished(FinishState.NOT_COMPLETE.getState());
            unitWordsStudy.setIsTested(FinishState.NOT_COMPLETE.getState());
            unitWordsStudy.setStudyPos(0);// 单词index从0开始
            unitWordsStudy.setState(State.VALID.getState());
            unitWordsStudyService.addUnitWordsStudy(unitWordsStudy);
        } catch (Exception e) {
            LogTypeEnum.DEFAULT.error(e, "单元学习记录插入失败, userId={},lessonId={},unitNbr={}",
                    userId, unit.getLessonId(),unit.getUnitNbr());
        }
    }

    private void insertWordStudy(Integer userId, UnitWords unitWord) {
        try {
            WordStudy wordStudy = new WordStudy();

            wordStudy.setStudentId(userId);

            // 1.拷贝unitword属性
            wordStudy.setVocCode(unitWord.getVocCode());
            wordStudy.setWordId(unitWord.getWordId());
            wordStudy.setSpell(unitWord.getSpelling());
            wordStudy.setMeaning(unitWord.getMeaning());

            // 2.初始化学习状态
            wordStudy.setMemoryLevel(MemoryLevel.NEW_WORD.getLevel());
            wordStudy.setReviewTimes(0);
            wordStudy.setTimeLeft(-1L);
            wordStudy.setFinishReadingTime(-1L);
            wordStudy.setIsFstReadSuccess(-1);
            wordStudy.setIsFstSpellSuccess(-1);
            wordStudy.setReadFailTimes(0);
            wordStudy.setContinueReadFailTimes(0);
            wordStudy.setContinueSpellFailTimes(0);
            wordStudy.setIsHalfReading(0);
            wordStudy.setIsHalfSpelling(0);
            wordStudy.setSpellFailTimes(0);
            wordStudy.setIsRemember(0);
            wordStudy.setIsCancelReview(0);
            wordStudy.setState(State.VALID.getState());
            wordStudyService.addWordStudy(wordStudy);
        } catch (Exception e) {
            LogTypeEnum.DEFAULT.error(e, "单词学习记录插入失败, userId={},vocCode={}", userId, unitWord.getVocCode());
        }
    }

    @Override
    public String getName() {
        return "初始化学生学习数据";
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.INIT_STUDY_DATA;
    }
}
