package com.wt.cms.entity;

import java.util.List;

/**
 * @description:
 * @author wt
 * @date 2017-12-2
 */
public class PageBean<E> {
	
	private List<E> rows; 			//数据

	private Integer total;			//数据总数
	
	private Integer pageSize = 10;	//每页大小
	
	private Integer pageNumber = 1;	//当前页

	private Integer maxPageNum;		//最大页数
	
	public List<E> getRows() {
		return rows;
	}

	public void setRows(List<E> rows) {
		this.rows = rows;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
		if(total > 0){
			if( total%pageSize==0){
				if(pageNumber > total/pageSize){
					pageNumber = total/pageSize;
				}
				maxPageNum = total/pageSize;
			}else {
				if(pageNumber > total/pageSize + 1){
					pageNumber = total/pageSize + 1;
				}
				maxPageNum = total/pageSize + 1;
			}
		}
		
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getMaxPageNum() {
		return maxPageNum;
	}

	public void setMaxPageNum(Integer maxPageNum) {
		this.maxPageNum = maxPageNum;
	}
}
