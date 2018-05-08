package com.goldfish.service.impl;



import javax.annotation.Resource;
import java.util.List;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.Question;
import com.goldfish.manager.QuestionManager;
import com.goldfish.service.QuestionService;


/**
 * @author hellosscat
 * @since 2018-5-8
 *<p>  试题service实现</p>
 *
 */
@Service("questionService")
public class QuestionServiceImpl implements QuestionService {

	private static final Logger logger = Logger.getLogger(QuestionServiceImpl.class);
	
	@Resource(name="questionManager")
	private QuestionManager questionManager;
    
    public CommonResult<Question> addQuestion(Question question) {
		CommonResult<Question> result = new CommonResult<Question>();
		try{
			
			question.setCreated(new Date());
			
			
			question.setModified(new Date());
			
			result.addDefaultModel(questionManager.addQuestion(question));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("添加 试题失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	public CommonResult<Question> updateQuestion(Question question) {
		CommonResult<Question> result = new CommonResult<Question>();
		try {
			
					question.setModified(new Date());
					
			questionManager.updateQuestion(question);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("更新 试题失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
   

	public CommonResult<Question> deleteQuestion(Long id) {
		CommonResult<Question> result = new CommonResult<Question>();
		try {
			questionManager.deleteQuestion(id);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("删除 试题失败", e);
			result.setSuccess(false);
		}
		return result;
    }


    	public CommonResult<Question> getQuestionById(Long id) {
		CommonResult<Question> result = new CommonResult<Question>();
		try {
			result.addDefaultModel("question", questionManager.getQuestionById(id));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据主键获取 试题失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	

        
	


	public CommonResult<Question> getUnique(Question question) {
		CommonResult<Question> result = new CommonResult<Question>();
		try {
			result.addDefaultModel(questionManager.getUnique(question));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据example获取唯一 试题失败", e);
			result.setSuccess(false);
		}
		return result;
	}


	public CommonResult<List<Question>> getListByExample(Question question) {
		CommonResult<List<Question>> result = new CommonResult<List<Question>>();
		try {
			List<Question> list = questionManager.getListByExample(question);
			result.addDefaultModel("list", list);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("取得 试题失败", e);
			result.setSuccess(false);
		}
		return result;
	}

	
	public CommonResult<List<Question>> getQuestionByPage(PageQuery pageQuery) {
		CommonResult<List<Question>> result = new CommonResult<List<Question>>();
		try {
			int totalCount = this.count(pageQuery);
			if (totalCount > 0) {
				pageQuery.setTotalCount(totalCount);
				List<Question> list = questionManager.getQuestionByPage(pageQuery);
				result.addDefaultModel("list", list);
				result.addModel("pageQuery", pageQuery);
			}
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("分页获取 试题失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	public int count(PageQuery pageQuery) {
		return questionManager.count(pageQuery);
	}


	/******* getter and setter ***/
	public QuestionManager getQuestionManager() {
		return questionManager;
	}

	public void setQuestionManager(QuestionManager questionManager) {
		this.questionManager = questionManager;
	}

}
