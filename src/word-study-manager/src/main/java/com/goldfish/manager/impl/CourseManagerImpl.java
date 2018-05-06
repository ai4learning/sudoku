package com.goldfish.manager.impl;

import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.domain.Course;
import com.goldfish.dao.CourseDao;
import com.goldfish.manager.CourseManager;

/**
 * @author hellosscat
 * @since 2018-5-2
 * CourseManager实现类
 */
 @Component("courseManager")
public class CourseManagerImpl implements CourseManager {

	@Resource(name="courseDao")
	private CourseDao courseDao;


  public Course addCourse(Course course) {
		return courseDao.addCourse(course);
    }
    
    public void updateCourse(Course course) {
		courseDao.updateCourse(course);
    }
    

    
    public void deleteCourse(Integer id) {
		courseDao.deleteCourse(id);
    }


    public Course getCourseById(Integer id) {
		return courseDao.getCourseById(id);
    }
    
   

   
    
    public List<Course> getAll() {
    	return courseDao.getAll();
    }
    	
    public List<Course> getListByExample(Course course) {
		return courseDao.getListByExample(course);
    }

        public Course getUnique(Course course) {
		return courseDao.getUnique(course);
    }

    
    

    
    public List<Course> getCourseByPage(PageQuery pageQuery) {
		return courseDao.getCourseByPage( pageQuery.getParams());
    }
    	
    public int count(PageQuery pageQuery) {
		return courseDao.count( pageQuery.getParams());
    }

    /******* getter and setter ***/
    
	public CourseDao getCourseDao() {
		return courseDao;
	}

	public void setCourseDao(CourseDao courseDao) {
		this.courseDao = courseDao;
	}
}
