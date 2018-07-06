package com.goldfish.service.impl;



import javax.annotation.Resource;
import java.util.List;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.UnitWords;
import com.goldfish.manager.UnitWordsManager;
import com.goldfish.service.UnitWordsService;


/**
 * @author hellosscat
 * @since 2018-5-8
 *<p>  单元单词service实现</p>
 *
 */
@Service("unitWordsService")
public class UnitWordsServiceImpl implements UnitWordsService {

	private static final Logger logger = Logger.getLogger(UnitWordsServiceImpl.class);
	
	@Resource(name="unitWordsManager")
	private UnitWordsManager unitWordsManager;
    
    @Override
    public CommonResult<UnitWords> addUnitWords(UnitWords unitWords) {
		CommonResult<UnitWords> result = new CommonResult<UnitWords>();
		try{
			
			unitWords.setCreated(new Date());
			
			
			unitWords.setModified(new Date());
			
			result.addDefaultModel(unitWordsManager.addUnitWords(unitWords));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("添加 单元单词失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	@Override
    public CommonResult<UnitWords> updateUnitWords(UnitWords unitWords) {
		CommonResult<UnitWords> result = new CommonResult<UnitWords>();
		try {
			
					unitWords.setModified(new Date());
					
			unitWordsManager.updateUnitWords(unitWords);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("更新 单元单词失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
   

	@Override
    public CommonResult<UnitWords> deleteUnitWords(Long id) {
		CommonResult<UnitWords> result = new CommonResult<UnitWords>();
		try {
			unitWordsManager.deleteUnitWords(id);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("删除 单元单词失败", e);
			result.setSuccess(false);
		}
		return result;
    }


    	@Override
        public CommonResult<UnitWords> getUnitWordsById(Long id) {
		CommonResult<UnitWords> result = new CommonResult<UnitWords>();
		try {
			result.addDefaultModel("unitWords", unitWordsManager.getUnitWordsById(id));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据主键获取 单元单词失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	

        
	


	@Override
    public CommonResult<UnitWords> getUnique(UnitWords unitWords) {
		CommonResult<UnitWords> result = new CommonResult<UnitWords>();
		try {
			result.addDefaultModel(unitWordsManager.getUnique(unitWords));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据example获取唯一 单元单词失败", e);
			result.setSuccess(false);
		}
		return result;
	}


	@Override
    public CommonResult<List<UnitWords>> getListByExample(UnitWords unitWords) {
		CommonResult<List<UnitWords>> result = new CommonResult<List<UnitWords>>();
		try {
			List<UnitWords> list = unitWordsManager.getListByExample(unitWords);
			result.addDefaultModel("list", list);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("取得 单元单词失败", e);
			result.setSuccess(false);
		}
		return result;
	}

	
	@Override
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
			logger.error("分页获取 单元单词失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	@Override
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
