<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="recId" name="recId" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	
	
	
			<s:textfield label="%{@com.dispensary.project.model.Receipt@ALIAS_DRUG_SUM}" key="drugSum" value="%{model.drugSum}" cssClass="validate-integer " required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.Receipt@ALIAS_REGISTER_SUM}" key="registerSum" value="%{model.registerSum}" cssClass="validate-integer " required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.Receipt@ALIAS_SUM}" key="sum" value="%{model.sum}" cssClass="validate-integer " required="false" />
	
	
	
	
	<tr>	
		<td class="tdLabel">
			<%=Receipt.ALIAS_REC_DATE%>:
		</td>	
		<td>
		<input type="text" value="${model.recDateString}" onclick="WdatePicker({dateFmt:'<%=Receipt.FORMAT_REC_DATE%>'})" id="recDateString" name="recDateString"   class="" />
		</td>
	</tr>
	
		<s:select label="%{@com.dispensary.project.model.Receipt@ALIAS_ME_ST_ID}" key="meStId" list="%{#attr.dispensarystaffList }" listKey="id" listValue="meStName" value="%{model.meStId}" />
		<s:select label="%{@com.dispensary.project.model.Receipt@ALIAS_PRES_ID}" key="presId" list="%{#attr.prescriptionInfoList }" listKey="id" listValue="id" value="%{model.presId}" />
