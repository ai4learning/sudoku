/**
 * Copyright(c) 2013-  www.jd.com
 *
 */

package com.goldfish.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;
import com.goldfish.dao.CourseDao;
import com.goldfish.domain.Course;
import java.util.Map;



/**
 * @author hellosscat
* @since 2018-5-2
 * Course Dao实现类
 */
@Repository("courseDao")
public class CourseDaoImpl  extends SqlMapClientTemplate implements CourseDao {

	    public static final String ADD = "Course.add";
	    public static final String UPDATE = "Course.update";
	    public static final String DELETE = "Course.delete";
	 	
	    public static final String GET_ALL = "Course.getAll";
	    public static final String GET_BY_EXAMPLE = "Course.getByExample";
	   
	    public static final String GET_BY_ID = "Course.getById";
	    public static final String GET_BY_PAGE = "Course.getByPage";
	    public static final String COUNT = "Course.count";
	
	
	/**
	 * 新增
	 */
	
	public Course addCourse(Course course) {
    		this.insert(ADD, course);
    		return course;
    }
    
   	/**
	 * 更新
	 */
    public void updateCourse(Course course) {
    	this.update(UPDATE, course);
    }
    
    	
    /**
	 * 删除
	 */
    public void deleteCourse(Integer id) {
    	this.update(DELETE, id);
    }

    public Course getCourseById(Integer id) {
    	return (Course) this.queryForObject(GET_BY_ID, id);
    }
    
    

	
    public List<Course> getAll() {
    	return this.queryForList(GET_ALL);
    }

    public List<Course> getListByExample(Course course) {
    	return this.queryForList(GET_BY_EXAMPLE, course);
    }

    public Course getUnique(Course course) {
		List<Course> list = this.getListByExample(course);
    	return list.size() > 0 ? list.get(0) : null;
    }
      	

      
    /**
	 * 获得分页数据
	 *@param params 查询参数类
	 *@return 
	 */
    @SuppressWarnings("unchecked")
    public List<Course> getCourseByPage(Map<String,Object> params) {
    	return this.queryForList(GET_BY_PAGE, params);
    }
    	
    public int count(Map<String,Object> params) {
    	return (Integer)this.queryForObject(COUNT, params);
    }

}
