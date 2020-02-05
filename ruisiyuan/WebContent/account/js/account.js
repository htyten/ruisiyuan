$(function() {
	// 初始化 datagrid
	$("#list_data").datagrid({
		title:'帐号列表',   // 表格标题
        iconCls:'icon-edit',  //表格 图标 
        nowrap: false,   // 是否自动换行
        striped: true,  // 是否隔行变色
        collapsible: true,  // 是否可折叠的 
        fit: true,  // datagrid 窗口自动大小 
        url:'account/account_table.do',   // 分页数据获取 URL

        singleSelect: false,  // 是否单选 
        selectOnCheck: true,  // 如果设置为true，单击复选框将始终选择行。如果为假，选择行不会选中复选框
        checkOnSelect: true,  // 如果是true，复选框被选中/未选中时，行选中状态与复选框一致
        
        pagination: true,  //分页控件 
        rownumbers:true,  //行号 
        pageSize: 20,  // 每页显示的记录条数，默认为10 
        pageList: [5,10,15,20],  // 可以设置每页记录条数的列表 
        beforePageText: '第',  // 页数文本框前显示的汉字 
        afterPageText: '页    共 {pages} 页', 
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
        
        // 冻结在左边的列
        frozenColumns:[[ 
            {field:'ck',checkbox:true} 
        ]]
	});
	
	// 初始化 add 的角色复选框内容
	$.post(
		"role/role_getAll.do",
		{},
		function(result) {
			// 将复选框内容渲染到 id="addRoles" 的span标签当中
			$.each(result.roles, function(index, ele) {
				$("#addRoles").append("<input type='checkbox' name='roles' value='" + ele.id + "' /> " + ele.name + "<br/>");
				$("#editRoles").append("<input type='checkbox' name='editRoles' value='" + ele.id + "' /> " + ele.name + "<br/>");
			});
		}
	);
	
	// 给表单增加验证规则(选择在页面加载完成之后，初始化验证规则)
	addValidate();
	editValidate();
});


$(function() {
	/* 查询按钮绑定function */
	$("#doSearch").on("click", function() {
		// 刷新/重新加载 datagrid 内容
		$("#list_data").datagrid("load", {
			accountCondition: $("#accountCondition").val(),
			cnameCondition: $("#cnameCondition").val()
		});
	});
	
	/* 新增dialog 页面的保存按钮绑定 */
	$("#saveBtn").on("click", function() {
		// 验证角色是否选中
		var rolesFlag = false;  // 角色验证临时标量
		// 判断是否有角色被选中
		if($("input[name='roles']:checked").length == 0) {
			$("#addRolesMsg").show();
			rolesFlag = false;
		} else {
			$("#addRolesMsg").hide();
			rolesFlag = true;
		}
		// 启用表单效验并且验证表单
		if($("#addFm").form("enableValidation").form("validate") && rolesFlag) {
			console.info("效验通过");
			
			// 获取所有选中的角色信息
			var roleIds = "";
			$.each($("input[name='roles']:checked"), function(index, ele) {
				roleIds += $(ele).val() + ",";
			});
			roleIds = roleIds.substring(0, roleIds.length - 1);
			
			// 获取帐号状态信息
			var status = $($("input[name='status']:checked")[0]).val();
			// ajax 请求服务器，保存新增的帐号信息
			$.post(
				"account/account_add.do",
				{
					account: $("#addAccount").val(),
					password: $("#addPassword").val(),
					cname: $("#addCname").val(),
					status: status,
					roleIds: roleIds
				},
				function(result) {
					if(result.code == 1) {
						// 关闭 dialog 
						$('#addDlg').dialog('close');  // 关闭dialog
						// 提示新增完成信息
						$.messager.alert("新增提示", result.message);
						// 刷新 datagrid
						$("#list_data").datagrid("reload");
					}
				}
			);
		} else {
			console.info("效验不通过");
		}
	});
	
	/* 新增dialog 页面的保存按钮绑定 */
	$("#editSaveBtn").on("click", function() {
		// 验证角色是否选中
		var rolesFlag = false;  // 角色验证临时标量
		// 判断是否有角色被选中
		if($("input[name='editRoles']:checked").length == 0) {
			$("#editRolesMsg").show();
			rolesFlag = false;
		} else {
			$("#editRolesMsg").hide();
			rolesFlag = true;
		}
		// 启用表单效验并且验证表单
		if($("#editFm").form("enableValidation").form("validate") && rolesFlag) {
			console.info("效验通过");
			
			// 获取所有选中的角色信息
			var roleIds = "";
			$.each($("input[name='editRoles']:checked"), function(index, ele) {
				roleIds += $(ele).val() + ",";
			});
			roleIds = roleIds.substring(0, roleIds.length - 1);
			
			// 获取帐号状态信息
			var status = $($("input[name='editStatus']:checked")[0]).val();
			// ajax 请求服务器，保存新增的帐号信息
			$.post(
				"account/account_edit.do",
				{
					account: $("#editAccount").val(),
					password: $("#editPassword").val(),
					cname: $("#editCname").val(),
					status: status,
					roleIds: roleIds
				},
				function(result) {
					if(result.code == 1) {
						// 关闭 dialog 
						$('#editDlg').dialog('close');  // 关闭dialog
						// 提示新增完成信息
						$.messager.alert("编辑提示", result.message);
						// 刷新 datagrid
						$("#list_data").datagrid("reload");
					}
				}
			);
		} else {
			console.info("效验不通过");
		}
	});
	
	/* 新增dialog 页面的取消按钮绑定 */
	$("#closeBtn").on("click", function() {
		// 启用验证规则
		$('#addDlg').dialog('close');  // 关闭dialog
	});
	/* 编辑dialog 页面的取消按钮绑定 */
	$("#editCloseBtn").on("click", function() {
		// 启用验证规则
		$('#editDlg').dialog('close');  // 关闭dialog
	});
	
	/* 新增按钮绑定方法 */
	$("#add").on("click", function() {
		// 清空表单数据
		$("#addFm").form("clear");
		// 将冻结状态，默认值设置为 正常
		$("#addDefaultStatus").prop("checked", "checked");
		// 隐藏角色自定义验证的span
		$("#addRolesMsg").hide();
		// 禁用表单验证
		// $("#addFm").form("disableValidation");
		// 弹出新增的div
		$("#addDlg").dialog("open");
	});
	
	$("#update").on("click", function() {
		// 判断是否有选中要编辑的记录
		var selections = $("#list_data").datagrid("getSelections");
		if(selections.length == 0) {
			$.messager.alert("编辑提示", "请选择要编辑的行！");
			return;
		} else if(selections.length > 1) {
			$.messager.alert("编辑提示", "只能选择一行进行编辑！");
			return;
		}
		
		// 打开 editDialog
		$("#editDlg").dialog("open");
		
		// 将要编辑的行数据，填充到 editDialog 当中
		var row = selections[0];
		
		$("#editAccount").val(row.account);
		// $("#addPassword").val(row.password);
		$("#editCname").val(row.cname);
		$.each($("input[name='editStatus']"), function(index, ele) {
			if($(ele).val() == row.status) {
				$(ele).attr("checked", true);
			}
		});
		
		// 通过 ajax 获取当前选中行的帐号，所拥有的角色信息
		$.post(
			"role/role_getRolesByAcc.do",
			{account: row.account},
			function(result) {
				// 服务器获取的帐号拥有的角色信息：result.roles
				$.each(result.roles, function(index, role) {
					// 页面加载完成之后，渲染的所有角色信息: $("input[name='editRoles']")
					$.each($("input[name='editRoles']"), function(index, ele) {
						if($(ele).val() == role.id ) {
							$(ele).prop("checked", true);
						}
					});
				});
			}
		);
	});
	
	/* 删除按钮绑定方法 */
	$("#del").on("click", function() {
		// 查看是否有记录被选中, 如果没有记录被选中，提示"选择要删除的信息"
		var selections = $("#list_data").datagrid("getSelections");  // 获取所有选中的行，当没有选中的记录时，将返回空数组。
		if(selections.length == 0) {
			// 没有选中，使用 easyui messager 弹出提示信息
			$.messager.alert("删除提示", "请选择要删除的信息！");
			return;
		} else {
			// 获取当前被选中的信息
			var accounts = "";
			$.each(selections, function(index, ele) {
				accounts += ele.account + ",";
			});
			accounts = accounts.substring(0, accounts.length - 1);
			// 提示用户是否真正要删除
			$.messager.confirm("删除提示", "是否要删除帐号：" + accounts, function(result) {
				if(result) {
					// 执行删除
					$.post(
						"account/account_del.do",
						{accounts: accounts},
						function(result) {
							if(result.code == 1) {
								$.messager.alert("删除提示", result.message);
								// 刷新 datagrid
								$("#list_data").datagrid("reload");
							}
						}
					);
				}
			});
		}
	});
});










/* 验证规则 */
/* 新增帐号验证 */
function addValidate() {
	$("#addAccount").validatebox({
		required: true,  // 非空验证 (easyui 自带的验证功能)
		// length[3, 12],  字符长度在 3 - 12之间
		// equalParamToServer  自定义的 Ajax 验证 param1: ajax 请求的地址，    param2：ajax请求时带的字段名称，字段对应的值是本文本框输入的值
		validType: ["length[3, 12]", "equalParamToServer['account/account_validate.do', 'account']"],
		missingMessage:  "帐号为必填项"  // 当文本框为空的时候提示信息
	});
	$("#addPassword").validatebox({
		required: true,  // 非空验证 (easyui 自带的验证功能)
		validType: "length[3, 12]",
		missingMessage:  "密码为必填项"  // 当文本框为空的时候提示信息
	});
	$("#addConfimpwd").validatebox({
		validType: "equalTo['#addPassword']"
	});
	$("#addCname").validatebox({
		required: true,  // 非空验证 (easyui 自带的验证功能)
		validType: "length[2, 5]",
		missingMessage:  "昵称为必填项"  // 当文本框为空的时候提示信息
	});
}

/* 编辑帐号验证 */
function editValidate() {
	$("#editPassword").validatebox({
		required: true,  // 非空验证 (easyui 自带的验证功能)
		validType: "length[3, 12]",
		missingMessage:  "密码为必填项"  // 当文本框为空的时候提示信息
	});
	$("#editCname").validatebox({
		required: true,  // 非空验证 (easyui 自带的验证功能)
		validType: "length[2, 5]",
		missingMessage:  "昵称为必填项"  // 当文本框为空的时候提示信息
	});
}

/* val 当前行的值， rows 当前的行对象    rows.account  rows.ename   rows.status .... */
var formatterStatus = function(val, rows) {
	if(val == 1) {
		return "正常";
	} else {
		return "<font color='red'>禁用</font>";
	}
}
/* 日期格式化 */
function formatterdate(val, row) {
	if (val != null) {
		var date = new Date(val);
		return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
			+ date.getDate();
	}
}