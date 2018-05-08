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
import com.goldfish.service.SelfWordsStudyService;
import com.goldfish.domain.SelfWordsStudy;

@Controller
@RequestMapping("/selfWordsStudy")
public class SelfWordsStudyController extends BaseController {

//	private final static Log log = LogFactory.getLog(SelfWordsStudyAction.class);
	
	@Resource(name="selfWordsStudyService")
	private SelfWordsStudyService selfWordsStudyService;


	@RequestMapping(value="manage",method={RequestMethod.GET,RequestMethod.POST})
	public String manage(){
		return "/selfWordsStudy/manage";
	}

	
	@RequestMapping(value="add",method={RequestMethod.GET,RequestMethod.POST})
    public String add() {
		return "/selfWordsStudy/add";
    }
    
	
	@RequestMapping(value="doAdd",method={RequestMethod.GET,RequestMethod.POST})
	 public @ResponseBody Map<String,Object> doAdd(SelfWordsStudy selfWordsStudy, ModelMap context) {
	    		CommonResult<SelfWordsStudy> result =selfWordsStudyService.addSelfWordsStudy(selfWordsStudy);
				return result.getReturnMap();
	    }
	 
	 

		@RequestMapping(value="update",method={RequestMethod.GET,RequestMethod.POST})
	    public String update(SelfWordsStudy selfWordsStudy, ModelMap context) {
			CommonResult<SelfWordsStudy> result = selfWordsStudyService.getSelfWordsStudyById(selfWordsStudy.getId());
			this.toVm(result, context);
			return "/selfWordsStudy/update";
	    }
	    
		
		@RequestMapping(value="doUpdate",method={RequestMethod.GET,RequestMethod.POST})
	    public @ResponseBody Map<String,Object> doUpdate(SelfWordsStudy selfWordsStudy, ModelMap context) {
			CommonResult<SelfWordsStudy> result = selfWordsStudyService.updateSelfWordsStudy(selfWordsStudy);
			return result.getReturnMap();
	    }
	    

		@RequestMapping(value="view",method={RequestMethod.GET,RequestMethod.POST})
		public String view(SelfWordsStudy selfWordsStudy, ModelMap context) {
			CommonResult<SelfWordsStudy> result = selfWordsStudyService.getSelfWordsStudyById(selfWordsStudy.getId());
			this.toVm(result, context);
			return "/selfWordsStudy/view";
	    }
	   
		
		@RequestMapping(value="doDelete",method={RequestMethod.GET,RequestMethod.POST})
	    public @ResponseBody  Map<String,Object>  doDelete(SelfWordsStudy selfWordsStudy) {
			CommonResult<SelfWordsStudy> result =selfWordsStudyService.deleteSelfWordsStudy(selfWordsStudy.getId());
			return result.getReturnMap();
	    }
	    
		@RequestMapping(value="list",method={RequestMethod.GET,RequestMethod.POST})
	    public String list(HttpServletRequest request, ModelMap context) {
			int pageSize = this.getPageSize(request,20,200);
            PageQuery pageQuery=new PageQuery(request,pageSize);
            CommonResult<List<SelfWordsStudy>> result = selfWordsStudyService.getSelfWordsStudyByPage(pageQuery);
			this.toVm(result, context);
			return "/selfWordsStudy/list";
	    }


}
