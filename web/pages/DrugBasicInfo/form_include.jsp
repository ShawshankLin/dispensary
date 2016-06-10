<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="drugId" name="drugId" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	
			<s:textfield label="%{@com.dispensary.project.model.DrugBasicInfo@ALIAS_DRUG_NAME}" key="drugName" value="%{model.drugName}" cssClass="" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.DrugBasicInfo@ALIAS_DRUG_PINGYIN}" key="drugPingyin" value="%{model.drugPingyin}" cssClass="" required="false" />
	
	
	
	
	
	
			<s:textfield label="%{@com.dispensary.project.model.DrugBasicInfo@ALIAS_DRUG_NOTE}" key="drugNote" value="%{model.drugNote}" cssClass="" required="false" />
	
	
	
	
			<s:textfield label="%{@com.dispensary.project.model.DrugBasicInfo@ALIAS_DRUG_TABU}" key="drugTabu" value="%{model.drugTabu}" cssClass="" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.DrugBasicInfo@ALIAS_COST_PRICE}" key="costPrice" value="%{model.costPrice}" cssClass="validate-integer " required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.DrugBasicInfo@ALIAS_RETAIL_PRICE}" key="retailPrice" value="%{model.retailPrice}" cssClass="validate-integer " required="false" />
	
	
	<tr>	
		<td class="tdLabel">
			<%=DrugBasicInfo.ALIAS_PRODUCTION_DATE%>:
		</td>	
		<td>
		<input type="text" value="${model.productionDateString}" onclick="WdatePicker({dateFmt:'<%=DrugBasicInfo.FORMAT_PRODUCTION_DATE%>'})" id="productionDateString" name="productionDateString"   class="" />
		</td>
	</tr>
	
	
			<s:textfield label="%{@com.dispensary.project.model.DrugBasicInfo@ALIAS_PERIOD_OF_VALIDITY}" key="periodOfValidity" value="%{model.periodOfValidity}" cssClass="validate-integer max-value-2147483647" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.DrugBasicInfo@ALIAS_UP_LIMIT}" key="upLimit" value="%{model.upLimit}" cssClass="validate-integer max-value-2147483647" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.DrugBasicInfo@ALIAS_DOWN_LIMIT}" key="downLimit" value="%{model.downLimit}" cssClass="validate-integer max-value-2147483647" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.DrugBasicInfo@ALIAS_UP_LIMIT1}" key="upLimit1" value="%{model.upLimit1}" cssClass="validate-integer max-value-2147483647" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.DrugBasicInfo@ALIAS_DOWN_LIMIT1}" key="downLimit1" value="%{model.downLimit1}" cssClass="validate-integer max-value-2147483647" required="false" />
	
	
	
		<s:select label="%{@com.dispensary.project.model.DrugBasicInfo@ALIAS_SYMPTOM_ID}" key="symptomId" list="%{#attr.symptomList }" listKey="id" listValue="symptomName" value="%{model.symptomId}" />
		<s:select label="%{@com.dispensary.project.model.DrugBasicInfo@ALIAS_UNIT_ID}" key="unitId" list="%{#attr.drugunitList }" listKey="id" listValue="drugUnitName" value="%{model.unitId}" />
		<s:select label="%{@com.dispensary.project.model.DrugBasicInfo@ALIAS_DRUG_EFFECT}" key="drugEffect" list="%{#attr.drugunitList }" listKey="id" listValue="drugUnitName" value="%{model.drugEffect}" />
		<s:select label="%{@com.dispensary.project.model.DrugBasicInfo@ALIAS_DRUG_KICK_BACK}" key="drugKickBack" list="%{#attr.drugunitList }" listKey="id" listValue="drugUnitName" value="%{model.drugKickBack}" />
