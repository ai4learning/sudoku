/**
 * Copyright(c) 2013-  www.jd.com
 *
 */

package com.goldfish.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;
import com.goldfish.dao.WordStudyDao;
import com.goldfish.domain.WordStudy;
import java.util.Map;



/**
 * @author hellosscat
* @since 2018-5-2
 * WordStudy Dao实现类
 */
@Repository("wordStudyDao")
public class WordStudyDaoImpl  extends SqlMapClientTemplate implements WordStudyDao {

	    public static final String ADD = "WordStudy.add";
	    public static final String UPDATE = "WordStudy.update";
	    public static final String DELETE = "WordStudy.delete";
	 	
	    public static final String GET_ALL = "WordStudy.getAll";
	    public static final String GET_BY_EXAMPLE = "WordStudy.getByExample";
	   
	    public static final String GET_BY_ID = "WordStudy.getById";
	    public static final String GET_BY_PAGE = "WordStudy.getByPage";
	    public static final String COUNT = "WordStudy.count";
	
	
	/**
	 * 新增
	 */
	
	public WordStudy addWordStudy(WordStudy wordStudy) {
    		this.insert(ADD, wordStudy);
    		return wordStudy;
    }
    
   	/**
	 * 更新
	 */
    public void updateWordStudy(WordStudy wordStudy) {
    	this.update(UPDATE, wordStudy);
    }
    
    	
    /**
	 * 删除
	 */
    public void deleteWordStudy(Long id) {
    	this.update(DELETE, id);
    }

    public WordStudy getWordStudyById(Long id) {
    	return (WordStudy) this.queryForObject(GET_BY_ID, id);
    }
    
    

	
    public List<WordStudy> getAll() {
    	return this.queryForList(GET_ALL);
    }

    public List<WordStudy> getListByExample(WordStudy wordStudy) {
    	return this.queryForList(GET_BY_EXAMPLE, wordStudy);
    }

    public WordStudy getUnique(WordStudy wordStudy) {
		List<WordStudy> list = this.getListByExample(wordStudy);
    	return list.size() > 0 ? list.get(0) : null;
    }
      	

      
    /**
	 * 获得分页数据
	 *@param params 查询参数类
	 *@return 
	 */
    @SuppressWarnings("unchecked")
    public List<WordStudy> getWordStudyByPage(Map<String,Object> params) {
    	return this.queryForList(GET_BY_PAGE, params);
    }
    	
    public int count(Map<String,Object> params) {
    	return (Integer)this.queryForObject(COUNT, params);
    }

}
