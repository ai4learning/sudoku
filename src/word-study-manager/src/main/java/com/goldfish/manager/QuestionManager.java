/**
 * Copyright(c) 2004-2018 bianfeng
 */


 package com.goldfish.manager;

import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.domain.Question;

/**
 * @author hellosscat
 * @since 2018-5-8
 * 试题Manager接口类
 */
public interface QuestionManager {
 
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
     * 根据example取得唯一的Question
     * 
     * @param question
     * @return
     */
    public Question getUnique(Question question);
    

    
    /**
     * 根据example取得Question列表
     * 
     * @param  question
     * @return
     */
    public List<Question> getListByExample(Question question);

	/**
     * 分页取得Question列表
     * 
     * @param pageQuery
     * @return
     */
    public List<Question> getQuestionByPage(PageQuery pageQuery);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param pageQuery
     * @return
     */
    public int count(PageQuery pageQuery);

}
