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
import com.goldfish.service.AllexamService;
import com.goldfish.domain.Allexam;

@Controller
@RequestMapping("/allexam")
public class AllexamController extends BaseController {

//	private final static Log log = LogFactory.getLog(AllexamAction.class);
	
	@Resource(name="allexamService")
	private AllexamService allexamService;


	@RequestMapping(value="manage",method={RequestMethod.GET,RequestMethod.POST})
	public String manage(){
		return "/allexam/manage";
	}

	
	@RequestMapping(value="add",method={RequestMethod.GET,RequestMethod.POST})
    public String add() {
		return "/allexam/add";
    }
    
	
	@RequestMapping(value="doAdd",method={RequestMethod.GET,RequestMethod.POST})
	 public @ResponseBody Map<String,Object> doAdd(Allexam allexam, ModelMap context) {
	    		CommonResult<Allexam> result =allexamService.addAllexam(allexam);
				return result.getReturnMap();
	    }
	 
	 

		@RequestMapping(value="update",method={RequestMethod.GET,RequestMethod.POST})
	    public String update(Allexam allexam, ModelMap context) {
			CommonResult<Allexam> result = allexamService.getAllexamById(allexam.getId());
			this.toVm(result, context);
			return "/allexam/update";
	    }
	    
		
		@RequestMapping(value="doUpdate",method={RequestMethod.GET,RequestMethod.POST})
	    public @ResponseBody Map<String,Object> doUpdate(Allexam allexam, ModelMap context) {
			CommonResult<Allexam> result = allexamService.updateAllexam(allexam);
			return result.getReturnMap();
	    }
	    

		@RequestMapping(value="view",method={RequestMethod.GET,RequestMethod.POST})
		public String view(Allexam allexam, ModelMap context) {
			CommonResult<Allexam> result = allexamService.getAllexamById(allexam.getId());
			this.toVm(result, context);
			return "/allexam/view";
	    }
	   
		
		@RequestMapping(value="doDelete",method={RequestMethod.GET,RequestMethod.POST})
	    public @ResponseBody  Map<String,Object>  doDelete(Allexam allexam) {
			CommonResult<Allexam> result =allexamService.deleteAllexam(allexam.getId());
			return result.getReturnMap();
	    }
	    
		@RequestMapping(value="list",method={RequestMethod.GET,RequestMethod.POST})
	    public String list(HttpServletRequest request, ModelMap context) {
			int pageSize = this.getPageSize(request,20,200);
            PageQuery pageQuery=new PageQuery(request,pageSize);
            CommonResult<List<Allexam>> result = allexamService.getAllexamByPage(pageQuery);
			this.toVm(result, context);
			return "/allexam/list";
	    }


}
