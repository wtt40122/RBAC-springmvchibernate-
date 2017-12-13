<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>用户修改</title>
<%@ include file="/WEB-INF/view/common/head.jsp"%>
<link rel="stylesheet" type="text/css" href="${paths}/css/jquery.validate.css"/>
<script type="text/javascript" src="${paths}/static/validation/jquery.validate.js"></script>
<script type="text/javascript" src="${paths}/static/validation/messages_zh.js"></script>
<script>
	$(function(){
		$("#updateUserForm").validate({
		rules:{
			"realName" : {
				required : true
			},
			"userName" : {
				required : true
			},
			"sex" : {
				required : true
			},
			"password" : {
				required : true
			},
			"email" : {
				required : true,
				checkEmail:true
			}
		},
		messages:{
          	realName:{
                  required:"请输入用户名",
              },
            userName:{
                required:"请输入登录名",
            },
            password:{
                required:"请输入密码",
            },
            sex:{
                required:"请选择地址"
            },
            email:{
                required:"请填写邮件",
                email:"邮箱格式不正确"
            }
          },
          submitHandler: function(form) { //通过之后回调
			//进行ajax传值
			$.ajax({
			　　url : "${paths }/user/updateUser",
			　　type : "post",
			　　data: $('#updateUserForm').serialize(),
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
		$.validator.addMethod("checkEmail",function(value,element,params){  
            var checkEmail = /^[a-z0-9]+@([a-z0-9]+\.)+[a-z]{2,4}$/i;  
            return this.optional(element)||(checkEmail.test(value));  
        },"*请输入正确的邮箱！");  
	});
	// 单击添加
	function updateUser() {
		$("#updateUserForm").submit();
    }
</script>
</head>

<body>
	<div id="main">
		<form action="" method="post" id="updateUserForm">
			<input type="hidden" name="userId" value="${user.userId }" />
			<table border="0" cellspacing="0" cellpadding="0" class="edit_table">
				<tr>
					<th width="20%"><label for="name">用户名:</label>
					</th>
					<td><input type="text" name="realName" id="name" class="wd200" value="${user.realName }" />
					</td>
				</tr>

				<tr>
					<th><label for="username">登陆名:</label>
					</th>
					<td><input type="text" name="userName" id="username" class=" wd200" value="${user.userName }"/>
					</td>
				</tr>
				<tr>
					<th><label for="password">密码:</label>
					</th>
					<td><input type="password" name="userPwd" id="password" value="${user.userPwd }"
						class=" wd200" />
					</td>
				</tr>
				<tr>
					<th width="20%">性别：</th>
					<td><select id="sex" name="sex">
						<option value="">==请选择==</option>
						<option value="0">男</option>
						<option value="1">女</option>
					</select></td>
				</tr>
				<tr>
					<th><label for="email">邮箱:</label>
					</th>
					<td><input type="text" name="email" id="email" class=" wd200"
						 maxlength=100 value="${user.email }"/>
					</td>
				</tr>
				<tr>
					<th><label for="mobilePhone">手机:</label>
					</th>
					<td><input type="text" name="phone" id="phone"
						class="wd200" maxlength=20 value="${user.phone }"/>
					</td>
				</tr>
				<tr>
					<th><label for="userComment">备注:</label>
					</th>
					<td><input type="text" name="remark" id="userComment"
						class="wd200" value="" maxlength=200 value="${user.remark }"/>
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align:center;padding:2px 0;"><a
						id="submit_add" href="javascript:void(0)" class="easyui-linkbutton"
						data-options="iconCls:'icon-ok'" onclick="updateUser()">确定</a></th>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
