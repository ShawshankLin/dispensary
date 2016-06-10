<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="id" name="id" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	
	
	
			<s:textfield label="%{@com.dispensary.project.model.Memo@ALIAS_TITLE}" key="title" value="%{model.title}" cssClass="" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.Memo@ALIAS_CONTENT}" key="content" value="%{model.content}" cssClass="" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.Memo@ALIAS_ATTACHMENT}" key="attachment" value="%{model.attachment}" cssClass="" required="false" />
	
		<s:select label="%{@com.dispensary.project.model.Memo@ALIAS_USER_ID}" key="userId" list="%{#attr.userinfoList }" listKey="id" listValue="userName" value="%{model.userId}" />
