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
//import com.goldfish.dao.WordStudyStatisticDao;
//import com.goldfish.domain.WordStudyStatistic;
//import java.util.Map;
//
//
//
///**
// * @author hellosscat
//* @since 2018-5-2
// * WordStudyStatistic Dao实现类
// */
//@Repository("wordStudyStatisticDao")
//public class WordStudyStatisticDaoImpl  extends SqlMapClientTemplate implements WordStudyStatisticDao {
//
//	    public static final String ADD = "WordStudyStatistic.add";
//	    public static final String UPDATE = "WordStudyStatistic.update";
//	    public static final String DELETE = "WordStudyStatistic.delete";
//
//	    public static final String GET_ALL = "WordStudyStatistic.getAll";
//	    public static final String GET_BY_EXAMPLE = "WordStudyStatistic.getByExample";
//
//	    public static final String GET_BY_ID = "WordStudyStatistic.getById";
//	    public static final String GET_BY_PAGE = "WordStudyStatistic.getByPage";
//	    public static final String COUNT = "WordStudyStatistic.count";
//
//
//	/**
//	 * 新增
//	 */
//
//	public WordStudyStatistic addWordStudyStatistic(WordStudyStatistic wordStudyStatistic) {
//    		this.insert(ADD, wordStudyStatistic);
//    		return wordStudyStatistic;
//    }
//
//   	/**
//	 * 更新
//	 */
//    public void updateWordStudyStatistic(WordStudyStatistic wordStudyStatistic) {
//    	this.update(UPDATE, wordStudyStatistic);
//    }
//
//
//    /**
//	 * 删除
//	 */
//    public void deleteWordStudyStatistic(Long id) {
//    	this.update(DELETE, id);
//    }
//
//    public WordStudyStatistic getWordStudyStatisticById(Long id) {
//    	return (WordStudyStatistic) this.queryForObject(GET_BY_ID, id);
//    }
//
//
//
//
//    public List<WordStudyStatistic> getAll() {
//    	return this.queryForList(GET_ALL);
//    }
//
//    public List<WordStudyStatistic> getListByExample(WordStudyStatistic wordStudyStatistic) {
//    	return this.queryForList(GET_BY_EXAMPLE, wordStudyStatistic);
//    }
//
//    public WordStudyStatistic getUnique(WordStudyStatistic wordStudyStatistic) {
//		List<WordStudyStatistic> list = this.getListByExample(wordStudyStatistic);
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
//    public List<WordStudyStatistic> getWordStudyStatisticByPage(Map<String,Object> params) {
//    	return this.queryForList(GET_BY_PAGE, params);
//    }
//
//    public int count(Map<String,Object> params) {
//    	return (Integer)this.queryForObject(COUNT, params);
//    }
//
//}
