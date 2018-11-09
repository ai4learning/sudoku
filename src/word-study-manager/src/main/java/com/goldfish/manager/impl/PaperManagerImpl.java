package com.goldfish.manager.impl;

import com.goldfish.dao.cache.redis.RedisUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

import com.goldfish.common.PageQuery;
import com.goldfish.domain.Paper;
import com.goldfish.dao.PaperDao;
import com.goldfish.manager.PaperManager;

/**
 * @author hellosscat
 * @since 2018-5-8
 * 试卷Manager实现类
 */
@Component("paperManager")
public class PaperManagerImpl implements PaperManager {

    @Resource(name = "paperDao")
    private PaperDao paperDao;

    @Resource(name = "redisUtils")
    private RedisUtils redisUtils;


    @Override
    public Paper addPaper(Paper paper) {
        redisUtils.setObject(paper.getClass().getSimpleName() + ":" + paper.getId(), paper);
        int i = paperDao.addPaper(paper);
        return paper;
    }

    @Override
    public void updatePaper(Paper paper) {
        redisUtils.setObject(paper.getClass().getSimpleName() + ":" + paper.getId(), paper);
        paperDao.updatePaper(paper);
    }


    @Override
    public void deletePaper(Long id) {
        redisUtils.deleteByKey(Paper.class.getSimpleName() + ":" + id);
        paperDao.deletePaper(id);
    }


    @Override
    public Paper getPaperById(Long id) {
        Paper paper = redisUtils.getObject(Paper.class.getSimpleName() + ":" + id, Paper.class);
        if (paper == null) {
            return paperDao.getPaperById(id);
        }
        return paper;
    }


    @Override
    public Paper getUnique(Paper paper) {
        return paperDao.getUnique(paper);
    }


    @Override
    public List<Paper> getListByExample(Paper paper) {
        return paperDao.getListByExample(paper);
    }


    @Override
    public List<Paper> getPaperByPage(PageQuery pageQuery) {
        return paperDao.getPaperByPage(pageQuery.getParams());
    }

    @Override
    public int count(PageQuery pageQuery) {
        return paperDao.count(pageQuery.getParams());
    }

    /******* getter and setter ***/

    public PaperDao getPaperDao() {
        return paperDao;
    }

    public void setPaperDao(PaperDao paperDao) {
        this.paperDao = paperDao;
    }
}
