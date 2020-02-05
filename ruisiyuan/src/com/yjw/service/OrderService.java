package com.yjw.service;

import java.util.List;

import com.yjw.condition.OrderCondition;
import com.yjw.pojo.Order;

/**
 * 订单服务
 * @author eason
 *
 * 2016年6月6日下午5:01:24
 */
public interface OrderService {
	/**
	 * 变更订单状态
	 * @param order
	 * @param account
	 */
	public void editState(Order order, String account);
	/**
	 * 编辑备注信息
	 * @param account
	 * @param order
	 */
	public void editInfo(Order order, String account);
	/**
	 * 分页查询的记录
	 * @param condition
	 * @param start
	 * @param rows
	 * @return
	 */
	public List<Order> getItems(OrderCondition condition, int start, int rows);
	/**
	 * 分页查询的条件筛选总数
	 * @param condition
	 * @return
	 */
	public int getTotal(OrderCondition condition);
	/**
	 * 下单
	 * @param order
	 */
	public void add(Order order);
}
