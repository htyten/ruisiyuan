package com.yjw.util;

import java.util.Calendar;

/**
 * 订单工具类
 * @author eason
 *
 * 2016年6月6日下午5:31:06
 */
public class OrderTools {

	/**
	 * 生成订单流水号
	 * @return
	 */
	public static String getOrderNum(int i) {
		Calendar calendar = Calendar.getInstance();
		return "3C-" + (calendar.getTime().getTime() + i);
	}
}
