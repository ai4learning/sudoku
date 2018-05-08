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
//import com.goldfish.dao.ExamDao;
//import com.goldfish.domain.Exam;
//import java.util.Map;
//
//
//
///**
// * @author hellosscat
//* @since 2018-5-2
// * Exam Dao实现类
// */
//@Repository("examDao")
//public class ExamDaoImpl  extends SqlMapClientTemplate implements ExamDao {
//
//	    public static final String ADD = "Exam.add";
//	    public static final String UPDATE = "Exam.update";
//	    public static final String DELETE = "Exam.delete";
//
//	    public static final String GET_ALL = "Exam.getAll";
//	    public static final String GET_BY_EXAMPLE = "Exam.getByExample";
//
//	    public static final String GET_BY_ID = "Exam.getById";
//	    public static final String GET_BY_PAGE = "Exam.getByPage";
//	    public static final String COUNT = "Exam.count";
//
//
//	/**
//	 * 新增
//	 */
//
//	public Exam addExam(Exam exam) {
//    		this.insert(ADD, exam);
//    		return exam;
//    }
//
//   	/**
//	 * 更新
//	 */
//    public void updateExam(Exam exam) {
//    	this.update(UPDATE, exam);
//    }
//
//
//    /**
//	 * 删除
//	 */
//    public void deleteExam(Long id) {
//    	this.update(DELETE, id);
//    }
//
//    public Exam getExamById(Long id) {
//    	return (Exam) this.queryForObject(GET_BY_ID, id);
//    }
//
//
//
//
//    public List<Exam> getAll() {
//    	return this.queryForList(GET_ALL);
//    }
//
//    public List<Exam> getListByExample(Exam exam) {
//    	return this.queryForList(GET_BY_EXAMPLE, exam);
//    }
//
//    public Exam getUnique(Exam exam) {
//		List<Exam> list = this.getListByExample(exam);
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
//    public List<Exam> getExamByPage(Map<String,Object> params) {
//    	return this.queryForList(GET_BY_PAGE, params);
//    }
//
//    public int count(Map<String,Object> params) {
//    	return (Integer)this.queryForObject(COUNT, params);
//    }
//
//}
