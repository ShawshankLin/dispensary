<%@page import="com.dispensary.project.model.Receipt"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html;charset=UTF-8" pageEncoding="utf-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title><%=Receipt.TABLE_ALIAS %>编辑</title>
  <meta name="description" content="编辑页面">
  <meta name="keywords" content="form">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="icon" type="image/png" href="assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <link rel="stylesheet" href="${ctx}/assets/css/amazeui.css"/>
  <link rel="stylesheet" href="${ctx}/assets/css/admin.css">
  <script type="text/javascript" src="../../scripts/common.js"></script>
</head>
  
<body>
<div class="am-g am-g-collapse">
  <div class="am-u-lg-12"><jsp:include page="../head.jsp"/></div>
  <div class="am-u-lg-2"><jsp:include page="../menu.jsp"/></div>
  <div class="am-u-lg-10 ">
  	<div class="am-cf admin-main">
		<!-- content start -->
		<div class="admin-content">
		
		  <div class="am-cf am-padding">
		    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg"><%=Receipt.TABLE_ALIAS%></strong> / <small>编辑</small></div>
		  </div>
		
		  <div class="am-tabs am-margin" data-am-tabs="{noSwipe: 1}">
		    <ul class="am-tabs-nav am-nav am-nav-tabs">
		      <li><a href="#tab1">基本信息</a></li>
		    </ul>
		
		    <div class="am-tabs-bd">
		      <div class="am-tab-panel am-fade am-in" id="tab1">
		        <form class="am-form" action="${ctx}/pages/Receipt/save.do" method="post">
		          <input type="hidden" class="am-input-sm" name="presId" value="${receipt.presId}">
				  <div class="am-g am-margin-top">
		            <div class="am-u-sm-4 am-u-md-2 am-text-right am-padding-right">
		            	<%=Receipt.ALIAS_REGISTER_FEE%>
		            </div>
		            <div class="am-u-sm-8 am-u-md-4 am-u-end">
		             	<input type="number" class="am-input-sm" name="registerFee" id="registerFee" value="${receipt.registerFee}" readonly="readonly">
		            </div>
		            <div class="am-hide-sm-only am-u-md-6 am-padding-left"></div>
		          </div>
		          
				  <div class="am-g am-margin-top">
		            <div class="am-u-sm-4 am-u-md-2 am-text-right am-padding-right">
		            	<%=Receipt.ALIAS_DRUG_FEE%>
		            </div>
		            <div class="am-u-sm-8 am-u-md-4 am-u-end">
		             	<input type="number" class="am-input-sm" name="drugFee" id="drugFee" value="${receipt.drugFee}" readonly="readonly">
		            </div>
		            <div class="am-hide-sm-only am-u-md-6 am-padding-left"></div>
		          </div>
		          
		          <div class="am-g am-margin-top">
		            <div class="am-u-sm-4 am-u-md-2 am-text-right am-padding-right">
		            	<%=Receipt.ALIAS_TRANSFER_FEE%>
		            </div>
		            <div class="am-u-sm-8 am-u-md-4 am-u-end">
		             	<input type="number" class="am-input-sm" name="transferFee" id="transferFee" value="${receipt.transferFee}" readonly="readonly">
		            </div>
		            <div class="am-hide-sm-only am-u-md-6 am-padding-left"></div>
		          </div>
		          
		          <div class="am-g am-margin-top">
		            <div class="am-u-sm-4 am-u-md-2 am-text-right am-padding-right">
		            	<%=Receipt.ALIAS_OXYGEN_FEE%>
		            </div>
		            <div class="am-u-sm-8 am-u-md-4 am-u-end">
		             	<input type="number" class="am-input-sm" name="oxygenFee" id="oxygenFee" value="${receipt.oxygenFee}" readonly="readonly">
		            </div>
		            <div class="am-hide-sm-only am-u-md-6 am-padding-left"></div>
		          </div>
		          
		          <div class="am-g am-margin-top">
		            <div class="am-u-sm-4 am-u-md-2 am-text-right am-padding-right">
		            	<%=Receipt.ALIAS_PHYSICAL_FEE%>
		            </div>
		            <div class="am-u-sm-8 am-u-md-4 am-u-end">
		             	<input type="number" class="am-input-sm" name="physicalFee" id="physicalFee" value="${receipt.physicalFee}" readonly="readonly">
		            </div>
		            <div class="am-hide-sm-only am-u-md-6 am-padding-left"></div>
		          </div>
		          
		          <div class="am-g am-margin-top">
		            <div class="am-u-sm-4 am-u-md-2 am-text-right am-padding-right">
		            	<%=Receipt.ALIAS_EMERGENCY_FEE%>
		            </div>
		            <div class="am-u-sm-8 am-u-md-4 am-u-end">
		             	<input type="number" class="am-input-sm" name="emergencyFee" id="emergencyFee" value="${receipt.emergencyFee}" readonly="readonly">
		            </div>
		            <div class="am-hide-sm-only am-u-md-6 am-padding-left"></div>
		          </div>
		          
		          <div class="am-g am-margin-top">
		            <div class="am-u-sm-4 am-u-md-2 am-text-right am-padding-right">
		            	<%=Receipt.ALIAS_INJECTION_FEE%>
		            </div>
		            <div class="am-u-sm-8 am-u-md-4 am-u-end">
		             	<input type="number" class="am-input-sm" name="injectionFee" id="injectionFee" value="${receipt.injectionFee}" readonly="readonly">
		            </div>
		            <div class="am-hide-sm-only am-u-md-6 am-padding-left"></div>
		          </div>
		          
		          <div class="am-g am-margin-top">
		            <div class="am-u-sm-4 am-u-md-2 am-text-right am-padding-right">
		            	<%=Receipt.ALIAS_NEBULIZATION_FEE%>
		            </div>
		            <div class="am-u-sm-8 am-u-md-4 am-u-end">
		             	<input type="number" class="am-input-sm" name="nebulizationFee" id="nebulizationFee" value="${receipt.nebulizationFee}" readonly="readonly">
		            </div>
		            <div class="am-hide-sm-only am-u-md-6 am-padding-left"></div>
		          </div>
		          
		          
		          
		          <div class="am-g am-margin-top">
		            <div class="am-u-sm-4 am-u-md-2 am-text-right am-padding-right">
		            	<%=Receipt.ALIAS_FEE_SUM%>
		            </div>
		            <div class="am-u-sm-8 am-u-md-4 am-u-end">
		             	<input type="number" class="am-input-sm" name="feeSum" id="feeSum" value="${receipt.feeSum}" readonly="readonly">
		            </div>
		            <div class="am-hide-sm-only am-u-md-6 am-padding-left"></div>
		          </div>
		          		
			     <div class="am-margin">
			    	<button type="submit" class="am-btn am-btn-primary am-btn-xs" >提交保存</button>
			    	<button type="button" class="am-btn am-btn-primary am-btn-xs" onclick="history.back()">放弃保存</button>
			  	</div>
		        </form>
		      </div>
		
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
<script type="text/javascript">
	$("#sum").focus(function(){
		$(this).val(accAdd($("#drugSum").val(),$("#transferFee").val()));
	});
	$("#registerSum").keyup(function(){
		$("#sum").val(accAdd($("#drugSum").val(),$("#transferFee").val()));
	});
</script>
</body>
</html>

