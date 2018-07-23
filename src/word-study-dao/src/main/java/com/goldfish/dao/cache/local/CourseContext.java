package com.goldfish.dao.cache.local;

import com.goldfish.common.log.LogTypeEnum;
import com.goldfish.constant.State;
import com.goldfish.dao.CourseDao;
import com.goldfish.domain.Course;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component("courseContext")
public class CourseContext {
    private Map<String, Integer> code2Id = new ConcurrentHashMap<String, Integer>();
    private Map<Integer, String> id2Code = new ConcurrentHashMap<Integer, String>();
    private Map<Integer, Course> id2Course = new ConcurrentHashMap<Integer, Course>();


    @Resource(name = "courseDao")
    private CourseDao courseDao;

    public Course getCourse(Integer lessonNumber) {
        Course course = id2Course.get(lessonNumber);
        if (course != null) {
            return course;
        }
        Course query = new Course();
        query.setBookNumber(lessonNumber);
        course = courseDao.getUnique(query);
        if (course == null) {
            LogTypeEnum.DEFAULT.error("Course Not Exist, lessonNumber={}", lessonNumber);
            return null;
        }
        id2Course.put(lessonNumber, course);
        return course;
    }

    public String getName(Integer lessonNumber) {
        Course course = this.getCourse(lessonNumber);
        if (course != null) {
            return course.getBookName();
        }
        return null;
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
