/**
 * Copyright(c) 2004-2018 bianfeng
 */


 package com.goldfish.manager;

import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.domain.UnitStudy;

/**
 * @author hellosscat
 * @since 2018-5-8
 * 单元单词学习Manager接口类
 */
public interface UnitStudyManager {
 
   /**
     * 添加并返回设置id的UnitWordsStudy对象
     * 
     * @param unitStudy
     * @return
     */
    public UnitStudy addUnitWordsStudy(UnitStudy unitStudy);
    
	/**
     * 更新UnitWordsStudy
     * 
     * @param unitStudy
     */
    public void updateUnitWordsStudy(UnitStudy unitStudy);
    
    

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
    public UnitStudy getUnitWordsStudyById(Long id);

    



    
        
	/**
     * 根据example取得唯一的UnitWordsStudy
     * 
     * @param unitStudy
     * @return
     */
    public UnitStudy getUnique(UnitStudy unitStudy);
    

    
    /**
     * 根据example取得UnitWordsStudy列表
     * 
     * @param  unitStudy
     * @return
     */
    public List<UnitStudy> getListByExample(UnitStudy unitStudy);

	/**
     * 分页取得UnitWordsStudy列表
     * 
     * @param pageQuery
     * @return
     */
    public List<UnitStudy> getUnitWordsStudyByPage(PageQuery pageQuery);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param pageQuery
     * @return
     */
    public int count(PageQuery pageQuery);

    /**
     * 更新用户非当前单元学习位置为false
     * @param unitStudy
     */
    public void otherUnitNotCurStudyPosition(UnitStudy unitStudy);

    public int sumReading(PageQuery pageQuery);

    public int sumWriting(PageQuery pageQuery);

}
