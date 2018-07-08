package com.goldfish.manager.impl;

import com.goldfish.common.PageQuery;
import com.goldfish.dao.ClassGradeDao;
import com.goldfish.domain.ClassGrade;
import com.goldfish.manager.ClassGradeManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhangjingtao
 * @date 2018/7/8 0008.
 */
@Component("classGradeManager")
public class ClassGradeManagerImpl implements ClassGradeManager {

    @Resource
    private ClassGradeDao classGradeDao;

    @Override
    public ClassGrade addClassGrade(ClassGrade classGrade) {
        classGradeDao.addClassGrade(classGrade);
        return classGrade;
    }

    @Override
    public void updateClassGrade(ClassGrade classGrade) {

    }

    @Override
    public void deleteClassGrade(Integer id) {

    }

    @Override
    public ClassGrade getClassGradeById(Integer id) {
        return null;
    }

    @Override
    public ClassGrade getUnique(ClassGrade classGrade) {
        return null;
    }

    @Override
    public List<ClassGrade> getListByExample(ClassGrade classGrade) {
        return null;
    }

    @Override
    public List<ClassGrade> getClassGradeByPage(PageQuery pageQuery) {
        return null;
    }

    @Override
    public int count(PageQuery pageQuery) {
        return 0;
    }
}
