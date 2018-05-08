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
import com.goldfish.service.CourseStudyService;
import com.goldfish.domain.CourseStudy;

@Controller
@RequestMapping("/courseStudy")
public class CourseStudyController extends BaseController {

//	private final static Log log = LogFactory.getLog(CourseStudyAction.class);
	
	@Resource(name="courseStudyService")
	private CourseStudyService courseStudyService;


	@RequestMapping(value="manage",method={RequestMethod.GET,RequestMethod.POST})
	public String manage(){
		return "/courseStudy/manage";
	}

	
	@RequestMapping(value="add",method={RequestMethod.GET,RequestMethod.POST})
    public String add() {
		return "/courseStudy/add";
    }
    
	
	@RequestMapping(value="doAdd",method={RequestMethod.GET,RequestMethod.POST})
	 public @ResponseBody Map<String,Object> doAdd(CourseStudy courseStudy, ModelMap context) {
	    		CommonResult<CourseStudy> result =courseStudyService.addCourseStudy(courseStudy);
				return result.getReturnMap();
	    }
	 
	 

		@RequestMapping(value="update",method={RequestMethod.GET,RequestMethod.POST})
	    public String update(CourseStudy courseStudy, ModelMap context) {
			CommonResult<CourseStudy> result = courseStudyService.getCourseStudyById(courseStudy.getId());
			this.toVm(result, context);
			return "/courseStudy/update";
	    }
	    
		
		@RequestMapping(value="doUpdate",method={RequestMethod.GET,RequestMethod.POST})
	    public @ResponseBody Map<String,Object> doUpdate(CourseStudy courseStudy, ModelMap context) {
			CommonResult<CourseStudy> result = courseStudyService.updateCourseStudy(courseStudy);
			return result.getReturnMap();
	    }
	    

		@RequestMapping(value="view",method={RequestMethod.GET,RequestMethod.POST})
		public String view(CourseStudy courseStudy, ModelMap context) {
			CommonResult<CourseStudy> result = courseStudyService.getCourseStudyById(courseStudy.getId());
			this.toVm(result, context);
			return "/courseStudy/view";
	    }
	   
		
		@RequestMapping(value="doDelete",method={RequestMethod.GET,RequestMethod.POST})
	    public @ResponseBody  Map<String,Object>  doDelete(CourseStudy courseStudy) {
			CommonResult<CourseStudy> result =courseStudyService.deleteCourseStudy(courseStudy.getId());
			return result.getReturnMap();
	    }
	    
		@RequestMapping(value="list",method={RequestMethod.GET,RequestMethod.POST})
	    public String list(HttpServletRequest request, ModelMap context) {
			int pageSize = this.getPageSize(request,20,200);
            PageQuery pageQuery=new PageQuery(request,pageSize);
            CommonResult<List<CourseStudy>> result = courseStudyService.getCourseStudyByPage(pageQuery);
			this.toVm(result, context);
			return "/courseStudy/list";
	    }


}
