<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="userId" name="userId" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	
	
	
			<s:textfield label="%{@com.dispensary.project.model.Userinfo@ALIAS_PASSWORD}" key="password" value="%{model.password}" cssClass="" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.Userinfo@ALIAS_USER_NAME}" key="userName" value="%{model.userName}" cssClass="" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.Userinfo@ALIAS_IF_VALIDITY}" key="ifValidity" value="%{model.ifValidity}" cssClass="validate-integer max-value-2147483647" required="false" />
	
		<s:select label="%{@com.dispensary.project.model.Userinfo@ALIAS_ME_ST_ID}" key="meStId" list="%{#attr.medicalstaffList }" listKey="id" listValue="meStName" value="%{model.meStId}" />
