<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath}" var="paths"></c:set>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- 下面是easyui的环境 -->
<link rel="stylesheet" href="<%=basePath%>/static/jquery-easyui-1.3.5/themes/icon.css" type="text/css"></link>
<link rel="stylesheet" href="<%=basePath%>/static/jquery-easyui-1.3.5/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="<%=basePath%>/static/jquery-easyui-1.3.5/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="<%=basePath%>/css/style.css" type="text/css"></link>
<link rel="stylesheet" href="<%=basePath%>/css/style1.css" type="text/css"></link>
<link rel="stylesheet" href="<%=basePath%>/css/colpick.css" type="text/css"></link>
<link rel="stylesheet" href="<%=basePath%>/css/jquery.autocomplete.css" type="text/css"></link>

<script type="text/javascript" src="<%=basePath%>/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/jquery-easyui-1.3.5/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/jquery-easyui-1.3.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/jquery-easyui-1.3.5/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.extend.js"></script>
