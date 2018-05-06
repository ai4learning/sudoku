package com.goldfish.service.impl;



import javax.annotation.Resource;
import java.util.List;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.UnitWords;
import com.goldfish.manager.UnitWordsManager;
import com.goldfish.service.UnitWordsService;


/**
 * @author hellosscat
 * @since 2018-5-2
 *<p>  UnitWordsservice实现</p>
 *
 */
@Service("unitWordsService")
public class UnitWordsServiceImpl implements UnitWordsService {

	private static final Logger logger = LoggerFactory.getLogger(UnitWordsServiceImpl.class);
	
	@Resource(name="unitWordsManager")
	private UnitWordsManager unitWordsManager;
    
    public CommonResult<UnitWords> addUnitWords(UnitWords unitWords) {
		CommonResult<UnitWords> result = new CommonResult<UnitWords>();
		try {
			
				unitWords.setCreated(new Date());
			 
			result.addDefaultModel(unitWordsManager.addUnitWords(unitWords));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("添加 UnitWords失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	public CommonResult<UnitWords> updateUnitWords(UnitWords unitWords) {
		CommonResult<UnitWords> result = new CommonResult<UnitWords>();
		try {
			
				unitWords.setModified(new Date());
			 
			unitWordsManager.updateUnitWords(unitWords);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("更新 UnitWords失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
   

	public CommonResult<UnitWords> deleteUnitWords(Long id) {
		CommonResult<UnitWords> result = new CommonResult<UnitWords>();
		try {
			unitWordsManager.deleteUnitWords(id);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("删除 UnitWords失败", e);
			result.setSuccess(false);
		}
		return result;
    }


    	public CommonResult<UnitWords> getUnitWordsById(Long id) {
		CommonResult<UnitWords> result = new CommonResult<UnitWords>();
		try {
			result.addDefaultModel("unitWords", unitWordsManager.getUnitWordsById(id));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据主键获取 UnitWords失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	

        
	
	public CommonResult<List<UnitWords>> getAll() {
		CommonResult<List<UnitWords>> result = new CommonResult<List<UnitWords>>();
		try {
			List<UnitWords> list = unitWordsManager.getAll();
			result.addDefaultModel("list", list);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("取得所有 UnitWords失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	public CommonResult<List<UnitWords>> getListByExample(UnitWords unitWords) {
		CommonResult<List<UnitWords>> result = new CommonResult<List<UnitWords>>();
		try {
			List<UnitWords> list = unitWordsManager.getListByExample(unitWords);
			result.addDefaultModel("list", list);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("取得 UnitWords失败", e);
			result.setSuccess(false);
		}
		return result;
	}

	public CommonResult<UnitWords> getUnique(UnitWords unitWords) {
		CommonResult<UnitWords> result = new CommonResult<UnitWords>();
		try {
			result.addDefaultModel(unitWordsManager.getUnique(unitWords));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据example获取唯一 UnitWords失败", e);
			result.setSuccess(false);
		}
		return result;
	}

	



	
	public CommonResult<List<UnitWords>> getUnitWordsByPage(PageQuery pageQuery) {
		CommonResult<List<UnitWords>> result = new CommonResult<List<UnitWords>>();
		try {
			int totalCount = this.count(pageQuery);
			if (totalCount > 0) {
				pageQuery.setTotalCount(totalCount);
				List<UnitWords> list = unitWordsManager.getUnitWordsByPage(pageQuery);
				result.addDefaultModel("list", list);
				result.addModel("pageQuery", pageQuery);
			}
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("分页获取 UnitWords失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	public int count(PageQuery pageQuery) {
		return unitWordsManager.count(pageQuery);
	}


	/******* getter and setter ***/
	public UnitWordsManager getUnitWordsManager() {
		return unitWordsManager;
	}

	public void setUnitWordsManager(UnitWordsManager unitWordsManager) {
		this.unitWordsManager = unitWordsManager;
	}

}
