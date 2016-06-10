<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<link href="<c:url value="/styles/style.css"/>" type="text/css" rel="stylesheet">
	<title><%=Power.TABLE_ALIAS%>信息</title>
</rapid:override>

<rapid:override name="content">
	<s:form action="/pages/Power/list.do" method="get" theme="simple">
		<input type="button" value="返回列表" onclick="window.location='${ctx}/pages/Power/list.do'"/>
		<input type="button" value="后退" onclick="history.back();"/>
		
		<s:hidden name="powerId" id="powerId" value="%{model.powerId}"/>
	
		<table class="tablelist">
			<tr>	
				<td class="tdLabel"><%=Power.ALIAS_POWER_NAME%></td>	
				<td><s:property value='%{model.powerName}'/>&nbsp;</td>
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