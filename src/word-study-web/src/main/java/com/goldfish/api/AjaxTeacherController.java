package com.goldfish.api;

import com.goldfish.common.CommonResult;
import com.goldfish.common.DateFormatUtils;
import com.goldfish.common.log.LogTypeEnum;
import com.goldfish.constant.CommonConstant;
import com.goldfish.constant.State;
import com.goldfish.domain.ClassGrade;
import com.goldfish.domain.Course;
import com.goldfish.domain.LoginRecord;
import com.goldfish.domain.User;
import com.goldfish.service.ClassGradeService;
import com.goldfish.service.CourseService;
import com.goldfish.service.LoginRecordService;
import com.goldfish.service.UserService;
import com.goldfish.vo.BasicVO;
import com.goldfish.vo.teacher.*;
import com.goldfish.web.interceptor.servlet.context.LoginContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zhangjingtao
 * @date 2018/10/19 0019.
 */
@Controller
@RequestMapping("/api/Ajax")
public class AjaxTeacherController {
    @Resource(name="classGradeService")
    private ClassGradeService classGradeService;
    @Resource(name="userService")
    private UserService userService;
    @Resource
    protected LoginRecordService loginRecordService;
    @Resource
    private CourseService courseService;

    @RequestMapping(value = "AjaxGetClassList", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    GetClassListVO doAjaxGetClassList() {
        GetClassListVO getClassListVO = new GetClassListVO();
        User teacher = this.getUserInfo();
        if (teacher == null){
            getClassListVO.setData(null);
            getClassListVO.setSuccess(false);
            getClassListVO.setMsg(CommonConstant.FAIL);
            return getClassListVO;
        }
        ClassGrade queryClassGrade = new ClassGrade();
        queryClassGrade.setTeacherId(teacher.getId());
        List<ClassGrade> classGradeList = classGradeService.getListByExample(queryClassGrade).getDefaultModel();
        getClassListVO.setData(classGradeList);
        getClassListVO.setMsg(CommonConstant.SUCCESS);
        getClassListVO.setSuccess(true);
        return getClassListVO;
    }

    @RequestMapping(value = "AjaxAddClass", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    BasicVO doAjaxAddClass(@RequestBody ClassVO classVO) {
        User teacher = this.getUserInfo();
        if (classVO == null || teacher == null){
            return new BasicVO(false, CommonConstant.PARAMETER_ERROR);
        }
        ClassGrade classGrade = new ClassGrade();
        classGrade.setTeacherId(teacher.getId());
        classGrade.setName(classVO.getName());
        classGrade.setStart(DateFormatUtils.parse(classVO.getStart(),null));
        classGrade.setEnd(DateFormatUtils.parse(classVO.getEnd(),null));
        classGradeService.addClassGrade(classGrade);
        return new BasicVO(true,CommonConstant.SUCCESS);
    }

    @RequestMapping(value = "AjaxUpdateClass", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    BasicVO doAjaxUpdateClass(@RequestBody ClassVO classVO) {
        User teacher = this.getUserInfo();
        if (classVO == null || teacher == null){
            return new BasicVO(false, CommonConstant.PARAMETER_ERROR);
        }
        ClassGrade classGrade = classGradeService.getClassGradeById(classVO.getId()).getDefaultModel();
        classGrade.setTeacherId(teacher.getId());
        classGrade.setName(classVO.getName());
        classGrade.setStart(DateFormatUtils.parse(classVO.getStart(),null));
        classGrade.setEnd(DateFormatUtils.parse(classVO.getEnd(),null));
        classGradeService.updateClassGrade(classGrade);
        return new BasicVO(true,CommonConstant.SUCCESS);
    }

    @RequestMapping(value = "AjaxGetCourses", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    GetCoursesVO doAjaxGetCourses() {
        User teacher = this.getUserInfo();
        GetCoursesVO getCoursesVO = new GetCoursesVO();
        if (teacher == null){
            getCoursesVO.setData(null);
            getCoursesVO.setMsg(CommonConstant.FAIL);
            getCoursesVO.setSuccess(false);
            return getCoursesVO;
        }
        List<Course> courseList = courseService.getListByExample(new Course()).getDefaultModel();
        getCoursesVO.setData(courseList);
        getCoursesVO.setSuccess(true);
        getCoursesVO.setMsg(CommonConstant.SUCCESS);
        return getCoursesVO;
    }

    @RequestMapping(value = "AjaxGetStudents", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    GetStudentsVO doAjaxGetStudents() {
        User teacher = this.getUserInfo();
        GetStudentsVO getStudentsVO = new GetStudentsVO();
        if (teacher == null){
            getStudentsVO.setData(null);
            getStudentsVO.setMsg(CommonConstant.FAIL);
            getStudentsVO.setSuccess(false);
            return getStudentsVO;
        }
        User queryStudent = new User();
        queryStudent.setCurrentTeacher(teacher.getId());
        List<User> studentList = userService.getListByExample(queryStudent).getDefaultModel();
        getStudentsVO.setData(studentList);
        getStudentsVO.setSuccess(true);
        getStudentsVO.setMsg(CommonConstant.SUCCESS);
        return getStudentsVO;
    }

    @RequestMapping(value = "AjaxAddStudent", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    BasicVO doAjaxAddStudent(@RequestBody UpdateStudentVO updateStudentVO) {
        User teacher = this.getUserInfo();
        if (updateStudentVO == null || teacher == null) {
            return new BasicVO(false, CommonConstant.PARAMETER_ERROR);
        }
        User user = new User();
        user.setUserId(updateStudentVO.getUserId());
        user.setPasswd(updateStudentVO.getPasswd());
        user.setLessonIds(updateStudentVO.getLessonIds());
        user.setCurrentClass(updateStudentVO.getCurrentClass());
        user.setCurrentTeacher(teacher.getId());
        String userCode = updateStudentVO.getUserCode();
        if(userCode == null) {
            userCode = user.getUserId();
        }
        user.setUserCode(userCode);
        String nikeName = updateStudentVO.getNikeName();
        if(nikeName == null) {
            nikeName = user.getUserId();
        }
        user.setNikeName(nikeName);
        user.setAuthorityLevel(1);
        user.setUserState(updateStudentVO.getUserState());
        user.setState(updateStudentVO.getState());
        user.setRoleType(1);
        user.setLevel(1);
        userService.addUser(user);
        return new BasicVO(true,CommonConstant.SUCCESS);
    }

    @RequestMapping(value = "AjaxUpdateStudent", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    BasicVO doAjaxUpdateStudent(@RequestBody UpdateStudentVO updateStudentVO) {
        User teacher = this.getUserInfo();
        if (updateStudentVO == null || teacher == null){
            return new BasicVO(false, CommonConstant.PARAMETER_ERROR);
        }
        User queryUser = new User();
        queryUser.setUserId(updateStudentVO.getUserId());
        User user = userService.getUnique(queryUser).getDefaultModel();
        if (user == null){
            return new BasicVO(false, CommonConstant.PARAMETER_ERROR);
        }
        user.setPasswd(updateStudentVO.getPasswd());
        user.setUserCode(updateStudentVO.getUserCode());
        user.setNikeName(updateStudentVO.getNikeName());
        user.setLessonIds(updateStudentVO.getLessonIds());
        user.setCurrentTeacher(teacher.getId());
        user.setCurrentClass(updateStudentVO.getCurrentClass());
        user.setUserState(updateStudentVO.getUserState());
        user.setState(updateStudentVO.getState());
        userService.updateUser(user);
        return new BasicVO(true,CommonConstant.SUCCESS);
    }

    /**
     * TODO 批量Insert操作需要优化，使用for循环单个insert效率低
     * @param batchAddStudentVO
     * @return
     */
    @RequestMapping(value = "AjaxBatchAddStudent", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    BasicVO doAjaxBatchAddStudent(@RequestBody BatchAddStudentVO batchAddStudentVO) {
        User teacher = this.getUserInfo();
        if (batchAddStudentVO == null || teacher == null){
            return new BasicVO(false, CommonConstant.PARAMETER_ERROR);
        }
        for (int index=1;index<=batchAddStudentVO.getTotal();index++){
            User user = new User();
            user.setUserId(batchAddStudentVO.getPrefix()+String.valueOf(index));
            user.setLessonIds(String.join(",", batchAddStudentVO.getLessonIds()));
            user.setCurrentClass(Long.valueOf(batchAddStudentVO.getCurrentClass()));
            user.setPasswd(batchAddStudentVO.getPasswd());
            user.setRoleType(1);
            user.setCurrentTeacher(teacher.getId());
            user.setUserCode(user.getUserId());
            user.setNikeName(user.getUserId());
            user.setAuthorityLevel(1);
            user.setUserState(0);
            user.setLevel(1);
            user.setState(1);
            userService.addUser(user);
        }
        return new BasicVO(true,CommonConstant.SUCCESS);
    }

    protected User getUserInfo()
    {
        // 1.根据登录获取用户信息
        LoginRecord loginRecord = getLoginRecord();
        if (loginRecord == null) {
            LogTypeEnum.DEFAULT.error("未获取到用户信息");
            return null;
        }
        Integer userId = loginRecord.getUserId();
        User userQuery = new User();
        userQuery.setId(Long.valueOf(String.valueOf(userId)));
        userQuery.setState(State.VALID.getState());
        CommonResult<User> userResult = userService.getUnique(userQuery);
        if (userResult == null || !userResult.isSuccess()) {
            LogTypeEnum.DEFAULT.error("未获取到用户信息");
            return null;
        }
        return userResult.getDefaultModel();
    }

    protected LoginRecord getLoginRecord() {
        LoginContext loginContext = LoginContext.getLoginContext();
        if (loginContext == null) {
            return null;
        }
        try {
            // 获取用户登录信息
            return loginRecordService.getLoginRecordByToken(loginContext.getUserName(),loginContext.getToken());
        } catch (Exception e) {
            LogTypeEnum.DEFAULT.error(e, "获取登录信息失败");
            return null;
        }
    }

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
}
