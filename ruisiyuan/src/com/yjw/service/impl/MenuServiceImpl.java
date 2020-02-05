package com.yjw.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjw.condition.MenuCondition;
import com.yjw.dao.MenuDAO;
import com.yjw.dao.RoleDAO;
import com.yjw.pojo.Account;
import com.yjw.pojo.Menu;
import com.yjw.pojo.Role;
import com.yjw.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private RoleDAO roleDAO;
	@Autowired
	private MenuDAO menuDAO;
	
	@Override
	public List<Menu> loadMenuByAcc(Account acc) {
		// 获取用户角色
		List<Role> roles = roleDAO.getRolesByAccount(acc.getAccount());
		// 获取用户菜单
		return menuDAO.getMenusByRoles(roles);
	}

	@Override
	public List<Menu> getAllMenus() {
		return menuDAO.getAllMenus();
	}

	@Override
	public List<Menu> getMenusByRoleId(int roleId) {
		return menuDAO.getMenusByRoleId(roleId);
	}

	@Override
	public List<Account> getItems(MenuCondition condition, int start, int rows) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("nameCondition", condition.getNameCondition());
		params.put("parentIdCondition", condition.getParentIdCondition());
		params.put("start", start);
		params.put("rows", rows);
		return menuDAO.getItems(params);
	}

	@Override
	public int getTotal(MenuCondition condition) {
		return menuDAO.getTotal(condition);
	}

	@Override
	public List<Menu> getMenusByMlevel(int mlevel) {
		return menuDAO.getMenusByMlevel(mlevel);
	}

	@Override
	public void add(Menu menu) {
		menuDAO.add(menu);
	}

	@Override
	public void edit(Menu menu) {
		menuDAO.edit(menu);
	}

	@Override
	public void del(String[] menuIds) {
		// 删除菜单角色关联关系
		menuDAO.delRoleMenu(menuIds);
		// 删除菜单基本信息
		menuDAO.del(menuIds);
	}

	@Override
	public List<Menu> getAllLeftMenus() {
		return menuDAO.getAllLeftMenus();
	}

}
