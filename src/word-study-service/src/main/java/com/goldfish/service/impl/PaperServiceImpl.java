package com.goldfish.service.impl;



import javax.annotation.Resource;
import java.util.List;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.Paper;
import com.goldfish.manager.PaperManager;
import com.goldfish.service.PaperService;


/**
 * @author hellosscat
 * @since 2018-5-2
 *<p>  Paperservice实现</p>
 *
 */
@Service("paperService")
public class PaperServiceImpl implements PaperService {

	private static final Logger logger = LoggerFactory.getLogger(PaperServiceImpl.class);
	
	@Resource(name="paperManager")
	private PaperManager paperManager;
    
    public CommonResult<Paper> addPaper(Paper paper) {
		CommonResult<Paper> result = new CommonResult<Paper>();
		try {
			
				paper.setCreated(new Date());
			 
			result.addDefaultModel(paperManager.addPaper(paper));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("添加 Paper失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	public CommonResult<Paper> updatePaper(Paper paper) {
		CommonResult<Paper> result = new CommonResult<Paper>();
		try {
			
				paper.setModified(new Date());
			 
			paperManager.updatePaper(paper);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("更新 Paper失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
   

	public CommonResult<Paper> deletePaper(Long id) {
		CommonResult<Paper> result = new CommonResult<Paper>();
		try {
			paperManager.deletePaper(id);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("删除 Paper失败", e);
			result.setSuccess(false);
		}
		return result;
    }


    	public CommonResult<Paper> getPaperById(Long id) {
		CommonResult<Paper> result = new CommonResult<Paper>();
		try {
			result.addDefaultModel("paper", paperManager.getPaperById(id));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据主键获取 Paper失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	

        
	
	public CommonResult<List<Paper>> getAll() {
		CommonResult<List<Paper>> result = new CommonResult<List<Paper>>();
		try {
			List<Paper> list = paperManager.getAll();
			result.addDefaultModel("list", list);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("取得所有 Paper失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	public CommonResult<List<Paper>> getListByExample(Paper paper) {
		CommonResult<List<Paper>> result = new CommonResult<List<Paper>>();
		try {
			List<Paper> list = paperManager.getListByExample(paper);
			result.addDefaultModel("list", list);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("取得 Paper失败", e);
			result.setSuccess(false);
		}
		return result;
	}

	public CommonResult<Paper> getUnique(Paper paper) {
		CommonResult<Paper> result = new CommonResult<Paper>();
		try {
			result.addDefaultModel(paperManager.getUnique(paper));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据example获取唯一 Paper失败", e);
			result.setSuccess(false);
		}
		return result;
	}

	



	
	public CommonResult<List<Paper>> getPaperByPage(PageQuery pageQuery) {
		CommonResult<List<Paper>> result = new CommonResult<List<Paper>>();
		try {
			int totalCount = this.count(pageQuery);
			if (totalCount > 0) {
				pageQuery.setTotalCount(totalCount);
				List<Paper> list = paperManager.getPaperByPage(pageQuery);
				result.addDefaultModel("list", list);
				result.addModel("pageQuery", pageQuery);
			}
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("分页获取 Paper失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	public int count(PageQuery pageQuery) {
		return paperManager.count(pageQuery);
	}


	/******* getter and setter ***/
	public PaperManager getPaperManager() {
		return paperManager;
	}

	public void setPaperManager(PaperManager paperManager) {
		this.paperManager = paperManager;
	}

}
