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
import com.goldfish.service.CourseService;
import com.goldfish.domain.Course;

@Controller
@RequestMapping("/course")
public class CourseController extends BaseController {

//	private final static Log log = LogFactory.getLog(CourseAction.class);
	
	@Resource(name="courseService")
	private CourseService courseService;


	@RequestMapping(value="manage",method={RequestMethod.GET,RequestMethod.POST})
	public String manage(){
		return "/course/manage";
	}

	
	@RequestMapping(value="add",method={RequestMethod.GET,RequestMethod.POST})
    public String add() {
		return "/course/add";
    }
    
	
	@RequestMapping(value="doAdd",method={RequestMethod.GET,RequestMethod.POST})
	 public @ResponseBody Map<String,Object> doAdd(Course course, ModelMap context) {
	    		CommonResult<Course> result =courseService.addCourse(course);
				return result.getReturnMap();
	    }
	 
	 

		@RequestMapping(value="update",method={RequestMethod.GET,RequestMethod.POST})
	    public String update(Course course, ModelMap context) {
			CommonResult<Course> result = courseService.getCourseById(course.getId());
			this.toVm(result, context);
			return "/course/update";
	    }
	    
		
		@RequestMapping(value="doUpdate",method={RequestMethod.GET,RequestMethod.POST})
	    public @ResponseBody Map<String,Object> doUpdate(Course course, ModelMap context) {
			CommonResult<Course> result = courseService.updateCourse(course);
			return result.getReturnMap();
	    }
	    

		@RequestMapping(value="view",method={RequestMethod.GET,RequestMethod.POST})
		public String view(Course course, ModelMap context) {
			CommonResult<Course> result = courseService.getCourseById(course.getId());
			this.toVm(result, context);
			return "/course/view";
	    }
	   
		
		@RequestMapping(value="doDelete",method={RequestMethod.GET,RequestMethod.POST})
	    public @ResponseBody  Map<String,Object>  doDelete(Course course) {
			CommonResult<Course> result =courseService.deleteCourse(course.getId());
			return result.getReturnMap();
	    }
	    
		@RequestMapping(value="list",method={RequestMethod.GET,RequestMethod.POST})
	    public String list(HttpServletRequest request, ModelMap context) {
			int pageSize = this.getPageSize(request,20,200);
            PageQuery pageQuery=new PageQuery(request,pageSize);
            CommonResult<List<Course>> result = courseService.getCourseByPage(pageQuery);
			this.toVm(result, context);
			return "/course/list";
	    }


}
