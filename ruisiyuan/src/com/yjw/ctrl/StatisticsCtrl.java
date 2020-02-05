package com.yjw.ctrl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yjw.pojo.Order;
import com.yjw.service.StatisticsOrderService;

/**
 * 统计分析
 * @author eason
 *
 * 2016年6月7日下午5:07:12
 */
@Controller
@RequestMapping("statistics")
public class StatisticsCtrl extends BaseCtrl<Order> {
	
	@Autowired
	private StatisticsOrderService statisticsOrderService;
	
	/**
	 * 订单统计分析
	 * @param type	month: 按月统计   day: 按日统计  year: 按年统计
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@RequestMapping("statistics_order_data")
	@ResponseBody
	public Map<String, Object> orderData(String type, String startDate, String endDate) {
		Map<String, Object> result = new HashMap<String, Object>();
		// 默认按月统计订单
		result.put("datas", statisticsOrderService.getStatisticsByMonth(startDate, endDate));
		return result;
	}
	
	@RequestMapping("statistics_order")
	public String order() {
		return "statistics/statistics_order";
	}
	
}
