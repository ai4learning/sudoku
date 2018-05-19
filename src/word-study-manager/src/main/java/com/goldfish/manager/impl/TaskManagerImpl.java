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


  public Task addTask(Task task) {
		int i=taskDao.addTask(task);
		return task;
    }
    
    public void updateTask(Task task) {
		taskDao.updateTask(task);
    }
    

    
    public void deleteTask(Long id) {
		taskDao.deleteTask(id);
    }


    public Task getTaskById(Long id) {
		return taskDao.getTaskById(id);
    }
    
   


    	
   
   public Task getUnique(Task task) {
		return taskDao.getUnique(task);
    }

    @Override
    public Task getUniqueValid(Task task) {
        return taskDao.getUniqueValid(task);
    }


    public List<Task> getListByExample(Task task) {
    return taskDao.getListByExample(task);
    }

    
    public List<Task> getTaskByPage(PageQuery pageQuery) {
		return taskDao.getTaskByPage( pageQuery.getParams());
    }
    	
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
