package com.yjw.condition;

/**
 * 帐号查询条件
 * @author eason
 *
 * 2016年5月24日下午3:30:59
 */
public class AccountCondition {
	private String accountCondition;  // 帐号
	private String cnameCondition;  // 昵称
	
	public String getAccountCondition() {
		return accountCondition;
	}
	public void setAccountCondition(String accountCondition) {
		this.accountCondition = accountCondition;
	}
	public String getCnameCondition() {
		return cnameCondition;
	}
	public void setCnameCondition(String cnameCondition) {
		this.cnameCondition = cnameCondition;
	}
}
