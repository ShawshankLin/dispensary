<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="id" name="id" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	
	
	
	
	
			<s:textfield label="%{@com.dispensary.project.model.PrescriptionInfoDetail@ALIAS_AMOUNT}" key="amount" value="%{model.amount}" cssClass="required validate-integer max-value-2147483647" required="true" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.PrescriptionInfoDetail@ALIAS_TIMES}" key="times" value="%{model.times}" cssClass="" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.PrescriptionInfoDetail@ALIAS_DAYS}" key="days" value="%{model.days}" cssClass="validate-integer max-value-2147483647" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.PrescriptionInfoDetail@ALIAS_DRUG_SUM}" key="drugSum" value="%{model.drugSum}" cssClass="required validate-integer " required="true" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.PrescriptionInfoDetail@ALIAS_NOTE}" key="note" value="%{model.note}" cssClass="" required="false" />
	
		<s:select label="%{@com.dispensary.project.model.PrescriptionInfoDetail@ALIAS_DRUG_ID}" key="drugId" list="%{#attr.drugBasicInfoList }" listKey="id" listValue="drugName" value="%{model.drugId}" />
		<s:select label="%{@com.dispensary.project.model.PrescriptionInfoDetail@ALIAS_PRES_ID}" key="presId" list="%{#attr.prescriptionInfoList }" listKey="id" listValue="id" value="%{model.presId}" />
