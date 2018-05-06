/**
 * Copyright(c) 2004-2018 www.jd.com
 * com.goldfish.controller.UnitWordsController.java
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
import com.goldfish.service.UnitWordsService;
import com.goldfish.domain.UnitWords;

@Controller
@RequestMapping("//unitWords")
public class UnitWordsController extends BaseController {

//	private final static Log log = LogFactory.getLog(UnitWordsAction.class);
	
	@Resource(name="unitWordsService")
	private UnitWordsService unitWordsService;


	@RequestMapping(value="manage",method={RequestMethod.GET,RequestMethod.POST})
	public String manage(){
		return "/unitWords/manage";
	}

	
	@RequestMapping(value="add",method={RequestMethod.GET,RequestMethod.POST})
    public String add() {
		return "/unitWords/add";
    }
    
	
	@RequestMapping(value="doAdd",method={RequestMethod.GET,RequestMethod.POST})
	 public @ResponseBody Map<String,Object> doAdd(UnitWords unitWords, ModelMap context) {
	    		CommonResult<UnitWords> result =unitWordsService.addUnitWords(unitWords);
				return result.getReturnMap();
	    }
	 
	 

		@RequestMapping(value="update",method={RequestMethod.GET,RequestMethod.POST})
	    public String update(UnitWords unitWords, ModelMap context) {
			CommonResult<UnitWords> result = unitWordsService.getUnitWordsById(unitWords.getId());
			this.toVm(result, context);
			return "/unitWords/update";
	    }
	    
		
		@RequestMapping(value="doUpdate",method={RequestMethod.GET,RequestMethod.POST})
	    public @ResponseBody Map<String,Object> doUpdate(UnitWords unitWords, ModelMap context) {
			CommonResult<UnitWords> result = unitWordsService.updateUnitWords(unitWords);
			return result.getReturnMap();
	    }
	    

		@RequestMapping(value="view",method={RequestMethod.GET,RequestMethod.POST})
		public String view(UnitWords unitWords, ModelMap context) {
			CommonResult<UnitWords> result = unitWordsService.getUnitWordsById(unitWords.getId());
			this.toVm(result, context);
			return "/unitWords/view";
	    }
	   
		
		@RequestMapping(value="doDelete",method={RequestMethod.GET,RequestMethod.POST})
	    public @ResponseBody  Map<String,Object>  doDelete(UnitWords unitWords) {
			CommonResult<UnitWords> result =unitWordsService.deleteUnitWords(unitWords.getId());
			return result.getReturnMap();
	    }
	    
		@RequestMapping(value="list",method={RequestMethod.GET,RequestMethod.POST})
	    public String list(HttpServletRequest request, ModelMap context) {
			int pageSize = this.getPageSize(request,20,200);
            PageQuery pageQuery=new PageQuery(request,pageSize);
            CommonResult<List<UnitWords>> result = unitWordsService.getUnitWordsByPage(pageQuery);
			this.toVm(result, context);
			return "/unitWords/list";
	    }


}
