package com.goldfish.manager.impl;

import com.goldfish.dao.cache.redis.RedisUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

import com.goldfish.common.PageQuery;
import com.goldfish.domain.Word;
import com.goldfish.dao.WordDao;
import com.goldfish.manager.WordManager;

/**
 * @author hellosscat
 * @since 2018-5-8
 * 单词库Manager实现类
 */
@Component("wordManager")
public class WordManagerImpl implements WordManager {

    @Resource(name = "wordDao")
    private WordDao wordDao;

    @Resource(name = "redisUtils")
    private RedisUtils redisUtils;

    @Override
    public Word addWord(Word word) {
        int i = wordDao.addWord(word);
        redisUtils.setObject(Word.class.getSimpleName() + ":" + word.getId(), word);
        return word;
    }

    @Override
    public void updateWord(Word word) {
        redisUtils.setObject(Word.class.getSimpleName() + ":" + word.getId(), word);
        wordDao.updateWord(word);
    }


    @Override
    public void deleteWord(Integer id) {
        redisUtils.deleteByKey(Word.class.getSimpleName() + ":" + id);
        wordDao.deleteWord(id);
    }


    @Override
    public Word getWordById(Integer id) {
        Word word = redisUtils.getObject(Word.class.getSimpleName() + ":" + id, Word.class);
        if (word == null) {
            return wordDao.getWordById(id);
        }
        return word;
    }


    @Override
    public Word getUnique(Word word) {
        return wordDao.getUnique(word);
    }


    @Override
    public List<Word> getListByExample(Word word) {
        return wordDao.getListByExample(word);
    }


    @Override
    public List<Word> getWordByPage(PageQuery pageQuery) {
        return wordDao.getWordByPage(pageQuery.getParams());
    }

    @Override
    public int count(PageQuery pageQuery) {
        return wordDao.count(pageQuery.getParams());
    }

    /******* getter and setter ***/

    public WordDao getWordDao() {
        return wordDao;
    }

    public void setWordDao(WordDao wordDao) {
        this.wordDao = wordDao;
    }
}
