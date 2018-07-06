package com.goldfish.manager.impl;

import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.domain.UnitWords;
import com.goldfish.dao.UnitWordsDao;
import com.goldfish.manager.UnitWordsManager;

/**
 * @author hellosscat
 * @since 2018-5-8
 * 单元单词Manager实现类
 */
 @Component("unitWordsManager")
public class UnitWordsManagerImpl implements UnitWordsManager {

	@Resource(name="unitWordsDao")
	private UnitWordsDao unitWordsDao;


  @Override
  public UnitWords addUnitWords(UnitWords unitWords) {
		int i=unitWordsDao.addUnitWords(unitWords);
		return unitWords;
    }
    
    @Override
    public void updateUnitWords(UnitWords unitWords) {
		unitWordsDao.updateUnitWords(unitWords);
    }
    

    
    @Override
    public void deleteUnitWords(Long id) {
		unitWordsDao.deleteUnitWords(id);
    }


    @Override
    public UnitWords getUnitWordsById(Long id) {
		return unitWordsDao.getUnitWordsById(id);
    }
    
   


    	
   
   @Override
   public UnitWords getUnique(UnitWords unitWords) {
		return unitWordsDao.getUnique(unitWords);
    }

    
 @Override
 public List<UnitWords> getListByExample(UnitWords unitWords) {
    return unitWordsDao.getListByExample(unitWords);
    }

    
    @Override
    public List<UnitWords> getUnitWordsByPage(PageQuery pageQuery) {
		return unitWordsDao.getUnitWordsByPage( pageQuery.getParams());
    }
    	
    @Override
    public int count(PageQuery pageQuery) {
		return unitWordsDao.count( pageQuery.getParams());
    }

    /******* getter and setter ***/
    
	public UnitWordsDao getUnitWordsDao() {
		return unitWordsDao;
	}

	public void setUnitWordsDao(UnitWordsDao unitWordsDao) {
		this.unitWordsDao = unitWordsDao;
	}
}
