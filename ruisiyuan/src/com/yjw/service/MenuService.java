package com.yjw.service;

import java.util.List;

import com.yjw.condition.MenuCondition;
import com.yjw.pojo.Account;
import com.yjw.pojo.Menu;

/**
 * 帐号服务
 * @author 姚嘉伟
 *
 * 2016年4月11日下午5:26:22
 */
public interface MenuService {
	/**
	 * 批量删除
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
	 * @param condition
	 * @param start
	 * @param rows
	 * @return
	 */
	public List<Account> getItems(MenuCondition condition, int start, int rows);
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
	 * 登录菜单加载
	 * @param acc
	 * @return
	 */
	public List<Menu> loadMenuByAcc(Account acc);
}
