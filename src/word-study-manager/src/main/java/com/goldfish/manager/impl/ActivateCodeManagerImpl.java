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


  @Override
  public ActivateCode addActivateCode(ActivateCode activateCode) {
		int i=activateCodeDao.addActivateCode(activateCode);
		return activateCode;
    }
    
    @Override
    public void updateActivateCode(ActivateCode activateCode) {
		activateCodeDao.updateActivateCode(activateCode);
    }
    

    
    @Override
    public void deleteActivateCode(Long id) {
		activateCodeDao.deleteActivateCode(id);
    }


    @Override
    public ActivateCode getActivateCodeById(Long id) {
		return activateCodeDao.getActivateCodeById(id);
    }
    
   


    	
   
   @Override
   public ActivateCode getUnique(ActivateCode activateCode) {
		return activateCodeDao.getUnique(activateCode);
    }

    
 @Override
 public List<ActivateCode> getListByExample(ActivateCode activateCode) {
    return activateCodeDao.getListByExample(activateCode);
    }

    
    @Override
    public List<ActivateCode> getActivateCodeByPage(PageQuery pageQuery) {
		return activateCodeDao.getActivateCodeByPage( pageQuery.getParams());
    }
    	
    @Override
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
