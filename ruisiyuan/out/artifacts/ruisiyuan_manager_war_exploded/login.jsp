<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head id="Head1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>睿思源后台管理系统</title>
    <link href="resources/css/default.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="resources/js/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="resources/js/themes/icon.css" />
    <script type="text/javascript" src="resources/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="resources/js/jquery.easyui.min.1.2.2.js"></script>
</head>
	<script language="javascript">
		$(function() {
			//设置登录窗口
            $('#w').window({
                title: '睿思源后台管理系统',
                width: 350,
                modal: true,
                shadow: true,
                closed: true,
                height: 160,
                resizable:false
            });
			
			// 打开登录窗口
			$('#w').window('open');

			$("#btnLogin").click(function() {
				window.location = 'loginOn.do?account=' + $("#account").val() + "&password=" + $("#password").val();
			});

			
		});
	</script>
<body>
	<!--修改密码窗口-->
    <div id="w" class="easyui-window" title="修改密码" collapsible="false" minimizable="false"
        maximizable="false" icon="icon-save"  style="width: 300px; height: 150px; padding: 5px;
        background: #fafafa;">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
                <table cellpadding=3>
                    <tr>
                        <td>用户名：</td>
                        <td><input id="account" type="text" class="account" /></td>
                    </tr>
                    <tr>
                        <td>密　码：</td>
                        <td><input id="password" type="Password" class="password" /></td>
                    </tr>
                </table>
            </div>
            <div region="south" border="false" style="text-align: center; height: 30px; line-height: 30px;">
                <a id="btnLogin" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)">
                    登录
				</a>
            </div>
        </div>
    </div>
</body>
</html>
