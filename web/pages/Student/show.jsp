<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<link href="<c:url value="/styles/style.css"/>" type="text/css" rel="stylesheet">
	<title><%=Student.TABLE_ALIAS%>信息</title>
</rapid:override>

<rapid:override name="content">
	<s:form action="/pages/Student/list.do" method="get" theme="simple">
		<input type="button" value="返回列表" onclick="window.location='${ctx}/pages/Student/list.do'"/>
		<input type="button" value="后退" onclick="history.back();"/>
		
		<s:hidden name="stuNum" id="stuNum" value="%{model.stuNum}"/>
	
		<table class="tablelist">
			<tr>	
				<td class="tdLabel"><%=Student.ALIAS_STU_NAME%></td>	
				<td><s:property value='%{model.stuName}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Student.ALIAS_SEX%></td>	
				<td><s:property value='%{model.sex}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Student.ALIAS_BIRTH_DATE%></td>	
				<td><s:property value="%{model.birthDateString}" /></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Student.ALIAS_IDCARD%></td>	
				<td><s:property value='%{model.idcard}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Student.ALIAS_TEL%></td>	
				<td><s:property value='%{model.tel}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Student.ALIAS_DEPARTMENT%></td>	
				<td><s:property value='%{model.department}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Student.ALIAS_MAJOR%></td>	
				<td><s:property value='%{model.major}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Student.ALIAS_STU_CLASS%></td>	
				<td><s:property value='%{model.stuClass}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Student.ALIAS_ADDRESS%></td>	
				<td><s:property value='%{model.address}'/>&nbsp;</td>
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