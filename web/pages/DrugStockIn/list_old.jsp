<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title><%=DrugStockIn.TABLE_ALIAS%> 维护</title>
	
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
	<form id="queryForm" name="queryForm" action="<c:url value="/pages/DrugStockIn/list.do"/>" method="post" style="display: inline;">
	<div class="queryPanel">
		<fieldset>
			<legend>搜索</legend>
			<table>
				<tr>	
					<td class="tdLabel"><%=DrugStockIn.ALIAS_SERIAL_NUMBER%></td>		
					<td>
						<input type="text" value="${query.serialNumber}" id="serialNumber" name="serialNumber" maxlength="20"  class=""/>
					</td>
					<td class="tdLabel"><%=DrugStockIn.ALIAS_STOCK_IN_ID%></td>		
					<td>
						<input type="text" value="${query.stockInId}" id="stockInId" name="stockInId" maxlength="20"  class=""/>
					</td>
					<td class="tdLabel"><%=DrugStockIn.ALIAS_IN_DATE%></td>		
					<td>
						<input type="text" value="${query.inDate}" id="inDate" name="inDate" maxlength="20"  class=""/>
					</td>
					<td class="tdLabel"><%=DrugStockIn.ALIAS_OPERATOR_ID%></td>		
					<td>
							<input type="text" value="${query.operatorIdModelTag}" id="operatorIdModelTag" name="operatorIdModelTag"/>
					</td>
				</tr>	
				<tr>	
					<td class="tdLabel"><%=DrugStockIn.ALIAS_SUPPLIER_ID%></td>		
					<td>
							<input type="text" value="${query.supplierIdModelTag}" id="supplierIdModelTag" name="supplierIdModelTag"/>
					</td>
				</tr>	
			</table>
		</fieldset>
		<div class="handleControl">
			<input type="submit" class="stdButton" style="width:80px" value="查询" onclick="getReferenceForm(this).action='${ctx}/pages/DrugStockIn/list.do'"/>
			<input type="submit" class="stdButton" style="width:80px" value="新增" onclick="getReferenceForm(this).action='${ctx}/pages/DrugStockIn/create.do'"/>
			<input type="button" class="stdButton" style="width:80px" value="删除" onclick="batchDelete('${ctx}/pages/DrugStockIn/delete.do','items',document.forms.queryForm)"/>
		</div>
	</div>
	
	<div class="gridTable">
	
		<simpletable:pageToolbar page="${page}">
		<s:property value="@com.dispensary.project.model.DrugStockIn@TABLE_ALIAS"/>列表
		</simpletable:pageToolbar>
	
		<table width="100%"  border="0" cellspacing="0" class="tablelist">
		  <thead>
			  
			  <tr>
				<th style="width:50px;"> </th>
				<th style="width:50px;"><input type="checkbox" onclick="setAllCheckboxState('items',this.checked)"></th>
				
				<!-- 排序时为th增加sortColumn即可,new SimpleTable('sortColumns')会为tableHeader自动增加排序功能; -->
				<th sortColumn="SerialNumber" ><%=DrugStockIn.ALIAS_SERIAL_NUMBER%></th>
				<th sortColumn="Stock_In_ID" ><%=DrugStockIn.ALIAS_STOCK_IN_ID%></th>
				<th sortColumn="InDate" ><%=DrugStockIn.ALIAS_IN_DATE%></th>
				<th sortColumn="OperatorID" ><%=DrugStockIn.ALIAS_OPERATOR_ID%></th>
				<th sortColumn="SupplierID" ><%=DrugStockIn.ALIAS_SUPPLIER_ID%></th>
	
				<th>操作</th>
			  </tr>
			  
		  </thead>
		  <tbody>
		  	  <c:forEach items="${page.result}" var="item" varStatus="status">
		  	  
			  <tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
				<td>${page.thisPageFirstElementNumber + status.index}</td>
				<td><input type="checkbox" name="items" value="id=${item.id}&"></td>
				
				<td><c:out value='${item.serialNumber}'/>&nbsp;</td>
				<td><c:out value='${item.stockInId}'/>&nbsp;</td>
				<td><c:out value='${item.inDate}'/>&nbsp;</td>
				<td><c:out value='${item.operatorIdModel.modelTagValue}'/>&nbsp;</td>
				<td><c:out value='${item.supplierIdModel.modelTagValue}'/>&nbsp;</td>
				<td>
					<a href="${ctx}/pages/DrugStockIn/show.do?id=${item.id}&">查看</a>&nbsp;&nbsp;&nbsp;
					<a href="${ctx}/pages/DrugStockIn/edit.do?id=${item.id}&">修改</a>
				</td>
			  </tr>
			  
		  	  </c:forEach>
		  </tbody>
		</table>
	
		<simpletable:pageToolbar page="${page}">
		<s:property value="@com.dispensary.project.model.DrugStockIn@TABLE_ALIAS"/>列表
		</simpletable:pageToolbar>
	</div>
	</form>
</rapid:override>


<%@ include file="base.jsp" %>

