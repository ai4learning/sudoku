package com.goldfish.service.impl;



import javax.annotation.Resource;
import java.util.List;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.WordStudyStatistic;
import com.goldfish.manager.WordStudyStatisticManager;
import com.goldfish.service.WordStudyStatisticService;


/**
 * @author hellosscat
 * @since 2018-5-8
 *<p>  单词学习统计service实现</p>
 *
 */
@Service("wordStudyStatisticService")
public class WordStudyStatisticServiceImpl implements WordStudyStatisticService {

	private static final Logger logger = Logger.getLogger(WordStudyStatisticServiceImpl.class);
	
	@Resource(name="wordStudyStatisticManager")
	private WordStudyStatisticManager wordStudyStatisticManager;
    
    public CommonResult<WordStudyStatistic> addWordStudyStatistic(WordStudyStatistic wordStudyStatistic) {
		CommonResult<WordStudyStatistic> result = new CommonResult<WordStudyStatistic>();
		try{
			
			wordStudyStatistic.setCreated(new Date());
			
			
			wordStudyStatistic.setModified(new Date());
			
			result.addDefaultModel(wordStudyStatisticManager.addWordStudyStatistic(wordStudyStatistic));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("添加 单词学习统计失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	public CommonResult<WordStudyStatistic> updateWordStudyStatistic(WordStudyStatistic wordStudyStatistic) {
		CommonResult<WordStudyStatistic> result = new CommonResult<WordStudyStatistic>();
		try {
			
					wordStudyStatistic.setModified(new Date());
					
			wordStudyStatisticManager.updateWordStudyStatistic(wordStudyStatistic);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("更新 单词学习统计失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
   

	public CommonResult<WordStudyStatistic> deleteWordStudyStatistic(Long id) {
		CommonResult<WordStudyStatistic> result = new CommonResult<WordStudyStatistic>();
		try {
			wordStudyStatisticManager.deleteWordStudyStatistic(id);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("删除 单词学习统计失败", e);
			result.setSuccess(false);
		}
		return result;
    }


    	public CommonResult<WordStudyStatistic> getWordStudyStatisticById(Long id) {
		CommonResult<WordStudyStatistic> result = new CommonResult<WordStudyStatistic>();
		try {
			result.addDefaultModel("wordStudyStatistic", wordStudyStatisticManager.getWordStudyStatisticById(id));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据主键获取 单词学习统计失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	

        
	


	public CommonResult<WordStudyStatistic> getUnique(WordStudyStatistic wordStudyStatistic) {
		CommonResult<WordStudyStatistic> result = new CommonResult<WordStudyStatistic>();
		try {
			result.addDefaultModel(wordStudyStatisticManager.getUnique(wordStudyStatistic));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据example获取唯一 单词学习统计失败", e);
			result.setSuccess(false);
		}
		return result;
	}


	public CommonResult<List<WordStudyStatistic>> getListByExample(WordStudyStatistic wordStudyStatistic) {
		CommonResult<List<WordStudyStatistic>> result = new CommonResult<List<WordStudyStatistic>>();
		try {
			List<WordStudyStatistic> list = wordStudyStatisticManager.getListByExample(wordStudyStatistic);
			result.addDefaultModel("list", list);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("取得 单词学习统计失败", e);
			result.setSuccess(false);
		}
		return result;
	}

	
	public CommonResult<List<WordStudyStatistic>> getWordStudyStatisticByPage(PageQuery pageQuery) {
		CommonResult<List<WordStudyStatistic>> result = new CommonResult<List<WordStudyStatistic>>();
		try {
			int totalCount = this.count(pageQuery);
			if (totalCount > 0) {
				pageQuery.setTotalCount(totalCount);
				List<WordStudyStatistic> list = wordStudyStatisticManager.getWordStudyStatisticByPage(pageQuery);
				result.addDefaultModel("list", list);
				result.addModel("pageQuery", pageQuery);
			}
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("分页获取 单词学习统计失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	public int count(PageQuery pageQuery) {
		return wordStudyStatisticManager.count(pageQuery);
	}


	/******* getter and setter ***/
	public WordStudyStatisticManager getWordStudyStatisticManager() {
		return wordStudyStatisticManager;
	}

	public void setWordStudyStatisticManager(WordStudyStatisticManager wordStudyStatisticManager) {
		this.wordStudyStatisticManager = wordStudyStatisticManager;
	}

}
