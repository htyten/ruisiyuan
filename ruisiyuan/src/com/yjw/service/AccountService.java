package com.yjw.service;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.yjw.condition.AccountCondition;
import com.yjw.pojo.Account;

/**
 * 帐号Service
 * @author 姚嘉伟
 *
 * 2016年4月11日下午5:59:30
 */
public interface AccountService {
	/**
	 * 导出Excel
	 * @param condition
	 * @return
	 */
	public HSSFWorkbook export(AccountCondition condition);
	/**
	 * 编辑帐号信息
	 * @param acc
	 * @param roleIds
	 */
	public void edit(Account acc, String[] roleIds);
	/**
	 * 查询帐号以及帐号关联的角色信息
	 * @param account
	 */
	public Account findAccountInfo(String account);
	/**
	 * 批量删除
	 * @param accounts
	 */
	public void delBatch(String[] accounts);
	/**
	 * 删除
	 * @param account
	 */
	public void del(String account);
	/**
	 * 新增帐号
	 * @param acc
	 * @param roleIds
	 */
	public void add(Account acc, String[] roleIds);
	/**
	 * 帐号验证
	 * @param account
	 * @return
	 */
	public Account validate(String account);
	/**
	 * 分页查询的记录
	 * @param condition
	 * @param start
	 * @param rows
	 * @return
	 */
	public List<Account> getItems(AccountCondition condition, int start, int rows);
	/**
	 * 分页查询的条件筛选总数
	 * @param condition
	 * @return
	 */
	public int getTotal(AccountCondition condition);
	/**
	 * 登录验证
	 * @param acc
	 * @return
	 */
	public Account LoginValidate(Account acc);
}
