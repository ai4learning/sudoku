package com.goldfish.service;

import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.UnitWordsStudy;

/**
 * @author hellosscat
 * @since 2018-5-8
 * jshow 单元单词学习service 接口
 *
 */
public interface UnitWordsStudyService {
   
    /**
     * 添加并返回设置id的UnitWordsStudy对象
     * 
     * @param unitWordsStudy
     * @return
     */
    public CommonResult<UnitWordsStudy> addUnitWordsStudy(UnitWordsStudy unitWordsStudy);
    
	/**
     * 更新UnitWordsStudy
     * 
     * @param unitWordsStudy
     */
    public CommonResult<UnitWordsStudy> updateUnitWordsStudy(UnitWordsStudy unitWordsStudy);
    

    

	 /**
     * 根据主键删除UnitWordsStudy
     * 
     * @param id
     */
    public CommonResult<UnitWordsStudy> deleteUnitWordsStudy(Long id);

	/**
     * 根据主键获取UnitWordsStudy
     * 
     * @param id
     * @return
     */	
    public CommonResult<UnitWordsStudy> getUnitWordsStudyById(Long id);

     


	
    
	/**
     * 根据example取得唯一的UnitWordsStudy
     * 
     * @param unitWordsStudy
     * @return
     */
    public CommonResult<UnitWordsStudy> getUnique(UnitWordsStudy unitWordsStudy);
    



    /**
     * 根据example取得UnitWordsStudy列表
     * 
     * @param  unitWordsStudy
     * @return
     */
    public CommonResult<List<UnitWordsStudy>> getListByExample(UnitWordsStudy unitWordsStudy);
    

	/**
     * 分页取得UnitWordsStudy列表
     * 
     * @param pageQuery
     * @return
     */
    public CommonResult<List<UnitWordsStudy>> getUnitWordsStudyByPage(PageQuery pageQuery);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param pageQuery
     * @return
     */
    public int count(PageQuery pageQuery);
	
	
}
