package com.voting.db.param;

import java.util.List;

public class PageResult<T> {

	/**
	 * 起始位置
	 */
	private Integer start;
	/**
	 * 结果数
	 */
	private Integer count;
	/**
	 * 总页数
	 */
	private Integer totalPage;
	/**
	 * 分页大小
	 */
	private Integer pageSize;
	/**
	 * 当前页
	 */
	private Integer currentPage;
	/**
	 * 结果集
	 */
	private List<T> data;
	/**
	 * 是否分页
	 */
	private boolean isCut;

	/**
	 * {@linkplain #count}
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * {@linkplain #currentPage}
	 */
	public Integer getCurrentPage() {
		return currentPage;
	}

	/**
	 * {@linkplain #data}
	 */
	public List<T> getData() {
		return data;
	}

	/**
	 * {@linkplain #isCut}
	 */
	public boolean getIsCut() {
		return isCut;
	}

	/**
	 * {@linkplain #pageSize}
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * {@linkplain #start}
	 */
	public Integer getStart() {
		return start;
	}

	/**
	 * {@linkplain #totalPage}
	 */
	public Integer getTotalPage() {
		return totalPage;
	}

	/**
	 * {@linkplain #count}
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * {@linkplain #currentPage}
	 */
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * {@linkplain #data}
	 */
	public void setData(List<T> data) {
		this.data = data;
	}

	/**
	 * {@linkplain #isCut}
	 */
	public void setIsCut(boolean isCut) {
		this.isCut = isCut;
	}

	/**
	 * {@linkplain #pageSize}
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * {@linkplain #start}
	 */
	public void setStart(Integer start) {
		this.start = start;
	}

	/**
	 * {@linkplain #totalPage}
	 */
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

}
