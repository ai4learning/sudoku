
/**
 * Copyright(c) 2004-2018 bianfeng
 */


 package com.goldfish.dao;

import java.util.List;
import com.goldfish.domain.WordStudyStatistic;
import java.util.Map;
/**
 * @author hellosscat
 * @since 2018-5-8
 * 单词学习统计 Dao接口类
 */
public interface WordStudyStatisticDao {
    
    
    
    	/**
     * 添加并返回设置id的WordStudyStatistic对象
     * 
     * @param wordStudyStatistic
     * @return
     */
    public int addWordStudyStatistic(WordStudyStatistic wordStudyStatistic);
    
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
     * 根据example取得唯一的WordStudyStatistic
     * 
     * @param wordStudyStatistic
     * @return
     */
    public WordStudyStatistic getUnique(WordStudyStatistic wordStudyStatistic);
    


    /**
     * 根据example取得WordStudyStatistic列表
     * 
     * @param  wordStudyStatistic
     * @return
     */
    public List<WordStudyStatistic> getListByExample(WordStudyStatistic wordStudyStatistic);

    
	/**
     * 分页取得WordStudyStatistic列表
     * 
     * @param paramMap
     * @return
     */
    public List<WordStudyStatistic> getWordStudyStatisticByPage(Map<String,Object> paramMap);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param paramMap
     * @return
     */
    public int count(Map<String,Object> paramMap);

}
