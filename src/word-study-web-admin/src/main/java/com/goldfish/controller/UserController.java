/**
 * Copyright(c) 2004-2018 bianfeng
 */


 package com.goldfish.controller;

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
import com.goldfish.service.UserService;
import com.goldfish.domain.User;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	
	@Resource(name="userService")
	private UserService userService;


	@RequestMapping(value="manage",method={RequestMethod.GET,RequestMethod.POST})
	public String manage(){
		return "/user/manage";
	}

	
	@RequestMapping(value="add",method={RequestMethod.GET,RequestMethod.POST})
    public String add() {
		return "/user/add";
    }
    
	
	@RequestMapping(value="doAdd",method={RequestMethod.GET,RequestMethod.POST})
	 public @ResponseBody Map<String,Object> doAdd(User user, ModelMap context) {
	    		CommonResult<User> result =userService.addUser(user);
				return result.getReturnMap();
	    }
	 
	 

	@RequestMapping(value="update",method={RequestMethod.GET,RequestMethod.POST})
	public String update(User user, ModelMap context) {
		CommonResult<User> result = userService.getUserById(user.getId());
		this.toVm(result, context);
		return "/user/update";
	}
	    
		
		@RequestMapping(value="doUpdate",method={RequestMethod.GET,RequestMethod.POST})
	    public @ResponseBody Map<String,Object> doUpdate(User user, ModelMap context) {
			CommonResult<User> result = userService.updateUser(user);
			return result.getReturnMap();
	    }
	    

		@RequestMapping(value="view",method={RequestMethod.GET,RequestMethod.POST})
		public String view(User user, ModelMap context) {
			CommonResult<User> result = userService.getUserById(user.getId());
			this.toVm(result, context);
			return "/user/view";
	    }
	   
		
		@RequestMapping(value="doDelete",method={RequestMethod.GET,RequestMethod.POST})
	    public @ResponseBody  Map<String,Object>  doDelete(User user) {
			CommonResult<User> result =userService.deleteUser(user.getId());
			return result.getReturnMap();
	    }
	    
		@RequestMapping(value="list",method={RequestMethod.GET,RequestMethod.POST})
	    public String list(HttpServletRequest request, ModelMap context) {
			int pageSize = this.getPageSize(request,20,200);
            PageQuery pageQuery=new PageQuery(request,pageSize);
            CommonResult<List<User>> result = userService.getUserByPage(pageQuery);
			this.toVm(result, context);
			return "/user/list";
	    }


}
