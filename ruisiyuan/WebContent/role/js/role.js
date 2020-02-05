$(function() {
	// 初始化 datagrid
	$("#list_data").datagrid({
		title:'角色列表',   // 表格标题
        iconCls:'icon-edit',  //表格 图标 
        nowrap: false,   // 是否自动换行
        striped: true,  // 是否隔行变色
        collapsible: true,  // 是否可折叠的 
        fit: true,  // datagrid 窗口自动大小 
        url:'role/role_table.do',   // 分页数据获取 URL

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
	
	// 获取所有菜单
	$.post(
		"menu/menu_getAllMenus.do",
		{},
		function(result) {
			editTreeNodeObject.menus = result.menus;
			reflushAddTree();  // 刷新
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
			nameCondition: $("#nameCondition").val()
		});
	});
	
	/* 新增dialog 页面的保存按钮绑定 */
	$("#saveBtn").on("click", function() {
		// 打印选中的 zTree值
		var tree = $.fn.zTree.getZTreeObj("tree");
		var nodes = tree.getCheckedNodes(true);  // 获得所有选中的节点

		// 启用表单效验并且验证表单
		if($("#addFm").form("enableValidation").form("validate")) {
			// 将zTree选中的所有菜单项，封装成 menus
			var menus = "";
			$.each(nodes, function(index, node) {
				menus += node.id + ",";
			});
			menus = menus.substring(0, menus.length - 1);
			
			// ajax 请求服务器，保存新增的帐号信息
			$.post(
				"role/role_add.do",
				{
					name: $("#addName").val(),
					menus: menus
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
			console.info("效验通过");
			
			var tree = $.fn.zTree.getZTreeObj("editTree");
			var nodes = tree.getCheckedNodes(true);  // 获得所有选中的节点
			// 将zTree选中的所有菜单项，封装成 menus
			var menus = "";
			$.each(nodes, function(index, node) {
				menus += node.id + ",";
			});
			menus = menus.substring(0, menus.length - 1);
			console.info(menus);
			// ajax 请求服务器，保存新增的帐号信息
			$.post(
				"role/role_edit.do",
				{
					id: $("#editId").val(),
					name: $("#editName").val(),
					menuIds: menus
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
		// 将所有选中的菜单权限，取消
		reflushAddTree();
		
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
		$("#editName").val(row.name);
		$("#editId").val(row.id);
		
		// 获取当前角色拥有的菜单,默认选中
		$.post(
			"menu/menu_getMenusByRoleId.do",
			{roleId: row.id},
			function(menuByRole) {
				editTreeNodeObject.menuByRole = menuByRole.menus;
				reflushEditTree(row);  // 刷新
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
			var roleIds = "";
			var roleNames = "";
			$.each(selections, function(index, ele) {
				roleIds += ele.id + ",";
				roleNames += ele.name + ",";
			});
			roleIds = roleIds.substring(0, roleIds.length - 1);
			roleNames = roleNames.substring(0, roleNames.length - 1);
			// 提示用户是否真正要删除
			$.messager.confirm("删除提示", "是否要角色：" + roleNames, function(result) {
				if(result) {
					// 执行删除
					$.post(
						"role/role_del.do",
						{roleIds: roleIds},
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

/* 新增帐号验证 */
function addValidate() {
	$("#editName").validatebox({
		required: true,  // 非空验证 (easyui 自带的验证功能)
		missingMessage:  "帐号为必填项"  // 当文本框为空的时候提示信息
	});
}

/* 编辑帐号验证 */
function editValidate() {
	$("#addName").validatebox({
		required: true,  // 非空验证 (easyui 自带的验证功能)
		missingMessage:  "帐号为必填项"  // 当文本框为空的时候提示信息
	});
}

/* 日期格式化 */
function formatterdate(val, row) {
	if (val != null) {
		var date = new Date(val);
		return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
			+ date.getDate();
	}
}


/* 新增角色获取zTree */
function reflushAddTree() {
	//配置zTree
	var setting = {
		data : {
			simpleData : {
				enable : true, //采用简单数据格式
				idKey : "id", //指定节点id
				pIdKey : "parentId" //指定节点父Id
			}
		},
		check : {
			enable : true,
			chkStyle : "checkbox"
		}
	};
	
	// 获取当前编辑的行
	var row = $("#list_data").datagrid("getSelections")[0];
	
	var nodes = new Array();
	var node;
	// 添加菜单
	$.each(editTreeNodeObject.menus, function(menusIndex, menu) {  // 循环所有菜单
		// 循环当前编辑角色拥有的菜单
		node = new Object();
		node.id = menu.id;
		node.name = menu.name;
		node.parentId = menu.parentId;
		nodes.push(node);
	});
	var zTreeObj = $.fn.zTree.init($("#tree"), setting, nodes);
	// 将加载完成的树，默认展开
	zTreeObj.expandAll(true);  // 展开所有节点
}

/* 编辑角色获取zTree */
var editZTreeObj = new Array();
var editTreeNodeObject = new Object();
function reflushEditTree(row) {
	if(editTreeNodeObject.menuByRole) {
		//配置zTree
		var setting = {
			data : {
				simpleData : {
					enable : true, //采用简单数据格式
					idKey : "id", //指定节点id
					pIdKey : "parentId" //指定节点父Id
				}
			},
			check : {
				enable : true,
				chkStyle : "checkbox"
			}
		};
		
		var nodes = new Array();
		var node;
		// 添加菜单
		$.each(editTreeNodeObject.menus, function(menusIndex, menu) {  // 循环所有菜单
			// 循环当前编辑角色拥有的菜单
			node = new Object();
			node.id = menu.id;
			node.name = menu.name;
			node.parentId = menu.parentId;
			// 嵌套当前角色拥有的菜单
			$.each(editTreeNodeObject.menuByRole, function(menuByRoleIndex, menuByRole) {
				if(menuByRole.id == menu.id) {
					node.checked = true;
				}
			});
			nodes.push(node);
		});
		
		var zTreeObj = $.fn.zTree.init($("#editTree"), setting, nodes);
		// 将加载完成的树，默认展开
		zTreeObj.expandAll(true);  // 展开所有节点
	}
}