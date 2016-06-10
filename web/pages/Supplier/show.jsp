<%@page import="com.dispensary.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<link href="<c:url value="/styles/style.css"/>" type="text/css" rel="stylesheet">
	<title><%=Supplier.TABLE_ALIAS%>信息</title>
</rapid:override>

<rapid:override name="content">
	<s:form action="/pages/Supplier/list.do" method="get" theme="simple">
		<input type="button" value="返回列表" onclick="window.location='${ctx}/pages/Supplier/list.do'"/>
		<input type="button" value="后退" onclick="history.back();"/>
		
		<s:hidden name="supplierId" id="supplierId" value="%{model.supplierId}"/>
	
		<table class="tablelist">
			<tr>	
				<td class="tdLabel"><%=Supplier.ALIAS_SUPPLIER_NAME%></td>	
				<td><s:property value='%{model.supplierName}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Supplier.ALIAS_CONTACTS%></td>	
				<td><s:property value='%{model.contacts}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Supplier.ALIAS_PINGYIN%></td>	
				<td><s:property value='%{model.pingyin}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Supplier.ALIAS_ADDRESS%></td>	
				<td><s:property value='%{model.address}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Supplier.ALIAS_USER_TEL%></td>	
				<td><s:property value='%{model.userTel}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Supplier.ALIAS_USER_MOBILE%></td>	
				<td><s:property value='%{model.userMobile}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Supplier.ALIAS_USER_EMAIL%></td>	
				<td><s:property value='%{model.userEmail}'/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Supplier.ALIAS_NOTE%></td>	
				<td><s:property value='%{model.note}'/>&nbsp;</td>
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