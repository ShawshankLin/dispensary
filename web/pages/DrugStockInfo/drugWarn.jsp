<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.dispensary.project.model.*"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<!doctype html>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title><%=DrugStockInfo.TABLE_ALIAS%>有效期管理</title>
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

	//搜索药品
	function search(){
		var searchInfo=$("#searchInfo").val();
		var searchType= $("ul[id='searchType']").find("li[class='am-active']").attr("value");
		window.location.href=encodeURI(encodeURI("${ctx}/pages/DrugStockInfo/search.do?searchType="+searchType+"&searchInfo="+searchInfo));
		return false;
	}
	//设置搜索类型
	function setSearchType(obj){
		$.each($("ul[id='searchType'] li"),function(i,item){
			$(item).removeClass("am-active");
		});
		$(obj).parent().addClass("am-active");
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
					<strong class="am-text-primary am-text-lg"><%=DrugStockInfo.TABLE_ALIAS%></strong>
					/ <small>有效期管理</small>
				</div>
			</div>

			<div class="am-g">
				<div class="am-u-sm-12 am-u-md-6 am-u-lg-6">
					<div class="am-btn-toolbar">
						<div class="am-btn-group am-btn-group-xs">
							<a  class="am-btn am-btn-default am-btn-success" href="${ctx}/pages/DrugStockInfo/create.do">
								<span class="am-icon-plus"></span> 1个月
							</a>
							<a  class="am-btn am-btn-default am-btn-success" href="${ctx}/pages/DrugStockInfo/getDrugsReport.do">
								<span class="am-icon-arrow-circle-o-up"></span> 3个月
							</a>
							<a  class="am-btn am-btn-default am-btn-success" href="${ctx}/pages/DrugStockInfo/getDrugsReport.do">
								<span class="am-icon-arrow-circle-o-down"></span> 半年
							</a>
						</div>
					</div>
				</div>
				<div class="am-u-sm-12 am-u-md-6 am-u-lg-6">
					<div class="am-topbar-form am-form-inline am-topbar-right">
			          	<input type="text" class="am-form-field" placeholder="请输入药品信息" id="searchInfo">
			          	<div class="am-btn-group">
						  	<button type="button" class="am-btn  am-btn-secondary" onclick="search()">搜索</button>
						  	<div class="am-dropdown" data-am-dropdown>
							    <button class="am-btn am-btn-secondary am-dropdown-toggle" data-am-dropdown-toggle> <span class="am-icon-caret-down"></span></button>
							    <ul class="am-dropdown-content" id="searchType">
							      <li class="am-active" value="drugId"><a href="javascript:void(0)"  onclick="setSearchType(this)">药品ID</a></li>
							      <li value="drugName"><a href="javascript:void(0)" onclick="setSearchType(this)">药品名称</a></li>
							      <li value="drugPingyin"><a href="javascript:void(0)" onclick="setSearchType(this)">药品拼音</a></li>
							    </ul>
						  	</div>
					  	</div>		      
				  	</div>
				</div>
			</div>

			<div class="am-g">
				<div class="am-u-sm-12">
					<form id="queryForm" name="queryForm" class="am-form" action="<c:url value="/pages/DrugStockInfo/showDrugWarn.do"/>" method="post">
						<table class="am-table am-table-striped am-table-hover table-main">
							<thead>
								<tr>											
									<th><input type="checkbox" name="item" id="allitem"
										onclick="checkAllBox(this)" /></th>
									<th>ID</th>
									<!-- 排序时为th增加sortColumn即可,new SimpleTable('sortColumns')会为tableHeader自动增加排序功能; -->
									<th sortColumn=DrugID><%=DrugBasicInfo.ALIAS_DRUG_ID%></th>
									<th sortColumn="DrugName"><%=DrugBasicInfo.ALIAS_DRUG_NAME%></th>
									<th sortColumn="ProductionDate"><%=DrugBasicInfo.ALIAS_PRODUCTION_DATE%></th>
									<th sortColumn="PeriodOfValidity"><%=DrugBasicInfo.ALIAS_PERIOD_OF_VALIDITY%></th>
									<th>当前时间</th>
									<th>到期时间</th>
									<th>剩余天数</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${page.result}" var="item" varStatus="status">
									<tr>
										<td><input type="checkbox" name="items" value="drugId=${item.drugId}"></td>
										<td>${page.thisPageFirstElementNumber + status.index}</td>
										<td><c:out value='${item.drugId}' /></td>
										<td><c:out value='${item.drugName}'/></td>
										<td><c:out value='${item.productionDate}' /></td>
										<td><c:out value='${item.periodOfValidity}年' /></td>
										<td><c:out value='${item.nowDate}' /></td>		
										<td><c:out value='${item.toDate}' /></td>
										<td><c:out value='${item.leftDay}天' /></td>										
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div class="am-cf">
							<div class="am-fr am-margin-bottom am-margin-right">
								<simpletable:pageToolbar page="${page}">
								</simpletable:pageToolbar>
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
</body>
</html>



