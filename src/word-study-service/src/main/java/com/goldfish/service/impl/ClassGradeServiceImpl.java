package com.goldfish.service.impl;

import com.goldfish.common.CommonResult;
import com.goldfish.common.PageQuery;
import com.goldfish.domain.ClassGrade;
import com.goldfish.manager.ClassGradeManager;
import com.goldfish.service.ClassGradeService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author zhangjingtao
 * @date 2018/7/9 0009.
 */
@Service("classGradeService")
public class ClassGradeServiceImpl implements ClassGradeService {

    private static final Logger logger = Logger.getLogger(ClassGradeServiceImpl.class);

    @Resource
    private ClassGradeManager classGradeManager;

    @Override
    public CommonResult<ClassGrade> addClassGrade(ClassGrade classGrade) {
        CommonResult<ClassGrade> result = new CommonResult<ClassGrade>();
        try{

            classGrade.setCreated(new Date());


            classGrade.setModified(new Date());

            result.addDefaultModel(classGradeManager.addClassGrade(classGrade));
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error("添加 班级失败", e);
            result.setSuccess(false);
        }
        return result;
    }

    @Override
    public CommonResult<ClassGrade> updateClassGrade(ClassGrade classGrade) {
        CommonResult<ClassGrade> result = new CommonResult<ClassGrade>();
        try {

            classGrade.setModified(new Date());

            classGradeManager.updateClassGrade(classGrade);
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error("更新 班级失败", e);
            result.setSuccess(false);
        }
        return result;
    }

    @Override
    public CommonResult<ClassGrade> deleteClassGrade(Long id) {
        CommonResult<ClassGrade> result = new CommonResult<ClassGrade>();
        try {
            classGradeManager.deleteClassGrade(id);
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error("删除 班级失败", e);
            result.setSuccess(false);
        }
        return result;
    }

    @Override
    public CommonResult<ClassGrade> getClassGradeById(Long id) {
        CommonResult<ClassGrade> result = new CommonResult<ClassGrade>();
        try {
            result.addDefaultModel("classGrade", classGradeManager.getClassGradeById(id));
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error("根据主键获取 班级失败", e);
            result.setSuccess(false);
        }
        return result;
    }

    @Override
    public CommonResult<ClassGrade> getUnique(ClassGrade classGrade) {
        CommonResult<ClassGrade> result = new CommonResult<ClassGrade>();
        try {
            result.addDefaultModel(classGradeManager.getUnique(classGrade));
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error("根据example获取唯一 班级失败", e);
            result.setSuccess(false);
        }
        return result;
    }

    @Override
    public CommonResult<List<ClassGrade>> getListByExample(ClassGrade classGrade) {
        CommonResult<List<ClassGrade>> result = new CommonResult<List<ClassGrade>>();
        try {
            List<ClassGrade> list = classGradeManager.getListByExample(classGrade);
            result.addDefaultModel("list", list);
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error("取得 班级失败", e);
            result.setSuccess(false);
        }
        return result;
    }

    @Override
    public CommonResult<List<ClassGrade>> getClassGradeByPage(PageQuery pageQuery) {
        CommonResult<List<ClassGrade>> result = new CommonResult<List<ClassGrade>>();
        try {
            int totalCount = this.count(pageQuery);
            if (totalCount > 0) {
                pageQuery.setTotalCount(totalCount);
                List<ClassGrade> list = classGradeManager.getClassGradeByPage(pageQuery);
                result.addDefaultModel("list", list);
                result.addModel("pageQuery", pageQuery);
            }
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error("分页获取 班级失败", e);
            result.setSuccess(false);
        }
        return result;
    }

    @Override
    public int count(PageQuery pageQuery) {
        return classGradeManager.count(pageQuery);
    }
}
