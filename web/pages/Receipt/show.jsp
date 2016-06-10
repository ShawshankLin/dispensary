<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<link href="<c:url value="/styles/style.css"/>" type="text/css" rel="stylesheet">
	<title><%=Receipt.TABLE_ALIAS%>信息</title>
</rapid:override>

<rapid:override name="content">
	<s:form action="/pages/Receipt/list.do" method="get" theme="simple">
		<input type="button" value="返回列表" onclick="window.location='${ctx}/pages/Receipt/list.do'"/>
		<input type="button" value="后退" onclick="history.back();"/>
		
		<s:hidden name="recId" id="recId" value="%{model.recId}"/>
	
		<table class="tablelist">
			<tr>	
				<td class="tdLabel"><%=Receipt.ALIAS_PRES_ID%></td>	
				<td><s:property value='%{model.presIdModel.modelTagValue}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Receipt.ALIAS_DRUG_SUM%></td>	
				<td><s:property value='%{model.drugSum}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Receipt.ALIAS_REGISTER_SUM%></td>	
				<td><s:property value='%{model.registerSum}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Receipt.ALIAS_SUM%></td>	
				<td><s:property value='%{model.sum}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Receipt.ALIAS_ME_ST_ID%></td>	
				<td><s:property value='%{model.meStIdModel.modelTagValue}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Receipt.ALIAS_REC_DATE%></td>	
				<td><s:property value="%{model.recDateString}" /></td>
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