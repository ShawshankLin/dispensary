<%@page import="com.dispensary.project.model.*"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/commons/taglibs.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<link rel="stylesheet" href="../../styles/pure-min.css">
	<link rel="stylesheet" href="../../styles/common.css" />
	<link rel="stylesheet" href="../../styles/font-awesome-4.2.0/css/font-awesome.min.css" />
	<script type="text/javascript" src="<c:url value="/widgets/simpletable/simpletable.js"/>"></script>
	<script type="text/javascript" src="../../scripts/common.js"></script>
	<script type="text/javascript" src="../../scripts/jquery.js"></script>
	<script type="text/javascript" src="../../scripts/json2.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		// 分页需要依赖的初始化动作
		window.simpleTable = new SimpleTable('queryForm',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
	});
	//删除出库单单信息
	function delOutOrder(){
		if($(":checked[name]").length>0){
			$("#queryForm").attr("action","${ctx}/pages/DrugStockOut/delete.do").submit();
		}else{
			alert("请选择删除的行！");
		}
		return false;	
	}
	//查询出库单信息
	function searchOutOrder(value) {
		if(value!=null){
			$("#searchOutOrderInfo").val(value);
		}
		$("#searchOutOrderBox").html("");
		$("#searchOutOrderBox").css("display","none");
			$.get("${ctx}/pages/DrugStockOut/getOutOrderInfos.do?serialNumber="
					+ $("#searchOutOrderInfo").val() + "&nocache="
					+ new Date().getTime(), function(data) {
				var obj = JSON.parse(data);
				var table=$(".data_table tbody");
				if(data!=null){
					table.empty();
					$.each(obj,function(i,item){
						table.append("<tr><td>"+(i+1)+"</td><td><input type='checkbox' name='items' value='"+item.id+"'></td><td>"+item.stockOutId+"</td><td>"+item.serialNumber+"</td><td>"+item.total+"</td><td>"+item.sum+"</td><td>"+item.operatorId+"</td><td>"+ChangeDateFormat(item.outDate)+"</td></tr>");
					});
				}else{
					table.empty();
				}
				
			});
		return false;
	}
 	//动态搜索出库单
    $(document).ready(function(){
        $("#searchOutOrderInfo").keyup(function(event){
	        //获取当前文本框的值
	        var seachText=$("#searchOutOrderInfo").val();
	        if(seachText!=""){
	        	$.post("${ctx}/pages/DrugStockOut/getOutOrderInfos.do",{"stockOutId":seachText,"serialNumber":seachText},function(data){
	        		console.log(data);
	        		var orders=JSON.parse(data);
    	    		//构造显示页面
    	        	var panel="<select style='width:300px' multiple='multiple' size='10' ondblclick='searchOutOrder(this.value)'><option disabled='disabled'>出库单号&nbsp;&nbsp;出库流水号&nbsp;&nbsp;经手人&nbsp;&nbsp;</option>";
    	        	//遍历解析json
    	        	$.each(orders,function(id, item){
    	        		//如果包含则为option赋值
    	        		panel+="<option value='"+item.serialNumber+"'>"+item.stockOutId+"&nbsp;&nbsp;"+item.serialNumber+"&nbsp;&nbsp;"+item.operatorId+"&nbsp;&nbsp;</option>";
    		         });
    	        	panel+="</select>";
    	         	$("#searchOutOrderBox").html(panel);
    	         	$("#searchOutOrderBox").slideDown("2000").css("display","block");
	        	});
	         }else{
	        	 $("#searchOutOrderBox").html("");
	        	 $("#searchOutOrderBox").css("display","none");
	         }
        });
    });
	</script>
  </head>
  
  <body>
	<div class="main pure-g">
		<!--分割-->
		<div class="pure-u-24-24">
			<div class="mg"></div>
		</div>
		<!--页头 S-->
		<form id="queryForm" name="queryForm" action="<c:url value="/pages/DrugStockOut/list.do"/>" method="post" style="display: inline;" class="pure-form">
		<div class="pure-u-3-4">
			<a class="pure-button green" href="${ctx}/pages/DrugStockOut/create.do">
			 	<i class="fa fa-plus-square-o"></i>&nbsp;添加
			</a> 
			<a class="pure-button red" href="javascript:void(0)" onclick="return delOutOrder()">
				<i class="fa fa-trash-o fa-lg"></i>&nbsp;删除
			</a>
		</div>
		<div class="pure-u-1-4">
			<div class="ph-45">
					<input type="text" class="pure-input-rounded" placeholder="请输入出库单/流水号" id="searchOutOrderInfo"> 
					<a class="pure-button yellow" href="javascript:void(0)" onclick="searchOutOrder()">
						<i class="fa fa-search">搜索</i>
					</a>
			</div>
			<div id="searchOutOrderBox" class="resultbox"></div>
		</div>
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
							<th sortColumn="StockOutID" ><%=DrugStockOut.ALIAS_STOCK_OUT_ID%></th>
							<th sortColumn="SerialNumber" ><%=DrugStockOut.ALIAS_SERIAL_NUMBER%></th>		
							<th sortColumn="Total" ><%=DrugStockOut.ALIAS_TOTAL%></th>
							<th sortColumn="Sum" ><%=DrugStockOut.ALIAS_SUM%></th>
							<th sortColumn="OperatorID" ><%=DrugStockOut.ALIAS_OPERATOR_ID%></th>
							<th sortColumn="OutDate" ><%=DrugStockOut.ALIAS_OUT_DATE%></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.result}" var="item" varStatus="status">
							<tr>
								<td>${page.thisPageFirstElementNumber + status.index}</td>
								<td><input type="checkbox" name="items"
									value="id=${item.id}"></td>
								<td><c:out value='${item.stockOutId}'/>&nbsp;</td>
								<td><c:out value='${item.serialNumber}'/>&nbsp;</td>
								<td><c:out value='${item.total}'/>&nbsp;</td>
								<td><c:out value='${item.sum}'/>&nbsp;</td>
								<td><c:out value='${item.operatorIdModel.modelTagValue}'/>&nbsp;</td>
								<td><c:out value='${item.outDateString}'/>&nbsp;</td>
							</tr>

						</c:forEach>
					</tbody>
				</table>
				</div>
				<div class="pure-u-22-24">
				<simpletable:pageToolbar page="${page}"/>
				</div>
			</div>
		</form>
	</div>

</body>
</html>
