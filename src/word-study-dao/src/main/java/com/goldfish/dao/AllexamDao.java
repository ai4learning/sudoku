
/**
 * Copyright(c) 2004-2018 bianfeng
 */


 package com.goldfish.dao;

import java.util.List;
import com.goldfish.domain.Allexam;
import java.util.Map;
/**
 * @author hellosscat
 * @since 2018-5-8
 * Allexam Dao接口类
 */
public interface AllexamDao {
    
    
    
    	/**
     * 添加并返回设置id的Allexam对象
     * 
     * @param allexam
     * @return
     */
    public int addAllexam(Allexam allexam);
    
	/**
     * 更新Allexam
     * 
     * @param allexam
     */
    public void updateAllexam(Allexam allexam);
    
    
    

    /**
     * 根据主键删除Allexam
     * 
     * @param id
     */
    public void deleteAllexam(Integer id);


	/**
     * 根据主键获取Allexam
     * 
     * @param id
     * @return
     */	
    public Allexam getAllexamById(Integer id);
    


    
    /**
     * 根据example取得唯一的Allexam
     * 
     * @param allexam
     * @return
     */
    public Allexam getUnique(Allexam allexam);
    


    /**
     * 根据example取得Allexam列表
     * 
     * @param  allexam
     * @return
     */
    public List<Allexam> getListByExample(Allexam allexam);

    
	/**
     * 分页取得Allexam列表
     * 
     * @param paramMap
     * @return
     */
    public List<Allexam> getAllexamByPage(Map<String,Object> paramMap);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param paramMap
     * @return
     */
    public int count(Map<String,Object> paramMap);

}
