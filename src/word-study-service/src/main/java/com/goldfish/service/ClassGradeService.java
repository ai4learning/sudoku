package com.goldfish.service;

import com.goldfish.common.CommonResult;
import com.goldfish.common.PageQuery;
import com.goldfish.domain.ClassGrade;

import java.util.List;

/**
 * @author zhangjingtao
 * @date 2018/7/9 0009.
 */
public interface ClassGradeService {
    /**
     * 添加并返回设置id的ClassGrade对象
     *
     * @param classGrade
     * @return
     */
    public CommonResult<ClassGrade> addClassGrade(ClassGrade classGrade);

    /**
     * 更新ClassGrade
     *
     * @param classGrade
     */
    public CommonResult<ClassGrade> updateClassGrade(ClassGrade classGrade);




    /**
     * 根据主键删除ClassGrade
     *
     * @param id
     */
    public CommonResult<ClassGrade> deleteClassGrade(Long id);

    /**
     * 根据主键获取ClassGrade
     *
     * @param id
     * @return
     */
    public CommonResult<ClassGrade> getClassGradeById(Long id);






    /**
     * 根据example取得唯一的ClassGrade
     *
     * @param classGrade
     * @return
     */
    public CommonResult<ClassGrade> getUnique(ClassGrade classGrade);




    /**
     * 根据example取得ClassGrade列表
     *
     * @param  classGrade
     * @return
     */
    public CommonResult<List<ClassGrade>> getListByExample(ClassGrade classGrade);


    /**
     * 分页取得ClassGrade列表
     *
     * @param pageQuery
     * @return
     */
    public CommonResult<List<ClassGrade>> getClassGradeByPage(PageQuery pageQuery);

    /**
     * 根据查询条件返回数量
     *
     * @param pageQuery
     * @return
     */
    public int count(PageQuery pageQuery);
}
