<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title><%=Supplier.TABLE_ALIAS%> 维护</title>
	
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
	<form id="queryForm" name="queryForm" action="<c:url value="/pages/Supplier/list.do"/>" method="post" style="display: inline;">
	<div class="queryPanel">
		<fieldset>
			<legend>搜索</legend>
			<table>
				<tr>	
					<td class="tdLabel"><%=Supplier.ALIAS_SUPPLIER_NAME%></td>		
					<td>
						<input type="text" value="${query.supplierName}" id="supplierName" name="supplierName" maxlength="100"  class=""/>
					</td>
					<td class="tdLabel"><%=Supplier.ALIAS_CONTACTS%></td>		
					<td>
						<input type="text" value="${query.contacts}" id="contacts" name="contacts" maxlength="20"  class=""/>
					</td>
					<td class="tdLabel"><%=Supplier.ALIAS_PINGYIN%></td>		
					<td>
						<input type="text" value="${query.pingyin}" id="pingyin" name="pingyin" maxlength="50"  class=""/>
					</td>
					<td class="tdLabel"><%=Supplier.ALIAS_ADDRESS%></td>		
					<td>
						<input type="text" value="${query.address}" id="address" name="address" maxlength="255"  class=""/>
					</td>
				</tr>	
				<tr>	
					<td class="tdLabel"><%=Supplier.ALIAS_USER_TEL%></td>		
					<td>
						<input type="text" value="${query.userTel}" id="userTel" name="userTel" maxlength="20"  class=""/>
					</td>
					<td class="tdLabel"><%=Supplier.ALIAS_USER_MOBILE%></td>		
					<td>
						<input type="text" value="${query.userMobile}" id="userMobile" name="userMobile" maxlength="20"  class=""/>
					</td>
					<td class="tdLabel"><%=Supplier.ALIAS_USER_EMAIL%></td>		
					<td>
						<input type="text" value="${query.userEmail}" id="userEmail" name="userEmail" maxlength="100"  class="validate-email "/>
					</td>
					<td class="tdLabel"><%=Supplier.ALIAS_NOTE%></td>		
					<td>
						<input type="text" value="${query.note}" id="note" name="note" maxlength="255"  class=""/>
					</td>
				</tr>	
			</table>
		</fieldset>
		<div class="handleControl">
			<input type="submit" class="stdButton" style="width:80px" value="查询" onclick="getReferenceForm(this).action='${ctx}/pages/Supplier/list.do'"/>
			<input type="submit" class="stdButton" style="width:80px" value="新增" onclick="getReferenceForm(this).action='${ctx}/pages/Supplier/create.do'"/>
			<input type="button" class="stdButton" style="width:80px" value="删除" onclick="batchDelete('${ctx}/pages/Supplier/delete.do','items',document.forms.queryForm)"/>
		</div>
	</div>
	
	<div class="gridTable">
	
		<simpletable:pageToolbar page="${page}">
		<s:property value="@com.dispensary.project.model.Supplier@TABLE_ALIAS"/>列表
		</simpletable:pageToolbar>
	
		<table width="100%"  border="0" cellspacing="0" class="tablelist">
		  <thead>
			  
			  <tr>
				<th style="width:50px;"> </th>
				<th style="width:50px;"><input type="checkbox" onclick="setAllCheckboxState('items',this.checked)"></th>
				
				<!-- 排序时为th增加sortColumn即可,new SimpleTable('sortColumns')会为tableHeader自动增加排序功能; -->
				<th sortColumn="SupplierName" ><%=Supplier.ALIAS_SUPPLIER_NAME%></th>
				<th sortColumn="Contacts" ><%=Supplier.ALIAS_CONTACTS%></th>
				<th sortColumn="Pingyin" ><%=Supplier.ALIAS_PINGYIN%></th>
				<th sortColumn="Address" ><%=Supplier.ALIAS_ADDRESS%></th>
				<th sortColumn="UserTel" ><%=Supplier.ALIAS_USER_TEL%></th>
				<th sortColumn="UserMobile" ><%=Supplier.ALIAS_USER_MOBILE%></th>
				<th sortColumn="UserEmail" ><%=Supplier.ALIAS_USER_EMAIL%></th>
				<th sortColumn="Note" ><%=Supplier.ALIAS_NOTE%></th>
	
				<th>操作</th>
			  </tr>
			  
		  </thead>
		  <tbody>
		  	  <c:forEach items="${page.result}" var="item" varStatus="status">
		  	  
			  <tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
				<td>${page.thisPageFirstElementNumber + status.index}</td>
				<td><input type="checkbox" name="items" value="supplierId=${item.supplierId}&"></td>
				
				<td><c:out value='${item.supplierName}'/>&nbsp;</td>
				<td><c:out value='${item.contacts}'/>&nbsp;</td>
				<td><c:out value='${item.pingyin}'/>&nbsp;</td>
				<td><c:out value='${item.address}'/>&nbsp;</td>
				<td><c:out value='${item.userTel}'/>&nbsp;</td>
				<td><c:out value='${item.userMobile}'/>&nbsp;</td>
				<td><c:out value='${item.userEmail}'/>&nbsp;</td>
				<td><c:out value='${item.note}'/>&nbsp;</td>
				<td>
					<a href="${ctx}/pages/Supplier/show.do?supplierId=${item.supplierId}&">查看</a>&nbsp;&nbsp;&nbsp;
					<a href="${ctx}/pages/Supplier/edit.do?supplierId=${item.supplierId}&">修改</a>
				</td>
			  </tr>
			  
		  	  </c:forEach>
		  </tbody>
		</table>
	
		<simpletable:pageToolbar page="${page}">
		<s:property value="@com.dispensary.project.model.Supplier@TABLE_ALIAS"/>列表
		</simpletable:pageToolbar>
	</div>
	</form>
</rapid:override>


<%@ include file="base.jsp" %>

