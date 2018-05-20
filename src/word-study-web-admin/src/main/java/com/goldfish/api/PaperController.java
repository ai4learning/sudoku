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
import com.goldfish.service.PaperService;
import com.goldfish.domain.Paper;

@Controller
@RequestMapping("/paper")
public class PaperController extends BaseController {

//	private final static Log log = LogFactory.getLog(PaperAction.class);
	
	@Resource(name="paperService")
	private PaperService paperService;


	@RequestMapping(value="manage",method={RequestMethod.GET,RequestMethod.POST})
	public String manage(){
		return "/paper/manage";
	}

	
	@RequestMapping(value="add",method={RequestMethod.GET,RequestMethod.POST})
    public String add() {
		return "/paper/add";
    }
    
	
	@RequestMapping(value="doAdd",method={RequestMethod.GET,RequestMethod.POST})
	 public @ResponseBody Map<String,Object> doAdd(Paper paper, ModelMap context) {
	    		CommonResult<Paper> result =paperService.addPaper(paper);
				return result.getReturnMap();
	    }
	 
	 

		@RequestMapping(value="update",method={RequestMethod.GET,RequestMethod.POST})
	    public String update(Paper paper, ModelMap context) {
			CommonResult<Paper> result = paperService.getPaperById(paper.getId());
			this.toVm(result, context);
			return "/paper/update";
	    }
	    
		
		@RequestMapping(value="doUpdate",method={RequestMethod.GET,RequestMethod.POST})
	    public @ResponseBody Map<String,Object> doUpdate(Paper paper, ModelMap context) {
			CommonResult<Paper> result = paperService.updatePaper(paper);
			return result.getReturnMap();
	    }
	    

		@RequestMapping(value="view",method={RequestMethod.GET,RequestMethod.POST})
		public String view(Paper paper, ModelMap context) {
			CommonResult<Paper> result = paperService.getPaperById(paper.getId());
			this.toVm(result, context);
			return "/paper/view";
	    }
	   
		
		@RequestMapping(value="doDelete",method={RequestMethod.GET,RequestMethod.POST})
	    public @ResponseBody  Map<String,Object>  doDelete(Paper paper) {
			CommonResult<Paper> result =paperService.deletePaper(paper.getId());
			return result.getReturnMap();
	    }
	    
		@RequestMapping(value="list",method={RequestMethod.GET,RequestMethod.POST})
	    public String list(HttpServletRequest request, ModelMap context) {
			int pageSize = this.getPageSize(request,20,200);
            PageQuery pageQuery=new PageQuery(request,pageSize);
            CommonResult<List<Paper>> result = paperService.getPaperByPage(pageQuery);
			this.toVm(result, context);
			return "/paper/list";
	    }


}
