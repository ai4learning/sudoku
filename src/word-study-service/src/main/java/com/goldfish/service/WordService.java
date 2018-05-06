package com.goldfish.service;

import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.Word;

/**
 * @author hellosscat
 * @since 2018-5-2
 * jshow Wordservice 接口
 *
 */
public interface WordService {
   
    /**
     * 添加并返回设置id的Word对象
     * 
     * @param word
     * @return
     */
    public CommonResult<Word> addWord(Word word);
    
	/**
     * 更新Word
     * 
     * @param word
     */
    public CommonResult<Word> updateWord(Word word);
    

    

	 /**
     * 根据主键删除Word
     * 
     * @param id
     */
    public CommonResult<Word> deleteWord(Integer id);

	/**
     * 根据主键获取Word
     * 
     * @param id
     * @return
     */	
    public CommonResult<Word> getWordById(Integer id);

     

    /**
     * 取得所有Word
     * 
     * @return
     */
    public CommonResult<List<Word>> getAll();
    
	/**
     * 根据example取得Word列表
     * 
     * @param  word
     * @return
     */
    public CommonResult<List<Word>> getListByExample(Word word);
    
	/**
     * 根据example取得唯一的Word
     * 
     * @param word
     * @return
     */
    public CommonResult<Word> getUnique(Word word);
    
    


    

	/**
     * 分页取得Word列表
     * 
     * @param pageQuery
     * @return
     */
    public CommonResult<List<Word>> getWordByPage(PageQuery pageQuery);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param pageQuery
     * @return
     */
    public int count(PageQuery pageQuery);
	
	
}
