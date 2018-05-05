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
 * @since 2018-5-2
 * UserManager实现类
 */
@Component("userManager")
public class UserManagerImpl implements UserManager {

	@Resource(name="userDao")
	private UserDao userDao;


  	public User addUser(User user) {
		return userDao.addUser(user);
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
