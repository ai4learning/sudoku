package com.goldfish.service;

import java.util.List;
import java.util.Map;

import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.Exam;

/**
 * @author hellosscat
 * @since 2018-5-8
 * jshow 考试service 接口
 *
 */
public interface ExamService {
   
    /**
     * 添加并返回设置id的Exam对象
     * 
     * @param exam
     * @return
     */
    public CommonResult<Exam> addExam(Exam exam);
    
	/**
     * 更新Exam
     * 
     * @param exam
     */
    public CommonResult<Exam> updateExam(Exam exam);
    

    

	 /**
     * 根据主键删除Exam
     * 
     * @param id
     */
    public CommonResult<Exam> deleteExam(Long id);

	/**
     * 根据主键获取Exam
     * 
     * @param id
     * @return
     */	
    public CommonResult<Exam> getExamById(Long id);

     


	
    
	/**
     * 根据example取得唯一的Exam
     * 
     * @param exam
     * @return
     */
    public CommonResult<Exam> getUnique(Exam exam);
    



    /**
     * 根据example取得Exam列表
     * 
     * @param  exam
     * @return
     */
    public CommonResult<List<Exam>> getListByExample(Exam exam);
    

	/**
     * 分页取得Exam列表
     * 
     * @param pageQuery
     * @return
     */
    public CommonResult<List<Exam>> getExamByPage(PageQuery pageQuery);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param pageQuery
     * @return
     */
    public int count(PageQuery pageQuery);

    /**
     * 查询该用户最近15天的考试结果
     *
     * @param  exam 查询条件
     * @return List<Exam> 结果
     */
    public CommonResult<List<Exam>> getListByUserRecent(PageQuery pageQuery);
}
