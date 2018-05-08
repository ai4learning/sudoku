package com.goldfish.service.impl;



import javax.annotation.Resource;
import java.util.List;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.SelfWordsStudy;
import com.goldfish.manager.SelfWordsStudyManager;
import com.goldfish.service.SelfWordsStudyService;


/**
 * @author hellosscat
 * @since 2018-5-8
 *<p>  学生自身单词学习service实现</p>
 *
 */
@Service("selfWordsStudyService")
public class SelfWordsStudyServiceImpl implements SelfWordsStudyService {

	private static final Logger logger = Logger.getLogger(SelfWordsStudyServiceImpl.class);
	
	@Resource(name="selfWordsStudyManager")
	private SelfWordsStudyManager selfWordsStudyManager;
    
    public CommonResult<SelfWordsStudy> addSelfWordsStudy(SelfWordsStudy selfWordsStudy) {
		CommonResult<SelfWordsStudy> result = new CommonResult<SelfWordsStudy>();
		try{
			
			selfWordsStudy.setCreated(new Date());
			
			
			selfWordsStudy.setModified(new Date());
			
			result.addDefaultModel(selfWordsStudyManager.addSelfWordsStudy(selfWordsStudy));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("添加 学生自身单词学习失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	public CommonResult<SelfWordsStudy> updateSelfWordsStudy(SelfWordsStudy selfWordsStudy) {
		CommonResult<SelfWordsStudy> result = new CommonResult<SelfWordsStudy>();
		try {
			
					selfWordsStudy.setModified(new Date());
					
			selfWordsStudyManager.updateSelfWordsStudy(selfWordsStudy);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("更新 学生自身单词学习失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
   

	public CommonResult<SelfWordsStudy> deleteSelfWordsStudy(Long id) {
		CommonResult<SelfWordsStudy> result = new CommonResult<SelfWordsStudy>();
		try {
			selfWordsStudyManager.deleteSelfWordsStudy(id);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("删除 学生自身单词学习失败", e);
			result.setSuccess(false);
		}
		return result;
    }


    	public CommonResult<SelfWordsStudy> getSelfWordsStudyById(Long id) {
		CommonResult<SelfWordsStudy> result = new CommonResult<SelfWordsStudy>();
		try {
			result.addDefaultModel("selfWordsStudy", selfWordsStudyManager.getSelfWordsStudyById(id));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据主键获取 学生自身单词学习失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	

        
	


	public CommonResult<SelfWordsStudy> getUnique(SelfWordsStudy selfWordsStudy) {
		CommonResult<SelfWordsStudy> result = new CommonResult<SelfWordsStudy>();
		try {
			result.addDefaultModel(selfWordsStudyManager.getUnique(selfWordsStudy));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据example获取唯一 学生自身单词学习失败", e);
			result.setSuccess(false);
		}
		return result;
	}


	public CommonResult<List<SelfWordsStudy>> getListByExample(SelfWordsStudy selfWordsStudy) {
		CommonResult<List<SelfWordsStudy>> result = new CommonResult<List<SelfWordsStudy>>();
		try {
			List<SelfWordsStudy> list = selfWordsStudyManager.getListByExample(selfWordsStudy);
			result.addDefaultModel("list", list);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("取得 学生自身单词学习失败", e);
			result.setSuccess(false);
		}
		return result;
	}

	
	public CommonResult<List<SelfWordsStudy>> getSelfWordsStudyByPage(PageQuery pageQuery) {
		CommonResult<List<SelfWordsStudy>> result = new CommonResult<List<SelfWordsStudy>>();
		try {
			int totalCount = this.count(pageQuery);
			if (totalCount > 0) {
				pageQuery.setTotalCount(totalCount);
				List<SelfWordsStudy> list = selfWordsStudyManager.getSelfWordsStudyByPage(pageQuery);
				result.addDefaultModel("list", list);
				result.addModel("pageQuery", pageQuery);
			}
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("分页获取 学生自身单词学习失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	public int count(PageQuery pageQuery) {
		return selfWordsStudyManager.count(pageQuery);
	}


	/******* getter and setter ***/
	public SelfWordsStudyManager getSelfWordsStudyManager() {
		return selfWordsStudyManager;
	}

	public void setSelfWordsStudyManager(SelfWordsStudyManager selfWordsStudyManager) {
		this.selfWordsStudyManager = selfWordsStudyManager;
	}

}
