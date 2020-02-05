<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
<base href="<%=basePath%>">

<title>权限限制</title>

</head>

<body>
	<h1><font color="red">您没有访问当前页面的权限，如有疑问，请联系系统管理员！！！</font></h1>
</body>
</html>
