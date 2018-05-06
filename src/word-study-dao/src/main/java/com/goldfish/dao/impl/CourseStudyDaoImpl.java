/**
 * Copyright(c) 2013-  www.jd.com
 *
 */

package com.goldfish.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;
import com.goldfish.dao.CourseStudyDao;
import com.goldfish.domain.CourseStudy;
import java.util.Map;



/**
 * @author hellosscat
* @since 2018-5-2
 * CourseStudy Dao实现类
 */
@Repository("courseStudyDao")
public class CourseStudyDaoImpl  extends SqlMapClientTemplate implements CourseStudyDao {

	    public static final String ADD = "CourseStudy.add";
	    public static final String UPDATE = "CourseStudy.update";
	    public static final String DELETE = "CourseStudy.delete";
	 	
	    public static final String GET_ALL = "CourseStudy.getAll";
	    public static final String GET_BY_EXAMPLE = "CourseStudy.getByExample";
	   
	    public static final String GET_BY_ID = "CourseStudy.getById";
	    public static final String GET_BY_PAGE = "CourseStudy.getByPage";
	    public static final String COUNT = "CourseStudy.count";
	
	
	/**
	 * 新增
	 */
	
	public CourseStudy addCourseStudy(CourseStudy courseStudy) {
    		this.insert(ADD, courseStudy);
    		return courseStudy;
    }
    
   	/**
	 * 更新
	 */
    public void updateCourseStudy(CourseStudy courseStudy) {
    	this.update(UPDATE, courseStudy);
    }
    
    	
    /**
	 * 删除
	 */
    public void deleteCourseStudy(Long id) {
    	this.update(DELETE, id);
    }

    public CourseStudy getCourseStudyById(Long id) {
    	return (CourseStudy) this.queryForObject(GET_BY_ID, id);
    }
    
    

	
    public List<CourseStudy> getAll() {
    	return this.queryForList(GET_ALL);
    }

    public List<CourseStudy> getListByExample(CourseStudy courseStudy) {
    	return this.queryForList(GET_BY_EXAMPLE, courseStudy);
    }

    public CourseStudy getUnique(CourseStudy courseStudy) {
		List<CourseStudy> list = this.getListByExample(courseStudy);
    	return list.size() > 0 ? list.get(0) : null;
    }
      	

      
    /**
	 * 获得分页数据
	 *@param params 查询参数类
	 *@return 
	 */
    @SuppressWarnings("unchecked")
    public List<CourseStudy> getCourseStudyByPage(Map<String,Object> params) {
    	return this.queryForList(GET_BY_PAGE, params);
    }
    	
    public int count(Map<String,Object> params) {
    	return (Integer)this.queryForObject(COUNT, params);
    }

}
