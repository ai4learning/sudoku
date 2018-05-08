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
import com.goldfish.service.UnitWordsStudyService;
import com.goldfish.domain.UnitWordsStudy;

@Controller
@RequestMapping("/unitWordsStudy")
public class UnitWordsStudyController extends BaseController {

//	private final static Log log = LogFactory.getLog(UnitWordsStudyAction.class);
	
	@Resource(name="unitWordsStudyService")
	private UnitWordsStudyService unitWordsStudyService;


	@RequestMapping(value="manage",method={RequestMethod.GET,RequestMethod.POST})
	public String manage(){
		return "/unitWordsStudy/manage";
	}

	
	@RequestMapping(value="add",method={RequestMethod.GET,RequestMethod.POST})
    public String add() {
		return "/unitWordsStudy/add";
    }
    
	
	@RequestMapping(value="doAdd",method={RequestMethod.GET,RequestMethod.POST})
	 public @ResponseBody Map<String,Object> doAdd(UnitWordsStudy unitWordsStudy, ModelMap context) {
	    		CommonResult<UnitWordsStudy> result =unitWordsStudyService.addUnitWordsStudy(unitWordsStudy);
				return result.getReturnMap();
	    }
	 
	 

		@RequestMapping(value="update",method={RequestMethod.GET,RequestMethod.POST})
	    public String update(UnitWordsStudy unitWordsStudy, ModelMap context) {
			CommonResult<UnitWordsStudy> result = unitWordsStudyService.getUnitWordsStudyById(unitWordsStudy.getId());
			this.toVm(result, context);
			return "/unitWordsStudy/update";
	    }
	    
		
		@RequestMapping(value="doUpdate",method={RequestMethod.GET,RequestMethod.POST})
	    public @ResponseBody Map<String,Object> doUpdate(UnitWordsStudy unitWordsStudy, ModelMap context) {
			CommonResult<UnitWordsStudy> result = unitWordsStudyService.updateUnitWordsStudy(unitWordsStudy);
			return result.getReturnMap();
	    }
	    

		@RequestMapping(value="view",method={RequestMethod.GET,RequestMethod.POST})
		public String view(UnitWordsStudy unitWordsStudy, ModelMap context) {
			CommonResult<UnitWordsStudy> result = unitWordsStudyService.getUnitWordsStudyById(unitWordsStudy.getId());
			this.toVm(result, context);
			return "/unitWordsStudy/view";
	    }
	   
		
		@RequestMapping(value="doDelete",method={RequestMethod.GET,RequestMethod.POST})
	    public @ResponseBody  Map<String,Object>  doDelete(UnitWordsStudy unitWordsStudy) {
			CommonResult<UnitWordsStudy> result =unitWordsStudyService.deleteUnitWordsStudy(unitWordsStudy.getId());
			return result.getReturnMap();
	    }
	    
		@RequestMapping(value="list",method={RequestMethod.GET,RequestMethod.POST})
	    public String list(HttpServletRequest request, ModelMap context) {
			int pageSize = this.getPageSize(request,20,200);
            PageQuery pageQuery=new PageQuery(request,pageSize);
            CommonResult<List<UnitWordsStudy>> result = unitWordsStudyService.getUnitWordsStudyByPage(pageQuery);
			this.toVm(result, context);
			return "/unitWordsStudy/list";
	    }


}
