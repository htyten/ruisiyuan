$(function() {
	// 初始化 datagrid
	$("#list_data").datagrid({
		title:'帐号列表',   // 表格标题
        iconCls:'icon-edit',  //表格 图标 
        nowrap: false,   // 是否自动换行
        striped: true,  // 是否隔行变色
        collapsible: true,  // 是否可折叠的 
        fit: true,  // datagrid 窗口自动大小 
        url:'menu/menu_table.do',   // 分页数据获取 URL

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
	
	// 加载菜单图标 select 选项
	$('#addIcon').combobox({
		url: "menu/js/icon.json",
		method: "get",
		valueField:'id',
		textField:'text',
		panelHeight:'300',
		formatter: function(row) {
			return '<span class="' + row.id + '">&nbsp;</span> ' + row.id;  // 显示在 option 的 formatter, 没有formatter 需要置顶 valueField
		},
		editable: false	// 不可编辑
	});
	$('#editIcon').combobox({
		url: "menu/js/icon.json",
		method: "get",
		valueField:'id',
		textField:'text',
		panelHeight:'300',
		formatter: function(row) {
			return '<span class="' + row.id + '">&nbsp;</span> ' + row.id;  // 显示在 option 的 formatter, 没有formatter 需要置顶 valueField
		},
		editable: false	// 不可编辑
	});
	
	// 给表单增加验证规则(选择在页面加载完成之后，初始化验证规则)
	addValidate();
	editValidate();
});


$(function() {
	/* 查询按钮绑定function */
	$("#doSearch").on("click", function() {
		// 刷新/重新加载 datagrid 内容
		$("#list_data").datagrid("load", {
			nameCondition: $("#nameCondition").val(),
			parentIdCondition: $("#parentIdCondition").combobox('getValue')
		});
	});
	
	/* 新增dialog 页面的保存按钮绑定 */
	$("#saveBtn").on("click", function() {
		// 启用表单效验并且验证表单
		if($("#addFm").form("enableValidation").form("validate")) {
			// ajax 请求服务器，保存新增的帐号信息
			$.post(
				"menu/menu_add.do",
				{
					name: $("#addName").val(),
					mlevel: $("#addMlevel").combobox("getValue"),
					parentId: $("#addParentId").combobox("getValue"),
					authPath: $("#addAuthPath").val(),
					icon: $("#addIcon").combobox("getValue"),
					orderNum: $("#addOrderNum").val(),
					type: $("#addMlevel").combobox("getValue") == 3 ? 1 : 0  // 菜单类型 菜单级别为 3 的时候，不能显示在侧边菜单栏
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
		// 启用表单效验并且验证表单
		if($("#editFm").form("enableValidation").form("validate")) {
			// ajax 请求服务器，保存新增的帐号信息
			$.post(
				"menu/menu_edit.do",
				{
					id: $("#editId").val(),
					name: $("#editName").val(),
					mlevel: $("#editMlevel").combobox("getValue"),
					parentId: $("#editParentId").combobox("getValue"),
					authPath: $("#editAuthPath").val(),
					icon: $("#editIcon").combobox("getValue"),
					orderNum: $("#editOrderNum").val(),
					type: $("#editMlevel").combobox("getValue") == 3 ? 1 : 0  // 菜单类型 菜单级别为 3 的时候，不能显示在侧边菜单栏
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
		
		// 初始化新增表单数据
		// 默认选中菜单级别为一级菜单
		$("#addMlevel").combobox("setValue", "1");
		// 选择菜单级别，联动调整父菜单选项
		$("#addMlevel").combobox({
			onSelect: function(selection) {
				// 一级菜单加载 mlevel 为 0 的菜单作为父菜单选项
				// 二级菜单加载 mlevel 为 1 的菜单作为父菜单选项
				// 三级菜单加载 mlevel 为 2 的菜单作为父菜单选项
				reflushAddParentIdSelections(selection.value - 1);
			}
		});
		// 初始化父菜单默认选项
		reflushAddParentIdSelections(0);
		// 默认选择图标
		$("#addIcon").combobox("setValue", "icon icon-users");
		
		// 禁用表单验证
		$("#addFm").form("disableValidation");
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
		
		// 初始化要编辑的数据
		$("#editId").val(row.id);
		$("#editName").val(row.name);
		$("#editMlevel").combobox("setValue", row.mlevel);
		// 根据 mlevel 初始化父菜单选项
		reflusEditParentIdSelections(row.mlevel - 1, row.parent.id);
		$("#editAuthPath").val(row.authPath);
		$("#editIcon").combobox("setValue", row.icon);
		$("#editOrderNum").val(row.orderNum);
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
			var menuIds = "";
			var menuNames = "";
			$.each(selections, function(index, ele) {
				menuIds += ele.id + ",";
				menuNames += ele.name + ",";
			});
			menuIds = menuIds.substring(0, menuIds.length - 1);
			menuNames = menuNames.substring(0, menuNames.length - 1);
			// 提示用户是否真正要删除
			$.messager.confirm("删除提示", "是否要删除菜单：" + menuNames, function(result) {
				if(result) {
					// 执行删除
					$.post(
						"menu/menu_del.do",
						{menuIds: menuIds},
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
	$("#addName").validatebox({
		required: true,  // 非空验证 (easyui 自带的验证功能)
		missingMessage:  "菜单昵称不能为空"  // 当文本框为空的时候提示信息
	});
	$("#addOrderNum").validatebox({
		required: true,  // 非空验证 (easyui 自带的验证功能)
		validType: "number",
		missingMessage:  "序号不能为空"  // 当文本框为空的时候提示信息
	});
}

/* 编辑帐号验证 */
function editValidate() {
	$("#editName").validatebox({
		required: true,  // 非空验证 (easyui 自带的验证功能)
		missingMessage:  "菜单昵称不能为空"  // 当文本框为空的时候提示信息
	});
	$("#editOrderNum").validatebox({
		required: true,  // 非空验证 (easyui 自带的验证功能)
		validType: "number",
		missingMessage:  "序号不能为空"  // 当文本框为空的时候提示信息
	});
}


/* 父菜单名称格式化 */
function formatterParentName(val, row) {
	if(val == null) {
		return "";
	} else {
		return val.name;
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

/* 刷新父菜单选项 */
function reflushAddParentIdSelections(mlevel) {
	$.post(
		"menu/menu_getMenusByMlevel.do",
		{mlevel: mlevel},
		function(result) {
			$("#addParentId").combobox({
				data: result.menus,
				valueField:'id',
				textField:'name',
				editable: false	// 不可编辑
			});
			$("#addParentId").combobox("setValue", result.menus[0].id);
		}
	);
}

/* 刷新父菜单选项 */
function reflusEditParentIdSelections(mlevel, id) {
	$.post(
		"menu/menu_getMenusByMlevel.do",
		{mlevel: mlevel},
		function(result) {
			$("#editParentId").combobox({
				data: result.menus,
				valueField:'id',
				textField:'name',
				editable: false	// 不可编辑
			});
			$("#editParentId").combobox("setValue", id);
		}
	);
}