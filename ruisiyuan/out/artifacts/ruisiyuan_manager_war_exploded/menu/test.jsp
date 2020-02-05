<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<base href="<%=basePath%>">

<title>菜单权限管理</title>
<%@ include file="/head.html" %>
</head>
<body>
	<input id="icon" />
	<script type="text/javascript">
		$(function() {
			$('#icon').combobox({
				url: "menu/js/icon.json",
				method: "get",
				valueField:'id',
				textField:'text',
				panelHeight:'auto',
				formatter: function(row) {
					return '<span class="' + row.id + '">&nbsp;</span> ' + row.id;  // 显示在 option 的 formatter, 没有formatter 需要置顶 valueField
				},
				editable: false	// 不可编辑
			});
		});
	</script>
</body>
</html>
