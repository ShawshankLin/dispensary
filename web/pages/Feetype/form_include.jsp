<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="feeTypeId" name="feeTypeId" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	
			<s:textfield label="%{@com.dispensary.project.model.Feetype@ALIAS_FEE_TYPE_NAME}" key="feeTypeName" value="%{model.feeTypeName}" cssClass="" required="false" />
	
