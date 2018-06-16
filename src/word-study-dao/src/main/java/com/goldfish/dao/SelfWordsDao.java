
/**
 * Copyright(c) 2004-2018 bianfeng
 */


 package com.goldfish.dao;

import java.util.List;
import com.goldfish.domain.SelfWords;
import java.util.Map;
/**
 * @author hellosscat
 * @since 2018-5-8
 * 学生自身单词 Dao接口类
 */
public interface SelfWordsDao {
    
    
    
    	/**
     * 添加并返回设置id的SelfWords对象
     * 
     * @param selfWords
     * @return
     */
    public int addSelfWords(SelfWords selfWords);
    
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
     * 根据example取得唯一的SelfWords
     * 
     * @param selfWords
     * @return
     */
    public SelfWords getUnique(SelfWords selfWords);
    


    /**
     * 根据example取得SelfWords列表
     * 
     * @param  selfWords
     * @return
     */
    public List<SelfWords> getListByExample(SelfWords selfWords);

    
	/**
     * 分页取得SelfWords列表
     * 
     * @param paramMap
     * @return
     */
    public List<SelfWords> getSelfWordsByPage(Map<String,Object> paramMap);

    /**
     * in查询
     * @param paramMap
     * @return
     */
    public List<SelfWords> inQuerySelfWords(Map<String,Object> paramMap);


	
	/**
     * 根据查询条件返回数量
     * 
     * @param paramMap
     * @return
     */
    public int count(Map<String,Object> paramMap);

}
