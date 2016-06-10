<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<link href="<c:url value="/styles/style.css"/>" type="text/css" rel="stylesheet">
	<title><%=Dispensarystaff.TABLE_ALIAS%>信息</title>
</rapid:override>

<rapid:override name="content">
	<s:form action="/pages/Dispensarystaff/list.do" method="get" theme="simple">
		<input type="button" value="返回列表" onclick="window.location='${ctx}/pages/Dispensarystaff/list.do'"/>
		<input type="button" value="后退" onclick="history.back();"/>
		
		<s:hidden name="meStId" id="meStId" value="%{model.meStId}"/>
	
		<table class="tablelist">
			<tr>	
				<td class="tdLabel"><%=Dispensarystaff.ALIAS_ME_ST_NAME%></td>	
				<td><s:property value='%{model.meStName}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Dispensarystaff.ALIAS_SEX%></td>	
				<td><s:property value='%{model.sex}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Dispensarystaff.ALIAS_AGE%></td>	
				<td><s:property value='%{model.age}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Dispensarystaff.ALIAS_EDUCATION%></td>	
				<td><s:property value='%{model.education}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Dispensarystaff.ALIAS_ADDRESS%></td>	
				<td><s:property value='%{model.address}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Dispensarystaff.ALIAS_TEL%></td>	
				<td><s:property value='%{model.tel}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Dispensarystaff.ALIAS_MOBILE%></td>	
				<td><s:property value='%{model.mobile}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Dispensarystaff.ALIAS_EMAIL%></td>	
				<td><s:property value='%{model.email}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Dispensarystaff.ALIAS_NOTE%></td>	
				<td><s:property value='%{model.note}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Dispensarystaff.ALIAS_WORK_PLACE%></td>	
				<td><s:property value='%{model.workPlace}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Dispensarystaff.ALIAS_USER_ID%></td>	
				<td><s:property value='%{model.userId}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Dispensarystaff.ALIAS_USER_TYPE_ID%></td>	
				<td><s:property value='%{model.userTypeId}'/>&nbsp;</td>
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