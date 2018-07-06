package com.goldfish.manager.impl;

import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.domain.WordStudyStatistic;
import com.goldfish.dao.WordStudyStatisticDao;
import com.goldfish.manager.WordStudyStatisticManager;

/**
 * @author hellosscat
 * @since 2018-5-8
 * 单词学习统计Manager实现类
 */
 @Component("wordStudyStatisticManager")
public class WordStudyStatisticManagerImpl implements WordStudyStatisticManager {

	@Resource(name="wordStudyStatisticDao")
	private WordStudyStatisticDao wordStudyStatisticDao;


  @Override
  public WordStudyStatistic addWordStudyStatistic(WordStudyStatistic wordStudyStatistic) {
		int i=wordStudyStatisticDao.addWordStudyStatistic(wordStudyStatistic);
		return wordStudyStatistic;
    }
    
    @Override
    public void updateWordStudyStatistic(WordStudyStatistic wordStudyStatistic) {
		wordStudyStatisticDao.updateWordStudyStatistic(wordStudyStatistic);
    }
    

    
    @Override
    public void deleteWordStudyStatistic(Long id) {
		wordStudyStatisticDao.deleteWordStudyStatistic(id);
    }


    @Override
    public WordStudyStatistic getWordStudyStatisticById(Long id) {
		return wordStudyStatisticDao.getWordStudyStatisticById(id);
    }
    
   


    	
   
   @Override
   public WordStudyStatistic getUnique(WordStudyStatistic wordStudyStatistic) {
		return wordStudyStatisticDao.getUnique(wordStudyStatistic);
    }

    
 @Override
 public List<WordStudyStatistic> getListByExample(WordStudyStatistic wordStudyStatistic) {
    return wordStudyStatisticDao.getListByExample(wordStudyStatistic);
    }

    
    @Override
    public List<WordStudyStatistic> getWordStudyStatisticByPage(PageQuery pageQuery) {
		return wordStudyStatisticDao.getWordStudyStatisticByPage( pageQuery.getParams());
    }
    	
    @Override
    public int count(PageQuery pageQuery) {
		return wordStudyStatisticDao.count( pageQuery.getParams());
    }

    /******* getter and setter ***/
    
	public WordStudyStatisticDao getWordStudyStatisticDao() {
		return wordStudyStatisticDao;
	}

	public void setWordStudyStatisticDao(WordStudyStatisticDao wordStudyStatisticDao) {
		this.wordStudyStatisticDao = wordStudyStatisticDao;
	}
}
