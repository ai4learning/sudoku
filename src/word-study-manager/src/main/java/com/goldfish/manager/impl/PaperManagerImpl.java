package com.goldfish.manager.impl;

import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
import com.goldfish.common.PageQuery;
import com.goldfish.domain.Paper;
import com.goldfish.dao.PaperDao;
import com.goldfish.manager.PaperManager;

/**
 * @author hellosscat
 * @since 2018-5-2
 * PaperManager实现类
 */
 @Component("paperManager")
public class PaperManagerImpl implements PaperManager {

	@Resource(name="paperDao")
	private PaperDao paperDao;


  public Paper addPaper(Paper paper) {
		return paperDao.addPaper(paper);
    }
    
    public void updatePaper(Paper paper) {
		paperDao.updatePaper(paper);
    }
    

    
    public void deletePaper(Long id) {
		paperDao.deletePaper(id);
    }


    public Paper getPaperById(Long id) {
		return paperDao.getPaperById(id);
    }
    
   

   
    
    public List<Paper> getAll() {
    	return paperDao.getAll();
    }
    	
    public List<Paper> getListByExample(Paper paper) {
		return paperDao.getListByExample(paper);
    }

        public Paper getUnique(Paper paper) {
		return paperDao.getUnique(paper);
    }

    
    

    
    public List<Paper> getPaperByPage(PageQuery pageQuery) {
		return paperDao.getPaperByPage( pageQuery.getParams());
    }
    	
    public int count(PageQuery pageQuery) {
		return paperDao.count( pageQuery.getParams());
    }

    /******* getter and setter ***/
    
	public PaperDao getPaperDao() {
		return paperDao;
	}

	public void setPaperDao(PaperDao paperDao) {
		this.paperDao = paperDao;
	}
}
