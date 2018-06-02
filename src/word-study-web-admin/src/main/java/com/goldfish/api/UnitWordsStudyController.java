/**
 * Copyright(c) 2004-2018 bianfeng
 */


 package com.goldfish.api;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.goldfish.domain.UnitStudy;
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
import com.goldfish.service.UnitStudyService;

@Controller
@RequestMapping("/unitWordsStudy")
public class UnitWordsStudyController extends BaseController {

//	private final static Log log = LogFactory.getLog(UnitWordsStudyAction.class);
	
	@Resource(name= "unitStudyService")
	private UnitStudyService unitStudyService;


	@RequestMapping(value="manage",method={RequestMethod.GET,RequestMethod.POST})
	public String manage(){
		return "/unitWordsStudy/manage";
	}

	
	@RequestMapping(value="add",method={RequestMethod.GET,RequestMethod.POST})
    public String add() {
		return "/unitWordsStudy/add";
    }
    
	
	@RequestMapping(value="doAdd",method={RequestMethod.GET,RequestMethod.POST})
	 public @ResponseBody Map<String,Object> doAdd(UnitStudy unitStudy, ModelMap context) {
	    		CommonResult<UnitStudy> result = unitStudyService.addUnitWordsStudy(unitStudy);
				return result.getReturnMap();
	    }
	 
	 

		@RequestMapping(value="update",method={RequestMethod.GET,RequestMethod.POST})
	    public String update(UnitStudy unitStudy, ModelMap context) {
			CommonResult<UnitStudy> result = unitStudyService.getUnitWordsStudyById(unitStudy.getId());
			this.toVm(result, context);
			return "/unitWordsStudy/update";
	    }
	    
		
		@RequestMapping(value="doUpdate",method={RequestMethod.GET,RequestMethod.POST})
	    public @ResponseBody Map<String,Object> doUpdate(UnitStudy unitStudy, ModelMap context) {
			CommonResult<UnitStudy> result = unitStudyService.updateUnitWordsStudy(unitStudy);
			return result.getReturnMap();
	    }
	    

		@RequestMapping(value="view",method={RequestMethod.GET,RequestMethod.POST})
		public String view(UnitStudy unitStudy, ModelMap context) {
			CommonResult<UnitStudy> result = unitStudyService.getUnitWordsStudyById(unitStudy.getId());
			this.toVm(result, context);
			return "/unitWordsStudy/view";
	    }
	   
		
		@RequestMapping(value="doDelete",method={RequestMethod.GET,RequestMethod.POST})
	    public @ResponseBody  Map<String,Object>  doDelete(UnitStudy unitStudy) {
			CommonResult<UnitStudy> result = unitStudyService.deleteUnitWordsStudy(unitStudy.getId());
			return result.getReturnMap();
	    }
	    
		@RequestMapping(value="list",method={RequestMethod.GET,RequestMethod.POST})
	    public String list(HttpServletRequest request, ModelMap context) {
			int pageSize = this.getPageSize(request,20,200);
            PageQuery pageQuery=new PageQuery(request,pageSize);
            CommonResult<List<UnitStudy>> result = unitStudyService.getUnitWordsStudyByPage(pageQuery);
			this.toVm(result, context);
			return "/unitWordsStudy/list";
	    }


}
