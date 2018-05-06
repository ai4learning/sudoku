
/**
 * Copyright(c) 2013-  www.jd.com
 *
 */
 package com.goldfish.dao;

import java.util.List;
import com.goldfish.domain.WordStudy;
import java.util.Map;
/**
 * @author hellosscat
 * @since 2018-5-2
 * WordStudy Dao接口类
 */
public interface WordStudyDao {
    
    
    
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
     * 取得所有WordStudy
     * 
     * @return
     */
    public List<WordStudy> getAll();
    
	/**
     * 根据example取得WordStudy列表
     * 
     * @param  wordStudy
     * @return
     */
    public List<WordStudy> getListByExample(WordStudy wordStudy);
    
    	/**
     * 根据example取得唯一的WordStudy
     * 
     * @param wordStudy
     * @return
     */
    public WordStudy getUnique(WordStudy wordStudy);
    

    

    

	/**
     * 分页取得WordStudy列表
     * 
     * @param params
     * @return
     */
    public List<WordStudy> getWordStudyByPage(Map<String,Object> params);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param params
     * @return
     */
    public int count(Map<String,Object> params);

}
