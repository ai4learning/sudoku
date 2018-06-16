package com.goldfish.service.impl;



import javax.annotation.Resource;
import java.util.List;
import java.util.Date;

import com.goldfish.common.log.LogTypeEnum;
import com.goldfish.constant.FinishState;
import com.goldfish.constant.StudyPhase;
import com.goldfish.domain.UnitStudy;
import com.goldfish.domain.WordStudy;
import com.goldfish.vo.unit.SaveUnitStudyVO;
import com.goldfish.vo.unit.WordStudyDto;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.SelfWordsStudy;
import com.goldfish.manager.SelfWordsStudyManager;
import com.goldfish.service.SelfWordsStudyService;


/**
 * @author hellosscat
 * @since 2018-5-8
 *<p>  学生自身单词学习service实现</p>
 *
 */
@Service("selfWordsStudyService")
public class SelfWordsStudyServiceImpl implements SelfWordsStudyService {

	private static final Logger logger = Logger.getLogger(SelfWordsStudyServiceImpl.class);
	
	@Resource(name="selfWordsStudyManager")
	private SelfWordsStudyManager selfWordsStudyManager;
    
    public CommonResult<SelfWordsStudy> addSelfWordsStudy(SelfWordsStudy selfWordsStudy) {
		CommonResult<SelfWordsStudy> result = new CommonResult<SelfWordsStudy>();
		try{
			
			selfWordsStudy.setCreated(new Date());
			
			
			selfWordsStudy.setModified(new Date());
			
			result.addDefaultModel(selfWordsStudyManager.addSelfWordsStudy(selfWordsStudy));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("添加 学生自身单词学习失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	public CommonResult<SelfWordsStudy> updateSelfWordsStudy(SelfWordsStudy selfWordsStudy) {
		CommonResult<SelfWordsStudy> result = new CommonResult<SelfWordsStudy>();
		try {
			
					selfWordsStudy.setModified(new Date());
					
			selfWordsStudyManager.updateSelfWordsStudy(selfWordsStudy);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("更新 学生自身单词学习失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
   

	public CommonResult<SelfWordsStudy> deleteSelfWordsStudy(Long id) {
		CommonResult<SelfWordsStudy> result = new CommonResult<SelfWordsStudy>();
		try {
			selfWordsStudyManager.deleteSelfWordsStudy(id);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("删除 学生自身单词学习失败", e);
			result.setSuccess(false);
		}
		return result;
    }


    	public CommonResult<SelfWordsStudy> getSelfWordsStudyById(Long id) {
		CommonResult<SelfWordsStudy> result = new CommonResult<SelfWordsStudy>();
		try {
			result.addDefaultModel("selfWordsStudy", selfWordsStudyManager.getSelfWordsStudyById(id));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据主键获取 学生自身单词学习失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	

        
	


	public CommonResult<SelfWordsStudy> getUnique(SelfWordsStudy selfWordsStudy) {
		CommonResult<SelfWordsStudy> result = new CommonResult<SelfWordsStudy>();
		try {
			result.addDefaultModel(selfWordsStudyManager.getUnique(selfWordsStudy));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据example获取唯一 学生自身单词学习失败", e);
			result.setSuccess(false);
		}
		return result;
	}


	public CommonResult<List<SelfWordsStudy>> getListByExample(SelfWordsStudy selfWordsStudy) {
		CommonResult<List<SelfWordsStudy>> result = new CommonResult<List<SelfWordsStudy>>();
		try {
			List<SelfWordsStudy> list = selfWordsStudyManager.getListByExample(selfWordsStudy);
			result.addDefaultModel("list", list);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("取得 学生自身单词学习失败", e);
			result.setSuccess(false);
		}
		return result;
	}

	
	public CommonResult<List<SelfWordsStudy>> getSelfWordsStudyByPage(PageQuery pageQuery) {
		CommonResult<List<SelfWordsStudy>> result = new CommonResult<List<SelfWordsStudy>>();
		try {
			int totalCount = this.count(pageQuery);
			if (totalCount > 0) {
				pageQuery.setTotalCount(totalCount);
				List<SelfWordsStudy> list = selfWordsStudyManager.getSelfWordsStudyByPage(pageQuery);
				result.addDefaultModel("list", list);
				result.addModel("pageQuery", pageQuery);
			}
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("分页获取 学生自身单词学习失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	public int count(PageQuery pageQuery) {
		return selfWordsStudyManager.count(pageQuery);
	}

	@Override
	public SaveUnitStudyVO saveErrorStudy(Integer userId, String moduleCode, String extra, Integer unitNbr,
  			String vocCode, String studytoken, Long totalReadingTime, Long totalWritingTime,
								  List<WordStudyDto> vocDataAfterReview) {
		return null;


//		// 1.保存单元学习记录
//		UnitStudy unitStudy = getUnitStudy(userId, moduleCode, unitNbr, saveUnitStudyVO);
//		if (unitStudy == null) {
//			return saveUnitStudyVO;
//		}
//		try{
//			// a.更新学习时间等字段
//			unitStudy.setTotalReadingTime(totalReadingTime);
//			unitStudy.setTotalWritingTime(totalWritingTime);
//			unitStudy.setTotalNumber(totalWordsNbr);
//			if ("finish".equals(extra)) {
//				/**  是否学习完成  */
//				unitStudy.setIsFinished(FinishState.COMPLETE.getState());
//				// 单元学习完毕，则到单元测试阶段
//				unitStudy.setCurrentPhase(StudyPhase.UNIT_TEST.getPhase());
//			}
//			/**  位置类型  */
//			unitStudy.setPositionType(1);// 1表示单词
//			/**  当前保存，则为当前学习位置，当前写是，同时更新该学生该课程其他单元为false  */
//			unitStudy.setIsCurrentPos(1);// 当前保存，则为当前位置
//
//			CommonResult<UnitStudy> updateUnitStudyResult = this.updateUnitWordsStudy(unitStudy);
//			if (updateUnitStudyResult == null || !updateUnitStudyResult.isSuccess()) {
//				LogTypeEnum.DEFAULT.error("更新学生单元学习失败");
//				saveUnitStudyVO.setMsg("更新学生单元学习失败");
//				return saveUnitStudyVO;
//			}
//			// 2.保存每个单词的学习情况
//			WordStudy lastStudyWord = null;
//			for (WordStudyDto dto : vocDataAfterReview) {
//				WordStudy wordStudy = updateWordStudy(dto);
//				doErrorWord(userId, moduleCode, unitNbr, dto);
//				lastStudyWord = wordStudy;
//			}
//
//			// b.更新学习位置信息
//			/**  单词学习位置  */
////            unitStudy.setStudyPos(lastStudyWord.);
//			/**  学习位置CODE  */
//			unitStudy.setStudyPositionCode(studyToken);
//			/**  单词CODE  */
//			unitStudy.setVocCode(lastStudyWord.getVocCode());
//			/**  单词  */
//			unitStudy.setSpelling(lastStudyWord.getSpell());
//			// 更新当前单元学习情况
//			unitStudyManager.updateUnitWordsStudy(unitStudy);
//			// 更新其他单元为非当前学习单元
//			unitStudyManager.otherUnitNotCurStudyPosition(unitStudy);
//			saveUnitStudyVO.setLatestStudyPosition(fillLastPostion(unitStudy, studyToken, lastStudyWord, seconds4SpellingLetter));
//		} catch (Exception e) {
//			LogTypeEnum.DEFAULT.error(e, "保存单元学习异常");
//			saveUnitStudyVO.setMsg("保存单元学习异常");
//		}
//
//		return saveUnitStudyVO;
	}


	/******* getter and setter ***/
	public SelfWordsStudyManager getSelfWordsStudyManager() {
		return selfWordsStudyManager;
	}

	public void setSelfWordsStudyManager(SelfWordsStudyManager selfWordsStudyManager) {
		this.selfWordsStudyManager = selfWordsStudyManager;
	}

}
