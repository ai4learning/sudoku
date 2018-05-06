/**
 * Copyright(c) 2013-  www.jd.com
 *
 */

package com.goldfish.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;
import com.goldfish.dao.PaperDao;
import com.goldfish.domain.Paper;
import java.util.Map;



/**
 * @author hellosscat
* @since 2018-5-2
 * Paper Dao实现类
 */
@Repository("paperDao")
public class PaperDaoImpl  extends SqlMapClientTemplate implements PaperDao {

	    public static final String ADD = "Paper.add";
	    public static final String UPDATE = "Paper.update";
	    public static final String DELETE = "Paper.delete";
	 	
	    public static final String GET_ALL = "Paper.getAll";
	    public static final String GET_BY_EXAMPLE = "Paper.getByExample";
	   
	    public static final String GET_BY_ID = "Paper.getById";
	    public static final String GET_BY_PAGE = "Paper.getByPage";
	    public static final String COUNT = "Paper.count";
	
	
	/**
	 * 新增
	 */
	
	public Paper addPaper(Paper paper) {
    		this.insert(ADD, paper);
    		return paper;
    }
    
   	/**
	 * 更新
	 */
    public void updatePaper(Paper paper) {
    	this.update(UPDATE, paper);
    }
    
    	
    /**
	 * 删除
	 */
    public void deletePaper(Long id) {
    	this.update(DELETE, id);
    }

    public Paper getPaperById(Long id) {
    	return (Paper) this.queryForObject(GET_BY_ID, id);
    }
    
    

	
    public List<Paper> getAll() {
    	return this.queryForList(GET_ALL);
    }

    public List<Paper> getListByExample(Paper paper) {
    	return this.queryForList(GET_BY_EXAMPLE, paper);
    }

    public Paper getUnique(Paper paper) {
		List<Paper> list = this.getListByExample(paper);
    	return list.size() > 0 ? list.get(0) : null;
    }
      	

      
    /**
	 * 获得分页数据
	 *@param params 查询参数类
	 *@return 
	 */
    @SuppressWarnings("unchecked")
    public List<Paper> getPaperByPage(Map<String,Object> params) {
    	return this.queryForList(GET_BY_PAGE, params);
    }
    	
    public int count(Map<String,Object> params) {
    	return (Integer)this.queryForObject(COUNT, params);
    }

}
