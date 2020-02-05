package com.yjw.dao;

import java.util.List;
import java.util.Map;

import com.yjw.condition.MenuCondition;
import com.yjw.pojo.Account;
import com.yjw.pojo.Menu;
import com.yjw.pojo.Role;

/**
 * 菜单DAO
 * @author 姚嘉伟
 *
 * 2016年4月11日下午5:36:47
 */
public interface MenuDAO {
	/**
	 * 批量删除菜单角色关联关系
	 * @param menuIds
	 */
	public void delRoleMenu(String[] menuIds);
	/**
	 * 批量删除菜单基本信息
	 * @param menuIds
	 */
	public void del(String[] menuIds);
	/**
	 * 编辑帐号
	 * @param menu
	 */
	public void edit(Menu menu);
	/**
	 * 新增菜单
	 * @param menu
	 */
	public void add(Menu menu);
	/**
	 * 分页查询的记录
	 * @param params
	 * @return
	 */
	public List<Account> getItems(Map<String, Object> params);
	/**
	 * 分页查询的条件筛选总数
	 * @param condition
	 * @return
	 */
	public int getTotal(MenuCondition condition);
	/**
	 * 获取
	 * @param roleId
	 * @return
	 */
	public List<Menu> getMenusByRoleId(int roleId);
	/**
	 * 获取对应菜单级别的菜单
	 * @param mlevel
	 * @return
	 */
	public List<Menu> getMenusByMlevel(int mlevel);
	/**
	 * 获取所有的菜单项
	 * @return
	 */
	public List<Menu> getAllMenus();
	/**
	 * 获取所有的侧边栏菜单项
	 * @return
	 */
	public List<Menu> getAllLeftMenus();
	/**
	 * 获取角色对应菜单
	 * @param roles
	 * @return
	 */
	public List<Menu> getMenusByRoles(List<Role> roles);
	/**
	 * 通过菜单ID获取子菜单
	 * @param parentId
	 * @return
	 */
	public List<Menu> getChildsByParentId(int menuId);
	/**
	 * 查找菜单
	 * @param id
	 * @return
	 */
	public Menu findMenu(int id);
}
