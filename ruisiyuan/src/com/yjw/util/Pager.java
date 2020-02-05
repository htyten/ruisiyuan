package com.yjw.util;

import java.util.List;

/**
 * 分页组件
 * @author 姚嘉伟
 *
 * 2016年4月1日上午9:19:35
 */
public class Pager<T> {
	private int total;  // 记录总数
	private int rows = 5;  // 每页多少条记录
	private int pageCount = 1;  // 当前第几页
	private List<T> items;
	
	/**
	 * 获得总页数
	 * @return
	 */
	public int getTotalPager() {
		return total % rows == 0 ? total / rows : total / rows + 1;
	}
	/**
	 * 获得当前页第一条记录是DB中的第几条记录
	 * @return
	 */
	public int getStart() {
		return rows * (pageCount - 1);
	}
	/**
	 * 获得当前页最后一条记录是DB中的第几条记录
	 * @return
	 */
	public int getEnd() {
		return rows * pageCount <= total ? 
				rows * pageCount
				:
				total;
	}
	/**
	 * 首页
	 * @return
	 */
	public int getFirstPage() {
		pageCount = 1;
		return pageCount;
	}
	/**
	 * 尾页
	 * @return
	 */
	public int getEndPage() {
		pageCount = total % rows == 0 ? total / rows : total / rows + 1;
		return pageCount;
	}
	
	/**
	 * 上一页
	 * @return  上一页，页号
	 */
	public int prePage() {
		if(pageCount > 1) {
			return --pageCount;
		} else {
			return 1;
		}
	}
	/**
	 * 下一页
	 * @return
	 */
	public int lastPage() {
		if(rows * pageCount >= total) {
			return pageCount;
		} else {
			return ++pageCount;
		}
	}
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public List<T> getItems() {
		return items;
	}
	public void setItems(List<T> items) {
		this.items = items;
	}
}
