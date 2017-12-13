<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>用户角色</title>
<%@ include file="/WEB-INF/view/common/head.jsp"%>
<link rel="stylesheet" type="text/css" href="${paths}/css/jquery.validate.css"/>
<script type="text/javascript" src="${paths}/static/validation/jquery.validate.js"></script>
<script type="text/javascript" src="${paths}/static/validation/messages_zh.js"></script>
<script>
	$(function(){
		$("#addRoleForm").validate({
		rules:{
			"role_name" : {
				required : true
			}
		},
		messages:{
          	role_name:{
                  required:"请输入角色名"
              }
          },
          submitHandler: function(form) { //通过之后回调
			//进行ajax传值
			$.ajax({
			　　url : "${paths }/role/add",
			　　type : "post",
			　　data: $('#addRoleForm').serialize(),
			　　success:function(result) {
				if (result == "success") {
					parent.$.messager.alert('提示信息','操作成功!');
					parent.$.getSelectedTabs("#tabs").find("iframe")[0].contentWindow.$.reloadEasyUiDatagrid("#dg");
					parent.$.closeEasyUiWin("#dialog_show___");
				} else {
					parent.$.messager.alert('提示信息','操作失败!');
				}
			　　}
			});
			}
		});
	});
	// 单击添加
	function addRole() {
		$("#addRoleForm").submit();
    }
</script>
</head>

<body>
	<div id="main">
		<form action="" method="post" id="addRoleForm">
			<table border="0" cellspacing="0" cellpadding="0" class="edit_table">
				<tr>
					<th width="20%"><label for="name">角色名:</label>
					</th>
					<td><input type="text" name="role_name" id="roleName" class="wd200" />
					</td>
				</tr>

				<tr>
					<th><label for="username">备注:</label>
					</th>
					<td><input type="text" name="remark" id="username"
						class=" wd200" />
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align:center;padding:2px 0;"><a
						id="submit_add" href="javascript:void(0)" class="easyui-linkbutton"
						data-options="iconCls:'icon-ok'" onclick="addRole()">确定</a></th>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
