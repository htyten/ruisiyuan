package com.yjw.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yjw.pojo.Account;
import com.yjw.service.AccountService;

@Controller
public class LoginCtrl extends BaseCtrl<Account> {
	
	@Autowired
	private AccountService accountService;

	/**
	 * 用户登录
	 * @param account
	 * @return
	 */
	@RequestMapping("loginOn.do")
	public String loginOn(Account account, ModelMap modelMap) {
		Account acc = accountService.LoginValidate(account);
		if(acc != null) {
			modelMap.put("loginAccount", acc);
			return "index";
		}
		return "login";
	}
}
