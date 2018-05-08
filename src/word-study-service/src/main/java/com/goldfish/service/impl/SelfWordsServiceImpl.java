package com.goldfish.service.impl;



import javax.annotation.Resource;
import java.util.List;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.SelfWords;
import com.goldfish.manager.SelfWordsManager;
import com.goldfish.service.SelfWordsService;


/**
 * @author hellosscat
 * @since 2018-5-8
 *<p>  学生自身单词service实现</p>
 *
 */
@Service("selfWordsService")
public class SelfWordsServiceImpl implements SelfWordsService {

	private static final Logger logger = Logger.getLogger(SelfWordsServiceImpl.class);
	
	@Resource(name="selfWordsManager")
	private SelfWordsManager selfWordsManager;
    
    public CommonResult<SelfWords> addSelfWords(SelfWords selfWords) {
		CommonResult<SelfWords> result = new CommonResult<SelfWords>();
		try{
			
			selfWords.setCreated(new Date());
			
			
			selfWords.setModified(new Date());
			
			result.addDefaultModel(selfWordsManager.addSelfWords(selfWords));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("添加 学生自身单词失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	public CommonResult<SelfWords> updateSelfWords(SelfWords selfWords) {
		CommonResult<SelfWords> result = new CommonResult<SelfWords>();
		try {
			
					selfWords.setModified(new Date());
					
			selfWordsManager.updateSelfWords(selfWords);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("更新 学生自身单词失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
   

	public CommonResult<SelfWords> deleteSelfWords(Long id) {
		CommonResult<SelfWords> result = new CommonResult<SelfWords>();
		try {
			selfWordsManager.deleteSelfWords(id);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("删除 学生自身单词失败", e);
			result.setSuccess(false);
		}
		return result;
    }


    	public CommonResult<SelfWords> getSelfWordsById(Long id) {
		CommonResult<SelfWords> result = new CommonResult<SelfWords>();
		try {
			result.addDefaultModel("selfWords", selfWordsManager.getSelfWordsById(id));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据主键获取 学生自身单词失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	

        
	


	public CommonResult<SelfWords> getUnique(SelfWords selfWords) {
		CommonResult<SelfWords> result = new CommonResult<SelfWords>();
		try {
			result.addDefaultModel(selfWordsManager.getUnique(selfWords));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据example获取唯一 学生自身单词失败", e);
			result.setSuccess(false);
		}
		return result;
	}


	public CommonResult<List<SelfWords>> getListByExample(SelfWords selfWords) {
		CommonResult<List<SelfWords>> result = new CommonResult<List<SelfWords>>();
		try {
			List<SelfWords> list = selfWordsManager.getListByExample(selfWords);
			result.addDefaultModel("list", list);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("取得 学生自身单词失败", e);
			result.setSuccess(false);
		}
		return result;
	}

	
	public CommonResult<List<SelfWords>> getSelfWordsByPage(PageQuery pageQuery) {
		CommonResult<List<SelfWords>> result = new CommonResult<List<SelfWords>>();
		try {
			int totalCount = this.count(pageQuery);
			if (totalCount > 0) {
				pageQuery.setTotalCount(totalCount);
				List<SelfWords> list = selfWordsManager.getSelfWordsByPage(pageQuery);
				result.addDefaultModel("list", list);
				result.addModel("pageQuery", pageQuery);
			}
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("分页获取 学生自身单词失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	public int count(PageQuery pageQuery) {
		return selfWordsManager.count(pageQuery);
	}


	/******* getter and setter ***/
	public SelfWordsManager getSelfWordsManager() {
		return selfWordsManager;
	}

	public void setSelfWordsManager(SelfWordsManager selfWordsManager) {
		this.selfWordsManager = selfWordsManager;
	}

}
