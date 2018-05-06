package com.goldfish.common;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 分页查询POJO
 * @Author wangyong
 * @Time 2015年1月21日 下午4:15:20
 */
public class PageQuery {
    /** 当前页数 **/
	private int pageIndex = 1;
	/** 分页起点 **/
	private int pageSize = 20;
	/** 总页数 **/
	private int totalPage;

    /** 起始行(表中无此字段) **/
	private long startIndex;
	/** 总数 **/
	private long totalCount;

	/**
	 * 查询条件
	 */
	private Map<String,Object> params = new HashMap<String, Object>();

	public PageQuery(HttpServletRequest request, int pageSize) {
		try {
			this.pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		} catch (NumberFormatException e) {
//			LogTypeEnum.DEFAULT.error(e, "");
		}
		this.pageSize = pageSize;
		this.startIndex = (pageIndex - 1) * pageSize;
		this.params.putAll(request.getParameterMap());
		this.params.put("startIndex",startIndex);
		this.params.put("pageIndex",pageIndex);
		this.params.put("pageSize",pageSize);
	}

	public PageQuery(int pageIndex, int pageSize) {

		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.startIndex = (pageIndex - 1) * pageSize;
		this.params.put("startIndex",startIndex);
		this.params.put("pageIndex",pageIndex);
		this.params.put("pageSize",pageSize);
	}

	/**
	 *
	 * @return
	 */
	public Map<String, Object> getParams() {
		return params;
	}

	/**
	 * 进行一些操作
	 * @param totalCount
	 */
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
		if (this.totalCount > 0) {
			this.totalPage = Integer.parseInt(String.valueOf((this.totalCount - 1) / this.pageSize + 1));
		} else {
			this.totalPage = 0;
		}
		this.params.put("totalCount",this.totalCount);
		this.params.put("totalPage",this.totalPage);
	}

	public void setParam(String key ,Object value) {
		this.params.put(key,value);
	}

	public Long getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Long startIndex) {
		this.startIndex = startIndex;
	}



	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public void setStartIndex(long startIndex) {
		this.startIndex = startIndex;
	}
	public Long getTotalCount() {
		return totalCount;
	}
}
