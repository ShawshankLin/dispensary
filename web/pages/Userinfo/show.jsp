<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<link href="<c:url value="/styles/style.css"/>" type="text/css" rel="stylesheet">
	<title><%=Userinfo.TABLE_ALIAS%>信息</title>
</rapid:override>

<rapid:override name="content">
	<s:form action="/pages/Userinfo/list.do" method="get" theme="simple">
		<input type="button" value="返回列表" onclick="window.location='${ctx}/pages/Userinfo/list.do'"/>
		<input type="button" value="后退" onclick="history.back();"/>
		
		<s:hidden name="userId" id="userId" value="%{model.userId}"/>
	
		<table class="tablelist">
			<tr>	
				<td class="tdLabel"><%=Userinfo.ALIAS_ME_ST_ID%></td>	
				<td><s:property value='%{model.meStIdModel.modelTagValue}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Userinfo.ALIAS_PASSWORD%></td>	
				<td><s:property value='%{model.password}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Userinfo.ALIAS_USER_NAME%></td>	
				<td><s:property value='%{model.userName}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Userinfo.ALIAS_IF_VALIDITY%></td>	
				<td><s:property value='%{model.ifValidity}'/>&nbsp;</td>
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