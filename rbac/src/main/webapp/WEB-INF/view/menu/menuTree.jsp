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
	
	var keyList = eval('${resIds}'); 
	var setting = {
		view: {dblClickExpand:false,showLine:true,checkable:true},
		data: {
			simpleData: {enable:true,idKey: "resourceId",pIdKey: "pid",rootPId: ""},
			key:{name:'resouceName'}
		},
		check: {enable: true,chkStyle: "checkbox"},
	};
	
	
	$.ajax({
		url : "${paths}/resource/getresources",
		dataType : "json",
		type : "post",
		success:function(data){
			var t = $("#resource_tree");
			 t = $.fn.zTree.init(t, setting, data);
			 var treeObj = $.fn.zTree.getZTreeObj("resource_tree"); 
			 treeObj.expandAll(true); 
			var ztree = $.fn.zTree.getZTreeObj("resource_tree");
			var length = ztree.getNodes().length;
			for(var i=0;i<length;i++){
				var childNodes = ztree.transformToArray(ztree.getNodes()[i]);
				for(var j=0;j<childNodes.length;j++){
					for(var k=0;k<keyList.length;k++){
						if(keyList[k] == childNodes[j].resourceId){
							ztree.checkNode(ztree.getNodeByParam("resourceId", childNodes[j].resourceId, null), true, true);
						}
					}
				}
			}
		}
	});
	
});

function setResources(){
	var resource = [];
	var treeObj = $.fn.zTree.getZTreeObj("resource_tree");
	var nodes = treeObj.getCheckedNodes(true);
	var html="";
	for(i=0;i<nodes.length;i++){
		resource.push(nodes[i].resourceId);
	}
	$.ajax({
		type : "POST",  
		url : "${paths}/resources/assignResources",
		data : {
			'rid' : $('#rid').val(),
			'resource' : resource
		},
		success : function(data) {
			if(data == "success"){
				window.parent.$.messager.alert("系统提示","分配角色成功！");
				$("#showTree").tree("reload");
			}else if(data == "fail"){
				parent.$.messager.alert("系统错误！","分配失败");
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
		<input type="hidden" id="rid" value="${rId }">
		<div class="easyui-panel" data-options="height:400">
			<ul id="resource_tree" class="ztree"></ul>
		</div>
		<div style="text-align:center"><a href="javascript:void(0)" 
			class="easyui-linkbutton" data-options="iconCls:'icon-save'" 
			onclick="javascript:setResources();">确定</a></div>
	</div>
</body>
</html>