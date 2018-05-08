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
import com.goldfish.service.WordStudyStatisticService;
import com.goldfish.domain.WordStudyStatistic;

@Controller
@RequestMapping("/wordStudyStatistic")
public class WordStudyStatisticController extends BaseController {

//	private final static Log log = LogFactory.getLog(WordStudyStatisticAction.class);
	
	@Resource(name="wordStudyStatisticService")
	private WordStudyStatisticService wordStudyStatisticService;


	@RequestMapping(value="manage",method={RequestMethod.GET,RequestMethod.POST})
	public String manage(){
		return "/wordStudyStatistic/manage";
	}

	
	@RequestMapping(value="add",method={RequestMethod.GET,RequestMethod.POST})
    public String add() {
		return "/wordStudyStatistic/add";
    }
    
	
	@RequestMapping(value="doAdd",method={RequestMethod.GET,RequestMethod.POST})
	 public @ResponseBody Map<String,Object> doAdd(WordStudyStatistic wordStudyStatistic, ModelMap context) {
	    		CommonResult<WordStudyStatistic> result =wordStudyStatisticService.addWordStudyStatistic(wordStudyStatistic);
				return result.getReturnMap();
	    }
	 
	 

		@RequestMapping(value="update",method={RequestMethod.GET,RequestMethod.POST})
	    public String update(WordStudyStatistic wordStudyStatistic, ModelMap context) {
			CommonResult<WordStudyStatistic> result = wordStudyStatisticService.getWordStudyStatisticById(wordStudyStatistic.getId());
			this.toVm(result, context);
			return "/wordStudyStatistic/update";
	    }
	    
		
		@RequestMapping(value="doUpdate",method={RequestMethod.GET,RequestMethod.POST})
	    public @ResponseBody Map<String,Object> doUpdate(WordStudyStatistic wordStudyStatistic, ModelMap context) {
			CommonResult<WordStudyStatistic> result = wordStudyStatisticService.updateWordStudyStatistic(wordStudyStatistic);
			return result.getReturnMap();
	    }
	    

		@RequestMapping(value="view",method={RequestMethod.GET,RequestMethod.POST})
		public String view(WordStudyStatistic wordStudyStatistic, ModelMap context) {
			CommonResult<WordStudyStatistic> result = wordStudyStatisticService.getWordStudyStatisticById(wordStudyStatistic.getId());
			this.toVm(result, context);
			return "/wordStudyStatistic/view";
	    }
	   
		
		@RequestMapping(value="doDelete",method={RequestMethod.GET,RequestMethod.POST})
	    public @ResponseBody  Map<String,Object>  doDelete(WordStudyStatistic wordStudyStatistic) {
			CommonResult<WordStudyStatistic> result =wordStudyStatisticService.deleteWordStudyStatistic(wordStudyStatistic.getId());
			return result.getReturnMap();
	    }
	    
		@RequestMapping(value="list",method={RequestMethod.GET,RequestMethod.POST})
	    public String list(HttpServletRequest request, ModelMap context) {
			int pageSize = this.getPageSize(request,20,200);
            PageQuery pageQuery=new PageQuery(request,pageSize);
            CommonResult<List<WordStudyStatistic>> result = wordStudyStatisticService.getWordStudyStatisticByPage(pageQuery);
			this.toVm(result, context);
			return "/wordStudyStatistic/list";
	    }


}
