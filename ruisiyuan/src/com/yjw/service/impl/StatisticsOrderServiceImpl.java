package com.yjw.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjw.dao.StatisticsOrderDAO;
import com.yjw.pojo.StatisticsOrder;
import com.yjw.service.StatisticsOrderService;

@Service
public class StatisticsOrderServiceImpl implements StatisticsOrderService {
	
	@Autowired
	private StatisticsOrderDAO statisticsOrderDAO;

	@Override
	public List<StatisticsOrder> getStatisticsByMonth(String startDate, String endDate) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		return statisticsOrderDAO.getStatisticsByMonth(params);
	}

}
