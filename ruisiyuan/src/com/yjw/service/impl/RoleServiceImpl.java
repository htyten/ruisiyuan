package com.yjw.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjw.condition.RoleCondition;
import com.yjw.dao.MenuDAO;
import com.yjw.dao.RoleDAO;
import com.yjw.interceptor.AuthInterceptor;
import com.yjw.pojo.Role;
import com.yjw.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDAO roleDAO;
	@Autowired
	private MenuDAO menuDAO;

	@Override
	public List<Role> getRolesByAcc(String account) {
		return roleDAO.getRolesByAccount(account);
	}

	@Override
	public List<Role> getRoles() {
		return roleDAO.getRoles();
	}

	@Override
	public List<Role> getItems(RoleCondition condition, int start, int rows) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("start", start);
		params.put("rows", rows);
		params.put("nameCondition", condition.getNameCondition());
		return roleDAO.getItems(params);
	}

	@Override
	public int getTotal(RoleCondition condition) {
		return roleDAO.getTotal(condition);
	}

	@Override
	public void add(Role role, String[] menus) {
		// 新增角色基本信息
		roleDAO.add(role);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleId", role.getId());
		params.put("menus", menus);
		// 新增角色菜单关联信息
		roleDAO.addRoleMenus(params);
	}

	@Override
	public void edit(Role role, String[] menuIds) {
		// 编辑角色基本信息
		roleDAO.edit(role);
		// 删除角色菜单关联关系
		roleDAO.delRoleMenus(role.getId());
		// 重新建立角色菜单关联关系
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleId", role.getId());
		params.put("menus", menuIds);
		// 新增角色菜单关联信息
		roleDAO.addRoleMenus(params);
		// 刷新权限缓存，增加权限管理效率
		AuthInterceptor.getAuthCache().put(role.getId(), menuDAO.getMenusByRoleId(role.getId()));
	}

	@Override
	public void del(String[] roleIds) {
		// 删除角色菜单关联关系
		roleDAO.delRolesMenus(roleIds);
		// 删除角色基本信息
		roleDAO.del(roleIds);
	}

}
