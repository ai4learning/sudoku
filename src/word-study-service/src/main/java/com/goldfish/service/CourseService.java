package com.goldfish.service;

import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.Course;

/**
 * @author hellosscat
 * @since 2018-5-8
 * jshow 课程service 接口
 *
 */
public interface CourseService {
   
    /**
     * 添加并返回设置id的Course对象
     * 
     * @param course
     * @return
     */
    public CommonResult<Course> addCourse(Course course);
    
	/**
     * 更新Course
     * 
     * @param course
     */
    public CommonResult<Course> updateCourse(Course course);
    

    

	 /**
     * 根据主键删除Course
     * 
     * @param id
     */
    public CommonResult<Course> deleteCourse(Integer id);

	/**
     * 根据主键获取Course
     * 
     * @param id
     * @return
     */	
    public CommonResult<Course> getCourseById(Integer id);

     


	
    
	/**
     * 根据example取得唯一的Course
     * 
     * @param course
     * @return
     */
    public CommonResult<Course> getUnique(Course course);
    



    /**
     * 根据example取得Course列表
     * 
     * @param  course
     * @return
     */
    public CommonResult<List<Course>> getListByExample(Course course);
    

	/**
     * 分页取得Course列表
     * 
     * @param pageQuery
     * @return
     */
    public CommonResult<List<Course>> getCourseByPage(PageQuery pageQuery);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param pageQuery
     * @return
     */
    public int count(PageQuery pageQuery);

    public CommonResult<List<Course>> getCourseLikeBookName(String bookNamePattern);
}
