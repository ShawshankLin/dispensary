<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="id" name="id" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	
			<s:textfield label="%{@com.dispensary.project.model.DrugStockInDetail@ALIAS_STOCK_IN_ID}" key="stockInId" value="%{model.stockInId}" cssClass="required " required="true" />
	
	
	
	
	
	
	
	
			<s:textfield label="%{@com.dispensary.project.model.DrugStockInDetail@ALIAS_IN_PRICE}" key="inPrice" value="%{model.inPrice}" cssClass="validate-integer " required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.DrugStockInDetail@ALIAS_IN_DATE}" key="inDate" value="%{model.inDate}" cssClass="" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.DrugStockInDetail@ALIAS_IN_STOCKS}" key="inStocks" value="%{model.inStocks}" cssClass="validate-number " required="false" />
	
		<s:select label="%{@com.dispensary.project.model.DrugStockInDetail@ALIAS_IN_PLACE_ID}" key="inPlaceId" list="%{#attr.drugPlaceList }" listKey="id" listValue="id" value="%{model.inPlaceId}" />
		<s:select label="%{@com.dispensary.project.model.DrugStockInDetail@ALIAS_SERIAL_NUMBER}" key="serialNumber" list="%{#attr.drugStockInList }" listKey="id" listValue="id" value="%{model.serialNumber}" />
		<s:select label="%{@com.dispensary.project.model.DrugStockInDetail@ALIAS_DRUG_ID}" key="drugId" list="%{#attr.drugBasicInfoList }" listKey="id" listValue="drugName" value="%{model.drugId}" />
