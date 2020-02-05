<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
<base href="<%=basePath%>">

<title>您访问的页面不存在</title>

</head>

<body>
	<h1><font color="red">服务器故障，请联系程序猿GG</font></h1>
</body>
</html>
