///**
// * Copyright(c) 2013-  www.jd.com
// *
// */
//
//package com.goldfish.dao.impl;
//
//import java.util.List;
//
//import org.springframework.orm.ibatis.SqlMapClientTemplate;
//import org.springframework.stereotype.Repository;
//import com.goldfish.dao.LoginRecordDao;
//import com.goldfish.domain.LoginRecord;
//import java.util.Map;
//
//
//
///**
// * @author hellosscat
//* @since 2018-5-2
// * LoginRecord Dao实现类
// */
//@Repository("loginRecordDao")
//public class LoginRecordDaoImpl  extends SqlMapClientTemplate implements LoginRecordDao {
//
//	    public static final String ADD = "LoginRecord.add";
//	    public static final String UPDATE = "LoginRecord.update";
//	    public static final String UPDATE_STATE = "LoginRecord.changeState";
//	    public static final String DELETE = "LoginRecord.delete";
//
//	    public static final String GET_ALL = "LoginRecord.getAll";
//	    public static final String GET_BY_EXAMPLE = "LoginRecord.getByExample";
//
//	    public static final String GET_BY_ID = "LoginRecord.getById";
//	    public static final String GET_BY_TOKEN = "LoginRecord.getByToken";
//	    public static final String GET_BY_PAGE = "LoginRecord.getByPage";
//	    public static final String COUNT = "LoginRecord.count";
//
//
//	/**
//	 * 新增
//	 */
//
//	public LoginRecord addLoginRecord(LoginRecord loginRecord) {
//    		this.insert(ADD, loginRecord);
//    		return loginRecord;
//    }
//
//   	/**
//	 * 更新
//	 */
//    public void updateLoginRecord(LoginRecord loginRecord) {
//    	this.update(UPDATE, loginRecord);
//    }
//
//
//	/**
//	 * 更新
//	 */
//    public void changeLoginRecordState(Map<String,Object> params) {
//    	this.update(UPDATE_STATE, params);
//    }
//
//
//    /**
//	 * 删除
//	 */
//    public void deleteLoginRecord(Long id) {
//    	this.update(DELETE, id);
//    }
//
//    public LoginRecord getLoginRecordById(Long id) {
//    	return (LoginRecord) this.queryForObject(GET_BY_ID, id);
//    }
//
//
//
//
//    public List<LoginRecord> getAll() {
//    	return this.queryForList(GET_ALL);
//    }
//
//    public List<LoginRecord> getListByExample(LoginRecord loginRecord) {
//    	return this.queryForList(GET_BY_EXAMPLE, loginRecord);
//    }
//
//    public LoginRecord getUnique(LoginRecord loginRecord) {
//		List<LoginRecord> list = this.getListByExample(loginRecord);
//    	return list.size() > 0 ? list.get(0) : null;
//    }
//
//
//
//    /**
//	 * 获得分页数据
//	 *@param params 查询参数类
//	 *@return
//	 */
//    @SuppressWarnings("unchecked")
//    public List<LoginRecord> getLoginRecordByPage(Map<String,Object> params) {
//    	return this.queryForList(GET_BY_PAGE, params);
//    }
//
//    public int count(Map<String,Object> params) {
//    	return (Integer)this.queryForObject(COUNT, params);
//    }
//
//}
