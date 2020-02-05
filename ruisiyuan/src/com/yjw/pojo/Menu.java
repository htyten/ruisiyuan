package com.yjw.pojo;

import java.util.Date;
import java.util.List;

/**
 * 菜单pojo
 * @author 姚嘉伟
 *
 * 2016年4月11日下午5:28:14
 */
public class Menu {
	private int id;
	private String name;
	private int mlevel;
	private int orderNum;
	private Menu parent;
	private int parentId;
	private List<Menu> childs;
	private String authPath;
	private String icon;
	private String account;
	private Date createTime;
	private int type;  // 菜单类型  0: 左边侧边栏菜单   1: 权限
	
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
	public int getMlevel() {
		return mlevel;
	}
	public void setMlevel(int mlevel) {
		this.mlevel = mlevel;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public List<Menu> getChilds() {
		return childs;
	}
	public void setChilds(List<Menu> childs) {
		this.childs = childs;
	}
	public String getAuthPath() {
		return authPath;
	}
	public void setAuthPath(String authPath) {
		this.authPath = authPath;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Menu getParent() {
		return parent;
	}
	public void setParent(Menu parent) {
		this.parent = parent;
	}
}
