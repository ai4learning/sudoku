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
import com.goldfish.service.ExamService;
import com.goldfish.domain.Exam;

@Controller
@RequestMapping("/exam")
public class ExamController extends BaseController {

//	private final static Log log = LogFactory.getLog(ExamAction.class);
	
	@Resource(name="examService")
	private ExamService examService;


	@RequestMapping(value="manage",method={RequestMethod.GET,RequestMethod.POST})
	public String manage(){
		return "/exam/manage";
	}

	
	@RequestMapping(value="add",method={RequestMethod.GET,RequestMethod.POST})
    public String add() {
		return "/exam/add";
    }
    
	
	@RequestMapping(value="doAdd",method={RequestMethod.GET,RequestMethod.POST})
	 public @ResponseBody Map<String,Object> doAdd(Exam exam, ModelMap context) {
	    		CommonResult<Exam> result =examService.addExam(exam);
				return result.getReturnMap();
	    }
	 
	 

		@RequestMapping(value="update",method={RequestMethod.GET,RequestMethod.POST})
	    public String update(Exam exam, ModelMap context) {
			CommonResult<Exam> result = examService.getExamById(exam.getId());
			this.toVm(result, context);
			return "/exam/update";
	    }
	    
		
		@RequestMapping(value="doUpdate",method={RequestMethod.GET,RequestMethod.POST})
	    public @ResponseBody Map<String,Object> doUpdate(Exam exam, ModelMap context) {
			CommonResult<Exam> result = examService.updateExam(exam);
			return result.getReturnMap();
	    }
	    

		@RequestMapping(value="view",method={RequestMethod.GET,RequestMethod.POST})
		public String view(Exam exam, ModelMap context) {
			CommonResult<Exam> result = examService.getExamById(exam.getId());
			this.toVm(result, context);
			return "/exam/view";
	    }
	   
		
		@RequestMapping(value="doDelete",method={RequestMethod.GET,RequestMethod.POST})
	    public @ResponseBody  Map<String,Object>  doDelete(Exam exam) {
			CommonResult<Exam> result =examService.deleteExam(exam.getId());
			return result.getReturnMap();
	    }
	    
		@RequestMapping(value="list",method={RequestMethod.GET,RequestMethod.POST})
	    public String list(HttpServletRequest request, ModelMap context) {
			int pageSize = this.getPageSize(request,20,200);
            PageQuery pageQuery=new PageQuery(request,pageSize);
            CommonResult<List<Exam>> result = examService.getExamByPage(pageQuery);
			this.toVm(result, context);
			return "/exam/list";
	    }


}
