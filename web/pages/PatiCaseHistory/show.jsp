<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<link href="<c:url value="/styles/style.css"/>" type="text/css" rel="stylesheet">
	<title><%=PatiCaseHistory.TABLE_ALIAS%>信息</title>
</rapid:override>

<rapid:override name="content">
	<s:form action="/pages/PatiCaseHistory/list.do" method="get" theme="simple">
		<input type="button" value="返回列表" onclick="window.location='${ctx}/pages/PatiCaseHistory/list.do'"/>
		<input type="button" value="后退" onclick="history.back();"/>
		
		<s:hidden name="id" id="id" value="%{model.id}"/>
	
		<table class="tablelist">
			<tr>	
				<td class="tdLabel"><%=PatiCaseHistory.ALIAS_CASE_ID%></td>	
				<td><s:property value='%{model.caseId}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=PatiCaseHistory.ALIAS_STU_NUM%></td>	
				<td><s:property value='%{model.stuNum}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=PatiCaseHistory.ALIAS_VISIT_DATE%></td>	
				<td><s:property value="%{model.visitDateString}" /></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=PatiCaseHistory.ALIAS_DISPENSARY_RECORD%></td>	
				<td><s:property value='%{model.dispensaryRecord}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=PatiCaseHistory.ALIAS_ALLERGY%></td>	
				<td><s:property value='%{model.allergy}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=PatiCaseHistory.ALIAS_PRIMARY_CARE%></td>	
				<td><s:property value='%{model.primaryCare}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=PatiCaseHistory.ALIAS_RE_EXAMINATION%></td>	
				<td><s:property value='%{model.reExamination}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=PatiCaseHistory.ALIAS_DESCRIBES%></td>	
				<td><s:property value='%{model.describes}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=PatiCaseHistory.ALIAS_EXAMINE_STATUS%></td>	
				<td><s:property value='%{model.examineStatus}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=PatiCaseHistory.ALIAS_FIRST_IMPRESS%></td>	
				<td><s:property value='%{model.firstImpress}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=PatiCaseHistory.ALIAS_PRECRIPT_ID%></td>	
				<td><s:property value='%{model.precriptIdModel.modelTagValue}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=PatiCaseHistory.ALIAS_ME_ST_ID%></td>	
				<td><s:property value='%{model.meStIdModel.modelTagValue}'/>&nbsp;</td>
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