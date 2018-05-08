
/**
 * Copyright(c) 2004-2018 bianfeng
 */


 package com.goldfish.dao;

import java.util.List;
import com.goldfish.domain.Paper;
import java.util.Map;
/**
 * @author hellosscat
 * @since 2018-5-8
 * 试卷 Dao接口类
 */
public interface PaperDao {
    
    
    
    	/**
     * 添加并返回设置id的Paper对象
     * 
     * @param paper
     * @return
     */
    public int addPaper(Paper paper);
    
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
     * 根据example取得唯一的Paper
     * 
     * @param paper
     * @return
     */
    public Paper getUnique(Paper paper);
    


    /**
     * 根据example取得Paper列表
     * 
     * @param  paper
     * @return
     */
    public List<Paper> getListByExample(Paper paper);

    
	/**
     * 分页取得Paper列表
     * 
     * @param paramMap
     * @return
     */
    public List<Paper> getPaperByPage(Map<String,Object> paramMap);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param paramMap
     * @return
     */
    public int count(Map<String,Object> paramMap);

}
