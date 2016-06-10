<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>

<head>
	<%@ include file="/commons/meta.jsp" %>
	<base href="<%=basePath%>">
	<link rel="stylesheet" href="<%=basePath%>scripts/uniform/css/uniform.default.css"/>
	<style type="text/css">
		body{
			font-size:14px
		}
		.wwFormTable{
			width:100%;
		}
	</style>
	<script type="text/javascript" src="<%=basePath%>scripts/uniform/jquery.uniform.min.js"></script>
	<script type="text/javascript">
		$(function(){
			$(".formTable :text,.formTable :password").width(185);
			$("select,input,textarea").not($(".gridTable *")).uniform();
			$(".button span").css("line-height","normal");
			$("table tr").click(function(event){
				if(!$(event.target).is(":checkbox")){
					var checkObj = $(this).find(":checkbox");
					checkObj.attr("checked",!checkObj.attr("checked"));
				}
			});
			var hcOffset = $("#toolbar").offset();
			$("#toolbar").click(function(){
				$(this).offset({left:hcOffset.left,top:hcOffset.top});
			});
			$(window).scroll(function(){
				var topInt = $(document).scrollTop();
				var controlPanel = $("#toolbar");
				if(topInt>hcOffset.top){
					controlPanel.offset({top:topInt});
				}else{
					controlPanel.offset({top:hcOffset.top});
				}
			});
		});
		
		
	</script>
	<rapid:block name="head"/>
</head>
<body>
	<%@ include file="/commons/messages.jsp" %>

	<rapid:block name="content"/>
	
</body>
</html>	