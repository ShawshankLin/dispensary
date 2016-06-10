<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="drugPlaceId" name="drugPlaceId" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	
			<s:textfield label="%{@com.dispensary.project.model.DrugPlace@ALIAS_DRUG_PLACE}" key="drugPlace" value="%{model.drugPlace}" cssClass="" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.DrugPlace@ALIAS_STATUS}" key="status" value="%{model.status}" cssClass="" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.DrugPlace@ALIAS_PLACE_TYPE}" key="placeType" value="%{model.placeType}" cssClass="validate-integer max-value-2147483647" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.DrugPlace@ALIAS_IS_STOREROOM}" key="isStoreroom" value="%{model.isStoreroom}" cssClass="validate-integer max-value-2147483647" required="false" />
	
