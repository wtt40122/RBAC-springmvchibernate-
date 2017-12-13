<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<title>用户管理</title>
<%@include file="/WEB-INF/view/common/head.jsp"%>
<script>
	function getSex(val, row) {
		if (val == 0) {
			return "男";
		} else if (val == 1) {
			return "女";
		}
	}
	
	function setRole(val,row){
		var html ="<span style='color:red'>";
		html +="<a onClick=setRoles('"+row.userId+"','"+row.userName+"') href='javascript:void(0)'>设置角色</a>";
		html +="</span>";
		return html;
	}
	
	function addUser(){
		var url="${paths}/user/addUser";
		parent.$.showDialogNew({
				'title' : '用户新增',
				width : 600,
				height : 500,
				'iconCls' : 'icon-add',
				'iframeUrl' : url
			});
	}
	
	function deleteUser(){
		//获取要删除的行
		var selectedRows = $("#dg").datagrid("getSelections");
		if(selectedRows.length == 0){
			$.messager.alert("系统提示","请选择要删除的数据");
			return;
		}
		//定义选中id数组
		var idsStr=[];
		for(var i=0;i<selectedRows.length;i++){
			idsStr.push(selectedRows[i].userId);
		}
		//将数组安装,连接成字符串
		var ids = idsStr.join(",");
		//提示是否需要删除
		$.messager.confirm("系统提示","<font color=red>你确定要删除所选择的数据吗？</font>",function(r){
			//确定删除
			if(r){
				//发送ajax请求
				$.post("${paths}/user/deleteUsers",
					{ids:ids},function(result){
						if(result == "success"){
							$.messager.alert("系统提示","删除成功");
							$("#dg").datagrid("reload");
						}else{
							$.messager.alert("系统提示","删除失败");
						}
					},"text");
			}
		});
	}
	function updateUser(){
		var selectRow = $("#dg").datagrid("getSelections");
		if(selectRow.length == 0){
			$.messager.alert("系统提示","请选择要修改的行");
			return;
		}
		var url="${paths}/user/updateUser/"+selectRow[0].userId;
		parent.$.showDialogNew({
				'title' : '用户修改',
				width : 600,
				height : 500,
				'iconCls' : 'icon-add',
				'iframeUrl' : url
			});
	}
	
	function setRoles(uid,uname){
		var url="${paths}/role/getRoles/"+uid;
		parent.$.showDialogNew({
			'title': '设置角色('+uname+')',
			width:600,
			height:500,
			'iconCls' : 'icon-add',
			'iframeUrl' : url
		});
	}
</script>
</head>

<body>
	<div class="easyui-panel" data-options="fit:true,border:false">
		<div data-options="region:'center'">
			<table id="dg" title="用户性息" class="easyui-datagrid" fitColumns="true"
				pagination="true" url="${paths }/user/getAllUser" toolbar="#tb">
				<thead>
					<tr>
						<th field="userId" checkbox="true" align="center"></th>
						<th field="userName" width="20px" align="center">登录名</th>
						<th field="realName" width="20px" align="center">用户名</th>
						<th field="sex" width="20px" align="center" formatter="getSex">性别</th>
						<th field="phone" width="20px" align="center">phone</th>
						<th field="email" width="20px" align="center">Email</th>
						<th field="setGroup" width="20px" align="center" formatter="setRole">操作</th>
					</tr>
				</thead>
			</table>
		</div>
		<div id="tb" class="datagrid-toolbar">
			<a href="javascript:addUser()" class="easyui-linkbutton"
				iconCls="icon-add" plain="true">新增</a>
			<a	href="javascript:updateUser()" class="easyui-linkbutton"
				iconCls="icon-reload" plain="true">修改</a> 
			<a	href="javascript:deleteUser()" class="easyui-linkbutton"
				iconCls="icon-cut" plain="true">删除</a>
		</div>
	</div>
</body>
</html>
