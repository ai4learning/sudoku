package com.goldfish.service.impl;



import javax.annotation.Resource;
import java.util.List;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.WordStudy;
import com.goldfish.manager.WordStudyManager;
import com.goldfish.service.WordStudyService;


/**
 * @author hellosscat
 * @since 2018-5-8
 *<p>  单词学习service实现</p>
 *
 */
@Service("wordStudyService")
public class WordStudyServiceImpl implements WordStudyService {

	private static final Logger logger = Logger.getLogger(WordStudyServiceImpl.class);
	
	@Resource(name="wordStudyManager")
	private WordStudyManager wordStudyManager;
    
    public CommonResult<WordStudy> addWordStudy(WordStudy wordStudy) {
		CommonResult<WordStudy> result = new CommonResult<WordStudy>();
		try{
			
			wordStudy.setCreated(new Date());
			
			
			wordStudy.setModified(new Date());
			
			result.addDefaultModel(wordStudyManager.addWordStudy(wordStudy));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("添加 单词学习失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	public CommonResult<WordStudy> updateWordStudy(WordStudy wordStudy) {
		CommonResult<WordStudy> result = new CommonResult<WordStudy>();
		try {
			
					wordStudy.setModified(new Date());
					
			wordStudyManager.updateWordStudy(wordStudy);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("更新 单词学习失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
   

	public CommonResult<WordStudy> deleteWordStudy(Long id) {
		CommonResult<WordStudy> result = new CommonResult<WordStudy>();
		try {
			wordStudyManager.deleteWordStudy(id);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("删除 单词学习失败", e);
			result.setSuccess(false);
		}
		return result;
    }


    	public CommonResult<WordStudy> getWordStudyById(Long id) {
		CommonResult<WordStudy> result = new CommonResult<WordStudy>();
		try {
			result.addDefaultModel("wordStudy", wordStudyManager.getWordStudyById(id));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据主键获取 单词学习失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	

        
	


	public CommonResult<WordStudy> getUnique(WordStudy wordStudy) {
		CommonResult<WordStudy> result = new CommonResult<WordStudy>();
		try {
			result.addDefaultModel(wordStudyManager.getUnique(wordStudy));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据example获取唯一 单词学习失败", e);
			result.setSuccess(false);
		}
		return result;
	}


	public CommonResult<List<WordStudy>> getListByExample(WordStudy wordStudy) {
		CommonResult<List<WordStudy>> result = new CommonResult<List<WordStudy>>();
		try {
			List<WordStudy> list = wordStudyManager.getListByExample(wordStudy);
			result.addDefaultModel("list", list);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("取得 单词学习失败", e);
			result.setSuccess(false);
		}
		return result;
	}

	
	public CommonResult<List<WordStudy>> getWordStudyByPage(PageQuery pageQuery) {
		CommonResult<List<WordStudy>> result = new CommonResult<List<WordStudy>>();
		try {
			int totalCount = this.count(pageQuery);
			if (totalCount > 0) {
				pageQuery.setTotalCount(totalCount);
				List<WordStudy> list = wordStudyManager.getWordStudyByPage(pageQuery);
				result.addDefaultModel("list", list);
				result.addModel("pageQuery", pageQuery);
			}
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("分页获取 单词学习失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	public int count(PageQuery pageQuery) {
		return wordStudyManager.count(pageQuery);
	}

    public int countDay(PageQuery pageQuery){
        return wordStudyManager.countDay(pageQuery);
    }

	/******* getter and setter ***/
	public WordStudyManager getWordStudyManager() {
		return wordStudyManager;
	}

	public void setWordStudyManager(WordStudyManager wordStudyManager) {
		this.wordStudyManager = wordStudyManager;
	}

    public CommonResult<List<WordStudy>> getStudiedWords(WordStudy wordStudy)
    {
        CommonResult<List<WordStudy>> result = new CommonResult<List<WordStudy>>();
        try {
            List<WordStudy> list = wordStudyManager.getStudiedWords(wordStudy);
            result.addDefaultModel("list", list);
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error("取得 单词学习失败", e);
            result.setSuccess(false);
        }
        return result;
    }
}
