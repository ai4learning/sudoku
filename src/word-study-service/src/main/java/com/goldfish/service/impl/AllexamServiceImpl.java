package com.goldfish.service.impl;



import javax.annotation.Resource;
import java.util.List;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.Allexam;
import com.goldfish.manager.AllexamManager;
import com.goldfish.service.AllexamService;


/**
 * @author hellosscat
 * @since 2018-5-8
 *<p>  Allexamservice实现</p>
 *
 */
@Service("allexamService")
public class AllexamServiceImpl implements AllexamService {

	private static final Logger logger = Logger.getLogger(AllexamServiceImpl.class);
	
	@Resource(name="allexamManager")
	private AllexamManager allexamManager;
    
    public CommonResult<Allexam> addAllexam(Allexam allexam) {
		CommonResult<Allexam> result = new CommonResult<Allexam>();
		try{
			
			
			result.addDefaultModel(allexamManager.addAllexam(allexam));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("添加 Allexam失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	public CommonResult<Allexam> updateAllexam(Allexam allexam) {
		CommonResult<Allexam> result = new CommonResult<Allexam>();
		try {
			
			allexamManager.updateAllexam(allexam);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("更新 Allexam失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
   

	public CommonResult<Allexam> deleteAllexam(Integer id) {
		CommonResult<Allexam> result = new CommonResult<Allexam>();
		try {
			allexamManager.deleteAllexam(id);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("删除 Allexam失败", e);
			result.setSuccess(false);
		}
		return result;
    }


    	public CommonResult<Allexam> getAllexamById(Integer id) {
		CommonResult<Allexam> result = new CommonResult<Allexam>();
		try {
			result.addDefaultModel("allexam", allexamManager.getAllexamById(id));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据主键获取 Allexam失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	

        
	


	public CommonResult<Allexam> getUnique(Allexam allexam) {
		CommonResult<Allexam> result = new CommonResult<Allexam>();
		try {
			result.addDefaultModel(allexamManager.getUnique(allexam));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据example获取唯一 Allexam失败", e);
			result.setSuccess(false);
		}
		return result;
	}


	public CommonResult<List<Allexam>> getListByExample(Allexam allexam) {
		CommonResult<List<Allexam>> result = new CommonResult<List<Allexam>>();
		try {
			List<Allexam> list = allexamManager.getListByExample(allexam);
			result.addDefaultModel("list", list);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("取得 Allexam失败", e);
			result.setSuccess(false);
		}
		return result;
	}

	
	public CommonResult<List<Allexam>> getAllexamByPage(PageQuery pageQuery) {
		CommonResult<List<Allexam>> result = new CommonResult<List<Allexam>>();
		try {
			int totalCount = this.count(pageQuery);
			if (totalCount > 0) {
				pageQuery.setTotalCount(totalCount);
				List<Allexam> list = allexamManager.getAllexamByPage(pageQuery);
				result.addDefaultModel("list", list);
				result.addModel("pageQuery", pageQuery);
			}
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("分页获取 Allexam失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	public int count(PageQuery pageQuery) {
		return allexamManager.count(pageQuery);
	}


	/******* getter and setter ***/
	public AllexamManager getAllexamManager() {
		return allexamManager;
	}

	public void setAllexamManager(AllexamManager allexamManager) {
		this.allexamManager = allexamManager;
	}

}
