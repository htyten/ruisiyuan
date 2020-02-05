package com.yjw.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjw.condition.OrderCondition;
import com.yjw.dao.OrderDAO;
import com.yjw.pojo.Order;
import com.yjw.pojo.OrderLog;
import com.yjw.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDAO orderDAO;

	@Override
	public List<Order> getItems(OrderCondition condition, int start, int rows) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idCondition", condition.getIdCondition());
		params.put("brandIdCondition", condition.getBrandIdCondition());
		params.put("modelIdCondition", condition.getModelIdCondition());
		params.put("createTimeStartCondition", condition.getCreateTimeStartCondition());
		params.put("createTimeEndCondition", condition.getCreateTimeEndCondition());
		params.put("stateCondition", condition.getStateCondition());
		params.put("userCondition", condition.getUserCondition());
		params.put("phoneCondition", condition.getPhoneCondition());
		
		
		params.put("start", start);
		params.put("rows", rows);
		return orderDAO.getItems(params);
	}

	@Override
	public int getTotal(OrderCondition condition) {
		return orderDAO.getTotal(condition);
	}

	@Override
	public void add(Order order) {
		orderDAO.add(order);
	}

	@Override
	public void editInfo(Order order, String account) {
		// 记录日志信息
		Order findOrder = orderDAO.findById(order.getId());
		OrderLog log = new OrderLog();
		log.setOrderId(order.getId());
		log.setCreateTime(findOrder.getCreateTime());
		log.setForm(findOrder.getState());
		log.setToState(findOrder.getState());
		log.setInfo(order.getInfo());
		log.setUpdator(account);
		orderDAO.addLog(log);
		// 变更订单信息
		orderDAO.editInfo(order);
	}

	@Override
	public void editState(Order order, String account) {
		// 记录日志信息
		Order findOrder = orderDAO.findById(order.getId());
		OrderLog log = new OrderLog();
		log.setOrderId(order.getId());
		log.setCreateTime(findOrder.getCreateTime());
		log.setForm(findOrder.getState());
		log.setToState(order.getState());
		log.setInfo(order.getInfo());
		log.setUpdator(account);
		orderDAO.addLog(log);
		// 变更订单信息
		orderDAO.editState(order);
	}

}
