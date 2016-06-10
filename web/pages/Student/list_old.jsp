<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title><%=Student.TABLE_ALIAS%> 维护</title>
	
	<link href="<c:url value="/widgets/simpletable/simpletable.css"/>" type="text/css" rel="stylesheet">
	<link href="<c:url value="/styles/style.css"/>" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="<c:url value="/widgets/simpletable/simpletable.js"/>"></script>	
	<script type="text/javascript" >
		$(document).ready(function() {
			// 分页需要依赖的初始化动作
			console.log(${page.thisPageNumber});
			console.log(${page.pageSize});
			window.simpleTable = new SimpleTable('queryForm',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
		});
	</script>
</rapid:override>

<rapid:override name="content">
	<form id="queryForm" name="queryForm" action="<c:url value="/pages/Student/list.do"/>" method="post" style="display: inline;">
	<div class="queryPanel">
		<fieldset>
			<legend>搜索</legend>
			<table>
				<tr>	
					<td class="tdLabel"><%=Student.ALIAS_STU_NAME%></td>		
					<td>
						<input type="text" value="${query.stuName}" id="stuName" name="stuName" maxlength="20"  class=""/>
					</td>
					<td class="tdLabel"><%=Student.ALIAS_SEX%></td>		
					<td>
						<input type="text" value="${query.sex}" id="sex" name="sex" maxlength="10"  class="validate-integer max-value-2147483647"/>
					</td>
					<td class="tdLabel"><%=Student.ALIAS_BIRTH_DATE%></td>		
					<td>
						<input type="text" value="<fmt:formatDate value='${query.birthDateBegin}' pattern='<%=Student.FORMAT_BIRTH_DATE%>'/>" onclick="WdatePicker({dateFmt:'<%=Student.FORMAT_BIRTH_DATE%>'})" id="birthDateBegin" name="birthDateBegin"   />
						<input type="text" value="<fmt:formatDate value='${query.birthDateEnd}' pattern='<%=Student.FORMAT_BIRTH_DATE%>'/>" onclick="WdatePicker({dateFmt:'<%=Student.FORMAT_BIRTH_DATE%>'})" id="birthDateEnd" name="birthDateEnd"   />
					</td>
					<td class="tdLabel"><%=Student.ALIAS_IDCARD%></td>		
					<td>
						<input type="text" value="${query.idcard}" id="idcard" name="idcard" maxlength="20"  class=""/>
					</td>
				</tr>	
				<tr>	
					<td class="tdLabel"><%=Student.ALIAS_TEL%></td>		
					<td>
						<input type="text" value="${query.tel}" id="tel" name="tel" maxlength="15"  class=""/>
					</td>
					<td class="tdLabel"><%=Student.ALIAS_DEPARTMENT%></td>		
					<td>
						<input type="text" value="${query.department}" id="department" name="department" maxlength="30"  class=""/>
					</td>
					<td class="tdLabel"><%=Student.ALIAS_MAJOR%></td>		
					<td>
						<input type="text" value="${query.major}" id="major" name="major" maxlength="30"  class=""/>
					</td>
					<td class="tdLabel"><%=Student.ALIAS_STU_CLASS%></td>		
					<td>
						<input type="text" value="${query.stuClass}" id="stuClass" name="stuClass" maxlength="50"  class=""/>
					</td>
				</tr>	
				<tr>	
					<td class="tdLabel"><%=Student.ALIAS_ADDRESS%></td>		
					<td>
						<input type="text" value="${query.address}" id="address" name="address" maxlength="50"  class=""/>
					</td>
				</tr>	
			</table>
		</fieldset>
		<div class="handleControl">
			<input type="submit" class="stdButton" style="width:80px" value="查询" onclick="getReferenceForm(this).action='${ctx}/pages/Student/list.do'"/>
			<input type="submit" class="stdButton" style="width:80px" value="新增" onclick="getReferenceForm(this).action='${ctx}/pages/Student/create.do'"/>
			<input type="button" class="stdButton" style="width:80px" value="删除" onclick="batchDelete('${ctx}/pages/Student/delete.do','items',document.forms.queryForm)"/>
		</div>
	</div>
	
	<div class="gridTable">
	
		<simpletable:pageToolbar page="${page}">
		<s:property value="@com.dispensary.project.model.Student@TABLE_ALIAS"/>列表
		</simpletable:pageToolbar>
	
		<table width="100%"  border="0" cellspacing="0" class="tablelist">
		  <thead>
			  
			  <tr>
				<th style="width:50px;"> </th>
				<th style="width:50px;"><input type="checkbox" onclick="setAllCheckboxState('items',this.checked)"></th>
				
				<!-- 排序时为th增加sortColumn即可,new SimpleTable('sortColumns')会为tableHeader自动增加排序功能; -->
				<th sortColumn="StuName" ><%=Student.ALIAS_STU_NAME%></th>
				<th sortColumn="Sex" ><%=Student.ALIAS_SEX%></th>
				<th sortColumn="BirthDate" ><%=Student.ALIAS_BIRTH_DATE%></th>
				<th sortColumn="IDCard" ><%=Student.ALIAS_IDCARD%></th>
				<th sortColumn="Tel" ><%=Student.ALIAS_TEL%></th>
				<th sortColumn="Department" ><%=Student.ALIAS_DEPARTMENT%></th>
				<th sortColumn="Major" ><%=Student.ALIAS_MAJOR%></th>
				<th sortColumn="StuClass" ><%=Student.ALIAS_STU_CLASS%></th>
				<th sortColumn="Address" ><%=Student.ALIAS_ADDRESS%></th>
	
				<th>操作</th>
			  </tr>
			  
		  </thead>
		  <tbody>
		  	  <c:forEach items="${page.result}" var="item" varStatus="status">
		  	  
			  <tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
				<td>${page.thisPageFirstElementNumber + status.index}</td>
				<td><input type="checkbox" name="items" value="stuNum=${item.stuNum}&"></td>
				
				<td><c:out value='${item.stuName}'/>&nbsp;</td>
				<td><c:out value='${item.sex}'/>&nbsp;</td>
				<td><c:out value='${item.birthDateString}'/>&nbsp;</td>
				<td><c:out value='${item.idcard}'/>&nbsp;</td>
				<td><c:out value='${item.tel}'/>&nbsp;</td>
				<td><c:out value='${item.department}'/>&nbsp;</td>
				<td><c:out value='${item.major}'/>&nbsp;</td>
				<td><c:out value='${item.stuClass}'/>&nbsp;</td>
				<td><c:out value='${item.address}'/>&nbsp;</td>
				<td>
					<a href="${ctx}/pages/Student/show.do?stuNum=${item.stuNum}&">查看</a>&nbsp;&nbsp;&nbsp;
					<a href="${ctx}/pages/Student/edit.do?stuNum=${item.stuNum}&">修改</a>
				</td>
			  </tr>
			  
		  	  </c:forEach>
		  </tbody>
		</table>
	
		<simpletable:pageToolbar page="${page}">
		<s:property value="@com.dispensary.project.model.Student@TABLE_ALIAS"/>列表
		</simpletable:pageToolbar>
	</div>
	</form>
</rapid:override>


<%@ include file="base.jsp" %>

