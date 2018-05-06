package com.goldfish.service.impl;



import javax.annotation.Resource;
import java.util.List;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.Course;
import com.goldfish.manager.CourseManager;
import com.goldfish.service.CourseService;


/**
 * @author hellosscat
 * @since 2018-5-2
 *<p>  Courseservice实现</p>
 *
 */
@Service("courseService")
public class CourseServiceImpl implements CourseService {

	private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
	
	@Resource(name="courseManager")
	private CourseManager courseManager;
    
    public CommonResult<Course> addCourse(Course course) {
		CommonResult<Course> result = new CommonResult<Course>();
		try {
			
				course.setCreated(new Date());
			 
			result.addDefaultModel(courseManager.addCourse(course));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("添加 Course失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	public CommonResult<Course> updateCourse(Course course) {
		CommonResult<Course> result = new CommonResult<Course>();
		try {
			
				course.setModified(new Date());
			 
			courseManager.updateCourse(course);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("更新 Course失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
   

	public CommonResult<Course> deleteCourse(Integer id) {
		CommonResult<Course> result = new CommonResult<Course>();
		try {
			courseManager.deleteCourse(id);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("删除 Course失败", e);
			result.setSuccess(false);
		}
		return result;
    }


    	public CommonResult<Course> getCourseById(Integer id) {
		CommonResult<Course> result = new CommonResult<Course>();
		try {
			result.addDefaultModel("course", courseManager.getCourseById(id));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据主键获取 Course失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	

        
	
	public CommonResult<List<Course>> getAll() {
		CommonResult<List<Course>> result = new CommonResult<List<Course>>();
		try {
			List<Course> list = courseManager.getAll();
			result.addDefaultModel("list", list);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("取得所有 Course失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	public CommonResult<List<Course>> getListByExample(Course course) {
		CommonResult<List<Course>> result = new CommonResult<List<Course>>();
		try {
			List<Course> list = courseManager.getListByExample(course);
			result.addDefaultModel("list", list);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("取得 Course失败", e);
			result.setSuccess(false);
		}
		return result;
	}

	public CommonResult<Course> getUnique(Course course) {
		CommonResult<Course> result = new CommonResult<Course>();
		try {
			result.addDefaultModel(courseManager.getUnique(course));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据example获取唯一 Course失败", e);
			result.setSuccess(false);
		}
		return result;
	}

	



	
	public CommonResult<List<Course>> getCourseByPage(PageQuery pageQuery) {
		CommonResult<List<Course>> result = new CommonResult<List<Course>>();
		try {
			int totalCount = this.count(pageQuery);
			if (totalCount > 0) {
				pageQuery.setTotalCount(totalCount);
				List<Course> list = courseManager.getCourseByPage(pageQuery);
				result.addDefaultModel("list", list);
				result.addModel("pageQuery", pageQuery);
			}
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("分页获取 Course失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	public int count(PageQuery pageQuery) {
		return courseManager.count(pageQuery);
	}


	/******* getter and setter ***/
	public CourseManager getCourseManager() {
		return courseManager;
	}

	public void setCourseManager(CourseManager courseManager) {
		this.courseManager = courseManager;
	}

}
