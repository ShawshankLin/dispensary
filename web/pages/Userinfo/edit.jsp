<%@page import="com.dispensary.project.model.Role"%>
<%@page import="com.dispensary.project.model.UserRoleInfo"%>
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
  <meta name="description" content="修改用户页面">
  <meta name="keywords" content="form">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="icon" type="image/png" href="assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <link rel="stylesheet" href="${ctx}/assets/css/amazeui.css"/>
  <link rel="stylesheet" href="${ctx}/assets/css/admin.css">
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
		    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg"><%=Userinfo.TABLE_ALIAS%></strong> / <small>编辑</small></div>
		  </div>
		
		  <div class="am-tabs am-margin" data-am-tabs="{noSwipe: 1}">
		    <ul class="am-tabs-nav am-nav am-nav-tabs">
		      <li><a href="#tab1">基本信息</a></li>
		    </ul>
		
		    <div class="am-tabs-bd">
		      <div class="am-tab-panel am-fade" id="tab1">
		        <form class="am-form" action="${ctx}/pages/Userinfo/update.do" method="post">
		          <div class="am-g am-margin-top">
		            <div class="am-u-sm-4 am-u-md-2 am-text-right am-padding-right">
		              	<%=Userinfo.ALIAS_USER_ID%>
		            </div>
		            <div class="am-u-sm-8 am-u-md-4">
		              <input type="text" class="am-input-sm" name="userId" value="${user.userId }" readonly>
		            </div>
		            <div class="am-hide-sm-only am-u-md-6 am-padding-left"></div>
		          </div>
		
		          <div class="am-g am-margin-top">
		            <div class="am-u-sm-4 am-u-md-2 am-text-right am-padding-right">
		            	<%=Userinfo.ALIAS_USER_NAME%>
		            </div>
		            <div class="am-u-sm-8 am-u-md-4 am-u-end">
		              <input type="text" class="am-input-sm" name="userName" value="${user.userName }">
		            </div>
		          </div>
		          
		          <input type="hidden" class="am-input-sm" name="password" value="${user.password}">
		          
				  <div class="am-g am-margin-top">
		            <div class="am-u-sm-4 am-u-md-2 am-text-right am-padding-right">
		              	<%=Userinfo.ALIAS_ME_ST_ID%>
		            </div>
		            <div class="am-u-sm-8 am-u-md-4">
		              <input type="text" class="am-input-sm" name="meStId" value="${user.meStId }">
		            </div>
		            <div class="am-hide-sm-only am-u-md-6 am-padding-left"></div>
		          </div>
				  
				  
				  <div class="am-g am-margin-top">
		            <div class="am-u-sm-4 am-u-md-2 am-text-right am-padding-right">
		              	<%=Userinfo.ALIAS_USER_TYPE_ID%>
		            </div>
		            <div class="am-u-sm-8 am-u-md-4">
		            <select name="userTypeId">
		              <s:iterator value="#attr.usertypeList" var="item">
		              	<s:if test="#attr.user.userTypeId==#item.userTypeId">
		              		<option value='<s:property value="userTypeId"/>' selected="selected"><s:property value="userTypeName"/></option>
		              	</s:if>
		              	<s:else>
		              		<option value='<s:property value="userTypeId"/>'><s:property value="userTypeName"/></option>
		              	</s:else>
		              </s:iterator>
		            </select>
		            </div>
		            <div class="am-hide-sm-only am-u-md-6 am-padding-left"></div>
		          </div>
		          		          
		          <div class="am-g am-margin-top">
		            <div class="am-u-sm-4 am-u-md-2 am-text-right am-padding-right">
		              	添加角色
		            </div>
		            <div class="am-u-sm-8 am-u-md-4">
		            	<div class="am-btn-group" data-am-button>
		            	     <c:forEach items="${roleList}" var="item">
	            	     		<label class="am-btn am-btn-default am-btn-xs">
			                	<input type="checkbox" name="roles" class="roles" value="${item.roleId}"> ${item.roleName}
			                	</label>
		            	     </c:forEach>
		            	</div>
		            </div>
		            <div class="am-u-sm-12 am-u-md-6 am-padding-left">可以添加多个角色</div>
		          </div>
		
		          <div class="am-g am-margin-top">
		            <div class="am-u-sm-4 am-u-md-2 am-text-right am-padding-right">
		              	<%=Userinfo.ALIAS_IF_VALIDITY%>
		            </div>
		            <div class="am-u-sm-8 am-u-md-4">
		            	<div class="am-btn-group" data-am-button>
		            	<c:choose>
			              	<c:when test="${user.ifValidity==1}">
			              	<label class="am-btn am-btn-default am-btn-xs am-active">
			                	<input type=radio name="ifValidity" value="1" checked="checked"/> 是
			                </label>
			                <label class="am-btn am-btn-default am-btn-xs">
				                	<input type="radio" name="ifValidity" value="0"> 否
				            </label>
			                </c:when>
			                <c:otherwise>
			               	<label class="am-btn am-btn-default am-btn-xs">
			                	<input type=radio name="ifValidity" value="1"/> 是
			                </label>
			                <label class="am-btn am-btn-default am-btn-xs am-active">
			                		<input type="radio" name="ifValidity" value="0" checked="checked"> 否
			               	</label>
			                </c:otherwise>
			               </c:choose>
			            </div>
		            </div>
		            <div class="am-u-sm-12 am-u-md-6 am-padding-left">无效用户将不允许登陆系统</div>
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
<script type="text/javascript" src="../../scripts/common.js"></script>
<script type="text/javascript" src="../../scripts/jquery.js"></script>
<script type="text/javascript" src="../../scripts/json2.js"></script>
<script type="text/javascript">
//获取用户角色
$.get("${ctx}/pages/UserRoleInfo/getUserRoles.do?userId="+${user.userId }+"&nocahe="+new Date().getTime(),function(data){
	var roles=JSON.parse(data);
	console.log(roles);
	console.log($(".roles"));
	$.each(roles,function(i,item1){
		$.each($(".roles"),function(j,item2){
			if(item1.roleId==item2.value){
				console.log("zhongle1");
				$(item2).attr("checked","checked");
				$(item2).parent().addClass("am-active");
				return true;
			}
			
		});
	});
});

</script>
</body>
</html>
