package com.goldfish.manager.impl;

import com.goldfish.dao.cache.redis.RedisUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

import com.goldfish.common.PageQuery;
import com.goldfish.domain.Allexam;
import com.goldfish.dao.AllexamDao;
import com.goldfish.manager.AllexamManager;

/**
 * @author hellosscat
 * @since 2018-5-8
 * AllexamManager实现类
 */
@Component("allexamManager")
public class AllexamManagerImpl implements AllexamManager {

    @Resource(name = "allexamDao")
    private AllexamDao allexamDao;

    @Resource(name = "redisUtils")
    private RedisUtils redisUtils;


    @Override
    public Allexam addAllexam(Allexam allexam) {
        redisUtils.setObject(allexam.getClass().getSimpleName() + ":" + allexam.getId(), allexam);
        int i = allexamDao.addAllexam(allexam);
        return allexam;
    }

    @Override
    public void updateAllexam(Allexam allexam) {
        redisUtils.setObject(allexam.getClass().getSimpleName() + ":" + allexam.getId(), allexam);
        allexamDao.updateAllexam(allexam);
    }


    @Override
    public void deleteAllexam(Integer id) {
        redisUtils.deleteByKey(Allexam.class.getSimpleName() + ":" + id);
        allexamDao.deleteAllexam(id);
    }


    @Override
    public Allexam getAllexamById(Integer id) {
        Allexam allexam = redisUtils.getObject(Allexam.class.getSimpleName() + ":" + id, Allexam.class);
        if (allexam == null) {
            return allexamDao.getAllexamById(id);
        }
        return allexam;
    }


    @Override
    public Allexam getUnique(Allexam allexam) {
        return allexamDao.getUnique(allexam);
    }


    @Override
    public List<Allexam> getListByExample(Allexam allexam) {
        return allexamDao.getListByExample(allexam);
    }


    @Override
    public List<Allexam> getAllexamByPage(PageQuery pageQuery) {
        return allexamDao.getAllexamByPage(pageQuery.getParams());
    }

    @Override
    public int count(PageQuery pageQuery) {
        return allexamDao.count(pageQuery.getParams());
    }

    /******* getter and setter ***/

    public AllexamDao getAllexamDao() {
        return allexamDao;
    }

    public void setAllexamDao(AllexamDao allexamDao) {
        this.allexamDao = allexamDao;
    }
}
