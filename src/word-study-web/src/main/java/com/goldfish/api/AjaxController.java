package com.goldfish.api;

import com.goldfish.common.CommonResult;
import com.goldfish.dao.cache.local.LoginRecordContext;
import com.goldfish.domain.LoginRecord;
import com.goldfish.manager.LoginRecordManager;
import com.goldfish.web.interceptor.servlet.context.LoginContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by John on 2018/5/20 0020.
 */
@Controller
@RequestMapping("/api/Ajax")
public class AjaxController extends AjaxExamController{
    /**
     * 用户登录心跳
     * @param context
     * @return
     *
     * {"success":true,"UserId":"jiaoshi"}
     */
    @RequestMapping(value="AjaxHeartBeat",method={RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody
    Map<String,Object> doAjaxHeartBeat(ModelMap context) {
        CommonResult result = new CommonResult();
        LoginRecord loginRecord = getLoginRecord();
        if (loginRecord == null) {
            result.addDefaultModel("UserId", "");
            return result.getReturnMap();
        }
        result.addDefaultModel("UserId", loginRecord.getUserName());
        result.setSuccess(true);
        return result.getReturnMap();
    }

//    @RequestMapping(value="view",method={RequestMethod.GET,RequestMethod.POST})
//    public String view(Course course, ModelMap context) {
//        CommonResult<Course> result = courseService.getCourseById(course.getId());
//        this.toVm(result, context);
//        return "/course/view";
//    }











}
