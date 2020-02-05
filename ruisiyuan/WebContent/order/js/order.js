$(function() {
	// 初始化 datagrid
	$("#list_data").datagrid({
		title:'订单列表',   // 表格标题
        iconCls:'icon-edit',  //表格 图标 
        nowrap: false,   // 是否自动换行
        striped: true,  // 是否隔行变色
        collapsible: true,  // 是否可折叠的 
        fit: true,  // datagrid 窗口自动大小 
        url:'order/order_table.do?stateCondition=' + $("#stateCondition").val(),   // 分页数据获取 URL

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
        
        /* 根据订单状态控制背景色 */
        rowStyler:function(index, row) {
        	if(row.state == 0) {
        		return "background-color:#CEFDCE;";
        	}
        },
        
        // 冻结在左边的列
        frozenColumns:[[ 
            {field:'ck',checkbox:true} 
        ]]
	});
});


$(function() {
	/* 查询按钮绑定function */
	$("#doSearch").on("click", function() {
		// 刷新/重新加载 datagrid 内容
		$("#list_data").datagrid("load", {
			idCondition: $("#idCondition").val(),
			userCondition: $("#userCondition").val(),
			phoneCondition: $("#phoneCondition").val(),
			stateCondition: $("#stateCondition").val()
		});
	});
	
	/* 保存备注信息，不变更订单状态 */
	$("#editSaveBtn").on("click", function() {
		$.post(
			"order/order_editInfo.do",
			{
				id: $("#id").html(),
				info: $("#info").val()
			},
			function(result) {
				if(result.code == 1) {
					$.messager.alert("备注提示", result.message);
					$('#editDlg').dialog('close');  // 关闭dialog
					$("#list_data").datagrid("reload");
				}
			}
		);
	});
	
	/* 取消订单 */
	$("#editBtn-1").on("click", function() {
		$.post(
			"order/order_editState.do",
			{
				id: $("#id").html(),
				info: $("#info").val(),
				state: -1
			},
			function(result) {
				if(result.code == 1) {
					$.messager.alert("订单状态变更提示", result.message);
					$('#editDlg').dialog('close');  // 关闭dialog
					$("#list_data").datagrid("reload");
				}
			}
		);
	});
	
	/* 同意退款 */
	$("#editBtn-3").on("click", function() {
		$.post(
			"order/order_editState.do",
			{
				id: $("#id").html(),
				info: $("#info").val(),
				state: -3
			},
			function(result) {
				if(result.code == 1) {
					$.messager.alert("订单状态变更提示", result.message);
					$('#editDlg').dialog('close');  // 关闭dialog
					$("#list_data").datagrid("reload");
				}
			}
		);
	});
	
	/* 同意退货 */
	$("#editBtn-6").on("click", function() {
		$.post(
			"order/order_editState.do",
			{
				id: $("#id").html(),
				info: $("#info").val(),
				state: -6
			},
			function(result) {
				if(result.code == 1) {
					$.messager.alert("订单状态变更提示", result.message);
					$('#editDlg').dialog('close');  // 关闭dialog
					$("#list_data").datagrid("reload");
				}
			}
		);
	});
	
	/* 不同意退款 */
	$("#editBtn-101").on("click", function() {
		$.post(
			"order/order_editState.do",
			{
				id: $("#id").html(),
				info: $("#info").val(),
				state: -101
			},
			function(result) {
				if(result.code == 1) {
					$.messager.alert("订单状态变更提示", result.message);
					$('#editDlg').dialog('close');  // 关闭dialog
					$("#list_data").datagrid("reload");
				}
			}
		);
	});
	
	/* 不同意退货 */
	$("#editBtn-102").on("click", function() {
		$.post(
			"order/order_editState.do",
			{
				id: $("#id").html(),
				info: $("#info").val(),
				state: -102
			},
			function(result) {
				if(result.code == 1) {
					$.messager.alert("订单状态变更提示", result.message);
					$('#editDlg').dialog('close');  // 关闭dialog
					$("#list_data").datagrid("reload");
				}
			}
		);
	});
	
	/* 编辑dialog 页面的取消按钮绑定 */
	$("#editCloseBtn").on("click", function() {
		// 启用验证规则
		$('#editDlg').dialog('close');  // 关闭dialog
	});
});

/* val 当前行的值， rows 当前的行对象    rows.account  rows.ename   rows.status .... */
var formatterState = function(val, rows) {
	if(val == 0) {
		return "<font color='green'>订单提交</font>";
	} else if(val == 1) {
		return "<font color='red'>支付中</font>";
	} else if(val == 2) {
		return "<font color='red'>已支付</font>";
	} else if(val == 3) {
		return "<font color='green'>已发货</font>";
	} else if(val == 4) {
		return "<font color='green'>已收货</font>";
	} else if(val == 5) {
		return "<font color='green'>已评价</font>";
	} else if(val == -1) {
		return "订单取消";
	} else if(val == -2) {
		return "<font color='red'>申请退款</font>";
	} else if(val == -3) {
		return "<font color='red'>退款中</font>";
	} else if(val == -4) {
		return "<font color='red'>退款成功并取消</font>";
	} else if(val == -5) {
		return "<font color='red'>申请退货</font>";
	} else if(val == -6) {
		return "<font color='red'>退货中</font>";
	} else if(val == -7) {
		return "<font color='red'>退货成功并取消</font>";
	} else if(val == -101) {
		return "<font color='#919191'>申请退款不通过</font>";
	} else if(val == -102) {
		return "<font color='#919191'>申请退货不通过</font>";
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

/* 变更订单状态 */
function openOrderStateDialog(index) {
	// 隐藏dialog的订单状态变更操作按钮
	$("a[custom='custom']").hide();
	// 清空审核建议
	$("#editFm").form("clear");
	
	var row = $("#list_data").datagrid("getData").rows[index];
	
	$("#id").html(row.id);
	$("#brandName").html(row.brandName);
	$("#modelName").html(row.modelName);
	$("#price").html(row.price);
	
	var state;
	if(row.state == 0) {
		state = "<font color='green'>订单提交</font>";
		$("#editBtn-1").show();
	} else if(row.state == 1) {
		state = "<font color='red'>支付中</font>";
	} else if(row.state == 2) {
		state = "<font color='red'>已支付</font>";
		$("#editBtn-3").show();
		$("#editBtn-101").show();
	} else if(row.state == 3) {
		state = "<font color='green'>已发货</font>";
		$("#editBtn-6").show();
		$("#editBtn-102").show();
	} else if(row.state == 4) {
		state = "<font color='green'>已收货</font>";
		$("#editBtn-6").show();
		$("#editBtn-102").show();
	} else if(row.state == 5) {
		state = "<font color='green'>已评价</font>";
	} else if(row.state == -1) {
		state = "订单取消";
	} else if(row.state == -2) {
		state = "<font color='red'>申请退款</font>";
		$("#editBtn-3").show();
		$("#editBtn-101").show();
	} else if(row.state == -3) {
		state = "<font color='red'>退款中</font>";
	} else if(row.state == -4) {
		state = "<font color='red'>退款成功并取消</font>";
	} else if(row.state == -5) {
		state = "<font color='red'>申请退货</font>";
		$("#editBtn-6").show();
		$("#editBtn-102").show();
	} else if(row.state == -6) {
		state = "<font color='red'>退货中</font>";
	} else if(row.state == -7) {
		state = "<font color='red'>退货成功并取消</font>";
	} else if(row.state == -101) {
		state = "<font color='#919191'>申请退款不通过</font>";
		$("#editBtn-3").show();
	} else if(row.state == -102) {
		state = "<font color='#919191'>申请退货不通过</font>";
		$("#editBtn-6").show();
	}
	
	$("#state").html(state);
	$("#info").html(row.info);
	
	$("#editDlg").dialog("open");
}

/* 操作 */
function formatterCtrl(val, row, index) {
	if(row.state == 1) {
		return "";
	} else if(row.state == -1) {
		return "";
	} else if(row.state == -3) {
		return "";
	} else if(row.state == -4) {
		return "";
	} else if(row.state == -6) {
		return "";
	} else if(row.state == -7) {
		return "";
	} else {
		return "<button onclick='openOrderStateDialog(" + index + ")'>查看</button>";
	}
}