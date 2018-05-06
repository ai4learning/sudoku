/**
 * Copyright(c) 2013-  www.jd.com
 *
 */

package com.goldfish.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;
import com.goldfish.dao.SelfWordsStudyDao;
import com.goldfish.domain.SelfWordsStudy;
import java.util.Map;



/**
 * @author hellosscat
* @since 2018-5-2
 * SelfWordsStudy Dao实现类
 */
@Repository("selfWordsStudyDao")
public class SelfWordsStudyDaoImpl  extends SqlMapClientTemplate implements SelfWordsStudyDao {

	    public static final String ADD = "SelfWordsStudy.add";
	    public static final String UPDATE = "SelfWordsStudy.update";
	    public static final String DELETE = "SelfWordsStudy.delete";
	 	
	    public static final String GET_ALL = "SelfWordsStudy.getAll";
	    public static final String GET_BY_EXAMPLE = "SelfWordsStudy.getByExample";
	   
	    public static final String GET_BY_ID = "SelfWordsStudy.getById";
	    public static final String GET_BY_PAGE = "SelfWordsStudy.getByPage";
	    public static final String COUNT = "SelfWordsStudy.count";
	
	
	/**
	 * 新增
	 */
	
	public SelfWordsStudy addSelfWordsStudy(SelfWordsStudy selfWordsStudy) {
    		this.insert(ADD, selfWordsStudy);
    		return selfWordsStudy;
    }
    
   	/**
	 * 更新
	 */
    public void updateSelfWordsStudy(SelfWordsStudy selfWordsStudy) {
    	this.update(UPDATE, selfWordsStudy);
    }
    
    	
    /**
	 * 删除
	 */
    public void deleteSelfWordsStudy(Long id) {
    	this.update(DELETE, id);
    }

    public SelfWordsStudy getSelfWordsStudyById(Long id) {
    	return (SelfWordsStudy) this.queryForObject(GET_BY_ID, id);
    }
    
    

	
    public List<SelfWordsStudy> getAll() {
    	return this.queryForList(GET_ALL);
    }

    public List<SelfWordsStudy> getListByExample(SelfWordsStudy selfWordsStudy) {
    	return this.queryForList(GET_BY_EXAMPLE, selfWordsStudy);
    }

    public SelfWordsStudy getUnique(SelfWordsStudy selfWordsStudy) {
		List<SelfWordsStudy> list = this.getListByExample(selfWordsStudy);
    	return list.size() > 0 ? list.get(0) : null;
    }
      	

      
    /**
	 * 获得分页数据
	 *@param params 查询参数类
	 *@return 
	 */
    @SuppressWarnings("unchecked")
    public List<SelfWordsStudy> getSelfWordsStudyByPage(Map<String,Object> params) {
    	return this.queryForList(GET_BY_PAGE, params);
    }
    	
    public int count(Map<String,Object> params) {
    	return (Integer)this.queryForObject(COUNT, params);
    }

}
