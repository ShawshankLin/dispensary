<%@page import="com.dispensary.project.model.Feetype"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html;charset=UTF-8" pageEncoding="utf-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!doctype html>
<html class="no-js">
<head>
	<meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title><%=Feetype.TABLE_ALIAS %>添加</title>
  <meta name="description" content="修改费用类型页面">
  <meta name="keywords" content="form">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="icon" type="image/png" href="assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <link rel="stylesheet" href="${ctx}/assets/css/amazeui.css"/>
  <link rel="stylesheet" href="${ctx}/assets/css/admin.css">
    <link rel="stylesheet" href="${ctx}/assets/css/amazeui.chosen.css"/>
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
  </style>
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
		    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg"><%=Feetype.TABLE_ALIAS%></strong> / <small>编辑</small></div>
		  </div>
		
		  <div class="am-tabs am-margin" data-am-tabs="{noSwipe: 1}">
		    <ul class="am-tabs-nav am-nav am-nav-tabs">
		      <li><a href="#tab1">基本信息</a></li>
		    </ul>
		
		    <div class="am-tabs-bd">
		      <div class="am-tab-panel am-fade" id="tab1" style="height:auto;">
		        <form class="am-form" action="${ctx}/pages/Feetype/update.do" method="post">
				<div class="am-g am-margin-top">
		            <div class="am-u-sm-4 am-u-md-2 am-text-right am-padding-right">
		            	<%=Feetype.ALIAS_FEE_TYPE_ID%>
		            </div>
		            <div class="am-u-sm-8 am-u-md-4 am-u-end">
		              <input type="text" class="am-input-sm" value="${feetype.feeTypeId }" name="feeTypeId" required="required" readonly="readonly">
		            </div>
		          </div>
		          <div class="am-g am-margin-top">
		            <div class="am-u-sm-4 am-u-md-2 am-text-right am-padding-right">
		            	<%=Feetype.ALIAS_FEE_TYPE_NAME%>
		            </div>
		            <div class="am-u-sm-8 am-u-md-4 am-u-end">
		              <input type="text" class="am-input-sm" name="feeTypeName" value="${feetype.feeTypeName }" required="required">
		            </div>
		          </div>
		          <div class="am-g am-margin-top-sm">
		            <div class="am-u-sm-12 am-u-md-2 am-text-right am-padding-right">
		              	修改费用类型药品
		            </div>
		            <div class="am-u-sm-12 am-u-md-4">
		            <select name="drugs" id="drugs" style="width:650px;" data-placeholder="请选择药品" multiple  class="chosen-select-no-results" tabindex="1">
		            	<s:iterator value="#attr.drugList" var="item">
		            		<option value="<s:property value="drugId"/>"><s:property value="drugName"/></option>
		            	</s:iterator>
		             </select>
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
<script src="${ctx}/assets/js/amazeui.chosen.min.js"></script>
<!--<![endif]-->
<script src="${ctx}/assets/js/app.js"></script>
<script type="text/javascript">
//激活chosen插件
$("#drugs").chosen();
//获取症状药品
$.get("${ctx}/pages/Feetype/getFeetypeDrugJSON.do?feeTypeId="+ ${feetype.feeTypeId }+ "&nocache="+ new Date().getTime(),function(data){
	var hasDrugs=JSON.parse(data);
	var arr=new Array();
	var drugs=$("#drugs option");
	$.each(hasDrugs,function(i,item1){
		$.each(drugs,function(j,item2){
			if(item2.value==item1.drugId){
				$(item2).attr("selected","selected");
				return true;
			}
		});
	});
	$("#drugs").trigger('chosen:updated');
});
</script>
</body>
</html>
