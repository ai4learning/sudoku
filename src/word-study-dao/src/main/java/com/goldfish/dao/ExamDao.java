
/**
 * Copyright(c) 2004-2018 bianfeng
 */


 package com.goldfish.dao;

import java.util.List;
import com.goldfish.domain.Exam;
import java.util.Map;
/**
 * @author hellosscat
 * @since 2018-5-8
 * 考试 Dao接口类
 */
public interface ExamDao {
    
    
    
    	/**
     * 添加并返回设置id的Exam对象
     * 
     * @param exam
     * @return
     */
    public int addExam(Exam exam);
    
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
     * @param paramMap
     * @return
     */
    public List<Exam> getExamByPage(Map<String,Object> paramMap);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param paramMap
     * @return
     */
    public int count(Map<String,Object> paramMap);

}
