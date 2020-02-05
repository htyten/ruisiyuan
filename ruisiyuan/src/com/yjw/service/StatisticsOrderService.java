package com.yjw.service;

import java.util.List;

import com.yjw.pojo.StatisticsOrder;

/**
 * 订单统计分析
 * @author eason
 *
 * 2016年6月7日下午5:21:43
 */
public interface StatisticsOrderService {
	/**
	 * 订单统计分析	按月
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<StatisticsOrder> getStatisticsByMonth(String startDate, String endDate);
}
