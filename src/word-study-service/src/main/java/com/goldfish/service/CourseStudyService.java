package com.goldfish.service;

import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.CourseStudy;

/**
 * @author hellosscat
 * @since 2018-5-2
 * jshow CourseStudyservice 接口
 *
 */
public interface CourseStudyService {
   
    /**
     * 添加并返回设置id的CourseStudy对象
     * 
     * @param courseStudy
     * @return
     */
    public CommonResult<CourseStudy> addCourseStudy(CourseStudy courseStudy);
    
	/**
     * 更新CourseStudy
     * 
     * @param courseStudy
     */
    public CommonResult<CourseStudy> updateCourseStudy(CourseStudy courseStudy);
    

    

	 /**
     * 根据主键删除CourseStudy
     * 
     * @param id
     */
    public CommonResult<CourseStudy> deleteCourseStudy(Long id);

	/**
     * 根据主键获取CourseStudy
     * 
     * @param id
     * @return
     */	
    public CommonResult<CourseStudy> getCourseStudyById(Long id);

     

    /**
     * 取得所有CourseStudy
     * 
     * @return
     */
    public CommonResult<List<CourseStudy>> getAll();
    
	/**
     * 根据example取得CourseStudy列表
     * 
     * @param  courseStudy
     * @return
     */
    public CommonResult<List<CourseStudy>> getListByExample(CourseStudy courseStudy);
    
	/**
     * 根据example取得唯一的CourseStudy
     * 
     * @param courseStudy
     * @return
     */
    public CommonResult<CourseStudy> getUnique(CourseStudy courseStudy);
    
    


    

	/**
     * 分页取得CourseStudy列表
     * 
     * @param pageQuery
     * @return
     */
    public CommonResult<List<CourseStudy>> getCourseStudyByPage(PageQuery pageQuery);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param pageQuery
     * @return
     */
    public int count(PageQuery pageQuery);
	
	
}
