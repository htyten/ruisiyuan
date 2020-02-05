package com.yjw.dao;

import java.util.List;
import java.util.Map;

import com.yjw.pojo.StatisticsOrder;

public interface StatisticsOrderDAO {
	/**
	 * 订单统计分析
	 * @param params
	 * @return
	 */
	public List<StatisticsOrder> getStatisticsByMonth(Map<String, Object> params);
}
