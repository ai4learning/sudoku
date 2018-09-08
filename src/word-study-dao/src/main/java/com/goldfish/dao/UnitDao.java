
/**
 * Copyright(c) 2004-2018 bianfeng
 */


 package com.goldfish.dao;

import java.util.List;
import com.goldfish.domain.Unit;
import java.util.Map;
/**
 * @author hellosscat
 * @since 2018-5-8
 * 课程单元 Dao接口类
 */
public interface UnitDao {
    
    
    
    	/**
     * 添加并返回设置id的Unit对象
     * 
     * @param unit
     * @return
     */
    public int addUnit(Unit unit);
    
	/**
     * 更新Unit
     * 
     * @param unit
     */
    public void updateUnit(Unit unit);
    
    
    

    /**
     * 根据主键删除Unit
     * 
     * @param id
     */
    public void deleteUnit(Long id);


	/**
     * 根据主键获取Unit
     * 
     * @param id
     * @return
     */	
    public Unit getUnitById(Long id);
    


    
    /**
     * 根据example取得唯一的Unit
     * 
     * @param unit
     * @return
     */
    public Unit getUnique(Unit unit);
    


    /**
     * 根据example取得Unit列表
     * 
     * @param  unit
     * @return
     */
    public List<Unit> getListByExample(Unit unit);

    
	/**
     * 分页取得Unit列表
     * 
     * @param paramMap
     * @return
     */
    public List<Unit> getUnitByPage(Map<String,Object> paramMap);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param paramMap
     * @return
     */
    public int count(Map<String,Object> paramMap);

}
