package com.hpe.utils;

import java.util.List;



public class Page<T> {//这里加上<T>
	private int rows;//总条数
	private int totalPage;//总页数
	private int pageNumber;//每页条数
	private int curPage;//当前页
	private List<T> data;//每页的数据
	
	
	
	
	public Page() {
		super();
	}
	public Page(int rows, int totalPage, int pageNumber, int curPage, List<T> data) {
		super();
		this.rows = rows;
		this.totalPage = totalPage;
		this.pageNumber = pageNumber;
		this.curPage = curPage;
		this.data = data;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	
	
	
}
