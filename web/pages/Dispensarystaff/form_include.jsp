<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="meStId" name="meStId" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	
			<s:textfield label="%{@com.dispensary.project.model.Dispensarystaff@ALIAS_ME_ST_NAME}" key="meStName" value="%{model.meStName}" cssClass="" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.Dispensarystaff@ALIAS_SEX}" key="sex" value="%{model.sex}" cssClass="validate-integer max-value-2147483647" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.Dispensarystaff@ALIAS_AGE}" key="age" value="%{model.age}" cssClass="validate-integer max-value-2147483647" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.Dispensarystaff@ALIAS_EDUCATION}" key="education" value="%{model.education}" cssClass="" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.Dispensarystaff@ALIAS_ADDRESS}" key="address" value="%{model.address}" cssClass="" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.Dispensarystaff@ALIAS_TEL}" key="tel" value="%{model.tel}" cssClass="" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.Dispensarystaff@ALIAS_MOBILE}" key="mobile" value="%{model.mobile}" cssClass="" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.Dispensarystaff@ALIAS_EMAIL}" key="email" value="%{model.email}" cssClass="validate-email " required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.Dispensarystaff@ALIAS_NOTE}" key="note" value="%{model.note}" cssClass="" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.Dispensarystaff@ALIAS_WORK_PLACE}" key="workPlace" value="%{model.workPlace}" cssClass="" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.Dispensarystaff@ALIAS_USER_ID}" key="userId" value="%{model.userId}" cssClass="" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.Dispensarystaff@ALIAS_USER_TYPE_ID}" key="userTypeId" value="%{model.userTypeId}" cssClass="" required="false" />
	
