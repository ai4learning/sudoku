package com.goldfish.service;

import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.LoginRecord;
import com.goldfish.domain.User;

/**
 * @author hellosscat
 * @since 2018-5-2
 * jshow LoginRecordservice 接口
 *
 */
public interface LoginRecordService {

    /**
     *
     * @param userName
     * @param token
     * @return
     */
    public LoginRecord getLoginRecordByToken(String userName, String token) throws Exception;

    /**
     * 添加并返回设置id的LoginRecord对象
     *
     * @param loginRecord
     * @return
     */
    public CommonResult<LoginRecord> addLoginRecord(LoginRecord loginRecord);




	/**
     * 更新LoginRecord
     *
     * @param loginRecord
     */
    public CommonResult<LoginRecord> updateLoginRecord(LoginRecord loginRecord);

	 /**
     * 根据主键删除LoginRecord
     *
     * @param id
     */
    public CommonResult<LoginRecord> deleteLoginRecord(Long id);

	/**
     * 根据主键获取LoginRecord
     *
     * @param id
     * @return
     */
    public CommonResult<LoginRecord> getLoginRecordById(Long id);

     

    /**
     * 取得所有LoginRecord
     * 
     * @return
     */
    public CommonResult<List<LoginRecord>> getAll();
    
	/**
     * 根据example取得LoginRecord列表
     * 
     * @param  loginRecord
     * @return
     */
    public CommonResult<List<LoginRecord>> getListByExample(LoginRecord loginRecord);
    
	/**
     * 根据example取得唯一的LoginRecord
     * 
     * @param loginRecord
     * @return
     */
    public CommonResult<LoginRecord> getUnique(LoginRecord loginRecord);
    
    


    

	/**
     * 分页取得LoginRecord列表
     * 
     * @param pageQuery
     * @return
     */
    public CommonResult<List<LoginRecord>> getLoginRecordByPage(PageQuery pageQuery);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param pageQuery
     * @return
     */
    public int count(PageQuery pageQuery);

    /**
     * 刷新登录记录
     * @param user
     */
    LoginRecord refreshLoginRecord(User user);
}
