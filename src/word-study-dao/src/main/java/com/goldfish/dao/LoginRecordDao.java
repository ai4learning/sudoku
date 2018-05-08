
/**
 * Copyright(c) 2004-2018 bianfeng
 */


 package com.goldfish.dao;

import java.util.List;
import com.goldfish.domain.LoginRecord;
import java.util.Map;
/**
 * @author hellosscat
 * @since 2018-5-8
 * 登录 Dao接口类
 */
public interface LoginRecordDao {
    
    
    
    	/**
     * 添加并返回设置id的LoginRecord对象
     * 
     * @param loginRecord
     * @return
     */
    public int addLoginRecord(LoginRecord loginRecord);
    
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
     * 根据example取得唯一的LoginRecord
     * 
     * @param loginRecord
     * @return
     */
    public LoginRecord getUnique(LoginRecord loginRecord);
    


    /**
     * 根据example取得LoginRecord列表
     * 
     * @param  loginRecord
     * @return
     */
    public List<LoginRecord> getListByExample(LoginRecord loginRecord);

    
	/**
     * 分页取得LoginRecord列表
     * 
     * @param paramMap
     * @return
     */
    public List<LoginRecord> getLoginRecordByPage(Map<String,Object> paramMap);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param paramMap
     * @return
     */
    public int count(Map<String,Object> paramMap);

}
