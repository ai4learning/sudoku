package com.goldfish.service;

import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.UnitStudy;
import com.goldfish.vo.unit.SaveFinishUnitStudyVO;
import com.goldfish.vo.unit.SaveUnitStudyVO;
import com.goldfish.vo.unit.WordStudyDto;

/**
 * @author hellosscat
 * @since 2018-5-8
 * jshow 单元单词学习service 接口
 *
 */
public interface UnitStudyService {


    public SaveFinishUnitStudyVO doAjaxFinishSaveUnit(Integer studentId,
                                                      SaveFinishUnitStudyVO saveFinishUnitStudyVO,
                                                      String moduleCode,
                                                      String extra,
                                                      Integer unitNbr,
                                                      String studyToken,
                                                      Integer isContinue,
                                                      Long seconds4SpellingLetter,
                                                      Long totalReadingTime,
                                                      Long totalWritingTime,
                                                      List<WordStudyDto> vocDataAfterReview,
                                                      Integer totalWordsNbr);


   
    /**
     * 添加并返回设置id的UnitWordsStudy对象
     * 
     * @param unitStudy
     * @return
     */
    public CommonResult<UnitStudy> addUnitWordsStudy(UnitStudy unitStudy);
    
	/**
     * 更新UnitWordsStudy
     * 
     * @param unitStudy
     */
    public CommonResult<UnitStudy> updateUnitWordsStudy(UnitStudy unitStudy);

	 /**
     * 根据主键删除UnitWordsStudy
     * 
     * @param id
     */
    public CommonResult<UnitStudy> deleteUnitWordsStudy(Long id);

	/**
     * 根据主键获取UnitWordsStudy
     * 
     * @param id
     * @return
     */	
    public CommonResult<UnitStudy> getUnitWordsStudyById(Long id);

     


	
    
	/**
     * 根据example取得唯一的UnitWordsStudy
     * 
     * @param unitStudy
     * @return
     */
    public CommonResult<UnitStudy> getUnique(UnitStudy unitStudy);
    



    /**
     * 根据example取得UnitWordsStudy列表
     * 
     * @param  unitStudy
     * @return
     */
    public CommonResult<List<UnitStudy>> getListByExample(UnitStudy unitStudy);
    

	/**
     * 分页取得UnitWordsStudy列表
     * 
     * @param pageQuery
     * @return
     */
    public CommonResult<List<UnitStudy>> getUnitWordsStudyByPage(PageQuery pageQuery);
	
	/**
     * 根据查询条件返回数量
     * 
     * @param pageQuery
     * @return
     */
    public int count(PageQuery pageQuery);


    SaveUnitStudyVO doAjaxInterruptSaveUnit(Integer userId,
                                            SaveUnitStudyVO saveUnitStudyVO,
                                            String moduleCode,
                                            String extra,
                                            Integer unitNbr,
                                            String studyToken,
                                            Integer isContinue,
                                            Long seconds4SpellingLetter,
                                            Long totalReadingTime,
                                            Long totalWritingTime,
                                            List<WordStudyDto> vocDataAfterReview,
                                            Integer totalWordsNbr);

    public int sumReading(PageQuery pageQuery);

    public int sumWriting(PageQuery pageQuery);
}
