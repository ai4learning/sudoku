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
import com.goldfish.service.LoginRecordService;
import com.goldfish.domain.LoginRecord;

@Controller
@RequestMapping("/loginRecord")
public class LoginRecordController extends BaseController {

//	private final static Log log = LogFactory.getLog(LoginRecordAction.class);
	
	@Resource(name="loginRecordService")
	private LoginRecordService loginRecordService;


	@RequestMapping(value="manage",method={RequestMethod.GET,RequestMethod.POST})
	public String manage(){
		return "/loginRecord/manage";
	}

	
	@RequestMapping(value="add",method={RequestMethod.GET,RequestMethod.POST})
    public String add() {
		return "/loginRecord/add";
    }
    
	
	@RequestMapping(value="doAdd",method={RequestMethod.GET,RequestMethod.POST})
	 public @ResponseBody Map<String,Object> doAdd(LoginRecord loginRecord, ModelMap context) {
	    		CommonResult<LoginRecord> result =loginRecordService.addLoginRecord(loginRecord);
				return result.getReturnMap();
	    }
	 
	 

		@RequestMapping(value="update",method={RequestMethod.GET,RequestMethod.POST})
	    public String update(LoginRecord loginRecord, ModelMap context) {
			CommonResult<LoginRecord> result = loginRecordService.getLoginRecordById(loginRecord.getId());
			this.toVm(result, context);
			return "/loginRecord/update";
	    }
	    
		
		@RequestMapping(value="doUpdate",method={RequestMethod.GET,RequestMethod.POST})
	    public @ResponseBody Map<String,Object> doUpdate(LoginRecord loginRecord, ModelMap context) {
			CommonResult<LoginRecord> result = loginRecordService.updateLoginRecord(loginRecord);
			return result.getReturnMap();
	    }
	    

		@RequestMapping(value="view",method={RequestMethod.GET,RequestMethod.POST})
		public String view(LoginRecord loginRecord, ModelMap context) {
			CommonResult<LoginRecord> result = loginRecordService.getLoginRecordById(loginRecord.getId());
			this.toVm(result, context);
			return "/loginRecord/view";
	    }
	   
		
		@RequestMapping(value="doDelete",method={RequestMethod.GET,RequestMethod.POST})
	    public @ResponseBody  Map<String,Object>  doDelete(LoginRecord loginRecord) {
			CommonResult<LoginRecord> result =loginRecordService.deleteLoginRecord(loginRecord.getId());
			return result.getReturnMap();
	    }
	    
		@RequestMapping(value="list",method={RequestMethod.GET,RequestMethod.POST})
	    public String list(HttpServletRequest request, ModelMap context) {
			int pageSize = this.getPageSize(request,20,200);
            PageQuery pageQuery=new PageQuery(request,pageSize);
            CommonResult<List<LoginRecord>> result = loginRecordService.getLoginRecordByPage(pageQuery);
			this.toVm(result, context);
			return "/loginRecord/list";
	    }


}
