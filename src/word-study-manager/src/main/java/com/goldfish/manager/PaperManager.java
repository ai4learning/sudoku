/**
 * Copyright(c) 2004-2018 www.jd.com
 * com.goldfish.manager.PaperManager.java
 */
 package com.goldfish.manager;

import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.domain.Paper;

/**
 * @author hellosscat
 * @since 2018-5-2
 * PaperManager接口类
 */
public interface PaperManager {
 
   /**
     * 添加并返回设置id的Paper对象
     * 
     * @param paper
     * @return
     */
    public Paper addPaper(Paper paper);
    
	/**
     * 更新Paper
     * 
     * @param paper
     */
    public void updatePaper(Paper paper);
    
    

	 /**
     * 根据主键删除Paper
     * 
     * @param id
     */
    public void deletePaper(Long id);

    	/**
     * 根据主键获取Paper
     * 
     * @param id
     * @return
     */	
    public Paper getPaperById(Long id);

    


       
    /**
     * 取得所有Paper
     * 
     * @return
     */
    public List<Paper> getAll();
    
	/**
     * 根据example取得Paper列表
     * 
     * @param  paper
     * @return
     */
    public List<Paper> getListByExample(Paper paper);
    
        
	/**
     * 根据example取得唯一的Paper
     * 
     * @param paper
     * @return
     */
    public Paper getUnique(Paper paper);
    

    

	/**
     * 分页取得Paper列表
     * 
     * @param pageQuery
     * @return
     */
    public List<Paper> getPaperByPage(PageQuery pageQuery);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param pageQuery
     * @return
     */
    public int count(PageQuery pageQuery);

}
