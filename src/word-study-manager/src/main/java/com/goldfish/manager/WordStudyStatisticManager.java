/**
 * Copyright(c) 2004-2018 www.jd.com
 * com.goldfish.manager.WordStudyStatisticManager.java
 */
 package com.goldfish.manager;

import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.domain.WordStudyStatistic;

/**
 * @author hellosscat
 * @since 2018-5-2
 * WordStudyStatisticManager接口类
 */
public interface WordStudyStatisticManager {
 
   /**
     * 添加并返回设置id的WordStudyStatistic对象
     * 
     * @param wordStudyStatistic
     * @return
     */
    public WordStudyStatistic addWordStudyStatistic(WordStudyStatistic wordStudyStatistic);
    
	/**
     * 更新WordStudyStatistic
     * 
     * @param wordStudyStatistic
     */
    public void updateWordStudyStatistic(WordStudyStatistic wordStudyStatistic);
    
    

	 /**
     * 根据主键删除WordStudyStatistic
     * 
     * @param id
     */
    public void deleteWordStudyStatistic(Long id);

    	/**
     * 根据主键获取WordStudyStatistic
     * 
     * @param id
     * @return
     */	
    public WordStudyStatistic getWordStudyStatisticById(Long id);

    


       
    /**
     * 取得所有WordStudyStatistic
     * 
     * @return
     */
    public List<WordStudyStatistic> getAll();
    
	/**
     * 根据example取得WordStudyStatistic列表
     * 
     * @param  wordStudyStatistic
     * @return
     */
    public List<WordStudyStatistic> getListByExample(WordStudyStatistic wordStudyStatistic);
    
        
	/**
     * 根据example取得唯一的WordStudyStatistic
     * 
     * @param wordStudyStatistic
     * @return
     */
    public WordStudyStatistic getUnique(WordStudyStatistic wordStudyStatistic);
    

    

	/**
     * 分页取得WordStudyStatistic列表
     * 
     * @param pageQuery
     * @return
     */
    public List<WordStudyStatistic> getWordStudyStatisticByPage(PageQuery pageQuery);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param pageQuery
     * @return
     */
    public int count(PageQuery pageQuery);

}
