package com.goldfish.manager.impl;

import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.domain.SelfWords;
import com.goldfish.dao.SelfWordsDao;
import com.goldfish.manager.SelfWordsManager;

/**
 * @author hellosscat
 * @since 2018-5-2
 * SelfWordsManager实现类
 */
 @Component("selfWordsManager")
public class SelfWordsManagerImpl implements SelfWordsManager {

	@Resource(name="selfWordsDao")
	private SelfWordsDao selfWordsDao;


  public SelfWords addSelfWords(SelfWords selfWords) {
		return selfWordsDao.addSelfWords(selfWords);
    }
    
    public void updateSelfWords(SelfWords selfWords) {
		selfWordsDao.updateSelfWords(selfWords);
    }
    

    
    public void deleteSelfWords(Long id) {
		selfWordsDao.deleteSelfWords(id);
    }


    public SelfWords getSelfWordsById(Long id) {
		return selfWordsDao.getSelfWordsById(id);
    }
    
   

   
    
    public List<SelfWords> getAll() {
    	return selfWordsDao.getAll();
    }
    	
    public List<SelfWords> getListByExample(SelfWords selfWords) {
		return selfWordsDao.getListByExample(selfWords);
    }

        public SelfWords getUnique(SelfWords selfWords) {
		return selfWordsDao.getUnique(selfWords);
    }

    
    

    
    public List<SelfWords> getSelfWordsByPage(PageQuery pageQuery) {
		return selfWordsDao.getSelfWordsByPage( pageQuery.getParams());
    }
    	
    public int count(PageQuery pageQuery) {
		return selfWordsDao.count( pageQuery.getParams());
    }

    /******* getter and setter ***/
    
	public SelfWordsDao getSelfWordsDao() {
		return selfWordsDao;
	}

	public void setSelfWordsDao(SelfWordsDao selfWordsDao) {
		this.selfWordsDao = selfWordsDao;
	}
}
