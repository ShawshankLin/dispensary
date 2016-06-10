<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="id" name="id" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	
	
	
	
	
			<s:textfield label="%{@com.dispensary.project.model.DrugStockInfo@ALIAS_CUR_AMOUNT}" key="curAmount" value="%{model.curAmount}" cssClass="validate-number " required="false" />
	
		<s:select label="%{@com.dispensary.project.model.DrugStockInfo@ALIAS_DRUG_ID}" key="drugId" list="%{#attr.drugBasicInfoList }" listKey="id" listValue="drugName" value="%{model.drugId}" />
		<s:select label="%{@com.dispensary.project.model.DrugStockInfo@ALIAS_DRUG_PLACE_ID}" key="drugPlaceId" list="%{#attr.drugPlaceList }" listKey="id" listValue="id" value="%{model.drugPlaceId}" />
