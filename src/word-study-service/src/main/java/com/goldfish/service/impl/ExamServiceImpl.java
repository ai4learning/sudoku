package com.goldfish.service.impl;



import javax.annotation.Resource;
import java.util.List;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.Exam;
import com.goldfish.manager.ExamManager;
import com.goldfish.service.ExamService;


/**
 * @author hellosscat
 * @since 2018-5-2
 *<p>  Examservice实现</p>
 *
 */
@Service("examService")
public class ExamServiceImpl implements ExamService {

	private static final Logger logger = LoggerFactory.getLogger(ExamServiceImpl.class);
	
	@Resource(name="examManager")
	private ExamManager examManager;
    
    public CommonResult<Exam> addExam(Exam exam) {
		CommonResult<Exam> result = new CommonResult<Exam>();
		try {
			
				exam.setCreated(new Date());
			 
			result.addDefaultModel(examManager.addExam(exam));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("添加 Exam失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	public CommonResult<Exam> updateExam(Exam exam) {
		CommonResult<Exam> result = new CommonResult<Exam>();
		try {
			
				exam.setModified(new Date());
			 
			examManager.updateExam(exam);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("更新 Exam失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
   

	public CommonResult<Exam> deleteExam(Long id) {
		CommonResult<Exam> result = new CommonResult<Exam>();
		try {
			examManager.deleteExam(id);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("删除 Exam失败", e);
			result.setSuccess(false);
		}
		return result;
    }


    	public CommonResult<Exam> getExamById(Long id) {
		CommonResult<Exam> result = new CommonResult<Exam>();
		try {
			result.addDefaultModel("exam", examManager.getExamById(id));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据主键获取 Exam失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	

        
	
	public CommonResult<List<Exam>> getAll() {
		CommonResult<List<Exam>> result = new CommonResult<List<Exam>>();
		try {
			List<Exam> list = examManager.getAll();
			result.addDefaultModel("list", list);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("取得所有 Exam失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	public CommonResult<List<Exam>> getListByExample(Exam exam) {
		CommonResult<List<Exam>> result = new CommonResult<List<Exam>>();
		try {
			List<Exam> list = examManager.getListByExample(exam);
			result.addDefaultModel("list", list);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("取得 Exam失败", e);
			result.setSuccess(false);
		}
		return result;
	}

	public CommonResult<Exam> getUnique(Exam exam) {
		CommonResult<Exam> result = new CommonResult<Exam>();
		try {
			result.addDefaultModel(examManager.getUnique(exam));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据example获取唯一 Exam失败", e);
			result.setSuccess(false);
		}
		return result;
	}

	



	
	public CommonResult<List<Exam>> getExamByPage(PageQuery pageQuery) {
		CommonResult<List<Exam>> result = new CommonResult<List<Exam>>();
		try {
			int totalCount = this.count(pageQuery);
			if (totalCount > 0) {
				pageQuery.setTotalCount(totalCount);
				List<Exam> list = examManager.getExamByPage(pageQuery);
				result.addDefaultModel("list", list);
				result.addModel("pageQuery", pageQuery);
			}
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("分页获取 Exam失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	public int count(PageQuery pageQuery) {
		return examManager.count(pageQuery);
	}


	/******* getter and setter ***/
	public ExamManager getExamManager() {
		return examManager;
	}

	public void setExamManager(ExamManager examManager) {
		this.examManager = examManager;
	}

}
