<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<%@page import="com.dispensary.project.model.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>填写<%=DrugStockOut.TABLE_ALIAS%></title> 
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link rel="stylesheet" href="../../styles/pure-min.css">
	<link rel="stylesheet" href="../../styles/common.css" />
	<link rel="stylesheet" href="../../styles/font-awesome-4.2.0/css/font-awesome.css" />
	<script type="text/javascript" src="../../scripts/jquery.js"></script>
	<script type="text/javascript" src="../../scripts/json2.js"></script>
	<script type="text/javascript" src="../../scripts/common.js"></script>
	<style type="text/css">
    .result{
   		width:300px;
   		height:280px;
		background: none repeat scroll 0 0 #fff;
    	display:none;
    	z-index: 10000;
	}
	.drugresult {
		position:absolute;
    	top:160px;
    	left:300px;
	}
	.result select option{
		color:#05a;
	}
    </style>
	<script type="text/javascript">
	//获取药品数据
    $(document).ready(function(){
    	//显示现在时间	
		document.getElementById('outDate').defaultValue=getDate();
		//查询药品
        $("#seachDrug").keyup(function(event){
	        //获取当前文本框的值
	        var seachText=$("#seachDrug").val();
	        var gys=$("#searchGys").val();
	        if(seachText!=""){
	        	$.post("${ctx}/pages/DrugBasicInfo/getDrugInfos.do",{"drugPingyin":seachText,"drugName":seachText,"supplierId":gys},function(data){
	        		var druglist=JSON.parse(data);
    	    		//构造显示页面
    	        	var panel="<select style='width:300px' multiple='multiple' size='10' ondblclick='return addDrug(this.value)'><option disabled='disabled'>药品编号&nbsp;&nbsp;拼音码&nbsp;&nbsp;药品名称&nbsp;&nbsp;</option>";
    	        	//遍历解析json
    	        	$.each(druglist,function(id, item){
    	        		//如果包含则为option赋值
    	        		panel+="<option value='"+item.drugId+"'>"+item.drugId+"&nbsp;&nbsp;"+item.drugPingyin+"&nbsp;&nbsp;"+item.drugName+"</option>";
    		         });
    	        	panel+="</select>";
    	         	$("#searchDrugBox").html(panel);
    	         	$("#searchDrugBox").slideDown("2000").css("display","block");
	        	});
	         }else{
	        	 $("#searchDrugBox").html("");
	        	 $("#searchDrugBox").css("display","none");
	         }
        });
        $("#searchDrugBox").mouseover(function(){
        	$("#searchDrugBox").css("display","block");
        }).mouseout(function(){
        	$("#searchDrugBox").css("display","none");
        });
    });
    //添加药品
    function addDrug(value){
    	var table=$(".data_table tbody");
    	//判断药品是否已经存在，存在加1
    	var flat=true;
    	if(flat==true){
			$.each(table.children("tr"),function(i,item1){ 
				   var had = $(item1).children("td:eq(1)").children("input").val();
				   if(value==had){
					   $(item1).children("td:eq(5)").children("input").val(parseInt($(item1).children("td:eq(5)").children("input").val())+1);
				   			flat=false;
					  	 	return false;
				   }
			});
    	}
    	if(flat==false){return false;}
    	var i=table.children("tr").length;
    	//添加药品行
		$.get("${ctx}/pages/DrugBasicInfo/getDrugInfo.do?drugId="+value+"&nocahce="+new Date().getTime(),
				function(data){
			var drug=JSON.parse(data);
			table.append("<tr align='center'><td><button class='pure-button red' onclick='delRow(this)'><i class='fa fa-times'></i></button><input type='hidden' name='drugStockOutDetails.makeNew["+i+"].id' value='"+i+"'/></td><td><input type='text' readonly name='drugStockOutDetails.makeNew["+i+"].drugId' size='5' value='"+drug.drugId+"'/></td><td><a href='javascript:window.open(\"${ctx}/pages/DrugBasicInfo/edit.do?drugId="+drug.drugId+"\",\"回复窗口\", \"height=500, width=600, top=150, left=300, toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no\")'>"+drug.drugName+"</a></td><td>"+drug.drugPingyin+"</td><td>"+drug.costPrice+"</td><td><input type='text' name='drugStockOutDetails.makeNew["+i+"].outAmount' size='5' value='1' onkeyup='countPrice(this)'/></td><td><input type='text' name='drugStockOutDetails.makeNew["+i+"].outPrice' size='5' readonly value="+drug.costPrice+"></td><td><select name='drugStockOutDetails.makeNew["+i+"].outPlaceId' onfocus='initDrugPlaces(this)'/></select></td><td><select name='drugStockOutDetails.makeNew["+i+"].fromPlaceId' onfocus='initDrugPlaces(this)'/></select></td></tr>");
		});
    }
  	//初始化药品位置信息
  	function initDrugPlaces(obj){
 		if(obj.length<=0){
 			$.get("${ctx}/pages/DrugPlace/getDrugPlaces.do?nocache="+new Date().getTime(),function(data){
 				var places=JSON.parse(data);
 				$.each(places, function(i,item) {
 					$(obj).append("<option value='"+item.drugPlaceId+"'>"+ item.drugPlace + "</option>");
 				});
 				
 			});
 		}
  	}
  	//删除行
  	function delRow(obj){
  		$(obj).parent().parent().remove();
  		
  	}
  	//生成新的出库单号
    function getNewStockOutId(){
    	$.get("${ctx}/pages/DrugStockOut/getStockOutId.do?nocahce="+new Date().getTime(),function(data){
    		$("#stockOutId").val(data);
    		getNewSerialNum();
    	});
    }
  	//生成新的流水号
  	function getNewSerialNum(){
  		$.get("${ctx}/pages/DrugStockOut/getSerialNum.do?stockOutId="+$("#stockOutId").val()+"&nocahce="+new Date().getTime(),function(data){
  			$("#serialNumber").val(data);
    	});
  	}
  	//即时计算药品价钱
	function countPrice(obj){
		var sum =$(obj).parent().next().find("input");
		var price=$(obj).parent().prev();
		sum.val($(obj).val()*price.text());
	}

	//计算总数量
  	function getTotalNum(){
  		var rows=$(".data_table>tbody").children("tr");
  		
  		var totalNum=0;
  		$.each(rows,function(i,item){
  			var num=$(item).children("td:eq(5)").children("input").val(); 
  			totalNum+=parseInt(num);
  		});
  		$("#totalNum").val(totalNum);
  	}
  	//计算总价格
  	function getTotalCost(){
  		var rows=$(".data_table>tbody").children("tr");
  		var totalCost=0;
  		$.each(rows,function(i,item){
  			var cost=$(item).children("td:eq(6)").children("input").val(); 
  			totalCost+=parseInt(cost);
  		});
  		$("#totalCost").val(totalCost);
  	}
  	//效验入库单
  	function checkEmpty(){
  		if($(".data_table>tbody").children("tr").length<=0){
  			alert("入库单药品不允许为空！");
  			return false;
  		}		
  	}
  	//初始化
    $(document).ready(function(){
    	//显示日期
    	$("#inDate").val(getDate());
    	//监听药品总数量变化
    	setInterval(getTotalNum, 1000);
    	//监听药品总价格变化
    	setInterval(getTotalCost, 1000);
    	//药品搜索框监听
    	$("#seachDrug").blur(function(){
    		$("#searchGysBox").css("display","none");
    	});
    	
	});
	</script>
  </head>
  
  <body>
		<div class="main pure-g">
			<form class="pure-form" action="${ctx}/pages/DrugStockOut/save.do" method="post">
				<div class="pure-u-24-24">
					<div class="ph-100">
						<fieldset>
						<legend><b>填写<%=DrugStockOut.TABLE_ALIAS%></b></legend>
						<table cellspacing="0">
							<tbody>
							<tr>
								<td>出库单号：</td>
								<td>
									<input type="text" size="14" id="stockOutId" name="stockOutId" placeholder="必填" required onkeyup="getNewSerialNum()">
									<a class="pure-button green" href="javascript:void(0)" onclick="getNewStockOutId()" title="生成新的出库单号"><i class="fa fa-plus-square-o"></i></a>
								</td>
								<td>流&nbsp;水&nbsp;号：</td>
								<td><input type="text" id="serialNumber" name="serialNumber" readonly></td>
								<td>经&nbsp;手&nbsp;人：</td>
								<td><input type="text" name=operatorId placeholder="必填" required value="${sessionScope.loginUser.meStId}"></td>
							</tr>
							<tr>										
								<td>当前时间：</td>
								<td><input type="date" readonly id="outDate" name="outDate"></td>
								<td>总数量</td>
								<td><input type="number" readonly  name="total" id="totalNum"></td>
								<td>总金额</td>
								<td><input type="number" readonly  name="sum" id="totalCost"></td>
							</tr>
							<tr>
								<td>搜索药品：</td>
								<td><input type="text" id="seachDrug">
								<div id="searchDrugBox" class="result drugresult"></div></td>
							</tr>
							</tbody>
							</table>
						</fieldset>
					</div>
					<div class="ph-400">
						<div style="overflow:scroll;height:400px;">
							<table cellspacing="0" class="data_table">
							<thead>
								<tr>
									<th>操作</th>
									<th sortColumn="DrugID"><%=DrugBasicInfo.ALIAS_DRUG_ID%></th>
									<th sortColumn="DrugName"><%=DrugBasicInfo.ALIAS_DRUG_NAME%></th>
									<th sortColumn="DrugPingyin"><%=DrugBasicInfo.ALIAS_DRUG_PINGYIN%></th>
									<th sortColumn="RetailPrice"><%=DrugBasicInfo.ALIAS_RETAIL_PRICE%></th>
									<th sortColumn="OutAmount"><%=DrugStockOutDetail.ALIAS_OUT_AMOUNT %></th>
									<th sortColumn="OutPrice"><%=DrugStockOutDetail.ALIAS_OUT_PRICE %></th>
									<th sortColumn="FromPlaceID"><%=DrugStockOutDetail.ALIAS_FROM_PLACE_ID %></th>
									<th sortColumn="OutPlaceID"><%=DrugStockOutDetail.ALIAS_OUT_PLACE_ID %></th>
								</tr>
							</thead>
							<tbody>
							</tbody>
							</table>
						</div>
					</div>
					<!--分割-->
					<div class="pure-u-24-24">
						<div class="mg"></div>
					</div>
					<div class="ph-50">
						备&nbsp;&nbsp;注：&nbsp;&nbsp;<textarea cols="90" rows="3"></textarea>
						<button type="submit" class="pure-button pure-button-primary">提交</button>
						<button type="button" class="pure-button pure-button-primary" onclick="history.back()">返回</button>
					</div>
			</form>
		</div>
	</body>
</html>
