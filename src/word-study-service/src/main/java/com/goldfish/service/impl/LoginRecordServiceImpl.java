package com.goldfish.service.impl;



import javax.annotation.Resource;
import java.util.List;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.LoginRecord;
import com.goldfish.manager.LoginRecordManager;
import com.goldfish.service.LoginRecordService;


/**
 * @author hellosscat
 * @since 2018-5-8
 *<p>  登录service实现</p>
 *
 */
@Service("loginRecordService")
public class LoginRecordServiceImpl implements LoginRecordService {

	private static final Logger logger = Logger.getLogger(LoginRecordServiceImpl.class);
	
	@Resource(name="loginRecordManager")
	private LoginRecordManager loginRecordManager;
    
    public CommonResult<LoginRecord> addLoginRecord(LoginRecord loginRecord) {
		CommonResult<LoginRecord> result = new CommonResult<LoginRecord>();
		try{
			
			loginRecord.setCreated(new Date());
			
			
			loginRecord.setModified(new Date());
			
			result.addDefaultModel(loginRecordManager.addLoginRecord(loginRecord));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("添加 登录失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	public CommonResult<LoginRecord> updateLoginRecord(LoginRecord loginRecord) {
		CommonResult<LoginRecord> result = new CommonResult<LoginRecord>();
		try {
			
					loginRecord.setModified(new Date());
					
			loginRecordManager.updateLoginRecord(loginRecord);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("更新 登录失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
   

	public CommonResult<LoginRecord> deleteLoginRecord(Long id) {
		CommonResult<LoginRecord> result = new CommonResult<LoginRecord>();
		try {
			loginRecordManager.deleteLoginRecord(id);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("删除 登录失败", e);
			result.setSuccess(false);
		}
		return result;
    }


    	public CommonResult<LoginRecord> getLoginRecordById(Long id) {
		CommonResult<LoginRecord> result = new CommonResult<LoginRecord>();
		try {
			result.addDefaultModel("loginRecord", loginRecordManager.getLoginRecordById(id));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据主键获取 登录失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	

        
	


	public CommonResult<LoginRecord> getUnique(LoginRecord loginRecord) {
		CommonResult<LoginRecord> result = new CommonResult<LoginRecord>();
		try {
			result.addDefaultModel(loginRecordManager.getUnique(loginRecord));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据example获取唯一 登录失败", e);
			result.setSuccess(false);
		}
		return result;
	}


	public CommonResult<List<LoginRecord>> getListByExample(LoginRecord loginRecord) {
		CommonResult<List<LoginRecord>> result = new CommonResult<List<LoginRecord>>();
		try {
			List<LoginRecord> list = loginRecordManager.getListByExample(loginRecord);
			result.addDefaultModel("list", list);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("取得 登录失败", e);
			result.setSuccess(false);
		}
		return result;
	}

	
	public CommonResult<List<LoginRecord>> getLoginRecordByPage(PageQuery pageQuery) {
		CommonResult<List<LoginRecord>> result = new CommonResult<List<LoginRecord>>();
		try {
			int totalCount = this.count(pageQuery);
			if (totalCount > 0) {
				pageQuery.setTotalCount(totalCount);
				List<LoginRecord> list = loginRecordManager.getLoginRecordByPage(pageQuery);
				result.addDefaultModel("list", list);
				result.addModel("pageQuery", pageQuery);
			}
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("分页获取 登录失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	public int count(PageQuery pageQuery) {
		return loginRecordManager.count(pageQuery);
	}


	/******* getter and setter ***/
	public LoginRecordManager getLoginRecordManager() {
		return loginRecordManager;
	}

	public void setLoginRecordManager(LoginRecordManager loginRecordManager) {
		this.loginRecordManager = loginRecordManager;
	}

}
