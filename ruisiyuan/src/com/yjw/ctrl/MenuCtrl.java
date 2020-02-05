package com.yjw.ctrl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yjw.condition.MenuCondition;
import com.yjw.pojo.Account;
import com.yjw.pojo.Menu;
import com.yjw.service.MenuService;

@Controller
@RequestMapping("menu")
public class MenuCtrl extends BaseCtrl<Menu> {
	
	@Autowired
	private MenuService menuService;
	
	@RequestMapping("menu_del")
	@ResponseBody
	public Map<String, Object> del(String[] menuIds) {
		Map<String, Object> result = new HashMap<String, Object>();
		menuService.del(menuIds);
		result.put("code", 1);
		result.put("message", "删除成功！");
		return result;
	}
	
	@RequestMapping("menu_edit")
	@ResponseBody
	public Map<String, Object> edit(Menu menu) {
		Map<String, Object> result = new HashMap<String, Object>();
		menuService.edit(menu);
		result.put("code", 1);
		result.put("message", "编辑成功！");
		return result;
	}
	
	@RequestMapping("menu_add")
	@ResponseBody
	public Map<String, Object> add(Menu menu, ModelMap model) {
		Map<String, Object> result = new HashMap<String, Object>();
		// 获取登录用户
		Account acc = (Account) model.get("loginAccount");
		menu.setAccount(acc.getAccount());
		menuService.add(menu);
		result.put("code", 1);
		result.put("message", "添加成功！");
		return result;
	}
	
	@RequestMapping("menu_table")
	@ResponseBody
	public Map<String, Object> table(int page, int rows, MenuCondition condition) {
		log.info("easyui，分页查询.....");
		Map<String, Object> result = new HashMap<String, Object>();
		
		// 计算从第几条记录开始查询
		int start = (page - 1) * rows;
		
		// 设置返回对象 (一定要返回 key： total、rows)
		result.put("total", menuService.getTotal(condition));  // 记录总数
		result.put("rows", menuService.getItems(condition, start, rows));  // 当前页的所有记录数
		return result;
	}
	
	@RequestMapping("menu_list")
	public String list(ModelMap model) {
		model.put("allMenus", menuService.getAllLeftMenus());
		return "menu/menu_list";
	}
	
	@RequestMapping("menu_getMenusByRoleId")
	@ResponseBody
	public Map<String, Object> getAllMenus(int roleId) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("menus", menuService.getMenusByRoleId(roleId));
		return result;
	}
	
	@RequestMapping("menu_getMenusByMlevel")
	@ResponseBody
	public Map<String, Object> getMenusByMlevel(int mlevel) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("menus", menuService.getMenusByMlevel(mlevel));
		return result;
	}
	
	@RequestMapping("menu_getAllMenus")
	@ResponseBody
	public Map<String, Object> getAllMenus() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("menus", menuService.getAllMenus());
		return result;
	}
	
	@RequestMapping("loadMenu")
	@ResponseBody
	public Map<String, Object> loadMenu(ModelMap model) {
		Account acc = (Account) model.get("loginAccount");
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("menus", menuService.loadMenuByAcc(acc));
		return result;
	}
}
