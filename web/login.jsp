<%@ page language="java" import="java.util.*,java.net.URLDecoder" pageEncoding="utf-8"%>
<%@page import="com.dispensary.project.model.*" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>医务室医疗系统</title>
<meta http-equiv="Content-Type" content="text/html charset=utf-8">
<link href="${ctx}/styles/login/login.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/styles/login/jquery.validation.css" rel="stylesheet" type="text/css"/>
<script src="${ctx}/scripts/jquery.js"></script>
</head>

<body>
	<div class="content">
		<div class="logo"></div>
		<div class="loginBox">
			<div class="logintop">用户登录</div>
			<form id="signupForm" name="signupForm" method="POST" class="validation-form-container" action="${ctx}/pages/Userinfo/login.do">
				<div class="field">
					<label for="username">用户名</label>
					<div class="ui left labeled input">
						<input id="username" name=userName class="{required:true,minlength:5,messages:{required:'请输入内容'}}" type="text"  required>
					</div>
				</div>
				<div class="field">
					<label for="password">密码</label>
					<div class="ui left labeled input">
						<input id="password" name="password" type="password" class="{required:true,minlength:5}" required/>
					</div>
				</div>
				<input type="submit" class="ui blue submit button" value="登陆" onclick="return check()"/>
				<input  type="reset" class="ui mini basic button" value="重置"/>
			</form>
		</div>
	</div>
<script type="text/javascript">
	//回车事件
	$(document).keypress(function(e) {
		if(e.which == 13) {
			$("#signupForm").submit();
		}
	});
	function check(){
		if($("#username").val()==""){
			alert("账号不允许为空！");
			return false;
		}
		if($("#password").val()==""){
			alert("密码不允许为空！");
			return false;
		}
		return true;
	}
</script>
</body>
</html>
