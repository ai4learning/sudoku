package com.goldfish.service.impl;



import javax.annotation.Resource;
import java.util.List;
import java.util.Date;

import com.goldfish.domain.UnitStudy;
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

}
