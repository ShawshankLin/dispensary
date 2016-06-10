<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="id" name="id" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	
			<s:textfield label="%{@com.dispensary.project.model.DrugStockIn@ALIAS_SERIAL_NUMBER}" key="serialNumber" value="%{model.serialNumber}" cssClass="required " required="true" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.DrugStockIn@ALIAS_STOCK_IN_ID}" key="stockInId" value="%{model.stockInId}" cssClass="required " required="true" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.DrugStockIn@ALIAS_IN_DATE}" key="inDate" value="%{model.inDate}" cssClass="" required="false" />
	
	
	
	
	
		<s:select label="%{@com.dispensary.project.model.DrugStockIn@ALIAS_OPERATOR_ID}" key="operatorId" list="%{#attr.dispensarystaffList }" listKey="id" listValue="meStName" value="%{model.operatorId}" />
		<s:select label="%{@com.dispensary.project.model.DrugStockIn@ALIAS_SUPPLIER_ID}" key="supplierId" list="%{#attr.supplierList }" listKey="id" listValue="supplierName" value="%{model.supplierId}" />
