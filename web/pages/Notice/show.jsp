<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<link href="<c:url value="/styles/style.css"/>" type="text/css" rel="stylesheet">
	<title><%=Notice.TABLE_ALIAS%>信息</title>
</rapid:override>

<rapid:override name="content">
	<s:form action="/pages/Notice/list.do" method="get" theme="simple">
		<input type="button" value="返回列表" onclick="window.location='${ctx}/pages/Notice/list.do'"/>
		<input type="button" value="后退" onclick="history.back();"/>
		
		<s:hidden name="id" id="id" value="%{model.id}"/>
	
		<table class="tablelist">
			<tr>	
				<td class="tdLabel"><%=Notice.ALIAS_USER_ID%></td>	
				<td><s:property value='%{model.userIdModel.modelTagValue}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Notice.ALIAS_TITLE%></td>	
				<td><s:property value='%{model.title}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Notice.ALIAS_CONTENT%></td>	
				<td><s:property value='%{model.content}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Notice.ALIAS_ATTACHMENT%></td>	
				<td><s:property value='%{model.attachment}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Notice.ALIAS_DATE%></td>	
				<td><s:property value="%{model.dateString}" /></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Notice.ALIAS_STATUS%></td>	
				<td><s:property value='%{model.status}'/>&nbsp;</td>
			</tr>
		</table>
	</s:form>
	<script>
		$(function(){
			$("tr:odd").addClass("odd");
			$("tr:even").addClass("even");
		});
	</script>
</rapid:override>


<%@ include file="base.jsp" %>