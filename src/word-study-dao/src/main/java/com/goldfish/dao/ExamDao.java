
/**
 * Copyright(c) 2013-  www.jd.com
 *
 */
 package com.goldfish.dao;

import java.util.List;
import com.goldfish.domain.Exam;
import java.util.Map;
/**
 * @author hellosscat
 * @since 2018-5-2
 * Exam Dao接口类
 */
public interface ExamDao {
    
    
    
    	/**
     * 添加并返回设置id的Exam对象
     * 
     * @param exam
     * @return
     */
    public Exam addExam(Exam exam);
    
	/**
     * 更新Exam
     * 
     * @param exam
     */
    public void updateExam(Exam exam);
    


    	
	 /**
     * 根据主键删除Exam
     * 
     * @param id
     */
    public void deleteExam(Long id);
    

    	/**
     * 根据主键获取Exam
     * 
     * @param id
     * @return
     */	
    public Exam getExamById(Long id);

   


	
    /**
     * 取得所有Exam
     * 
     * @return
     */
    public List<Exam> getAll();
    
	/**
     * 根据example取得Exam列表
     * 
     * @param  exam
     * @return
     */
    public List<Exam> getListByExample(Exam exam);
    
    	/**
     * 根据example取得唯一的Exam
     * 
     * @param exam
     * @return
     */
    public Exam getUnique(Exam exam);
    

    

    

	/**
     * 分页取得Exam列表
     * 
     * @param params
     * @return
     */
    public List<Exam> getExamByPage(Map<String,Object> params);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param params
     * @return
     */
    public int count(Map<String,Object> params);

}
