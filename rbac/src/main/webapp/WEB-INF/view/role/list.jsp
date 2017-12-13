<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>角色管理</title>
    <%@ include file="/WEB-INF/view/common/head.jsp" %>
	<script>
		function setResource(val,row){
			var html ="<span style='color:red'>";
			html +="<a onClick=setRes('"+row.roleId+"','"+row.roleName+"') href='javascript:void(0)'>分配权限</a>";
			html +="</span>";
			return html;
		}
		function setRes(rid,rname){
			var url="${paths}/resource/assignPermissions/"+rid;
			parent.$.showDialogNew({
				'title': '角色授权('+rname+')',
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
				pagination="true">
				<thead>
					<tr>
						<th field="roleId" checkbox="true" align="center"></th>
						<th field="roleName" width="20px" align="center">角色名称</th>
						<th field="remark" width="20px" align="center">备注</th>
						<th field="setGroup" width="20px" align="center" formatter="setResource">操作</th>
					</tr>
				</thead>
			</table>
		</div>
  </div>
    <div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center'" title="查询结果">
			<div id="showList" width="600px">
			</div>
		</div>
	</div>
</div>
  </body>
  <script>
  	$(function(){
  		var url="${paths}/role/getRoles";
  		$("#dg").datagrid({
  			url:url,
  			method : 'post',
           	height:'auto',
  			toolbar:[{
  				text:'添加',
  				iconCls : 'icon-add',
  				handler:function(){
  					var url="${paths}/role/add";
						parent.$.showDialogNew({
									title: '新增角色',
									width : 800,
									height : 500,
									'iconCls' : 'icon-edit',
									'iframeUrl' : url
						});
  				}
  			},'-',{
  				text:'修改',
  				iconCls:'icon-edit',
  				handler:function(){
  					var selectRows = $("#dg").datagrid("getSelections");
  					if(selectRows.length == 0){
  						$.messager.alert('消息提示', '请选择一个删除','warning');
  					}
  					var url = "${paths}/role/update/"+selectRows[0].roleId;
  					parent.$.showDialogNew({
  						title:'修改角色',
  						width:800,
  						height:500,
  						'iconCls' : 'icon-edit',
  						'iframeUrl' : url
  					});
  				}
  			},'-',{
  				text:'删除',
  				iconCls:'icon-remove',
  				handler:function(){
  					var selectRows = $("#dg").datagrid("getSelections");
  					if(selectRows.length == 0){
						$.messager.alert("提示消息","请选择你要删除的列");
						return;  					
  					}
  					var idStr = [];
  					for(var i=0;i<selectRows.length;i++){
  						idStr.push(selectRows[i].roleId);
  					}
  					var ids = idStr.join(",");
  					$.messager.confirm("系统提示","<font color='red'>你确定要删除所选择的数据吗？</font>",function(r){
  						if(r){
  							$.post("${paths}/role/delete",{ids:ids},function(result){
  								if(result == "success"){
  									parent.$.messager.alert("系统提示","删除成功");
									$("#dg").datagrid("reload");
  								}else{
  									parent.$.messager.alert("系统提示","删除失败");
  								}
  							});
  						}
  					},"text");
  				}
  			}]
  		});
  		
  		
  	});
  </script>
</html>
