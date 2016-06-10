<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@page import="com.dispensary.project.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title><%=DrugStockIn.TABLE_ALIAS %>添加</title>
  <meta name="description" content="这是一个入库单添加页面">
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
  <script src="${ctx}/assets/js/jquery.min.js"></script>
  <script type="text/javascript" src="../../scripts/common.js"></script>	
  <script type="text/javascript" src="../../scripts/json2.js"></script>
  <style type="text/css">
   /*初始化chosen插件  */
  .chosen-drop {
	position: absolute;
	top:100%;
	left: -9999px;
	z-index: 1010;
	width: 100%;
	border: 1px solid #bbb;
	border-top: 0;
	background: #fff;
	-webkit-box-shadow: 0 4px 5px rgba(0,0,0,.15);
	box-shadow: 0 4px 5px rgba(0,0,0,.15);
	}
	.sup_result {
		width: 210px;
		height: 280px;
		background: none repeat scroll 0 0 #fff;
		display: none;
		z-index: 10000;
		position: absolute;
		top: 20px;
		left:250px;
	}
	.sup_result select option {
		color: #05a;
	}
	</style>
  	<script type="text/javascript">
  	//获取药品存放位置的状态空或未满，库房类型
	var places;
	$.get("${ctx}/pages/DrugPlace/getDrugPlaceJSON.do?nocache="+new Date().getTime(),function(data){
			places=JSON.parse(data);
			console.log(places);
	});
	$(document).ready(function(){
		//获取供应商数据
	   	$("#searchInfo").keyup(function(event){
	       	//获取当前文本框的值
		    var searchInfo=encodeURI(encodeURI($("#searchInfo").val()));
	       	var searchType= $("ul[id='searchType']").find("li[class='am-active']").attr("value");
	       	$.get("${ctx}/pages/Supplier/getSupJSON.do?searchType="+searchType+"&searchInfo="+searchInfo,function(data){
	       		 var suplist=JSON.parse(data);
	       		 if(suplist!=""){
	   	        	//构造显示页面
	   	        	var panel="<select style='width:210px' size='15' ondblclick='return search(this.value)'><option disabled='disabled'>编号&nbsp;&nbsp;名称&nbsp;&nbsp;</option>";
	   	        	//遍历解析json
	   	        	$.each(suplist,function(i, item){
	     	        	//如果包含则为option赋值
	   	        		panel+="<option value='"+item.supplierId+"'>"+item.supplierId+"&nbsp;&nbsp;"+item.supplierName+"</option>";
	   	         	});
	   	        	panel+="</select>";
	   	         	$("#searchSupBox").html(panel);
	   	         	$("#searchSupBox").slideDown("2000").css("display","block");
	   	         }else{
	   	        	 $("#searchSupBox").html("");
	   	        	 $("#searchSupBox").css("display","none");
	   	         }
	       	  });
	       });

	});
  </script>
 
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
		      <div class="am-fl am-cf am-padding"><strong class="am-text-primary am-text-lg"><%=DrugStockIn.TABLE_ALIAS %></strong> / <small>添加</small></div>
			      <form class="am-topbar-form am-topbar-left am-form-inline am-topbar-right" id="searchSup" action="#" method="post">
				      <div class="am-form-group" style="display:none;">
					        <div class="am-btn-group">
							  	<button type="button" class="am-btn  am-btn-secondary" onclick="search()">搜索</button>
							  	<div class="am-dropdown" data-am-dropdown>
								    <button class="am-btn am-btn-secondary am-dropdown-toggle" data-am-dropdown-toggle> <span class="am-icon-caret-down"></span></button>
								    <ul class="am-dropdown-content" id="searchType">
								      <li class="am-active" value="supplierId"><a href="javascript:void(0)"  onclick="setSearchType(this)">供应商ID</a></li>
								      <li value="supplierName"><a href="javascript:void(0)" onclick="setSearchType(this)">供应商名称</a></li>
								      <li value="pingyin"><a href="javascript:void(0)" onclick="setSearchType(this)">供应商拼音</a></li>
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
				      <li><a href="#tab1">供应商基本信息</a></li>
				      <li><a href="#tab2" onclick="return isSearch()">添加入库单</a></li>
				    </ul>
				    <div class="am-tabs-bd">
				    	<!-- 查看供应商基本信息 -->
				    	<div class="am-tab-panel" id="tab1">
				    		<div class="am-g">
						     	<table class="am-table am-table-striped am-table-hover">
									<div>
										<tr>
											<td width="10%"><%=Supplier.ALIAS_SUPPLIER_ID%></td>
											<td><input type="text" class="am-form-field am-input-md" placeholder="请输入供应商ID " id="searchInfo" value="${sup.supplierId }"></td>
											<div id="searchSupBox" class="sup_result"></div>
										</tr>
									</div>
									<tr>
										<td><%=Supplier.ALIAS_SUPPLIER_NAME%></td>
										<td>${sup.supplierName }</td>
									</tr>
									<tr>
										<td><%=Supplier.ALIAS_CONTACTS%></td>
										<td>${sup.contacts }</td>
									</tr>
									<tr>
										<td><%=Supplier.ALIAS_PINGYIN%></td>
										<td>${sup.pingyin }</td>
									</tr>
									<tr>
										<td><%=Supplier.ALIAS_ADDRESS%></td>
										<td>${sup.address }</td>
									</tr>
									<tr>
										<td><%=Supplier.ALIAS_USER_TEL%></td>
										<td>${sup.userTel }</td>
									</tr>
									<tr>
										<td><%=Supplier.ALIAS_USER_MOBILE%></td>
										<td>${sup.userMobile }</td>
									</tr>
									<tr>
										<td><%=Supplier.ALIAS_USER_EMAIL%></td>
										<td>${sup.userEmail }</td>
									</tr>
									<tr>
										<td><%=Supplier.ALIAS_NOTE%></td>
										<td>${sup.note }</td>
									</tr>
								</table>
		           			 </div>
					    </div>
					    <!-- 添加入库单 -->
				      	<div class="am-tab-panel" id="tab2" style="height:550px;">
					      	<div id="addOrder">
								<form id="stockInform" class="am-form am-form-horizontal" action="${ctx}/pages/DrugStockIn/save.do" method="post">
							        <div class="am-form-group">
							        	<input type="hidden" class="am-modal-prompt-input" name="stockInId" id="stockInId">
							      		<input type="hidden" name="supplierId" id="supplierId" value="${sup.supplierId }"/>
							      		
										<label for="kind" class="am-u-sm-1 am-u-md-1 am-form-label">总种类：</label>
							          	<div class="am-u-sm-2">
							          		<input type="text" id="kind"  value="0" readonly>
							          	</div>
										<label for="total" class="am-u-sm-1 am-u-md-1 am-form-label">总数量：</label>
							          	<div class="am-u-sm-2">
							          		<input type="text" name="total" id="totalNum" value="0" readonly >
							          	</div>
									  	<label for="sum" class="am-u-sm-1 am-u-md-1 am-form-label">总价钱：</label>
							          	<div class="am-u-sm-2">
							          		<input type="text" name="sum" id="totalCost" value="0" readonly>
							          	</div>
							          	<div class="am-u-sm-2">
							          		<button type="button" class="am-btn am-btn-secondary" onclick="addTr()">添加</button>
							          	</div>
							          	
							        </div>  
						          	<div class="am-form-group">
						            	<!-- 入库表 -->
										<div style="overflow:auto;width:100%;height:400px;">
											<table cellspacing="0" id="drug_table" class="am-table am-table-bordered am-table-radius am-table-striped">
												<thead>
													<tr>
														<th>序号</th>
														<th><%=DrugBasicInfo.ALIAS_DRUG_NAME%></th>
														<th><%=DrugBasicInfo.ALIAS_COST_PRICE%></th>
														<th><%=DrugStockInDetail.ALIAS_IN_STOCKS %></th>
														<th><%=DrugStockInDetail.ALIAS_IN_PRICE %></th>
														<%-- <th><%=DrugStockInDetail.ALIAS_PRODUCTION_DATE %></th> --%>
														<th><%=DrugStockInDetail.ALIAS_IN_PLACE_ID %></th>
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
						              <button type="submit" class="am-btn am-btn-primary" id="submit" data-am-loading="{spinner: 'circle-o-notch', loadingText: '正在提交...', resetText: '已经提交'}" onclick="return check()" >提交入库单</button>
						              <button type="button" class="am-btn am-btn-primary" id="toSerial">转为流水单</button>
						            </div>
						          </div>
					        	</form>
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
<!-- 对话框 -->
<div class="am-modal am-modal-prompt" tabindex="-1" id="inputStockId">
 <div class="am-modal-dialog">
   <div class="am-modal-hd">消息</div>
   <div class="am-modal-bd">
             请输入入库单据编号
     <input type="text" class="am-modal-prompt-input">
   </div>
   <div class="am-modal-footer">
     <span class="am-modal-btn" data-am-modal-cancel>取消</span>
     <span class="am-modal-btn" data-am-modal-confirm>提交</span>
   </div>
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
<script type="text/javascript">
	//查找供应商信息
	function search(value) {
		if(value!=null){
			$("#searchInfo").val(value);
		}
		var searchInfo=encodeURI(encodeURI($("#searchInfo").val().trim()));
		var searchType=$("ul[id='searchType']").find("li[class='am-active']").attr("value");
		$("#searchSup").attr("action","${ctx}/pages/Supplier/searchSup.do?searchType="+searchType+"&searchInfo="+searchInfo).submit();
	}
  	//添加行
  	function addTr(){
  		$.get("${ctx}/pages/DrugBasicInfo/getDrugJSON.do?supplierId="+$("#supplierId").val(),function(data){
  			 var drugs=JSON.parse(data);
       	 	 var tab =$("table[id='drug_table']>tbody");
	   		 var rownum = tab.children("tr").length;
	   		 var panel="<tr align='center'><td><input type='hidden' name='drugStockInDetails.makeNew["+rownum+"].id' value='"+rownum+"'/>"+(rownum+1)+"</td><td style='width:50px;'><select class='chosen-select' data-placeholder='请选择药品' style='width:200px;' name='drugStockInDetails.makeNew["+rownum+"].drugId' onchange='selectDrug(this)' required='required'><option disabled='disabled' selected='selected'>---请选择药品---</option>";
	   		 $.each(drugs,function(i,item){
	   			 panel+="<option value='"+item.drugId+"'>"+item.drugName+"</option>";
	   		 });
	   		 panel+="</select></td><td></td><td><input type='number' name='drugStockInDetails.makeNew["+rownum+"].inStocks' value='0' onkeyup='countPrice(this)'/></td><td><input type='text' name='drugStockInDetails.makeNew["+rownum+"].inPrice' value='0' readonly /></td><td style='width:50px;'><select class='chosen-select' data-placeholder='请选择位置' style='width:200px;' name='drugStockInDetails.makeNew["+rownum+"].inPlaceId' required='required'><option disabled='disabled' selected='selected'>---请选择位置---</option>";
	   		 $.each(places, function(i,item) {
				if(item.status!=0){
					panel+=("<option value='"+item.drugPlaceId+"'>"+ item.drugPlace + "</option>");
				}
			});
	   		panel+="</select></td><td><div class='am-dropdown' data-am-dropdown>"+
	   				"<button class='am-btn am-btn-default am-btn-xs am-dropdown-toggle' data-am-dropdown-toggle>"+
	   				"<span class='am-icon-cog'></span> <span class='am-icon-caret-down'></span></button><ul class='am-dropdown-content'>"+
	   				"<li><a href='' >1.查看药品详情</a></li><li><a href='javascript:void(0)' onclick='romoveTr(this)'>2.删除</a></li></ul></div></td></tr>";
	   		tab.append(panel);
	   		$(".chosen-select").chosen();
	   		$('[data-am-dropdown]').dropdown();
	   		$('[data-am-selected]').selected();
	   		$('[data-am-datepicker]').datepicker();
  		});
  		
  	}
  	//删除行
  	function romoveTr(obj){
  		$(obj).parent().parent().parent().parent().parent().remove();
  	}
  	//添加药品
  	function selectDrug(obj){
  		$.get("${ctx}/pages/DrugBasicInfo/getDrugJSON.do?drugId="+obj.value,function(data){
  			var drug=JSON.parse(data);
  			$.each(drug,function(i,item){
  				$(obj).parent().next().text(item.costPrice);
  				//动态更新药品金额
  				countPrice($(obj).parent().next().next().children(":input"));
  				//查看药品详情
  				var tab =$("table[id='drug_table']>tbody");
  				tab.find("a").eq(0).attr("href","${ctx}/pages/DrugBasicInfo/search.do?searchType=drugId&searchInfo="+item.drugId);
  			});
  			
  		});
  	}
	
  	//采用冒泡事件设置div隐藏 
  	function stopPropagation(e) {
        if (e.stopPropagation) 
            e.stopPropagation();
        else 
            e.cancelBubble = true;
    }
  	//即时计算药品价钱
	function countPrice(obj){
		var sum =$(obj).parent().next().find("input");
		var price=$(obj).parent().prev();
		sum.val(accMul(price.text(),$(obj).val()));
	}
	//计算总总列数
	function getTotalKind(){
		var rows=$("#drug_table>tbody").children("tr");
		$("#kind").val(rows.length);
	}
	//计算总数量
  	function getTotalNum(){
  		var rows=$("#drug_table>tbody").children("tr");
  		var totalNum=0;
  		$.each(rows,function(i,item){
  			var num=$(item).children("td:eq(3)").children("input").val();
  			totalNum+=parseInt(num);
  		});
  		$("#totalNum").val(totalNum);
  	}
  	//计算总价格
  	function getTotalCost(){
  		var rows=$("#drug_table>tbody").children("tr");
  		var totalCost=0;
  		$.each(rows,function(i,item){
  			var cost=$(item).children("td:eq(4)").children("input").val();
  			totalCost=accAdd(totalCost,cost);
  		});
  		$("#totalCost").val(totalCost);
  		
  	}
  	//判断是否已经搜索
	function isSearch(){
		if($("#supplierId").val()==""){
			$("#addOrder").html("").html("<div class='am-alert am-alert-success' data-am-alert><button type='button' class='am-close'>&times;</button><p>请先搜索供应商!</p></div>");
  			/* $("#message").text("请先查询供应商!");
			$("#alert").modal('open'); */
			return false;
  		}
		return true;
	}
  	//检查添加药品是否为空
  	function check(){
  		if(!isSearch()){
			return false;
  		}
  		if($("table[id='drug_table']>tbody").children("tr").length<=0){
			$("#message").text("请添加入库药品!");
			$("#alert").modal('open');
			return false;
		}
  		return true;
  	}
    $(document).on('click',function(){
    	$('#searchSupBox').css('display','none');
    });
    $('#searchSupBox').on('click',function(e){
        stopPropagation(e);
    });
  	//监听药品总列变化
	setInterval(getTotalKind, 1000);
  	//监听药品总数量变化
	setInterval(getTotalNum, 1000);
	//监听药品总价格变化
	setInterval(getTotalCost, 1000);
	
	//打开流水单对话框
	$('#toSerial').on('click', function() {
		if(!check()){
			return;
		}
	    $('#inputStockId').modal({
	      relatedTarget: this,
	      onConfirm: function(e) {
	    	$('#stockInId').val($('#inputStockId').find(':input').val());
	        $('#stockInform').submit();
	      },
	      onCancel: function(e) {
	        
	      }
	    });
  	});
	$('#submit').click(function () {
		  var $btn = $(this)
		  $btn.button('loading');
		    setTimeout(function(){
		      $btn.button('reset');
		  }, 3000);
	});	
	</script>	
</body>
</html>

