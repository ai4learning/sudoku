/**
 * Copyright(c) 2013-  www.jd.com
 *
 */

package com.goldfish.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;
import com.goldfish.dao.UserDao;
import com.goldfish.domain.User;
import java.util.Map;



/**
 * @author hellosscat
* @since 2018-5-2
 * User Dao实现类
 */
@Repository("userDao")
public class UserDaoImpl  extends SqlMapClientTemplate implements UserDao {

	    public static final String ADD = "User.add";
	    public static final String UPDATE = "User.update";
	    public static final String DELETE = "User.delete";
	 	
	    public static final String GET_BY_ID = "User.getById";
	    public static final String GET_BY_PAGE = "User.getByPage";
	    public static final String COUNT = "User.count";
	
	
	/**
	 * 新增
	 */
	
	public User addUser(User user) {
    		this.insert(ADD, user);
    		return user;
    }
    
   	/**
	 * 更新
	 */
    public void updateUser(User user) {
    	this.update(UPDATE, user);
    }
    
    	
    /**
	 * 删除
	 */
    public void deleteUser(Long id) {
    	this.update(DELETE, id);
    }

    public User getUserById(Long id) {
    	return (User) this.queryForObject(GET_BY_ID, id);
    }
    
    

	

      
    /**
	 * 获得分页数据
	 *@param params 查询参数类
	 *@return 
	 */
    @SuppressWarnings("unchecked")
    public List<User> getUserByPage(Map<String,Object> params) {
    	return this.queryForList(GET_BY_PAGE, params);
    }
    	
    public int count(Map<String,Object> params) {
    	return (Integer)this.queryForObject(COUNT, params);
    }

}
