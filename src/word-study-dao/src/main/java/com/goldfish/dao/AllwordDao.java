
/**
 * Copyright(c) 2004-2018 bianfeng
 */


 package com.goldfish.dao;

import java.util.List;
import com.goldfish.domain.Allword;
import java.util.Map;
/**
 * @author hellosscat
 * @since 2018-5-8
 * 原始单词 Dao接口类
 */
public interface AllwordDao {
    
    
    
    	/**
     * 添加并返回设置id的Allword对象
     * 
     * @param allword
     * @return
     */
    public int addAllword(Allword allword);
    
	/**
     * 更新Allword
     * 
     * @param allword
     */
    public void updateAllword(Allword allword);
    
    
    

    /**
     * 根据主键删除Allword
     * 
     * @param id
     */
    public void deleteAllword(Integer id);


	/**
     * 根据主键获取Allword
     * 
     * @param id
     * @return
     */	
    public Allword getAllwordById(Integer id);
    


    
    /**
     * 根据example取得唯一的Allword
     * 
     * @param allword
     * @return
     */
    public Allword getUnique(Allword allword);
    


    /**
     * 根据example取得Allword列表
     * 
     * @param  allword
     * @return
     */
    public List<Allword> getListByExample(Allword allword);

    
	/**
     * 分页取得Allword列表
     * 
     * @param paramMap
     * @return
     */
    public List<Allword> getAllwordByPage(Map<String,Object> paramMap);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param paramMap
     * @return
     */
    public int count(Map<String,Object> paramMap);

}
