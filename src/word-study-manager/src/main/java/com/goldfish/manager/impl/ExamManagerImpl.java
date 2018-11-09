package com.goldfish.manager.impl;

import com.goldfish.dao.cache.redis.RedisUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import com.goldfish.common.PageQuery;
import com.goldfish.domain.Exam;
import com.goldfish.dao.ExamDao;
import com.goldfish.manager.ExamManager;

/**
 * @author hellosscat
 * @since 2018-5-8
 * 考试Manager实现类
 */
@Component("examManager")
public class ExamManagerImpl implements ExamManager {

    @Resource(name = "examDao")
    private ExamDao examDao;

    @Resource(name = "redisUtils")
    private RedisUtils redisUtils;

    @Override
    public Exam addExam(Exam exam) {
        int i = examDao.addExam(exam);
        redisUtils.setObject(exam.getClass().getSimpleName() + ":" + exam.getId(), exam);
        return exam;
    }

    @Override
    public void updateExam(Exam exam) {
        redisUtils.setObject(exam.getClass().getSimpleName() + ":" + exam.getId(), exam);
        examDao.updateExam(exam);
    }


    @Override
    public void deleteExam(Long id) {
        redisUtils.deleteByKey(Exam.class.getSimpleName() + ":" + id);
        examDao.deleteExam(id);
    }


    @Override
    public Exam getExamById(Long id) {
        Exam exam = redisUtils.getObject(Exam.class.getSimpleName() + ":" + id, Exam.class);
        if (exam == null) {
            return examDao.getExamById(id);
        }
        return exam;
    }


    @Override
    public Exam getUnique(Exam exam) {
        return examDao.getUnique(exam);
    }


    @Override
    public List<Exam> getListByExample(Exam exam) {
        return examDao.getListByExample(exam);
    }

    @Override
    public List<Exam> getListByUserRecent(PageQuery pageQuery) {
        return examDao.getListByUserRecent(pageQuery.getParams());
    }

    @Override
    public List<Exam> getExamByPage(PageQuery pageQuery) {
        return examDao.getExamByPage(pageQuery.getParams());
    }

    @Override
    public int count(PageQuery pageQuery) {
        return examDao.count(pageQuery.getParams());
    }

    /******* getter and setter ***/

    public ExamDao getExamDao() {
        return examDao;
    }

    public void setExamDao(ExamDao examDao) {
        this.examDao = examDao;
    }
}
