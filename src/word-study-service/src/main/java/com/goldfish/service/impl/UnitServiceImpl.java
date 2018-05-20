package com.goldfish.service.impl;



import javax.annotation.Resource;
import java.util.*;

import com.goldfish.dao.cache.local.CourseContext;
import com.goldfish.domain.Course;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.Unit;
import com.goldfish.manager.UnitManager;
import com.goldfish.service.UnitService;


/**
 * @author hellosscat
 * @since 2018-5-8
 *<p>  课程单元service实现</p>
 *
 */
@Service("unitService")
public class UnitServiceImpl implements UnitService {

	private static final Logger logger = Logger.getLogger(UnitServiceImpl.class);
	
	@Resource(name="unitManager")
	private UnitManager unitManager;
	@Resource(name="courseContext")
	private CourseContext courseContext;

//	@Resource(name="courseManager")
//	private CourseManager courseManager;
    
    public CommonResult<Unit> addUnit(Unit unit) {
		CommonResult<Unit> result = new CommonResult<Unit>();
		try{
			
			unit.setCreated(new Date());
			
			
			unit.setModified(new Date());
			
			result.addDefaultModel(unitManager.addUnit(unit));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("添加 课程单元失败", e);
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
			logger.error("更新 课程单元失败", e);
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
			logger.error("删除 课程单元失败", e);
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
			logger.error("根据主键获取 课程单元失败", e);
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
			logger.error("根据example获取唯一 课程单元失败", e);
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
			logger.error("取得 课程单元失败", e);
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
				list = setLessonInfo(result, list);
				result.addDefaultModel("list", list);
				result.addModel("pageQuery", pageQuery);
			}
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("分页获取 课程单元失败", e);
			result.setSuccess(false);
		}
		return result;
	}

	private List<Unit> setLessonInfo(CommonResult<List<Unit>> result, List<Unit> origin) {
		if (origin == null && origin.size() == 0) {
			return origin;
		}
		List<Unit> target = new ArrayList<Unit>(origin.size());
		Course query = new Course();
		for (Unit ele : origin) {
			ele.setLessonName(courseContext.getName(Integer.valueOf(String.valueOf(ele.getLessonId()))));
			target.add(ele);
		}
		return target;
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
