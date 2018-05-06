/**
 * Copyright(c) 2013-  www.jd.com
 *
 */

package com.goldfish.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;
import com.goldfish.dao.QuestionDao;
import com.goldfish.domain.Question;
import java.util.Map;



/**
 * @author hellosscat
* @since 2018-5-2
 * Question Dao实现类
 */
@Repository("questionDao")
public class QuestionDaoImpl  extends SqlMapClientTemplate implements QuestionDao {

	    public static final String ADD = "Question.add";
	    public static final String UPDATE = "Question.update";
	    public static final String DELETE = "Question.delete";
	 	
	    public static final String GET_ALL = "Question.getAll";
	    public static final String GET_BY_EXAMPLE = "Question.getByExample";
	   
	    public static final String GET_BY_ID = "Question.getById";
	    public static final String GET_BY_PAGE = "Question.getByPage";
	    public static final String COUNT = "Question.count";
	
	
	/**
	 * 新增
	 */
	
	public Question addQuestion(Question question) {
    		this.insert(ADD, question);
    		return question;
    }
    
   	/**
	 * 更新
	 */
    public void updateQuestion(Question question) {
    	this.update(UPDATE, question);
    }
    
    	
    /**
	 * 删除
	 */
    public void deleteQuestion(Long id) {
    	this.update(DELETE, id);
    }

    public Question getQuestionById(Long id) {
    	return (Question) this.queryForObject(GET_BY_ID, id);
    }
    
    

	
    public List<Question> getAll() {
    	return this.queryForList(GET_ALL);
    }

    public List<Question> getListByExample(Question question) {
    	return this.queryForList(GET_BY_EXAMPLE, question);
    }

    public Question getUnique(Question question) {
		List<Question> list = this.getListByExample(question);
    	return list.size() > 0 ? list.get(0) : null;
    }
      	

      
    /**
	 * 获得分页数据
	 *@param params 查询参数类
	 *@return 
	 */
    @SuppressWarnings("unchecked")
    public List<Question> getQuestionByPage(Map<String,Object> params) {
    	return this.queryForList(GET_BY_PAGE, params);
    }
    	
    public int count(Map<String,Object> params) {
    	return (Integer)this.queryForObject(COUNT, params);
    }

}
