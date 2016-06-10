<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="symptomId" name="symptomId" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	
			<s:textfield label="%{@com.dispensary.project.model.Symptom@ALIAS_SYMPTOM_NAME}" key="symptomName" value="%{model.symptomName}" cssClass="" required="false" />
	
