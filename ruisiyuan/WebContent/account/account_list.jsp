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
<script type="text/javascript" src="account/js/account.js"></script>
<body>
	<!-- 
		定义datagrid控件 
			toolbar: 定义工具栏
	-->
	<table id="list_data" toolbar="#toolbar">
		<thead>
			<tr>
				<th field="account" width="10%">帐号</th>
				<th field="cname" width="10%">昵称</th>
				<th field="headpic" width="10%">头像</th>
				<!-- formatter 指定列的格式化 -->
				<th field="status" formatter="formatterStatus" width="10%">状态</th>
				<th field="createTime" formatter="formatterdate" width="20%">创建时间</th>
			</tr>
		</thead>
	</table>
	
	<!-- 工具栏 -->
	<div id="toolbar">
		<!-- 查询导航栏 -->
		<div>
			<span>帐号:</span><input id="accountCondition" class="easyui-textbox" />
			<span>昵称:</span><input id="cnameCondition" class="easyui-textbox" />
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
					<td>帐号</td>
					<td><input name="account" id="addAccount" type="text" style="width: 200" ></td>
				</tr>
				<tr>
					<td>密码</td>
					<td><input name="password" id="addPassword" type="password" style="width: 200" ></td>
				</tr>
				<tr>
					<td>确认密码</td>
					<td><input name="confimpwd" id="addConfimpwd" type="password" style="width: 200" ></td>
				</tr>
				<tr>
					<td>昵称</td>
					<td><input name="cname" id="addCname" type="text" style="width: 200" ></td>
				</tr>
				<tr>
					<td>是否冻结</td>
					<td>
						<input type="radio" id="addDefaultStatus" name="status" value=1 checked/>正常
						<input type="radio" name="status" value="-1" />冻结
					</td>
				</tr>
				<tr>
					<td>角色</td>
					<td>
						<!-- 页面第一次加载的时候把角色信息load -->
						<span id="addRoles"></span>
						<span id="addRolesMsg" style="display: none"><font color="red">至少选择一个角色</font></span>
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
					<td>帐号</td>
					<td><input name="account" id="editAccount" type="text" style="width: 200" readonly="readonly" ></td>
				</tr>
				<tr>
					<td>密码</td>
					<td><input name="password" id="editPassword" type="password" style="width: 200" ></td>
				</tr>
				<tr>
					<td>昵称</td>
					<td><input name="cname" id="editCname" type="text" style="width: 200" ></td>
				</tr>
				<tr>
					<td>是否冻结</td>
					<td>
						<input type="radio" name="editStatus" value="1" />正常
						<input type="radio" name="editStatus" value="-1" />冻结
					</td>
				</tr>
				<tr>
					<td>角色</td>
					<td>
						<!-- 页面第一次加载的时候把角色信息load -->
						<span id="editRoles"></span>
						<span id="editRolesMsg" style="display: none"><font color="red">至少选择一个角色</font></span>
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
