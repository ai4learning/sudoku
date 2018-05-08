package com.goldfish.service.impl;



import javax.annotation.Resource;
import java.util.List;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.UnitWordsStudy;
import com.goldfish.manager.UnitWordsStudyManager;
import com.goldfish.service.UnitWordsStudyService;


/**
 * @author hellosscat
 * @since 2018-5-8
 *<p>  单元单词学习service实现</p>
 *
 */
@Service("unitWordsStudyService")
public class UnitWordsStudyServiceImpl implements UnitWordsStudyService {

	private static final Logger logger = Logger.getLogger(UnitWordsStudyServiceImpl.class);
	
	@Resource(name="unitWordsStudyManager")
	private UnitWordsStudyManager unitWordsStudyManager;
    
    public CommonResult<UnitWordsStudy> addUnitWordsStudy(UnitWordsStudy unitWordsStudy) {
		CommonResult<UnitWordsStudy> result = new CommonResult<UnitWordsStudy>();
		try{
			
			unitWordsStudy.setCreated(new Date());
			
			
			unitWordsStudy.setModified(new Date());
			
			result.addDefaultModel(unitWordsStudyManager.addUnitWordsStudy(unitWordsStudy));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("添加 单元单词学习失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	public CommonResult<UnitWordsStudy> updateUnitWordsStudy(UnitWordsStudy unitWordsStudy) {
		CommonResult<UnitWordsStudy> result = new CommonResult<UnitWordsStudy>();
		try {
			
					unitWordsStudy.setModified(new Date());
					
			unitWordsStudyManager.updateUnitWordsStudy(unitWordsStudy);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("更新 单元单词学习失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
   

	public CommonResult<UnitWordsStudy> deleteUnitWordsStudy(Long id) {
		CommonResult<UnitWordsStudy> result = new CommonResult<UnitWordsStudy>();
		try {
			unitWordsStudyManager.deleteUnitWordsStudy(id);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("删除 单元单词学习失败", e);
			result.setSuccess(false);
		}
		return result;
    }


    	public CommonResult<UnitWordsStudy> getUnitWordsStudyById(Long id) {
		CommonResult<UnitWordsStudy> result = new CommonResult<UnitWordsStudy>();
		try {
			result.addDefaultModel("unitWordsStudy", unitWordsStudyManager.getUnitWordsStudyById(id));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据主键获取 单元单词学习失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	

        
	


	public CommonResult<UnitWordsStudy> getUnique(UnitWordsStudy unitWordsStudy) {
		CommonResult<UnitWordsStudy> result = new CommonResult<UnitWordsStudy>();
		try {
			result.addDefaultModel(unitWordsStudyManager.getUnique(unitWordsStudy));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据example获取唯一 单元单词学习失败", e);
			result.setSuccess(false);
		}
		return result;
	}


	public CommonResult<List<UnitWordsStudy>> getListByExample(UnitWordsStudy unitWordsStudy) {
		CommonResult<List<UnitWordsStudy>> result = new CommonResult<List<UnitWordsStudy>>();
		try {
			List<UnitWordsStudy> list = unitWordsStudyManager.getListByExample(unitWordsStudy);
			result.addDefaultModel("list", list);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("取得 单元单词学习失败", e);
			result.setSuccess(false);
		}
		return result;
	}

	
	public CommonResult<List<UnitWordsStudy>> getUnitWordsStudyByPage(PageQuery pageQuery) {
		CommonResult<List<UnitWordsStudy>> result = new CommonResult<List<UnitWordsStudy>>();
		try {
			int totalCount = this.count(pageQuery);
			if (totalCount > 0) {
				pageQuery.setTotalCount(totalCount);
				List<UnitWordsStudy> list = unitWordsStudyManager.getUnitWordsStudyByPage(pageQuery);
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
		return unitWordsStudyManager.count(pageQuery);
	}


	/******* getter and setter ***/
	public UnitWordsStudyManager getUnitWordsStudyManager() {
		return unitWordsStudyManager;
	}

	public void setUnitWordsStudyManager(UnitWordsStudyManager unitWordsStudyManager) {
		this.unitWordsStudyManager = unitWordsStudyManager;
	}

}
