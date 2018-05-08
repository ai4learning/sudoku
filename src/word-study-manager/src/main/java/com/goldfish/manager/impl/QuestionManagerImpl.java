package com.goldfish.manager.impl;

import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.domain.Question;
import com.goldfish.dao.QuestionDao;
import com.goldfish.manager.QuestionManager;

/**
 * @author hellosscat
 * @since 2018-5-8
 * 试题Manager实现类
 */
 @Component("questionManager")
public class QuestionManagerImpl implements QuestionManager {

	@Resource(name="questionDao")
	private QuestionDao questionDao;


  public Question addQuestion(Question question) {
		int i=questionDao.addQuestion(question);
		return question;
    }
    
    public void updateQuestion(Question question) {
		questionDao.updateQuestion(question);
    }
    

    
    public void deleteQuestion(Long id) {
		questionDao.deleteQuestion(id);
    }


    public Question getQuestionById(Long id) {
		return questionDao.getQuestionById(id);
    }
    
   


    	
   
   public Question getUnique(Question question) {
		return questionDao.getUnique(question);
    }

    
 public List<Question> getListByExample(Question question) {
    return questionDao.getListByExample(question);
    }

    
    public List<Question> getQuestionByPage(PageQuery pageQuery) {
		return questionDao.getQuestionByPage( pageQuery.getParams());
    }
    	
    public int count(PageQuery pageQuery) {
		return questionDao.count( pageQuery.getParams());
    }

    /******* getter and setter ***/
    
	public QuestionDao getQuestionDao() {
		return questionDao;
	}

	public void setQuestionDao(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}
}
