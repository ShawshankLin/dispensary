<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title><%=DrugBasicInfo.TABLE_ALIAS%> 维护</title>
	
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
	<form id="queryForm" name="queryForm" action="<c:url value="/pages/DrugBasicInfo/list.do"/>" method="post" style="display: inline;">
	<div class="queryPanel">
		<fieldset>
			<legend>搜索</legend>
			<table>
				<tr>	
					<td class="tdLabel"><%=DrugBasicInfo.ALIAS_DRUG_NAME%></td>		
					<td>
						<input type="text" value="${query.drugName}" id="drugName" name="drugName" maxlength="50"  class=""/>
					</td>
					<td class="tdLabel"><%=DrugBasicInfo.ALIAS_DRUG_PINGYIN%></td>		
					<td>
						<input type="text" value="${query.drugPingyin}" id="drugPingyin" name="drugPingyin" maxlength="50"  class=""/>
					</td>
					<td class="tdLabel"><%=DrugBasicInfo.ALIAS_DRUG_EFFECT%></td>		
					<td>
							<input type="text" value="${query.drugEffectModelTag}" id="drugEffectModelTag" name="drugEffectModelTag"/>
					</td>
					<td class="tdLabel"><%=DrugBasicInfo.ALIAS_DRUG_KICK_BACK%></td>		
					<td>
							<input type="text" value="${query.drugKickBackModelTag}" id="drugKickBackModelTag" name="drugKickBackModelTag"/>
					</td>
				</tr>	
				<tr>	
					<td class="tdLabel"><%=DrugBasicInfo.ALIAS_DRUG_NOTE%></td>		
					<td>
						<input type="text" value="${query.drugNote}" id="drugNote" name="drugNote" maxlength="500"  class=""/>
					</td>
					<td class="tdLabel"><%=DrugBasicInfo.ALIAS_UNIT_ID%></td>		
					<td>
							<input type="text" value="${query.unitIdModelTag}" id="unitIdModelTag" name="unitIdModelTag"/>
					</td>
					<td class="tdLabel"><%=DrugBasicInfo.ALIAS_DRUG_TABU%></td>		
					<td>
						<input type="text" value="${query.drugTabu}" id="drugTabu" name="drugTabu" maxlength="255"  class=""/>
					</td>
					<td class="tdLabel"><%=DrugBasicInfo.ALIAS_COST_PRICE%></td>		
					<td>
						<input type="text" value="${query.costPrice}" id="costPrice" name="costPrice" maxlength="10"  class="validate-integer "/>
					</td>
				</tr>	
				<tr>	
					<td class="tdLabel"><%=DrugBasicInfo.ALIAS_RETAIL_PRICE%></td>		
					<td>
						<input type="text" value="${query.retailPrice}" id="retailPrice" name="retailPrice" maxlength="10"  class="validate-integer "/>
					</td>
					<td class="tdLabel"><%=DrugBasicInfo.ALIAS_PRODUCTION_DATE%></td>		
					<td>
						<input type="text" value="<fmt:formatDate value='${query.productionDateBegin}' pattern='<%=DrugBasicInfo.FORMAT_PRODUCTION_DATE%>'/>" onclick="WdatePicker({dateFmt:'<%=DrugBasicInfo.FORMAT_PRODUCTION_DATE%>'})" id="productionDateBegin" name="productionDateBegin"   />
						<input type="text" value="<fmt:formatDate value='${query.productionDateEnd}' pattern='<%=DrugBasicInfo.FORMAT_PRODUCTION_DATE%>'/>" onclick="WdatePicker({dateFmt:'<%=DrugBasicInfo.FORMAT_PRODUCTION_DATE%>'})" id="productionDateEnd" name="productionDateEnd"   />
					</td>
					<td class="tdLabel"><%=DrugBasicInfo.ALIAS_PERIOD_OF_VALIDITY%></td>		
					<td>
						<input type="text" value="${query.periodOfValidity}" id="periodOfValidity" name="periodOfValidity" maxlength="10"  class="validate-integer max-value-2147483647"/>
					</td>
					<td class="tdLabel"><%=DrugBasicInfo.ALIAS_UP_LIMIT%></td>		
					<td>
						<input type="text" value="${query.upLimit}" id="upLimit" name="upLimit" maxlength="10"  class="validate-integer max-value-2147483647"/>
					</td>
				</tr>	
				<tr>	
					<td class="tdLabel"><%=DrugBasicInfo.ALIAS_DOWN_LIMIT%></td>		
					<td>
						<input type="text" value="${query.downLimit}" id="downLimit" name="downLimit" maxlength="10"  class="validate-integer max-value-2147483647"/>
					</td>
					<td class="tdLabel"><%=DrugBasicInfo.ALIAS_UP_LIMIT1%></td>		
					<td>
						<input type="text" value="${query.upLimit1}" id="upLimit1" name="upLimit1" maxlength="10"  class="validate-integer max-value-2147483647"/>
					</td>
					<td class="tdLabel"><%=DrugBasicInfo.ALIAS_DOWN_LIMIT1%></td>		
					<td>
						<input type="text" value="${query.downLimit1}" id="downLimit1" name="downLimit1" maxlength="10"  class="validate-integer max-value-2147483647"/>
					</td>
					<td class="tdLabel"><%=DrugBasicInfo.ALIAS_SYMPTOM_ID%></td>		
					<td>
							<input type="text" value="${query.symptomIdModelTag}" id="symptomIdModelTag" name="symptomIdModelTag"/>
					</td>
				</tr>	
			</table>
		</fieldset>
		<div class="handleControl">
			<input type="submit" class="stdButton" style="width:80px" value="查询" onclick="getReferenceForm(this).action='${ctx}/pages/DrugBasicInfo/list.do'"/>
			<input type="submit" class="stdButton" style="width:80px" value="新增" onclick="getReferenceForm(this).action='${ctx}/pages/DrugBasicInfo/create.do'"/>
			<input type="button" class="stdButton" style="width:80px" value="删除" onclick="batchDelete('${ctx}/pages/DrugBasicInfo/delete.do','items',document.forms.queryForm)"/>
		</div>
	</div>
	
	<div class="gridTable">
	
		<simpletable:pageToolbar page="${page}">
		<s:property value="@com.dispensary.project.model.DrugBasicInfo@TABLE_ALIAS"/>列表
		</simpletable:pageToolbar>
	
		<table width="100%"  border="0" cellspacing="0" class="tablelist">
		  <thead>
			  
			  <tr>
				<th style="width:50px;"> </th>
				<th style="width:50px;"><input type="checkbox" onclick="setAllCheckboxState('items',this.checked)"></th>
				
				<!-- 排序时为th增加sortColumn即可,new SimpleTable('sortColumns')会为tableHeader自动增加排序功能; -->
				<th sortColumn="DrugName" ><%=DrugBasicInfo.ALIAS_DRUG_NAME%></th>
				<th sortColumn="DrugPingyin" ><%=DrugBasicInfo.ALIAS_DRUG_PINGYIN%></th>
				<th sortColumn="DrugEffect" ><%=DrugBasicInfo.ALIAS_DRUG_EFFECT%></th>
				<th sortColumn="DrugKickBack" ><%=DrugBasicInfo.ALIAS_DRUG_KICK_BACK%></th>
				<th sortColumn="DrugNote" ><%=DrugBasicInfo.ALIAS_DRUG_NOTE%></th>
				<th sortColumn="UnitID" ><%=DrugBasicInfo.ALIAS_UNIT_ID%></th>
				<th sortColumn="DrugTabu" ><%=DrugBasicInfo.ALIAS_DRUG_TABU%></th>
				<th sortColumn="CostPrice" ><%=DrugBasicInfo.ALIAS_COST_PRICE%></th>
				<th sortColumn="RetailPrice" ><%=DrugBasicInfo.ALIAS_RETAIL_PRICE%></th>
				<th sortColumn="ProductionDate" ><%=DrugBasicInfo.ALIAS_PRODUCTION_DATE%></th>
				<th sortColumn="PeriodOfValidity" ><%=DrugBasicInfo.ALIAS_PERIOD_OF_VALIDITY%></th>
				<th sortColumn="UpLimit" ><%=DrugBasicInfo.ALIAS_UP_LIMIT%></th>
				<th sortColumn="DownLimit" ><%=DrugBasicInfo.ALIAS_DOWN_LIMIT%></th>
				<th sortColumn="UpLimit1" ><%=DrugBasicInfo.ALIAS_UP_LIMIT1%></th>
				<th sortColumn="DownLimit1" ><%=DrugBasicInfo.ALIAS_DOWN_LIMIT1%></th>
				<th sortColumn="SymptomID" ><%=DrugBasicInfo.ALIAS_SYMPTOM_ID%></th>
	
				<th>操作</th>
			  </tr>
			  
		  </thead>
		  <tbody>
		  	  <c:forEach items="${page.result}" var="item" varStatus="status">
		  	  
			  <tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
				<td>${page.thisPageFirstElementNumber + status.index}</td>
				<td><input type="checkbox" name="items" value="drugId=${item.drugId}&"></td>
				
				<td><c:out value='${item.drugName}'/>&nbsp;</td>
				<td><c:out value='${item.drugPingyin}'/>&nbsp;</td>
				<td><c:out value='${item.drugEffectModel.modelTagValue}'/>&nbsp;</td>
				<td><c:out value='${item.drugKickBackModel.modelTagValue}'/>&nbsp;</td>
				<td><c:out value='${item.drugNote}'/>&nbsp;</td>
				<td><c:out value='${item.unitIdModel.modelTagValue}'/>&nbsp;</td>
				<td><c:out value='${item.drugTabu}'/>&nbsp;</td>
				<td><c:out value='${item.costPrice}'/>&nbsp;</td>
				<td><c:out value='${item.retailPrice}'/>&nbsp;</td>
				<td><c:out value='${item.productionDateString}'/>&nbsp;</td>
				<td><c:out value='${item.periodOfValidity}'/>&nbsp;</td>
				<td><c:out value='${item.upLimit}'/>&nbsp;</td>
				<td><c:out value='${item.downLimit}'/>&nbsp;</td>
				<td><c:out value='${item.upLimit1}'/>&nbsp;</td>
				<td><c:out value='${item.downLimit1}'/>&nbsp;</td>
				<td><c:out value='${item.symptomIdModel.modelTagValue}'/>&nbsp;</td>
				<td>
					<a href="${ctx}/pages/DrugBasicInfo/show.do?drugId=${item.drugId}&">查看</a>&nbsp;&nbsp;&nbsp;
					<a href="${ctx}/pages/DrugBasicInfo/edit.do?drugId=${item.drugId}&">修改</a>
				</td>
			  </tr>
			  
		  	  </c:forEach>
		  </tbody>
		</table>
	
		<simpletable:pageToolbar page="${page}">
		<s:property value="@com.dispensary.project.model.DrugBasicInfo@TABLE_ALIAS"/>列表
		</simpletable:pageToolbar>
	</div>
	</form>
</rapid:override>


<%@ include file="base.jsp" %>

