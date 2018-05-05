/**
 * Copyright(c) 2004-2018 www.jd.com
 * com.goldfish.manager.UserManager.java
 */
package com.goldfish.manager;

import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.domain.User;

/**
 * @author hellosscat
 * @since 2018-5-2
 * UserManager接口类
 */
public interface UserManager {
 
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
     * @param pageQuery
     * @return
     */
    public List<User> getUserByPage(PageQuery pageQuery);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param pageQuery
     * @return
     */
    public int count(PageQuery pageQuery);

}
