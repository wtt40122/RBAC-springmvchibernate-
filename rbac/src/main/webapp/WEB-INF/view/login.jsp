<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<form action="user/login" method="post">
  		用户名：<input type="text" name="userName"><br/>
  		密码：<input type="password" name="userPwd"><br/>
  		<input type="submit" value="登录" />
  	</form>
  	<br/>
  	<form action="user/getRoleById">
  		用户Id：<input type="text" name="uid"><br/>
  		<input type="submit" value="登录" />
  	</form>
  	<br/>
  	<form action="user/getResource">
  		用户Id：<input type="text" name="rid"><br/>
  		<input type="submit" value="登录" />
  	</form>
  </body>
</html>
