package com.goldfish.manager.impl;

import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.domain.User;
import com.goldfish.dao.UserDao;
import com.goldfish.manager.UserManager;

/**
 * @author hellosscat
 * @since 2018-5-8
 * 用户Manager实现类
 */
 @Component("userManager")
public class UserManagerImpl implements UserManager {

	@Resource(name="userDao")
	private UserDao userDao;


  public User addUser(User user) {
		int i=userDao.addUser(user);
		return user;
    }
    
    public void updateUser(User user) {
		userDao.updateUser(user);
    }
    

    
    public void deleteUser(Long id) {
		userDao.deleteUser(id);
    }


    public User getUserById(Long id) {
		return userDao.getUserById(id);
    }
    
   


    	
   
   public User getUnique(User user) {
		return userDao.getUnique(user);
    }

    
 public List<User> getListByExample(User user) {
    return userDao.getListByExample(user);
    }

    
    public List<User> getUserByPage(PageQuery pageQuery) {
		return userDao.getUserByPage( pageQuery.getParams());
    }
    	
    public int count(PageQuery pageQuery) {
		return userDao.count( pageQuery.getParams());
    }

    /******* getter and setter ***/
    
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
