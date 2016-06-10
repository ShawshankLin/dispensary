<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title><%=Notice.TABLE_ALIAS%> 维护</title>
	
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
	<form id="queryForm" name="queryForm" action="<c:url value="/pages/Notice/list.do"/>" method="post" style="display: inline;">
	<div class="queryPanel">
		<fieldset>
			<legend>搜索</legend>
			<table>
				<tr>	
					<td class="tdLabel"><%=Notice.ALIAS_USER_ID%></td>		
					<td>
							<input type="text" value="${query.userIdModelTag}" id="userIdModelTag" name="userIdModelTag"/>
					</td>
					<td class="tdLabel"><%=Notice.ALIAS_TITLE%></td>		
					<td>
						<input type="text" value="${query.title}" id="title" name="title" maxlength="65535"  class=""/>
					</td>
					<td class="tdLabel"><%=Notice.ALIAS_CONTENT%></td>		
					<td>
						<input type="text" value="${query.content}" id="content" name="content" maxlength="65535"  class=""/>
					</td>
					<td class="tdLabel"><%=Notice.ALIAS_ATTACHMENT%></td>		
					<td>
						<input type="text" value="${query.attachment}" id="attachment" name="attachment" maxlength="255"  class=""/>
					</td>
				</tr>	
				<tr>	
					<td class="tdLabel"><%=Notice.ALIAS_DATE%></td>		
					<td>
						<input type="text" value="<fmt:formatDate value='${query.dateBegin}' pattern='<%=Notice.FORMAT_DATE%>'/>" onclick="WdatePicker({dateFmt:'<%=Notice.FORMAT_DATE%>'})" id="dateBegin" name="dateBegin"   />
						<input type="text" value="<fmt:formatDate value='${query.dateEnd}' pattern='<%=Notice.FORMAT_DATE%>'/>" onclick="WdatePicker({dateFmt:'<%=Notice.FORMAT_DATE%>'})" id="dateEnd" name="dateEnd"   />
					</td>
					<td class="tdLabel"><%=Notice.ALIAS_STATUS%></td>		
					<td>
						<input type="text" value="${query.status}" id="status" name="status" maxlength="10"  class="validate-integer max-value-2147483647"/>
					</td>
				</tr>	
			</table>
		</fieldset>
		<div class="handleControl">
			<input type="submit" class="stdButton" style="width:80px" value="查询" onclick="getReferenceForm(this).action='${ctx}/pages/Notice/list.do'"/>
			<input type="submit" class="stdButton" style="width:80px" value="新增" onclick="getReferenceForm(this).action='${ctx}/pages/Notice/create.do'"/>
			<input type="button" class="stdButton" style="width:80px" value="删除" onclick="batchDelete('${ctx}/pages/Notice/delete.do','items',document.forms.queryForm)"/>
		</div>
	</div>
	
	<div class="gridTable">
	
		<simpletable:pageToolbar page="${page}">
		<s:property value="@com.dispensary.project.model.Notice@TABLE_ALIAS"/>列表
		</simpletable:pageToolbar>
	
		<table width="100%"  border="0" cellspacing="0" class="tablelist">
		  <thead>
			  
			  <tr>
				<th style="width:50px;"> </th>
				<th style="width:50px;"><input type="checkbox" onclick="setAllCheckboxState('items',this.checked)"></th>
				
				<!-- 排序时为th增加sortColumn即可,new SimpleTable('sortColumns')会为tableHeader自动增加排序功能; -->
				<th sortColumn="UserID" ><%=Notice.ALIAS_USER_ID%></th>
				<th sortColumn="Title" ><%=Notice.ALIAS_TITLE%></th>
				<th sortColumn="Content" ><%=Notice.ALIAS_CONTENT%></th>
				<th sortColumn="Attachment" ><%=Notice.ALIAS_ATTACHMENT%></th>
				<th sortColumn="Date" ><%=Notice.ALIAS_DATE%></th>
				<th sortColumn="Status" ><%=Notice.ALIAS_STATUS%></th>
	
				<th>操作</th>
			  </tr>
			  
		  </thead>
		  <tbody>
		  	  <c:forEach items="${page.result}" var="item" varStatus="status">
		  	  
			  <tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
				<td>${page.thisPageFirstElementNumber + status.index}</td>
				<td><input type="checkbox" name="items" value="id=${item.id}&"></td>
				
				<td><c:out value='${item.userIdModel.modelTagValue}'/>&nbsp;</td>
				<td><c:out value='${item.title}'/>&nbsp;</td>
				<td><c:out value='${item.content}'/>&nbsp;</td>
				<td><c:out value='${item.attachment}'/>&nbsp;</td>
				<td><c:out value='${item.dateString}'/>&nbsp;</td>
				<td><c:out value='${item.status}'/>&nbsp;</td>
				<td>
					<a href="${ctx}/pages/Notice/show.do?id=${item.id}&">查看</a>&nbsp;&nbsp;&nbsp;
					<a href="${ctx}/pages/Notice/edit.do?id=${item.id}&">修改</a>
				</td>
			  </tr>
			  
		  	  </c:forEach>
		  </tbody>
		</table>
	
		<simpletable:pageToolbar page="${page}">
		<s:property value="@com.dispensary.project.model.Notice@TABLE_ALIAS"/>列表
		</simpletable:pageToolbar>
	</div>
	</form>
</rapid:override>


<%@ include file="base.jsp" %>

