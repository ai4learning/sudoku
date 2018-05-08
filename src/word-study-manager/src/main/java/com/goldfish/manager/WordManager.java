/**
 * Copyright(c) 2004-2018 bianfeng
 */


 package com.goldfish.manager;

import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.domain.Word;

/**
 * @author hellosscat
 * @since 2018-5-8
 * 单词库Manager接口类
 */
public interface WordManager {
 
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
     * 根据example取得唯一的Word
     * 
     * @param word
     * @return
     */
    public Word getUnique(Word word);
    

    
    /**
     * 根据example取得Word列表
     * 
     * @param  word
     * @return
     */
    public List<Word> getListByExample(Word word);

	/**
     * 分页取得Word列表
     * 
     * @param pageQuery
     * @return
     */
    public List<Word> getWordByPage(PageQuery pageQuery);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param pageQuery
     * @return
     */
    public int count(PageQuery pageQuery);

}
