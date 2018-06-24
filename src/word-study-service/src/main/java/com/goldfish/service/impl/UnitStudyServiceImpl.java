package com.goldfish.service.impl;



import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import com.goldfish.common.log.LogTypeEnum;
import com.goldfish.constant.FinishState;
import com.goldfish.constant.State;
import com.goldfish.constant.StudyPhase;
import com.goldfish.constant.WordLibType;
import com.goldfish.dao.cache.local.CourseContext;
import com.goldfish.dao.cache.local.UnitWordContext;
import com.goldfish.domain.SelfWords;
import com.goldfish.domain.UnitStudy;
import com.goldfish.domain.UnitWords;
import com.goldfish.domain.WordStudy;
import com.goldfish.manager.SelfWordsManager;
import com.goldfish.manager.WordStudyManager;
import com.goldfish.vo.BasicVO;
import com.goldfish.vo.unit.*;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.manager.UnitStudyManager;
import com.goldfish.service.UnitStudyService;


/**
 * @author hellosscat
 * @since 2018-5-8
 *<p>  单元单词学习service实现</p>
 *
 */
@Service("unitStudyService")
public class UnitStudyServiceImpl implements UnitStudyService {

	private static final Logger logger = Logger.getLogger(UnitStudyServiceImpl.class);
	
	@Resource(name= "unitStudyManager")
	private UnitStudyManager unitStudyManager;
	@Resource
	private WordStudyManager wordStudyManager;
	@Resource
	private SelfWordsManager selfWordsManager;
	@Resource
	private CourseContext courseContext;
	@Resource
	private UnitWordContext unitWordContext;



	@Override
	public SaveUnitStudyVO doAjaxInterruptSaveUnit(Integer userId, SaveUnitStudyVO saveUnitStudyVO, String moduleCode, String extra, Integer unitNbr, String studyToken, Integer isContinue, Long seconds4SpellingLetter, Long totalReadingTime, Long totalWritingTime, List<WordStudyDto> vocDataAfterReview, Integer totalWordsNbr) {
		// 1.保存单元学习记录
		UnitStudy unitStudy = getUnitStudy(userId, moduleCode, unitNbr, saveUnitStudyVO);
		if (unitStudy == null) {
			return saveUnitStudyVO;
		}
		try{
			// a.更新学习时间等字段
			unitStudy.setTotalReadingTime(totalReadingTime);
			unitStudy.setTotalWritingTime(totalWritingTime);
			unitStudy.setTotalNumber(totalWordsNbr);
			if ("finish".equals(extra)) {
				/**  是否学习完成  */
				unitStudy.setIsFinished(FinishState.COMPLETE.getState());
				// 单元学习完毕，则到单元测试阶段
				unitStudy.setCurrentPhase(StudyPhase.UNIT_TEST.getPhase());
				saveUnitStudyVO.setIsFinished(2);
			}
			/**  位置类型  */
			unitStudy.setPositionType(1);// 1表示单词
			/**  当前保存，则为当前学习位置，当前写是，同时更新该学生该课程其他单元为false  */
			unitStudy.setIsCurrentPos(1);// 当前保存，则为当前位置

			CommonResult<UnitStudy> updateUnitStudyResult = this.updateUnitWordsStudy(unitStudy);
			if (updateUnitStudyResult == null || !updateUnitStudyResult.isSuccess()) {
				LogTypeEnum.DEFAULT.error("更新学生单元学习失败");
				saveUnitStudyVO.setMsg("更新学生单元学习失败");
				return saveUnitStudyVO;
			}
			// 2.保存每个单词的学习情况
			WordStudy lastStudyWord = new WordStudy();
			for (WordStudyDto dto : vocDataAfterReview) {
				WordStudy wordStudy = updateWordStudy(dto);
				doErrorWord(userId, moduleCode, unitNbr, dto);
				lastStudyWord = wordStudy;
			}

			// b.更新学习位置信息
			/**  单词学习位置  */
//            unitStudy.setStudyPos(lastStudyWord.);
			/**  学习位置CODE  */
			unitStudy.setStudyPositionCode(studyToken);
			/**  单词CODE  */
			unitStudy.setVocCode(lastStudyWord.getVocCode());
			/**  单词  */
			unitStudy.setSpelling(lastStudyWord.getSpell());
			// 更新当前单元学习情况
			unitStudyManager.updateUnitWordsStudy(unitStudy);
			// 更新其他单元为非当前学习单元
			unitStudyManager.otherUnitNotCurStudyPosition(unitStudy);
			saveUnitStudyVO.setLatestStudyPosition(fillLastPostion(unitStudy, studyToken, lastStudyWord, seconds4SpellingLetter));
			saveUnitStudyVO.setSuccess(true);
		} catch (Exception e) {
			LogTypeEnum.DEFAULT.error(e, "保存单元学习异常");
			saveUnitStudyVO.setMsg("保存单元学习异常");
			saveUnitStudyVO.setSuccess(false);
		}

		return saveUnitStudyVO;

	}

	private WordStudy updateWordStudy(WordStudyDto dto) {
		WordStudy wordStudy = new WordStudy();
		wordStudy.setMemoryLevel(dto.getMemoryLevel());
		wordStudy.setTimeLeft(dto.getTimeLeft());
		wordStudy.setUserVocCode(dto.getUserVocCode());
		wordStudy.setUserCode(dto.getUserCode());
		wordStudy.setFinishReadingTime((long) (int) (dto.getFinishReadingTime() * 1000));
		wordStudy.setIsFstReadSuccess(State.getBoolInt().get(dto.isFstReadSuccess()));
		wordStudy.setReadFailTimes(dto.getReadFailTimes());
		wordStudy.setContinueReadFailTimes(dto.getContinueReadFailTimes());
		wordStudy.setIsHalfReading(State.getBoolInt().get(dto.isHalfReading()));
		wordStudy.setIsFstSpellSuccess(State.getBoolInt().get(dto.isFstSpellSuccess()));
		wordStudy.setSpellFailTimes(dto.getSpellFailTimes());
		wordStudy.setContinueSpellFailTimes(dto.getContinueSpellFailTimes());
		wordStudy.setIsHalfSpelling(State.getBoolInt().get(dto.isHalfSpelling()));
		wordStudy.setIsRemember(State.getBoolInt().get(dto.isRemember()));
		wordStudy.setIsCancelReview(State.getBoolInt().get(dto.isCancelReview()));
		wordStudy.setVocCode(dto.getVocCode());
		wordStudy.setModified(new Date());
		wordStudyManager.updateWordStudy(wordStudy);
		return wordStudy;
	}

	private LastStudyPositonVO fillLastPostion(UnitStudy unitStudy, String studyToken, WordStudy lastStudyWord, Long seconds4SpellingLetter) {
		LastStudyPositonVO vo = new LastStudyPositonVO();
		vo.setId(unitStudy.getId());
		vo.setStudyPositionCode(unitStudy.getStudyPositionCode());
		vo.setStudytoken(studyToken);
		vo.setModuleCode(unitStudy.getLessonCode());
		vo.setPositionType(unitStudy.getPositionType());
		vo.setRemark(null);
		vo.setVocCode(unitStudy.getVocCode());
		vo.setTotalReadingTime(unitStudy.getTotalReadingTime());
		vo.setTotalWritingTime(unitStudy.getTotalWritingTime());
		vo.setUserCode(lastStudyWord.getUserCode());
		vo.setUnitNbr(unitStudy.getUnitNbr());
		vo.setWordCount(unitStudy.getTotalNumber());
		vo.setIsContinue(false);
		vo.setSeconds4SpellingLetter(seconds4SpellingLetter);
		vo.setIsCurrentPos(unitStudy.getIsCurrentPos() == 1);
		vo.setIsFinished(unitStudy.getIsFinished() + 1);// 1-->2; 0-->1
		vo.setSpelling(unitStudy.getSpelling());
		vo.setIsAllFinished(unitStudy.getIsAllFinished() == 1);
		vo.setCreatetime(unitStudy.getCreated());
		vo.setIsTested(unitStudy.getIsTested());
		vo.setStatus(unitStudy.getStatus());
		return vo;
	}


	@Override
	public SaveFinishUnitStudyVO doAjaxFinishSaveUnit(Integer studentId, SaveFinishUnitStudyVO saveFinishUnitStudyVO,
			String moduleCode, String extra, Integer unitNbr, String studyToken,
		  Integer isContinue, Long seconds4SpellingLetter, Long totalReadingTime, Long totalWritingTime, List<WordStudyDto> vocDataAfterReview, Integer totalWordsNbr) {
		// 1.保存单元学习记录
		UnitStudy unitStudy = getUnitStudy(studentId, moduleCode, unitNbr, saveFinishUnitStudyVO);
		if (unitStudy == null) {
			return saveFinishUnitStudyVO;
		}
		try{
			// a.更新学习时间等字段
			unitStudy.setTotalReadingTime(totalReadingTime);
			unitStudy.setTotalWritingTime(totalWritingTime);
			unitStudy.setTotalNumber(totalWordsNbr);
			if ("finish".equals(extra)) {
				/**  是否学习完成  */
				unitStudy.setIsFinished(FinishState.COMPLETE.getState());
				// 单元学习完毕，则到单元测试阶段
				unitStudy.setCurrentPhase(StudyPhase.UNIT_TEST.getPhase());
			}
			/**  位置类型  */
			unitStudy.setPositionType(1);// 1表示单词
			/**  当前保存，则为当前学习位置，当前写是，同时更新该学生该课程其他单元为false  */
			unitStudy.setIsCurrentPos(1);// 当前保存，则为当前位置

			CommonResult<UnitStudy> updateUnitStudyResult = this.updateUnitWordsStudy(unitStudy);
			if (updateUnitStudyResult == null || !updateUnitStudyResult.isSuccess()) {
				LogTypeEnum.DEFAULT.error("更新学生单元学习失败");
				saveFinishUnitStudyVO.setMsg("更新学生单元学习失败");
				return saveFinishUnitStudyVO;
			}
			// 2.保存每个单词的学习情况
			WordStudy lastStudyWord = null;
			for (WordStudyDto dto : vocDataAfterReview) {
				WordStudy wordStudy = updateWordStudy(dto);
				doErrorWord(studentId, moduleCode, unitNbr, dto);
				lastStudyWord = wordStudy;
			}

			// b.更新学习位置信息
			/**  单词学习位置  */
//            unitStudy.setStudyPos(lastStudyWord.);
            /**  学习位置CODE  */
            unitStudy.setStudyPositionCode(studyToken);
            /**  单词CODE  */
            unitStudy.setVocCode(lastStudyWord.getVocCode());
            /**  单词  */
            unitStudy.setSpelling(lastStudyWord.getSpell());
			// 更新当前单元学习情况
			unitStudyManager.updateUnitWordsStudy(unitStudy);
			// 更新其他单元为非当前学习单元
			unitStudyManager.otherUnitNotCurStudyPosition(unitStudy);
			saveFinishUnitStudyVO.setSuccess(true);
		} catch (Exception e) {
			LogTypeEnum.DEFAULT.error(e, "保存单元学习异常");
			saveFinishUnitStudyVO.setMsg("保存单元学习异常");
			saveFinishUnitStudyVO.setSuccess(false);
		}
		saveFinishUnitStudyVO.setLatestStudyPosition(fillLastPostion(vocDataAfterReview));
		return saveFinishUnitStudyVO;
	}

	private void doErrorWord(Integer studentId, String moduleCode, Integer unitNbr, WordStudyDto dto) {

		// 若为错词，则添加到错词本
		Integer readFailTimes = dto.getReadFailTimes();
		Integer spellFailTimes = dto.getSpellFailTimes();
		Integer continueReadFailTimes = dto.getContinueReadFailTimes();
		Integer continueSpellFailTimes = dto.getContinueSpellFailTimes();
		if (readFailTimes > 0 || spellFailTimes > 0 || continueReadFailTimes > 0 || continueSpellFailTimes > 0) {
			LogTypeEnum.DEFAULT.info("add to error book, vocCode={},userId={}", dto.getVocCode(), studentId);
			SelfWords errWord = new SelfWords();
			errWord.setModuleCode(moduleCode);
			errWord.setStudentId(Long.valueOf(String.valueOf(studentId)));
			errWord.setUserCode(dto.getUserCode());
			errWord.setType(WordLibType.ERROR_BOOK.getType());
			errWord.setLessonId(courseContext.getLessonIdByCode(moduleCode));
			errWord.setUnitNbr(unitNbr);

			errWord.setVocCode(dto.getVocCode());
			UnitWords unitWord = unitWordContext.getUnitWord(dto.getVocCode());
			errWord.setWordId(unitWord.getWordId());
			errWord.setSpelling(unitWord.getSpelling());
			errWord.setState(State.VALID.getState());
			Date current = new Date();
			errWord.setCreated(current);
			errWord.setModified(current);
			selfWordsManager.addSelfWords(errWord);
		}
	}

	private List<WordStudyVO> fillLastPostion(List<WordStudyDto> src) {
		if (src == null || src.isEmpty()) {
			return null;
		}
		List<WordStudyVO> target = new ArrayList<WordStudyVO>(src.size());
		for (WordStudyDto dto : src) {
			WordStudyVO wordStudyVO = new WordStudyVO();
			wordStudyVO.setUserCode(dto.getUserCode());
			wordStudyVO.setVocCode(dto.getVocCode());
			wordStudyVO.setFinishReadingTime(dto.getFinishReadingTime());
			wordStudyVO.setIsFstReadSuccess(dto.isFstReadSuccess());
			wordStudyVO.setReadFailTimes(dto.getReadFailTimes());
			wordStudyVO.setContinueReadFailTimes(dto.getContinueReadFailTimes());
			wordStudyVO.setIsFstSpellSuccess(dto.isFstSpellSuccess());
			wordStudyVO.setSpellFailTimes(dto.getSpellFailTimes());
			wordStudyVO.setContinueSpellFailTimes(dto.getContinueSpellFailTimes());
			wordStudyVO.setIsHalfReading(dto.isHalfReading());
			wordStudyVO.setIsHalfSpelling(dto.isHalfSpelling());
			wordStudyVO.setRemark(null);
			wordStudyVO.setStartTime(null);
			wordStudyVO.setLastReviewTime(new Date());
			wordStudyVO.setLastComputeTime(null);
			wordStudyVO.setIsRemember(dto.isRemember());
			wordStudyVO.setIsCancelReview(dto.isCancelReview());
			wordStudyVO.setCreateTime(null);
			wordStudyVO.setReviewTimes(null);
			wordStudyVO.setMemoryLevel(dto.getMemoryLevel());
			wordStudyVO.setTimeLeft(dto.getTimeLeft());
			wordStudyVO.setStatus(null);
			target.add(wordStudyVO);
		}
		return target;
	}


	private UnitStudy getUnitStudy(Integer studentId, String moduleCode, Integer unitNbr, BasicVO vo) {
		UnitStudy unitStudyQuery = new UnitStudy();
		unitStudyQuery.setStudentId(studentId);
		unitStudyQuery.setLessonCode(moduleCode);
		unitStudyQuery.setUnitNbr(unitNbr);
		unitStudyQuery.setState(State.VALID.getState());
		CommonResult<UnitStudy> unitStudyResult = this.getUnique(unitStudyQuery);
		if (unitStudyResult == null || !unitStudyResult.isSuccess()) {
			LogTypeEnum.DEFAULT.error("查询单元学习失败");
			vo.setMsg("查询单元学习失败");
			return null;
		}
		UnitStudy unitStudy = unitStudyResult.getDefaultModel();
		if (unitStudy == null) {
			LogTypeEnum.DEFAULT.error("学生单元学习不存在");
			vo.setMsg("学生单元学习不存在");
			vo.setSuccess(true);
			return null;
		}
		return unitStudy;
	}

	public CommonResult<UnitStudy> addUnitWordsStudy(UnitStudy unitStudy) {
		CommonResult<UnitStudy> result = new CommonResult<UnitStudy>();
		try{
			
			unitStudy.setCreated(new Date());
			
			
			unitStudy.setModified(new Date());
			
			result.addDefaultModel(unitStudyManager.addUnitWordsStudy(unitStudy));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("添加 单元单词学习失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	public CommonResult<UnitStudy> updateUnitWordsStudy(UnitStudy unitStudy) {
		CommonResult<UnitStudy> result = new CommonResult<UnitStudy>();
		try {
			
					unitStudy.setModified(new Date());
					
			unitStudyManager.updateUnitWordsStudy(unitStudy);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("更新 单元单词学习失败", e);
			result.setSuccess(false);
		}
		return result;
	}


	public CommonResult<UnitStudy> deleteUnitWordsStudy(Long id) {
		CommonResult<UnitStudy> result = new CommonResult<UnitStudy>();
		try {
			unitStudyManager.deleteUnitWordsStudy(id);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("删除 单元单词学习失败", e);
			result.setSuccess(false);
		}
		return result;
    }


    	public CommonResult<UnitStudy> getUnitWordsStudyById(Long id) {
		CommonResult<UnitStudy> result = new CommonResult<UnitStudy>();
		try {
			result.addDefaultModel("unitWordsStudy", unitStudyManager.getUnitWordsStudyById(id));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据主键获取 单元单词学习失败", e);
			result.setSuccess(false);
		}
		return result;
	}

	public CommonResult<UnitStudy> getUnique(UnitStudy unitStudy) {
		CommonResult<UnitStudy> result = new CommonResult<UnitStudy>();
		try {
			result.addDefaultModel(unitStudyManager.getUnique(unitStudy));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据example获取唯一 单元单词学习失败", e);
			result.setSuccess(false);
		}
		return result;
	}


	public CommonResult<List<UnitStudy>> getListByExample(UnitStudy unitStudy) {
		CommonResult<List<UnitStudy>> result = new CommonResult<List<UnitStudy>>();
		try {
			List<UnitStudy> list = unitStudyManager.getListByExample(unitStudy);
			result.addDefaultModel("list", list);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("取得 单元单词学习失败", e);
			result.setSuccess(false);
		}
		return result;
	}

	
	public CommonResult<List<UnitStudy>> getUnitWordsStudyByPage(PageQuery pageQuery) {
		CommonResult<List<UnitStudy>> result = new CommonResult<List<UnitStudy>>();
		try {
			int totalCount = this.count(pageQuery);
			if (totalCount > 0) {
				pageQuery.setTotalCount(totalCount);
				List<UnitStudy> list = unitStudyManager.getUnitWordsStudyByPage(pageQuery);
				result.addDefaultModel("list", list);
				result.addModel("pageQuery", pageQuery);
			}
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("分页获取 单元单词学习失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	public int count(PageQuery pageQuery) {
		return unitStudyManager.count(pageQuery);
	}




	/******* getter and setter ***/
	public UnitStudyManager getUnitStudyManager() {
		return unitStudyManager;
	}

	public void setUnitStudyManager(UnitStudyManager unitStudyManager) {
		this.unitStudyManager = unitStudyManager;
	}

	public int sumReading(PageQuery pageQuery){
		return unitStudyManager.sumReading(pageQuery);
	}

	public int sumWriting(PageQuery pageQuery)
	{
		return unitStudyManager.sumWriting(pageQuery);
	}

}
