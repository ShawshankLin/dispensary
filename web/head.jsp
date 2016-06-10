<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%
	if(session.getAttribute("MySession")==null){
%>	
	<script language=javascript>window.top.location.href="${ctx}/login.jsp"</script>	
<%		
	}
%>
<header class="am-topbar am-topbar-fixed-top admin-header">
  <div class="am-topbar-brand">
    <strong style="font-size:22px;">东莞理工学院城市学院</strong> <small style="font-size:18px;">医务室医疗系统</small>
  </div>

  <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span></button>

  <div class="am-collapse am-topbar-collapse" id="topbar-collapse">

    <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
      <li><a href="${ctx}/index.jsp"><span class="am-icon-home am-icon-sm"></span> 首页 </a></li>
      <li class="am-dropdown" data-am-dropdown>
        <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
          <span class="am-icon-bookmark-o"></span> ${MySession.userinfo.userName } <span class="am-icon-caret-down"></span>
        </a>
        <ul class="am-dropdown-content">
          <li><a href="${ctx}/pages/Dispensarystaff/edit.do?meStId=${MySession.userinfo.meStId }"><span class="am-icon-user"></span> 资料</a></li>
          <li><a href="${ctx}/pages/Userinfo/password.jsp"><span class="am-icon-lock"></span> 修改密码</a></li>
          <li><a href="${ctx}/pages/Userinfo/logout.do"><span class="am-icon-sign-out"></span> 退出</a></li>
        </ul>
      </li>
      <li class="am-hide-sm-only"><a href="javascript:;" id="admin-fullscreen"><span class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>
    </ul>
  </div>
</header>
