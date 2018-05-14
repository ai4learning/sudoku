package com.goldfish.service;

import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.ActivateCode;

/**
 * @author hellosscat
 * @since 2018-5-8
 * jshow 激活码service 接口
 *
 */
public interface ActivateCodeService {
   
    /**
     * 添加并返回设置id的ActivateCode对象
     * 
     * @param activateCode
     * @return
     */
    public CommonResult<ActivateCode> addActivateCode(ActivateCode activateCode);
    
	/**
     * 更新ActivateCode
     * 
     * @param activateCode
     */
    public CommonResult<ActivateCode> updateActivateCode(ActivateCode activateCode);
    

    

	 /**
     * 根据主键删除ActivateCode
     * 
     * @param id
     */
    public CommonResult<ActivateCode> deleteActivateCode(Long id);

	/**
     * 根据主键获取ActivateCode
     * 
     * @param id
     * @return
     */	
    public CommonResult<ActivateCode> getActivateCodeById(Long id);

     


	
    
	/**
     * 根据example取得唯一的ActivateCode
     * 
     * @param activateCode
     * @return
     */
    public CommonResult<ActivateCode> getUnique(ActivateCode activateCode);
    



    /**
     * 根据example取得ActivateCode列表
     * 
     * @param  activateCode
     * @return
     */
    public CommonResult<List<ActivateCode>> getListByExample(ActivateCode activateCode);
    

	/**
     * 分页取得ActivateCode列表
     * 
     * @param pageQuery
     * @return
     */
    public CommonResult<List<ActivateCode>> getActivateCodeByPage(PageQuery pageQuery);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param pageQuery
     * @return
     */
    public int count(PageQuery pageQuery);


    public CommonResult<ActivateCode> generateActivateCode();
}
