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
 * @since 2018-5-8
 * 课程学习Manager实现类
 */
 @Component("courseStudyManager")
public class CourseStudyManagerImpl implements CourseStudyManager {

	@Resource(name="courseStudyDao")
	private CourseStudyDao courseStudyDao;


  @Override
  public CourseStudy addCourseStudy(CourseStudy courseStudy) {
		int i=courseStudyDao.addCourseStudy(courseStudy);
		return courseStudy;
    }
    
    @Override
    public void updateCourseStudy(CourseStudy courseStudy) {
		courseStudyDao.updateCourseStudy(courseStudy);
    }
    

    
    @Override
    public void deleteCourseStudy(Long id) {
		courseStudyDao.deleteCourseStudy(id);
    }


    @Override
    public CourseStudy getCourseStudyById(Long id) {
		return courseStudyDao.getCourseStudyById(id);
    }
    
   


    	
   
   @Override
   public CourseStudy getUnique(CourseStudy courseStudy) {
		return courseStudyDao.getUnique(courseStudy);
    }

    
 @Override
 public List<CourseStudy> getListByExample(CourseStudy courseStudy) {
    return courseStudyDao.getListByExample(courseStudy);
    }

    
    @Override
    public List<CourseStudy> getCourseStudyByPage(PageQuery pageQuery) {
		return courseStudyDao.getCourseStudyByPage( pageQuery.getParams());
    }
    	
    @Override
    public int count(PageQuery pageQuery) {
		return courseStudyDao.count( pageQuery.getParams());
    }

    @Override
    public void deleteCourseStudyByCondition(CourseStudy courseStudy) {
        courseStudyDao.deleteCourseStudyByCondition(courseStudy);
    }

    /******* getter and setter ***/
    
	public CourseStudyDao getCourseStudyDao() {
		return courseStudyDao;
	}

	public void setCourseStudyDao(CourseStudyDao courseStudyDao) {
		this.courseStudyDao = courseStudyDao;
	}
}
