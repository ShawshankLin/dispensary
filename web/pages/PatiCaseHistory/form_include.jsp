<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="id" name="id" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	
			<s:textfield label="%{@com.dispensary.project.model.PatiCaseHistory@ALIAS_CASE_ID}" key="caseId" value="%{model.caseId}" cssClass="required validate-integer max-value-2147483647" required="true" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.PatiCaseHistory@ALIAS_STU_NUM}" key="stuNum" value="%{model.stuNum}" cssClass="required " required="true" />
	
	
	<tr>	
		<td class="tdLabel">
			<%=PatiCaseHistory.ALIAS_VISIT_DATE%>:
		</td>	
		<td>
		<input type="text" value="${model.visitDateString}" onclick="WdatePicker({dateFmt:'<%=PatiCaseHistory.FORMAT_VISIT_DATE%>'})" id="visitDateString" name="visitDateString"   class="" />
		</td>
	</tr>
	
	
			<s:textfield label="%{@com.dispensary.project.model.PatiCaseHistory@ALIAS_DISPENSARY_RECORD}" key="dispensaryRecord" value="%{model.dispensaryRecord}" cssClass="" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.PatiCaseHistory@ALIAS_ALLERGY}" key="allergy" value="%{model.allergy}" cssClass="" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.PatiCaseHistory@ALIAS_PRIMARY_CARE}" key="primaryCare" value="%{model.primaryCare}" cssClass="" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.PatiCaseHistory@ALIAS_RE_EXAMINATION}" key="reExamination" value="%{model.reExamination}" cssClass="" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.PatiCaseHistory@ALIAS_DESCRIBES}" key="describes" value="%{model.describes}" cssClass="" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.PatiCaseHistory@ALIAS_EXAMINE_STATUS}" key="examineStatus" value="%{model.examineStatus}" cssClass="" required="false" />
	
	
			<s:textfield label="%{@com.dispensary.project.model.PatiCaseHistory@ALIAS_FIRST_IMPRESS}" key="firstImpress" value="%{model.firstImpress}" cssClass="" required="false" />
	
	
	
	
	
		<s:select label="%{@com.dispensary.project.model.PatiCaseHistory@ALIAS_PRECRIPT_ID}" key="precriptId" list="%{#attr.prescriptionInfoList }" listKey="id" listValue="id" value="%{model.precriptId}" />
		<s:select label="%{@com.dispensary.project.model.PatiCaseHistory@ALIAS_ME_ST_ID}" key="meStId" list="%{#attr.dispensarystaffList }" listKey="id" listValue="meStName" value="%{model.meStId}" />
