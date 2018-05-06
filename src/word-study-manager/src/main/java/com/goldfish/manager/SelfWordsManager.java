/**
 * Copyright(c) 2004-2018 www.jd.com
 * com.goldfish.manager.SelfWordsManager.java
 */
 package com.goldfish.manager;

import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.domain.SelfWords;

/**
 * @author hellosscat
 * @since 2018-5-2
 * SelfWordsManager接口类
 */
public interface SelfWordsManager {
 
   /**
     * 添加并返回设置id的SelfWords对象
     * 
     * @param selfWords
     * @return
     */
    public SelfWords addSelfWords(SelfWords selfWords);
    
	/**
     * 更新SelfWords
     * 
     * @param selfWords
     */
    public void updateSelfWords(SelfWords selfWords);
    
    

	 /**
     * 根据主键删除SelfWords
     * 
     * @param id
     */
    public void deleteSelfWords(Long id);

    	/**
     * 根据主键获取SelfWords
     * 
     * @param id
     * @return
     */	
    public SelfWords getSelfWordsById(Long id);

    


       
    /**
     * 取得所有SelfWords
     * 
     * @return
     */
    public List<SelfWords> getAll();
    
	/**
     * 根据example取得SelfWords列表
     * 
     * @param  selfWords
     * @return
     */
    public List<SelfWords> getListByExample(SelfWords selfWords);
    
        
	/**
     * 根据example取得唯一的SelfWords
     * 
     * @param selfWords
     * @return
     */
    public SelfWords getUnique(SelfWords selfWords);
    

    

	/**
     * 分页取得SelfWords列表
     * 
     * @param pageQuery
     * @return
     */
    public List<SelfWords> getSelfWordsByPage(PageQuery pageQuery);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param pageQuery
     * @return
     */
    public int count(PageQuery pageQuery);

}
