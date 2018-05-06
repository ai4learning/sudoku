package com.goldfish.manager.impl;

import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.domain.CourseStudy;
import com.goldfish.dao.CourseStudyDao;
import com.goldfish.manager.CourseStudyManager;

/**
 * @author hellosscat
 * @since 2018-5-2
 * CourseStudyManager实现类
 */
 @Component("courseStudyManager")
public class CourseStudyManagerImpl implements CourseStudyManager {

	@Resource(name="courseStudyDao")
	private CourseStudyDao courseStudyDao;


  public CourseStudy addCourseStudy(CourseStudy courseStudy) {
		return courseStudyDao.addCourseStudy(courseStudy);
    }
    
    public void updateCourseStudy(CourseStudy courseStudy) {
		courseStudyDao.updateCourseStudy(courseStudy);
    }
    

    
    public void deleteCourseStudy(Long id) {
		courseStudyDao.deleteCourseStudy(id);
    }


    public CourseStudy getCourseStudyById(Long id) {
		return courseStudyDao.getCourseStudyById(id);
    }
    
   

   
    
    public List<CourseStudy> getAll() {
    	return courseStudyDao.getAll();
    }
    	
    public List<CourseStudy> getListByExample(CourseStudy courseStudy) {
		return courseStudyDao.getListByExample(courseStudy);
    }

        public CourseStudy getUnique(CourseStudy courseStudy) {
		return courseStudyDao.getUnique(courseStudy);
    }

    
    

    
    public List<CourseStudy> getCourseStudyByPage(PageQuery pageQuery) {
		return courseStudyDao.getCourseStudyByPage( pageQuery.getParams());
    }
    	
    public int count(PageQuery pageQuery) {
		return courseStudyDao.count( pageQuery.getParams());
    }

    /******* getter and setter ***/
    
	public CourseStudyDao getCourseStudyDao() {
		return courseStudyDao;
	}

	public void setCourseStudyDao(CourseStudyDao courseStudyDao) {
		this.courseStudyDao = courseStudyDao;
	}
}
