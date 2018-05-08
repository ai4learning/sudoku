package com.goldfish.manager.impl;

import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.domain.ActivateCode;
import com.goldfish.dao.ActivateCodeDao;
import com.goldfish.manager.ActivateCodeManager;

/**
 * @author hellosscat
 * @since 2018-5-8
 * 激活码Manager实现类
 */
 @Component("activateCodeManager")
public class ActivateCodeManagerImpl implements ActivateCodeManager {

	@Resource(name="activateCodeDao")
	private ActivateCodeDao activateCodeDao;


  public ActivateCode addActivateCode(ActivateCode activateCode) {
		int i=activateCodeDao.addActivateCode(activateCode);
		return activateCode;
    }
    
    public void updateActivateCode(ActivateCode activateCode) {
		activateCodeDao.updateActivateCode(activateCode);
    }
    

    
    public void deleteActivateCode(Long id) {
		activateCodeDao.deleteActivateCode(id);
    }


    public ActivateCode getActivateCodeById(Long id) {
		return activateCodeDao.getActivateCodeById(id);
    }
    
   


    	
   
   public ActivateCode getUnique(ActivateCode activateCode) {
		return activateCodeDao.getUnique(activateCode);
    }

    
 public List<ActivateCode> getListByExample(ActivateCode activateCode) {
    return activateCodeDao.getListByExample(activateCode);
    }

    
    public List<ActivateCode> getActivateCodeByPage(PageQuery pageQuery) {
		return activateCodeDao.getActivateCodeByPage( pageQuery.getParams());
    }
    	
    public int count(PageQuery pageQuery) {
		return activateCodeDao.count( pageQuery.getParams());
    }

    /******* getter and setter ***/
    
	public ActivateCodeDao getActivateCodeDao() {
		return activateCodeDao;
	}

	public void setActivateCodeDao(ActivateCodeDao activateCodeDao) {
		this.activateCodeDao = activateCodeDao;
	}
}
