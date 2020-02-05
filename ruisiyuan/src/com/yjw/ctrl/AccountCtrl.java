package com.yjw.ctrl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yjw.condition.AccountCondition;
import com.yjw.pojo.Account;
import com.yjw.service.AccountService;

@Controller
@RequestMapping("account")
public class AccountCtrl extends BaseCtrl<Account> {
	
	@Autowired
	private AccountService accountService;
	
	/**
	 * 导出 Excel
	 * 		需要导入jar包：poi-3.6-20091214.jar
	 * @param res
	 * @param condition
	 * @throws IOException
	 */
	@RequestMapping("account_export")
	public void exportExcel(HttpServletResponse res, AccountCondition condition) throws IOException {
		HSSFWorkbook workbook = accountService.export(condition);
			
		// 设置响应类型
		res.setContentType("application/vnd.ms-excel");
		res.setHeader("Content-disposition", "attachment;filename=student.xls");
		// 获取输出流
		OutputStream ouputStream = res.getOutputStream();
		// 将报表对象写入到输出流
		workbook.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}
	@RequestMapping("account_edit")
	@ResponseBody
	public Map<String, Object> edit(Account account, String[] roleIds) {
		Map<String, Object> result = new HashMap<String, Object>();
		accountService.edit(account, roleIds);
		result.put("code", 1);
		result.put("message", "编辑成功！");
		return result;
	}
	@RequestMapping("account_add")
	@ResponseBody
	public Map<String, Object> add(Account account, String[] roleIds) {
		Map<String, Object> result = new HashMap<String, Object>();
		accountService.add(account, roleIds);
		result.put("code", 1);
		result.put("message", "添加成功！");
		return result;
	}
	
	@RequestMapping("account_validate")
	@ResponseBody
	public Map<String, Object> validate(String account) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(accountService.findAccountInfo(account) == null) {
			result.put("code", 1);
			result.put("message", "帐号可以注册！");
		} else {
			result.put("code", -1);
			result.put("message", "帐号已存在，请更换其他帐号！");
		}
		return result;
	}
	
	@RequestMapping("account_del")
	@ResponseBody
	public Map<String, Object> del(String[] accounts) {
		log.info("删除帐号.....");
		Map<String, Object> result = new HashMap<String, Object>();
		accountService.delBatch(accounts);
		result.put("code", 1);
		result.put("message", "删除成功！");
		return result;
	}
	
	/**
	 * easy-ui datagrid 请求(page 和 rows 参数由easy-ui 前端框架自动传递)
	 * @param page		当前第几页
	 * @param rows		每页多少条记录
	 * @return
	 */
	@RequestMapping("account_table")
	@ResponseBody
	public Map<String, Object> table(int page, int rows, AccountCondition condition) {
		log.info("easyui，分页查询.....");
		Map<String, Object> result = new HashMap<String, Object>();
		
		// 计算从第几条记录开始查询
		int start = (page - 1) * rows;

		// 设置返回对象 (一定要返回 key： total、rows)
		 result.put("total", accountService.getTotal(condition));  // 记录总数
		result.put("rows", accountService.getItems(condition, start, rows));  // 当前页的所有记录数
		return result;
	}

	/**
	 * 跳转到 account 管理页
	 * @return
	 */
	@RequestMapping("account_list")
	public String list() {
		log.info("进入account 管理页.......");
		return "account/account_list";
	}
}
