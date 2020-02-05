<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
<base href="<%=basePath%>">

<title>订单统计分析</title>
<%@ include file="/head.html" %>
</head>
<script type="text/javascript" src="statistics/js/statisticsOrder.js"></script>
<body>
	<div align="center">
		<!-- 查询导航栏 -->
		<div>
			<span>帐号:</span><input id="startDate" class="easyui-textbox" />
			<span>昵称:</span><input id="endDate" class="easyui-textbox" />
			<a class="easyui-linkbutton" iconCls="icon-search" plain="true"
				id="doSearch">查询</a>
		</div>
	</div>
	<!-- 为 ECharts 准备一个具备大小（宽高）的Dom -->
    <div id="main" style="width: 100%;height:400px;"></div>
    <div align="center">
		<a ref="62" href="javascript:void(0)" id="line">
			<span class="icon icon-large-smartart">&nbsp;</span>
			<span class="nav">线形图</span>
		</a>
		<a ref="62" href="javascript:void(0)" id="bar">
			<span class="icon icon-large-chart">&nbsp;</span>
			<span class="nav">柱状图</span>
		</a>
	</div>
</body>
</html>
