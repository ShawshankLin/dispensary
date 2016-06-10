<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'show.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/common.css" />
	<style type="text/css">

	</style>
  </head>
  
  <body>
   	<table cellspacing="0" class="data_table">
   		<thead>
   		<tr>
   			<th>标题</th>
   			<th>日期</th>
   			<th>内容</th>
   			<th>附件</th>
   		</tr>
   		</thead>
   		<tbody>
   		<tr>
   			<td><c:out value="${requestScope.memo.title}"></c:out></td>
   			<td><c:out value="${requestScope.memo.date}"/></td>
   			<td><c:out value="${requestScope.memo.content}"/></td>		
   			<td><c:out value="${requestScope.memo.attachment}"/></td>
   		</tr>
   		</tbody>
   	</table>
  </body>
</html>
