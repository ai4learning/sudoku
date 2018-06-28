/**
 * Copyright(c) 2004-2018 bianfeng
 */


 package com.goldfish.manager;

import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.domain.WordStudy;

/**
 * @author hellosscat
 * @since 2018-5-8
 * 单词学习Manager接口类
 */
public interface WordStudyManager {
 
   /**
     * 添加并返回设置id的WordStudy对象
     * 
     * @param wordStudy
     * @return
     */
    public WordStudy addWordStudy(WordStudy wordStudy);
    
	/**
     * 更新WordStudy
     * 
     * @param wordStudy
     */
    public void updateWordStudy(WordStudy wordStudy);
    
    

	 /**
     * 根据主键删除WordStudy
     * 
     * @param id
     */
    public void deleteWordStudy(Long id);

    	/**
     * 根据主键获取WordStudy
     * 
     * @param id
     * @return
     */	
    public WordStudy getWordStudyById(Long id);

    



    
        
	/**
     * 根据example取得唯一的WordStudy
     * 
     * @param wordStudy
     * @return
     */
    public WordStudy getUnique(WordStudy wordStudy);
    

    
    /**
     * 根据example取得WordStudy列表
     * 
     * @param  wordStudy
     * @return
     */
    public List<WordStudy> getListByExample(WordStudy wordStudy);

	/**
     * 分页取得WordStudy列表
     * 
     * @param pageQuery
     * @return
     */
    public List<WordStudy> getWordStudyByPage(PageQuery pageQuery);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param pageQuery
     * @return
     */
    public int count(PageQuery pageQuery);

    /**
     * 根据查询条件返回数量
     *
     * @param pageQuery
     * @return
     */
    public int countDay(PageQuery pageQuery);
    public List<WordStudy> getStudiedWords(WordStudy wordStudy);
}
