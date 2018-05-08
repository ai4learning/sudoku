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
//import com.goldfish.dao.UnitWordsStudyDao;
//import com.goldfish.domain.UnitWordsStudy;
//import java.util.Map;
//
//
//
///**
// * @author hellosscat
//* @since 2018-5-2
// * UnitWordsStudy Dao实现类
// */
//@Repository("unitWordsStudyDao")
//public class UnitWordsStudyDaoImpl  extends SqlMapClientTemplate implements UnitWordsStudyDao {
//
//	    public static final String ADD = "UnitWordsStudy.add";
//	    public static final String UPDATE = "UnitWordsStudy.update";
//	    public static final String DELETE = "UnitWordsStudy.delete";
//
//	    public static final String GET_ALL = "UnitWordsStudy.getAll";
//	    public static final String GET_BY_EXAMPLE = "UnitWordsStudy.getByExample";
//
//	    public static final String GET_BY_ID = "UnitWordsStudy.getById";
//	    public static final String GET_BY_PAGE = "UnitWordsStudy.getByPage";
//	    public static final String COUNT = "UnitWordsStudy.count";
//
//
//	/**
//	 * 新增
//	 */
//
//	public UnitWordsStudy addUnitWordsStudy(UnitWordsStudy unitWordsStudy) {
//    		this.insert(ADD, unitWordsStudy);
//    		return unitWordsStudy;
//    }
//
//   	/**
//	 * 更新
//	 */
//    public void updateUnitWordsStudy(UnitWordsStudy unitWordsStudy) {
//    	this.update(UPDATE, unitWordsStudy);
//    }
//
//
//    /**
//	 * 删除
//	 */
//    public void deleteUnitWordsStudy(Long id) {
//    	this.update(DELETE, id);
//    }
//
//    public UnitWordsStudy getUnitWordsStudyById(Long id) {
//    	return (UnitWordsStudy) this.queryForObject(GET_BY_ID, id);
//    }
//
//
//
//
//    public List<UnitWordsStudy> getAll() {
//    	return this.queryForList(GET_ALL);
//    }
//
//    public List<UnitWordsStudy> getListByExample(UnitWordsStudy unitWordsStudy) {
//    	return this.queryForList(GET_BY_EXAMPLE, unitWordsStudy);
//    }
//
//    public UnitWordsStudy getUnique(UnitWordsStudy unitWordsStudy) {
//		List<UnitWordsStudy> list = this.getListByExample(unitWordsStudy);
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
//    public List<UnitWordsStudy> getUnitWordsStudyByPage(Map<String,Object> params) {
//    	return this.queryForList(GET_BY_PAGE, params);
//    }
//
//    public int count(Map<String,Object> params) {
//    	return (Integer)this.queryForObject(COUNT, params);
//    }
//
//}
