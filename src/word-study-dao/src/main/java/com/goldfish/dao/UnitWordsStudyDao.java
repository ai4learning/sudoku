
/**
 * Copyright(c) 2004-2018 bianfeng
 */


 package com.goldfish.dao;

import java.util.List;

import com.goldfish.domain.UnitStudy;

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
     * @param unitStudy
     * @return
     */
    public int addUnitWordsStudy(UnitStudy unitStudy);
    
	/**
     * 更新UnitWordsStudy
     * 
     * @param unitStudy
     */
    public void updateUnitWordsStudy(UnitStudy unitStudy);

    /**
     * 更新用户非当前单元学习位置为false
     * @param unitStudy
     */
    public void updateNotCurStudyPosition(UnitStudy unitStudy);
    
    
    

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
    public UnitStudy getUnitWordsStudyById(Long id);
    


    
    /**
     * 根据example取得唯一的UnitWordsStudy
     * 
     * @param unitStudy
     * @return
     */
    public UnitStudy getUnique(UnitStudy unitStudy);
    


    /**
     * 根据example取得UnitWordsStudy列表
     * 
     * @param  unitStudy
     * @return
     */
    public List<UnitStudy> getListByExample(UnitStudy unitStudy);

    
	/**
     * 分页取得UnitWordsStudy列表
     * 
     * @param paramMap
     * @return
     */
    public List<UnitStudy> getUnitWordsStudyByPage(Map<String,Object> paramMap);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param paramMap
     * @return
     */
    public int count(Map<String,Object> paramMap);



}
