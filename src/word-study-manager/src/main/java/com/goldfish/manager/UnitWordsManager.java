/**
 * Copyright(c) 2004-2018 www.jd.com
 * com.goldfish.manager.UnitWordsManager.java
 */
 package com.goldfish.manager;

import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.domain.UnitWords;

/**
 * @author hellosscat
 * @since 2018-5-2
 * UnitWordsManager接口类
 */
public interface UnitWordsManager {
 
   /**
     * 添加并返回设置id的UnitWords对象
     * 
     * @param unitWords
     * @return
     */
    public UnitWords addUnitWords(UnitWords unitWords);
    
	/**
     * 更新UnitWords
     * 
     * @param unitWords
     */
    public void updateUnitWords(UnitWords unitWords);
    
    

	 /**
     * 根据主键删除UnitWords
     * 
     * @param id
     */
    public void deleteUnitWords(Long id);

    	/**
     * 根据主键获取UnitWords
     * 
     * @param id
     * @return
     */	
    public UnitWords getUnitWordsById(Long id);

    


       
    /**
     * 取得所有UnitWords
     * 
     * @return
     */
    public List<UnitWords> getAll();
    
	/**
     * 根据example取得UnitWords列表
     * 
     * @param  unitWords
     * @return
     */
    public List<UnitWords> getListByExample(UnitWords unitWords);
    
        
	/**
     * 根据example取得唯一的UnitWords
     * 
     * @param unitWords
     * @return
     */
    public UnitWords getUnique(UnitWords unitWords);
    

    

	/**
     * 分页取得UnitWords列表
     * 
     * @param pageQuery
     * @return
     */
    public List<UnitWords> getUnitWordsByPage(PageQuery pageQuery);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param pageQuery
     * @return
     */
    public int count(PageQuery pageQuery);

}
