/**
 * Copyright(c) 2004-2018 www.jd.com
 * com.goldfish.manager.LoginRecordManager.java
 */
 package com.goldfish.manager;

import java.util.List;
import java.util.Map;

import com.goldfish.common.PageQuery;
import com.goldfish.domain.LoginRecord;

/**
 * @author hellosscat
 * @since 2018-5-2
 * LoginRecordManager接口类
 */
public interface LoginRecordManager {

   public void changeLoginRecordState(Map<String,Object> params);
 
   /**
     * 添加并返回设置id的LoginRecord对象
     * 
     * @param loginRecord
     * @return
     */
    public LoginRecord addLoginRecord(LoginRecord loginRecord);
    
	/**
     * 更新LoginRecord
     * 
     * @param loginRecord
     */
    public void updateLoginRecord(LoginRecord loginRecord);
    
    

	 /**
     * 根据主键删除LoginRecord
     * 
     * @param id
     */
    public void deleteLoginRecord(Long id);

    	/**
     * 根据主键获取LoginRecord
     * 
     * @param id
     * @return
     */	
    public LoginRecord getLoginRecordById(Long id);

    


       
    /**
     * 取得所有LoginRecord
     * 
     * @return
     */
    public List<LoginRecord> getAll();
    
	/**
     * 根据example取得LoginRecord列表
     * 
     * @param  loginRecord
     * @return
     */
    public List<LoginRecord> getListByExample(LoginRecord loginRecord);
    
        
	/**
     * 根据example取得唯一的LoginRecord
     * 
     * @param loginRecord
     * @return
     */
    public LoginRecord getUnique(LoginRecord loginRecord);
    

    

	/**
     * 分页取得LoginRecord列表
     * 
     * @param pageQuery
     * @return
     */
    public List<LoginRecord> getLoginRecordByPage(PageQuery pageQuery);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param pageQuery
     * @return
     */
    public int count(PageQuery pageQuery);

}
