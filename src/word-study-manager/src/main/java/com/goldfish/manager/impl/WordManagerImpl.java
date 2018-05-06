package com.goldfish.manager.impl;

import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.domain.Word;
import com.goldfish.dao.WordDao;
import com.goldfish.manager.WordManager;

/**
 * @author hellosscat
 * @since 2018-5-2
 * WordManager实现类
 */
 @Component("wordManager")
public class WordManagerImpl implements WordManager {

	@Resource(name="wordDao")
	private WordDao wordDao;


  public Word addWord(Word word) {
		return wordDao.addWord(word);
    }
    
    public void updateWord(Word word) {
		wordDao.updateWord(word);
    }
    

    
    public void deleteWord(Integer id) {
		wordDao.deleteWord(id);
    }


    public Word getWordById(Integer id) {
		return wordDao.getWordById(id);
    }
    
   

   
    
    public List<Word> getAll() {
    	return wordDao.getAll();
    }
    	
    public List<Word> getListByExample(Word word) {
		return wordDao.getListByExample(word);
    }

        public Word getUnique(Word word) {
		return wordDao.getUnique(word);
    }

    
    

    
    public List<Word> getWordByPage(PageQuery pageQuery) {
		return wordDao.getWordByPage( pageQuery.getParams());
    }
    	
    public int count(PageQuery pageQuery) {
		return wordDao.count( pageQuery.getParams());
    }

    /******* getter and setter ***/
    
	public WordDao getWordDao() {
		return wordDao;
	}

	public void setWordDao(WordDao wordDao) {
		this.wordDao = wordDao;
	}
}
