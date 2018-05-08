package com.goldfish.manager.impl;

import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
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

	@Resource(name="examDao")
	private ExamDao examDao;


  public Exam addExam(Exam exam) {
		int i=examDao.addExam(exam);
		return exam;
    }
    
    public void updateExam(Exam exam) {
		examDao.updateExam(exam);
    }
    

    
    public void deleteExam(Long id) {
		examDao.deleteExam(id);
    }


    public Exam getExamById(Long id) {
		return examDao.getExamById(id);
    }
    
   


    	
   
   public Exam getUnique(Exam exam) {
		return examDao.getUnique(exam);
    }

    
 public List<Exam> getListByExample(Exam exam) {
    return examDao.getListByExample(exam);
    }

    
    public List<Exam> getExamByPage(PageQuery pageQuery) {
		return examDao.getExamByPage( pageQuery.getParams());
    }
    	
    public int count(PageQuery pageQuery) {
		return examDao.count( pageQuery.getParams());
    }

    /******* getter and setter ***/
    
	public ExamDao getExamDao() {
		return examDao;
	}

	public void setExamDao(ExamDao examDao) {
		this.examDao = examDao;
	}
}
