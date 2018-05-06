package com.goldfish.service;

import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.User;

/**
 * @author hellosscat
 * @since 2018-5-2
 * jshow Userservice 接口
 *
 */
public interface UserService {
   
    /**
     * 添加并返回设置id的User对象
     * 
     * @param user
     * @return
     */
    public CommonResult<User> addUser(User user);
    
	/**
     * 更新User
     * 
     * @param user
     */
    public CommonResult<User> updateUser(User user);
    

    

	 /**
     * 根据主键删除User
     * 
     * @param id
     */
    public CommonResult<User> deleteUser(Long id);

	/**
     * 根据主键获取User
     * 
     * @param id
     * @return
     */	
    public CommonResult<User> getUserById(Long id);

     

    /**
     * 取得所有User
     * 
     * @return
     */
    public CommonResult<List<User>> getAll();
    
	/**
     * 根据example取得User列表
     * 
     * @param  user
     * @return
     */
    public CommonResult<List<User>> getListByExample(User user);
    
	/**
     * 根据example取得唯一的User
     * 
     * @param user
     * @return
     */
    public CommonResult<User> getUnique(User user);
    
    


    

	/**
     * 分页取得User列表
     * 
     * @param pageQuery
     * @return
     */
    public CommonResult<List<User>> getUserByPage(PageQuery pageQuery);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param pageQuery
     * @return
     */
    public int count(PageQuery pageQuery);
	
	
}
