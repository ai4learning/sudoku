/**
 * Copyright(c) 2004-2018 bianfeng
 */


 package com.goldfish.manager;

import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.domain.UnitWordsStudy;

/**
 * @author hellosscat
 * @since 2018-5-8
 * 单元单词学习Manager接口类
 */
public interface UnitWordsStudyManager {
 
   /**
     * 添加并返回设置id的UnitWordsStudy对象
     * 
     * @param unitWordsStudy
     * @return
     */
    public UnitWordsStudy addUnitWordsStudy(UnitWordsStudy unitWordsStudy);
    
	/**
     * 更新UnitWordsStudy
     * 
     * @param unitWordsStudy
     */
    public void updateUnitWordsStudy(UnitWordsStudy unitWordsStudy);
    
    

	 /**
     * 根据主键删除UnitWordsStudy
     * 
     * @param id
     */
    public void deleteUnitWordsStudy(Long id);

    	/**
     * 根据主键获取UnitWordsStudy
     * 
     * @param id
     * @return
     */	
    public UnitWordsStudy getUnitWordsStudyById(Long id);

    



    
        
	/**
     * 根据example取得唯一的UnitWordsStudy
     * 
     * @param unitWordsStudy
     * @return
     */
    public UnitWordsStudy getUnique(UnitWordsStudy unitWordsStudy);
    

    
    /**
     * 根据example取得UnitWordsStudy列表
     * 
     * @param  unitWordsStudy
     * @return
     */
    public List<UnitWordsStudy> getListByExample(UnitWordsStudy unitWordsStudy);

	/**
     * 分页取得UnitWordsStudy列表
     * 
     * @param pageQuery
     * @return
     */
    public List<UnitWordsStudy> getUnitWordsStudyByPage(PageQuery pageQuery);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param pageQuery
     * @return
     */
    public int count(PageQuery pageQuery);

}
