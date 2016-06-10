<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title><%=Userinfo.TABLE_ALIAS%> 维护</title>
	
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
	<form id="queryForm" name="queryForm" action="<c:url value="/pages/Userinfo/list.do"/>" method="post" style="display: inline;">
	<div class="queryPanel">
		<fieldset>
			<legend>搜索</legend>
			<table>
				<tr>	
					<td class="tdLabel"><%=Userinfo.ALIAS_ME_ST_ID%></td>		
					<td>
							<input type="text" value="${query.meStIdModelTag}" id="meStIdModelTag" name="meStIdModelTag"/>
					</td>
					<td class="tdLabel"><%=Userinfo.ALIAS_PASSWORD%></td>		
					<td>
						<input type="text" value="${query.password}" id="password" name="password" maxlength="50"  class=""/>
					</td>
					<td class="tdLabel"><%=Userinfo.ALIAS_USER_NAME%></td>		
					<td>
						<input type="text" value="${query.userName}" id="userName" name="userName" maxlength="30"  class=""/>
					</td>
					<td class="tdLabel"><%=Userinfo.ALIAS_IF_VALIDITY%></td>		
					<td>
						<input type="text" value="${query.ifValidity}" id="ifValidity" name="ifValidity" maxlength="10"  class="validate-integer max-value-2147483647"/>
					</td>
				</tr>	
			</table>
		</fieldset>
		<div class="handleControl">
			<input type="submit" class="stdButton" style="width:80px" value="查询" onclick="getReferenceForm(this).action='${ctx}/pages/Userinfo/list.do'"/>
			<input type="submit" class="stdButton" style="width:80px" value="新增" onclick="getReferenceForm(this).action='${ctx}/pages/Userinfo/create.do'"/>
			<input type="button" class="stdButton" style="width:80px" value="删除" onclick="batchDelete('${ctx}/pages/Userinfo/delete.do','items',document.forms.queryForm)"/>
		</div>
	</div>
	
	<div class="gridTable">
	
		<simpletable:pageToolbar page="${page}">
		<s:property value="@com.dispensary.project.model.Userinfo@TABLE_ALIAS"/>列表
		</simpletable:pageToolbar>
	
		<table width="100%"  border="0" cellspacing="0" class="tablelist">
		  <thead>
			  
			  <tr>
				<th style="width:50px;"> </th>
				<th style="width:50px;"><input type="checkbox" onclick="setAllCheckboxState('items',this.checked)"></th>
				
				<!-- 排序时为th增加sortColumn即可,new SimpleTable('sortColumns')会为tableHeader自动增加排序功能; -->
				<th sortColumn="UserName" ><%=Userinfo.ALIAS_USER_NAME%></th>
				<th sortColumn="MeStID" ><%=Userinfo.ALIAS_ME_ST_ID%></th>
				<th sortColumn="Password" ><%=Userinfo.ALIAS_PASSWORD%></th>
				<th sortColumn="UserTypeID" ><%=Userinfo.ALIAS_USER_TYPE_ID%></th>
				<th sortColumn="If_validity" ><%=Userinfo.ALIAS_IF_VALIDITY%></th>
	
				<th>操作</th>
			  </tr>
			  
		  </thead>
		  <tbody>
		  	  <c:forEach items="${page.result}" var="item" varStatus="status">
		  	  
			  <tr>
				<td>${page.thisPageFirstElementNumber + status.index}</td>
				<td><input type="checkbox" name="items" value="userId=${item.userId}&"></td>
				<td><c:out value='${item.userName}'/>&nbsp;</td>
				<td><c:out value='${item.meStIdModel.modelTagValue}'/>&nbsp;</td>
				<td><c:out value='${item.password}'/>&nbsp;</td>
				<td><c:out value='${item.userTypeIdModel.modelTagValue}'/>&nbsp;</td>
				<td><c:out value='${item.ifValidity}'/>&nbsp;</td>
				<td>
					<a href="${ctx}/pages/Userinfo/show.do?userId=${item.userId}&">查看</a>&nbsp;&nbsp;&nbsp;
					<a href="${ctx}/pages/Userinfo/edit.do?userId=${item.userId}&">修改</a>
				</td>
			  </tr>
			  
		  	  </c:forEach>
		  </tbody>
		</table>
	
		<simpletable:pageToolbar page="${page}">
		<s:property value="@com.dispensary.project.model.Userinfo@TABLE_ALIAS"/>列表
		</simpletable:pageToolbar>
	</div>
	</form>
</rapid:override>


<%@ include file="base.jsp" %>

