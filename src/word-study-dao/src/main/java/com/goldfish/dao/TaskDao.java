
/**
 * Copyright(c) 2004-2018 bianfeng
 */


 package com.goldfish.dao;

import java.util.List;
import com.goldfish.domain.Task;
import java.util.Map;
/**
 * @author hellosscat
 * @since 2018-5-8
 * 任务表 Dao接口类
 */
public interface TaskDao {
    
    
    
    	/**
     * 添加并返回设置id的Task对象
     * 
     * @param task
     * @return
     */
    public int addTask(Task task);
    
	/**
     * 更新Task
     * 
     * @param task
     */
    public void updateTask(Task task);
    
    
    

    /**
     * 根据主键删除Task
     * 
     * @param id
     */
    public void deleteTask(Long id);


	/**
     * 根据主键获取Task
     * 
     * @param id
     * @return
     */	
    public Task getTaskById(Long id);
    


    
    /**
     * 根据example取得唯一的Task
     * 
     * @param task
     * @return
     */
    public Task getUnique(Task task);



    public Task getUniqueValid(Task task);


    /**
     * 根据example取得Task列表
     *
     * @param  task
     * @return
     */
    public List<Task> getListByExample(Task task);

	/**
     * 分页取得Task列表
     *
     * @param paramMap
     * @return
     */
    public List<Task> getTaskByPage(Map<String,Object> paramMap);

	/**
     * 根据查询条件返回数量
     *
     * @param paramMap
     * @return
     */
    public int count(Map<String,Object> paramMap);
}
