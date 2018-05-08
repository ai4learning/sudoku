package com.goldfish.manager.impl;

import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.domain.SelfWordsStudy;
import com.goldfish.dao.SelfWordsStudyDao;
import com.goldfish.manager.SelfWordsStudyManager;

/**
 * @author hellosscat
 * @since 2018-5-8
 * 学生自身单词学习Manager实现类
 */
 @Component("selfWordsStudyManager")
public class SelfWordsStudyManagerImpl implements SelfWordsStudyManager {

	@Resource(name="selfWordsStudyDao")
	private SelfWordsStudyDao selfWordsStudyDao;


  public SelfWordsStudy addSelfWordsStudy(SelfWordsStudy selfWordsStudy) {
		int i=selfWordsStudyDao.addSelfWordsStudy(selfWordsStudy);
		return selfWordsStudy;
    }
    
    public void updateSelfWordsStudy(SelfWordsStudy selfWordsStudy) {
		selfWordsStudyDao.updateSelfWordsStudy(selfWordsStudy);
    }
    

    
    public void deleteSelfWordsStudy(Long id) {
		selfWordsStudyDao.deleteSelfWordsStudy(id);
    }


    public SelfWordsStudy getSelfWordsStudyById(Long id) {
		return selfWordsStudyDao.getSelfWordsStudyById(id);
    }
    
   


    	
   
   public SelfWordsStudy getUnique(SelfWordsStudy selfWordsStudy) {
		return selfWordsStudyDao.getUnique(selfWordsStudy);
    }

    
 public List<SelfWordsStudy> getListByExample(SelfWordsStudy selfWordsStudy) {
    return selfWordsStudyDao.getListByExample(selfWordsStudy);
    }

    
    public List<SelfWordsStudy> getSelfWordsStudyByPage(PageQuery pageQuery) {
		return selfWordsStudyDao.getSelfWordsStudyByPage( pageQuery.getParams());
    }
    	
    public int count(PageQuery pageQuery) {
		return selfWordsStudyDao.count( pageQuery.getParams());
    }

    /******* getter and setter ***/
    
	public SelfWordsStudyDao getSelfWordsStudyDao() {
		return selfWordsStudyDao;
	}

	public void setSelfWordsStudyDao(SelfWordsStudyDao selfWordsStudyDao) {
		this.selfWordsStudyDao = selfWordsStudyDao;
	}
}
