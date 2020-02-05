package com.yjw.service;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yjw.JUnitBase;
import com.yjw.pojo.Order;
import com.yjw.util.OrderTools;

/**
 * 帐号管理测试类
 * 
 * @author eason
 *
 *         2016年6月6日下午4:44:03
 */
public class OrderServiceTest extends JUnitBase {
	@Autowired
	private OrderService orderService;

	@Test
	public void test() {
		Order order = null;
		for (int i = 0; i < 42; i++) {
			order = new Order();
			order.setId(OrderTools.getOrderNum(i));
			order.setBrandId(2);
			order.setBrandName("华为");
			order.setModelId(3);
			order.setModelName("华为P9 64G 黑色");
			order.setPrice(6400.00D);
			order.setState(-1);
			order.setCreateTime(this.dateTest(i / 3));
			order.setUpdateTime(this.dateTest(i / 3));
			order.setUser("eason");
			order.setUpdator("eason");
			orderService.add(order);
		}
	}

	/**
	 * 获取几天前
	 * @param day
	 */
	private Date dateTest(int day) {
		Date toDay = new Date(new Date().getTime() - 24 * 60 * 60 * 1000 * day);
//		System.out.println(DateUtil.format(toDay, DateUtil.YYYY_MM_DD_HH_MM_SS_FORMAT));
		return toDay;
	}

}
