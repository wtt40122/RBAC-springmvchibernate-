<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/view/common/head.jsp" %>
<link rel="stylesheet" href="${paths }/static/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${paths }/static/ztree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${paths }/static/ztree/js/jquery.ztree.excheck.min.js"></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>设置角色</title>
<script type="text/javascript">
$(function(){
	
	var keyList = eval('${role}'); 
	var setting = {
		view: {dblClickExpand:false,showLine:true,checkable:true},
		data: {
			simpleData: {enable:true,idKey: "id",pIdKey: "pid",rootPId: ""}
		},
		check: {enable: true,chkStyle: "checkbox"},
	};
	
	
	$.ajax({
		url : "${paths}/role/getAllRoles",
		dataType : "json",
		type : "post",
		success:function(data){
			var t = $("#resource_tree");
			t = $.fn.zTree.init(t, setting, data);
			var ztree = $.fn.zTree.getZTreeObj("resource_tree");
			var nodes = ztree.getNodes()[0];
			var childNodes = ztree.transformToArray(nodes); 
			for(var i=0;i<childNodes.length;i++){
				for(var j=0;j<keyList.length;j++){
					if(keyList[j] == childNodes[i].id){
						ztree.checkNode(ztree.getNodeByParam("id", childNodes[i].id, null), true, true);
					}
				}
			}
		}
	});
	
});

function setRoles(){
	var role = [];
	var treeObj = $.fn.zTree.getZTreeObj("resource_tree");
	var nodes = treeObj.getCheckedNodes(true);
	if(nodes.length == 0){
		parent.$.messager.alert("提示信息","请选择用户角色","warning");
		return;
	}
	var html="";
	for(i=0;i<nodes.length;i++){
		if(nodes[i].roleId!="010" && !nodes[i].isParent){
			role.push(nodes[i].id);
		}
	}
	$.ajax({
		type : "POST",  
		url : "${paths}/role/setRoles",
		data : {
			'userId' : $('#userId').val(),
			'role' : role
		},
		success : function(data) {
			if(data == "success"){
				window.parent.$.messager.alert("系统提示","分配角色成功！");
				$("#showTree").tree("reload");
			}else{
				parent.$.messager.alert("系统错误！","ERROR");
			}
		}
	});
}
</script>
</head>
<body>
	<div id="main">
		<input type="hidden" id="userId" value="${userId }">
		<div class="easyui-panel" data-options="height:400">
			<ul id="resource_tree" class="ztree"></ul>
		</div>
		<div style="text-align:center"><a href="javascript:void(0)" 
			class="easyui-linkbutton" data-options="iconCls:'icon-save'" 
			onclick="javascript:setRoles();">确定</a></div>
	</div>
</body>
</html>