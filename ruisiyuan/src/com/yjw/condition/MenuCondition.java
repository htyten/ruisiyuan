package com.yjw.condition;

/**
 * 菜单分页查询条件
 * @author eason
 *
 * 2016年6月2日下午2:35:08
 */
public class MenuCondition {
	private String nameCondition;
	private int parentIdCondition = -100;  // -100 全部菜单
	
	public String getNameCondition() {
		return nameCondition;
	}
	public void setNameCondition(String nameCondition) {
		this.nameCondition = nameCondition;
	}
	public int getParentIdCondition() {
		return parentIdCondition;
	}
	public void setParentIdCondition(int parentIdCondition) {
		this.parentIdCondition = parentIdCondition;
	}
}
