<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%@ include file="/WEB-INF/view/common/head.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>resource list</title>
<script type="text/javascript">
$(function(){
	$('#showList').treegrid({
			url: '${paths}/resource/list?datetime=' + new Date(),
			method : 'post',
			title : '功能列表',
			idField:'resourceId',
			treeField:'resouceName',
			rownumbers:true,
			fitColumns:true,
			fit		 : true,
			autoRowHeight:false,
			columns : [ [ {
				field : 'resourceId',
				title : 'id',
				width : 50
			}, {
				field : 'resouceName',
				title : 'name',
				width : 300
			},  {
				field : 'resouceUrl',
				title : 'url',
				width : 100
			}
			]],toolbar : [{
				text : '新增',
				iconCls : 'icon-add',
				handler : function() {
					var row = $('#showList').treegrid('getSelected');
					if(row){
						var url = "${paths}/resource/add?id=" + row.resourceId;
						parent.$.showDialogNew({
							title:"新增菜单",
							width:800,
							height:300,
							iconCls:'icon-edit',
							iframeUrl:url
						});
					}else{
						parent.$.messager.alert('提示信息', '请选择父节点');
					}
				}
			},'-', {
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					var row = $('#showList').treegrid('getSelected');
					if(row){
						var url = "${paths}/resource/update/" + row.resourceId;
						parent.$.showDialogNew({
							title:"编辑权限",
							width:800,
							height:300,
							iconCls:'icon-edit',
							iframeUrl:url
						});
					}else{
						parent.$.messager.alert('提示信息', '请选选择节点');
					}
				}
			},'-', {
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					var row = $('#showList').treegrid('getSelected');
					if(row == null){
						parent.$.messager.alert('提示信息','请选择节点', 'ERROR');
						return;
					}
					if(row){
						parent.$.messager.confirm("系统提示", "确定删除菜单么？", function(r) {
						if (r) {
							$.post("${paths}/resource/del",{id:row.resourceId},function(result){
								if(result == "success"){
  									parent.$.messager.alert("系统提示","删除成功");
									$('#showList').treegrid('reload');   
									$('#showList').treegrid('clearSelections'); 
  								}else if(result == "fail"){
  									parent.$.messager.alert("系统提示","该菜单有子菜单，请先删除");
  								}else{
  									parent.$.messager.alert("系统提示","删除失败");
  								}
							});
						}
					});
					}
					
				}
			},'-', {
						text : "刷新",
						iconCls : "icon-reload",
						handler : function() {
								var row = $('#showList').treegrid('getSelected');
								if(row){
									if(row.state=="open"){
										prow = $("#showList").treegrid("getParent",row.id);
										$('#showList').treegrid("reload",prow.id);
									}else {
										$('#showList').treegrid("reload",row.id);
									}
									
								}else{
									$('#showList').treegrid("reload");
								}
								
						}
			}]
		});
})

</script>
</head>
<body>
	<div class="easyui-panel p2" data-options="fit:true,border:false">
		<table id="showList"></table>
	</div>
</body>
</html>