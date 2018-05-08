package com.goldfish.manager.impl;

import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.domain.UnitWordsStudy;
import com.goldfish.dao.UnitWordsStudyDao;
import com.goldfish.manager.UnitWordsStudyManager;

/**
 * @author hellosscat
 * @since 2018-5-8
 * 单元单词学习Manager实现类
 */
 @Component("unitWordsStudyManager")
public class UnitWordsStudyManagerImpl implements UnitWordsStudyManager {

	@Resource(name="unitWordsStudyDao")
	private UnitWordsStudyDao unitWordsStudyDao;


  public UnitWordsStudy addUnitWordsStudy(UnitWordsStudy unitWordsStudy) {
		int i=unitWordsStudyDao.addUnitWordsStudy(unitWordsStudy);
		return unitWordsStudy;
    }
    
    public void updateUnitWordsStudy(UnitWordsStudy unitWordsStudy) {
		unitWordsStudyDao.updateUnitWordsStudy(unitWordsStudy);
    }
    

    
    public void deleteUnitWordsStudy(Long id) {
		unitWordsStudyDao.deleteUnitWordsStudy(id);
    }


    public UnitWordsStudy getUnitWordsStudyById(Long id) {
		return unitWordsStudyDao.getUnitWordsStudyById(id);
    }
    
   


    	
   
   public UnitWordsStudy getUnique(UnitWordsStudy unitWordsStudy) {
		return unitWordsStudyDao.getUnique(unitWordsStudy);
    }

    
 public List<UnitWordsStudy> getListByExample(UnitWordsStudy unitWordsStudy) {
    return unitWordsStudyDao.getListByExample(unitWordsStudy);
    }

    
    public List<UnitWordsStudy> getUnitWordsStudyByPage(PageQuery pageQuery) {
		return unitWordsStudyDao.getUnitWordsStudyByPage( pageQuery.getParams());
    }
    	
    public int count(PageQuery pageQuery) {
		return unitWordsStudyDao.count( pageQuery.getParams());
    }

    /******* getter and setter ***/
    
	public UnitWordsStudyDao getUnitWordsStudyDao() {
		return unitWordsStudyDao;
	}

	public void setUnitWordsStudyDao(UnitWordsStudyDao unitWordsStudyDao) {
		this.unitWordsStudyDao = unitWordsStudyDao;
	}
}
