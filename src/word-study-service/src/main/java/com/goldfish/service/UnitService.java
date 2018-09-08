package com.goldfish.service;

import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.Unit;

/**
 * @author hellosscat
 * @since 2018-5-8
 * jshow 课程单元service 接口
 *
 */
public interface UnitService {
   
    /**
     * 添加并返回设置id的Unit对象
     * 
     * @param unit
     * @return
     */
    public CommonResult<Unit> addUnit(Unit unit);
    
	/**
     * 更新Unit
     * 
     * @param unit
     */
    public CommonResult<Unit> updateUnit(Unit unit);
    

    

	 /**
     * 根据主键删除Unit
     * 
     * @param id
     */
    public CommonResult<Unit> deleteUnit(Long id);

	/**
     * 根据主键获取Unit
     * 
     * @param id
     * @return
     */	
    public CommonResult<Unit> getUnitById(Long id);

     


	
    
	/**
     * 根据example取得唯一的Unit
     * 
     * @param unit
     * @return
     */
    public CommonResult<Unit> getUnique(Unit unit);
    



    /**
     * 根据example取得Unit列表
     * 
     * @param  unit
     * @return
     */
    public CommonResult<List<Unit>> getListByExample(Unit unit);
    

	/**
     * 分页取得Unit列表
     * 
     * @param pageQuery
     * @return
     */
    public CommonResult<List<Unit>> getUnitByPage(PageQuery pageQuery);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param pageQuery
     * @return
     */
    public int count(PageQuery pageQuery);
	
	
}
