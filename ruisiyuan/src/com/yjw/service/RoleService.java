package com.yjw.service;

import java.util.List;

import com.yjw.condition.RoleCondition;
import com.yjw.pojo.Role;

/**
 * 角色服务
 * @author 姚嘉伟
 *
 * 2016年4月11日下午5:31:58
 */
public interface RoleService {
	/**
	 * 删除角色
	 * @param roleId
	 */
	public void del(String[] roleIds);
	/**
	 * 编辑角色
	 * @param role
	 * @param menuIds
	 */
	public void edit(Role role, String[] menuIds);
	/**
	 * 新增角色
	 * @param role
	 * @param menus
	 */
	public void add(Role role, String[] menus);
	/**
	 * 分页查询的记录
	 * @param condition
	 * @param start
	 * @param rows
	 * @return
	 */
	public List<Role> getItems(RoleCondition condition, int start, int rows);
	/**
	 * 分页查询的条件筛选总数
	 * @param condition
	 * @return
	 */
	public int getTotal(RoleCondition condition);
	/**
	 * 获取所有的角色
	 * @return
	 */
	public List<Role> getRoles();
	/**
	 * 获取帐号对应的角色
	 * @param acc
	 * @return
	 */
	public List<Role> getRolesByAcc(String account);
}
