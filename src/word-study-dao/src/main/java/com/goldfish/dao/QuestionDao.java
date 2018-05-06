
/**
 * Copyright(c) 2013-  www.jd.com
 *
 */
 package com.goldfish.dao;

import java.util.List;
import com.goldfish.domain.Question;
import java.util.Map;
/**
 * @author hellosscat
 * @since 2018-5-2
 * Question Dao接口类
 */
public interface QuestionDao {
    
    
    
    	/**
     * 添加并返回设置id的Question对象
     * 
     * @param question
     * @return
     */
    public Question addQuestion(Question question);
    
	/**
     * 更新Question
     * 
     * @param question
     */
    public void updateQuestion(Question question);
    


    	
	 /**
     * 根据主键删除Question
     * 
     * @param id
     */
    public void deleteQuestion(Long id);
    

    	/**
     * 根据主键获取Question
     * 
     * @param id
     * @return
     */	
    public Question getQuestionById(Long id);

   


	
    /**
     * 取得所有Question
     * 
     * @return
     */
    public List<Question> getAll();
    
	/**
     * 根据example取得Question列表
     * 
     * @param  question
     * @return
     */
    public List<Question> getListByExample(Question question);
    
    	/**
     * 根据example取得唯一的Question
     * 
     * @param question
     * @return
     */
    public Question getUnique(Question question);
    

    

    

	/**
     * 分页取得Question列表
     * 
     * @param params
     * @return
     */
    public List<Question> getQuestionByPage(Map<String,Object> params);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param params
     * @return
     */
    public int count(Map<String,Object> params);

}
