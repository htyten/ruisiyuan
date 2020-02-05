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

<title>订单管理</title>
<%@ include file="/head.html" %>
</head>
<!-- 自定义 js，加载 easyui 组件 -->
<script type="text/javascript" src="order/js/order.js"></script>
<body>
	<!-- 
		定义datagrid控件 
			toolbar: 定义工具栏
	-->
	<table id="list_data" toolbar="#toolbar">
		<thead>
			<tr>
				<th field="id" width="10%">订单编号</th>
				<th field="brandName" width="5%">产品类型</th>
				<th field="modelName" width="15%">产品名称</th>
				<th field="price" width="5%">价格</th>
				<!-- formatter 指定列的格式化 -->
				<th field="logistics" width="15%">物流单号</th>
				<th field="user" width="5%">用户帐号</th>
				<th field="userCname" width="5%">联系人</th>
				<th field="phone" width="5%">联系方式</th>
				<th field="updateTime" formatter="formatterdate" width="5%">变更时间</th>
				<th field="createTime" formatter="formatterdate" width="5%">下单时间</th>
				<th field="state" formatter="formatterState" width="5%">状态</th>
				<th field="info" width="10%">备注</th>
				<th field="ctrl" formatter="formatterCtrl" width="5%">操作</th>
			</tr>
		</thead>
	</table>
	
	<!-- 工具栏 -->
	<div id="toolbar">
		<!-- 查询导航栏 -->
		<div>
			<span>订单号:</span><input id="idCondition" class="easyui-textbox" />
			<span>联系人:</span><input id="userCondition" class="easyui-textbox" />
			<span>联系电话:</span><input id="phoneCondition" class="easyui-textbox" />
			<a class="easyui-linkbutton" iconCls="icon-search" plain="true"
				id="doSearch">查询</a>
		</div>
	</div>
	
	<!-- 编辑 -->
	<div id="editDlg" title="编辑帐号" class="easyui-dialog" closed="true" buttons="#dlg-buttons-edit"
		style="width:450px;height:440px;padding:10px 20px">
		<form id="editFm" method="post" data-options="novalidate:true">
			<table>
				<tr>
					<td>订单编号</td>
					<td><span id="id"></span></td>
				</tr>
				<tr>
					<td>品牌</td>
					<td><span id="brandName"></span></td>
				</tr>
				<tr>
					<td>机型</td>
					<td><span id="modelName"></span></td>
				</tr>
				<tr>
					<td>价格</td>
					<td><span id="price"></span></td>
				</tr>
				<tr>
					<td>订单状态</td>
					<td><span id="state"></span></td>
				</tr>
				<tr>
					<td>备注</td>
					<td><textarea id="info" rows="3" cols="50"></textarea></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dlg-buttons-edit" style="text-align: center">
		<a href="javascript:void(0)" align="center" class="easyui-linkbutton" iconCls="icon-clear" custom="custom" id="editBtn-1">
			订单取消
		</a>
		<a href="javascript:void(0)" align="center" class="easyui-linkbutton" iconCls="icon-ok" custom="custom" id="editBtn-3">
			同意退款
		</a>
		<a href="javascript:void(0)" align="center" class="easyui-linkbutton" iconCls="icon-ok" custom="custom" id="editBtn-6">
			同意退货
		</a>
		<a href="javascript:void(0)" align="center" class="easyui-linkbutton" iconCls="icon-no" custom="custom" id="editBtn-101">
			不同意退款
		</a>
		<a href="javascript:void(0)" align="center" class="easyui-linkbutton" iconCls="icon-no" custom="custom" id="editBtn-102">
			不同意退货
		</a>
		<a href="javascript:void(0)" align="center" class="easyui-linkbutton" iconCls="icon-save" id="editSaveBtn">保存备注</a>
		<a href="javascript:void(0)" align="center" class="easyui-linkbutton" iconCls="icon-cancel" id="editCloseBtn">取消</a>
	</div>
	
	<input type="hidden" id="stateCondition" value="${stateCondition}" />
</body>
</html>
