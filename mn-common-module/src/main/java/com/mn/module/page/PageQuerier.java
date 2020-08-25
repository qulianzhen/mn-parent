package com.mn.module.page;

import java.util.List;

/**
 * 分页查询器 -- 根据实际情况改造
 */
public class  PageQuerier<T> implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*EasyUI传过来的参数*/
	private Integer page;  //重绘标识
	private Integer rows; //每页多个条
	private List<Order> order;//排序
	private T search;
	public List<Order> getOrder() {
		return order;
	}
	public void setOrder(List<Order> order) {
		this.order = order;
	}
	public T getSearch() {
		return search;
	}
	public void setSearch(T search) {
		this.search = search;
	}


	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}
}