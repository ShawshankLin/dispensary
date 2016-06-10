<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="supplierId" name="supplierId" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	
			<s:textfield label="%{@com.dispensary.project.model.Supplier@ALIAS_SUPPLIER_NAME}" key="supplierName" value="%{model.supplierName}" cssClass="" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.Supplier@ALIAS_CONTACTS}" key="contacts" value="%{model.contacts}" cssClass="" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.Supplier@ALIAS_PINGYIN}" key="pingyin" value="%{model.pingyin}" cssClass="" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.Supplier@ALIAS_ADDRESS}" key="address" value="%{model.address}" cssClass="" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.Supplier@ALIAS_USER_TEL}" key="userTel" value="%{model.userTel}" cssClass="" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.Supplier@ALIAS_USER_MOBILE}" key="userMobile" value="%{model.userMobile}" cssClass="" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.Supplier@ALIAS_USER_EMAIL}" key="userEmail" value="%{model.userEmail}" cssClass="validate-email " required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.Supplier@ALIAS_NOTE}" key="note" value="%{model.note}" cssClass="" required="false" />
	
