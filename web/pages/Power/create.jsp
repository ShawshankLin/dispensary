<%@page import="com.dispensary.project.model.Power"%>
<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8" pageEncoding="utf-8"%>
<%@ include file="/commons/taglibs.jsp" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>添加<%=Power.TABLE_ALIAS %>信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link rel="stylesheet" href="../../styles/pure-min.css">
	<link rel="stylesheet" href="../../styles/font-awesome-4.2.0/css/font-awesome.min.css" />
	<script type="text/javascript" src="../../scripts/jquery.js"></script>
	<script type="text/javascript">
	
	
	</script>

  </head>
  
  <body>
    <div class="main">
		<form class="pure-form pure-form-aligned" action="${ctx}/pages/Power/save.do" method="post">
			<fieldset>
					<legend>添加<%=Power.TABLE_ALIAS %>信息</legend>
					<div class="pure-control-group">
						<label for="powerName">权限名称</label> 
						<input id="powerName" type="text" name="powerName" placeholder="必填" required>
					</div>
					
					<div class="pure-control-group">
						<label></label>
						<button type="submit" class="pure-button  pure-button-primary" onclick="return checkReg()">
							保存</button>
						<button type="button" class="pure-button  pure-button-primary" onclick="history.back()">返回</button>
					</div>
			</fieldset>
		</form>

	</div>
  </body>
</html>
