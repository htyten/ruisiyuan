package com.yjw.pojo;

import java.util.Date;

/**
 * 订单日志pojo
 * @author eason
 *
 * 2016年6月7日下午3:41:15
 */
public class OrderLog {
	private int id;
	private String orderId;
	private int form;
	private int toState;
	private String info;
	private String updator;
	private Date updateTime;
	private Date createTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public int getForm() {
		return form;
	}
	public void setForm(int form) {
		this.form = form;
	}
	public int getToState() {
		return toState;
	}
	public void setToState(int toState) {
		this.toState = toState;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getUpdator() {
		return updator;
	}
	public void setUpdator(String updator) {
		this.updator = updator;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
