package com.goldfish.service;

import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.Question;

/**
 * @author hellosscat
 * @since 2018-5-2
 * jshow Questionservice 接口
 *
 */
public interface QuestionService {
   
    /**
     * 添加并返回设置id的Question对象
     * 
     * @param question
     * @return
     */
    public CommonResult<Question> addQuestion(Question question);
    
	/**
     * 更新Question
     * 
     * @param question
     */
    public CommonResult<Question> updateQuestion(Question question);
    

    

	 /**
     * 根据主键删除Question
     * 
     * @param id
     */
    public CommonResult<Question> deleteQuestion(Long id);

	/**
     * 根据主键获取Question
     * 
     * @param id
     * @return
     */	
    public CommonResult<Question> getQuestionById(Long id);

     

    /**
     * 取得所有Question
     * 
     * @return
     */
    public CommonResult<List<Question>> getAll();
    
	/**
     * 根据example取得Question列表
     * 
     * @param  question
     * @return
     */
    public CommonResult<List<Question>> getListByExample(Question question);
    
	/**
     * 根据example取得唯一的Question
     * 
     * @param question
     * @return
     */
    public CommonResult<Question> getUnique(Question question);
    
    


    

	/**
     * 分页取得Question列表
     * 
     * @param pageQuery
     * @return
     */
    public CommonResult<List<Question>> getQuestionByPage(PageQuery pageQuery);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param pageQuery
     * @return
     */
    public int count(PageQuery pageQuery);
	
	
}
