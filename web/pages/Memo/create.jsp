<%@page import="com.dispensary.project.model.Memo"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html;charset=UTF-8" pageEncoding="utf-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!doctype html>
<html class="no-js">
<head>
	<meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title><%=Memo.TABLE_ALIAS %>添加</title>
  <meta name="description" content="添加便签页面">
  <meta name="keywords" content="form">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="icon" type="image/png" href="assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <link rel="stylesheet" href="${ctx}/assets/css/amazeui.css"/>
  <link rel="stylesheet" href="${ctx}/assets/css/admin.css">
  <link rel="stylesheet" href="../../kindeditor/themes/default/default.css" />
  <link rel="stylesheet" href="../../kindeditor/plugins/code/prettify.css" />
  <script charset="utf-8" src="../../kindeditor/kindeditor.js"></script>
  <script charset="utf-8" src="../../kindeditor/lang/zh_CN.js"></script>
  <script charset="utf-8" src="../../kindeditor/plugins/code/prettify.js"></script>
  <script type="text/javascript">
  KindEditor.ready(function(K) {

		var editor = K.create('textarea[name="content"]', {

			cssPath : '../../kindeditor/plugins/code/prettify.css',

			uploadJson : '../../kindeditor/jsp/upload_json.jsp',

			fileManagerJson : '../../kindeditor/jsp/file_manager_json.jsp',

			allowFileManager : true,

			afterCreate : function() {

				var self = this;

				K.ctrl(document, 13, function() {

					self.sync();

					document.forms['memo'].submit();

				});

				K.ctrl(self.edit.doc, 13, function() {

					self.sync();

					document.forms['memo'].submit();

				});

			}

		});

		prettyPrint();
	});

</script>
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
		    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg"><%=Memo.TABLE_ALIAS%></strong> / <small>添加</small></div>
		  </div>
		
		  <div class="am-g">
		  		<form name="memo" class="am-form" action="${ctx}/pages/Memo/save.do" method="post">
		          <div class="am-g am-margin-top">
		            <div class="am-u-sm-4 am-u-md-2 am-text-right am-padding-right">
		              	<%=Memo.ALIAS_TITLE%>
		            </div>
		            <div class="am-u-sm-8 am-u-md-8 am-u-end">
		              <input type="text" class="am-input-sm" name="title" required="required">
		            </div>
		          </div>
		
		         <div class="am-g am-margin-top">
		            <div class="am-u-sm-4 am-u-md-2 am-text-right am-padding-right">
		            	<%=Memo.ALIAS_CONTENT%>
		            </div>
		            <div class="am-u-sm-8 am-u-md-8 am-u-end">
		              <textarea name="content" cols="108" rows="15" id="content"></textarea>
		            </div>
		         </div>
		         
			     <div class="am-margin">
			    	<button type="submit" class="am-btn am-btn-primary am-btn-xs">提交保存</button>
			    	<button type="button" class="am-btn am-btn-primary am-btn-xs" onclick="history.back()">放弃保存</button>
			  	</div>
		      </form>
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

