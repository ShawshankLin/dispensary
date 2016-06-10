<%@page import="com.dispensary.project.model.Supplier"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html;charset=UTF-8" pageEncoding="utf-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!doctype html>
<html class="no-js">
<head>
	<meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title><%=Supplier.TABLE_ALIAS %>添加</title>
  <meta name="description" content="添加供应商页面">
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
  <script type="text/javascript" src="../../scripts/jquery.js"></script>
  <script type="text/javascript" src="../../scripts/json2.js"></script>
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
		    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg"><%=Supplier.TABLE_ALIAS%></strong> / <small>添加</small></div>
		  </div>
		
		  <div class="am-tabs am-margin" data-am-tabs="{noSwipe: 1}">
		    <ul class="am-tabs-nav am-nav am-nav-tabs">
		      <li><a href="#tab1">基本信息</a></li>
		    </ul>
		
		    <div class="am-tabs-bd">
		      <div class="am-tab-panel am-fade" id="tab1">
		        <form class="am-form" action="${ctx}/pages/Supplier/save.do" method="post">		
		          <div class="am-g am-margin-top">
		            <div class="am-u-sm-4 am-u-md-2 am-text-right am-padding-right">
		            	<%=Supplier.ALIAS_SUPPLIER_NAME%>
		            </div>
		            <div class="am-u-sm-8 am-u-md-4 am-u-end">
		              <input type="text" class="am-input-sm" name="supplierName" id="supplierName" required="required">
		            </div>
		          </div>
				  <div class="am-g am-margin-top">
		            <div class="am-u-sm-4 am-u-md-2 am-text-right am-padding-right">
		            	<%=Supplier.ALIAS_CONTACTS%>
		            </div>
		            <div class="am-u-sm-8 am-u-md-4 am-u-end">
		              <input type="text" class="am-input-sm" name="contacts" required="required">
		            </div>
		            <div class="am-hide-sm-only am-u-md-6 am-padding-left"></div>
		          </div>
				  
		          <div class="am-g am-margin-top">
		            <div class="am-u-sm-4 am-u-md-2 am-text-right am-padding-right">
		              	<%=Supplier.ALIAS_PINGYIN%>
		            </div>
		            <div class="am-u-sm-8 am-u-md-4">
		              <input type="text" class="am-input-sm" name="pingyin" id="pingyin" required="required">
		            </div>
		            <div class="am-hide-sm-only am-u-md-6 am-padding-left">多音字请自行更正</div>
		          </div>
		
		          <div class="am-g am-margin-top">
		            <div class="am-u-sm-4 am-u-md-2 am-text-right am-padding-right">
		              	<%=Supplier.ALIAS_ADDRESS%>
		            </div>
		            <div class="am-u-sm-8 am-u-md-4">
		              <input type="text" class="am-input-sm" name="address">
		            </div>
		            <div class="am-u-sm-12 am-u-md-6 am-padding-left"></div>
		          </div>
		
		          <div class="am-g am-margin-top-sm">
		            <div class="am-u-sm-12 am-u-md-2 am-text-right am-padding-right">
		              	<%=Supplier.ALIAS_USER_TEL%>
		            </div>
		            <div class="am-u-sm-12 am-u-md-4">
		              <input type="text" name="userTel" class="am-input-sm">
		            </div>
		            <div class="am-u-sm-12 am-u-md-6 am-padding-left"></div>
		          </div>
				  
				  <div class="am-g am-margin-top-sm">
		            <div class="am-u-sm-12 am-u-md-2 am-text-right am-padding-right">
		              	<%=Supplier.ALIAS_USER_MOBILE%>
		            </div>
		            <div class="am-u-sm-12 am-u-md-4">
		              <input type="text" name="userMobile" class="am-input-sm">
		            </div>
		            <div class="am-u-sm-12 am-u-md-6 am-padding-left"></div>
		          </div>
		          <div class="am-g am-margin-top-sm">
		            <div class="am-u-sm-12 am-u-md-2 am-text-right am-padding-right">
		              	<%=Supplier.ALIAS_USER_EMAIL%>
		            </div>
		            <div class="am-u-sm-12 am-u-md-4">
		              <input type="email" name="userEmail" class="am-input-sm">
		            </div>
		            <div class="am-u-sm-12 am-u-md-6 am-padding-left"></div>
		          </div>
		          <div class="am-g am-margin-top-sm">
		            <div class="am-u-sm-12 am-u-md-2 am-text-right am-padding-right">
		              	<%=Supplier.ALIAS_NOTE%>
		            </div>
		            <div class="am-u-sm-12 am-u-md-4">
		              <input type="text" name="note" class="am-input-sm">
		            </div>
		            <div class="am-u-sm-12 am-u-md-6 am-padding-left"></div>
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
<script>
  $(function() {
	//配置药品拼音
    $("#supplierName").blur(function(){
		$("#pingyin").val(makePy($("#supplierName").val()).join());
	});

  })
</script>
</body>
</html>
