package com.goldfish.service.impl;



import javax.annotation.Resource;
import java.util.*;

import com.goldfish.constant.State;
import com.goldfish.dao.cache.local.LoginRecordContext;
import com.goldfish.domain.User;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.LoginRecord;
import com.goldfish.manager.LoginRecordManager;
import com.goldfish.service.LoginRecordService;
import org.springframework.util.CollectionUtils;


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
	@Resource
	private LoginRecordContext loginRecordContext;
    
    @Override
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
	
	@Override
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
	
   

	@Override
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


    	@Override
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
	

        
	


	@Override
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


	@Override
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

	
	@Override
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
	
	@Override
    public int count(PageQuery pageQuery) {
		return loginRecordManager.count(pageQuery);
	}

	@Override
	public LoginRecord getLoginRecordByToken(String userName, String token) throws Exception {
		try {
			// 只查询一条记录,查询条件为userName,token,state
			PageQuery pageQuery = new PageQuery(0,1);
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

	@Override
	public LoginRecord getLoginRecordByTraining(String trainingId, String trainingCode) throws Exception {
		try {
			// 只查询一条记录,查询条件为userName,token,state
			PageQuery pageQuery = new PageQuery(0,1);
			pageQuery.setParam("wordTrainingId", trainingId);
			pageQuery.setParam("wordTrainingCode", trainingCode);
//			pageQuery.setParam("state", State.VALID.getState());
			List<LoginRecord> list = loginRecordManager.getLoginRecordByPage(pageQuery);

			if (CollectionUtils.isEmpty(list)) {
				logger.info("no login record in system");
				return null;
			}
			return list.get(0);
		} catch (Exception e) {
			logger.error("根据traningId和traningCode获取 LoginRecord失败", e);
			throw e;
		}
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
		newRecord.setWordTrainingCode("wtc_" + uuid);
		newRecord.setState(State.VALID.getState());
		Date curr = new Date();
		newRecord.setCreated(curr);
		newRecord.setModified(curr);
		LoginRecord  loginRecord= loginRecordManager.addLoginRecord(newRecord);
		// 写入缓存
		loginRecordContext.addByTraning(loginRecord.getWordTrainingId(), loginRecord.getWordTrainingCode(), loginRecord);
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
