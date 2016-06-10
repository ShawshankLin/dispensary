<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="id" name="id" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	
			<s:textfield label="%{@com.dispensary.project.model.DrugStockOut@ALIAS_SERIAL_NUMBER}" key="serialNumber" value="%{model.serialNumber}" cssClass="required " required="true" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.DrugStockOut@ALIAS_STOCK_OUT_ID}" key="stockOutId" value="%{model.stockOutId}" cssClass="required " required="true" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.DrugStockOut@ALIAS_OUT_DATE}" key="outDate" value="%{model.outDate}" cssClass="" required="false" />
	
	
	
		<s:select label="%{@com.dispensary.project.model.DrugStockOut@ALIAS_OPERATOR_ID}" key="operatorId" list="%{#attr.medicalstaffList }" listKey="id" listValue="meStName" value="%{model.operatorId}" />
