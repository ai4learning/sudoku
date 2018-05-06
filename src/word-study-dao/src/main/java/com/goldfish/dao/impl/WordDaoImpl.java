/**
 * Copyright(c) 2013-  www.jd.com
 *
 */

package com.goldfish.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;
import com.goldfish.dao.WordDao;
import com.goldfish.domain.Word;
import java.util.Map;



/**
 * @author hellosscat
* @since 2018-5-2
 * Word Dao实现类
 */
@Repository("wordDao")
public class WordDaoImpl  extends SqlMapClientTemplate implements WordDao {

	    public static final String ADD = "Word.add";
	    public static final String UPDATE = "Word.update";
	    public static final String DELETE = "Word.delete";
	 	
	    public static final String GET_ALL = "Word.getAll";
	    public static final String GET_BY_EXAMPLE = "Word.getByExample";
	   
	    public static final String GET_BY_ID = "Word.getById";
	    public static final String GET_BY_PAGE = "Word.getByPage";
	    public static final String COUNT = "Word.count";
	
	
	/**
	 * 新增
	 */
	
	public Word addWord(Word word) {
    		this.insert(ADD, word);
    		return word;
    }
    
   	/**
	 * 更新
	 */
    public void updateWord(Word word) {
    	this.update(UPDATE, word);
    }
    
    	
    /**
	 * 删除
	 */
    public void deleteWord(Integer id) {
    	this.update(DELETE, id);
    }

    public Word getWordById(Integer id) {
    	return (Word) this.queryForObject(GET_BY_ID, id);
    }
    
    

	
    public List<Word> getAll() {
    	return this.queryForList(GET_ALL);
    }

    public List<Word> getListByExample(Word word) {
    	return this.queryForList(GET_BY_EXAMPLE, word);
    }

    public Word getUnique(Word word) {
		List<Word> list = this.getListByExample(word);
    	return list.size() > 0 ? list.get(0) : null;
    }
      	

      
    /**
	 * 获得分页数据
	 *@param params 查询参数类
	 *@return 
	 */
    @SuppressWarnings("unchecked")
    public List<Word> getWordByPage(Map<String,Object> params) {
    	return this.queryForList(GET_BY_PAGE, params);
    }
    	
    public int count(Map<String,Object> params) {
    	return (Integer)this.queryForObject(COUNT, params);
    }

}
