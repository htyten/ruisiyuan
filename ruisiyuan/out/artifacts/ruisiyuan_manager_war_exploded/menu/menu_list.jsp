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
<!-- 自定义 js，加载 easyui 组件 -->
<script type="text/javascript" src="menu/js/menu.js"></script>
<body>
	<!-- 
		定义datagrid控件 
			toolbar: 定义工具栏
	-->
	<table id="list_data" toolbar="#toolbar">
		<thead>
			<tr>
				<th field="name" width="10%">名称</th>
				<th field="mlevel" width="10%">级别</th>
				<th field="orderNum" width="10%">排序号</th>
				<th field="parent" formatter="formatterParentName" width="10%">父菜单</th>
				<th field="authPath" width="10%">路径</th>
				<th field="icon" width="10%">图标</th>
				<!-- formatter 指定列的格式化 -->
				<th field="createTime" formatter="formatterdate" width="20%">创建时间</th>
				<th field="account" width="10%">创建人</th>
			</tr>
		</thead>
	</table>
	
	<!-- 工具栏 -->
	<div id="toolbar">
		<!-- 查询导航栏 -->
		<div>
			<span>菜单名称:</span>
			<input id="nameCondition" class="easyui-textbox" />
			
			<span>父菜单:</span>
			<select id="parentIdCondition" style="width:200px;" class="easyui-combobox" editable="false">
				<option value=-100>全部</option>
				<c:forEach items="${allMenus}" var="menu">
				<option value="${menu.id}">${menu.name}</option>
				</c:forEach>
			</select>
			
			<a class="easyui-linkbutton" iconCls="icon-search" plain="true"
				id="doSearch">查询</a>
		</div>
		<!-- RUD按钮 -->
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" id="add">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id="update">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" id="del">删除</a>
	</div>
	
	<!-- 添加 -->
	<div id="addDlg" title="新增帐号" class="easyui-dialog" closed="true" buttons="#dlg-buttons"
		style="width:450px;height:440px;padding:10px 20px">
		<form id="addFm" method="post" data-options="novalidate:true">
			<table>
				<tr>
					<td>菜单名称</td>
					<td><input id="addName" type="text" style="width: 200" ></td>
				</tr>
				<tr>
					<td>菜单级别</td>
					<td>
						<select id="addMlevel" style="width:200px;" class="easyui-combobox" editable="false">
							<option value=1>菜单栏一级菜单</option>
							<option value=2>菜单栏二级菜单</option>
							<option value=3>非菜单栏菜单</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>父菜单</td>
					<td>
						<input id="addParentId" style="width:200px;" class="easyui-combobox" />
					</td>
				</tr>
				<tr>
					<td>菜单路径</td>
					<td><input id="addAuthPath" type="text" style="width: 200" ></td>
				</tr>
				<tr>
					<td>菜单图标</td>
					<td>
						<input id="addIcon" style="width:200px;"/>
					</td>
				</tr>
				<tr>
					<td>排序号</td>
					<td>
						<input id="addOrderNum" type="text" />
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
					<td>菜单名称</td>
					<td>
						<input id="editName" type="text" style="width: 200" >
						<input type="hidden" id="editId" />
					</td>
				</tr>
				<tr>
					<td>菜单级别</td>
					<td>
						<select id="editMlevel" style="width:200px;" class="easyui-combobox" editable="false">
							<option value=1>菜单栏一级菜单</option>
							<option value=2>菜单栏二级菜单</option>
							<option value=3>非菜单栏菜单</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>父菜单</td>
					<td>
						<select id="editParentId" style="width:200px;" class="easyui-combobox" editable="false">
							<option value=0 style="display: none;">root</option>
							<c:forEach items="${allMenus}" var="menu">
							<option value="${menu.id}">${menu.name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>菜单路径</td>
					<td><input id="editAuthPath" type="text" style="width: 200" ></td>
				</tr>
				<tr>
					<td>菜单图标</td>
					<td>
						<input id="editIcon" style="width:200px;"/>
					</td>
				</tr>
				<tr>
					<td>排序号</td>
					<td>
						<input id="editOrderNum" type="text" />
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
