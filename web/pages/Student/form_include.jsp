<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="stuNum" name="stuNum" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	
			<s:textfield label="%{@com.dispensary.project.model.Student@ALIAS_STU_NAME}" key="stuName" value="%{model.stuName}" cssClass="" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.Student@ALIAS_SEX}" key="sex" value="%{model.sex}" cssClass="validate-integer max-value-2147483647" required="false" />
	
	
	<tr>	
		<td class="tdLabel">
			<%=Student.ALIAS_BIRTH_DATE%>:
		</td>	
		<td>
		<input type="text" value="${model.birthDateString}" onclick="WdatePicker({dateFmt:'<%=Student.FORMAT_BIRTH_DATE%>'})" id="birthDateString" name="birthDateString"   class="" />
		</td>
	</tr>
	
	
			<s:textfield label="%{@com.dispensary.project.model.Student@ALIAS_IDCARD}" key="idcard" value="%{model.idcard}" cssClass="" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.Student@ALIAS_TEL}" key="tel" value="%{model.tel}" cssClass="" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.Student@ALIAS_DEPARTMENT}" key="department" value="%{model.department}" cssClass="" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.Student@ALIAS_MAJOR}" key="major" value="%{model.major}" cssClass="" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.Student@ALIAS_STU_CLASS}" key="stuClass" value="%{model.stuClass}" cssClass="" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.Student@ALIAS_ADDRESS}" key="address" value="%{model.address}" cssClass="" required="false" />
	
