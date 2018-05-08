/**
 * Copyright(c) 2004-2018 bianfeng
 */


 package com.goldfish.manager;

import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.domain.Exam;

/**
 * @author hellosscat
 * @since 2018-5-8
 * 考试Manager接口类
 */
public interface ExamManager {
 
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
     * 根据example取得唯一的Exam
     * 
     * @param exam
     * @return
     */
    public Exam getUnique(Exam exam);
    

    
    /**
     * 根据example取得Exam列表
     * 
     * @param  exam
     * @return
     */
    public List<Exam> getListByExample(Exam exam);

	/**
     * 分页取得Exam列表
     * 
     * @param pageQuery
     * @return
     */
    public List<Exam> getExamByPage(PageQuery pageQuery);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param pageQuery
     * @return
     */
    public int count(PageQuery pageQuery);

}
