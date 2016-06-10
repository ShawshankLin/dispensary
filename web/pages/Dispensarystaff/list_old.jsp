<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title><%=Dispensarystaff.TABLE_ALIAS%> 维护</title>
	
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
	<form id="queryForm" name="queryForm" action="<c:url value="/pages/Dispensarystaff/list.do"/>" method="post" style="display: inline;">
	<div class="queryPanel">
		<fieldset>
			<legend>搜索</legend>
			<table>
				<tr>	
					<td class="tdLabel"><%=Dispensarystaff.ALIAS_ME_ST_NAME%></td>		
					<td>
						<input type="text" value="${query.meStName}" id="meStName" name="meStName" maxlength="30"  class=""/>
					</td>
					<td class="tdLabel"><%=Dispensarystaff.ALIAS_SEX%></td>		
					<td>
						<input type="text" value="${query.sex}" id="sex" name="sex" maxlength="10"  class="validate-integer max-value-2147483647"/>
					</td>
					<td class="tdLabel"><%=Dispensarystaff.ALIAS_AGE%></td>		
					<td>
						<input type="text" value="${query.age}" id="age" name="age" maxlength="10"  class="validate-integer max-value-2147483647"/>
					</td>
					<td class="tdLabel"><%=Dispensarystaff.ALIAS_EDUCATION%></td>		
					<td>
						<input type="text" value="${query.education}" id="education" name="education" maxlength="10"  class=""/>
					</td>
				</tr>	
				<tr>	
					<td class="tdLabel"><%=Dispensarystaff.ALIAS_ADDRESS%></td>		
					<td>
						<input type="text" value="${query.address}" id="address" name="address" maxlength="200"  class=""/>
					</td>
					<td class="tdLabel"><%=Dispensarystaff.ALIAS_TEL%></td>		
					<td>
						<input type="text" value="${query.tel}" id="tel" name="tel" maxlength="20"  class=""/>
					</td>
					<td class="tdLabel"><%=Dispensarystaff.ALIAS_MOBILE%></td>		
					<td>
						<input type="text" value="${query.mobile}" id="mobile" name="mobile" maxlength="20"  class=""/>
					</td>
					<td class="tdLabel"><%=Dispensarystaff.ALIAS_EMAIL%></td>		
					<td>
						<input type="text" value="${query.email}" id="email" name="email" maxlength="100"  class="validate-email "/>
					</td>
				</tr>	
				<tr>	
					<td class="tdLabel"><%=Dispensarystaff.ALIAS_NOTE%></td>		
					<td>
						<input type="text" value="${query.note}" id="note" name="note" maxlength="255"  class=""/>
					</td>
					<td class="tdLabel"><%=Dispensarystaff.ALIAS_WORK_PLACE%></td>		
					<td>
						<input type="text" value="${query.workPlace}" id="workPlace" name="workPlace" maxlength="20"  class=""/>
					</td>
					<td class="tdLabel"><%=Dispensarystaff.ALIAS_USER_ID%></td>		
					<td>
						<input type="text" value="${query.userId}" id="userId" name="userId" maxlength="20"  class=""/>
					</td>
					<td class="tdLabel"><%=Dispensarystaff.ALIAS_USER_TYPE_ID%></td>		
					<td>
						<input type="text" value="${query.userTypeId}" id="userTypeId" name="userTypeId" maxlength="10"  class=""/>
					</td>
				</tr>	
			</table>
		</fieldset>
		<div class="handleControl">
			<input type="submit" class="stdButton" style="width:80px" value="查询" onclick="getReferenceForm(this).action='${ctx}/pages/Dispensarystaff/list.do'"/>
			<input type="submit" class="stdButton" style="width:80px" value="新增" onclick="getReferenceForm(this).action='${ctx}/pages/Dispensarystaff/create.do'"/>
			<input type="button" class="stdButton" style="width:80px" value="删除" onclick="batchDelete('${ctx}/pages/Dispensarystaff/delete.do','items',document.forms.queryForm)"/>
		</div>
	</div>
	
	<div class="gridTable">
	
		<simpletable:pageToolbar page="${page}">
		<s:property value="@com.dispensary.project.model.Dispensarystaff@TABLE_ALIAS"/>列表
		</simpletable:pageToolbar>
	
		<table width="100%"  border="0" cellspacing="0" class="tablelist">
		  <thead>
			  
			  <tr>
				<th style="width:50px;"> </th>
				<th style="width:50px;"><input type="checkbox" onclick="setAllCheckboxState('items',this.checked)"></th>
				
				<!-- 排序时为th增加sortColumn即可,new SimpleTable('sortColumns')会为tableHeader自动增加排序功能; -->
				<th sortColumn="MeStName" ><%=Dispensarystaff.ALIAS_ME_ST_NAME%></th>
				<th sortColumn="Sex" ><%=Dispensarystaff.ALIAS_SEX%></th>
				<th sortColumn="Age" ><%=Dispensarystaff.ALIAS_AGE%></th>
				<th sortColumn="Education" ><%=Dispensarystaff.ALIAS_EDUCATION%></th>
				<th sortColumn="Address" ><%=Dispensarystaff.ALIAS_ADDRESS%></th>
				<th sortColumn="Tel" ><%=Dispensarystaff.ALIAS_TEL%></th>
				<th sortColumn="Mobile" ><%=Dispensarystaff.ALIAS_MOBILE%></th>
				<th sortColumn="Email" ><%=Dispensarystaff.ALIAS_EMAIL%></th>
				<th sortColumn="Note" ><%=Dispensarystaff.ALIAS_NOTE%></th>
				<th sortColumn="WorkPlace" ><%=Dispensarystaff.ALIAS_WORK_PLACE%></th>
				<th sortColumn="UserID" ><%=Dispensarystaff.ALIAS_USER_ID%></th>
				<th sortColumn="UserTypeID" ><%=Dispensarystaff.ALIAS_USER_TYPE_ID%></th>
	
				<th>操作</th>
			  </tr>
			  
		  </thead>
		  <tbody>
		  	  <c:forEach items="${page.result}" var="item" varStatus="status">
		  	  
			  <tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
				<td>${page.thisPageFirstElementNumber + status.index}</td>
				<td><input type="checkbox" name="items" value="meStId=${item.meStId}&"></td>
				
				<td><c:out value='${item.meStName}'/>&nbsp;</td>
				<td><c:out value='${item.sex}'/>&nbsp;</td>
				<td><c:out value='${item.age}'/>&nbsp;</td>
				<td><c:out value='${item.education}'/>&nbsp;</td>
				<td><c:out value='${item.address}'/>&nbsp;</td>
				<td><c:out value='${item.tel}'/>&nbsp;</td>
				<td><c:out value='${item.mobile}'/>&nbsp;</td>
				<td><c:out value='${item.email}'/>&nbsp;</td>
				<td><c:out value='${item.note}'/>&nbsp;</td>
				<td><c:out value='${item.workPlace}'/>&nbsp;</td>
				<td><c:out value='${item.userId}'/>&nbsp;</td>
				<td><c:out value='${item.userTypeId}'/>&nbsp;</td>
				<td>
					<a href="${ctx}/pages/Dispensarystaff/show.do?meStId=${item.meStId}&">查看</a>&nbsp;&nbsp;&nbsp;
					<a href="${ctx}/pages/Dispensarystaff/edit.do?meStId=${item.meStId}&">修改</a>
				</td>
			  </tr>
			  
		  	  </c:forEach>
		  </tbody>
		</table>
	
		<simpletable:pageToolbar page="${page}">
		<s:property value="@com.dispensary.project.model.Dispensarystaff@TABLE_ALIAS"/>列表
		</simpletable:pageToolbar>
	</div>
	</form>
</rapid:override>


<%@ include file="base.jsp" %>

