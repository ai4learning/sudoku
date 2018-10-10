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
import com.goldfish.service.UnitService;
import com.goldfish.domain.Unit;

@Controller
@RequestMapping("/unit")
public class UnitController extends BaseController {

//	private final static Log log = LogFactory.getLog(UnitAction.class);
	
	@Resource(name="unitService")
	private UnitService unitService;


	@RequestMapping(value="manage",method={RequestMethod.GET,RequestMethod.POST})
	public String manage(){
		return "/unit/manage";
	}

	
	@RequestMapping(value="add",method={RequestMethod.GET,RequestMethod.POST})
    public String add() {
		return "/unit/add";
    }
    
	
	@RequestMapping(value="doAdd",method={RequestMethod.GET,RequestMethod.POST})
	 public @ResponseBody Map<String,Object> doAdd(Unit unit, ModelMap context) {
	    		CommonResult<Unit> result =unitService.addUnit(unit);
				return result.getReturnMap();
	    }
	 
	 

		@RequestMapping(value="update",method={RequestMethod.GET,RequestMethod.POST})
	    public String update(Unit unit, ModelMap context) {
			CommonResult<Unit> result = unitService.getUnitById(unit.getId());
			this.toVm(result, context);
			return "/unit/update";
	    }
	    
		
		@RequestMapping(value="doUpdate",method={RequestMethod.GET,RequestMethod.POST})
	    public @ResponseBody Map<String,Object> doUpdate(Unit unit, ModelMap context) {
			CommonResult<Unit> result = unitService.updateUnit(unit);
			return result.getReturnMap();
	    }
	    

		@RequestMapping(value="view",method={RequestMethod.GET,RequestMethod.POST})
		public String view(Unit unit, ModelMap context) {
			CommonResult<Unit> result = unitService.getUnitById(unit.getId());
			this.toVm(result, context);
			return "/unit/view";
	    }
	   
		
		@RequestMapping(value="doDelete",method={RequestMethod.GET,RequestMethod.POST})
	    public @ResponseBody  Map<String,Object>  doDelete(Unit unit) {
			CommonResult<Unit> result =unitService.deleteUnit(unit.getId());
			return result.getReturnMap();
	    }
	    
		@RequestMapping(value="list",method={RequestMethod.GET,RequestMethod.POST})
	    public String list(HttpServletRequest request, ModelMap context) {
			int pageSize = this.getPageSize(request,20,200);
            PageQuery pageQuery=new PageQuery(request,pageSize);
            CommonResult<List<Unit>> result = unitService.getUnitByPage(pageQuery);
			this.toVm(result, context);
			return "/unit/list";
	    }


}
