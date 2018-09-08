
/**
 * Copyright(c) 2004-2018 bianfeng
 */


 package com.goldfish.dao;

import java.util.List;
import com.goldfish.domain.User;
import java.util.Map;
/**
 * @author hellosscat
 * @since 2018-5-8
 * 用户 Dao接口类
 */
public interface UserDao {
    
    
    
    	/**
     * 添加并返回设置id的User对象
     * 
     * @param user
     * @return
     */
    public int addUser(User user);
    
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
     * 根据example取得唯一的User
     * 
     * @param user
     * @return
     */
    public User getUnique(User user);
    


    /**
     * 根据example取得User列表
     * 
     * @param  user
     * @return
     */
    public List<User> getListByExample(User user);

    
	/**
     * 分页取得User列表
     * 
     * @param paramMap
     * @return
     */
    public List<User> getUserByPage(Map<String,Object> paramMap);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param paramMap
     * @return
     */
    public int count(Map<String,Object> paramMap);

}
