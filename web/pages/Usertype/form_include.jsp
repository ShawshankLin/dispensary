<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="userTypeId" name="userTypeId" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	
			<s:textfield label="%{@com.dispensary.project.model.Usertype@ALIAS_USER_TYPE_NAME}" key="userTypeName" value="%{model.userTypeName}" cssClass="" required="false" />
	
