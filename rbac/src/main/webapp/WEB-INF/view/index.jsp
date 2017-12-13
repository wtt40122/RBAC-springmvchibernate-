<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    
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
    <h3>超级管理端</h3>欢迎你${user.userName}<br/>
    功能：<br/>
    <c:forEach var="resource" items="${resources}">
    	<a href="${resouce.resouceUrl }">${resource.resouceName }</a><br/>
    </c:forEach>
    角色管理:<button onclick="">添加角色</button><br/>
    <c:forEach var="role" items="${roles }">
    	<text value="">${role.role_name}</text><><br />
    </c:forEach>
  </body>
</html>
