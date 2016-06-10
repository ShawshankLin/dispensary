<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="id" name="id" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	
	
	
			<s:textfield label="%{@com.dispensary.project.model.Notice@ALIAS_TITLE}" key="title" value="%{model.title}" cssClass="required " required="true" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.Notice@ALIAS_CONTENT}" key="content" value="%{model.content}" cssClass="required " required="true" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.Notice@ALIAS_ATTACHMENT}" key="attachment" value="%{model.attachment}" cssClass="" required="false" />
	
	
	<tr>	
		<td class="tdLabel">
			<%=Notice.ALIAS_DATE%>:
		</td>	
		<td>
		<input type="text" value="${model.dateString}" onclick="WdatePicker({dateFmt:'<%=Notice.FORMAT_DATE%>'})" id="dateString" name="dateString"   class="" />
		</td>
	</tr>
	
	
			<s:textfield label="%{@com.dispensary.project.model.Notice@ALIAS_STATUS}" key="status" value="%{model.status}" cssClass="required validate-integer max-value-2147483647" required="true" />
	
		<s:select label="%{@com.dispensary.project.model.Notice@ALIAS_USER_ID}" key="userId" list="%{#attr.userinfoList }" listKey="id" listValue="userName" value="%{model.userId}" />
