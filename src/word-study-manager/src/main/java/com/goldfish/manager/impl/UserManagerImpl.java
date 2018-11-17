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


  @Override
  public User addUser(User user) {
		int i=userDao.addUser(user);
		return user;
    }
    
    @Override
    public void updateUser(User user) {
		userDao.updateUser(user);
    }
    

    
    @Override
    public void deleteUser(Long id) {
		userDao.deleteUser(id);
    }


    @Override
    public User getUserById(Long id) {
		return userDao.getUserById(id);
    }
    
   


    	
   
   @Override
   public User getUnique(User user) {
		return userDao.getUnique(user);
    }

    
 @Override
 public List<User> getListByExample(User user) {
    return userDao.getListByExample(user);
    }

    
    @Override
    public List<User> getUserByPage(PageQuery pageQuery) {
		return userDao.getUserByPage( pageQuery.getParams());
    }
    	
    @Override
    public int count(PageQuery pageQuery) {
		return userDao.count( pageQuery.getParams());
    }

    @Override
    public List<User> getUserLike(PageQuery pageQuery) {
        return userDao.getUserLike(pageQuery.getParams());
    }

    /******* getter and setter ***/
    
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
