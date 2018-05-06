/**
 * Copyright(c) 2004-2018 www.jd.com
 * com.goldfish.manager.ActivateCodeManager.java
 */
 package com.goldfish.manager;

import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.domain.ActivateCode;

/**
 * @author hellosscat
 * @since 2018-5-2
 * ActivateCodeManager接口类
 */
public interface ActivateCodeManager {
 
   /**
     * 添加并返回设置id的ActivateCode对象
     * 
     * @param activateCode
     * @return
     */
    public ActivateCode addActivateCode(ActivateCode activateCode);
    
	/**
     * 更新ActivateCode
     * 
     * @param activateCode
     */
    public void updateActivateCode(ActivateCode activateCode);
    
    

	 /**
     * 根据主键删除ActivateCode
     * 
     * @param id
     */
    public void deleteActivateCode(Long id);

    	/**
     * 根据主键获取ActivateCode
     * 
     * @param id
     * @return
     */	
    public ActivateCode getActivateCodeById(Long id);

    


       
    /**
     * 取得所有ActivateCode
     * 
     * @return
     */
    public List<ActivateCode> getAll();
    
	/**
     * 根据example取得ActivateCode列表
     * 
     * @param  activateCode
     * @return
     */
    public List<ActivateCode> getListByExample(ActivateCode activateCode);
    
        
	/**
     * 根据example取得唯一的ActivateCode
     * 
     * @param activateCode
     * @return
     */
    public ActivateCode getUnique(ActivateCode activateCode);
    

    

	/**
     * 分页取得ActivateCode列表
     * 
     * @param pageQuery
     * @return
     */
    public List<ActivateCode> getActivateCodeByPage(PageQuery pageQuery);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param pageQuery
     * @return
     */
    public int count(PageQuery pageQuery);

}
