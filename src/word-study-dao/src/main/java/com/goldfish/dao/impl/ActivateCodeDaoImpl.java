/**
 * Copyright(c) 2013-  www.jd.com
 *
 */

package com.goldfish.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;
import com.goldfish.dao.ActivateCodeDao;
import com.goldfish.domain.ActivateCode;
import java.util.Map;



/**
 * @author hellosscat
* @since 2018-5-2
 * ActivateCode Dao实现类
 */
@Repository("activateCodeDao")
public class ActivateCodeDaoImpl  extends SqlMapClientTemplate implements ActivateCodeDao {

	    public static final String ADD = "ActivateCode.add";
	    public static final String UPDATE = "ActivateCode.update";
	    public static final String DELETE = "ActivateCode.delete";
	 	
	    public static final String GET_ALL = "ActivateCode.getAll";
	    public static final String GET_BY_EXAMPLE = "ActivateCode.getByExample";
	   
	    public static final String GET_BY_ID = "ActivateCode.getById";
	    public static final String GET_BY_PAGE = "ActivateCode.getByPage";
	    public static final String COUNT = "ActivateCode.count";
	
	
	/**
	 * 新增
	 */
	
	public ActivateCode addActivateCode(ActivateCode activateCode) {
    		this.insert(ADD, activateCode);
    		return activateCode;
    }
    
   	/**
	 * 更新
	 */
    public void updateActivateCode(ActivateCode activateCode) {
    	this.update(UPDATE, activateCode);
    }
    
    	
    /**
	 * 删除
	 */
    public void deleteActivateCode(Long id) {
    	this.update(DELETE, id);
    }

    public ActivateCode getActivateCodeById(Long id) {
    	return (ActivateCode) this.queryForObject(GET_BY_ID, id);
    }
    
    

	
    public List<ActivateCode> getAll() {
    	return this.queryForList(GET_ALL);
    }

    public List<ActivateCode> getListByExample(ActivateCode activateCode) {
    	return this.queryForList(GET_BY_EXAMPLE, activateCode);
    }

    public ActivateCode getUnique(ActivateCode activateCode) {
		List<ActivateCode> list = this.getListByExample(activateCode);
    	return list.size() > 0 ? list.get(0) : null;
    }
      	

      
    /**
	 * 获得分页数据
	 *@param params 查询参数类
	 *@return 
	 */
    @SuppressWarnings("unchecked")
    public List<ActivateCode> getActivateCodeByPage(Map<String,Object> params) {
    	return this.queryForList(GET_BY_PAGE, params);
    }
    	
    public int count(Map<String,Object> params) {
    	return (Integer)this.queryForObject(COUNT, params);
    }

}
