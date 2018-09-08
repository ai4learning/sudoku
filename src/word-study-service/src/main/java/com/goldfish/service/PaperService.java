package com.goldfish.service;

import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.Paper;

/**
 * @author hellosscat
 * @since 2018-5-8
 * jshow 试卷service 接口
 *
 */
public interface PaperService {
   
    /**
     * 添加并返回设置id的Paper对象
     * 
     * @param paper
     * @return
     */
    public CommonResult<Paper> addPaper(Paper paper);
    
	/**
     * 更新Paper
     * 
     * @param paper
     */
    public CommonResult<Paper> updatePaper(Paper paper);
    

    

	 /**
     * 根据主键删除Paper
     * 
     * @param id
     */
    public CommonResult<Paper> deletePaper(Long id);

	/**
     * 根据主键获取Paper
     * 
     * @param id
     * @return
     */	
    public CommonResult<Paper> getPaperById(Long id);

     


	
    
	/**
     * 根据example取得唯一的Paper
     * 
     * @param paper
     * @return
     */
    public CommonResult<Paper> getUnique(Paper paper);
    



    /**
     * 根据example取得Paper列表
     * 
     * @param  paper
     * @return
     */
    public CommonResult<List<Paper>> getListByExample(Paper paper);
    

	/**
     * 分页取得Paper列表
     * 
     * @param pageQuery
     * @return
     */
    public CommonResult<List<Paper>> getPaperByPage(PageQuery pageQuery);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param pageQuery
     * @return
     */
    public int count(PageQuery pageQuery);
	
	
}
