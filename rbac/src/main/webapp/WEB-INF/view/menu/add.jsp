<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/view/common/head.jsp" %>
<link rel="stylesheet" type="text/css" href="${paths}/css/jquery.validate.css"/>
<script type="text/javascript" src="${paths}/static/validation/jquery.validate.js"></script>
<script type="text/javascript" src="${paths}/static/validation/messages_zh.js"></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加资源</title>
<script type="text/javascript">
		$(function(){
		$("#addResForm").validate({
		rules:{
			"resouceName" : {
				required : true
			},
			"resouceUrl" : {
				required : true
			}
		},
		messages:{
          	resouceName:{
                  required:"请输入角色名"
              },
             resouceUrl:{
             	  required:"请输入url"
             }
          },
          submitHandler: function(form) { //通过之后回调
			//进行ajax传值
			$.ajax({
			　　url : "${paths }/resource/add",
			　　type : "post",
			　　data: $('#addResForm').serialize(),
			　　success:function(result) {
				if (result == "success") {
					parent.$.messager.alert('提示信息','操作成功!');
					parent.$.getSelectedTabs("#tabs").find("iframe")[0].contentWindow.$.reloadEasyUiTreegrid("#showList","${pid}","${state}");
					parent.$.closeEasyUiWin("#dialog_show___");
				} else {
					parent.$.messager.alert('提示信息','操作失败!');
				}
			　　}
			});
			}
		});
	});
		function addRes(){
			$("#addResForm").submit();
		}
	</script>
</head>
<body>
	<div id="main">
	<form action="" method="post" id="addResForm">
		<input type="hidden" name="pid" value="${pid }">
		<table border="0" cellspacing="0" cellpadding="0" class="edit_table" >
		<tr>
			<th width="20%"><label for="pname">父权限:</label></th>
			<td>${resName }</td>
		</tr>
		<tr>
			<th><label for="name">名称:</label></th>
			<td><input type="text" id="resouceName" name="resouceName" maxlength="100" class="wd200"/></td>
		</tr>
		<tr>
			<th><label for="url">URL:</label></th>
			<td><input type="text" id="resouceUrl" name="resouceUrl" maxlength="200" class="wd200"/><span class="Validform_checktip">扩展action地址，例如：/column/list，最多100个字符</span></td>
		</tr>
		<tr>
			<th><label for="showMenu">是否有子菜单:</label></th>
			<td><input type="radio" name="state" value=closed checked/>是 &nbsp;&nbsp;
				<input type="radio" name="state" value="open" />否</td>
		</tr>
		<tr>
			<th><label for="url">描述:</label></th>
			<td><textarea name="remark" id="remark" style="width:400px;height:80px"></textarea></td>
		</tr>
		
		<tr>
		 	<td colspan="2" align="center">
			<a id="btn" href="javascript:void(0)" class="easyui-linkbutton" onclick="addRes()" data-options="iconCls:'icon-ok'">确定</a> 
			</th>
		</tr>
		</table>
	</form>
	</div>
</body>
</html>