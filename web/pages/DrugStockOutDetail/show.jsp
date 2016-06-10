<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<link href="<c:url value="/styles/style.css"/>" type="text/css" rel="stylesheet">
	<title><%=DrugStockOutDetail.TABLE_ALIAS%>信息</title>
</rapid:override>

<rapid:override name="content">
	<s:form action="/pages/DrugStockOutDetail/list.do" method="get" theme="simple">
		<input type="button" value="返回列表" onclick="window.location='${ctx}/pages/DrugStockOutDetail/list.do'"/>
		<input type="button" value="后退" onclick="history.back();"/>
		
		<s:hidden name="id" id="id" value="%{model.id}"/>
	
		<table class="tablelist">
			<tr>	
				<td class="tdLabel"><%=DrugStockOutDetail.ALIAS_STOCK_OUT_ID%></td>	
				<td><s:property value='%{model.stockOutId}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=DrugStockOutDetail.ALIAS_SERIAL_NUMBER%></td>	
				<td><s:property value='%{model.serialNumberModel.modelTagValue}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=DrugStockOutDetail.ALIAS_DRUG_ID%></td>	
				<td><s:property value='%{model.drugId}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=DrugStockOutDetail.ALIAS_OUT_DATE%></td>	
				<td><s:property value='%{model.outDate}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=DrugStockOutDetail.ALIAS_OUT_AMOUNT%></td>	
				<td><s:property value='%{model.outAmount}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=DrugStockOutDetail.ALIAS_OUT_PLACE_ID%></td>	
				<td><s:property value='%{model.outPlaceIdModel.modelTagValue}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=DrugStockOutDetail.ALIAS_FROM_PLACE_ID%></td>	
				<td><s:property value='%{model.fromPlaceIdModel.modelTagValue}'/>&nbsp;</td>
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