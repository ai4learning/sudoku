package com.goldfish.dao.cache.local;

import com.goldfish.common.log.LogTypeEnum;
import com.goldfish.constant.State;
import com.goldfish.dao.CourseDao;
import com.goldfish.domain.Course;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Component("courseContext")
public class CourseContext {

    private Map<Integer, String> context = new HashMap<Integer, String>();
    private Map<String, Integer> code2Id = new HashMap<String, Integer>();
    private Map<Integer, String> id2Code = new HashMap<Integer, String>();


    @Resource(name = "courseDao")
    private CourseDao courseDao;

    public String getName(Integer lessonNumber) {
        String lessonName = context.get(lessonNumber);
        if (lessonName != null) {
            return lessonName;
        }
        Course query = new Course();
        query.setBookNumber(lessonNumber);
        Course course = courseDao.getUnique(query);
        if (course == null) {
            LogTypeEnum.DEFAULT.error("Course Not Exist, lessonNumber={}", lessonNumber);
            return null;
        }
        context.put(lessonNumber, course.getBookName());
        return course.getBookName();
    }

    public Integer getLessonIdByCode(String moduleCode) {
        Integer lessonId = code2Id.get(moduleCode);
        if (lessonId != null) {
            return lessonId;
        }
        Course query = new Course();
        query.setModuleCode(moduleCode);
        query.setBookState(State.VALID.getState());
        Course course = courseDao.getUnique(query);
        if (course == null) {
            LogTypeEnum.DEFAULT.error("Course Not Exist, ModuleCode={}", moduleCode);
            return null;
        }
        code2Id.put(moduleCode, course.getBookNumber());
        return course.getBookNumber();
    }

    public String getModuleCodeByLessonId(Integer lessonId) {
        String moduleCode = id2Code.get(lessonId);
        if (!StringUtils.isEmpty(moduleCode)) {
            return moduleCode;
        }
        Course query = new Course();
        query.setBookNumber(lessonId);
        query.setBookState(State.VALID.getState());
        Course course = courseDao.getUnique(query);
        if (course == null) {
            LogTypeEnum.DEFAULT.error("Course Not Exist, BookNumber={}", lessonId);
            return null;
        }
        id2Code.put(lessonId, course.getModuleCode());
        return course.getModuleCode();
    }
}
