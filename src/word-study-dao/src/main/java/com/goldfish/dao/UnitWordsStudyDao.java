
/**
 * Copyright(c) 2004-2018 bianfeng
 */


 package com.goldfish.dao;

import java.util.List;
import com.goldfish.domain.UnitWordsStudy;
import java.util.Map;
/**
 * @author hellosscat
 * @since 2018-5-8
 * 单元单词学习 Dao接口类
 */
public interface UnitWordsStudyDao {
    
    
    
    	/**
     * 添加并返回设置id的UnitWordsStudy对象
     * 
     * @param unitWordsStudy
     * @return
     */
    public int addUnitWordsStudy(UnitWordsStudy unitWordsStudy);
    
	/**
     * 更新UnitWordsStudy
     * 
     * @param unitWordsStudy
     */
    public void updateUnitWordsStudy(UnitWordsStudy unitWordsStudy);
    
    
    

    /**
     * 根据主键删除UnitWordsStudy
     * 
     * @param id
     */
    public void deleteUnitWordsStudy(Long id);


	/**
     * 根据主键获取UnitWordsStudy
     * 
     * @param id
     * @return
     */	
    public UnitWordsStudy getUnitWordsStudyById(Long id);
    


    
    /**
     * 根据example取得唯一的UnitWordsStudy
     * 
     * @param unitWordsStudy
     * @return
     */
    public UnitWordsStudy getUnique(UnitWordsStudy unitWordsStudy);
    


    /**
     * 根据example取得UnitWordsStudy列表
     * 
     * @param  unitWordsStudy
     * @return
     */
    public List<UnitWordsStudy> getListByExample(UnitWordsStudy unitWordsStudy);

    
	/**
     * 分页取得UnitWordsStudy列表
     * 
     * @param paramMap
     * @return
     */
    public List<UnitWordsStudy> getUnitWordsStudyByPage(Map<String,Object> paramMap);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param paramMap
     * @return
     */
    public int count(Map<String,Object> paramMap);

}
