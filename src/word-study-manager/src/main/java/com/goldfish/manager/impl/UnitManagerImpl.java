package com.goldfish.manager.impl;

import com.goldfish.dao.cache.redis.RedisUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

import com.goldfish.common.PageQuery;
import com.goldfish.domain.Unit;
import com.goldfish.dao.UnitDao;
import com.goldfish.manager.UnitManager;

/**
 * @author hellosscat
 * @since 2018-5-8
 * 课程单元Manager实现类
 */
@Component("unitManager")
public class UnitManagerImpl implements UnitManager {

    @Resource(name = "unitDao")
    private UnitDao unitDao;

    @Resource(name = "redisUtils")
    private RedisUtils redisUtils;

    @Override
    public Unit addUnit(Unit unit) {
        int i = unitDao.addUnit(unit);
        redisUtils.setObject(unit.getClass().getSimpleName() + ":" + unit.getId(), unit);
        return unit;
    }

    @Override
    public void updateUnit(Unit unit) {
        redisUtils.setObject(unit.getClass().getSimpleName() + ":" + unit.getId(), unit);
        unitDao.updateUnit(unit);
    }


    @Override
    public void deleteUnit(Long id) {
        redisUtils.deleteByKey(Unit.class.getSimpleName() + ":" + id);
        unitDao.deleteUnit(id);
    }


    @Override
    public Unit getUnitById(Long id) {
        Unit unit = redisUtils.getObject(Unit.class.getSimpleName() + ":" + id, Unit.class);
        if (unit == null) {
            return unitDao.getUnitById(id);
        }
        return unit;
    }


    @Override
    public Unit getUnique(Unit unit) {
        return unitDao.getUnique(unit);
    }


    @Override
    public List<Unit> getListByExample(Unit unit) {
        return unitDao.getListByExample(unit);
    }


    @Override
    public List<Unit> getUnitByPage(PageQuery pageQuery) {
        return unitDao.getUnitByPage(pageQuery.getParams());
    }

    @Override
    public int count(PageQuery pageQuery) {
        return unitDao.count(pageQuery.getParams());
    }

    /******* getter and setter ***/

    public UnitDao getUnitDao() {
        return unitDao;
    }

    public void setUnitDao(UnitDao unitDao) {
        this.unitDao = unitDao;
    }
}
