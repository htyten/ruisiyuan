package com.yjw.pojo;

/**
 * 订单统计分析 pojo
 * @author eason
 *
 * 2016年6月7日下午5:20:20
 */
public class StatisticsOrder {
//	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private String date;  // 日期
	private int total;  // 数据量
	private int off;  // 取消订单量
	private int success;  // 交易成功量
	private int fail;  // 退款退货量
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getOff() {
		return off;
	}
	public void setOff(int off) {
		this.off = off;
	}
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	public int getFail() {
		return fail;
	}
	public void setFail(int fail) {
		this.fail = fail;
	}
}
