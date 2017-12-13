<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>主页</title>
<%@ include file="/WEB-INF/view/common/head.jsp"%>

<style type="text/css">
body {
	font-family: microsoft yahei;
}
</style>
<script type="text/javascript">
		
        function openTab(id,text,url){
        	var path="<%=request.getContextPath()%>";
        	url = path + url;
        	var content = "<iframe frameborder=0 scroling='auto' style='width:100%;height:100%' src='"+url+"'></iframe>"
        	
        	if($('#tabs').tabs('exists',text)){
        		$('#tabs').tabs('select',text);
        	}else{
        		$('#tabs').tabs('add',{
        			title:text,
        			closable:true,		//是否允许选项卡折叠
        			content:content
        		})
        	}
        }
        
        function openPasswordModifyDialog() {
            $("#dlg").dialog("open").dialog("setTitle","修改密码");
        }
        /**
         * 提交修改密码
         */
        function submitData() {
            $("#fm").form('submit',{
                url:'${blog}/admin/blogger/modifyPassword.do',
                onsubmit:function () {
                    var newPassword = $("#password").val();
                    var newPassword2 = $("#password2").val();
                    if(!$(this).form("validate")) {
                        return false; //验证不通过直接false，即没填
                    }
                    if(newPassword != newPassword2) {
                        $.messager.alert("系统提示", "两次密码必须填写一致");
                        return false
                    }
                    return true;
                },//进行验证，通过才让提交
                success: function(result) {
                    //var result = eval("(" + result + ")"); //将json格式的result转换成js对象
                    var result = JSON.parse(result);
                    if(result.success) {
                        $.messager.alert("系统提示", "密码修改成功，下一次登陆生效");
                        closePasswordModifyDialog();
                    } else {
                        $.messager.alert("系统提示", "密码修改失败");
                        return;
                    }
                }
            });
        }
        /**
         * 关闭对话框
         */
        function closePasswordModifyDialog() {
            $("#password").val(""); //保存成功后将内容置空
            $("#password2").val("");
            $("#dlg").dialog("close"); //关闭对话框
        }
        
        
        function getSubMenu(pid){
        	 var pid = pid;
        	/*$.ajax({
        		url:user/getSubmenu,
        		type:'post',
        		data{pid:pid},
        		dataType:'json',
        		success:function(result){
        			
        		}
        	}); */
        	var resources = '${requestScope.resources}';
        	for(var i=0;i<resources.length();i++){
        		
        	}
        }
        
</script>
</head>
<body class="easyui-layout">
	<div region="north" style="height: 78px; background-color: #E0ECFF">
		<table style="padding: 5px" width="100%">
        <tr>
            <td width="50%">
                <h2>主页</h2>
            </td>
            <td valign="bottom" align="right" width="50%">
                <font size="3">&nbsp;&nbsp;<strong>欢迎：</strong></font>
            </td>
        </tr>
        </table>
	</div>
	<div region="west" style="width: 200px" title="导航菜单" split="true">
		<div class="easyui-accordion" data-options="fit:true,border:false" type="menu">
			<c:forEach var="resource" items="${resources }">
				<c:if test="${resource.pid eq '0' }">
				<div id="${resource.resourceId }" title="${resource.resouceName }" 
				data-options="iconCls:'${resource.icon }'" style="paddding:10px" >
			<ul class='left-menu'>
				<c:forEach var="resou" items="${resources }">
					<c:if test="${resou.pid eq resource.resourceId }">
						<li onclick="openTab('${resou.resourceId}','${resou.resouceName }','${resou.resouceUrl }')">
							<a class="menu-a">${resou.resouceName }</a>
						</li>
					</c:if>
				</c:forEach>
			</ul>
			</div>
			</c:if>
			
		</c:forEach>
    </div>
	</div>
	<div data-options="region:'center'" style="background:#eee;">
		<div class="easyui-tabs" fit="true" border="false" id="tabs">
        <div title="首页" data-options="iconCls:'icon-home'">
            <div align="center" style="padding-top: 100px"><font color="red" size="10">欢迎使用</font></div>
        </div>
    </div>
	</div>
	<div region="south" style="height: 25px;padding: 5px" align="center">
		<div region="south" style="height: 25px;padding: 5px" align="center">
    		Copyright 鸡声茅店月，人迹板桥霜
		</div>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width:400px; height:200px; padding:10px 20px"
     closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post">
        <table cellspacing="8px">
            <tr>
                <td>用户名</td>
                <input type="hidden" value="" name="id"/>
                <td>
                    <input type="text" id="username" name="userName" value="${currentUser.userName }" readonly="readonly">
                </td>
            </tr>
            <tr>
                <td>新密码</td>
                <td>
                    <input type="password" id="password" name="password" class="easyui-validatebox"
                           required="true" style="width:200px">
                </td>
            </tr>
            <tr>
                <td>确认新密码</td>
                <td>
                    <input type="password" id="password2" name="password2" class="easyui-validatebox"
                           required="true" style="width:200px">
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="dlg-buttons">
    <div>
        <a href="javascript:submitData()" class="easyui-linkbutton" iconCls="icon-ok" plain="true">保存</a>
        <a href="javascript:closePasswordModifyDialog()" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">关闭</a>
    </div>
</div>
	
</body>
</html>