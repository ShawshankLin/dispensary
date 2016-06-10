<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="id" name="id" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	
	
	
	
		<s:select label="%{@com.dispensary.project.model.UserRoleInfo@ALIAS_USER_ID}" key="userId" list="%{#attr.userinfoList }" listKey="id" listValue="userName" value="%{model.userId}" />
		<s:select label="%{@com.dispensary.project.model.UserRoleInfo@ALIAS_ROLE_ID}" key="roleId" list="%{#attr.roleList }" listKey="id" listValue="roleName" value="%{model.roleId}" />
