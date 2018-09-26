package com.goldfish.manager.impl;

import com.goldfish.dao.cache.redis.RedisUtils;
import com.goldfish.domain.Unit;
import com.goldfish.domain.UnitStudy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

import com.goldfish.common.PageQuery;
import com.goldfish.dao.UnitWordsStudyDao;
import com.goldfish.manager.UnitStudyManager;

/**
 * @author hellosscat
 * @since 2018-5-8
 * 单元单词学习Manager实现类
 */
@Component("unitStudyManager")
public class UnitStudyManagerImpl implements UnitStudyManager {

    @Resource(name = "unitWordsStudyDao")
    private UnitWordsStudyDao unitWordsStudyDao;

    @Resource(name = "redisUtils")
    private RedisUtils redisUtils;

    @Override
    public UnitStudy addUnitWordsStudy(UnitStudy unitStudy) {
        int i = unitWordsStudyDao.addUnitWordsStudy(unitStudy);
        redisUtils.setObject(unitStudy.getClass().getSimpleName() + ":" + unitStudy.getId(), unitStudy);
        return unitStudy;
    }

    @Override
    public void updateUnitWordsStudy(UnitStudy unitStudy) {
        redisUtils.setObject(unitStudy.getClass().getSimpleName() + ":" + unitStudy.getId(), unitStudy);
        unitWordsStudyDao.updateUnitWordsStudy(unitStudy);
    }


    @Override
    public void deleteUnitWordsStudy(Long id) {
        redisUtils.deleteByKey(UnitStudy.class.getSimpleName() + ":" + id);
        unitWordsStudyDao.deleteUnitWordsStudy(id);
    }


    @Override
    public UnitStudy getUnitWordsStudyById(Long id) {
        UnitStudy unitStudy = redisUtils.getObject(UnitStudy.class.getSimpleName() + ":" + id, UnitStudy.class);
        if (unitStudy == null) {
            return unitWordsStudyDao.getUnitWordsStudyById(id);
        }
        return unitStudy;
    }


    @Override
    public UnitStudy getUnique(UnitStudy unitStudy) {
        return unitWordsStudyDao.getUnique(unitStudy);
    }


    @Override
    public List<UnitStudy> getListByExample(UnitStudy unitStudy) {
        return unitWordsStudyDao.getListByExample(unitStudy);
    }


    @Override
    public List<UnitStudy> getUnitWordsStudyByPage(PageQuery pageQuery) {
        return unitWordsStudyDao.getUnitWordsStudyByPage(pageQuery.getParams());
    }

    @Override
    public int count(PageQuery pageQuery) {
        return unitWordsStudyDao.count(pageQuery.getParams());
    }

    @Override
    public void otherUnitNotCurStudyPosition(UnitStudy unitStudy) {
        unitWordsStudyDao.updateNotCurStudyPosition(unitStudy);
    }

    /******* getter and setter ***/

    public UnitWordsStudyDao getUnitWordsStudyDao() {
        return unitWordsStudyDao;
    }

    public void setUnitWordsStudyDao(UnitWordsStudyDao unitWordsStudyDao) {
        this.unitWordsStudyDao = unitWordsStudyDao;
    }

    @Override
    public int sumReading(PageQuery pageQuery) {
        return unitWordsStudyDao.sumReading(pageQuery.getParams());
    }

    @Override
    public int sumWriting(PageQuery pageQuery) {
        return unitWordsStudyDao.sumWriting(pageQuery.getParams());
    }

    @Override
    public void deleteUnitWordsStudyByCondition(UnitStudy unitStudy) {
        unitWordsStudyDao.deleteUnitWordsStudyByCondition(unitStudy);
    }
}
