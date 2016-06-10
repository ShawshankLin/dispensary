<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title><%=PatiCaseHistory.TABLE_ALIAS%> 维护</title>
	
	<link href="<c:url value="/widgets/simpletable/simpletable.css"/>" type="text/css" rel="stylesheet">
	<link href="<c:url value="/styles/style.css"/>" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="<c:url value="/widgets/simpletable/simpletable.js"/>"></script>
	
	<script type="text/javascript" >
		$(document).ready(function() {
			// 分页需要依赖的初始化动作
			window.simpleTable = new SimpleTable('queryForm',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
		});
	</script>
</rapid:override>

<rapid:override name="content">
	<form id="queryForm" name="queryForm" action="<c:url value="/pages/PatiCaseHistory/list.do"/>" method="post" style="display: inline;">
	<div class="queryPanel">
		<fieldset>
			<legend>搜索</legend>
			<table>
				<tr>	
					<td class="tdLabel"><%=PatiCaseHistory.ALIAS_CASE_ID%></td>		
					<td>
						<input type="text" value="${query.caseId}" id="caseId" name="caseId" maxlength="20"  class=""/>
					</td>
					<td class="tdLabel"><%=PatiCaseHistory.ALIAS_STU_NUM%></td>		
					<td>
							<input type="text" value="${query.stuNumModelTag}" id="stuNumModelTag" name="stuNumModelTag"/>
					</td>
					<td class="tdLabel"><%=PatiCaseHistory.ALIAS_VISIT_DATE%></td>		
					<td>
						<input type="text" value="<fmt:formatDate value='${query.visitDateBegin}' pattern='<%=PatiCaseHistory.FORMAT_VISIT_DATE%>'/>" onclick="WdatePicker({dateFmt:'<%=PatiCaseHistory.FORMAT_VISIT_DATE%>'})" id="visitDateBegin" name="visitDateBegin"   />
						<input type="text" value="<fmt:formatDate value='${query.visitDateEnd}' pattern='<%=PatiCaseHistory.FORMAT_VISIT_DATE%>'/>" onclick="WdatePicker({dateFmt:'<%=PatiCaseHistory.FORMAT_VISIT_DATE%>'})" id="visitDateEnd" name="visitDateEnd"   />
					</td>
					<td class="tdLabel"><%=PatiCaseHistory.ALIAS_DISPENSARY_RECORD%></td>		
					<td>
						<input type="text" value="${query.dispensaryRecord}" id="dispensaryRecord" name="dispensaryRecord" maxlength="255"  class=""/>
					</td>
				</tr>	
				<tr>	
					<td class="tdLabel"><%=PatiCaseHistory.ALIAS_ALLERGY%></td>		
					<td>
						<input type="text" value="${query.allergy}" id="allergy" name="allergy" maxlength="255"  class=""/>
					</td>
					<td class="tdLabel"><%=PatiCaseHistory.ALIAS_PRIMARY_CARE%></td>		
					<td>
						<input type="text" value="${query.primaryCare}" id="primaryCare" name="primaryCare" maxlength="255"  class=""/>
					</td>
					<td class="tdLabel"><%=PatiCaseHistory.ALIAS_RE_EXAMINATION%></td>		
					<td>
						<input type="text" value="${query.reExamination}" id="reExamination" name="reExamination" maxlength="255"  class=""/>
					</td>
					<td class="tdLabel"><%=PatiCaseHistory.ALIAS_DESCRIBES%></td>		
					<td>
						<input type="text" value="${query.describes}" id="describes" name="describes" maxlength="255"  class=""/>
					</td>
				</tr>	
				<tr>	
					<td class="tdLabel"><%=PatiCaseHistory.ALIAS_EXAMINE_STATUS%></td>		
					<td>
						<input type="text" value="${query.examineStatus}" id="examineStatus" name="examineStatus" maxlength="255"  class=""/>
					</td>
					<td class="tdLabel"><%=PatiCaseHistory.ALIAS_FIRST_IMPRESS%></td>		
					<td>
						<input type="text" value="${query.firstImpress}" id="firstImpress" name="firstImpress" maxlength="255"  class=""/>
					</td>
					<td class="tdLabel"><%=PatiCaseHistory.ALIAS_ME_ST_ID%></td>		
					<td>
							<input type="text" value="${query.meStIdModelTag}" id="meStIdModelTag" name="meStIdModelTag"/>
					</td>
				</tr>	
			</table>
		</fieldset>
		<div class="handleControl">
			<input type="submit" class="stdButton" style="width:80px" value="查询" onclick="getReferenceForm(this).action='${ctx}/pages/PatiCaseHistory/list.do'"/>
			<input type="submit" class="stdButton" style="width:80px" value="新增" onclick="getReferenceForm(this).action='${ctx}/pages/PatiCaseHistory/create.do'"/>
			<input type="button" class="stdButton" style="width:80px" value="删除" onclick="batchDelete('${ctx}/pages/PatiCaseHistory/delete.do','items',document.forms.queryForm)"/>
		</div>
	</div>
	
	<div class="gridTable">
	
		<simpletable:pageToolbar page="${page}">
		<s:property value="@com.dispensary.project.model.PatiCaseHistory@TABLE_ALIAS"/>列表
		</simpletable:pageToolbar>
	
		<table width="100%"  border="0" cellspacing="0" class="tablelist">
		  <thead>
			  
			  <tr>
				<th style="width:50px;"> </th>
				<th style="width:50px;"><input type="checkbox" onclick="setAllCheckboxState('items',this.checked)"></th>
				
				<!-- 排序时为th增加sortColumn即可,new SimpleTable('sortColumns')会为tableHeader自动增加排序功能; -->
				<th sortColumn="Case_ID" ><%=PatiCaseHistory.ALIAS_CASE_ID%></th>
				<th sortColumn="StuNum" ><%=PatiCaseHistory.ALIAS_STU_NUM%></th>
				<th sortColumn="VisitDate" ><%=PatiCaseHistory.ALIAS_VISIT_DATE%></th>
				<th sortColumn="DispensaryRecord" ><%=PatiCaseHistory.ALIAS_DISPENSARY_RECORD%></th>
				<th sortColumn="allergy" ><%=PatiCaseHistory.ALIAS_ALLERGY%></th>
				<th sortColumn="primary_care" ><%=PatiCaseHistory.ALIAS_PRIMARY_CARE%></th>
				<th sortColumn="re_examination" ><%=PatiCaseHistory.ALIAS_RE_EXAMINATION%></th>
				<th sortColumn="Describes" ><%=PatiCaseHistory.ALIAS_DESCRIBES%></th>
				<th sortColumn="ExamineStatus" ><%=PatiCaseHistory.ALIAS_EXAMINE_STATUS%></th>
				<th sortColumn="FirstImpress" ><%=PatiCaseHistory.ALIAS_FIRST_IMPRESS%></th>
				<th sortColumn="MeStID" ><%=PatiCaseHistory.ALIAS_ME_ST_ID%></th>
	
				<th>操作</th>
			  </tr>
			  
		  </thead>
		  <tbody>
		  	  <c:forEach items="${page.result}" var="item" varStatus="status">
		  	  
			  <tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
				<td>${page.thisPageFirstElementNumber + status.index}</td>
				<td><input type="checkbox" name="items" value="id=${item.id}&"></td>
				
				<td><c:out value='${item.caseId}'/>&nbsp;</td>
				<td><c:out value='${item.stuNumModel.modelTagValue}'/>&nbsp;</td>
				<td><c:out value='${item.visitDateString}'/>&nbsp;</td>
				<td><c:out value='${item.dispensaryRecord}'/>&nbsp;</td>
				<td><c:out value='${item.allergy}'/>&nbsp;</td>
				<td><c:out value='${item.primaryCare}'/>&nbsp;</td>
				<td><c:out value='${item.reExamination}'/>&nbsp;</td>
				<td><c:out value='${item.describes}'/>&nbsp;</td>
				<td><c:out value='${item.examineStatus}'/>&nbsp;</td>
				<td><c:out value='${item.firstImpress}'/>&nbsp;</td>
				<td><c:out value='${item.meStIdModel.modelTagValue}'/>&nbsp;</td>
				<td>
					<a href="${ctx}/pages/PatiCaseHistory/show.do?id=${item.id}&">查看</a>&nbsp;&nbsp;&nbsp;
					<a href="${ctx}/pages/PatiCaseHistory/edit.do?id=${item.id}&">修改</a>
				</td>
			  </tr>
			  
		  	  </c:forEach>
		  </tbody>
		</table>
	
		<simpletable:pageToolbar page="${page}">
		<s:property value="@com.dispensary.project.model.PatiCaseHistory@TABLE_ALIAS"/>列表
		</simpletable:pageToolbar>
	</div>
	</form>
</rapid:override>


<%@ include file="base.jsp" %>

