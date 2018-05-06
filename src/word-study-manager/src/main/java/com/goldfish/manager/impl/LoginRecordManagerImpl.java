package com.goldfish.manager.impl;

import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import com.goldfish.common.PageQuery;
import com.goldfish.domain.LoginRecord;
import com.goldfish.dao.LoginRecordDao;
import com.goldfish.manager.LoginRecordManager;

/**
 * @author hellosscat
 * @since 2018-5-2
 * LoginRecordManager实现类
 */
 @Component("loginRecordManager")
public class LoginRecordManagerImpl implements LoginRecordManager {

	@Resource(name="loginRecordDao")
	private LoginRecordDao loginRecordDao;


  @Override
  public void changeLoginRecordState(Map<String, Object> params) {
    loginRecordDao.changeLoginRecordState(params);
  }

  public LoginRecord addLoginRecord(LoginRecord loginRecord) {
		return loginRecordDao.addLoginRecord(loginRecord);
    }
    
    public void updateLoginRecord(LoginRecord loginRecord) {
		loginRecordDao.updateLoginRecord(loginRecord);
    }
    

    
    public void deleteLoginRecord(Long id) {
		loginRecordDao.deleteLoginRecord(id);
    }


    public LoginRecord getLoginRecordById(Long id) {
		return loginRecordDao.getLoginRecordById(id);
    }
    
   

   
    
    public List<LoginRecord> getAll() {
    	return loginRecordDao.getAll();
    }
    	
    public List<LoginRecord> getListByExample(LoginRecord loginRecord) {
		return loginRecordDao.getListByExample(loginRecord);
    }

        public LoginRecord getUnique(LoginRecord loginRecord) {
		return loginRecordDao.getUnique(loginRecord);
    }

    
    

    
    public List<LoginRecord> getLoginRecordByPage(PageQuery pageQuery) {
		return loginRecordDao.getLoginRecordByPage( pageQuery.getParams());
    }
    	
    public int count(PageQuery pageQuery) {
		return loginRecordDao.count( pageQuery.getParams());
    }

    /******* getter and setter ***/
    
	public LoginRecordDao getLoginRecordDao() {
		return loginRecordDao;
	}

	public void setLoginRecordDao(LoginRecordDao loginRecordDao) {
		this.loginRecordDao = loginRecordDao;
	}
}
