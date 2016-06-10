<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<link href="<c:url value="/styles/style.css"/>" type="text/css" rel="stylesheet">
	<title><%=PrescriptionInfoDetail.TABLE_ALIAS%>信息</title>
</rapid:override>

<rapid:override name="content">
	<s:form action="/pages/PrescriptionInfoDetail/list.do" method="get" theme="simple">
		<input type="button" value="返回列表" onclick="window.location='${ctx}/pages/PrescriptionInfoDetail/list.do'"/>
		<input type="button" value="后退" onclick="history.back();"/>
		
		<s:hidden name="id" id="id" value="%{model.id}"/>
	
		<table class="tablelist">
			<tr>	
				<td class="tdLabel"><%=PrescriptionInfoDetail.ALIAS_PRES_ID%></td>	
				<td><s:property value='%{model.presIdModel.modelTagValue}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=PrescriptionInfoDetail.ALIAS_DRUG_ID%></td>	
				<td><s:property value='%{model.drugIdModel.modelTagValue}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=PrescriptionInfoDetail.ALIAS_AMOUNT%></td>	
				<td><s:property value='%{model.amount}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=PrescriptionInfoDetail.ALIAS_TIMES%></td>	
				<td><s:property value='%{model.times}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=PrescriptionInfoDetail.ALIAS_DAYS%></td>	
				<td><s:property value='%{model.days}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=PrescriptionInfoDetail.ALIAS_DRUG_SUM%></td>	
				<td><s:property value='%{model.drugSum}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=PrescriptionInfoDetail.ALIAS_NOTE%></td>	
				<td><s:property value='%{model.note}'/>&nbsp;</td>
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