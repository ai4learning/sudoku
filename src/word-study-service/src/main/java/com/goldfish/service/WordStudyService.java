package com.goldfish.service;

import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.WordStudy;

/**
 * @author hellosscat
 * @since 2018-5-2
 * jshow WordStudyservice 接口
 *
 */
public interface WordStudyService {
   
    /**
     * 添加并返回设置id的WordStudy对象
     * 
     * @param wordStudy
     * @return
     */
    public CommonResult<WordStudy> addWordStudy(WordStudy wordStudy);
    
	/**
     * 更新WordStudy
     * 
     * @param wordStudy
     */
    public CommonResult<WordStudy> updateWordStudy(WordStudy wordStudy);
    

    

	 /**
     * 根据主键删除WordStudy
     * 
     * @param id
     */
    public CommonResult<WordStudy> deleteWordStudy(Long id);

	/**
     * 根据主键获取WordStudy
     * 
     * @param id
     * @return
     */	
    public CommonResult<WordStudy> getWordStudyById(Long id);

     

    /**
     * 取得所有WordStudy
     * 
     * @return
     */
    public CommonResult<List<WordStudy>> getAll();
    
	/**
     * 根据example取得WordStudy列表
     * 
     * @param  wordStudy
     * @return
     */
    public CommonResult<List<WordStudy>> getListByExample(WordStudy wordStudy);
    
	/**
     * 根据example取得唯一的WordStudy
     * 
     * @param wordStudy
     * @return
     */
    public CommonResult<WordStudy> getUnique(WordStudy wordStudy);
    
    


    

	/**
     * 分页取得WordStudy列表
     * 
     * @param pageQuery
     * @return
     */
    public CommonResult<List<WordStudy>> getWordStudyByPage(PageQuery pageQuery);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param pageQuery
     * @return
     */
    public int count(PageQuery pageQuery);
	
	
}
