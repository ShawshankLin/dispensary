<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<link href="<c:url value="/styles/style.css"/>" type="text/css" rel="stylesheet">
	<title><%=DrugPlace.TABLE_ALIAS%>信息</title>
</rapid:override>

<rapid:override name="content">
	<s:form action="/pages/DrugPlace/list.do" method="get" theme="simple">
		<input type="button" value="返回列表" onclick="window.location='${ctx}/pages/DrugPlace/list.do'"/>
		<input type="button" value="后退" onclick="history.back();"/>
		
		<s:hidden name="drugPlaceId" id="drugPlaceId" value="%{model.drugPlaceId}"/>
	
		<table class="tablelist">
			<tr>	
				<td class="tdLabel"><%=DrugPlace.ALIAS_DRUG_PLACE%></td>	
				<td><s:property value='%{model.drugPlace}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=DrugPlace.ALIAS_STATUS%></td>	
				<td><s:property value='%{model.status}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=DrugPlace.ALIAS_PLACE_TYPE%></td>	
				<td><s:property value='%{model.placeType}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=DrugPlace.ALIAS_IS_STOREROOM%></td>	
				<td><s:property value='%{model.isStoreroom}'/>&nbsp;</td>
			</tr>
		</table>
	</s:form>
	<script>
		$(function(){
			$("tr:odd").addClass("odd");
			$("tr:even").addClass("even");
		});
	</script>
</rapid:override>


<%@ include file="base.jsp" %>