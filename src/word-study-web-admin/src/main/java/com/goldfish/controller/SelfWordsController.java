/**
 * Copyright(c) 2004-2018 www.jd.com
 * com.goldfish.controller.SelfWordsController.java
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
import com.goldfish.service.SelfWordsService;
import com.goldfish.domain.SelfWords;

@Controller
@RequestMapping("//selfWords")
public class SelfWordsController extends BaseController {

//	private final static Log log = LogFactory.getLog(SelfWordsAction.class);
	
	@Resource(name="selfWordsService")
	private SelfWordsService selfWordsService;


	@RequestMapping(value="manage",method={RequestMethod.GET,RequestMethod.POST})
	public String manage(){
		return "/selfWords/manage";
	}

	
	@RequestMapping(value="add",method={RequestMethod.GET,RequestMethod.POST})
    public String add() {
		return "/selfWords/add";
    }
    
	
	@RequestMapping(value="doAdd",method={RequestMethod.GET,RequestMethod.POST})
	 public @ResponseBody Map<String,Object> doAdd(SelfWords selfWords, ModelMap context) {
	    		CommonResult<SelfWords> result =selfWordsService.addSelfWords(selfWords);
				return result.getReturnMap();
	    }
	 
	 

		@RequestMapping(value="update",method={RequestMethod.GET,RequestMethod.POST})
	    public String update(SelfWords selfWords, ModelMap context) {
			CommonResult<SelfWords> result = selfWordsService.getSelfWordsById(selfWords.getId());
			this.toVm(result, context);
			return "/selfWords/update";
	    }
	    
		
		@RequestMapping(value="doUpdate",method={RequestMethod.GET,RequestMethod.POST})
	    public @ResponseBody Map<String,Object> doUpdate(SelfWords selfWords, ModelMap context) {
			CommonResult<SelfWords> result = selfWordsService.updateSelfWords(selfWords);
			return result.getReturnMap();
	    }
	    

		@RequestMapping(value="view",method={RequestMethod.GET,RequestMethod.POST})
		public String view(SelfWords selfWords, ModelMap context) {
			CommonResult<SelfWords> result = selfWordsService.getSelfWordsById(selfWords.getId());
			this.toVm(result, context);
			return "/selfWords/view";
	    }
	   
		
		@RequestMapping(value="doDelete",method={RequestMethod.GET,RequestMethod.POST})
	    public @ResponseBody  Map<String,Object>  doDelete(SelfWords selfWords) {
			CommonResult<SelfWords> result =selfWordsService.deleteSelfWords(selfWords.getId());
			return result.getReturnMap();
	    }
	    
		@RequestMapping(value="list",method={RequestMethod.GET,RequestMethod.POST})
	    public String list(HttpServletRequest request, ModelMap context) {
			int pageSize = this.getPageSize(request,20,200);
            PageQuery pageQuery=new PageQuery(request,pageSize);
            CommonResult<List<SelfWords>> result = selfWordsService.getSelfWordsByPage(pageQuery);
			this.toVm(result, context);
			return "/selfWords/list";
	    }


}
