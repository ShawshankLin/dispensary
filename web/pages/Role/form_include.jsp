<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="roleId" name="roleId" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	
			<s:textfield label="%{@com.dispensary.project.model.Role@ALIAS_ROLE_NAME}" key="roleName" value="%{model.roleName}" cssClass="" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.Role@ALIAS_ROLE_DESCRIBE}" key="roleDescribe" value="%{model.roleDescribe}" cssClass="" required="false" />
	
