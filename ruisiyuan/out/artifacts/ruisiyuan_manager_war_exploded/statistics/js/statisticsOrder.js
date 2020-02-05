// 得到当前年1月-现在的所有月份数据
var date = new Date();
var year = date.getFullYear();
var month = date.getMonth() + 1;
// 初始化 默认 x 轴区间
var defaultXAxisData = getMonths(year, 1, year, month);

var type = "line";  // 默认统计图为线形图


$(function() {
	// 初始化加载echats
	loadEcharts(defaultXAxisData, type);
	
	// 初始化查询条件
	$("#startDate").datebox({
		required: false,  // 允许为空
		editable: false  // 文本框不允许编辑
	});
	$("#endDate").datebox({
		required: false,  // 允许为空
		editable: false  // 文本框不允许编辑
	});
	
	$("#line").on("click", function() {
		type = "line";
		if(!$("#startDate").datebox("getValue") || !$("#endDate").datebox("getValue")) {
			// 没有查询条件的时候
			loadEcharts(defaultXAxisData, type);
		} else {
			// 有查询条件的时候
			var startDate = $("#startDate").datebox("getValue");
			var endDate = $("#endDate").datebox("getValue");
			var xAxisData = getMonths(startDate.substring(0, 4), startDate.substring(5, 7) * 1, endDate.substring(0, 4), endDate.substring(5, 7) * 1);
			loadEcharts(xAxisData, type, startDate, endDate);
		}
	});
	
	$("#bar").on("click", function() {
		type = "bar";
		if(!$("#startDate").datebox("getValue") || !$("#endDate").datebox("getValue")) {
			// 没有查询条件的时候
			loadEcharts(defaultXAxisData, type);
		} else {
			// 有查询条件的时候
			var startDate = $("#startDate").datebox("getValue");
			var endDate = $("#endDate").datebox("getValue");
			var xAxisData = getMonths(startDate.substring(0, 4), startDate.substring(5, 7) * 1, endDate.substring(0, 4), endDate.substring(5, 7) * 1);
			loadEcharts(xAxisData, type, startDate, endDate);
		}
	});
	
	$("#doSearch").on("click", function() {
		// 计算查询条件的 时间区间
		var startDate = $("#startDate").datebox("getValue");
		var endDate = $("#endDate").datebox("getValue");
		var xAxisData = getMonths(startDate.substring(0, 4), startDate.substring(5, 7) * 1, endDate.substring(0, 4), endDate.substring(5, 7) * 1);
		loadEcharts(xAxisData, type, startDate, endDate);
	});
});

/* *
 * xAxisData:  x 轴时间区间
 * type: 图形   line 先行图   		bar 柱状图
 * 
 * */
function loadEcharts(xAxisData, type, startDate, endDate) {
	// 基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document.getElementById('main'));
	// 加载echats, 默认按月统计
	$.post(
		"statistics/statistics_order_data.do",
		{
			type: "month",
			startDate: !startDate ? "" : startDate,
			endDate: !endDate ? "" : endDate
		},
		function(result) {
			// 初始化统计图数据
			var seriesData = new Array();
			// 下单量
			var seriesTotal = new Object();
			seriesTotal.name = "下单量";
			seriesTotal.type = type;
			seriesTotal.data = new Array();
			// 取消量
			var seriesOff = new Object();
			seriesOff.name = "订单取消量";
			seriesOff.type = type;
			seriesOff.data = new Array();
			// 交易成功量
			var seriesSuccess = new Object();
			seriesSuccess.name = "交易成功量";
			seriesSuccess.type = type;
			seriesSuccess.data = new Array();
			// 退款退货量
			// 交易成功量
			var seriesFail = new Object();
			seriesFail.name = "退款退货量";
			seriesFail.type = type;
			seriesFail.data = new Array();
			// 临时变量
			var serverDate;
			var dataByMonth;
			for(var i = 0; i < xAxisData.length; i++) {
				dataByMonth = false;
				for(var j = 0; j < result.datas.length; j++) {
					serverDate = result.datas[j].date.substring(0, 4) + " 年 " + result.datas[j].date.substring(4, result.datas[j].date.length) + " 月";
					if(serverDate == xAxisData[i]) {
						seriesTotal.data.push(result.datas[j].total);  // 添加该月份的下单量
						seriesOff.data.push(result.datas[j].off);  // 取消量
						seriesSuccess.data.push(result.datas[j].success);  // 成交量
						seriesFail.data.push(result.datas[j].fail);  // 退款退货量
						dataByMonth = true;
					}
				}
				if(!dataByMonth) {
					seriesTotal.data.push(0);  // 添加该月份的下单量
					seriesOff.data.push(0);  // 取消量
					seriesSuccess.data.push(0);  // 成交量
					seriesFail.data.push(0);  // 退款退货量
				}
			}
			
			seriesData.push(seriesTotal);
			seriesData.push(seriesOff);
			seriesData.push(seriesSuccess);
			seriesData.push(seriesFail);
			
			// 指定图表的配置项和数据
			var option = {
				title: {
					text: '订单统计'
				},
				tooltip: {},
				legend: {
					data:[
						{
							name: '下单量',
							icon: 'circle'  // 强制设置图形为圆。
						},
						{
							name: '订单取消量',
							icon: 'circle'  // 强制设置图形为圆。
						},
						{
							name: '交易成功量',
							icon: 'circle'  // 强制设置图形为圆。
						},
						{
							name: '退款退货量',
							icon: 'circle'  // 强制设置图形为圆。
						}
					]
				},
				xAxis: {
					data: xAxisData
				},
				yAxis: {},
				series: seriesData
			};
			myChart.setOption(option);
		}
	);
}

// 获得统计图的x轴区间
function getMonths(startYear, startMonth, endYear, endMonth) {
	var xAxis = new Array();
	if(startYear == endYear) {
		for(var i = startMonth; i <= endMonth; i++) {
			if(i < 10) {
				xAxis.push(startYear + " 年 0" + i + " 月");
			} else {
				xAxis.push(startYear + " 年 " + i + " 月");
			}
		}
	} else {
		for(var i = startYear; i <= endYear; i++) {
			if(i < endYear) {
				for(var j = 1; j <= 12; j++) {
					if(j < 10) {
						xAxis.push(i + " 年 0" + j + " 月");
					} else {
						xAxis.push(i + " 年 " + j + " 月");
					}
				}
			}
		}
	}
	return xAxis;
}