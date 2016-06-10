<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title><%=Menu.TABLE_ALIAS%> 维护</title>
	
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
	<form id="queryForm" name="queryForm" action="<c:url value="/pages/Menu/list.do"/>" method="post" style="display: inline;">
	<div class="queryPanel">
		<fieldset>
			<legend>搜索</legend>
			<table>
				<tr>	
					<td class="tdLabel"><%=Menu.ALIAS_MENU_NAME%></td>		
					<td>
						<input type="text" value="${query.menuName}" id="menuName" name="menuName" maxlength="255"  class=""/>
					</td>
					<td class="tdLabel"><%=Menu.ALIAS_MENU_URL%></td>		
					<td>
						<input type="text" value="${query.menuUrl}" id="menuUrl" name="menuUrl" maxlength="255"  class=""/>
					</td>
					<td class="tdLabel"><%=Menu.ALIAS_PARENT%></td>		
					<td>
						<input type="text" value="${query.parent}" id="parent" name="parent" maxlength="255"  class=""/>
					</td>
					<td class="tdLabel"><%=Menu.ALIAS_MENU_NO%></td>		
					<td>
						<input type="text" value="${query.menuNo}" id="menuNo" name="menuNo" maxlength="10"  class="validate-integer max-value-2147483647"/>
					</td>
				</tr>	
			</table>
		</fieldset>
		<div class="handleControl">
			<input type="submit" class="stdButton" style="width:80px" value="查询" onclick="getReferenceForm(this).action='${ctx}/pages/Menu/list.do'"/>
			<input type="submit" class="stdButton" style="width:80px" value="新增" onclick="getReferenceForm(this).action='${ctx}/pages/Menu/create.do'"/>
			<input type="button" class="stdButton" style="width:80px" value="删除" onclick="batchDelete('${ctx}/pages/Menu/delete.do','items',document.forms.queryForm)"/>
		</div>
	</div>
	
	<div class="gridTable">
	
		<simpletable:pageToolbar page="${page}">
		<s:property value="@com.dispensary.project.model.Menu@TABLE_ALIAS"/>列表
		</simpletable:pageToolbar>
	
		<table width="100%"  border="0" cellspacing="0" class="tablelist">
		  <thead>
			  
			  <tr>
				<th style="width:50px;"> </th>
				<th style="width:50px;"><input type="checkbox" onclick="setAllCheckboxState('items',this.checked)"></th>
				
				<!-- 排序时为th增加sortColumn即可,new SimpleTable('sortColumns')会为tableHeader自动增加排序功能; -->
				<th sortColumn="MenuName" ><%=Menu.ALIAS_MENU_NAME%></th>
				<th sortColumn="MenuUrl" ><%=Menu.ALIAS_MENU_URL%></th>
				<th sortColumn="Parent" ><%=Menu.ALIAS_PARENT%></th>
				<th sortColumn="MenuNo" ><%=Menu.ALIAS_MENU_NO%></th>
	
				<th>操作</th>
			  </tr>
			  
		  </thead>
		  <tbody>
		  	  <c:forEach items="${page.result}" var="item" varStatus="status">
		  	  
			  <tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
				<td>${page.thisPageFirstElementNumber + status.index}</td>
				<td><input type="checkbox" name="items" value="menuId=${item.menuId}&"></td>
				
				<td><c:out value='${item.menuName}'/>&nbsp;</td>
				<td><c:out value='${item.menuUrl}'/>&nbsp;</td>
				<td><c:out value='${item.parent}'/>&nbsp;</td>
				<td><c:out value='${item.menuNo}'/>&nbsp;</td>
				<td>
					<a href="${ctx}/pages/Menu/show.do?menuId=${item.menuId}&">查看</a>&nbsp;&nbsp;&nbsp;
					<a href="${ctx}/pages/Menu/edit.do?menuId=${item.menuId}&">修改</a>
				</td>
			  </tr>
			  
		  	  </c:forEach>
		  </tbody>
		</table>
	
		<simpletable:pageToolbar page="${page}">
		<s:property value="@com.dispensary.project.model.Menu@TABLE_ALIAS"/>列表
		</simpletable:pageToolbar>
	</div>
	</form>
</rapid:override>


<%@ include file="base.jsp" %>

