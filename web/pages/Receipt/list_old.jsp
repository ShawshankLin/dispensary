<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title><%=Receipt.TABLE_ALIAS%> 维护</title>
	
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
	<form id="queryForm" name="queryForm" action="<c:url value="/pages/Receipt/list.do"/>" method="post" style="display: inline;">
	<div class="queryPanel">
		<fieldset>
			<legend>搜索</legend>
			<table>
				<tr>	
					<td class="tdLabel"><%=Receipt.ALIAS_PRES_ID%></td>		
					<td>
							<input type="text" value="${query.presIdModelTag}" id="presIdModelTag" name="presIdModelTag"/>
					</td>
					<td class="tdLabel"><%=Receipt.ALIAS_DRUG_FEE%></td>		
					<td>
						<input type="text" value="${query.drugFee}" id="drugFee" name="drugFee" maxlength="12"  class="validate-integer "/>
					</td>
					<td class="tdLabel"><%=Receipt.ALIAS_TRANSFER_FEE%></td>		
					<td>
						<input type="text" value="${query.transferFee}" id="transferFee" name="transferFee" maxlength="12"  class="validate-integer "/>
					</td>
					<td class="tdLabel"><%=Receipt.ALIAS_OXYGEN_FEE%></td>		
					<td>
						<input type="text" value="${query.oxygenFee}" id="oxygenFee" name="oxygenFee" maxlength="12"  class="validate-integer "/>
					</td>
				</tr>	
				<tr>	
					<td class="tdLabel"><%=Receipt.ALIAS_PHYSICAL_FEE%></td>		
					<td>
						<input type="text" value="${query.physicalFee}" id="physicalFee" name="physicalFee" maxlength="12"  class="validate-integer "/>
					</td>
					<td class="tdLabel"><%=Receipt.ALIAS_EMERGENCY_FEE%></td>		
					<td>
						<input type="text" value="${query.emergencyFee}" id="emergencyFee" name="emergencyFee" maxlength="12"  class="validate-integer "/>
					</td>
					<td class="tdLabel"><%=Receipt.ALIAS_INJECTION_FEE%></td>		
					<td>
						<input type="text" value="${query.injectionFee}" id="injectionFee" name="injectionFee" maxlength="12"  class="validate-integer "/>
					</td>
					<td class="tdLabel"><%=Receipt.ALIAS_NEBULIZATION_FEE%></td>		
					<td>
						<input type="text" value="${query.nebulizationFee}" id="nebulizationFee" name="nebulizationFee" maxlength="12"  class="validate-integer "/>
					</td>
				</tr>	
				<tr>	
					<td class="tdLabel"><%=Receipt.ALIAS_REGISTER_FEE%></td>		
					<td>
						<input type="text" value="${query.registerFee}" id="registerFee" name="registerFee" maxlength="12"  class="validate-integer "/>
					</td>
					<td class="tdLabel"><%=Receipt.ALIAS_FEE_SUM%></td>		
					<td>
						<input type="text" value="${query.feeSum}" id="feeSum" name="feeSum" maxlength="12"  class="validate-integer "/>
					</td>
					<td class="tdLabel"><%=Receipt.ALIAS_ME_ST_ID%></td>		
					<td>
							<input type="text" value="${query.meStIdModelTag}" id="meStIdModelTag" name="meStIdModelTag"/>
					</td>
					<td class="tdLabel"><%=Receipt.ALIAS_REC_DATE%></td>		
					<td>
						<input type="text" value="<fmt:formatDate value='${query.recDateBegin}' pattern='<%=Receipt.FORMAT_REC_DATE%>'/>" onclick="WdatePicker({dateFmt:'<%=Receipt.FORMAT_REC_DATE%>'})" id="recDateBegin" name="recDateBegin"   />
						<input type="text" value="<fmt:formatDate value='${query.recDateEnd}' pattern='<%=Receipt.FORMAT_REC_DATE%>'/>" onclick="WdatePicker({dateFmt:'<%=Receipt.FORMAT_REC_DATE%>'})" id="recDateEnd" name="recDateEnd"   />
					</td>
				</tr>	
			</table>
		</fieldset>
		<div class="handleControl">
			<input type="submit" class="stdButton" style="width:80px" value="查询" onclick="getReferenceForm(this).action='${ctx}/pages/Receipt/list.do'"/>
			<input type="submit" class="stdButton" style="width:80px" value="新增" onclick="getReferenceForm(this).action='${ctx}/pages/Receipt/create.do'"/>
			<input type="button" class="stdButton" style="width:80px" value="删除" onclick="batchDelete('${ctx}/pages/Receipt/delete.do','items',document.forms.queryForm)"/>
		</div>
	</div>
	
	<div class="gridTable">
	
		<simpletable:pageToolbar page="${page}">
		<s:property value="@com.dispensary.project.model.Receipt@TABLE_ALIAS"/>列表
		</simpletable:pageToolbar>
	
		<table width="100%"  border="0" cellspacing="0" class="tablelist">
		  <thead>
			  
			  <tr>
				<th style="width:50px;"> </th>
				<th style="width:50px;"><input type="checkbox" onclick="setAllCheckboxState('items',this.checked)"></th>
				
				<!-- 排序时为th增加sortColumn即可,new SimpleTable('sortColumns')会为tableHeader自动增加排序功能; -->
				<th sortColumn="PresID" ><%=Receipt.ALIAS_PRES_ID%></th>
				<th sortColumn="DrugFee" ><%=Receipt.ALIAS_DRUG_FEE%></th>
				<th sortColumn="TransferFee" ><%=Receipt.ALIAS_TRANSFER_FEE%></th>
				<th sortColumn="OxygenFee" ><%=Receipt.ALIAS_OXYGEN_FEE%></th>
				<th sortColumn="PhysicalFee" ><%=Receipt.ALIAS_PHYSICAL_FEE%></th>
				<th sortColumn="EmergencyFee" ><%=Receipt.ALIAS_EMERGENCY_FEE%></th>
				<th sortColumn="InjectionFee" ><%=Receipt.ALIAS_INJECTION_FEE%></th>
				<th sortColumn="NebulizationFee" ><%=Receipt.ALIAS_NEBULIZATION_FEE%></th>
				<th sortColumn="RegisterFee" ><%=Receipt.ALIAS_REGISTER_FEE%></th>
				<th sortColumn="FeeSum" ><%=Receipt.ALIAS_FEE_SUM%></th>
				<th sortColumn="MeStID" ><%=Receipt.ALIAS_ME_ST_ID%></th>
				<th sortColumn="RecDate" ><%=Receipt.ALIAS_REC_DATE%></th>
	
				<th>操作</th>
			  </tr>
			  
		  </thead>
		  <tbody>
		  	  <c:forEach items="${page.result}" var="item" varStatus="status">
		  	  
			  <tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
				<td>${page.thisPageFirstElementNumber + status.index}</td>
				<td><input type="checkbox" name="items" value="recId=${item.recId}&"></td>
				
				<td><c:out value='${item.presIdModel.modelTagValue}'/>&nbsp;</td>
				<td><c:out value='${item.drugFee}'/>&nbsp;</td>
				<td><c:out value='${item.transferFee}'/>&nbsp;</td>
				<td><c:out value='${item.oxygenFee}'/>&nbsp;</td>
				<td><c:out value='${item.physicalFee}'/>&nbsp;</td>
				<td><c:out value='${item.emergencyFee}'/>&nbsp;</td>
				<td><c:out value='${item.injectionFee}'/>&nbsp;</td>
				<td><c:out value='${item.nebulizationFee}'/>&nbsp;</td>
				<td><c:out value='${item.registerFee}'/>&nbsp;</td>
				<td><c:out value='${item.feeSum}'/>&nbsp;</td>
				<td><c:out value='${item.meStIdModel.modelTagValue}'/>&nbsp;</td>
				<td><c:out value='${item.recDateString}'/>&nbsp;</td>
				<td>
					<a href="${ctx}/pages/Receipt/show.do?recId=${item.recId}&">查看</a>&nbsp;&nbsp;&nbsp;
					<a href="${ctx}/pages/Receipt/edit.do?recId=${item.recId}&">修改</a>
				</td>
			  </tr>
			  
		  	  </c:forEach>
		  </tbody>
		</table>
	
		<simpletable:pageToolbar page="${page}">
		<s:property value="@com.dispensary.project.model.Receipt@TABLE_ALIAS"/>列表
		</simpletable:pageToolbar>
	</div>
	</form>
</rapid:override>


<%@ include file="base.jsp" %>

