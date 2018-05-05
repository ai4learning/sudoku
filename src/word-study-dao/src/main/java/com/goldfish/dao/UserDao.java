
/**
 * Copyright(c) 2013-  www.jd.com
 *
 */
 package com.goldfish.dao;

import java.util.List;
import com.goldfish.domain.User;
import java.util.Map;
/**
 * @author hellosscat
 * @since 2018-5-2
 * User Dao接口类
 */
public interface UserDao {
    
    
    
    	/**
     * 添加并返回设置id的User对象
     * 
     * @param user
     * @return
     */
    public User addUser(User user);
    
	/**
     * 更新User
     * 
     * @param user
     */
    public void updateUser(User user);
    


    	
	 /**
     * 根据主键删除User
     * 
     * @param id
     */
    public void deleteUser(Long id);
    

    	/**
     * 根据主键获取User
     * 
     * @param id
     * @return
     */	
    public User getUserById(Long id);

   


	

    

	/**
     * 分页取得User列表
     * 
     * @param params
     * @return
     */
    public List<User> getUserByPage(Map<String,Object> params);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param params
     * @return
     */
    public int count(Map<String,Object> params);

}
