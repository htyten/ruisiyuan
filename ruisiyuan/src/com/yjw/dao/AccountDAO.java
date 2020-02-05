package com.yjw.dao;

import java.util.List;
import java.util.Map;

import com.yjw.condition.AccountCondition;
import com.yjw.pojo.Account;

/**
 * 帐号DAO
 * @author 姚嘉伟
 *
 * 2016年4月11日下午6:00:18
 */
public interface AccountDAO {
	/**
	 * 查询符合查询条件的所有帐号信息
	 * @param condition
	 * @return
	 */
	public List<Account> getAccounts(AccountCondition condition);
	/**
	 * 跟新帐号基本信息
	 * @param acc
	 */
	public void edit(Account acc);
	/**
	 * 查询帐号以及帐号关联的角色信息
	 * @param account
	 */
	public Account findAccountInfo(String account);
	/**
	 * 批量删除帐号角色关联关系
	 * @param accounts
	 */
	public void delBatchRoleByAccs(String[] accounts);
	/**
	 * 删除帐号角色中间表数据
	 * @param account
	 */
	public void delRoleByAcc(String account);
	/**
	 * 批量删除
	 * @param accounts
	 */
	public void delBatch(String[] accounts);
	/**
	 * 删除帐号基本数据
	 * @param account
	 */
	public void del(String account);
	/**
	 * 新增帐号角色中间表数据
	 * @param params
	 */
	public void addAccRole(Map<String, Object> params);
	/**
	 * 新增帐号基本信息
	 * @param acc
	 */
	public void add(Account acc);
	/**
	 * 帐号验证
	 * @param account
	 * @return
	 */
	public Account validate(String account);
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
	public int getTotal(AccountCondition condition);

	/**
	 * 登录验证
	 * @param acc
	 * @return
	 */
	public Account loginValidate(Account acc);
}
