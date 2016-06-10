<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.dispensary.project.model.*"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<!doctype html>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title><%=DrugBasicInfo.TABLE_ALIAS%>管理</title>
<meta name="description" content="这是一个form页面">
<meta name="keywords" content="form">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="icon" type="image/png" href="${ctx}/assets/i/favicon.png">
<link rel="apple-touch-icon-precomposed"
	href="${ctx}/sassets/i/app-icon72x72@2x.png">
<meta name="apple-mobile-web-app-title" content="Amaze UI" />
<link rel="stylesheet" href="${ctx}/assets/css/amazeui.css" />
<link rel="stylesheet" href="${ctx}/assets/css/admin.css">
<script type="text/javascript" src="<c:url value="/widgets/simpletable/simpletable.js"/>"></script>
<script type="text/javascript" src="../../scripts/common.js"></script>	
<script type="text/javascript" src="../../scripts/jquery.js"></script>
<script type="text/javascript" src="../../scripts/json2.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		// 分页需要依赖的初始化动作
		window.simpleTable = new SimpleTable('queryForm',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
	});
	//删除药品信息
	function delBatch(){
		if($(":checked[name]").length>0){
			$("#queryForm").attr("action","${ctx}/pages/DrugBasicInfo/delBatch.do").submit();
		}else{
			alert("请选择删除的行！");
		}
		return false;	
	}
	//搜索药品
	function search(){
		var searchInfo=$("#searchInfo").val().trim();;
		var searchType= $("ul[id='searchType']").find("li[class='am-active']").attr("value");
		window.location.href=encodeURI(encodeURI("${ctx}/pages/DrugBasicInfo/search.do?searchType="+searchType+"&searchInfo="+searchInfo));
		return false;
	}
</script>
</head>

<body>
	<div class="am-g am-g-collapse">
		<div class="am-u-lg-12"><jsp:include page="../head.jsp" /></div>
		<div class="am-u-lg-2"><jsp:include page="../menu.jsp" /></div>
		<div class="am-u-lg-10">
			<div class="am-cf admin-main">
			<!-- content start -->
			<div class="admin-content">
				<div class="am-cf am-padding">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg"><%=DrugBasicInfo.TABLE_ALIAS%></strong>
						/ <small>管理</small>
					</div>
				</div>
	
				<div class="am-g">
					<div class="am-u-sm-12 am-u-md-6 am-u-lg-6">
						<div class="am-btn-toolbar">
							<div class="am-btn-group am-btn-group-xs">
								<a  class="am-btn am-btn-default am-btn-success" href="${ctx}/pages/DrugBasicInfo/create.do">
									<span class="am-icon-plus"></span> 新增
								</a>
								<a  class="am-btn am-btn-default am-btn-success" href="${ctx}/pages/DrugBasicInfo/getDrugsReport.do">
									<span class="am-icon-arrow-circle-o-down"></span> 导出
								</a>
								<button type="button" class="am-btn am-btn-default am-btn-danger" onclick="delBatch()">
									<span class="am-icon-trash-o"></span> 批量删除
								</button>
							</div>
						</div>
					</div>
					<div class="am-u-sm-12 am-u-md-6 am-u-lg-6">
						<div class="am-topbar-form am-form-inline am-topbar-right">
				          	<input type="text" class="am-form-field" placeholder="请输入药品ID" id="searchInfo" onkeydown="enterSearch()">
				          	<div class="am-btn-group">
							  	<button type="button" class="am-btn  am-btn-secondary" onclick="search()">搜索</button>
							  	<div class="am-dropdown" data-am-dropdown>
								    <button class="am-btn am-btn-secondary am-dropdown-toggle" data-am-dropdown-toggle> <span class="am-icon-caret-down"></span></button>
								    <ul class="am-dropdown-content" id="searchType">
								      <li class="am-active" value="drugId"><a href="javascript:void(0)"  onclick="setSearchType(this)">药品ID</a></li>
								      <li value="drugName"><a href="javascript:void(0)" onclick="setSearchType(this)">药品名称</a></li>
								      <li value="drugPingyin"><a href="javascript:void(0)" onclick="setSearchType(this)">药品拼音</a></li>
								      <li value="supplierId"><a href="javascript:void(0)" onclick="setSearchType(this)">供应商名称</a></li>
								    </ul>
							  	</div>
						  	</div>		      
					  	</div>
					</div>
				</div>
	
				<div class="am-g">
					<div class="am-u-sm-12">
						<form id="queryForm" name="queryForm" class="am-form" action="<c:url value="/pages/DrugBasicInfo/list.do"/>" method="post">
							<table class="am-table am-table-striped am-table-hover table-main">
								<thead>
									<tr>											
										<th><input type="checkbox" name="item" id="allitem"
											onclick="checkAllBox(this)" /></th>
										<th>ID</th>
										<!-- 排序时为th增加sortColumn即可,new SimpleTable('sortColumns')会为tableHeader自动增加排序功能; -->
										<th sortColumn=DrugID><%=DrugBasicInfo.ALIAS_DRUG_ID%></th>
										<th sortColumn="DrugName"><%=DrugBasicInfo.ALIAS_DRUG_NAME%></th>
										<th sortColumn="DrugPingyin"><%=DrugBasicInfo.ALIAS_DRUG_PINGYIN%></th>
										<th sortColumn="DrugEffect"><%=DrugBasicInfo.ALIAS_DRUG_EFFECT%></th>
										<th sortColumn="DrugKickBack"><%=DrugBasicInfo.ALIAS_DRUG_KICK_BACK%></th>
										<th sortColumn="QuantityUnit"><%=DrugBasicInfo.ALIAS_QUANTITY_UNIT%></th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${page.result}" var="item" varStatus="status">
										<tr>
											<td><input type="checkbox" name="items" value="drugId=${item.drugId}"></td>
											<td>${page.thisPageFirstElementNumber + status.index}</td>
											<td><c:out value='${item.drugId}' /></td>
											<td><a href="javascript:void(0)" data-am-modal="{target: '#show${item.drugId}', closeViaDimmer: 0, width: 600, height: 425}" onclick="getDrugSymptom(${item.drugId})"><c:out value='${item.drugName}' /></a></td>
											<td><c:out value='${item.drugPingyin}' />&nbsp;</td>
											<td><c:out value='${item.drugEffectModel.modelTagValue}' /></td>
											<td><c:out value='${item.drugKickBackModel.modelTagValue}' /></td>
											<td><c:out value='${item.quantityUnitModel.modelTagValue}' /></td>										
											<td>
											<div class="am-btn-toolbar">
												<div class="am-btn-group am-btn-group-xs">
													<button type="button" class="am-btn am-btn-default am-text-success am-hide-sm-only" data-am-modal="{target: '#show${item.drugId}', closeViaDimmer: 0, width: 600, height: 425}" onclick="getDrugSymptom(${item.drugId})">
														<span class="am-icon-search-plus"></span> 查看
													</button>
													<!-- 模拟窗口 -->
													<div class="am-modal am-modal-no-btn" tabindex="-1" id="show${item.drugId}">
													  <div class="am-modal-dialog">
													    <div class="am-modal-hd">药品基本信息
													      <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
													    </div>
													    <div class="am-modal-bd">
													    	<div style="overflow:auto;width:100%;height:300px">
													     	<table class="am-table am-table-striped am-table-hover">
																<tr>
																	<td><%=DrugBasicInfo.ALIAS_DRUG_ID%></td>
																	<td>${item.drugId}</td>
																</tr>
																<tr>
																	<td><%=DrugBasicInfo.ALIAS_DRUG_NAME%></td>
																	<td>${item.drugName}</td>
																</tr>
																<tr>
																	<td><%=DrugBasicInfo.ALIAS_DRUG_PINGYIN%></td>
																	<td>${item.drugPingyin}</td>
																</tr>
																<tr>
																	<td><%=DrugBasicInfo.ALIAS_DRUG_EFFECT%></td>
																	<td>${item.drugEffectModel.modelTagValue}</td>
																</tr>
																<tr>
																	<td><%=DrugBasicInfo.ALIAS_DRUG_KICK_BACK%></td>
																	<td>${item.drugKickBackModel.modelTagValue}</td>
																</tr>
																<!--  
																<tr>
																	<td><%=DrugBasicInfo.ALIAS_DRUG_NOTE%></td>
																	<td>${item.drugNote}</td>
																</tr>
																-->
																<tr>
																	<td><%=DrugBasicInfo.ALIAS_QUANTITY_UNIT%></td>
																	<td>${item.quantityUnitModel.modelTagValue}</td>
																</tr>
																<tr>
																	<td><%=DrugBasicInfo.ALIAS_DRUG_USAGE%></td>
																	<td>${item.drugUsage}</td>
																</tr>
																<tr>
																	<td><%=DrugBasicInfo.ALIAS_DRUG_SPEC%></td>
																	<td>${item.drugSpec}</td>
																</tr>
																<tr>
																	<td><%=DrugBasicInfo.ALIAS_DRUG_TABU%></td>
																	<td>${item.drugTabu}</td>
																</tr>
																<tr>
																	<td><%=DrugBasicInfo.ALIAS_COST_PRICE%></td>
																	<td>${item.costPrice}元</td>
																</tr>
																<tr>
																	<td><%=DrugBasicInfo.ALIAS_RETAIL_PRICE%></td>
																	<td>${item.retailPrice}元</td>
																</tr>
																<tr>
																	<td><%=DrugBasicInfo.ALIAS_PRODUCTION_DATE%></td>
																	<td>${item.productionDateString}</td>
																</tr>
																<tr>
																	<td><%=DrugBasicInfo.ALIAS_PERIOD_OF_VALIDITY%></td>
																	<td>${item.periodOfValidity}年</td>
																</tr>
																<!-- 
																<tr>
																	<td><%=DrugBasicInfo.ALIAS_UP_LIMIT%></td>
																	<td>${item.upLimit}</td>
																</tr>
																<tr>
																	<td><%=DrugBasicInfo.ALIAS_DOWN_LIMIT%></td>
																	<td>${item.downLimit}</td>
																</tr>
																<tr>
																	<td><%=DrugBasicInfo.ALIAS_UP_LIMIT1%></td>
																	<td>${item.upLimit1}</td>
																</tr>
																<tr>
																	<td><%=DrugBasicInfo.ALIAS_UP_LIMIT1%></td>
																	<td>${item.downLimit1}</td>
																</tr>
																 -->
																<tr>
																	<td><%=DrugBasicInfo.ALIAS_IS_DRUG%></td>
																	<td>${item.isDrug==0?"是":"否"}</td>
																</tr>
																<tr>
																	<td><%=DrugBasicInfo.ALIAS_SUPPLIER_ID%></td>
																	<td>${item.supplierIdModel.modelTagValue}</td>
																</tr>
																<tr>
																	<td><%=DrugBasicInfo.ALIAS_SYMPTOM_ID%></td>
																	<td class="symptom">
																		
																	</td>
																</tr>
															</table>
															</div>
													    </div>
													  </div>
													</div>
													<a class="am-btn am-btn-default am-text-secondary" href="${ctx}/pages/DrugBasicInfo/edit.do?drugId=${item.drugId}">
													  <span class="am-icon-pencil-square-o"></span> 编辑
													</a>
													<a class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only" onclick="return confirm('${ctx}/pages/DrugBasicInfo/delete.do?drugId=${item.drugId}')" href="javascript:void(0)">
														<span class="am-icon-trash-o"></span> 删除
													</a>
												</div>
											</div>
										</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<div class="am-cf">
								<div class="am-fr am-margin-bottom am-margin-right">
									<simpletable:pageToolbar page="${page}">
									</simpletable:pageToolbar>
									<!-- <ul class="am-pagination">
										<li class="am-disabled"><a href="#">«</a></li>
										<li class="am-active"><a href="#">1</a></li>
										<li><a href="#">2</a></li>
										<li><a href="#">3</a></li>
										<li><a href="#">4</a></li>
										<li><a href="#">5</a></li>
										<li><a href="#">»</a></li>
									</ul> -->
								</div>
							</div>
						</form>
					</div>
	
				</div>
			</div>
			<!-- content end -->
		</div>
	</div>
</div>
	
	
<!-- message box -->
<div class="am-modal am-modal-confirm" tabindex="-1" id="delConfirm">
  <div class="am-modal-dialog">
    <div class="am-modal-hd">消息</div>
    <div class="am-modal-bd">
      	你，确定要删除这条记录吗？
    </div>
    <div class="am-modal-footer">
      <span class="am-modal-btn" data-am-modal-cancel>取消</span>
      <span class="am-modal-btn" data-am-modal-confirm>确定</span>
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
<!--<![endif]-->
<script src="${ctx}/assets/js/app.js"></script>
<script type="text/javascript">
	function getDrugSymptom(v){
		var drugId=v;
		$.get("${ctx}/pages/DrugSymptomRelation/getDrugSymptomJSON.do?drugId="+ drugId+ "&nocache="+ new Date().getTime(),function(data){
			var symptoms=JSON.parse(data);
			var arr=new Array();
			$.each(symptoms,function(i,item){
				arr.push(item.symptomIdModelTag);
			});
			$("div#show"+drugId+" .symptom").text(arr.join(","));
		});
		
	}

</script>
</body>
</html>



