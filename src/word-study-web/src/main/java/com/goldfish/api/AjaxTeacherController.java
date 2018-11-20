package com.goldfish.api;

import com.goldfish.common.CommonResult;
import com.goldfish.common.DateFormatUtils;
import com.goldfish.common.PageQuery;
import com.goldfish.common.log.LogTypeEnum;
import com.goldfish.constant.CommonConstant;
import com.goldfish.constant.State;
import com.goldfish.domain.*;
import com.goldfish.service.*;
import com.goldfish.vo.BasicVO;
import com.goldfish.vo.course.BookStudyVO;
import com.goldfish.vo.course.BookVO;
import com.goldfish.vo.teacher.*;
import com.goldfish.vo.unit.RichUnitStudyVO;
import com.goldfish.vo.unit.UnitStudyVO;
import com.goldfish.web.interceptor.servlet.context.LoginContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author zhangjingtao
 * @date 2018/10/19 0019.
 */
@Controller
@RequestMapping("/api/teacher")
public class AjaxTeacherController {
    @Resource(name="classGradeService")
    private ClassGradeService classGradeService;
    @Resource(name="userService")
    private UserService userService;
    @Resource
    protected LoginRecordService loginRecordService;
    @Resource
    private CourseService courseService;
    @Resource
    private UnitWordsService unitWordsService;

    @RequestMapping(value = "AjaxGetClassList", method = {RequestMethod.GET})
    public @ResponseBody
    GetClassListVO doAjaxGetClassList() {
        GetClassListVO getClassListVO = new GetClassListVO();
        User teacher = this.getUserInfo();
        if (teacher == null || teacher.getRoleType()==1){
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

    @RequestMapping(value = "AjaxAddClass", method = {RequestMethod.POST})
    public @ResponseBody
    BasicVO doAjaxAddClass(@RequestBody ClassVO classVO) {
        User teacher = this.getUserInfo();
        if (classVO == null || teacher == null || teacher.getRoleType()==1){
            return new BasicVO(false, CommonConstant.FAIL);
        }
        ClassGrade classGrade = new ClassGrade();
        classGrade.setTeacherId(teacher.getId());
        classGrade.setName(classVO.getName());
        classGrade.setStart(DateFormatUtils.parse(classVO.getStart(),null));
        classGrade.setEnd(DateFormatUtils.parse(classVO.getEnd(),null));
        classGradeService.addClassGrade(classGrade);
        return new BasicVO(true,CommonConstant.SUCCESS);
    }

    @RequestMapping(value = "AjaxUpdateClass", method = {RequestMethod.POST})
    public @ResponseBody
    BasicVO doAjaxUpdateClass(@RequestBody ClassVO classVO) {
        User teacher = this.getUserInfo();
        if (classVO == null || teacher == null || teacher.getRoleType()==1){
            return new BasicVO(false, CommonConstant.FAIL);
        }
        ClassGrade classGrade = classGradeService.getClassGradeById(classVO.getId()).getDefaultModel();
        classGrade.setTeacherId(teacher.getId());
        classGrade.setName(classVO.getName());
        classGrade.setStart(DateFormatUtils.parse(classVO.getStart(),null));
        classGrade.setEnd(DateFormatUtils.parse(classVO.getEnd(),null));
        classGradeService.updateClassGrade(classGrade);
        return new BasicVO(true,CommonConstant.SUCCESS);
    }

    @RequestMapping(value = "AjaxGetCourses", method = {RequestMethod.GET})
    public @ResponseBody
    GetCoursesVO doAjaxGetCourses(@RequestParam(required = false) String bookName) {
        User teacher = this.getUserInfo();
        GetCoursesVO getCoursesVO = new GetCoursesVO();
        if (teacher == null || teacher.getRoleType()==1){
            getCoursesVO.setData(null);
            getCoursesVO.setMsg(CommonConstant.FAIL);
            getCoursesVO.setSuccess(false);
            return getCoursesVO;
        }

        List<Course> courseList = null;
        if (StringUtils.isEmpty(bookName)){
            courseList = courseService.getListByExample(new Course()).getDefaultModel();
        }else{
            courseList = courseService.getCourseLikeBookName(bookName).getDefaultModel();
        }
        getCoursesVO.setData(courseList);
        getCoursesVO.setSuccess(true);
        getCoursesVO.setMsg(CommonConstant.SUCCESS);
        return getCoursesVO;
    }

    @RequestMapping(value = "AjaxGetStudents", method = {RequestMethod.GET})
    public @ResponseBody
    GetStudentsVO doAjaxGetStudents(@RequestParam(required = false) String userId,
                                    @RequestParam(required = false) Long currentClass,
                                    @RequestParam(required = false) Integer userState,
                                    @RequestParam(required = false) Integer state) {
        User teacher = this.getUserInfo();
        GetStudentsVO getStudentsVO = new GetStudentsVO();
        if (teacher == null || teacher.getRoleType()==1){
            getStudentsVO.setData(null);
            getStudentsVO.setMsg(CommonConstant.FAIL);
            getStudentsVO.setSuccess(false);
            return getStudentsVO;
        }
        List<User> studentList = null;
        if (StringUtils.isEmpty(userId) && currentClass==null && userState==null && state==null){
            User queryStudent = new User();
            queryStudent.setCurrentTeacher(teacher.getId());
            studentList = userService.getListByExample(queryStudent).getDefaultModel();
        }else{
            Map<String, Object> params = new HashMap<>();
            params.put("currentClass",currentClass);
            params.put("userId",userId);
            params.put("userState",userState);
            params.put("state",state);
            params.put("currentTeacher",teacher.getId());
            studentList = userService.getUserLike(new PageQuery(Integer.MAX_VALUE,params)).getDefaultModel();
        }
        getStudentsVO.setData(studentList);
        getStudentsVO.setSuccess(true);
        getStudentsVO.setMsg(CommonConstant.SUCCESS);
        return getStudentsVO;
    }

    @RequestMapping(value = "AjaxAddStudent", method = {RequestMethod.POST})
    public @ResponseBody
    BasicVO doAjaxAddStudent(@RequestBody UpdateStudentVO updateStudentVO) {
        User teacher = this.getUserInfo();
        if (updateStudentVO == null || teacher == null || teacher.getRoleType()==1) {
            return new BasicVO(false, CommonConstant.FAIL);
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

    @RequestMapping(value = "AjaxUpdateStudent", method = {RequestMethod.POST})
    public @ResponseBody
    BasicVO doAjaxUpdateStudent(@RequestBody UpdateStudentVO updateStudentVO) {
        User teacher = this.getUserInfo();
        if (updateStudentVO == null || teacher == null || teacher.getRoleType()==1){
            return new BasicVO(false, CommonConstant.FAIL);
        }
        User queryUser = new User();
        queryUser.setUserId(updateStudentVO.getUserId());
        User user = userService.getUnique(queryUser).getDefaultModel();
        if (user == null){
            return new BasicVO(false, CommonConstant.FAIL);
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
    @RequestMapping(value = "AjaxBatchAddStudent", method = {RequestMethod.POST})
    public @ResponseBody
    BasicVO doAjaxBatchAddStudent(@RequestBody BatchAddStudentVO batchAddStudentVO) {
        User teacher = this.getUserInfo();
        if (batchAddStudentVO == null || teacher == null || teacher.getRoleType()==1){
            return new BasicVO(false, CommonConstant.FAIL);
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

    @RequestMapping(value = "AjaxGetUnit", method = {RequestMethod.GET})
    public
    @ResponseBody
    RichUnitStudyVO doAjaxGetUnit(@RequestParam Integer unit, @RequestParam String moduleCode) {
        RichUnitStudyVO richUnitStudyVO = new RichUnitStudyVO();
        User teacher = this.getUserInfo();
        if (teacher == null || teacher.getRoleType()==1){
            richUnitStudyVO.setMsg(CommonConstant.FAIL);
            richUnitStudyVO.setSuccess(false);
            return richUnitStudyVO;
        }
        //1、此处不需要学习进度信息
        richUnitStudyVO.setStudyPos(null);
        richUnitStudyVO.setStudyToken(null);
        //2、生成不含进度的课本信息
        Course queryCourse = new Course();
        queryCourse.setModuleCode(moduleCode);
        Course course = courseService.getUnique(queryCourse).getDefaultModel();
        richUnitStudyVO.setBookInfo((BookStudyVO) BookStudyVO.generateBookVO(course));
        //3、生成不含进度的单元单词信息
        List<UnitStudyVO> unitStudyVOList = new ArrayList<>();
        UnitWords queryUnit = new UnitWords();
        queryUnit.setUnitNbr(unit);
        queryUnit.setLessonId(course.getBookNumber().longValue());
        List<UnitWords> unitWordsList = unitWordsService.getListByExample(queryUnit).getDefaultModel();
        for (UnitWords unitWords : unitWordsList){
            UnitStudyVO unitStudyVO = new UnitStudyVO();
            unitStudyVO.setVocCode(unitWords.getVocCode());
            unitStudyVO.setVocIndex(unitWords.getVocIndex());
            unitStudyVO.setUnitNbr(unit);
            unitStudyVO.setFstClassId(unitWords.getFstClassId());
            unitStudyVO.setSpelling(unitWords.getSpelling());
            unitStudyVO.setMeaning(unitWords.getMeaning());
            //此处为了保持与学生端一致，设置为unitWord的id,实际上我觉得应该是word_id
            unitStudyVO.setId(unitWords.getId());
            unitStudyVO.setLessonNbr(unitWords.getLessonId().intValue());
            unitStudyVOList.add(unitStudyVO);
        }
        richUnitStudyVO.setData(unitStudyVOList);
        richUnitStudyVO.setSuccess(true);
        richUnitStudyVO.setMsg(CommonConstant.SUCCESS);
        return richUnitStudyVO;
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

    @RequestMapping(value = "AjaxBatchAssignCourse", method = {RequestMethod.POST})
    public @ResponseBody
    BasicVO doAjaxBatchAssignCourse(@RequestBody BatchAssignCourseVO batchAssignCourseVO) {
        User teacher = this.getUserInfo();
        if (batchAssignCourseVO == null || !batchAssignCourseVO.isVaild() || teacher == null || teacher.getRoleType()==1){
            return new BasicVO(false, CommonConstant.FAIL);
        }

        for (String userId : batchAssignCourseVO.getUserIds().split(CommonConstant.COMMA_SPLIT)){
            //根据userId查询学生
            User queryUser = new User();
            queryUser.setUserId(userId);
            User user = userService.getUnique(queryUser).getDefaultModel();
            //增量更新学生课程
            user = incrementAddStudentCourse(user,batchAssignCourseVO.getLessonIds());
            userService.updateUser(user);
        }
        return new BasicVO(true,CommonConstant.SUCCESS);
    }

    private User incrementAddStudentCourse(User user,String incrementCourse){
        HashSet<String> set = new HashSet<>();
        Collections.addAll(set, incrementCourse.split(CommonConstant.COMMA_SPLIT));
        if (!StringUtils.isEmpty(user.getLessonIds())){
            Collections.addAll(set, user.getLessonIds().split(CommonConstant.COMMA_SPLIT));
        }
        String[] courses = set.toArray(new String[0]);
        user.setLessonIds(String.join(",",courses));
        return user;
    }
}
