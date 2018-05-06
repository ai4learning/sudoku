/**
 * Copyright(c) 2004-2018 www.jd.com
 * com.goldfish.controller.ActivateCodeController.java
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
import com.goldfish.service.ActivateCodeService;
import com.goldfish.domain.ActivateCode;

@Controller
@RequestMapping("//activateCode")
public class ActivateCodeController extends BaseController {

//	private final static Log log = LogFactory.getLog(ActivateCodeAction.class);
	
	@Resource(name="activateCodeService")
	private ActivateCodeService activateCodeService;


	@RequestMapping(value="manage",method={RequestMethod.GET,RequestMethod.POST})
	public String manage(){
		return "/activateCode/manage";
	}

	
	@RequestMapping(value="add",method={RequestMethod.GET,RequestMethod.POST})
    public String add() {
		return "/activateCode/add";
    }
    
	
	@RequestMapping(value="doAdd",method={RequestMethod.GET,RequestMethod.POST})
	 public @ResponseBody Map<String,Object> doAdd(ActivateCode activateCode, ModelMap context) {
	    		CommonResult<ActivateCode> result =activateCodeService.addActivateCode(activateCode);
				return result.getReturnMap();
	    }
	 
	 

		@RequestMapping(value="update",method={RequestMethod.GET,RequestMethod.POST})
	    public String update(ActivateCode activateCode, ModelMap context) {
			CommonResult<ActivateCode> result = activateCodeService.getActivateCodeById(activateCode.getId());
			this.toVm(result, context);
			return "/activateCode/update";
	    }
	    
		
		@RequestMapping(value="doUpdate",method={RequestMethod.GET,RequestMethod.POST})
	    public @ResponseBody Map<String,Object> doUpdate(ActivateCode activateCode, ModelMap context) {
			CommonResult<ActivateCode> result = activateCodeService.updateActivateCode(activateCode);
			return result.getReturnMap();
	    }
	    

		@RequestMapping(value="view",method={RequestMethod.GET,RequestMethod.POST})
		public String view(ActivateCode activateCode, ModelMap context) {
			CommonResult<ActivateCode> result = activateCodeService.getActivateCodeById(activateCode.getId());
			this.toVm(result, context);
			return "/activateCode/view";
	    }
	   
		
		@RequestMapping(value="doDelete",method={RequestMethod.GET,RequestMethod.POST})
	    public @ResponseBody  Map<String,Object>  doDelete(ActivateCode activateCode) {
			CommonResult<ActivateCode> result =activateCodeService.deleteActivateCode(activateCode.getId());
			return result.getReturnMap();
	    }
	    
		@RequestMapping(value="list",method={RequestMethod.GET,RequestMethod.POST})
	    public String list(HttpServletRequest request, ModelMap context) {
			int pageSize = this.getPageSize(request,20,200);
            PageQuery pageQuery=new PageQuery(request,pageSize);
            CommonResult<List<ActivateCode>> result = activateCodeService.getActivateCodeByPage(pageQuery);
			this.toVm(result, context);
			return "/activateCode/list";
	    }


}
