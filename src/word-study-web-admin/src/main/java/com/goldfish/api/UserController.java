/**
 * Copyright(c) 2004-2018 bianfeng
 */


package com.goldfish.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.goldfish.common.DateFormatUtils;
import com.goldfish.domain.ClassGrade;
import com.goldfish.domain.Course;
import com.goldfish.service.ClassGradeService;
import com.goldfish.service.CourseService;
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
import com.goldfish.service.UserService;
import com.goldfish.domain.User;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Resource(name = "userService")
    private UserService userService;
    @Resource
    private ClassGradeService classGradeService;
    @Resource(name="courseService")
    private CourseService courseService;

    @RequestMapping(value = "manage", method = {RequestMethod.GET, RequestMethod.POST})
    public String manage() {
        return "/user/manage";
    }


    @RequestMapping(value = "add", method = {RequestMethod.GET, RequestMethod.POST})
    public String add() {
        return "/user/add";
    }


    @RequestMapping(value = "doAdd", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    Map<String, Object> doAdd(User user, ModelMap context) {
        CommonResult<User> result = userService.addUser(user);
        generateClassGrade(user);
        return result.getReturnMap();
    }


    @RequestMapping(value = "update", method = {RequestMethod.GET, RequestMethod.POST})
    public String update(User user, ModelMap context) {
        CommonResult<User> result = userService.getUserById(user.getId());
        int pageSize = this.getPageSize(null,200,2000);
        PageQuery pageQuery = new PageQuery(null,pageSize);
        CommonResult<List<Course>> courseResult = courseService.getCourseByPage(pageQuery);
        this.toVm(courseResult, context);
        this.toVm(result, context);
        String [] selectedCourse = result.getDefaultModel().getLessonIds().split(",");
        List<Integer> selectedCourseClone = new ArrayList<Integer>();
        for(int i = 0; i < selectedCourse.length; i++) {
            selectedCourseClone.add(Integer.valueOf(selectedCourse[i]));
        }
        context.put("selectedCourse", selectedCourseClone);
        return "/user/update";
    }


    @RequestMapping(value = "doUpdate", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    Map<String, Object> doUpdate(User user, ModelMap context) {
        CommonResult<User> result = userService.updateUser(user);
        generateClassGrade(user);
        return result.getReturnMap();
    }


    @RequestMapping(value = "view", method = {RequestMethod.GET, RequestMethod.POST})
    public String view(User user, ModelMap context) {
        CommonResult<User> result = userService.getUserById(user.getId());
        this.toVm(result, context);
        return "/user/view";
    }


    @RequestMapping(value = "doDelete", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    Map<String, Object> doDelete(User user) {
        CommonResult<User> result = userService.deleteUser(user.getId());
        return result.getReturnMap();
    }

    @RequestMapping(value = "list", method = {RequestMethod.GET, RequestMethod.POST})
    public String list(HttpServletRequest request, ModelMap context) {
        int pageSize = this.getPageSize(request, 20, 200);
        PageQuery pageQuery = new PageQuery(request, pageSize);
        CommonResult<List<User>> result = userService.getUserByPage(pageQuery);
        this.toVm(result, context);
        return "/user/list";
    }

    private void generateClassGrade(User user)
    {
        if (user != null && user.getCurrentClass() != null)
        {
            ClassGrade classGrade = classGradeService.getClassGradeById(user.getCurrentClass()).getDefaultModel();
            if (classGrade == null)
            {
                ClassGrade newClassGrade = new ClassGrade();
                newClassGrade.setId(user.getCurrentClass());
                newClassGrade.setStart(DateFormatUtils.removeHourMinuteSecond(DateFormatUtils.getNextDay(new Date())));
                newClassGrade.setEnd(DateFormatUtils.removeHourMinuteSecond(DateFormatUtils.getNextWeekDay(new Date())));
                classGradeService.addClassGrade(newClassGrade);
            }
        }
    }
}
