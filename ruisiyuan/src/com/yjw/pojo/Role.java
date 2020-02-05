package com.yjw.pojo;

import java.util.Date;

/**
 * 角色pojo
 * @author 姚嘉伟
 *
 * 2016年4月11日下午5:30:00
 */
public class Role {
	private int id;
	private String name;
	private Date createTime;
	private String account;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
}
