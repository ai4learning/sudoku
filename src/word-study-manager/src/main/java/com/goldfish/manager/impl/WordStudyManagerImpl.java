package com.goldfish.manager.impl;

import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.domain.WordStudy;
import com.goldfish.dao.WordStudyDao;
import com.goldfish.manager.WordStudyManager;

/**
 * @author hellosscat
 * @since 2018-5-8
 * 单词学习Manager实现类
 */
 @Component("wordStudyManager")
public class WordStudyManagerImpl implements WordStudyManager {

	@Resource(name="wordStudyDao")
	private WordStudyDao wordStudyDao;


  public WordStudy addWordStudy(WordStudy wordStudy) {
		int i=wordStudyDao.addWordStudy(wordStudy);
		return wordStudy;
    }
    
    public void updateWordStudy(WordStudy wordStudy) {
		wordStudyDao.updateWordStudy(wordStudy);
    }
    

    
    public void deleteWordStudy(Long id) {
		wordStudyDao.deleteWordStudy(id);
    }


    public WordStudy getWordStudyById(Long id) {
		return wordStudyDao.getWordStudyById(id);
    }
    
   


    	
   
   public WordStudy getUnique(WordStudy wordStudy) {
		return wordStudyDao.getUnique(wordStudy);
    }

    
 public List<WordStudy> getListByExample(WordStudy wordStudy) {
    return wordStudyDao.getListByExample(wordStudy);
    }

    
    public List<WordStudy> getWordStudyByPage(PageQuery pageQuery) {
		return wordStudyDao.getWordStudyByPage( pageQuery.getParams());
    }
    	
    public int count(PageQuery pageQuery) {
		return wordStudyDao.count( pageQuery.getParams());
    }

    /******* getter and setter ***/
    
	public WordStudyDao getWordStudyDao() {
		return wordStudyDao;
	}

	public void setWordStudyDao(WordStudyDao wordStudyDao) {
		this.wordStudyDao = wordStudyDao;
	}
}
