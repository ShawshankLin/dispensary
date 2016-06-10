<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="drugUnitId" name="drugUnitId" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	
			<s:textfield label="%{@com.dispensary.project.model.Drugunit@ALIAS_DRUG_UNIT_NAME}" key="drugUnitName" value="%{model.drugUnitName}" cssClass="" required="false" />
	
