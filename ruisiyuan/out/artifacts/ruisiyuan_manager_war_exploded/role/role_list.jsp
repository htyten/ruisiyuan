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

<title>帐号管理</title>
<%@ include file="/head.html" %>
</head>
<!-- 自定义 js，加载 easyui 组件 -->
<script type="text/javascript" src="role/js/role.js"></script>
<body>
	<!-- 
		定义datagrid控件 
			toolbar: 定义工具栏
	-->
	<table id="list_data" toolbar="#toolbar">
		<thead>
			<tr>
				<th field="name" width="10%">角色名称</th>
				<th field="account" width="10%">创建人</th>
				<th field="createTime" formatter="formatterdate" width="20%">创建时间</th>
			</tr>
		</thead>
	</table>
	
	<!-- 工具栏 -->
	<div id="toolbar">
		<!-- 查询导航栏 -->
		<div>
			<span>角色名称:</span><input id="nameCondition" class="easyui-textbox" />
			<a class="easyui-linkbutton" iconCls="icon-search" plain="true"
				id="doSearch">查询</a>
		</div>
		<!-- RUD按钮 -->
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" id="add">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id="update">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" id="del">删除</a>
	</div>
	
	<!-- 添加 -->
	<div id="addDlg" title="新增角色" class="easyui-dialog" closed="true" buttons="#dlg-buttons"
		style="width:450px;height:440px;padding:10px 20px">
		<form id="addFm" method="post" data-options="novalidate:true">
			<table>
				<tr>
					<td>角色名称</td>
					<td><input name="name" id="addName" type="text" style="width: 200" ></td>
				</tr>
				<tr>
					<td>菜单权限</td>
					<td>
						<!-- 页面第一次加载的时候菜单和权限组合成zTree显示出来 -->
						<ul id="tree" class="ztree" style="width: 260px; overflow: auto;"></ul>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dlg-buttons" style="text-align: center">
		<a href="javascript:void(0)" align="center" class="easyui-linkbutton" iconCls="icon-ok" id="saveBtn">保存</a>
		<a href="javascript:void(0)" align="center" class="easyui-linkbutton" iconCls="icon-cancel" id="closeBtn">取消</a>
	</div>
	
	
	<!-- 编辑 -->
	<div id="editDlg" title="编辑帐号" class="easyui-dialog" closed="true" buttons="#dlg-buttons-edit"
		style="width:450px;height:440px;padding:10px 20px">
		<form id="editFm" method="post" data-options="novalidate:true">
			<table>
				<tr>
					<td>角色名称</td>
					<td>
						<input name="name" id="editName" type="text" style="width: 200" >
						<input type="hidden" id="editId" />
					</td>
				</tr>
				<tr>
					<td>菜单权限</td>
					<td>
						<!-- 页面第一次加载的时候菜单和权限组合成zTree显示出来 -->
						<ul id="editTree" class="ztree" style="width: 260px; overflow: auto;"></ul>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dlg-buttons-edit" style="text-align: center">
		<a href="javascript:void(0)" align="center" class="easyui-linkbutton" iconCls="icon-ok" id="editSaveBtn">保存</a>
		<a href="javascript:void(0)" align="center" class="easyui-linkbutton" iconCls="icon-cancel" id="editCloseBtn">取消</a>
	</div>
</body>
</html>
