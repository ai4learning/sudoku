/**
 * Copyright(c) 2004-2018 bianfeng
 */


 package com.goldfish.manager;

import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.domain.Course;

/**
 * @author hellosscat
 * @since 2018-5-8
 * 课程Manager接口类
 */
public interface CourseManager {
 
   /**
     * 添加并返回设置id的Course对象
     * 
     * @param course
     * @return
     */
    public Course addCourse(Course course);
    
	/**
     * 更新Course
     * 
     * @param course
     */
    public void updateCourse(Course course);
    
    

	 /**
     * 根据主键删除Course
     * 
     * @param id
     */
    public void deleteCourse(Integer id);

    	/**
     * 根据主键获取Course
     * 
     * @param id
     * @return
     */	
    public Course getCourseById(Integer id);

    



    
        
	/**
     * 根据example取得唯一的Course
     * 
     * @param course
     * @return
     */
    public Course getUnique(Course course);
    

    
    /**
     * 根据example取得Course列表
     * 
     * @param  course
     * @return
     */
    public List<Course> getListByExample(Course course);

	/**
     * 分页取得Course列表
     * 
     * @param pageQuery
     * @return
     */
    public List<Course> getCourseByPage(PageQuery pageQuery);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param pageQuery
     * @return
     */
    public int count(PageQuery pageQuery);

    public List<Course> getCourseLikeBookName(String bookNamePattern);
}
