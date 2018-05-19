package com.goldfish.service.impl;



import javax.annotation.Resource;
import java.util.List;
import java.util.Date;

import com.goldfish.constant.State;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.CourseStudy;
import com.goldfish.manager.CourseStudyManager;
import com.goldfish.service.CourseStudyService;


/**
 * @author hellosscat
 * @since 2018-5-8
 *<p>  课程学习service实现</p>
 *
 */
@Service("courseStudyService")
public class CourseStudyServiceImpl implements CourseStudyService {

	private static final Logger logger = Logger.getLogger(CourseStudyServiceImpl.class);
	
	@Resource(name="courseStudyManager")
	private CourseStudyManager courseStudyManager;
    
    public CommonResult<CourseStudy> addCourseStudy(CourseStudy courseStudy) {
		CommonResult<CourseStudy> result = new CommonResult<CourseStudy>();
		try{

			courseStudy.setCreated(new Date());
			courseStudy.setModified(new Date());
			result.addDefaultModel(courseStudyManager.addCourseStudy(courseStudy));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("添加 课程学习失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	public CommonResult<CourseStudy> updateCourseStudy(CourseStudy courseStudy) {
		CommonResult<CourseStudy> result = new CommonResult<CourseStudy>();
		try {
			
					courseStudy.setModified(new Date());
					
			courseStudyManager.updateCourseStudy(courseStudy);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("更新 课程学习失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
   

	public CommonResult<CourseStudy> deleteCourseStudy(Long id) {
		CommonResult<CourseStudy> result = new CommonResult<CourseStudy>();
		try {
			courseStudyManager.deleteCourseStudy(id);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("删除 课程学习失败", e);
			result.setSuccess(false);
		}
		return result;
    }


    	public CommonResult<CourseStudy> getCourseStudyById(Long id) {
		CommonResult<CourseStudy> result = new CommonResult<CourseStudy>();
		try {
			result.addDefaultModel("courseStudy", courseStudyManager.getCourseStudyById(id));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据主键获取 课程学习失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	

        
	


	public CommonResult<CourseStudy> getUnique(CourseStudy courseStudy) {
		CommonResult<CourseStudy> result = new CommonResult<CourseStudy>();
		try {
			result.addDefaultModel(courseStudyManager.getUnique(courseStudy));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据example获取唯一 课程学习失败", e);
			result.setSuccess(false);
		}
		return result;
	}


	public CommonResult<List<CourseStudy>> getListByExample(CourseStudy courseStudy) {
		CommonResult<List<CourseStudy>> result = new CommonResult<List<CourseStudy>>();
		try {
			List<CourseStudy> list = courseStudyManager.getListByExample(courseStudy);
			result.addDefaultModel("list", list);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("取得 课程学习失败", e);
			result.setSuccess(false);
		}
		return result;
	}

	
	public CommonResult<List<CourseStudy>> getCourseStudyByPage(PageQuery pageQuery) {
		CommonResult<List<CourseStudy>> result = new CommonResult<List<CourseStudy>>();
		try {
			int totalCount = this.count(pageQuery);
			if (totalCount > 0) {
				pageQuery.setTotalCount(totalCount);
				List<CourseStudy> list = courseStudyManager.getCourseStudyByPage(pageQuery);
				result.addDefaultModel("list", list);
				result.addModel("pageQuery", pageQuery);
			}
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("分页获取 课程学习失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	public int count(PageQuery pageQuery) {
		return courseStudyManager.count(pageQuery);
	}


	/******* getter and setter ***/
	public CourseStudyManager getCourseStudyManager() {
		return courseStudyManager;
	}

	public void setCourseStudyManager(CourseStudyManager courseStudyManager) {
		this.courseStudyManager = courseStudyManager;
	}

}
