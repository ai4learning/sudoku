
/**
 * Copyright(c) 2004-2018 bianfeng
 */


 package com.goldfish.dao;

import java.util.List;
import com.goldfish.domain.UnitWords;
import java.util.Map;
/**
 * @author hellosscat
 * @since 2018-5-8
 * 单元单词 Dao接口类
 */
public interface UnitWordsDao {
    
    
    	/**
     * 添加并返回设置id的UnitWords对象
     * 
     * @param unitWords
     * @return
     */
    public int addUnitWords(UnitWords unitWords);
    
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
     * 根据example取得唯一的UnitWords
     * 
     * @param unitWords
     * @return
     */
    public UnitWords getUnique(UnitWords unitWords);
    


    /**
     * 根据example取得UnitWords列表
     * 
     * @param  unitWords
     * @return
     */
    public List<UnitWords> getListByExample(UnitWords unitWords);

    
	/**
     * 分页取得UnitWords列表
     * 
     * @param paramMap
     * @return
     */
    public List<UnitWords> getUnitWordsByPage(Map<String,Object> paramMap);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param paramMap
     * @return
     */
    public int count(Map<String,Object> paramMap);

    List<UnitWords> getUnitWordsAsc(Map<String, Object> params);
}
