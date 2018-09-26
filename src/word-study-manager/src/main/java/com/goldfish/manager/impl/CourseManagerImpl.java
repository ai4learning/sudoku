package com.goldfish.manager.impl;

import com.goldfish.dao.cache.redis.RedisUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

import com.goldfish.common.PageQuery;
import com.goldfish.domain.Course;
import com.goldfish.dao.CourseDao;
import com.goldfish.manager.CourseManager;

/**
 * @author hellosscat
 * @since 2018-5-8
 * 课程Manager实现类
 */
@Component("courseManager")
public class CourseManagerImpl implements CourseManager {

    @Resource(name = "courseDao")
    private CourseDao courseDao;

    @Resource(name = "redisUtils")
    private RedisUtils redisUtils;


    @Override
    public Course addCourse(Course course) {
        int i = courseDao.addCourse(course);
        redisUtils.setObject(course.getClass().getSimpleName() + ":" + course.getId(), course);
        return course;
    }

    @Override
    public void updateCourse(Course course) {
        redisUtils.setObject(course.getClass().getSimpleName() + ":" + course.getId(), course);
        courseDao.updateCourse(course);
    }


    @Override
    public void deleteCourse(Integer id) {
        redisUtils.deleteByKey(Course.class.getSimpleName() + ":" + id);
        courseDao.deleteCourse(id);
    }


    @Override
    public Course getCourseById(Integer id) {
        Course course = redisUtils.getObject(Course.class.getSimpleName() + ":" + id, Course.class);
        if (course == null) {
            return courseDao.getCourseById(id);
        }
        return course;
    }


    @Override
    public Course getUnique(Course course) {
        return courseDao.getUnique(course);
    }


    @Override
    public List<Course> getListByExample(Course course) {
        return courseDao.getListByExample(course);
    }


    @Override
    public List<Course> getCourseByPage(PageQuery pageQuery) {
        return courseDao.getCourseByPage(pageQuery.getParams());
    }

    @Override
    public int count(PageQuery pageQuery) {
        return courseDao.count(pageQuery.getParams());
    }

    /******* getter and setter ***/

    public CourseDao getCourseDao() {
        return courseDao;
    }

    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }
}
