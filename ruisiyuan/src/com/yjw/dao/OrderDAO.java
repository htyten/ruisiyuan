package com.yjw.dao;

import java.util.List;
import java.util.Map;

import com.yjw.condition.OrderCondition;
import com.yjw.pojo.Order;
import com.yjw.pojo.OrderLog;

/**
 * 订单DAO
 * @author eason
 *
 * 2016年6月6日下午5:02:12
 */
public interface OrderDAO {
	/**
	 * 添加日志
	 * @param log
	 */
	public void addLog(OrderLog log);
	/**
	 * 根据ID查询订单
	 * @param id
	 * @return
	 */
	public Order findById(String id);
	/**
	 * 变更订单状态
	 * @param order
	 */
	public void editState(Order order);
	/**
	 * 编辑备注信息
	 * @param order
	 */
	public void editInfo(Order order);
	/**
	 * 分页查询的记录
	 * @param params
	 * @return
	 */
	public List<Order> getItems(Map<String, Object> params);
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
