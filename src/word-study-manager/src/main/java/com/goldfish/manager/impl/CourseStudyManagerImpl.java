package com.goldfish.manager.impl;

import com.goldfish.dao.cache.redis.RedisUtils;
import com.goldfish.domain.Course;
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

    @Resource(name = "courseStudyDao")
    private CourseStudyDao courseStudyDao;

    @Resource(name = "redisUtils")
    private RedisUtils redisUtils;

    @Override
    public CourseStudy addCourseStudy(CourseStudy courseStudy) {
        int i = courseStudyDao.addCourseStudy(courseStudy);
        redisUtils.setObject(courseStudy.getClass().getSimpleName() + ":" + courseStudy.getId(), courseStudy);
        return courseStudy;
    }

    @Override
    public void updateCourseStudy(CourseStudy courseStudy) {
        redisUtils.setObject(courseStudy.getClass().getSimpleName() + ":" + courseStudy.getId(), courseStudy);
        courseStudyDao.updateCourseStudy(courseStudy);
    }


    @Override
    public void deleteCourseStudy(Long id) {
        redisUtils.deleteByKey(CourseStudy.class.getSimpleName() + ":" + id);
        courseStudyDao.deleteCourseStudy(id);
    }


    @Override
    public CourseStudy getCourseStudyById(Long id) {
        CourseStudy courseStudy = redisUtils.getObject(CourseStudy.class.getSimpleName() + ":" + id, CourseStudy.class);
        if (courseStudy == null) {
            return courseStudyDao.getCourseStudyById(id);
        }
        return courseStudy;
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
        return courseStudyDao.getCourseStudyByPage(pageQuery.getParams());
    }

    @Override
    public int count(PageQuery pageQuery) {
        return courseStudyDao.count(pageQuery.getParams());
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
