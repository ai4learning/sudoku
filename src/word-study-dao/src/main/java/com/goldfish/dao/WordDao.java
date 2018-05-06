
/**
 * Copyright(c) 2013-  www.jd.com
 *
 */
 package com.goldfish.dao;

import java.util.List;
import com.goldfish.domain.Word;
import java.util.Map;
/**
 * @author hellosscat
 * @since 2018-5-2
 * Word Dao接口类
 */
public interface WordDao {
    
    
    
    	/**
     * 添加并返回设置id的Word对象
     * 
     * @param word
     * @return
     */
    public Word addWord(Word word);
    
	/**
     * 更新Word
     * 
     * @param word
     */
    public void updateWord(Word word);
    


    	
	 /**
     * 根据主键删除Word
     * 
     * @param id
     */
    public void deleteWord(Integer id);
    

    	/**
     * 根据主键获取Word
     * 
     * @param id
     * @return
     */	
    public Word getWordById(Integer id);

   


	
    /**
     * 取得所有Word
     * 
     * @return
     */
    public List<Word> getAll();
    
	/**
     * 根据example取得Word列表
     * 
     * @param  word
     * @return
     */
    public List<Word> getListByExample(Word word);
    
    	/**
     * 根据example取得唯一的Word
     * 
     * @param word
     * @return
     */
    public Word getUnique(Word word);
    

    

    

	/**
     * 分页取得Word列表
     * 
     * @param params
     * @return
     */
    public List<Word> getWordByPage(Map<String,Object> params);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param params
     * @return
     */
    public int count(Map<String,Object> params);

}
