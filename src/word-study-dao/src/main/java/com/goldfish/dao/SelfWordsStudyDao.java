
/**
 * Copyright(c) 2004-2018 bianfeng
 */


 package com.goldfish.dao;

import java.util.List;
import com.goldfish.domain.SelfWordsStudy;
import java.util.Map;
/**
 * @author hellosscat
 * @since 2018-5-8
 * 学生自身单词学习 Dao接口类
 */
public interface SelfWordsStudyDao {
    
    
    
    	/**
     * 添加并返回设置id的SelfWordsStudy对象
     * 
     * @param selfWordsStudy
     * @return
     */
    public int addSelfWordsStudy(SelfWordsStudy selfWordsStudy);
    
	/**
     * 更新SelfWordsStudy
     * 
     * @param selfWordsStudy
     */
    public void updateSelfWordsStudy(SelfWordsStudy selfWordsStudy);
    
    
    

    /**
     * 根据主键删除SelfWordsStudy
     * 
     * @param id
     */
    public void deleteSelfWordsStudy(Long id);


	/**
     * 根据主键获取SelfWordsStudy
     * 
     * @param id
     * @return
     */	
    public SelfWordsStudy getSelfWordsStudyById(Long id);
    


    
    /**
     * 根据example取得唯一的SelfWordsStudy
     * 
     * @param selfWordsStudy
     * @return
     */
    public SelfWordsStudy getUnique(SelfWordsStudy selfWordsStudy);
    


    /**
     * 根据example取得SelfWordsStudy列表
     * 
     * @param  selfWordsStudy
     * @return
     */
    public List<SelfWordsStudy> getListByExample(SelfWordsStudy selfWordsStudy);

    
	/**
     * 分页取得SelfWordsStudy列表
     * 
     * @param paramMap
     * @return
     */
    public List<SelfWordsStudy> getSelfWordsStudyByPage(Map<String,Object> paramMap);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param paramMap
     * @return
     */
    public int count(Map<String,Object> paramMap);

}
