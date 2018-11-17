package com.goldfish.service.impl;



import javax.annotation.Resource;
import java.util.List;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.Course;
import com.goldfish.manager.CourseManager;
import com.goldfish.service.CourseService;


/**
 * @author hellosscat
 * @since 2018-5-8
 *<p>  课程service实现</p>
 *
 */
@Service("courseService")
public class CourseServiceImpl implements CourseService {

	private static final Logger logger = Logger.getLogger(CourseServiceImpl.class);
	
	@Resource(name="courseManager")
	private CourseManager courseManager;
    
    @Override
    public CommonResult<Course> addCourse(Course course) {
		CommonResult<Course> result = new CommonResult<Course>();
		try{
			
			course.setCreated(new Date());
			
			
			course.setModified(new Date());
			
			result.addDefaultModel(courseManager.addCourse(course));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("添加 课程失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	@Override
    public CommonResult<Course> updateCourse(Course course) {
		CommonResult<Course> result = new CommonResult<Course>();
		try {
			
					course.setModified(new Date());
					
			courseManager.updateCourse(course);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("更新 课程失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
   

	@Override
    public CommonResult<Course> deleteCourse(Integer id) {
		CommonResult<Course> result = new CommonResult<Course>();
		try {
			courseManager.deleteCourse(id);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("删除 课程失败", e);
			result.setSuccess(false);
		}
		return result;
    }


    	@Override
        public CommonResult<Course> getCourseById(Integer id) {
		CommonResult<Course> result = new CommonResult<Course>();
		try {
			result.addDefaultModel("course", courseManager.getCourseById(id));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据主键获取 课程失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	

        
	


	@Override
    public CommonResult<Course> getUnique(Course course) {
		CommonResult<Course> result = new CommonResult<Course>();
		try {
			result.addDefaultModel(courseManager.getUnique(course));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据example获取唯一 课程失败", e);
			result.setSuccess(false);
		}
		return result;
	}


	@Override
    public CommonResult<List<Course>> getListByExample(Course course) {
		CommonResult<List<Course>> result = new CommonResult<List<Course>>();
		try {
			List<Course> list = courseManager.getListByExample(course);
			result.addDefaultModel("list", list);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("取得 课程失败", e);
			result.setSuccess(false);
		}
		return result;
	}

	
	@Override
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
			logger.error("分页获取 课程失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	@Override
    public int count(PageQuery pageQuery) {
		return courseManager.count(pageQuery);
	}

    @Override
    public CommonResult<List<Course>> getCourseLikeBookName(String bookNamePattern) {
        CommonResult<List<Course>> result = new CommonResult<List<Course>>();
        try {
            List<Course> list = courseManager.getCourseLikeBookName(bookNamePattern);
            result.addDefaultModel("list", list);
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error("取得 课程失败", e);
            result.setSuccess(false);
        }
        return result;
    }


    /******* getter and setter ***/
	public CourseManager getCourseManager() {
		return courseManager;
	}

	public void setCourseManager(CourseManager courseManager) {
		this.courseManager = courseManager;
	}

}
