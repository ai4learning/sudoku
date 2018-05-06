package com.goldfish.service.impl;



import javax.annotation.Resource;
import java.util.*;

import com.goldfish.constant.State;
import com.goldfish.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.LoginRecord;
import com.goldfish.manager.LoginRecordManager;
import com.goldfish.service.LoginRecordService;
import org.springframework.util.CollectionUtils;


/**
 * @author hellosscat
 * @since 2018-5-2
 *<p>  LoginRecordservice实现</p>
 *
 */
@Service("loginRecordService")
public class LoginRecordServiceImpl implements LoginRecordService {

	private static final Logger logger = LoggerFactory.getLogger(LoginRecordServiceImpl.class);
	
	@Resource(name="loginRecordManager")
	private LoginRecordManager loginRecordManager;



	public CommonResult<LoginRecord> addLoginRecord(LoginRecord loginRecord) {
		CommonResult<LoginRecord> result = new CommonResult<LoginRecord>();
		try {
			
				loginRecord.setCreated(new Date());
			 
			result.addDefaultModel(loginRecordManager.addLoginRecord(loginRecord));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("添加 LoginRecord失败", e);
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
			logger.error("更新 LoginRecord失败", e);
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
			logger.error("删除 LoginRecord失败", e);
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
			logger.error("根据主键获取 LoginRecord失败", e);
			result.setSuccess(false);
		}
		return result;
	}

	@Override
	public LoginRecord getLoginRecordByToken(String userName, String token) throws Exception {
		try {
			// 只查询一条记录,查询条件为userName,token,state
			PageQuery pageQuery = new PageQuery(1,1);
			pageQuery.setParam("userName", userName);
			pageQuery.setParam("studyToken", token);
			pageQuery.setParam("state", State.VALID.getState());
			List<LoginRecord> list = loginRecordManager.getLoginRecordByPage(pageQuery);

			if (CollectionUtils.isEmpty(list)) {
				logger.info("no login record in system");
				return null;
			}
			return list.get(0);
		} catch (Exception e) {
			logger.error("根据userName和token获取 LoginRecord失败", e);
			throw e;
		}
	}
	

        
	
	public CommonResult<List<LoginRecord>> getAll() {
		CommonResult<List<LoginRecord>> result = new CommonResult<List<LoginRecord>>();
		try {
			List<LoginRecord> list = loginRecordManager.getAll();
			result.addDefaultModel("list", list);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("取得所有 LoginRecord失败", e);
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
			logger.error("取得 LoginRecord失败", e);
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
			logger.error("根据example获取唯一 LoginRecord失败", e);
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
			logger.error("分页获取 LoginRecord失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	public int count(PageQuery pageQuery) {
		return loginRecordManager.count(pageQuery);
	}

	@Override
	public LoginRecord refreshLoginRecord(User user) {

		// 1.将原记录职为无效；
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userName", user.getUserId());
		params.put("oldState", State.VALID.getState());
		params.put("newState", State.IN_VALID.getState());
		loginRecordManager.changeLoginRecordState(params);

		// 2.插入新记录
		LoginRecord newRecord = new LoginRecord();
		String uuid = UUID.randomUUID().toString();
		newRecord.setStudyToken(uuid);
		newRecord.setUserId(Integer.valueOf(String.valueOf(user.getId())));
		newRecord.setUserCode(user.getUserCode());
		newRecord.setUserName(user.getUserId());
		newRecord.setWordTrainingId("wti_" + uuid);
		newRecord.setWordTraningCode("wtc_" + uuid);
		newRecord.setState(State.VALID.getState());
		Date curr = new Date();
		newRecord.setCreated(curr);
		newRecord.setModified(curr);
		LoginRecord  loginRecord= loginRecordManager.addLoginRecord(newRecord);
		return loginRecord;
	}


	/******* getter and setter ***/
	public LoginRecordManager getLoginRecordManager() {
		return loginRecordManager;
	}

	public void setLoginRecordManager(LoginRecordManager loginRecordManager) {
		this.loginRecordManager = loginRecordManager;
	}

}
