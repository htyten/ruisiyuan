package com.yjw.ctrl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yjw.condition.OrderCondition;
import com.yjw.pojo.Account;
import com.yjw.pojo.Order;
import com.yjw.service.OrderService;

/**
 * 订单管理
 * @author eason
 *
 * 2016年6月6日下午4:21:39
 */
@Controller
@RequestMapping("order")
public class OrderCtrl extends BaseCtrl<Order> {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("order_editState")
	@ResponseBody
	public Map<String, Object> editState(Order order, ModelMap model) {
		log.info("编辑订单状态....." + order.getInfo() + "   " + order.getState());
		Map<String, Object> result = new HashMap<String, Object>();
		
		// 获得当前登录用户
		Account acc = (Account) model.get("loginAccount");
		// 编辑备注
		orderService.editState(order, acc.getAccount());
		
		result.put("code", 1);
		result.put("message", "变更订单状态成功！");
		return result;
	}
	
	@RequestMapping("order_editInfo")
	@ResponseBody
	public Map<String, Object> editInfo(Order order, ModelMap model) {
		log.info("编辑备注....." + order.getInfo());
		Map<String, Object> result = new HashMap<String, Object>();
		
		// 获得当前登录用户
		Account acc = (Account) model.get("loginAccount");
		// 编辑备注
		orderService.editInfo(order, acc.getAccount());
		
		result.put("code", 1);
		result.put("message", "备注成功！");
		return result;
	}
	
	@RequestMapping("order_table")
	@ResponseBody
	public Map<String, Object> table(int page, int rows,OrderCondition condition) {
		log.info("easyui，分页查询....." + condition.getStateCondition());
		Map<String, Object> result = new HashMap<String, Object>();
		
		// 计算从第几条记录开始查询
		int start = (page - 1) * rows;
		
		// 设置返回对象 (一定要返回 key： total、rows)
		result.put("total", orderService.getTotal(condition));  // 记录总数
		result.put("rows", orderService.getItems(condition, start, rows));  // 当前页的所有记录数
		return result;
	}

	@RequestMapping("order_list")
	public String list(int stateCondition, ModelMap model) {	
		model.put("stateCondition", stateCondition);
		log.info("进入order 管理页......." + stateCondition);
		return "order/order_list";
	}
}
