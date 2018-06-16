package com.goldfish.service.impl;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import com.goldfish.common.log.LogTypeEnum;
import com.goldfish.domain.Word;
import com.goldfish.manager.WordManager;
import com.goldfish.manager.WordStudyManager;
import com.goldfish.vo.error.ErrorBookInfo;
import com.goldfish.vo.error.ErrorBookVO;
import com.goldfish.vo.word.UnitWordVO;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.SelfWords;
import com.goldfish.manager.SelfWordsManager;
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
    private WordStudyManager wordStudyManager;
    @Resource
    private WordManager wordManager;

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

    public int count(PageQuery pageQuery) {
        return selfWordsManager.count(pageQuery);
    }

    /**
     *      * {
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
    @Override
    public ErrorBookVO getErrorUnit(Integer userId, String studyToken, String moduleCode, Integer unitNbr) {
        ErrorBookVO errorBookVO = new ErrorBookVO();

        PageQuery pageQuery = new PageQuery();
        pageQuery.setStartIndex(0);
        pageQuery.setPageSize(UN_LIMIT);
        List<SelfWords> errorWords = this.getSelfWordsByPage(pageQuery).getDefaultModel();

        long totalCount = pageQuery.getTotalCount();
        if (totalCount == 0) {
            LogTypeEnum.DEFAULT.info("没有错词记录，userId={},moduleCode={},unitNbr={}", userId, moduleCode, unitNbr);
            errorBookVO.setSuccess(true);
            errorBookVO.setMsg("错词本为空");
            return errorBookVO;
        }
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

        // 填充bookInfo
        errorBookVO.setBookInfo(ERROR_BOOK_INFO);
        errorBookVO.setStudytoken(studyToken);
        errorBookVO.setMsg("搞定！");
        errorBookVO.setSuccess(true);
        return errorBookVO;
    }


    /*******
     * getter and setter
     ***/
    public SelfWordsManager getSelfWordsManager() {
        return selfWordsManager;
    }

    public void setSelfWordsManager(SelfWordsManager selfWordsManager) {
        this.selfWordsManager = selfWordsManager;
    }

}
