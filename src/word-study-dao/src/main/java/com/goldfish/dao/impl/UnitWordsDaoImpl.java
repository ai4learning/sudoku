/**
 * Copyright(c) 2013-  www.jd.com
 *
 */

package com.goldfish.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;
import com.goldfish.dao.UnitWordsDao;
import com.goldfish.domain.UnitWords;
import java.util.Map;



/**
 * @author hellosscat
* @since 2018-5-2
 * UnitWords Dao实现类
 */
@Repository("unitWordsDao")
public class UnitWordsDaoImpl  extends SqlMapClientTemplate implements UnitWordsDao {

	    public static final String ADD = "UnitWords.add";
	    public static final String UPDATE = "UnitWords.update";
	    public static final String DELETE = "UnitWords.delete";
	 	
	    public static final String GET_ALL = "UnitWords.getAll";
	    public static final String GET_BY_EXAMPLE = "UnitWords.getByExample";
	   
	    public static final String GET_BY_ID = "UnitWords.getById";
	    public static final String GET_BY_PAGE = "UnitWords.getByPage";
	    public static final String COUNT = "UnitWords.count";
	
	
	/**
	 * 新增
	 */
	
	public UnitWords addUnitWords(UnitWords unitWords) {
    		this.insert(ADD, unitWords);
    		return unitWords;
    }
    
   	/**
	 * 更新
	 */
    public void updateUnitWords(UnitWords unitWords) {
    	this.update(UPDATE, unitWords);
    }
    
    	
    /**
	 * 删除
	 */
    public void deleteUnitWords(Long id) {
    	this.update(DELETE, id);
    }

    public UnitWords getUnitWordsById(Long id) {
    	return (UnitWords) this.queryForObject(GET_BY_ID, id);
    }
    
    

	
    public List<UnitWords> getAll() {
    	return this.queryForList(GET_ALL);
    }

    public List<UnitWords> getListByExample(UnitWords unitWords) {
    	return this.queryForList(GET_BY_EXAMPLE, unitWords);
    }

    public UnitWords getUnique(UnitWords unitWords) {
		List<UnitWords> list = this.getListByExample(unitWords);
    	return list.size() > 0 ? list.get(0) : null;
    }
      	

      
    /**
	 * 获得分页数据
	 *@param params 查询参数类
	 *@return 
	 */
    @SuppressWarnings("unchecked")
    public List<UnitWords> getUnitWordsByPage(Map<String,Object> params) {
    	return this.queryForList(GET_BY_PAGE, params);
    }
    	
    public int count(Map<String,Object> params) {
    	return (Integer)this.queryForObject(COUNT, params);
    }

}
