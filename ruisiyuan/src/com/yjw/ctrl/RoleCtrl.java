package com.yjw.ctrl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yjw.condition.RoleCondition;
import com.yjw.pojo.Account;
import com.yjw.pojo.Role;
import com.yjw.service.RoleService;

@Controller
@RequestMapping("role")
public class RoleCtrl extends BaseCtrl<Role> {

	@Autowired
	private RoleService roleService;
	
	@RequestMapping("role_del")
	@ResponseBody
	public Map<String, Object> del(String[] roleIds) {
		Map<String, Object> result = new HashMap<String, Object>();
		roleService.del(roleIds);
		result.put("code", 1);
		result.put("message", "删除成功！");
		return result;
	}
	
	@RequestMapping("role_edit")
	@ResponseBody
	public Map<String, Object> edit(Role role, String[] menuIds) {
		Map<String, Object> result = new HashMap<String, Object>();
		roleService.edit(role, menuIds);
		result.put("code", 1);
		result.put("message", "编辑成功！");
		return result;
	}
	
	@RequestMapping("role_add")
	@ResponseBody
	public Map<String, Object> add(Role role, String[] menus, ModelMap model) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		// 获取当前登录用户
		Account acc = (Account) model.get("loginAccount");
		role.setAccount(acc.getAccount());
		roleService.add(role, menus);
		result.put("code", 1);
		result.put("message", "添加成功！");
		return result;
	}
//	
//	@RequestMapping("role_del")
//	@ResponseBody
//	public Map<String, Object> del(String[] roles) {
//		log.info("删除帐号.....");
//		Map<String, Object> result = new HashMap<String, Object>();
//		roleService.delBatch(roles);
//		result.put("code", 1);
//		result.put("message", "删除成功！");
//		return result;
//	}
	
	/**
	 * easy-ui datagrid 请求(page 和 rows 参数由easy-ui 前端框架自动传递)
	 * @param page		当前第几页
	 * @param rows		每页多少条记录
	 * @return
	 */
	@RequestMapping("role_table")
	@ResponseBody
	public Map<String, Object> table(int page, int rows, RoleCondition condition) {
		log.info("easyui，分页查询.....");
		Map<String, Object> result = new HashMap<String, Object>();
		
		// 计算从第几条记录开始查询
		int start = (page - 1) * rows;
		
		// 设置返回对象 (一定要返回 key： total、rows)
		result.put("total", roleService.getTotal(condition));  // 记录总数
		result.put("rows", roleService.getItems(condition, start, rows));  // 当前页的所有记录数
		return result;
	}

	/**
	 * 跳转到 role 管理页
	 * @return
	 */
	@RequestMapping("role_list")
	public String list() {
		log.info("进入role 管理页.......");
		return "role/role_list";
	}
	
	@RequestMapping("role_getRolesByAcc")
	@ResponseBody
	public Map<String, Object> getRolesByAcc(String account) {
		log.info("通过帐号获取角色....");
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("roles", roleService.getRolesByAcc(account));
		return result;
	}
	
	/**
	 * 异步获取所有的角色
	 * @return
	 */
	@RequestMapping("role_getAll")
	@ResponseBody
	public Map<String, Object> getAll() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("roles", roleService.getRoles());
		return result;
	}
}
