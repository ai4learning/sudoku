package com.goldfish.service.impl;



import javax.annotation.Resource;
import java.util.List;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.ActivateCode;
import com.goldfish.manager.ActivateCodeManager;
import com.goldfish.service.ActivateCodeService;


/**
 * @author hellosscat
 * @since 2018-5-8
 *<p>  激活码service实现</p>
 *
 */
@Service("activateCodeService")
public class ActivateCodeServiceImpl implements ActivateCodeService {

	private static final Logger logger = Logger.getLogger(ActivateCodeServiceImpl.class);
	
	@Resource(name="activateCodeManager")
	private ActivateCodeManager activateCodeManager;
    
    @Override
    public CommonResult<ActivateCode> addActivateCode(ActivateCode activateCode) {
		CommonResult<ActivateCode> result = new CommonResult<ActivateCode>();
		try{
			
			activateCode.setCreated(new Date());
			
			
			activateCode.setModified(new Date());
			
			result.addDefaultModel(activateCodeManager.addActivateCode(activateCode));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("添加 激活码失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	@Override
    public CommonResult<ActivateCode> updateActivateCode(ActivateCode activateCode) {
		CommonResult<ActivateCode> result = new CommonResult<ActivateCode>();
		try {
			
			activateCode.setModified(new Date());
					
			activateCodeManager.updateActivateCode(activateCode);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("更新 激活码失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
   

	@Override
    public CommonResult<ActivateCode> deleteActivateCode(Long id) {
		CommonResult<ActivateCode> result = new CommonResult<ActivateCode>();
		try {
			activateCodeManager.deleteActivateCode(id);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("删除 激活码失败", e);
			result.setSuccess(false);
		}
		return result;
    }


	@Override
    public CommonResult<ActivateCode> getActivateCodeById(Long id) {
		CommonResult<ActivateCode> result = new CommonResult<ActivateCode>();
		try {
			result.addDefaultModel("activateCode", activateCodeManager.getActivateCodeById(id));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据主键获取 激活码失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	

        
	


	@Override
    public CommonResult<ActivateCode> getUnique(ActivateCode activateCode) {
		CommonResult<ActivateCode> result = new CommonResult<ActivateCode>();
		try {
			result.addDefaultModel(activateCodeManager.getUnique(activateCode));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据example获取唯一 激活码失败", e);
			result.setSuccess(false);
		}
		return result;
	}


	@Override
    public CommonResult<List<ActivateCode>> getListByExample(ActivateCode activateCode) {
		CommonResult<List<ActivateCode>> result = new CommonResult<List<ActivateCode>>();
		try {
			List<ActivateCode> list = activateCodeManager.getListByExample(activateCode);
			result.addDefaultModel("list", list);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("取得 激活码失败", e);
			result.setSuccess(false);
		}
		return result;
	}

	
	@Override
    public CommonResult<List<ActivateCode>> getActivateCodeByPage(PageQuery pageQuery) {
		CommonResult<List<ActivateCode>> result = new CommonResult<List<ActivateCode>>();
		try {
			int totalCount = this.count(pageQuery);
			if (totalCount > 0) {
				pageQuery.setTotalCount(totalCount);
				List<ActivateCode> list = activateCodeManager.getActivateCodeByPage(pageQuery);
				result.addDefaultModel("list", list);
				result.addModel("pageQuery", pageQuery);
			}
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("分页获取 激活码失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	@Override
    public int count(PageQuery pageQuery) {
		return activateCodeManager.count(pageQuery);
	}

	@Override
	public CommonResult<ActivateCode> generateActivateCode() {
		CommonResult<ActivateCode> result = new CommonResult<ActivateCode>();
		try {
			ActivateCode activateCode = new ActivateCode();
			activateCode.setActivateCode(UUID.randomUUID().toString());
			result.addDefaultModel("activateCode", activateCode);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("生成激活码异常", e);
			result.setSuccess(false);
		}
		return result;
	}


	/******* getter and setter ***/
	public ActivateCodeManager getActivateCodeManager() {
		return activateCodeManager;
	}

	public void setActivateCodeManager(ActivateCodeManager activateCodeManager) {
		this.activateCodeManager = activateCodeManager;
	}

}
