<%@page import="com.dispensary.project.model.Userinfo"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html;charset=UTF-8" pageEncoding="utf-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!doctype html>
<html class="no-js">
<head>
	<meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title><%=Userinfo.TABLE_ALIAS %></title>
  <meta name="description" content="添加用户页面">
  <meta name="keywords" content="form">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="icon" type="image/png" href="assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <link rel="stylesheet" href="${ctx}/assets/css/amazeui.css"/>
  <link rel="stylesheet" href="${ctx}/assets/css/admin.css">
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
		    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">密码</strong> / <small>修改</small></div>
		  </div>
		
		  <div class="am-tabs am-margin" data-am-tabs="{noSwipe: 1}">
		    <ul class="am-tabs-nav am-nav am-nav-tabs">
		      <li><a href="#tab1">基本信息</a></li>
		    </ul>
		
		    <div class="am-tabs-bd">
		      <div class="am-tab-panel am-fade" id="tab1">
		        <form class="am-form" action="${ctx}/pages/Userinfo/changePWD.do" method="post">
		        	<input type="hidden" class="am-input-sm" name="userId" value="${MySession.userinfo.userId }" required>
		        	
		          <div class="am-g am-margin-top">
		            <div class="am-u-sm-4 am-u-md-2 am-text-right am-padding-right">
		            	旧密码
		            </div>
		            <div class="am-u-sm-8 am-u-md-4 am-u-end">
		              <input type="password" class="am-input-sm"  id="oldPWD" required>
		            </div>
		            <div class="am-u-sm-12 am-u-md-6 am-padding-left" id="msg1"></div>
		          </div>
		          
				<div class="am-g am-margin-top">
		            <div class="am-u-sm-4 am-u-md-2 am-text-right am-padding-right">
		            	新密码
		            </div>
		            <div class="am-u-sm-8 am-u-md-4 am-u-end">
		              <input type="password" class="am-input-sm" name="password" id="newPWD" required/>
		            </div>
		          </div>
		          
		          <div class="am-g am-margin-top">
		            <div class="am-u-sm-4 am-u-md-2 am-text-right am-padding-right">
		            	确认密码
		            </div>
		            <div class="am-u-sm-8 am-u-md-4 am-u-end">
		              <input type="password" class="am-input-sm" placeholder="请与上面输入的值一致" id="confirmPWD" required/>
		            </div>
		            <div class="am-u-sm-12 am-u-md-6 am-padding-left" id="msg2"></div>
		          </div>

			     <div class="am-margin">
			    	<button type="submit" class="am-btn am-btn-primary am-btn-xs" onclick="return check()">提交保存</button>
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
<script type="text/javascript">
$("#oldPWD").blur(function(){
	$.post("${ctx}/pages/Userinfo/checkPWD.do",{"userId":$("#userId").val(),"password":$("#oldPWD").val()},function(data){
		var obj=JSON.parse(data);
		if(obj.result=="true"){
			$("#msg1").text("").removeClass("am-text-danger").addClass("am-text-success am-icon-check ");
		}else{
			$("#msg1").text("旧密码不正确").removeClass("am-text-success").addClass("am-text-danger");
		}
		
	});			
});
$("#confirmPWD").blur(function(){
	var confirmPWD=$("#confirmPWD").val();
	var newPWD=$("#newPWD").val();
	if(newPWD!=confirmPWD){
		$("#msg2").text("两次输入的密码不一致").removeClass("am-text-success").addClass("am-text-danger");
	}else{
		$("#msg2").text("").removeClass("am-text-danger").addClass("am-text-success am-icon-check ");
	}
});
</script>
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
