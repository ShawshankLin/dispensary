<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="id" name="id" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	
	
	
	
		<s:select label="%{@com.dispensary.project.model.DrugSymptomRelation@ALIAS_SYMPTOM_ID}" key="symptomId" list="%{#attr.symptomList }" listKey="id" listValue="symptomName" value="%{model.symptomId}" />
		<s:select label="%{@com.dispensary.project.model.DrugSymptomRelation@ALIAS_DRUG_ID}" key="drugId" list="%{#attr.drugBasicInfoList }" listKey="id" listValue="drugName" value="%{model.drugId}" />
