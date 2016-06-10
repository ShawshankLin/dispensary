<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<link href="<c:url value="/styles/style.css"/>" type="text/css" rel="stylesheet">
	<title><%=Role.TABLE_ALIAS%>信息</title>
</rapid:override>

<rapid:override name="content">
	<s:form action="/pages/Role/list.do" method="get" theme="simple">
		<input type="button" value="返回列表" onclick="window.location='${ctx}/pages/Role/list.do'"/>
		<input type="button" value="后退" onclick="history.back();"/>
		
		<s:hidden name="roleId" id="roleId" value="%{model.roleId}"/>
	
		<table class="tablelist">
			<tr>	
				<td class="tdLabel"><%=Role.ALIAS_ROLE_NAME%></td>	
				<td><s:property value='%{model.roleName}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Role.ALIAS_ROLE_DESCRIBE%></td>	
				<td><s:property value='%{model.roleDescribe}'/>&nbsp;</td>
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