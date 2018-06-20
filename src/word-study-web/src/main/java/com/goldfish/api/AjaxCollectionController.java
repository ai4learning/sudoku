package com.goldfish.api;

import com.goldfish.common.log.LogTypeEnum;
import com.goldfish.constant.State;
import com.goldfish.domain.*;
import com.goldfish.service.UnitWordsService;
import com.goldfish.service.WordService;
import com.goldfish.service.WordStudyService;
import com.goldfish.vo.BaseVO;
import com.goldfish.vo.BasicVO;
import com.goldfish.vo.collection.VocabularyVO;
import com.goldfish.vo.word.UnitWordVO;
import com.goldfish.web.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/16 0016.
 * 收藏模块
 */
@Controller
@RequestMapping("/api/Ajax")
public class AjaxCollectionController extends BaseController {

    private static final String MEMORY_SUCCESS = "记忆已经保存";
    private static final String MEMORY_FAIL = "记忆保存失败";
    private static final String LOAD_SUCCESS = "完成加载";
    private static final String LOAD_FAIL = "加载失败";

    @Resource
    private WordStudyService wordStudyService;
    @Resource
    private WordService wordService;
    @Resource
    private UnitWordsService unitWordsService;

    /**
     * /api/Ajax/AjaxPostCollect 收藏单词
     * 入参
     * {
     * vocCode: "8a108cb7-42aa-d911-0142-ad8120300028"
     * }
     * 出参
     * {
     * "success": true,
     * "msg": "记忆已经保存"
     * }
     */
    @RequestMapping(value = "AjaxPostCollect", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    BasicVO doAjaxPostCollect(String vocCode, ModelMap context) {
        BasicVO vo = new BasicVO();
        // 1.装填用户信息
        User user = this.getUserInfo();
        if (user == null) {
            vo.setSuccess(false);
            vo.setMsg(MEMORY_FAIL);
            return vo;
        }
        // 2.使用vocCode查询单词
        WordStudy wordStudyQuery = new WordStudy();
        wordStudyQuery.setStudentId(user.getId().intValue());
        wordStudyQuery.setVocCode(vocCode);
        wordStudyQuery.setState(State.VALID.getState());

        WordStudy ws = wordStudyService.getUnique(wordStudyQuery).getDefaultModel();
        ws.setIscollected(1);
        try {
            wordStudyService.updateWordStudy(ws);
            vo.setSuccess(true);
            vo.setMsg(MEMORY_SUCCESS);
            return vo;
        } catch (Exception e) {
            LogTypeEnum.DEFAULT.error(e.toString());
            vo.setSuccess(false);
            vo.setMsg(MEMORY_FAIL);
            return vo;
        }
    }

    /**
     * /api/Ajax/GetVocabulary
     * 我的收藏单词信息
     * <p>
     * 入参
     * orderType：0表示其他，1表示小学，2表示初中，3表示高中
     * {
     * orderType: '0,1,2,3'
     * }
     * 出参
     * {
     * "data": [{
     * "Id": 113620,
     * "vocCode": "8a108cb7-4c56-483d-014c-564f271e065a",
     * "spelling": "motor neurone disease",
     * "meaning": "n.运动神经元病",
     * "soundMarkUs": "",
     * "vocIndex": 1,
     * "UnitId": 0,
     * "isCollected": false
     * }, {
     * "Id": 1384,
     * "vocCode": "8a108cb7-42aa-d911-0142-ad812032002b",
     * "spelling": "fat",
     * "meaning": "肥的;油腻的",
     * "soundMarkUs": "[fæt]",
     * "vocIndex": 0,
     * "UnitId": 0,
     * "isCollected": false
     * },
     * ...
     * ],
     * "success": true,
     * "msg": "完成加载"
     * }
     */
    @RequestMapping(value = "GetVocabulary", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    VocabularyVO doGetVocabulary(String orderType, ModelMap context) {
        VocabularyVO vocabularyVO = new VocabularyVO();
        List<WordStudy> wordStudyList = getWordStudyList();
        if (wordStudyList == null) {
            vocabularyVO.setMsg(LOAD_FAIL);
            vocabularyVO.setSuccess(false);
            vocabularyVO.setWordList(null);
            return vocabularyVO;
        }
        List<UnitWordVO> unitWordVOList = new ArrayList<>(wordStudyList.size());
        for (WordStudy ws : wordStudyList) {
            UnitWordVO unitWordVO = new UnitWordVO();
            unitWordVO.setId(ws.getId());
            unitWordVO.setIsCollected(true);
            unitWordVO.setVocCode(ws.getVocCode());
            unitWordVO.setSpelling(ws.getSpell());
            unitWordVO.setMeaning(ws.getMeaning());

            Word word = wordService.getWordById(ws.getWordId()).getDefaultModel();
            unitWordVO.setSoundMarkUs(word.getSoundMarkUs());

            UnitWords unitWordsQuery = new UnitWords();
            unitWordsQuery.setWordId(ws.getWordId());
            UnitWords unitWords = unitWordsService.getUnique(unitWordsQuery).getDefaultModel();
            unitWordVO.setVocIndex(unitWords.getVocIndex());
            unitWordVO.setUnitId(unitWords.getUnitNbr());
        }
        vocabularyVO.setWordList(unitWordVOList);
        return vocabularyVO;
    }

    /**
     * /api/Ajax/GetVocabularyCount
     * 我的收藏单词总数
     * <p>
     * 入参
     * orderType：0表示其他，1表示小学，2表示初中，3表示高中
     * {
     * orderType： '0,1,2,3'
     * }
     * 出参
     * {
     * "success": true,
     * "msg": "完成加载",
     * "totalNbr": 45
     * }
     */
    @RequestMapping(value = "GetVocabularyCount", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    BaseVO doGetVocabularyCount(String orderType, ModelMap context) {
        BaseVO vo = new BaseVO();
        List<WordStudy> wordStudyList = getWordStudyList();
        if (wordStudyList == null) {
            vo.setSuccess(false);
            vo.setCondition(-1);
            vo.setMsg(LOAD_FAIL);
            vo.setTotalNbr(0);
            return vo;
        }
        vo.setTotalNbr(wordStudyList.size());
        vo.setMsg(LOAD_SUCCESS);
        vo.setSuccess(true);
        vo.setTotalNbr(0);
        vo.setMsg(LOAD_FAIL);
        vo.setSuccess(false);
        return vo;
    }

    private List<WordStudy> getWordStudyList() {
        User user = this.getUserInfo();
        if (user == null) {
            return null;
        }
        WordStudy wordStudyQuery = new WordStudy();
        wordStudyQuery.setIscollected(1);
        wordStudyQuery.setStudentId(user.getId().intValue());
        return wordStudyService.getListByExample(wordStudyQuery).getDefaultModel();
    }
}
