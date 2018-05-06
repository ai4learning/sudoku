package com.goldfish.service.impl;



import javax.annotation.Resource;
import java.util.List;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.Unit;
import com.goldfish.manager.UnitManager;
import com.goldfish.service.UnitService;


/**
 * @author hellosscat
 * @since 2018-5-2
 *<p>  Unitservice实现</p>
 *
 */
@Service("unitService")
public class UnitServiceImpl implements UnitService {

	private static final Logger logger = LoggerFactory.getLogger(UnitServiceImpl.class);
	
	@Resource(name="unitManager")
	private UnitManager unitManager;
    
    public CommonResult<Unit> addUnit(Unit unit) {
		CommonResult<Unit> result = new CommonResult<Unit>();
		try {
			
				unit.setCreated(new Date());
			 
			result.addDefaultModel(unitManager.addUnit(unit));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("添加 Unit失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	public CommonResult<Unit> updateUnit(Unit unit) {
		CommonResult<Unit> result = new CommonResult<Unit>();
		try {
			
				unit.setModified(new Date());
			 
			unitManager.updateUnit(unit);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("更新 Unit失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
   

	public CommonResult<Unit> deleteUnit(Long id) {
		CommonResult<Unit> result = new CommonResult<Unit>();
		try {
			unitManager.deleteUnit(id);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("删除 Unit失败", e);
			result.setSuccess(false);
		}
		return result;
    }


    	public CommonResult<Unit> getUnitById(Long id) {
		CommonResult<Unit> result = new CommonResult<Unit>();
		try {
			result.addDefaultModel("unit", unitManager.getUnitById(id));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据主键获取 Unit失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	

        
	
	public CommonResult<List<Unit>> getAll() {
		CommonResult<List<Unit>> result = new CommonResult<List<Unit>>();
		try {
			List<Unit> list = unitManager.getAll();
			result.addDefaultModel("list", list);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("取得所有 Unit失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	public CommonResult<List<Unit>> getListByExample(Unit unit) {
		CommonResult<List<Unit>> result = new CommonResult<List<Unit>>();
		try {
			List<Unit> list = unitManager.getListByExample(unit);
			result.addDefaultModel("list", list);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("取得 Unit失败", e);
			result.setSuccess(false);
		}
		return result;
	}

	public CommonResult<Unit> getUnique(Unit unit) {
		CommonResult<Unit> result = new CommonResult<Unit>();
		try {
			result.addDefaultModel(unitManager.getUnique(unit));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据example获取唯一 Unit失败", e);
			result.setSuccess(false);
		}
		return result;
	}

	



	
	public CommonResult<List<Unit>> getUnitByPage(PageQuery pageQuery) {
		CommonResult<List<Unit>> result = new CommonResult<List<Unit>>();
		try {
			int totalCount = this.count(pageQuery);
			if (totalCount > 0) {
				pageQuery.setTotalCount(totalCount);
				List<Unit> list = unitManager.getUnitByPage(pageQuery);
				result.addDefaultModel("list", list);
				result.addModel("pageQuery", pageQuery);
			}
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("分页获取 Unit失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	public int count(PageQuery pageQuery) {
		return unitManager.count(pageQuery);
	}


	/******* getter and setter ***/
	public UnitManager getUnitManager() {
		return unitManager;
	}

	public void setUnitManager(UnitManager unitManager) {
		this.unitManager = unitManager;
	}

}
