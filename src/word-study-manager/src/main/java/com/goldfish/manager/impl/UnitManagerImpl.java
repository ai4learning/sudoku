package com.goldfish.manager.impl;

import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.domain.Unit;
import com.goldfish.dao.UnitDao;
import com.goldfish.manager.UnitManager;

/**
 * @author hellosscat
 * @since 2018-5-2
 * UnitManager实现类
 */
 @Component("unitManager")
public class UnitManagerImpl implements UnitManager {

	@Resource(name="unitDao")
	private UnitDao unitDao;


  public Unit addUnit(Unit unit) {
		return unitDao.addUnit(unit);
    }
    
    public void updateUnit(Unit unit) {
		unitDao.updateUnit(unit);
    }
    

    
    public void deleteUnit(Long id) {
		unitDao.deleteUnit(id);
    }


    public Unit getUnitById(Long id) {
		return unitDao.getUnitById(id);
    }
    
   

   
    
    public List<Unit> getAll() {
    	return unitDao.getAll();
    }
    	
    public List<Unit> getListByExample(Unit unit) {
		return unitDao.getListByExample(unit);
    }

        public Unit getUnique(Unit unit) {
		return unitDao.getUnique(unit);
    }

    
    

    
    public List<Unit> getUnitByPage(PageQuery pageQuery) {
		return unitDao.getUnitByPage( pageQuery.getParams());
    }
    	
    public int count(PageQuery pageQuery) {
		return unitDao.count( pageQuery.getParams());
    }

    /******* getter and setter ***/
    
	public UnitDao getUnitDao() {
		return unitDao;
	}

	public void setUnitDao(UnitDao unitDao) {
		this.unitDao = unitDao;
	}
}
