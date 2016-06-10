<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@page import="com.dispensary.project.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!doctype html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>就诊</title>
  <meta name="description" content="这是一个就诊页面">
  <meta name="keywords" content="form">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="icon" type="image/png" href="${ctx}/assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="${ctx}/sassets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <link rel="stylesheet" href="${ctx}/assets/css/amazeui.css"/>
  <link rel="stylesheet" href="${ctx}/assets/css/admin.css">
  <link rel="stylesheet" href="${ctx}/assets/css/amazeui.chosen.css"/>
  <style type="text/css">
	.resultbox {
		width: 230px;
		height: 280px;
		background: none repeat scroll 0 0 #fff;
		display: none;
		z-index: 10000;
		position: absolute;
		top:20px;
		left:300px;
	}
	.resultbox select option {
		color: #05a;
	}
	</style>
  	<script type="text/javascript" src="../../scripts/jquery.js"></script>
	<script type="text/javascript" src="../../scripts/json2.js"></script>
	<script type="text/javascript" src="../../scripts/common.js"></script>
	<script type="text/javascript">
	//删除行
  	function romoveTr(obj){
  		$(obj).parent().parent().parent().remove();
  		//每次删除一次药品，重新排序
  		sort();
  	}

  	//批量删除行
	function romoveBatchTr(){
		if($(":checked[name]").length>0){
			$.each($(":checked[name=items]"),function(i,item){
				$(item).parent().parent().remove();
			});
			//每次删除一次药品，重新排序
	  		sort();
		}else{
			$("#message").text("请选择删除的行！");
			$("#alert").modal('open');
		}
		return false;	
	}
	//即时计算药品价钱
	function countPrice(obj){
		var sum =$(obj).parent().next().find("input:text");
		var price=$(obj).parent().prev();
		sum.val(accMul($(obj).val(),price.text()));
	}
	//查找学生信息
	function search(value) {
		if(value!=null){
			$("#searchInfo").val(value);	
		}
		var searchInfo=encodeURI(encodeURI($("#searchInfo").val().trim()));
		var searchType=$("ul[id='searchType']").find("li[class='am-active']").attr("value");
		$("#searchStu").attr("action","${ctx}/pages/Student/visitSearch.do?searchType="+searchType+"&searchInfo="+searchInfo).submit();
	}
	//采用冒泡事件设置div隐藏 
  	function stopPropagation(e) {
        if (e.stopPropagation) 
            e.stopPropagation();
        else 
            e.cancelBubble = true;
    }
    $(document).live('click',function(){
    	$('#searchStuBox').css('display','none');
    });
    $('#searchStuBox').live('click',function(e){
        stopPropagation(e);
    });
  	//判断是否已经搜索
	function isSearch(){
		if($("#stuNum").val()==""){
			$("#addPch").html("").html("<div class='am-alert am-alert-warning' data-am-alert><button type='button' class='am-close'>&times;</button><p>请先搜索学生!</p></div>");
			$("#showPch").html("").html("<div class='am-alert am-alert-warning' data-am-alert><button type='button' class='am-close'>&times;</button><p>请先搜索学生!</p></div>");
			return false;
		}
		return true;
	}
  	//效验病历
	function check(){
		if(!isSearch()){
			return false;
		}
		if($("table[id='drug_table']>tbody").children("tr").length<=0){
			$("#message").text("请添加处方药品!");
			$("#alert").modal('open');
			return false;
		}
		var flat=true;
		/* $.each($(".days"),function(i,item){
			if(item.value==""){
				$("#message").text("请填写天数!");
				$("#alert").modal('open');
				flat=false;
				return false;
			}
		}); */
		$.each($(".nums"),function(i,item){
			if(item.value==""){
				$("#message").text("请填写数量!");
				$("#alert").modal('open');
				flat=false;
				return false;
			}
		});
		//调用处方确认框
		if(flat){
			confirm();
			$("#confirm").modal({target: '#confirm', closeViaDimmer: 0, width: 960});
		}
	}
	//获取药品单位名称
	function getDrugunitName(drugUnitId){
		var drugUnitName;
		$.ajax({
			data: "get",
			url: "${ctx}/pages/Drugunit/getDrugunitJSON.do",
			data: "drugUnitId=" + drugUnitId,
			cache: false,
			async: false,//修改ajax为同步
			success: function (data) {
				var drugunit=JSON.parse(data);	
	 			drugUnitName=drugunit.drugUnitName;   
			}
		})
		return drugUnitName;
	}
	function confirm(){
		var printDrugs=$("table[id='printDrugs']>tbody");
		var drugFee=$("#drugFee");
		var registerFee=$("#registerFee");
		var otherFee=$("#otherFee");
		var tr=$("table[id='drug_table']>tbody").children("tr");
		$.each(tr,function(i,item){
			var drugId=$(item).children("td").eq(0).children("input").eq(2).val();
			$.get("${ctx}/pages/DrugBasicInfo/getDrugJSON.do?drugId="+drugId+"&nocache="+new Date().getTime(),function(data){
				var drug=JSON.parse(data);
				$.each(drug,function(j,drug){
					/* printDrugs.append("<tr><td>"+$(item).children("td").eq(2).text()+"</td><td>"+drug.drugSpec
							+"</td><td>"+getDrugunitName(drug.drugKickBack)+"</td><td>"+$(item).children("td").eq(3).children("select").val()
							+"</td><td>"+getDrugunitName(drug.quantityUnit)+"</td><td>"+$(item).children("td").eq(4).children("input").val()
							+"</td><td>"+$(item).children("td").eq(7).children("input").val()+"</td><td>"+drug.retailPrice+"</td></tr>"); */
					//判断添加药品的费用类型，添加对应的div中
					if(drug.feeTypeId==1){
						drugFee.append("<tr><td>"+$(item).children("td").eq(2).text()+"&nbsp;&nbsp;</td><td><div>"+drug.drugSpec+"<span style='margin-left:20px'></span></div><div>sig:"+$(item).children("td").eq(3).text()+"&nbsp;&nbsp;"+getDrugunitName(drug.drugKickBack)+"</div></td><td>￥"+drug.retailPrice+"</td></tr>");
					}else if(drug.feeTypeId==2){
						registerFee.append("<tr><td>"+$(item).children("td").eq(2).text()+"&nbsp;&nbsp;</td><td><div>"+drug.drugSpec+"<span style='margin-left:20px'></span></div><div>sig:"+$(item).children("td").eq(3).text()+"&nbsp;&nbsp;"+getDrugunitName(drug.drugKickBack)+"</div></td><td>￥"+drug.retailPrice+"</td></tr>");
					}else{
						otherFee.append("<tr><td>"+$(item).children("td").eq(2).text()+"&nbsp;&nbsp;</td><td><div>"+drug.drugSpec+"<span style='margin-left:20px'></span></div><div>sig:"+$(item).children("td").eq(3).text()+"&nbsp;&nbsp;"+getDrugunitName(drug.drugKickBack)+"</div></td><td>￥"+drug.retailPrice+"</td></tr>");
					}
					
				});
				
			});
			
		});
		$("#print_total_cost").text("总计："+$("#total_cost").val()+"元");
	}
	//提交表单
	function submit(){
		var form=$("#caseform").serialize();
		console.log(form);
		var params = decodeURIComponent(form,true); //反序列化 
		//console.log(params);
		$.ajax({
            type: "post",
            url: "${ctx}/pages/PatiCaseHistory/save.do",
            data:params,
            dataType: "text",
            success: function(data,status){
            	if(status=="success"){
            		if(data.trim()=="SUCCESS"){
            			doPrint();            			
            			window.location.href="${ctx}/pages/PatiCaseHistory/list.do";
            		}
            	}
            },
			error:function(data){
				alert("打印失败，请重新尝试！");
			}
        });
	}
	//排序
	function sort(){
		var tab =$("table[id='drug_table']>tbody");
		$.each(tab.children("tr"),function(i,item){
			$(item).children("td:eq(1)").text(i+1);
			
		});
	}
	function addDrug(sel){
		var tab =$("table[id='drug_table']>tbody");
		//判断药品是否已经存在，存在加1
		var rownum = tab.children("tr").length;
		var select;
		if(sel!=null){
			select=sel;
		}else{
			select=$("#drugBox").val();
		}
		var flat=true;
		if(rownum!=0){
			$.each(tab.children("tr"),function(i,item){
				var had=$(item).children("td:eq(0)").children("input:eq(2)").val();
				if(had==select){
					flat=false;
					return false; 
				}
			});
			if(!flat){
				return;
			}
		}
		if(flat){
			$.get("${ctx}/pages/DrugBasicInfo/getDrugJSON.do?drugId="+select,function(data){
				var drugInfos=JSON.parse(data);
				$.each(drugInfos,function(i,item){
					rownum=tab.children("tr").length;
					tab.append("<tr><td><input type='checkbox' name='items'><input type='hidden' name='prescriptionInfoDetails.makeNew["+rownum+"].id' value='"+rownum+"'/>"+
							"<input type='hidden' name='prescriptionInfoDetails.makeNew["+rownum+"].drugId' value='"+item.drugId+"'/></td><td>"+(rownum+1)+"</td>"+
							"<td><a href='javascript:void(0)'>"+item.drugName+"</td>"+
							"<td>"+item.drugUsage+"</td>"+
							"<td><input type='text' name='prescriptionInfoDetails.makeNew["+rownum+"].note' placeholder='有什么要嘱咐的话吗~'/></td><td>"+item.retailPrice+"</td><td><input type='text' class='nums' name='prescriptionInfoDetails.makeNew["+rownum+"].amount' size='5' value='1' placeholder='必填' required onkeyup='countPrice(this)'/></td>"+
							"<td><input type='text' name='prescriptionInfoDetails.makeNew["+rownum+"].drugSum' value='"+item.retailPrice+"' size='5' readonly/></td>"+
							"<td><div class='am-btn-group'><button class='am-btn am-btn-xs am-btn-danger' onclick='romoveTr(this)'>删除</button><div class='am-dropdown' data-am-dropdown><button class='am-btn am-btn-danger am-btn-xs am-dropdown-toggle' data-am-dropdown-toggle> <span class='am-icon-caret-down'></span></button><ul class='am-dropdown-content'><li><a href='${ctx}/pages/DrugBasicInfo/search.do?searchType=drugId&searchInfo="+item.drugId+"'>1.查看详情</a></li></ul></div></div></td></tr>");			
					
				});
				
				$('[data-am-dropdown]').dropdown();
			});	
		}
	}
	
	$(document).ready(function(){
		//获取学生数据
	   	$("#searchInfo").keyup(function(event){
	       	//获取当前文本框的值
		    var searchInfo=encodeURI(encodeURI($("#searchInfo").val().trim()));
		  	//输入字符大于4的时候开始搜索
		  	if(searchInfo.length>6){
		  		var searchType= $("ul[id='searchType']").find("li[class='am-active']").attr("value");
			    $.get("${ctx}/pages/Student/getStuJSON.do?searchType="+searchType+"&searchInfo="+searchInfo,function(data){
		       		 var stulist=JSON.parse(data);
		       		 if(stulist!=""){
		   	        	//构造显示页面
		   	        	var panel="<select style='width:230px;' size='15' ondblclick='return search(this.value)'><option disabled='disabled'>学号&nbsp;&nbsp;姓名&nbsp;&nbsp;</option>";
		   	        	//遍历解析json
		   	        	$.each(stulist,function(i, item){
		     	        	//如果包含则为option赋值
		   	        		panel+="<option value='"+item.stuNum+"'>"+item.stuNum+"&nbsp;&nbsp;"+item.stuName+"</option>";
		   	         	});
		   	        	panel+="</select>";
		   	         	$("#searchStuBox").html(panel);
		   	         	$("#searchStuBox").slideDown("2000").css("display","block");
		   	         }
		        });
		  	}
		    
	    });
		//添加症状
	   	$.get("${ctx}/pages/Symptom/getSymptomInfos.do?nocache="+ new Date().getTime(), function(data) {
			var symptoms = JSON.parse(data);
			$("#symptomBox").append("<option disabled='disabled' selected>---请选择症状---</option>");
			$.each(symptoms, function(i,item) {
				var symptom="<option value='"+item.symptomId+"'>"+item.symptomName+"</option>";
				$("#symptomBox").append(symptom);
				$("#symptomBox").trigger('chosen:updated');
			});
			
			//$("#symptomBox").data('amui.selected').destroy(); 
			//$("#symptomBox").removeData('amui.selected'). selected();
			
		});
		
	  	//获取症状对应的药品列表
		$("#symptomBox").change(function() {
			$.get("${ctx}/pages/DrugSymptomRelation/getSymptomDrugJSON.do?symptomId="+ $("#symptomBox").val()+ "&nocache="+ new Date().getTime(), function(data) {
				var obj = JSON.parse(data);
				$("#drugBox").empty();
				$("#drugBox").append("<option disabled='disabled' selected>-----请选择药品-----</option>");
				$.each(obj, function(i) {
					$("#drugBox").append("<option value='"+obj[i].drugId+"'>"+ obj[i].drugName+"&nbsp;&nbsp;&nbsp;&nbsp;"+obj[i].retailPrice+"元</option>");
					$("#drugBox").trigger('chosen:updated');
					//添加选中药品到表格
					addDrug(obj[i].drugId);
				});
				//$("#drugBox").data('amui.selected').destroy(); 
				//$("#drugBox").removeData('amui.selected'). selected();
			});
			
			$.get("${ctx}/pages/DrugSymptomRelation/getOtherSymptomDrugJSON.do?symptomId="+ $("#symptomBox").val()+ "&nocache="+ new Date().getTime(), function(data) {
				var obj = JSON.parse(data);
				//添加其他症状药品到下拉框
				$("#drugBox").append("<option disabled='disabled'>-----其他症状药品-----</option>");
				$.each(obj, function(i) {
					$("#drugBox").append("<option value='"+obj[i].drugId+"'>"+ obj[i].drugName+"&nbsp;&nbsp;&nbsp;&nbsp;"+obj[i].retailPrice+"元</option>");
					$("#drugBox").trigger('chosen:updated');
				});
			});
			
			
		});
	  	//chrome不支持click事件，判断版本添加change事件
		/* var isChrome = window.navigator.userAgent.indexOf("Chrome") !== -1;
		if(isChrome){
			$("#drugBox").change(function(){
				addDrug();
			});
		} */
		
	  	//计算总价钱
		setInterval(function () {
			var tab =$("table[id='drug_table']>tbody tr");
			var sum=0;
			$.each(tab,function(i,item){
				var td=$(item).children("td").eq(7).children(":input");
				sum=accAdd(sum,Number(td.val()));
			});
			$("#total_cost").val(sum.toFixed(2));
	    }, 1000);
	  	
	  	//清空table[id='printDrugs']
		$("#confirm").on('closed.modal.amui', function() {
			var printDrugs=$("table[id='printDrugs']>tbody");
			printDrugs.empty();
		});
		//激活select
	    $(".chosen-select").chosen();
	});
		
	</script>
	<style type="text/css">
	.printtable td{
		line-height: 1.6;
		padding: 0.5rem;
	}
</style>
</head>

<body>
<div class="am-g am-g-collapse">
  <div class="am-u-lg-12"><jsp:include page="../head.jsp"/></div>
  <div class="am-u-lg-2"><jsp:include page="../menu.jsp"/></div>
  <div class="am-u-lg-10">
		<div class="am-cf admin-main">
		<!-- content start -->
		  <div class="admin-content">
		    <div class="am-cf">
		      <div class="am-fl am-cf am-padding"><strong class="am-text-primary am-text-lg">就诊</strong> / <small>Treatment</small></div>
		      
		      <form id="searchStu" name="searchStu" class="am-topbar-form am-form-inline am-topbar-right" action="#" method="post">
			      <div class="am-form-group">
		          	<div class="am-btn-group" style="display:none">
					  	<button type="button" class="am-btn  am-btn-secondary" onclick="search()">搜索</button>
					  	<div class="am-dropdown" data-am-dropdown>
						    <button class="am-btn am-btn-secondary am-dropdown-toggle" data-am-dropdown-toggle> <span class="am-icon-caret-down"></span></button>
						    <ul class="am-dropdown-content" id="searchType">
						      <li class="am-active" value="stuNum"><a href="javascript:void(0)"  onclick="setSearchType(this)">学生学号</a></li>
						      <li value="stuName"><a href="javascript:void(0)" onclick="setSearchType(this)">学生姓名</a></li>
						      <li value="idcard"><a href="javascript:void(0)" onclick="setSearchType(this)">学生身份证</a></li>
						    </ul>
					  	</div>
				  	</div>
			      </div>			       
			  </form>
			  
		    </div>
		    <hr/>
		    <div class="am-g">
		      <div class="am-u-sm-12 am-u-md-12 am-u-md-pull-12">
			      <div class="am-tabs am-margin-left am-margin-right" data-am-tabs="{noSwipe: 1}">
				    <ul class="am-tabs-nav am-nav am-nav-tabs">
				      <li><a href="#tab1">学生基本信息</a></li>
				      <li><a href="#tab2" onclick="return isSearch()">添加病历</a></li>
				      <li><a href="#tab3">查看病历</a></li>
				    </ul>
				    <div class="am-tabs-bd">
				    	<!-- 查看学生基本信息 -->
				    	<div class="am-tab-panel am-fade" id="tab1">
				    		<div class="am-g">
						     	<table class="am-table am-table-striped am-table-hover">
									<div>
										<tr>
											<td width="10%"><%=Student.ALIAS_STU_NUM%></td>
											<td><input type="text" class="am-form-field" size="20" placeholder="请输入学生学号 " id="searchInfo" onkeydown="enterSearch()" value="${stu.stuNum }"></td>
											<div id="searchStuBox" class="resultbox"></div>
										</tr>
									</div>
									
									<tr>
										<td><%=Student.ALIAS_STU_NAME%></td>
										<td>${stu.stuName}</td>
									</tr>
									<tr>
										<td><%=Student.ALIAS_SEX%></td>
										<td><c:if test="${not empty stu.sex }">${stu.sex==1?"男":"女"}</c:if></td>
									</tr>
									<tr>
										<td><%=Student.ALIAS_BIRTH_DATE%></td>
										<td>${stu.birthDateString}</td>
									</tr>
									<tr>
										<td><%=Student.ALIAS_TEL%></td>
										<td>${stu.tel}</td>
									</tr>
									<tr>
										<td><%=Student.ALIAS_IDCARD%></td>
										<td>${stu.idcard}</td>
									</tr>
									<tr>
										<td><%=Student.ALIAS_DEPARTMENT%></td>
										<td>${stu.department}</td>
									</tr>
									<tr>
										<td><%=Student.ALIAS_MAJOR%></td>
										<td>${stu.major}</td>
									</tr>
									<tr>
										<td><%=Student.ALIAS_STU_CLASS%></td>
										<td>${stu.stuClass}</td>
									</tr>
									<tr>
										<td><%=Student.ALIAS_ADDRESS%></td>
										<td>${stu.address}</td>
									</tr>
								</table>
		           			 </div>
		           			 
					    </div>
					    <!-- 添加病历 -->
				      	<div class="am-tab-panel am-fade" id="tab2">
				      		<div id="addPch">
						        <form id="caseform" class="am-form am-form-horizontal" action="${ctx}/pages/PatiCaseHistory/save.do" method="post">
						        <input type="hidden" name="stuNum" id="stuNum" value="${stu.stuNum}">
						          <div class="am-form-group">
						           	<label for="allergy" class="am-u-sm-1 am-form-label">就诊类型：</label>
						            <div class="am-btn-group" data-am-button>
						              <label class="am-btn am-btn-default am-btn-xs am-active">
						                <input type=radio name="primaryCare" value="初诊" checked="checked"> 初诊
						              </label>
						              <label class="am-btn am-btn-default am-btn-xs">
						                <input type="radio" name="primaryCare" value="复诊"> 复诊
						              </label>
					          		</div>
						          </div>
						          
						          <div class="am-form-group">
						            <label for="dispensaryRecord" class="am-u-sm-1 am-form-label"><%=PatiCaseHistory.ALIAS_DISPENSARY_RECORD %>：</label>						     
								     <div class="am-btn-group" data-am-button>
						              <label class="am-btn am-btn-default am-btn-xs ">
						                <input type=radio name="dispensaryRecord" value="是"> 是
						              </label>
						              <label class="am-btn am-btn-default am-btn-xs am-active">
						                <input type="radio" name="dispensaryRecord" value="否" checked="checked"> 否
						              </label>
					          		</div>					            
						          </div>
						          
						          <div class="am-form-group">
						            <label for="dispensaryRecord" class="am-u-sm-1 am-form-label"><%=PatiCaseHistory.ALIAS_ALLERGY %>：</label>						     
								     <div class="am-btn-group" data-am-button>
						              <label class="am-btn am-btn-default am-btn-xs ">
						                <input type=radio name="allergy" value="是"> 是
						              </label>
						              <label class="am-btn am-btn-default am-btn-xs am-active">
						                <input type="radio" name="allergy" value="否" checked="checked"> 否
						              </label>
					          		</div>					            
						          </div>
						          
						          <%-- <div class="am-form-group">
						            <label for="describes" class="am-u-sm-1 am-form-label"><%=PatiCaseHistory.ALIAS_DESCRIBES %>：</label>
						            <div class="am-u-sm-11 am-padding-right">
						              <input type="text" name="describes">
						            </div>
						          </div>
						          
						
						          <div class="am-form-group">
						            <label for="examineStatus" class="am-u-sm-1 am-form-label"><%=PatiCaseHistory.ALIAS_EXAMINE_STATUS %> ：</label>
						            <div class="am-u-sm-11 am-padding-right">
						              <input type="text" name="examineStatus">
						            </div>
						          </div> --%>
						
						          <div class="am-form-group">
						            <label for="firstImpress" class="am-u-sm-1 am-form-label"><%=PatiCaseHistory.ALIAS_FIRST_IMPRESS %>：</label>
						            <div class="am-u-sm-11 am-padding-right">
						              <textarea  rows="5" name="firstImpress"></textarea>
						            </div>
						          </div>
						          <div class="am-form-group">
						          	<label for="drugSum" class="am-u-sm-1 am-u-md-1 am-form-label">初步印象：</label>
						          	<div class="am-u-sm-8">
						          		<select id="symptomBox" class="chosen-select" data-placeholder="请选择症状" style="width:200px;">
								  		</select>
								  		<select id="drugBox" onchange="addDrug()" class="chosen-select" data-placeholder="请选择药品" style="width:200px;">
										</select>
										
						          	</div>
						          	<label for="drugSum" class="am-u-sm-1 am-u-md-1 am-form-label">总价钱：</label>
						          	<div class="am-u-sm-2">
						          		<input type="text" name="prescriptionInfos.makeNew[0].drugSum" id="total_cost" value="0" readonly>
						          	</div>
						          </div>
						       	  <input type="hidden" name="prescriptionInfos.makeNew[0].presId" value="0"/>
						          <div class="am-form-group">
						            	<!-- 处方表 -->
										<div style="overflow:auto;width:100%;">
											<table class="am-table am-table-bordered am-table-radius am-table-striped" width="800px" cellspacing="0" id="drug_table">
												<thead>
													<tr>
														<th><input type="checkbox" name="item" id="allitem"
												onclick="checkAllBox(this)" /></th>
														<th>序号</th>
														<th>药品名称</th>									
														<th>用法</th>
														<!--<th>天数</th> -->
														<th>备注</th>
														<th>单价/元</th>
														<th>数量</th>
														<th>金额</th>
														<th>操作</th>
													</tr>
												</thead>
												<tbody>						
												</tbody>
											</table>
										</div>
						          </div>	          					  
						          <div class="am-form-group">
						            <div class="am-u-sm-9 am-u-sm-push-3">
						              <button type="button" class="am-btn am-btn-default am-btn-danger" onclick="romoveBatchTr()">批量删除</button>
						              <button type="button" class="am-btn am-btn-primary" onclick="return check()">提交病历</button>
						              <button type="reset" class="am-btn am-btn-primary">重新输入</button>
						            </div>
						          </div>
						        </form>
						     </div>
					     </div>
					     <!-- 查看病历 -->
					     <div class="am-tab-panel am-fade" id="tab3">
					     	<div id="showPch">
						     <c:choose>
							     <c:when test="${empty stu.patiCaseHistorys}">
								     <div class="am-alert am-alert-success" data-am-alert>
									  <button type="button" class="am-close">&times;</button>
									  <p>无以往就诊记录。</p>
									</div>
							     </c:when>
							     <c:otherwise>
							     	
							     	<c:forEach items="${stu.patiCaseHistorys}" var="item" varStatus="status">	     		
							     		<table class="am-table am-table-striped am-table-hover">
							     			<caption><span class="am-text-primary"><c:out value="${item.caseId}"/></span><span class="am-fr"><small><%=PatiCaseHistory.ALIAS_VISIT_DATE %>：<c:out value="${item.visitDateString}"/></small></span></caption>
		    								<tr><td>就诊类型：</td><td><c:out value="${item.primaryCare}"/></td></tr>
		    								<%-- <tr><td><%=PatiCaseHistory.ALIAS_RE_EXAMINATION %>：</td><td><c:out value="${item.reExamination}"/></td></tr> --%>
		    								<tr><td><%=PatiCaseHistory.ALIAS_DISPENSARY_RECORD %>：</td><td><c:out value="${item.dispensaryRecord}"/></td></tr>
		    								<tr><td><%=PatiCaseHistory.ALIAS_ALLERGY %>：</td><td><c:out value="${item.allergy}"/></td></tr>							
		    								<%-- <tr><td><%=PatiCaseHistory.ALIAS_DESCRIBES %>：</td><td><c:out value="${item.describes}"/></td></tr>
		    								<tr><td><%=PatiCaseHistory.ALIAS_EXAMINE_STATUS %>：</td><td><c:out value="${item.examineStatus}"/></td></tr> --%>
		    								<tr><td><%=PatiCaseHistory.ALIAS_FIRST_IMPRESS %>：</td><td><c:out value="${item.firstImpress}"/></td></tr>
		    						
										</table>
							     	</c:forEach>
						     		
						     	</c:otherwise>
						     </c:choose>
					     	</div>
					     </div>
					  </div>
			      </div>
		      </div>
		    </div>
		  </div>
  		<!-- content end -->
		</div>
  </div>
</div>
<!-- 消息框 -->
<div class="am-modal am-modal-alert" tabindex="-1" id="alert">
  <div class="am-modal-dialog">
    <div class="am-modal-hd">消息</div>
    <div class="am-modal-bd" id="message">
      
    </div>
    <div class="am-modal-footer">
      <span class="am-modal-btn">确定</span>
    </div>
  </div>
</div>
<!-- 打印处方单 -->

<div class="am-modal am-modal-no-btn" tabindex="-1" id="confirm">	
  	<div class="am-modal-dialog" style="width:960px;margin:0 auto">
  		<div style="overflow:auto;width:100%;height:500px">
  		<!--startprint-->
    	<div class="am-modal-hd">
      		<!-- 游览器可见，打印机不可见 -->
    		<div class="am-print-block" style="text-align: center;">东莞理工学院城市学院处方笺</div>
      		<!-- 打印机可见，游览器不可见 -->
      		<div class="am-print-hide">东莞理工学院城市学院处方笺<a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a></div>
    	</div>
   		<div class="am-g" style="border-top:1px solid #000000;">
      		<table>
      			<tr>
      			<td style="border-right:1px solid #000000;" valign="top">
	      			<div style="text-align: left;font-weight:bold">&nbsp;&nbsp;初步印象：</div>
		  			<table class="printtable" id="printStu" style="width:200px;">
	   					<!-- <tr><td width="50%"><%=Student.ALIAS_STU_NUM%>：</td><td>${stu.stuNum}</td></tr> -->
	   					<tr><td>姓名：</td><td>${stu.stuName}</td></tr>
	   					<tr><td>性别：</td><td><c:if test="${not empty stu.sex }">${stu.sex==1?"男":"女"}</c:if></td></tr>
	   					<tr><td>年龄：</td><td>${stu.birthDateString}</td></tr>
	   					<tr><td>类别：</td><td>${stu.stuClass}</td></tr>
	   					<tr><td>电话：</td><td>${stu.tel}</td></tr>
	   					<jsp:useBean id="now" class="java.util.Date"></jsp:useBean>
	   					<tr>
		   					<td>时间：</td>
		   					<td>
		   					（当天有效）<br>
		   					<fmt:formatDate value="${now}"  pattern="yyyy-MM-dd"/>
		   					</td>
	   					</tr>
	 				</table>
		  		</td>
			<td valign="top">
			  	<table class="printtable" id="printDrugs" style="width:760px">
			  		<thead>
						<tr>
							<th style="font-size:22px;font-weight:bold" colspan="3">&nbsp;&nbsp;℞</th>
						</tr>
					</thead>
					<tbody id="drugFee">									
					</tbody>
					<tbody id="registerFee">									
					</tbody>
					<tbody id="otherFee">									
					</tbody>
			  	</table>
			</td>
			</tr>
			</table>
	</div>
	<table width="100%">
			<tr>
				<td></td>
				<td></td>
				<td><h3 id="print_total_cost"></h3></td>
			</tr>
			<tr>
				<td><h3>医生签名：</h3></td>
				<td><h3>核对/发药：</h3></td>
				<td></td>
			</tr>
	</table>
	<!--endprint-->
	</div>
	<!-- 在打印预览中查看效果 -->
	<div class="am-print-hide"><button type="button" class="am-btn am-btn-primary am-btn-block" onclick="submit()">提交并打印</button></div>	
  </div>	
</div>

<!--[if lt IE 9]>
<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="${ctx}/assets/js/polyfill/rem.min.js"></script>
<script src="${ctx}/assets/js/polyfill/respond.min.js"></script>
<script src="${ctx}/assets/js/amazeui.legacy.js"></script>
<![endif]-->

<!--[if (gte IE 9)|!(IE)]><!-->
<script src="${ctx}/assets/js/jquery.min.js"></script>
<script src="${ctx}/assets/js/amazeui.min.js"></script>
<script src="${ctx}/assets/js/amazeui.chosen.min.js"></script>
<!--<![endif]-->
<script src="${ctx}/assets/js/app.js"></script>
</body>
</html>

