<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title><%=DrugStockOutDetail.TABLE_ALIAS%> 维护</title>
	
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
	<form id="queryForm" name="queryForm" action="<c:url value="/pages/DrugStockOutDetail/list.do"/>" method="post" style="display: inline;">
	<div class="queryPanel">
		<fieldset>
			<legend>搜索</legend>
			<table>
				<tr>	
					<td class="tdLabel"><%=DrugStockOutDetail.ALIAS_STOCK_OUT_ID%></td>		
					<td>
						<input type="text" value="${query.stockOutId}" id="stockOutId" name="stockOutId" maxlength="20"  class=""/>
					</td>
					<td class="tdLabel"><%=DrugStockOutDetail.ALIAS_SERIAL_NUMBER%></td>		
					<td>
							<input type="text" value="${query.serialNumberModelTag}" id="serialNumberModelTag" name="serialNumberModelTag"/>
					</td>
					<td class="tdLabel"><%=DrugStockOutDetail.ALIAS_DRUG_ID%></td>		
					<td>
						<input type="text" value="${query.drugId}" id="drugId" name="drugId" maxlength="10"  class="validate-integer max-value-2147483647"/>
					</td>
					<td class="tdLabel"><%=DrugStockOutDetail.ALIAS_OUT_DATE%></td>		
					<td>
						<input type="text" value="${query.outDate}" id="outDate" name="outDate" maxlength="20"  class=""/>
					</td>
				</tr>	
				<tr>	
					<td class="tdLabel"><%=DrugStockOutDetail.ALIAS_OUT_AMOUNT%></td>		
					<td>
						<input type="text" value="${query.outAmount}" id="outAmount" name="outAmount" maxlength="12"  class="validate-number "/>
					</td>
					<td class="tdLabel"><%=DrugStockOutDetail.ALIAS_OUT_PLACE_ID%></td>		
					<td>
							<input type="text" value="${query.outPlaceIdModelTag}" id="outPlaceIdModelTag" name="outPlaceIdModelTag"/>
					</td>
					<td class="tdLabel"><%=DrugStockOutDetail.ALIAS_FROM_PLACE_ID%></td>		
					<td>
							<input type="text" value="${query.fromPlaceIdModelTag}" id="fromPlaceIdModelTag" name="fromPlaceIdModelTag"/>
					</td>
				</tr>	
			</table>
		</fieldset>
		<div class="handleControl">
			<input type="submit" class="stdButton" style="width:80px" value="查询" onclick="getReferenceForm(this).action='${ctx}/pages/DrugStockOutDetail/list.do'"/>
			<input type="submit" class="stdButton" style="width:80px" value="新增" onclick="getReferenceForm(this).action='${ctx}/pages/DrugStockOutDetail/create.do'"/>
			<input type="button" class="stdButton" style="width:80px" value="删除" onclick="batchDelete('${ctx}/pages/DrugStockOutDetail/delete.do','items',document.forms.queryForm)"/>
		</div>
	</div>
	
	<div class="gridTable">
	
		<simpletable:pageToolbar page="${page}">
		<s:property value="@com.dispensary.project.model.DrugStockOutDetail@TABLE_ALIAS"/>列表
		</simpletable:pageToolbar>
	
		<table width="100%"  border="0" cellspacing="0" class="tablelist">
		  <thead>
			  
			  <tr>
				<th style="width:50px;"> </th>
				<th style="width:50px;"><input type="checkbox" onclick="setAllCheckboxState('items',this.checked)"></th>
				
				<!-- 排序时为th增加sortColumn即可,new SimpleTable('sortColumns')会为tableHeader自动增加排序功能; -->
				<th sortColumn="StockOutID" ><%=DrugStockOutDetail.ALIAS_STOCK_OUT_ID%></th>
				<th sortColumn="SerialNumber" ><%=DrugStockOutDetail.ALIAS_SERIAL_NUMBER%></th>
				<th sortColumn="DrugID" ><%=DrugStockOutDetail.ALIAS_DRUG_ID%></th>
				<th sortColumn="OutDate" ><%=DrugStockOutDetail.ALIAS_OUT_DATE%></th>
				<th sortColumn="OutAmount" ><%=DrugStockOutDetail.ALIAS_OUT_AMOUNT%></th>
				<th sortColumn="OutPlaceID" ><%=DrugStockOutDetail.ALIAS_OUT_PLACE_ID%></th>
				<th sortColumn="FromPlaceID" ><%=DrugStockOutDetail.ALIAS_FROM_PLACE_ID%></th>
	
				<th>操作</th>
			  </tr>
			  
		  </thead>
		  <tbody>
		  	  <c:forEach items="${page.result}" var="item" varStatus="status">
		  	  
			  <tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
				<td>${page.thisPageFirstElementNumber + status.index}</td>
				<td><input type="checkbox" name="items" value="id=${item.id}&"></td>
				
				<td><c:out value='${item.stockOutId}'/>&nbsp;</td>
				<td><c:out value='${item.serialNumberModel.modelTagValue}'/>&nbsp;</td>
				<td><c:out value='${item.drugId}'/>&nbsp;</td>
				<td><c:out value='${item.outDate}'/>&nbsp;</td>
				<td><c:out value='${item.outAmount}'/>&nbsp;</td>
				<td><c:out value='${item.outPlaceIdModel.modelTagValue}'/>&nbsp;</td>
				<td><c:out value='${item.fromPlaceIdModel.modelTagValue}'/>&nbsp;</td>
				<td>
					<a href="${ctx}/pages/DrugStockOutDetail/show.do?id=${item.id}&">查看</a>&nbsp;&nbsp;&nbsp;
					<a href="${ctx}/pages/DrugStockOutDetail/edit.do?id=${item.id}&">修改</a>
				</td>
			  </tr>
			  
		  	  </c:forEach>
		  </tbody>
		</table>
	
		<simpletable:pageToolbar page="${page}">
		<s:property value="@com.dispensary.project.model.DrugStockOutDetail@TABLE_ALIAS"/>列表
		</simpletable:pageToolbar>
	</div>
	</form>
</rapid:override>


<%@ include file="base.jsp" %>

