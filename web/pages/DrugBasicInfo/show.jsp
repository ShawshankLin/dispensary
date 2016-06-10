<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<link href="<c:url value="/styles/style.css"/>" type="text/css" rel="stylesheet">
	<title><%=DrugBasicInfo.TABLE_ALIAS%>信息</title>
</rapid:override>

<rapid:override name="content">
	<s:form action="/pages/DrugBasicInfo/list.do" method="get" theme="simple">
		<input type="button" value="返回列表" onclick="window.location='${ctx}/pages/DrugBasicInfo/list.do'"/>
		<input type="button" value="后退" onclick="history.back();"/>
		
		<s:hidden name="drugId" id="drugId" value="%{model.drugId}"/>
	
		<table class="tablelist">
			<tr>	
				<td class="tdLabel"><%=DrugBasicInfo.ALIAS_DRUG_NAME%></td>	
				<td><s:property value='%{model.drugName}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=DrugBasicInfo.ALIAS_DRUG_PINGYIN%></td>	
				<td><s:property value='%{model.drugPingyin}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=DrugBasicInfo.ALIAS_DRUG_EFFECT%></td>	
				<td><s:property value='%{model.drugEffectModel.modelTagValue}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=DrugBasicInfo.ALIAS_DRUG_KICK_BACK%></td>	
				<td><s:property value='%{model.drugKickBackModel.modelTagValue}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=DrugBasicInfo.ALIAS_DRUG_NOTE%></td>	
				<td><s:property value='%{model.drugNote}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=DrugBasicInfo.ALIAS_UNIT_ID%></td>	
				<td><s:property value='%{model.unitIdModel.modelTagValue}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=DrugBasicInfo.ALIAS_DRUG_TABU%></td>	
				<td><s:property value='%{model.drugTabu}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=DrugBasicInfo.ALIAS_COST_PRICE%></td>	
				<td><s:property value='%{model.costPrice}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=DrugBasicInfo.ALIAS_RETAIL_PRICE%></td>	
				<td><s:property value='%{model.retailPrice}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=DrugBasicInfo.ALIAS_PRODUCTION_DATE%></td>	
				<td><s:property value="%{model.productionDateString}" /></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=DrugBasicInfo.ALIAS_PERIOD_OF_VALIDITY%></td>	
				<td><s:property value='%{model.periodOfValidity}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=DrugBasicInfo.ALIAS_UP_LIMIT%></td>	
				<td><s:property value='%{model.upLimit}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=DrugBasicInfo.ALIAS_DOWN_LIMIT%></td>	
				<td><s:property value='%{model.downLimit}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=DrugBasicInfo.ALIAS_UP_LIMIT1%></td>	
				<td><s:property value='%{model.upLimit1}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=DrugBasicInfo.ALIAS_DOWN_LIMIT1%></td>	
				<td><s:property value='%{model.downLimit1}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=DrugBasicInfo.ALIAS_SYMPTOM_ID%></td>	
				<td><s:property value='%{model.symptomIdModel.modelTagValue}'/>&nbsp;</td>
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