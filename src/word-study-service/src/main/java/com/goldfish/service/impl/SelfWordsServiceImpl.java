package com.goldfish.service.impl;


import javax.annotation.Resource;
import java.util.*;

import com.goldfish.common.log.LogTypeEnum;
import com.goldfish.constant.State;
import com.goldfish.constant.WordLibType;
import com.goldfish.domain.Course;
import com.goldfish.domain.CourseStudy;
import com.goldfish.domain.Word;
import com.goldfish.manager.*;
import com.goldfish.vo.BaseVO;
import com.goldfish.vo.error.ErrorBookInfo;
import com.goldfish.vo.error.ErrorBookVO;
import com.goldfish.vo.error.ErrorWordsVO;
import com.goldfish.vo.word.UnitWordVO;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.SelfWords;
import com.goldfish.service.SelfWordsService;


/**
 * @author hellosscat
 * @since 2018-5-8
 * <p>  学生自身单词service实现</p>
 */
@Service("selfWordsService")
public class SelfWordsServiceImpl implements SelfWordsService {

    private static final Logger logger = Logger.getLogger(SelfWordsServiceImpl.class);
    private static final Integer UN_LIMIT = 1000000000;
    private static final ErrorBookInfo ERROR_BOOK_INFO = new ErrorBookInfo();


    @Resource(name = "selfWordsManager")
    private SelfWordsManager selfWordsManager;
    @Resource
    private WordManager wordManager;
    @Resource
    private CourseManager courseManager;
    @Resource
    private CourseStudyManager courseStudyManager;

    @Override
    public CommonResult<SelfWords> addSelfWords(SelfWords selfWords) {
        CommonResult<SelfWords> result = new CommonResult<SelfWords>();
        try {
            selfWords.setCreated(new Date());
            selfWords.setModified(new Date());
            result.addDefaultModel(selfWordsManager.addSelfWords(selfWords));
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error("添加 学生自身单词失败", e);
            result.setSuccess(false);
        }
        return result;
    }

    @Override
    public CommonResult<SelfWords> updateSelfWords(SelfWords selfWords) {
        CommonResult<SelfWords> result = new CommonResult<SelfWords>();
        try {

            selfWords.setModified(new Date());

            selfWordsManager.updateSelfWords(selfWords);
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error("更新 学生自身单词失败", e);
            result.setSuccess(false);
        }
        return result;
    }


    @Override
    public CommonResult<SelfWords> deleteSelfWords(Long id) {
        CommonResult<SelfWords> result = new CommonResult<SelfWords>();
        try {
            selfWordsManager.deleteSelfWords(id);
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error("删除 学生自身单词失败", e);
            result.setSuccess(false);
        }
        return result;
    }


    @Override
    public CommonResult<SelfWords> getSelfWordsById(Long id) {
        CommonResult<SelfWords> result = new CommonResult<SelfWords>();
        try {
            result.addDefaultModel("selfWords", selfWordsManager.getSelfWordsById(id));
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error("根据主键获取 学生自身单词失败", e);
            result.setSuccess(false);
        }
        return result;
    }


    @Override
    public CommonResult<SelfWords> getUnique(SelfWords selfWords) {
        CommonResult<SelfWords> result = new CommonResult<SelfWords>();
        try {
            result.addDefaultModel(selfWordsManager.getUnique(selfWords));
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error("根据example获取唯一 学生自身单词失败", e);
            result.setSuccess(false);
        }
        return result;
    }


    @Override
    public CommonResult<List<SelfWords>> getListByExample(SelfWords selfWords) {
        CommonResult<List<SelfWords>> result = new CommonResult<List<SelfWords>>();
        try {
            List<SelfWords> list = selfWordsManager.getListByExample(selfWords);
            result.addDefaultModel("list", list);
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error("取得 学生自身单词失败", e);
            result.setSuccess(false);
        }
        return result;
    }


    @Override
    public CommonResult<List<SelfWords>> getSelfWordsByPage(PageQuery pageQuery) {
        CommonResult<List<SelfWords>> result = new CommonResult<List<SelfWords>>();
        try {
            int totalCount = this.count(pageQuery);
            if (totalCount > 0) {
                pageQuery.setTotalCount(totalCount);
                List<SelfWords> list = selfWordsManager.getSelfWordsByPage(pageQuery);
                result.addDefaultModel("list", list);
                result.addModel("pageQuery", pageQuery);
            }
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error("分页获取 学生自身单词失败", e);
            result.setSuccess(false);
        }
        return result;
    }

    @Override
    public int count(PageQuery pageQuery) {
        return selfWordsManager.count(pageQuery);
    }


    @Override
    public ErrorBookVO getErrorUnit(Integer userId, String studyToken, String moduleCode, Integer unitNbr) {
        ErrorBookVO errorBookVO = new ErrorBookVO();

        PageQuery pageQuery = new PageQuery(0, UN_LIMIT);
        // 增加错词状态查询，已学习状态单词就不需再学习了
        pageQuery.addQueryParam("studentId", userId);
        pageQuery.addQueryParam("type", WordLibType.ERROR_BOOK.getType());
        pageQuery.addQueryParam("state", State.VALID.getState());
        List<SelfWords> errorWords = this.getSelfWordsByPage(pageQuery).getDefaultModel();

        long totalCount = pageQuery.getTotalCount();
        if (totalCount == 0) {
            LogTypeEnum.DEFAULT.info("没有错词记录，userId={},moduleCode={},unitNbr={}", userId, moduleCode, unitNbr);
            errorBookVO.setSuccess(true);
            errorBookVO.setMsg("错词本为空");
            return errorBookVO;
        }
        fillErrorWordsVO(studyToken, errorBookVO, errorWords, totalCount);
        // 填充bookInfo
        errorBookVO.setBookInfo(ERROR_BOOK_INFO);
        return errorBookVO;
    }

    private void fillErrorWordsVO(String studyToken, ErrorWordsVO errorBookVO, List<SelfWords> errorWords, long totalCount) {
        errorBookVO.setTotalNbr(Integer.valueOf(String.valueOf(totalCount)));
        // 填充错词
        List<UnitWordVO> unitWordVOs = new ArrayList<UnitWordVO>(errorWords.size());
        for (SelfWords err : errorWords) {
            UnitWordVO unitWordVO = new UnitWordVO();
            unitWordVO.setId(err.getId());
            unitWordVO.setUnitId(0);
            unitWordVO.setUnitNbr(err.getUnitNbr());
            unitWordVO.setVocCode(err.getVocCode());
            unitWordVO.setVocIndex(err.getVocIndex());
            unitWordVO.setSpelling(err.getSpelling());
            unitWordVO.setIsCollected(false);

            Word word = wordManager.getWordById(err.getWordId());
            unitWordVO.setMeaning(word.getMeaning());
            unitWordVO.setSoundMarkUs(word.getSoundMarkUs());
            unitWordVOs.add(unitWordVO);
        }
        errorBookVO.setData(unitWordVOs);

        errorBookVO.setStudytoken(studyToken);
        errorBookVO.setMsg("搞定！");
        errorBookVO.setSuccess(true);
    }

    @Override
    public ErrorWordsVO getErrorWordsByConditon(Integer userId, String studyToken,
                                                String orderType, Integer start, Integer limit) {
        ErrorWordsVO errorWordsVO = new ErrorWordsVO();
        String[] orderTypes = orderType.split(",");
        Set<Integer> orderSet = new HashSet<Integer>(16);
        for (String order : orderTypes) {
            orderSet.add(Integer.valueOf(order));
        }
        List<Integer> lessonIds = new ArrayList<Integer>();

        // 1.查询用户课程信息
        PageQuery courseQuery = new PageQuery(0, UN_LIMIT);
        courseQuery.addQueryParam("studentId", userId);
        List<CourseStudy> courseStudies =
                courseStudyManager.getCourseStudyByPage(courseQuery);
        if (courseStudies == null || courseStudies.isEmpty()) {
            LogTypeEnum.DEFAULT.info("未找到相应学习课程，studengId={}", userId);
            errorWordsVO.setMsg("错词本为空");
            errorWordsVO.setSuccess(true);
            return errorWordsVO;
        }
        for (CourseStudy courseStudy : courseStudies) {
            Course query = new Course();
            query.setBookNumber(courseStudy.getLessonId());
            query.setBookState(State.VALID.getState());
            Course course = courseManager.getUnique(query);
            if (orderSet.contains(course.getOrderType())) {
                lessonIds.add(course.getBookNumber());
            }
        }
        // 不存在需要搜索的课程
        if (lessonIds.isEmpty()) {
            LogTypeEnum.DEFAULT.info("指定课程下没有找到满足条件的课程");
            errorWordsVO.setMsg("错词本为空");
            errorWordsVO.setSuccess(true);
            return errorWordsVO;
        }

        PageQuery pageQuery = new PageQuery(start,limit);
        pageQuery.addQueryParam("studentId", userId);
        pageQuery.addQueryParam("type", WordLibType.ERROR_BOOK.getType());
        pageQuery.addQueryParam("state", State.VALID.getState());
        pageQuery.addQueryParam("lessonIds", lessonIds);
        List<SelfWords> errorWords = selfWordsManager.inQuerySelfWords(pageQuery);
        if (errorWords == null || errorWords.isEmpty()) {
            LogTypeEnum.DEFAULT.info("未找到错词");
            errorWordsVO.setMsg("错词本为空");
            errorWordsVO.setSuccess(true);
            return errorWordsVO;
        }
        // 组装VO返回
        fillErrorWordsVO(studyToken, errorWordsVO, errorWords, errorWords.size());
        return errorWordsVO;
    }

    @Override
    public BaseVO countErrorWordsByConditon(Integer userId, String studyToken, String orderType) {
        BaseVO vo = new BaseVO();
        ErrorWordsVO errorWordsVO = this.getErrorWordsByConditon(userId, studyToken, orderType, 0, UN_LIMIT);
        vo.setTotalNbr(errorWordsVO.getTotalNbr());
        vo.setSuccess(errorWordsVO.isSuccess());
        vo.setMsg(errorWordsVO.getMsg());
        vo.setCondition(errorWordsVO.getCondition());
        return vo;
    }

}
