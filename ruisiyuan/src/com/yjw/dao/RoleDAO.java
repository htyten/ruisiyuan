package com.yjw.dao;

import java.util.List;
import java.util.Map;

import com.yjw.condition.RoleCondition;
import com.yjw.pojo.Role;

/**
 * 角色DAO 
 * @author 姚嘉伟
 *
 * 2016年4月11日下午5:33:18
 */
public interface RoleDAO {
	/**
	 * 删除角色基本信息
	 * @param roleId
	 */
	public void del(String[] roleIds);
	/**
	 * 删除角色菜单关联关系
	 * @param roleId
	 */
	public void delRolesMenus(String[] roleIds);
	/**
	 * 删除角色菜单关联关系
	 * @param roleId
	 */
	public void delRoleMenus(int roleId);
	/**
	 * 编辑角色基本信息
	 * @param role
	 */
	public void edit(Role role);
	/**
	 * 新增角色菜单关联关系
	 * @param params
	 */
	public void addRoleMenus(Map<String, Object> params);
	/**
	 * 新增角色基本信息
	 * @param role
	 */
	public void add(Role role);
	/**
	 * 分页查询的记录
	 * @param params
	 * @return
	 */
	public List<Role> getItems(Map<String, Object> params);
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
	 * 获取帐号对应的角色信息
	 * @param account
	 * @return
	 */
	public List<Role> getRolesByAccount(String account);
}
