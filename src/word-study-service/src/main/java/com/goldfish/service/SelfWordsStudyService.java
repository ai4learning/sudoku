package com.goldfish.service;

import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.SelfWordsStudy;
import com.goldfish.vo.unit.SaveUnitStudyVO;
import com.goldfish.vo.unit.WordStudyDto;

/**
 * @author hellosscat
 * @since 2018-5-8
 * jshow 学生自身单词学习service 接口
 *
 */
public interface SelfWordsStudyService {
   
    /**
     * 添加并返回设置id的SelfWordsStudy对象
     * 
     * @param selfWordsStudy
     * @return
     */
    public CommonResult<SelfWordsStudy> addSelfWordsStudy(SelfWordsStudy selfWordsStudy);
    
	/**
     * 更新SelfWordsStudy
     * 
     * @param selfWordsStudy
     */
    public CommonResult<SelfWordsStudy> updateSelfWordsStudy(SelfWordsStudy selfWordsStudy);
    

    

	 /**
     * 根据主键删除SelfWordsStudy
     * 
     * @param id
     */
    public CommonResult<SelfWordsStudy> deleteSelfWordsStudy(Long id);

	/**
     * 根据主键获取SelfWordsStudy
     * 
     * @param id
     * @return
     */	
    public CommonResult<SelfWordsStudy> getSelfWordsStudyById(Long id);

     


	
    
	/**
     * 根据example取得唯一的SelfWordsStudy
     * 
     * @param selfWordsStudy
     * @return
     */
    public CommonResult<SelfWordsStudy> getUnique(SelfWordsStudy selfWordsStudy);
    



    /**
     * 根据example取得SelfWordsStudy列表
     * 
     * @param  selfWordsStudy
     * @return
     */
    public CommonResult<List<SelfWordsStudy>> getListByExample(SelfWordsStudy selfWordsStudy);
    

	/**
     * 分页取得SelfWordsStudy列表
     * 
     * @param pageQuery
     * @return
     */
    public CommonResult<List<SelfWordsStudy>> getSelfWordsStudyByPage(PageQuery pageQuery);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param pageQuery
     * @return
     */
    public int count(PageQuery pageQuery);


    SaveUnitStudyVO saveErrorStudy(Integer userId, String moduleCode, String extra, Integer unitNbr, String vocCode, String studytoken, Long totalReadingTime, Long totalWritingTime, List<WordStudyDto> vocDataAfterReview);

}
