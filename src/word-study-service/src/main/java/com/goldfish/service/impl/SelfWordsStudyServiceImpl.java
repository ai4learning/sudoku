package com.goldfish.service.impl;



import javax.annotation.Resource;
import java.util.List;
import java.util.Date;

import com.goldfish.common.log.LogTypeEnum;
import com.goldfish.constant.FinishState;
import com.goldfish.constant.State;
import com.goldfish.constant.StudyPhase;
import com.goldfish.constant.WordLibType;
import com.goldfish.domain.UnitStudy;
import com.goldfish.domain.WordStudy;
import com.goldfish.manager.SelfWordsManager;
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
public class SelfWordsStudyServiceImpl extends UnitStudyServiceImpl implements SelfWordsStudyService {

	private static final Logger logger = Logger.getLogger(SelfWordsStudyServiceImpl.class);
	
	@Resource(name="selfWordsStudyManager")
	private SelfWordsStudyManager selfWordsStudyManager;
	@Resource(name="selfWordsManager")
	private SelfWordsManager selfWordsManager;
    
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
		SaveUnitStudyVO saveUnitStudyVO = new SaveUnitStudyVO();
		// 更改单词学习状态
		// 1.保存单元学习记录
		SelfWordsStudy errorBookStudy = getErrorBook(userId, unitNbr, saveUnitStudyVO);
		if (errorBookStudy == null) {
			return saveUnitStudyVO;
		}
		try{
			// a.更新学习时间等字段
			errorBookStudy.setTotalReadingTime(totalReadingTime);
			errorBookStudy.setTotalWritingTime(totalWritingTime);
			errorBookStudy.setTotalNumber(vocDataAfterReview.size());
			if ("finish".equals(extra)) {
				/**  是否学习完成  */
				errorBookStudy.setIsFinished(FinishState.COMPLETE.getState());
				// 单元学习完毕，则到单元测试阶段
				errorBookStudy.setCurrentPhase(StudyPhase.UNIT_TEST.getPhase());
			}
			CommonResult<SelfWordsStudy> updateErrorBookStudyResult = this.updateSelfWordsStudy(errorBookStudy);
			if (updateErrorBookStudyResult == null || !updateErrorBookStudyResult.isSuccess()) {
				LogTypeEnum.DEFAULT.error("更新学生错词本失败");
				saveUnitStudyVO.setMsg("更新错词本学习失败");
				return saveUnitStudyVO;
			}
			// 2.保存每个单词的学习情况
			boolean needDelete = !"dontdelete".equals(extra);
			for (WordStudyDto dto : vocDataAfterReview) {
				this.updateWordStudy(dto, userId);
				// 确定是否需要删除错词
				if (needDelete && dto.isRemember()) {
					selfWordsManager.deleteByVocCode(dto.getVocCode(), WordLibType.ERROR_BOOK.getType(),userId);
				}
			}
			saveUnitStudyVO.setSuccess(true);
		} catch (Exception e) {
			LogTypeEnum.DEFAULT.error(e, "保存错词学习异常");
			saveUnitStudyVO.setMsg("保存错词学习异常");
		}
		return saveUnitStudyVO;
	}

	private SelfWordsStudy getErrorBook(Integer userId, Integer unitNbr, SaveUnitStudyVO vo) {
		SelfWordsStudy errWordStudyQuery = new SelfWordsStudy();
		errWordStudyQuery.setStudentId(userId);
		errWordStudyQuery.setUnitNbr(unitNbr);
		errWordStudyQuery.setState(State.VALID.getState());
		errWordStudyQuery.setType(WordLibType.ERROR_BOOK.getType());

		CommonResult<SelfWordsStudy> errStudyResult = this.getUnique(errWordStudyQuery);
		if (errStudyResult == null || !errStudyResult.isSuccess()) {
			LogTypeEnum.DEFAULT.error("查询错词本学习失败");
			vo.setMsg("查询错词本学习失败");
			return null;
		}
		SelfWordsStudy errWordStudy = errStudyResult.getDefaultModel();
		// 不存在，则插入
		if (errWordStudy == null) {
			errWordStudy = errWordStudyQuery;
			errWordStudy.setCurrentPhase(StudyPhase.ENHANCE_STUDY.getPhase());
			Date cur = new Date();
			errWordStudy.setCreated(cur);
			errWordStudy.setModified(cur);
			selfWordsStudyManager.addSelfWordsStudy(errWordStudy);
		}
		return errWordStudy;
	}


	/******* getter and setter ***/
	public SelfWordsStudyManager getSelfWordsStudyManager() {
		return selfWordsStudyManager;
	}

	public void setSelfWordsStudyManager(SelfWordsStudyManager selfWordsStudyManager) {
		this.selfWordsStudyManager = selfWordsStudyManager;
	}

}
