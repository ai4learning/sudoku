package com.goldfish.service;

import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.WordStudyStatistic;

/**
 * @author hellosscat
 * @since 2018-5-2
 * jshow WordStudyStatisticservice 接口
 *
 */
public interface WordStudyStatisticService {
   
    /**
     * 添加并返回设置id的WordStudyStatistic对象
     * 
     * @param wordStudyStatistic
     * @return
     */
    public CommonResult<WordStudyStatistic> addWordStudyStatistic(WordStudyStatistic wordStudyStatistic);
    
	/**
     * 更新WordStudyStatistic
     * 
     * @param wordStudyStatistic
     */
    public CommonResult<WordStudyStatistic> updateWordStudyStatistic(WordStudyStatistic wordStudyStatistic);
    

    

	 /**
     * 根据主键删除WordStudyStatistic
     * 
     * @param id
     */
    public CommonResult<WordStudyStatistic> deleteWordStudyStatistic(Long id);

	/**
     * 根据主键获取WordStudyStatistic
     * 
     * @param id
     * @return
     */	
    public CommonResult<WordStudyStatistic> getWordStudyStatisticById(Long id);

     

    /**
     * 取得所有WordStudyStatistic
     * 
     * @return
     */
    public CommonResult<List<WordStudyStatistic>> getAll();
    
	/**
     * 根据example取得WordStudyStatistic列表
     * 
     * @param  wordStudyStatistic
     * @return
     */
    public CommonResult<List<WordStudyStatistic>> getListByExample(WordStudyStatistic wordStudyStatistic);
    
	/**
     * 根据example取得唯一的WordStudyStatistic
     * 
     * @param wordStudyStatistic
     * @return
     */
    public CommonResult<WordStudyStatistic> getUnique(WordStudyStatistic wordStudyStatistic);
    
    


    

	/**
     * 分页取得WordStudyStatistic列表
     * 
     * @param pageQuery
     * @return
     */
    public CommonResult<List<WordStudyStatistic>> getWordStudyStatisticByPage(PageQuery pageQuery);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param pageQuery
     * @return
     */
    public int count(PageQuery pageQuery);
	
	
}
