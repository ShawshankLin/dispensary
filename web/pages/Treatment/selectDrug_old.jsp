<%@ page language="java" import="java.util.*"
	contentType="text/html;charset=UTF-8" pageEncoding="utf-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!doctype html>
<html class="no-js">
<head>
	<meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>选择药品</title>
  <meta name="description" content="选择药品">
  <meta name="keywords" content="form">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="icon" type="image/png" href="assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <link rel="stylesheet" href="../../styles/treatment/selectDrug.css" type="text/css">
  <link rel="stylesheet" href="${ctx}/assets/css/amazeui.css"/>
  <link rel="stylesheet" href="${ctx}/assets/css/admin.css">
<script type="text/javascript" src="../../scripts/json2.js"></script>
<script src="../../scripts/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	//从左边移到右边
	$("#singleRight").click(function() {
			var lbox = $("#leftBox option:selected");
			$(lbox).each(function() {
				$(lbox).remove();
				$("#rightBox").append(lbox);
			});
				$("#rightBox").attr("selectedIndex", -1);
		});
	//由右移到左
	$("#singleLeft").click(function() {
			var rbox = $("#rightBox option:selected");
			$(rbox).each(function() {
				$(rbox).remove();
				$("#leftBox").append(rbox);
			});
				$("#leftBox").attr("selectedIndex", -1);
		});

	//全部移动到右侧
	$("#allRight").click(function() {
			$("#leftBox option").appendTo("#rightBox");
		});
	//全部移动到左侧
	$("#allLeft").click(function() {
			$("#rightBox option").appendTo("#leftBox");
		});

	//获取所有症状信息
	$.get("${ctx}/pages/Symptom/getSymptomInfos.do?nocache="+ new Date().getTime(), function(data) {
			var obj = JSON.parse(data);
			$.each(obj, function(i) {
				
				$(".symptomBox").append("<option value='"+obj[i].symptomId+"'>"+ obj[i].symptomName + "</option>");
			});
		});

	//获取症状对应的药品列表
	$(".symptomBox").change(function() {
			$("#leftBox").empty();
			$.get("${ctx}/pages/Symptom/getSymptom_drugs.do?symptomId="+ $(".symptomBox").val()+ "&nocache="+ new Date().getTime(), function(data) {
				var obj = JSON.parse(data);
				$.each(obj, function(i) {
					$("#leftBox").append("<option value='" + obj[i].drugId + "'>"+ obj[i].drugName+ "</option>");
					});
				});
			});			
		});
	
	//设置父窗口处方表选择的药品名称
	function save(){
		var drug_box=$("#rightBox");
		var tab =$("table[id='drug_table']>tbody",window.opener.document);
		//var rownum = tab.children("tr").length;
		//var colnum = $("table[id='drug_table']>thead>tr",window.opener.document).children("th").length;
		//判断药品是否已经存在，存在加1
		$.each(tab.children("tr"),function(i,item1){ 
			   var had = $(item1).children("td:eq(1)").children("input:eq(1)").val();
			   $.each(drug_box[0].options,function(j,item2){
				   if(item2.value==had){
					   $(item1).children("td:eq(7)").children("input").val(parseInt($(item1).children("td:eq(7)").children("input").val())+1);
					   drug_box[0].remove(j);
				   }
			   });
		 });
		drug_box=$("#rightBox");
		$.each(drug_box[0].options,function(i,item){
			$.get("${ctx}/pages/DrugBasicInfo/getDrugInfo.do?drugId="+drug_box[0].options[i].value,function(data){
				var drugInfos=JSON.parse(data);
				tab.append("<tr align='center'><td><a class='pure-button red' onclick='removeTr(this)'><i class='fa fa-times'></i></a></td><td><input type='hidden' name='prescriptionInfoDetails.makeNew["+i+"].id' value='"+i+"'/><input type='text' name='prescriptionInfoDetails.makeNew["+i+"].drugId' value='"+drugInfos.drugId+"' size='5' readonly/></td><td><a href='javascript:void(0)' onclick='window.open(\"${ctx}/pages/DrugBasicInfo/edit.do?drugId="+drugInfos.drugId+"\",\"回复窗口\", \"height=500, width=600, top=150, left=300, toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no\")'>"+drugInfos.drugName+"</td><td><select name='prescriptionInfoDetails.makeNew["+i+"].times'><option value='qd'>qd</option><option value='qod'>qod</option><option value='tid'>tid</option><option value='qn'>qn</option></select></td><td><input type='text' name='prescriptionInfoDetails.makeNew["+i+"].days' size='10' placeholder='必填' required/></td><td><input type='text' name='prescriptionInfoDetails.makeNew["+i+"].note' size='20'/></td><td>"+drugInfos.retailPrice+"</td><td><input type='text' name='prescriptionInfoDetails.makeNew["+i+"].amount' size='5' value='1' placeholder='必填' required onkeyup='countPrice(this)'/></td><td><input type='text' name='prescriptionInfoDetails.makeNew["+i+"].drugSum' value='"+drugInfos.retailPrice+"' size='5' readonly/></td></tr>");			
			});	
		});
		$("#rightBox").empty();
	}
</script>
</head>
<body>
	<div class="symptom">
		<select name="symptomBox" tabindex="3" class="symptomBox">
		</select>
	</div>
	<div class="drug">
		<table width="100%">
			<tr>	
				<td width="40%">
					<select id="leftBox" size="15" style="width:100%;" multiple="multiple"></select></td>
				<td width="20%" align="center">
					<button class="am-btn am-btn-default am-inco-angle-right" id="singleRight" type="button">
					</button> <br> <br>
					<button class="am-btn am-btn-default am-inco-double-right" id="allRight" type="button">
					</button> <br> <br>
					<button class="am-btn am-btn-default am-icon-angle-left" id="singleLeft" type="button">
					</button> <br> <br>
					<button class="am-btn am-btn-default am-icon-double-left" id="allLeft" type="button">
					</button>
				</td>
				<td width="40%">
					<select id="rightBox" size="15"	style="width:100%;" multiple="multiple"></select>
				</td>
			</tr>
			<tr align="center">
				<td colspan="3">
					<button class="am-btn am-btn-primary"
						onclick="save()">保存</button>
					<button class="am-btn am-btn-primary" id="close"
						onclick="javascript:window.close()">取消</button>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
