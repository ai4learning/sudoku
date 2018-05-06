
/**
 * Copyright(c) 2013-  www.jd.com
 *
 */
 package com.goldfish.dao;

import java.util.List;
import com.goldfish.domain.CourseStudy;
import java.util.Map;
/**
 * @author hellosscat
 * @since 2018-5-2
 * CourseStudy Dao接口类
 */
public interface CourseStudyDao {
    
    
    
    	/**
     * 添加并返回设置id的CourseStudy对象
     * 
     * @param courseStudy
     * @return
     */
    public CourseStudy addCourseStudy(CourseStudy courseStudy);
    
	/**
     * 更新CourseStudy
     * 
     * @param courseStudy
     */
    public void updateCourseStudy(CourseStudy courseStudy);
    


    	
	 /**
     * 根据主键删除CourseStudy
     * 
     * @param id
     */
    public void deleteCourseStudy(Long id);
    

    	/**
     * 根据主键获取CourseStudy
     * 
     * @param id
     * @return
     */	
    public CourseStudy getCourseStudyById(Long id);

   


	
    /**
     * 取得所有CourseStudy
     * 
     * @return
     */
    public List<CourseStudy> getAll();
    
	/**
     * 根据example取得CourseStudy列表
     * 
     * @param  courseStudy
     * @return
     */
    public List<CourseStudy> getListByExample(CourseStudy courseStudy);
    
    	/**
     * 根据example取得唯一的CourseStudy
     * 
     * @param courseStudy
     * @return
     */
    public CourseStudy getUnique(CourseStudy courseStudy);
    

    

    

	/**
     * 分页取得CourseStudy列表
     * 
     * @param params
     * @return
     */
    public List<CourseStudy> getCourseStudyByPage(Map<String,Object> params);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param params
     * @return
     */
    public int count(Map<String,Object> params);

}
