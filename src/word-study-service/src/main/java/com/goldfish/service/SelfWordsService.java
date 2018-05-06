package com.goldfish.service;

import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.SelfWords;

/**
 * @author hellosscat
 * @since 2018-5-2
 * jshow SelfWordsservice 接口
 *
 */
public interface SelfWordsService {
   
    /**
     * 添加并返回设置id的SelfWords对象
     * 
     * @param selfWords
     * @return
     */
    public CommonResult<SelfWords> addSelfWords(SelfWords selfWords);
    
	/**
     * 更新SelfWords
     * 
     * @param selfWords
     */
    public CommonResult<SelfWords> updateSelfWords(SelfWords selfWords);
    

    

	 /**
     * 根据主键删除SelfWords
     * 
     * @param id
     */
    public CommonResult<SelfWords> deleteSelfWords(Long id);

	/**
     * 根据主键获取SelfWords
     * 
     * @param id
     * @return
     */	
    public CommonResult<SelfWords> getSelfWordsById(Long id);

     

    /**
     * 取得所有SelfWords
     * 
     * @return
     */
    public CommonResult<List<SelfWords>> getAll();
    
	/**
     * 根据example取得SelfWords列表
     * 
     * @param  selfWords
     * @return
     */
    public CommonResult<List<SelfWords>> getListByExample(SelfWords selfWords);
    
	/**
     * 根据example取得唯一的SelfWords
     * 
     * @param selfWords
     * @return
     */
    public CommonResult<SelfWords> getUnique(SelfWords selfWords);
    
    


    

	/**
     * 分页取得SelfWords列表
     * 
     * @param pageQuery
     * @return
     */
    public CommonResult<List<SelfWords>> getSelfWordsByPage(PageQuery pageQuery);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param pageQuery
     * @return
     */
    public int count(PageQuery pageQuery);
	
	
}
