/**
 * Copyright(c) 2013-  www.jd.com
 *
 */

package com.goldfish.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;
import com.goldfish.dao.SelfWordsDao;
import com.goldfish.domain.SelfWords;
import java.util.Map;



/**
 * @author hellosscat
* @since 2018-5-2
 * SelfWords Dao实现类
 */
@Repository("selfWordsDao")
public class SelfWordsDaoImpl  extends SqlMapClientTemplate implements SelfWordsDao {

	    public static final String ADD = "SelfWords.add";
	    public static final String UPDATE = "SelfWords.update";
	    public static final String DELETE = "SelfWords.delete";
	 	
	    public static final String GET_ALL = "SelfWords.getAll";
	    public static final String GET_BY_EXAMPLE = "SelfWords.getByExample";
	   
	    public static final String GET_BY_ID = "SelfWords.getById";
	    public static final String GET_BY_PAGE = "SelfWords.getByPage";
	    public static final String COUNT = "SelfWords.count";
	
	
	/**
	 * 新增
	 */
	
	public SelfWords addSelfWords(SelfWords selfWords) {
    		this.insert(ADD, selfWords);
    		return selfWords;
    }
    
   	/**
	 * 更新
	 */
    public void updateSelfWords(SelfWords selfWords) {
    	this.update(UPDATE, selfWords);
    }
    
    	
    /**
	 * 删除
	 */
    public void deleteSelfWords(Long id) {
    	this.update(DELETE, id);
    }

    public SelfWords getSelfWordsById(Long id) {
    	return (SelfWords) this.queryForObject(GET_BY_ID, id);
    }
    
    

	
    public List<SelfWords> getAll() {
    	return this.queryForList(GET_ALL);
    }

    public List<SelfWords> getListByExample(SelfWords selfWords) {
    	return this.queryForList(GET_BY_EXAMPLE, selfWords);
    }

    public SelfWords getUnique(SelfWords selfWords) {
		List<SelfWords> list = this.getListByExample(selfWords);
    	return list.size() > 0 ? list.get(0) : null;
    }
      	

      
    /**
	 * 获得分页数据
	 *@param params 查询参数类
	 *@return 
	 */
    @SuppressWarnings("unchecked")
    public List<SelfWords> getSelfWordsByPage(Map<String,Object> params) {
    	return this.queryForList(GET_BY_PAGE, params);
    }
    	
    public int count(Map<String,Object> params) {
    	return (Integer)this.queryForObject(COUNT, params);
    }

}
