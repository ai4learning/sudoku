/**
 * Copyright(c) 2013-  www.jd.com
 *
 */

package com.goldfish.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;
import com.goldfish.dao.UnitDao;
import com.goldfish.domain.Unit;
import java.util.Map;



/**
 * @author hellosscat
* @since 2018-5-2
 * Unit Dao实现类
 */
@Repository("unitDao")
public class UnitDaoImpl  extends SqlMapClientTemplate implements UnitDao {

	    public static final String ADD = "Unit.add";
	    public static final String UPDATE = "Unit.update";
	    public static final String DELETE = "Unit.delete";
	 	
	    public static final String GET_ALL = "Unit.getAll";
	    public static final String GET_BY_EXAMPLE = "Unit.getByExample";
	   
	    public static final String GET_BY_ID = "Unit.getById";
	    public static final String GET_BY_PAGE = "Unit.getByPage";
	    public static final String COUNT = "Unit.count";
	
	
	/**
	 * 新增
	 */
	
	public Unit addUnit(Unit unit) {
    		this.insert(ADD, unit);
    		return unit;
    }
    
   	/**
	 * 更新
	 */
    public void updateUnit(Unit unit) {
    	this.update(UPDATE, unit);
    }
    
    	
    /**
	 * 删除
	 */
    public void deleteUnit(Long id) {
    	this.update(DELETE, id);
    }

    public Unit getUnitById(Long id) {
    	return (Unit) this.queryForObject(GET_BY_ID, id);
    }
    
    

	
    public List<Unit> getAll() {
    	return this.queryForList(GET_ALL);
    }

    public List<Unit> getListByExample(Unit unit) {
    	return this.queryForList(GET_BY_EXAMPLE, unit);
    }

    public Unit getUnique(Unit unit) {
		List<Unit> list = this.getListByExample(unit);
    	return list.size() > 0 ? list.get(0) : null;
    }
      	

      
    /**
	 * 获得分页数据
	 *@param params 查询参数类
	 *@return 
	 */
    @SuppressWarnings("unchecked")
    public List<Unit> getUnitByPage(Map<String,Object> params) {
    	return this.queryForList(GET_BY_PAGE, params);
    }
    	
    public int count(Map<String,Object> params) {
    	return (Integer)this.queryForObject(COUNT, params);
    }

}
