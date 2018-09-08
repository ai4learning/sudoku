package com.goldfish.task;

import com.goldfish.constant.TaskType;
import com.goldfish.domain.CourseStudy;
import com.goldfish.domain.Task;
import com.goldfish.domain.UnitStudy;
import com.goldfish.domain.WordStudy;
import com.goldfish.manager.CourseStudyManager;
import com.goldfish.manager.UnitStudyManager;
import com.goldfish.manager.WordStudyManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by John on 2018/6/28 0028.
 */
@Component("delStuStudyDataTask")
public class DelStuStudyDataTask extends AbstractTask {

    @Resource(name = "courseStudyManager")
    private CourseStudyManager courseStudyManager;
    @Resource
    private UnitStudyManager unitStudyManager;
    @Resource
    private WordStudyManager wordStudyManager;

    @Override
    protected boolean doExecute(Task task) {
        Integer userId = task.getUserId();
        Long lessonId = task.getBusinessId();
        CourseStudy courseStudy = new CourseStudy();
        courseStudy.setStudentId(userId);
        courseStudy.setLessonId(Integer.valueOf(String.valueOf(lessonId)));
        // 删除课程数据
        courseStudyManager.deleteCourseStudyByCondition(courseStudy);

        UnitStudy unitStudy = new UnitStudy();
        unitStudy.setStudentId(userId);
        unitStudy.setLessonId(Integer.valueOf(String.valueOf(lessonId)));
        // 删除单元数据
        unitStudyManager.deleteUnitWordsStudyByCondition(unitStudy);
//        // 批量删除单词数据
//        wordStudyManager.deleteWordStudy();
        return true;
    }

    @Override
    public String getName() {
        return TaskType.DELETE_STUDY_DATA.getDesc();
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.DELETE_STUDY_DATA;
    }
}
