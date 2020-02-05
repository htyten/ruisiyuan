package com.yjw.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjw.condition.AccountCondition;
import com.yjw.dao.AccountDAO;
import com.yjw.pojo.Account;
import com.yjw.service.AccountService;
import com.yjw.util.DateUtil;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDAO accountDAO;

	@Override
	public Account LoginValidate(Account acc) {
		return accountDAO.loginValidate(acc);
	}

	@Override
	public List<Account> getItems(AccountCondition condition, int start, int rows) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("start", start);
		params.put("rows", rows);
		params.put("accountCondition", condition.getAccountCondition());
		params.put("cnameCondition", condition.getCnameCondition());
		return accountDAO.getItems(params);
	}

	@Override
	public int getTotal(AccountCondition condition) {
		return accountDAO.getTotal(condition);
	}

	@Override
	public Account validate(String account) {
		return accountDAO.validate(account);
	}

	@Override
	public void add(Account acc, String[] roleIds) {
		// 新增帐号基本信息数据
		accountDAO.add(acc);
		// 新增帐号角色中间表数据
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("account", acc.getAccount());
		params.put("roleIds", roleIds);
		accountDAO.addAccRole(params);
	}

	@Override
	public void del(String account) {
		// 删除帐号基本信息
		accountDAO.del(account);
		// 删除帐号角色中间表信息
		accountDAO.delRoleByAcc(account);
	}

	@Override
	public Account findAccountInfo(String account) {
		return accountDAO.findAccountInfo(account);
	}

	@Override
	public void edit(Account acc, String[] roleIds) {
		// 更新帐号基本信息
		accountDAO.edit(acc);
		// 删除帐号角色中间表的数据
		accountDAO.delRoleByAcc(acc.getAccount());
		// 重新添加帐号角色中间表的数据
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("account", acc.getAccount());
		params.put("roleIds", roleIds);
		accountDAO.addAccRole(params);
	}

	@Override
	public void delBatch(String[] accounts) {
		// 删除帐号基本信息
		accountDAO.delBatch(accounts);
		// 删除帐号角色关联关系
		accountDAO.delBatchRoleByAccs(accounts);
	}

	@Override
	public HSSFWorkbook export(AccountCondition condition) {
		// 生成的 Excel 表头
		String[] heads = {"帐号", "昵称", "头像", "状态", "创建时间"};
		
		// 条件查询所有的帐号信息
		List<Account> accs = accountDAO.getAccounts(condition);
		// 将查询出来的帐号信息，封装成报表

		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		// 生成一个excel 的 sheet 页
		HSSFSheet sheet = workbook.createSheet("帐号报表");
		
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth(15);
		
		// 创建一个新的行，用于存放表头
		HSSFRow row = sheet.createRow(0);
		
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.VIOLET.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		
		// 把字体应用到当前的样式
		style.setFont(font);
		
		// 产生表格标题行
		for(int i = 0; i < heads.length; i++) {
			HSSFCell cell = row.createCell(i);  // 创建单元格
			cell.setCellStyle(style);  // 设置单元格样式
			cell.setCellValue(heads[i]);
		}
		
		// 遍历集合数据，产生数据行
		Account acc = null;
		for(int i = 1; i <= accs.size(); i++) {
			acc = accs.get(i - 1);
			row = sheet.createRow(i);
			// 帐号
			row.createCell(0).setCellValue(acc.getAccount());
			// 昵称
			row.createCell(1).setCellValue(acc.getCname());
			// 头像
			row.createCell(2).setCellValue(acc.getHeadpic());
			// 状态
			if(acc.getStatus() == 1) {
				row.createCell(3).setCellValue("正常");
			} else {
				// 获取单元格
				HSSFCell statusCell = row.createCell(3);
				// 冻结样式设置
				HSSFCellStyle statusStyle = workbook.createCellStyle();
				// 设置字体
				HSSFFont statusFont = workbook.createFont();
				statusFont.setColor(HSSFColor.RED.index);
				statusStyle.setFont(statusFont);
				statusCell.setCellStyle(statusStyle);
				statusCell.setCellValue("冻结");
			}
			// 创建时间
			row.createCell(4).setCellValue(DateUtil.format(acc.getCreateTime(), DateUtil.YYYY_MM_DD_HH_MM_SS_FORMAT));
		}
		return workbook;
	}

}
