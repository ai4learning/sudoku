package com.goldfish.service;

import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.UnitWords;

/**
 * @author hellosscat
 * @since 2018-5-8
 * jshow 单元单词service 接口
 *
 */
public interface UnitWordsService {
   
    /**
     * 添加并返回设置id的UnitWords对象
     * 
     * @param unitWords
     * @return
     */
    public CommonResult<UnitWords> addUnitWords(UnitWords unitWords);
    
	/**
     * 更新UnitWords
     * 
     * @param unitWords
     */
    public CommonResult<UnitWords> updateUnitWords(UnitWords unitWords);
    

    

	 /**
     * 根据主键删除UnitWords
     * 
     * @param id
     */
    public CommonResult<UnitWords> deleteUnitWords(Long id);

	/**
     * 根据主键获取UnitWords
     * 
     * @param id
     * @return
     */	
    public CommonResult<UnitWords> getUnitWordsById(Long id);

     


	
    
	/**
     * 根据example取得唯一的UnitWords
     * 
     * @param unitWords
     * @return
     */
    public CommonResult<UnitWords> getUnique(UnitWords unitWords);
    



    /**
     * 根据example取得UnitWords列表
     * 
     * @param  unitWords
     * @return
     */
    public CommonResult<List<UnitWords>> getListByExample(UnitWords unitWords);
    

	/**
     * 分页取得UnitWords列表
     * 
     * @param pageQuery
     * @return
     */
    public CommonResult<List<UnitWords>> getUnitWordsByPage(PageQuery pageQuery);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param pageQuery
     * @return
     */
    public int count(PageQuery pageQuery);


    CommonResult<List<UnitWords>> getUnitWordsAsc(PageQuery pageQuery);
}
