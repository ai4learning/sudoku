package com.goldfish.dao;

import com.goldfish.domain.ClassGrade;

import java.util.List;
import java.util.Map;

/**
 * @author zhangjingtao
 * @date 2018/7/8 0008.
 */
public interface ClassGradeDao {



    /**
     * 添加并返回设置id的ClassGrade对象
     *
     * @param classGrade
     * @return
     */
    public int addClassGrade(ClassGrade classGrade);

    /**
     * 更新ClassGrade
     *
     * @param classGrade
     */
    public void updateClassGrade(ClassGrade classGrade);




    /**
     * 根据主键删除ClassGrade
     *
     * @param id
     */
    public void deleteClassGrade(Long id);


    /**
     * 根据主键获取ClassGrade
     *
     * @param id
     * @return
     */
    public ClassGrade getClassGradeById(Long id);




    /**
     * 根据example取得唯一的ClassGrade
     *
     * @param classGrade
     * @return
     */
    public ClassGrade getUnique(ClassGrade classGrade);



    /**
     * 根据example取得ClassGrade列表
     *
     * @param  classGrade
     * @return
     */
    public List<ClassGrade> getListByExample(ClassGrade classGrade);


    /**
     * 分页取得ClassGrade列表
     *
     * @param paramMap
     * @return
     */
    public List<ClassGrade> getClassGradeByPage(Map<String,Object> paramMap);

    /**
     * 根据查询条件返回数量
     *
     * @param paramMap
     * @return
     */
    public int count(Map<String,Object> paramMap);
}
