<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="id" name="id" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	
			<s:textfield label="%{@com.dispensary.project.model.DrugStockOutDetail@ALIAS_STOCK_OUT_ID}" key="stockOutId" value="%{model.stockOutId}" cssClass="required " required="true" />
	
	
	
	
			<s:textfield label="%{@com.dispensary.project.model.DrugStockOutDetail@ALIAS_DRUG_ID}" key="drugId" value="%{model.drugId}" cssClass="validate-integer max-value-2147483647" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.DrugStockOutDetail@ALIAS_OUT_DATE}" key="outDate" value="%{model.outDate}" cssClass="" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.DrugStockOutDetail@ALIAS_OUT_AMOUNT}" key="outAmount" value="%{model.outAmount}" cssClass="validate-number " required="false" />
	
	
	
	
	
		<s:select label="%{@com.dispensary.project.model.DrugStockOutDetail@ALIAS_SERIAL_NUMBER}" key="serialNumber" list="%{#attr.drugStockOutList }" listKey="id" listValue="id" value="%{model.serialNumber}" />
		<s:select label="%{@com.dispensary.project.model.DrugStockOutDetail@ALIAS_OUT_PLACE_ID}" key="outPlaceId" list="%{#attr.drugPlaceList }" listKey="id" listValue="id" value="%{model.outPlaceId}" />
		<s:select label="%{@com.dispensary.project.model.DrugStockOutDetail@ALIAS_FROM_PLACE_ID}" key="fromPlaceId" list="%{#attr.drugPlaceList }" listKey="id" listValue="id" value="%{model.fromPlaceId}" />
