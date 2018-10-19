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
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangjingtao
 * @date 2018/10/19 0019.
 */
@Controller
@RequestMapping("/api/Ajax")
public class ApiAjaxController {
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
    BasicVO doAjaxAddClass(ClassVO classVO) {
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
    BasicVO doAjaxUpdateClass(ClassVO classVO) {
        return doAjaxAddClass(classVO);
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
        List<GetCoursesElementVO> elementVOS = new ArrayList<>();
        for (Course course : courseList){
            elementVOS.add(new GetCoursesElementVO(course.getId(),course.getBookName()));
        }
        getCoursesVO.setData(elementVOS);
        getCoursesVO.setSuccess(true);
        getCoursesVO.setMsg(CommonConstant.SUCCESS);
        return getCoursesVO;
    }

    @RequestMapping(value = "AjaxAddStudent", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    BasicVO doAjaxAddStudent(@RequestBody UpdateStudentVO updateStudentVO) {
        if (updateStudentVO==null){
            return new BasicVO(false, CommonConstant.PARAMETER_ERROR);
        }
        User user = new User();
        user.setPasswd(updateStudentVO.getPassword());
        user.setUserCode(updateStudentVO.getUserCode());
        user.setNikeName(updateStudentVO.getNickName());
        user.setLessonIds(updateStudentVO.getLessonIds());
        try {
            user.setCurrentClass(Long.valueOf(updateStudentVO.getCurrentClass()));
        }catch(Exception e){
            LogTypeEnum.DEFAULT.warn("批量增加学生，班级信息错误"+e);
        }
        user.setRoleType(1);
        /************************以下为非必要字段***********************/
        user.setUserCode(user.getUserId());
        user.setNikeName(user.getUserId());
        user.setAuthorityLevel(1);
        user.setUserState(0);
        user.setLevel(1);
        userService.addUser(user);
        return new BasicVO(true,CommonConstant.SUCCESS);
    }

    @RequestMapping(value = "AjaxUpdateStudent", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    BasicVO doAjaxUpdateStudent(@RequestBody UpdateStudentVO updateStudentVO) {
        if (updateStudentVO==null){
            return new BasicVO(false, CommonConstant.PARAMETER_ERROR);
        }
        User queryUser = new User();
        queryUser.setUserId(updateStudentVO.getUserId());
        User user = userService.getUnique(queryUser).getDefaultModel();
        if (user == null){
            return new BasicVO(false, CommonConstant.PARAMETER_ERROR);
        }
        user.setPasswd(updateStudentVO.getPassword());
        user.setUserCode(updateStudentVO.getUserCode());
        user.setNikeName(updateStudentVO.getNickName());
        user.setLessonIds(updateStudentVO.getLessonIds());
        try {
            user.setCurrentClass(Long.valueOf(updateStudentVO.getCurrentClass()));
        }catch(Exception e){
            LogTypeEnum.DEFAULT.warn("批量增加学生，班级信息错误"+e);
        }
        userService.updateUser(user);
        return new BasicVO(true,CommonConstant.SUCCESS);
    }

    @RequestMapping(value = "AjaxBatchAddStudent", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    BasicVO doAjaxBatchAddStudent(@RequestBody BatchAddStudentVO batchAddStudentVO) {
        if (batchAddStudentVO == null){
            return new BasicVO(false, CommonConstant.PARAMETER_ERROR);
        }
        for (int index=1;index<=batchAddStudentVO.getTotal();index++){
            User user = new User();
            user.setUserId(batchAddStudentVO.getPrefix()+String.valueOf(index));
            user.setLessonIds(batchAddStudentVO.getLessonIdList());
            try {
                user.setCurrentClass(Long.valueOf(batchAddStudentVO.getCurrentClass()));
            }catch(Exception e){
                LogTypeEnum.DEFAULT.warn("批量增加学生，班级信息错误"+e);
            }
            user.setPasswd(batchAddStudentVO.getPassword());
            user.setRoleType(1);
            /************************以下为非必要字段***********************/
            user.setUserCode(user.getUserId());
            user.setNikeName(user.getUserId());
            user.setAuthorityLevel(1);
            user.setUserState(0);
            user.setLevel(1);
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
        if (loginContext == null || StringUtils.isEmpty(loginContext.getTrainingId()) ||
                StringUtils.isEmpty(loginContext.getTrainingCode())) {
            return null;
        }
        try {
            // 获取用户登录信息
            return loginRecordService.getLoginRecordByTraining(loginContext.getTrainingId(), loginContext.getTrainingCode());
        } catch (Exception e) {
            LogTypeEnum.DEFAULT.error(e, "获取登录信息失败");
            return null;
        }
    }
}
