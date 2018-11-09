package com.goldfish.manager.impl;

import com.goldfish.dao.cache.redis.RedisUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import com.goldfish.common.PageQuery;
import com.goldfish.domain.SelfWords;
import com.goldfish.dao.SelfWordsDao;
import com.goldfish.manager.SelfWordsManager;

/**
 * @author hellosscat
 * @since 2018-5-8
 * 学生自身单词Manager实现类
 */
@Component("selfWordsManager")
public class SelfWordsManagerImpl implements SelfWordsManager {

    @Resource(name = "selfWordsDao")
    private SelfWordsDao selfWordsDao;

    @Resource(name = "redisUtils")
    private RedisUtils redisUtils;

    @Override
    public SelfWords addSelfWords(SelfWords selfWords) {
        int i = selfWordsDao.addSelfWords(selfWords);
        redisUtils.setObject(selfWords.getClass().getSimpleName() + ":" + selfWords.getId(), selfWords);
        return selfWords;
    }

    @Override
    public void updateSelfWords(SelfWords selfWords) {
        redisUtils.setObject(selfWords.getClass().getSimpleName() + ":" + selfWords.getId(), selfWords);
        selfWordsDao.updateSelfWords(selfWords);
    }

    @Override
    public void updateSelfWordsByVocCode(SelfWords selfWords) {
        List<Integer> listId = selfWordsDao.selectSelfWordsByVocCode(selfWords);
        for (int i : listId) {
            redisUtils.setObject(selfWords.getClass().getSimpleName() + ":" + i, selfWords);
        }
        selfWordsDao.updateSelfWordsByVocCode(selfWords);
    }


    @Override
    public void deleteSelfWords(Long id) {
        redisUtils.deleteByKey(SelfWords.class.getSimpleName() + ":" + id);
        selfWordsDao.deleteSelfWords(id);
    }

    @Override
    public void deleteByVocCode(String vocCode, Integer type, Integer userId) {
        SelfWords query = new SelfWords();
        query.setVocCode(vocCode);
        query.setStudentId(Long.valueOf(String.valueOf(userId)));
        query.setType(type);
        selfWordsDao.delete(query);
        List<Integer> listId = selfWordsDao.select(query);
        for (int i : listId) {
            redisUtils.deleteByKey(SelfWords.class.getSimpleName() + ":" + i);
        }
    }


    @Override
    public SelfWords getSelfWordsById(Long id) {
        SelfWords selfWords = redisUtils.getObject(SelfWords.class.getSimpleName() + ":" + id, SelfWords.class);
        if (selfWords == null) {
            return selfWordsDao.getSelfWordsById(id);
        }
        return selfWords;
    }


    @Override
    public SelfWords getUnique(SelfWords selfWords) {
        return selfWordsDao.getUnique(selfWords);
    }


    @Override
    public List<SelfWords> getListByExample(SelfWords selfWords) {
        return selfWordsDao.getListByExample(selfWords);
    }


    @Override
    public List<SelfWords> getSelfWordsByPage(PageQuery pageQuery) {
        return selfWordsDao.getSelfWordsByPage(pageQuery.getParams());
    }

    @Override
    public int count(PageQuery pageQuery) {
        return selfWordsDao.count(pageQuery.getParams());
    }

    @Override
    public List<SelfWords> inQuerySelfWords(PageQuery pageQuery) {
        return selfWordsDao.inQuerySelfWords(pageQuery.getParams());
    }

    /*******
     * getter and setter
     ***/

    public SelfWordsDao getSelfWordsDao() {
        return selfWordsDao;
    }

    public void setSelfWordsDao(SelfWordsDao selfWordsDao) {
        this.selfWordsDao = selfWordsDao;
    }
}
