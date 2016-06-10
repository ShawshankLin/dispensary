<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<link href="<c:url value="/styles/style.css"/>" type="text/css" rel="stylesheet">
	<title><%=DrugStockIn.TABLE_ALIAS%>信息</title>
</rapid:override>

<rapid:override name="content">
	<s:form action="/pages/DrugStockIn/list.do" method="get" theme="simple">
		<input type="button" value="返回列表" onclick="window.location='${ctx}/pages/DrugStockIn/list.do'"/>
		<input type="button" value="后退" onclick="history.back();"/>
		
		<s:hidden name="id" id="id" value="%{model.id}"/>
	
		<table class="tablelist">
			<tr>	
				<td class="tdLabel"><%=DrugStockIn.ALIAS_SERIAL_NUMBER%></td>	
				<td><s:property value='%{model.serialNumber}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=DrugStockIn.ALIAS_STOCK_IN_ID%></td>	
				<td><s:property value='%{model.stockInId}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=DrugStockIn.ALIAS_IN_DATE%></td>	
				<td><s:property value='%{model.inDate}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=DrugStockIn.ALIAS_OPERATOR_ID%></td>	
				<td><s:property value='%{model.operatorIdModel.modelTagValue}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=DrugStockIn.ALIAS_SUPPLIER_ID%></td>	
				<td><s:property value='%{model.supplierIdModel.modelTagValue}'/>&nbsp;</td>
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