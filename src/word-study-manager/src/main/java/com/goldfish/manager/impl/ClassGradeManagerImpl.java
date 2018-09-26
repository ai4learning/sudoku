package com.goldfish.manager.impl;

import com.goldfish.common.PageQuery;
import com.goldfish.dao.ClassGradeDao;
import com.goldfish.dao.cache.redis.RedisUtils;
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

    @Resource(name = "redisUtils")
    private RedisUtils redisUtils;

    @Override
    public ClassGrade addClassGrade(ClassGrade classGrade) {
        redisUtils.setObject(classGrade.getClass().getSimpleName() + ":" + classGrade.getId(), classGrade);
        classGradeDao.addClassGrade(classGrade);
        return classGrade;
    }

    @Override
    public void updateClassGrade(ClassGrade classGrade) {
        redisUtils.setObject(classGrade.getClass().getSimpleName() + ":" + classGrade.getId(), classGrade);
        classGradeDao.updateClassGrade(classGrade);
    }

    @Override
    public void deleteClassGrade(Long id) {
        redisUtils.deleteByKey(ClassGrade.class.getSimpleName() + ":" + id);
        classGradeDao.deleteClassGrade(id);
    }

    @Override
    public ClassGrade getClassGradeById(Long id) {
        ClassGrade classGrade = redisUtils.getObject(ClassGrade.class.getSimpleName() + ":" + id, ClassGrade.class);
        if (classGrade == null) {
            return classGradeDao.getClassGradeById(id);
        }
        return classGrade;
    }

    @Override
    public ClassGrade getUnique(ClassGrade classGrade) {
        return classGradeDao.getUnique(classGrade);
    }

    @Override
    public List<ClassGrade> getListByExample(ClassGrade classGrade) {
        return classGradeDao.getListByExample(classGrade);
    }

    @Override
    public List<ClassGrade> getClassGradeByPage(PageQuery pageQuery) {
        return classGradeDao.getClassGradeByPage(pageQuery.getParams());
    }

    @Override
    public int count(PageQuery pageQuery) {
        return classGradeDao.count(pageQuery.getParams());
    }

    public ClassGradeDao getClassGradeDao() {
        return classGradeDao;
    }

    public void setClassGradeDao(ClassGradeDao classGradeDao) {
        this.classGradeDao = classGradeDao;
    }
}
