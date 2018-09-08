package com.goldfish.service.impl;



import javax.annotation.Resource;
import java.util.List;
import java.util.Date;

import com.goldfish.dao.cache.local.WordContext;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.Word;
import com.goldfish.manager.WordManager;
import com.goldfish.service.WordService;


/**
 * @author hellosscat
 * @since 2018-5-8
 *<p>  单词库service实现</p>
 *
 */
@Service("wordService")
public class WordServiceImpl implements WordService {

	private static final Logger logger = Logger.getLogger(WordServiceImpl.class);
	
	@Resource(name="wordManager")
	private WordManager wordManager;

	@Resource
	private WordContext wordContext;
    
    @Override
    public CommonResult<Word> addWord(Word word) {
		CommonResult<Word> result = new CommonResult<Word>();
		try{
			
			word.setCreated(new Date());
			
			
			word.setModified(new Date());
			
			result.addDefaultModel(wordManager.addWord(word));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("添加 单词库失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	@Override
    public CommonResult<Word> updateWord(Word word) {
		CommonResult<Word> result = new CommonResult<Word>();
		try {
			
					word.setModified(new Date());
					
			wordManager.updateWord(word);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("更新 单词库失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
   

	@Override
    public CommonResult<Word> deleteWord(Integer id) {
		CommonResult<Word> result = new CommonResult<Word>();
		try {
			wordManager.deleteWord(id);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("删除 单词库失败", e);
			result.setSuccess(false);
		}
		return result;
    }


    	@Override
        public CommonResult<Word> getWordById(Integer id) {
		CommonResult<Word> result = new CommonResult<Word>();
		try {
			result.addDefaultModel("word", wordContext.getWord(id));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据主键获取 单词库失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	

        
	


	@Override
    public CommonResult<Word> getUnique(Word word) {
		CommonResult<Word> result = new CommonResult<Word>();
		try {
			result.addDefaultModel(wordManager.getUnique(word));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据example获取唯一 单词库失败", e);
			result.setSuccess(false);
		}
		return result;
	}


	@Override
    public CommonResult<List<Word>> getListByExample(Word word) {
		CommonResult<List<Word>> result = new CommonResult<List<Word>>();
		try {
			List<Word> list = wordManager.getListByExample(word);
			result.addDefaultModel("list", list);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("取得 单词库失败", e);
			result.setSuccess(false);
		}
		return result;
	}

	
	@Override
    public CommonResult<List<Word>> getWordByPage(PageQuery pageQuery) {
		CommonResult<List<Word>> result = new CommonResult<List<Word>>();
		try {
			int totalCount = this.count(pageQuery);
			if (totalCount > 0) {
				pageQuery.setTotalCount(totalCount);
				List<Word> list = wordManager.getWordByPage(pageQuery);
				result.addDefaultModel("list", list);
				result.addModel("pageQuery", pageQuery);
			}
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("分页获取 单词库失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	@Override
    public int count(PageQuery pageQuery) {
		return wordManager.count(pageQuery);
	}


	/******* getter and setter ***/
	public WordManager getWordManager() {
		return wordManager;
	}

	public void setWordManager(WordManager wordManager) {
		this.wordManager = wordManager;
	}

}
