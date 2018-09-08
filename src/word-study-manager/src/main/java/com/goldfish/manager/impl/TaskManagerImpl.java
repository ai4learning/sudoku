package com.goldfish.manager.impl;

import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.domain.Task;
import com.goldfish.dao.TaskDao;
import com.goldfish.manager.TaskManager;

/**
 * @author hellosscat
 * @since 2018-5-8
 * 任务表Manager实现类
 */
 @Component("taskManager")
public class TaskManagerImpl implements TaskManager {

	@Resource(name="taskDao")
	private TaskDao taskDao;


  @Override
  public Task addTask(Task task) {
		int i=taskDao.addTask(task);
		return task;
    }
    
    @Override
    public void updateTask(Task task) {
		taskDao.updateTask(task);
    }
    

    
    @Override
    public void deleteTask(Long id) {
		taskDao.deleteTask(id);
    }


    @Override
    public Task getTaskById(Long id) {
		return taskDao.getTaskById(id);
    }
    
   


    	
   
   @Override
   public Task getUnique(Task task) {
		return taskDao.getUnique(task);
    }

    @Override
    public Task getUniqueValid(Task task) {
        return taskDao.getUniqueValid(task);
    }


    @Override
    public List<Task> getListByExample(Task task) {
    return taskDao.getListByExample(task);
    }

    
    @Override
    public List<Task> getTaskByPage(PageQuery pageQuery) {
		return taskDao.getTaskByPage( pageQuery.getParams());
    }
    	
    @Override
    public int count(PageQuery pageQuery) {
		return taskDao.count( pageQuery.getParams());
    }

    /******* getter and setter ***/
    
	public TaskDao getTaskDao() {
		return taskDao;
	}

	public void setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
	}
}
