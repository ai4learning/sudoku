/**
 * Copyright(c) 2004-2018 www.jd.com
 * com.goldfish.controller.WordStudyController.java
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
import com.goldfish.service.WordStudyService;
import com.goldfish.domain.WordStudy;

@Controller
@RequestMapping("//wordStudy")
public class WordStudyController extends BaseController {

//	private final static Log log = LogFactory.getLog(WordStudyAction.class);
	
	@Resource(name="wordStudyService")
	private WordStudyService wordStudyService;


	@RequestMapping(value="manage",method={RequestMethod.GET,RequestMethod.POST})
	public String manage(){
		return "/wordStudy/manage";
	}

	
	@RequestMapping(value="add",method={RequestMethod.GET,RequestMethod.POST})
    public String add() {
		return "/wordStudy/add";
    }
    
	
	@RequestMapping(value="doAdd",method={RequestMethod.GET,RequestMethod.POST})
	 public @ResponseBody Map<String,Object> doAdd(WordStudy wordStudy, ModelMap context) {
	    		CommonResult<WordStudy> result =wordStudyService.addWordStudy(wordStudy);
				return result.getReturnMap();
	    }
	 
	 

		@RequestMapping(value="update",method={RequestMethod.GET,RequestMethod.POST})
	    public String update(WordStudy wordStudy, ModelMap context) {
			CommonResult<WordStudy> result = wordStudyService.getWordStudyById(wordStudy.getId());
			this.toVm(result, context);
			return "/wordStudy/update";
	    }
	    
		
		@RequestMapping(value="doUpdate",method={RequestMethod.GET,RequestMethod.POST})
	    public @ResponseBody Map<String,Object> doUpdate(WordStudy wordStudy, ModelMap context) {
			CommonResult<WordStudy> result = wordStudyService.updateWordStudy(wordStudy);
			return result.getReturnMap();
	    }
	    

		@RequestMapping(value="view",method={RequestMethod.GET,RequestMethod.POST})
		public String view(WordStudy wordStudy, ModelMap context) {
			CommonResult<WordStudy> result = wordStudyService.getWordStudyById(wordStudy.getId());
			this.toVm(result, context);
			return "/wordStudy/view";
	    }
	   
		
		@RequestMapping(value="doDelete",method={RequestMethod.GET,RequestMethod.POST})
	    public @ResponseBody  Map<String,Object>  doDelete(WordStudy wordStudy) {
			CommonResult<WordStudy> result =wordStudyService.deleteWordStudy(wordStudy.getId());
			return result.getReturnMap();
	    }
	    
		@RequestMapping(value="list",method={RequestMethod.GET,RequestMethod.POST})
	    public String list(HttpServletRequest request, ModelMap context) {
			int pageSize = this.getPageSize(request,20,200);
            PageQuery pageQuery=new PageQuery(request,pageSize);
            CommonResult<List<WordStudy>> result = wordStudyService.getWordStudyByPage(pageQuery);
			this.toVm(result, context);
			return "/wordStudy/list";
	    }


}
