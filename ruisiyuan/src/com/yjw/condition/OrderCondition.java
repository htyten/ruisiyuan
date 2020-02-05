package com.yjw.condition;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 订单条件查询
 * @author eason
 *
 * 2016年6月6日下午4:58:59
 */
public class OrderCondition {
	private String idCondition;  // 订单编号
	private String userCondition;  // 联系人
	private String phoneCondition; // 联系电话
	private int brandIdCondition;  // 品牌
	private int modelIdCondition;  // 机型
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTimeStartCondition;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTimeEndCondition;
	private int stateCondition = -100;
	
	public String getIdCondition() {
		return idCondition;
	}
	public void setIdCondition(String idCondition) {
		this.idCondition = idCondition;
	}
	public int getBrandIdCondition() {
		return brandIdCondition;
	}
	public void setBrandIdCondition(int brandIdCondition) {
		this.brandIdCondition = brandIdCondition;
	}
	public int getModelIdCondition() {
		return modelIdCondition;
	}
	public void setModelIdCondition(int modelIdCondition) {
		this.modelIdCondition = modelIdCondition;
	}
	public Date getCreateTimeStartCondition() {
		return createTimeStartCondition;
	}
	public void setCreateTimeStartCondition(Date createTimeStartCondition) {
		this.createTimeStartCondition = createTimeStartCondition;
	}
	public Date getCreateTimeEndCondition() {
		return createTimeEndCondition;
	}
	public void setCreateTimeEndCondition(Date createTimeEndCondition) {
		this.createTimeEndCondition = createTimeEndCondition;
	}
	public int getStateCondition() {
		return stateCondition;
	}
	public void setStateCondition(int stateCondition) {
		this.stateCondition = stateCondition;
	}
	public String getUserCondition() {
		return userCondition;
	}
	public void setUserCondition(String userCondition) {
		this.userCondition = userCondition;
	}
	public String getPhoneCondition() {
		return phoneCondition;
	}
	public void setPhoneCondition(String phoneCondition) {
		this.phoneCondition = phoneCondition;
	}
}
