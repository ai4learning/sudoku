package com.goldfish.manager.impl;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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


    @Override
    public SelfWords addSelfWords(SelfWords selfWords) {
        int i = selfWordsDao.addSelfWords(selfWords);
        return selfWords;
    }

    @Override
    public void updateSelfWords(SelfWords selfWords) {
        selfWordsDao.updateSelfWords(selfWords);
    }

    @Override
    public void updateSelfWordsByVocCode(SelfWords selfWords) {
        selfWordsDao.updateSelfWordsByVocCode(selfWords);
    }


    @Override
    public void deleteSelfWords(Long id) {
        selfWordsDao.deleteSelfWords(id);
    }

    @Override
    public void deleteByVocCode(String vocCode, Integer type, Integer userId) {
        SelfWords query = new SelfWords();
        query.setVocCode(vocCode);
        query.setStudentId(Long.valueOf(String.valueOf(userId)));
        query.setType(type);
        selfWordsDao.delete(query);
    }


    @Override
    public SelfWords getSelfWordsById(Long id) {
        return selfWordsDao.getSelfWordsById(id);
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
