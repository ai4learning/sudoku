package com.goldfish.manager.impl;

import com.goldfish.domain.UnitStudy;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.dao.UnitWordsStudyDao;
import com.goldfish.manager.UnitStudyManager;

/**
 * @author hellosscat
 * @since 2018-5-8
 * 单元单词学习Manager实现类
 */
 @Component("unitStudyManager")
public class UnitStudyManagerImpl implements UnitStudyManager {

	@Resource(name="unitWordsStudyDao")
	private UnitWordsStudyDao unitWordsStudyDao;


  public UnitStudy addUnitWordsStudy(UnitStudy unitStudy) {
		int i=unitWordsStudyDao.addUnitWordsStudy(unitStudy);
		return unitStudy;
    }
    
    public void updateUnitWordsStudy(UnitStudy unitStudy) {
		unitWordsStudyDao.updateUnitWordsStudy(unitStudy);
    }
    

    
    public void deleteUnitWordsStudy(Long id) {
		unitWordsStudyDao.deleteUnitWordsStudy(id);
    }


    public UnitStudy getUnitWordsStudyById(Long id) {
		return unitWordsStudyDao.getUnitWordsStudyById(id);
    }
    
   


    	
   
   public UnitStudy getUnique(UnitStudy unitStudy) {
		return unitWordsStudyDao.getUnique(unitStudy);
    }

    
 public List<UnitStudy> getListByExample(UnitStudy unitStudy) {
    return unitWordsStudyDao.getListByExample(unitStudy);
    }

    
    public List<UnitStudy> getUnitWordsStudyByPage(PageQuery pageQuery) {
		return unitWordsStudyDao.getUnitWordsStudyByPage( pageQuery.getParams());
    }
    	
    public int count(PageQuery pageQuery) {
		return unitWordsStudyDao.count( pageQuery.getParams());
    }

    @Override
    public void otherUnitNotCurStudyPosition(UnitStudy unitStudy) {
        unitWordsStudyDao.updateNotCurStudyPosition(unitStudy);
    }

    /******* getter and setter ***/
    
	public UnitWordsStudyDao getUnitWordsStudyDao() {
		return unitWordsStudyDao;
	}

	public void setUnitWordsStudyDao(UnitWordsStudyDao unitWordsStudyDao) {
		this.unitWordsStudyDao = unitWordsStudyDao;
	}

    public int sumReading(PageQuery pageQuery) {
        return unitWordsStudyDao.sumReading( pageQuery.getParams());
    }

    public int sumWriting(PageQuery pageQuery) {
        return unitWordsStudyDao.sumWriting( pageQuery.getParams());
    }

    @Override
    public void deleteUnitWordsStudyByCondition(UnitStudy unitStudy) {
        unitWordsStudyDao.deleteUnitWordsStudyByCondition(unitStudy);
    }
}
