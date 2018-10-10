/**
 * Copyright(c) 2004-2018 bianfeng
 */


 package com.goldfish.api;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldfish.common.CommonResult;
import com.goldfish.common.PageQuery;
import com.goldfish.web.base.BaseController;
import com.goldfish.service.TaskService;
import com.goldfish.domain.Task;

@Controller
@RequestMapping("/task")
public class TaskController extends BaseController {

//	private final static Log log = LogFactory.getLog(TaskAction.class);
	
	@Resource(name="taskService")
	private TaskService taskService;


	@RequestMapping(value="manage",method={RequestMethod.GET,RequestMethod.POST})
	public String manage(){
		return "/task/manage";
	}

	
	@RequestMapping(value="add",method={RequestMethod.GET,RequestMethod.POST})
    public String add() {
		return "/task/add";
    }
    
	
	@RequestMapping(value="doAdd",method={RequestMethod.GET,RequestMethod.POST})
	 public @ResponseBody Map<String,Object> doAdd(Task task, ModelMap context) {
	    		CommonResult<Task> result =taskService.addTask(task);
				return result.getReturnMap();
	    }
	 
	 

		@RequestMapping(value="update",method={RequestMethod.GET,RequestMethod.POST})
	    public String update(Task task, ModelMap context) {
			CommonResult<Task> result = taskService.getTaskById(task.getId());
			this.toVm(result, context);
			return "/task/update";
	    }
	    
		
		@RequestMapping(value="doUpdate",method={RequestMethod.GET,RequestMethod.POST})
	    public @ResponseBody Map<String,Object> doUpdate(Task task, ModelMap context) {
			CommonResult<Task> result = taskService.updateTask(task);
			return result.getReturnMap();
	    }
	    

		@RequestMapping(value="view",method={RequestMethod.GET,RequestMethod.POST})
		public String view(Task task, ModelMap context) {
			CommonResult<Task> result = taskService.getTaskById(task.getId());
			this.toVm(result, context);
			return "/task/view";
	    }
	   
		
		@RequestMapping(value="doDelete",method={RequestMethod.GET,RequestMethod.POST})
	    public @ResponseBody  Map<String,Object>  doDelete(Task task) {
			CommonResult<Task> result =taskService.deleteTask(task.getId());
			return result.getReturnMap();
	    }
	    
		@RequestMapping(value="list",method={RequestMethod.GET,RequestMethod.POST})
	    public String list(HttpServletRequest request, ModelMap context) {
			int pageSize = this.getPageSize(request,20,200);
            PageQuery pageQuery=new PageQuery(request,pageSize);
            CommonResult<List<Task>> result = taskService.getTaskByPage(pageQuery);
			this.toVm(result, context);
			return "/task/list";
	    }


}
