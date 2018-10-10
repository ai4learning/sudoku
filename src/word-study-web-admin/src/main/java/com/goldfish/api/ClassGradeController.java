package com.goldfish.api;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldfish.common.CommonResult;
import com.goldfish.common.PageQuery;
import com.goldfish.web.base.BaseController;
import com.goldfish.service.ClassGradeService;
import com.goldfish.domain.ClassGrade;


/**
 * @author Administrator
 */
@Controller
@RequestMapping("/classGrade")
public class ClassGradeController extends BaseController {

//	private final static Log log = LogFactory.getLog(ClassGradeAction.class);

    @Resource(name="classGradeService")
    private ClassGradeService classGradeService;


    @RequestMapping(value="manage",method={RequestMethod.GET,RequestMethod.POST})
    public String manage(){
        return "/classGrade/manage";
    }


    @RequestMapping(value="add",method={RequestMethod.GET,RequestMethod.POST})
    public String add() {
        return "/classGrade/add";
    }


    @RequestMapping(value="doAdd",method={RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody Map<String,Object> doAdd(ClassGrade classGrade, ModelMap context) {
        CommonResult<ClassGrade> result =classGradeService.addClassGrade(classGrade);
        return result.getReturnMap();
    }



    @RequestMapping(value="update",method={RequestMethod.GET,RequestMethod.POST})
    public String update(ClassGrade classGrade, ModelMap context) {
        CommonResult<ClassGrade> result = classGradeService.getClassGradeById(classGrade.getId());
        this.toVm(result, context);
        return "/classGrade/update";
    }


    @RequestMapping(value="doUpdate",method={RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody Map<String,Object> doUpdate(ClassGrade classGrade, ModelMap context) {
        CommonResult<ClassGrade> result = classGradeService.updateClassGrade(classGrade);
        return result.getReturnMap();
    }


    @RequestMapping(value="view",method={RequestMethod.GET,RequestMethod.POST})
    public String view(ClassGrade classGrade, ModelMap context) {
        CommonResult<ClassGrade> result = classGradeService.getClassGradeById(classGrade.getId());
        this.toVm(result, context);
        return "/classGrade/view";
    }


    @RequestMapping(value="doDelete",method={RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody  Map<String,Object>  doDelete(ClassGrade classGrade) {
        CommonResult<ClassGrade> result =classGradeService.deleteClassGrade(classGrade.getId());
        return result.getReturnMap();
    }

    @RequestMapping(value="list",method={RequestMethod.GET,RequestMethod.POST})
    public String list(HttpServletRequest request, ModelMap context) {
        int pageSize = this.getPageSize(request,20,200);
        PageQuery pageQuery=new PageQuery(request,pageSize);
        CommonResult<List<ClassGrade>> result = classGradeService.getClassGradeByPage(pageQuery);
        this.toVm(result, context);
        return "/classGrade/list";
    }


}
