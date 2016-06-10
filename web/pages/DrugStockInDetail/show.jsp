<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<link href="<c:url value="/styles/style.css"/>" type="text/css" rel="stylesheet">
	<title><%=DrugStockInDetail.TABLE_ALIAS%>信息</title>
</rapid:override>

<rapid:override name="content">
	<s:form action="/pages/DrugStockInDetail/list.do" method="get" theme="simple">
		<input type="button" value="返回列表" onclick="window.location='${ctx}/pages/DrugStockInDetail/list.do'"/>
		<input type="button" value="后退" onclick="history.back();"/>
		
		<s:hidden name="id" id="id" value="%{model.id}"/>
	
		<table class="tablelist">
			<tr>	
				<td class="tdLabel"><%=DrugStockInDetail.ALIAS_STOCK_IN_ID%></td>	
				<td><s:property value='%{model.stockInId}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=DrugStockInDetail.ALIAS_SERIAL_NUMBER%></td>	
				<td><s:property value='%{model.serialNumberModel.modelTagValue}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=DrugStockInDetail.ALIAS_DRUG_ID%></td>	
				<td><s:property value='%{model.drugIdModel.modelTagValue}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=DrugStockInDetail.ALIAS_IN_PLACE_ID%></td>	
				<td><s:property value='%{model.inPlaceIdModel.modelTagValue}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=DrugStockInDetail.ALIAS_IN_PRICE%></td>	
				<td><s:property value='%{model.inPrice}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=DrugStockInDetail.ALIAS_IN_DATE%></td>	
				<td><s:property value='%{model.inDate}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=DrugStockInDetail.ALIAS_IN_STOCKS%></td>	
				<td><s:property value='%{model.inStocks}'/>&nbsp;</td>
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