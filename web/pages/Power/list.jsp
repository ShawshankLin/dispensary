<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.dispensary.project.model.*" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title><%=Power.TABLE_ALIAS %>管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link rel="stylesheet" href="../../styles/pure-min.css">
	<link rel="stylesheet" href="../../styles/common.css" />
	<link rel="stylesheet" href="../../styles/font-awesome-4.2.0/css/font-awesome.min.css" />
	<script type="text/javascript" src="<c:url value="/widgets/simpletable/simpletable.js"/>"></script>	
	<script type="text/javascript" src="../../scripts/common.js"></script>
	<script type="text/javascript" src="../../scripts/jquery.js"></script>
	<script type="text/javascript" src="../../scripts/json2.js"></script>
	<script type="text/javascript" src="../../scripts/echo.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		// 分页需要依赖的初始化动作
		window.simpleTable = new SimpleTable('queryForm',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
	});
	//编辑权限信息
	function editPower(uri){
		var items=document.getElementsByName("items");
		for(var i=0;i<items.length;i++){
			if(items[i].checked==true){
				window.location.href=uri+"?"+items[i].value;
				return true;
			}
		}
		alert("请选择修改的行！");
		return false;
	}
	//删除权限信息
	function delPower(uri){
		if($(":checked[name]").length>0){
			$("#queryForm").attr("action","${ctx}/pages/Power/delete.do").submit();
		}else{
			alert("请选择删除的行！");
		}
		return false;	
	}
	
	</script>
  </head>
  
  <body>
    <div class="main pure-g">
		<!--页头 S-->
		<form id="queryForm" name="queryForm" action="<c:url value="/pages/Power/list.do"/>" method="post" style="display: inline;" class="pure-form">
			<div class="pure-u-3-4">
				<a class="pure-button green" href="${ctx}/pages/Power/create.do"><i class="fa fa-plus-square-o"></i>&nbsp;添加</a>
				<a class="pure-button blue" onclick="return editPower('${ctx}/pages/Power/edit.do')"><i class="fa fa-pencil-square-o"></i>&nbsp;编辑</a>
				<a class="pure-button red" href="javascript:void(0)" onclick="return delPower()"><i class="fa fa-trash-o fa-lg"></i>&nbsp;删除</a>
			</div>
			<!-- 搜索框 -->
			<!-- <div class="pure-u-1-4">
				<div class="ph-45">
					<input type="text" class="pure-input-rounded" placeholder="请输入学号/姓名查询" id="searchStuNum">
					<button class="pure-button yellow" href="javascript:void(0)" onclick="return searchStu()">
						<i class="fa fa-search">搜索</i>
					</button>
				</div>
				<div id="searchStuBox" class="resultbox"></div>
			</div> -->
			<!--页头 E-->
			<!--分割-->
			<div class="pure-u-24-24">
				<div class="mg"></div>
			</div>
			<!--页体 S-->
			<div class="pure-u-24-24" >
				<div style="overflow:auto;height:500px;border-bottom:1px solid #d3d3d3;">
				<table cellspacing="0" class="data_table">
					<thead>
						<tr>
							<th>序号</th>
							<th><input type="checkbox" name="item" id="allitem"
								onclick="checkBox(this)" /></th>
							<!-- 排序时为th增加sortColumn即可,new SimpleTable('sortColumns')会为tableHeader自动增加排序功能; -->
							<th sortColumn="PowerID"><%=Power.ALIAS_POWER_ID%></th>
							<th sortColumn="PowerName" ><%=Power.ALIAS_POWER_NAME%></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.result}" var="item" varStatus="status">
							<tr>
								<td>${page.thisPageFirstElementNumber + status.index}</td>
								<td><input type="checkbox" name="items"
									value="powerId=${item.powerId}"></td>
								<td><c:out value="${item.powerId }"/></td>
								<td><c:out value='${item.powerName}'/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
				<div class="pure-u-24-24">
					<simpletable:pageToolbar page="${page}" />
				</div>
			</div>
		</form>
	</div>
  </body>
</html>
