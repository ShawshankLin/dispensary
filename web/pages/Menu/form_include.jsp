<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="menuId" name="menuId" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	
			<s:textfield label="%{@com.dispensary.project.model.Menu@ALIAS_MENU_NAME}" key="menuName" value="%{model.menuName}" cssClass="" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.Menu@ALIAS_MENU_URL}" key="menuUrl" value="%{model.menuUrl}" cssClass="" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.Menu@ALIAS_PARENT}" key="parent" value="%{model.parent}" cssClass="" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.Menu@ALIAS_MENU_NO}" key="menuNo" value="%{model.menuNo}" cssClass="validate-integer max-value-2147483647" required="false" />
	
