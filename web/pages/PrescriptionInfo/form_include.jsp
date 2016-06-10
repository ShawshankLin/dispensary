<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="id" name="id" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	
			<s:textfield label="%{@com.dispensary.project.model.PrescriptionInfo@ALIAS_PRES_ID}" key="presId" value="%{model.presId}" cssClass="" required="false" />
	
	
	
	
			<s:textfield label="%{@com.dispensary.project.model.PrescriptionInfo@ALIAS_AMOUNT}" key="amount" value="%{model.amount}" cssClass="validate-number " required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.PrescriptionInfo@ALIAS_USAGES}" key="usages" value="%{model.usages}" cssClass="" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.PrescriptionInfo@ALIAS_DRUG_SUM}" key="drugSum" value="%{model.drugSum}" cssClass="validate-integer " required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.PrescriptionInfo@ALIAS_IS_DRUG}" key="isDrug" value="%{model.isDrug}" cssClass="validate-integer max-value-2147483647" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.PrescriptionInfo@ALIAS_SELF_PAY}" key="selfPay" value="%{model.selfPay}" cssClass="validate-integer " required="false" />
	
	
	
		<s:select label="%{@com.dispensary.project.model.PrescriptionInfo@ALIAS_QUANTITY_UNIT}" key="quantityUnit" list="%{#attr.drugunitList }" listKey="id" listValue="drugUnitName" value="%{model.quantityUnit}" />
		<s:select label="%{@com.dispensary.project.model.PrescriptionInfo@ALIAS_DRUG_ID}" key="drugId" list="%{#attr.drugBasicInfoList }" listKey="id" listValue="drugName" value="%{model.drugId}" />
