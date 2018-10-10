package com.goldfish.service;

import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.Task;

/**
 * @author hellosscat
 * @since 2018-5-8
 * jshow 任务表service 接口
 *
 */
public interface TaskService {
   
    /**
     * 添加并返回设置id的Task对象
     * 
     * @param task
     * @return
     */
    public CommonResult<Task> addTask(Task task);
    
	/**
     * 更新Task
     * 
     * @param task
     */
    public CommonResult<Task> updateTask(Task task);
    

    

	 /**
     * 根据主键删除Task
     * 
     * @param id
     */
    public CommonResult<Task> deleteTask(Long id);

	/**
     * 根据主键获取Task
     * 
     * @param id
     * @return
     */	
    public CommonResult<Task> getTaskById(Long id);

     


	
    
	/**
     * 根据example取得唯一的Task
     * 
     * @param task
     * @return
     */
    public CommonResult<Task> getUnique(Task task);
    



    /**
     * 根据example取得Task列表
     * 
     * @param  task
     * @return
     */
    public CommonResult<List<Task>> getListByExample(Task task);
    

	/**
     * 分页取得Task列表
     * 
     * @param pageQuery
     * @return
     */
    public CommonResult<List<Task>> getTaskByPage(PageQuery pageQuery);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param pageQuery
     * @return
     */
    public int count(PageQuery pageQuery);
	
	
}
