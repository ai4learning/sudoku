/**
 * Copyright(c) 2004-2018 bianfeng
 */


 package com.goldfish.manager;

import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.domain.CourseStudy;

/**
 * @author hellosscat
 * @since 2018-5-8
 * 课程学习Manager接口类
 */
public interface CourseStudyManager {
 
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
     * 根据example取得唯一的CourseStudy
     * 
     * @param courseStudy
     * @return
     */
    public CourseStudy getUnique(CourseStudy courseStudy);
    

    
    /**
     * 根据example取得CourseStudy列表
     * 
     * @param  courseStudy
     * @return
     */
    public List<CourseStudy> getListByExample(CourseStudy courseStudy);

	/**
     * 分页取得CourseStudy列表
     * 
     * @param pageQuery
     * @return
     */
    public List<CourseStudy> getCourseStudyByPage(PageQuery pageQuery);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param pageQuery
     * @return
     */
    public int count(PageQuery pageQuery);

 void deleteCourseStudyByCondition(CourseStudy courseStudy);
}
