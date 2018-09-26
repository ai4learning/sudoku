package com.goldfish.manager.impl;

import com.goldfish.dao.cache.redis.RedisUtils;
import com.goldfish.domain.SelfWords;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

import com.goldfish.common.PageQuery;
import com.goldfish.domain.SelfWordsStudy;
import com.goldfish.dao.SelfWordsStudyDao;
import com.goldfish.manager.SelfWordsStudyManager;

/**
 * @author hellosscat
 * @since 2018-5-8
 * 学生自身单词学习Manager实现类
 */
@Component("selfWordsStudyManager")
public class SelfWordsStudyManagerImpl implements SelfWordsStudyManager {

    @Resource(name = "selfWordsStudyDao")
    private SelfWordsStudyDao selfWordsStudyDao;

    @Resource(name = "redisUtils")
    private RedisUtils redisUtils;

    @Override
    public SelfWordsStudy addSelfWordsStudy(SelfWordsStudy selfWordsStudy) {
        redisUtils.setObject(selfWordsStudy.getClass().getSimpleName() + ":" + selfWordsStudy.getId(), selfWordsStudy);
        int i = selfWordsStudyDao.addSelfWordsStudy(selfWordsStudy);
        return selfWordsStudy;
    }

    @Override
    public void updateSelfWordsStudy(SelfWordsStudy selfWordsStudy) {
        redisUtils.setObject(selfWordsStudy.getClass().getSimpleName() + ":" + selfWordsStudy.getId(), selfWordsStudy);
        selfWordsStudyDao.updateSelfWordsStudy(selfWordsStudy);
    }


    @Override
    public void deleteSelfWordsStudy(Long id) {
        redisUtils.deleteByKey(SelfWordsStudy.class.getSimpleName() + ":" + id);
        selfWordsStudyDao.deleteSelfWordsStudy(id);
    }


    @Override
    public SelfWordsStudy getSelfWordsStudyById(Long id) {
        SelfWordsStudy selfWordsStudy = redisUtils.getObject(SelfWordsStudy.class.getSimpleName() + ":" + id, SelfWordsStudy.class);
        if (selfWordsStudy == null) {
            return selfWordsStudyDao.getSelfWordsStudyById(id);
        }
        return selfWordsStudy;
    }


    @Override
    public SelfWordsStudy getUnique(SelfWordsStudy selfWordsStudy) {
        return selfWordsStudyDao.getUnique(selfWordsStudy);
    }


    @Override
    public List<SelfWordsStudy> getListByExample(SelfWordsStudy selfWordsStudy) {
        return selfWordsStudyDao.getListByExample(selfWordsStudy);
    }


    @Override
    public List<SelfWordsStudy> getSelfWordsStudyByPage(PageQuery pageQuery) {
        return selfWordsStudyDao.getSelfWordsStudyByPage(pageQuery.getParams());
    }

    @Override
    public int count(PageQuery pageQuery) {
        return selfWordsStudyDao.count(pageQuery.getParams());
    }

    /******* getter and setter ***/

    public SelfWordsStudyDao getSelfWordsStudyDao() {
        return selfWordsStudyDao;
    }

    public void setSelfWordsStudyDao(SelfWordsStudyDao selfWordsStudyDao) {
        this.selfWordsStudyDao = selfWordsStudyDao;
    }
}
