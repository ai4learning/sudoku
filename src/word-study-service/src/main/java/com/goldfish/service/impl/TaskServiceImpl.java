package com.goldfish.service.impl;



import javax.annotation.Resource;
import java.util.List;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.Task;
import com.goldfish.manager.TaskManager;
import com.goldfish.service.TaskService;


/**
 * @author hellosscat
 * @since 2018-5-8
 *<p>  任务表service实现</p>
 *
 */
@Service("taskService")
public class TaskServiceImpl implements TaskService {

	private static final Logger logger = Logger.getLogger(TaskServiceImpl.class);
	
	@Resource(name="taskManager")
	private TaskManager taskManager;
    
    @Override
    public CommonResult<Task> addTask(Task task) {
		CommonResult<Task> result = new CommonResult<Task>();
		try{
			
			task.setCreated(new Date());
			
			
			task.setModified(new Date());
			
			result.addDefaultModel(taskManager.addTask(task));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("添加 任务表失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	@Override
    public CommonResult<Task> updateTask(Task task) {
		CommonResult<Task> result = new CommonResult<Task>();
		try {
			task.setModified(new Date());
			taskManager.updateTask(task);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("更新 任务表失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
   

	@Override
    public CommonResult<Task> deleteTask(Long id) {
		CommonResult<Task> result = new CommonResult<Task>();
		try {
			taskManager.deleteTask(id);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("删除 任务表失败", e);
			result.setSuccess(false);
		}
		return result;
    }


    	@Override
        public CommonResult<Task> getTaskById(Long id) {
		CommonResult<Task> result = new CommonResult<Task>();
		try {
			result.addDefaultModel("task", taskManager.getTaskById(id));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据主键获取 任务表失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	

        
	


	@Override
    public CommonResult<Task> getUnique(Task task) {
		CommonResult<Task> result = new CommonResult<Task>();
		try {
			result.addDefaultModel(taskManager.getUnique(task));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据example获取唯一 任务表失败", e);
			result.setSuccess(false);
		}
		return result;
	}


	@Override
    public CommonResult<List<Task>> getListByExample(Task task) {
		CommonResult<List<Task>> result = new CommonResult<List<Task>>();
		try {
			List<Task> list = taskManager.getListByExample(task);
			result.addDefaultModel("list", list);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("取得 任务表失败", e);
			result.setSuccess(false);
		}
		return result;
	}

	
	@Override
    public CommonResult<List<Task>> getTaskByPage(PageQuery pageQuery) {
		CommonResult<List<Task>> result = new CommonResult<List<Task>>();
		try {
			int totalCount = this.count(pageQuery);
			if (totalCount > 0) {
				pageQuery.setTotalCount(totalCount);
				List<Task> list = taskManager.getTaskByPage(pageQuery);
				result.addDefaultModel("list", list);
				result.addModel("pageQuery", pageQuery);
			}
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("分页获取 任务表失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	@Override
    public int count(PageQuery pageQuery) {
		return taskManager.count(pageQuery);
	}


	/******* getter and setter ***/
	public TaskManager getTaskManager() {
		return taskManager;
	}

	public void setTaskManager(TaskManager taskManager) {
		this.taskManager = taskManager;
	}

}
