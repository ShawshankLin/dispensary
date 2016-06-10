<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="am-cf admin-main">
  <!-- sidebar start -->
  	<ul class="am-list admin-sidebar-list">
        <s:iterator value="#session.MySession.roleList" >
  			<s:if test="roleId==1">
  				<li><a href="${ctx}/pages/Treatment/visit.jsp"><span class="am-icon-stethoscope"></span> 诊断</a></li>
  			</s:if>
  		 </s:iterator>	
        <li class="admin-parent">
          <a class="am-cf" data-am-collapse="{target: '#data-nav'}"><span class="am-icon-table"></span> 基本数据管理 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
          <ul class="am-list am-collapse admin-sidebar-sub " id="data-nav">
            <li><a href="${ctx}/pages/Student/list.do"><span class="am-icon-graduation-cap"></span>学生信息管理</a></li>
            <li><a href="${ctx}/pages/DrugBasicInfo/list.do"><span class="am-icon-medkit"></span> 药品息管理</a></li>
            <li><a href="${ctx}/pages/Dispensarystaff/list.do"><span class="am-icon-user-md"></span> 医务人员管理</a></li>
            <li><a href="${ctx}/pages/Supplier/list.do"><span class="am-icon-briefcase"></span> 供应商管理</a></li>
            <li><a href="${ctx}/pages/PatiCaseHistory/list.do"><span class="am-icon-puzzle-piece"></span> 病历管理</a></li>
            <!-- <li><a href="${ctx}/pages/PrescriptionInfo/list.do"><span class="am-icon-puzzle-piece"></span> 处方管理</a></li>
            <li><a href="${ctx}/pages/PrescriptionInfoDetail/list.do"><span class="am-icon-puzzle-piece"></span> 处方明细管理</a></li> -->
            <li><a href="${ctx}/pages/DrugStockInDetail/list.do"><span class="am-icon-ambulance"></span> 入库明细管理</a></li>
            <li><a href="${ctx}/pages/Drugunit/list.do"><span class="am-icon-tasks"></span> 药品单位管理</a></li>
            <li><a href="${ctx}/pages/DrugPlace/list.do"><span class="am-icon-tasks"></span> 药品存放位置管理</a></li>
            <li><a href="${ctx}/pages/Symptom/list.do"><span class="am-icon-tasks"></span> 症状管理</a></li>
            <li><a href="${ctx}/pages/Feetype/list.do"><span class="am-icon-tasks"></span> 费用类型管理</a></li>
          </ul>
        </li>
        <s:iterator value="#session.MySession.roleList" >
        <s:if test="roleId==2">
	        <li class="admin-parent">
	          <a class="am-cf" data-am-collapse="{target: '#dispensary-nav'}"><span class="am-icon-hospital-o"></span> 药房管理 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
	          <ul class="am-list am-collapse admin-sidebar-sub" id="dispensary-nav">
	            <li><a href="${ctx}/pages/PrescriptionInfo/list.do"><span class="am-icon-puzzle-piece"></span> 处方划价</a></li>
	            <li><a href="${ctx}/pages/Receipt/list.do"><span class="am-icon-puzzle-piece"></span> 收益管理</a></li>
	          </ul>
	        </li>    
	        <li class="admin-parent">
	          <a class="am-cf" data-am-collapse="{target: '#storehouse-nav'}"><span class="am-icon-building-o"></span> 仓库管理 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
	          <ul class="am-list am-collapse admin-sidebar-sub" id="storehouse-nav">
	            <li><a href="${ctx}/pages/DrugStockIn/list.do"><span class="am-icon-ambulance"></span> 入库管理</a></li>
	            <li><a href="${ctx}/pages/DrugStockInfo/list.do"><span class="am-icon-database"></span> 库存管理</a></li>
	            <%-- <li><a href="${ctx}/pages/DrugStockInfo/showDrugWarn.do"><span class="am-icon-bell"></span> 药品有效期</a></li> --%>
	          </ul>
	        </li>
        </s:if>
        </s:iterator>	
        <li class="admin-parent">
          <a class="am-cf" data-am-collapse="{target: '#tool-nav'}"><span class="am-icon-wrench"></span> 实用工具 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
          <ul class="am-list am-collapse admin-sidebar-sub" id="tool-nav">
          	<li><a href="${ctx}/pages/Memo/list.do"><span class="am-icon-pencil"></span> 备忘录</a></li>
          	<li><a href="${ctx}/pages/Notice/list.do"><span class="am-icon-bullhorn"></span> 发布公告</a></li>
            <li><a href="javascript:var win=window.open('${ctx}/counter.html','计算器', 'width=300, height=450, top=150, left=500, toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no');win.focus();"><span class="am-icon-calculator"></span> 计算器</a></li>
            <li><a href="javascript:var win=window.open('${ctx}/calendar/calendar.html','万年历', 'width=786, height=400, top=150, left=400, toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no');win.focus();"><span class="am-icon-calendar"></span> 万年历</a></li>
          </ul>
        </li>
        <li class="admin-parent">
          <a class="am-cf" data-am-collapse="{target: '#chart-nav'}"><span class="am-icon-area-chart"></span> 图表查看 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
          <ul class="am-list am-collapse admin-sidebar-sub" id="chart-nav">
          	<li><a href="${ctx}/pages/Charts/monthVisitsView.jsp?type=bar"><span class="am-icon-bar-chart"></span> 柱形图</a></li>
          	<li><a href="${ctx}/pages/Charts/monthVisitsView.jsp?type=line"><span class="am-icon-line-chart"></span> 折线图</a></li>
          	<li><a href="${ctx}/pages/Charts/monthVisitsView.jsp?type=pie"><span class="am-icon-pie-chart"></span> 饼状图</a></li>
          	<li><a href="${ctx}/pages/Charts/monthVisitsView.jsp?type=funnel"><span class="am-icon-glass"></span> 漏斗图</a></li>  
          	<!-- Java chart 
          	<li><a href="${ctx}/pages/PatiCaseHistory/bulidColChart.do" class="am-cf"><span class="am-icon-bar-chart"></span> 柱形图</a></li>
            <li><a href="${ctx}/pages/PatiCaseHistory/bulidLineChart.do"><span class="am-icon-line-chart"></span> 折线图</a></li>
            <li><a href="${ctx}/pages/PatiCaseHistory/bulidPieChart.do"><span class="am-icon-pie-chart"></span> 饼状图</a></li> 
          	-->
          </ul>
        </li>
        <s:iterator value="#session.MySession.roleList" >
        <s:if test="roleId==3">
       	 <li class="admin-parent">
          <a class="am-cf" data-am-collapse="{target: '#system-nav'}"><span class="am-icon-cog"></span> 系统管理 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
          <ul class="am-list am-collapse admin-sidebar-sub" id="system-nav">
          	<li><a href="${ctx}/pages/Userinfo/list.do"><span class="am-icon-users"></span> 用户管理</a></li>
            <li><a href="${ctx}/pages/Role/list.do"><span class="am-icon-puzzle-piece"></span> 角色管理</a></li>
            <!--<li><a href="${ctx}/pages/Power/list.do"><span class="am-icon-puzzle-piece"></span> 权限管理</a></li>-->
            <li><a href="${ctx}/pages/Usertype/list.do"><span class="am-icon-puzzle-piece"></span> 用户类型管理</a></li>
            <li><a href="${ctx}/pages/Log/list.do"><span class="am-icon-list-alt"></span> 查看日记</a></li>
          </ul>
        </li>
        </s:if>
       	</s:iterator>	
	 </ul>
	
	      <div class="am-panel am-panel-default admin-sidebar-panel">
	        <div class="am-panel-bd">
	          	<div class="am-icon-bookmark"> 公告-<a href="${ctx}/pages/Notice/search.do?searchType=id&searchInfo=${sessionScope.MySession.notice.id}">${sessionScope.MySession.notice.title}</a></div>
	          	<div class="am-fr "><small>&nbsp;&nbsp;<fmt:formatDate value="${sessionScope.MySession.notice.date }"  type="date" dateStyle="default"/></small></div>
	          	<p>${sessionScope.MySession.notice.content}
	         	</p>
	        </div>
	      </div>
	
	      <div class="am-panel am-panel-default admin-sidebar-panel">
	        <div class="am-panel-bd">
	          <div class="am-icon-tag"></span> 便签-<a href="${ctx}/pages/Memo/search.do?searchType=id&searchInfo=${sessionScope.MySession.memo.id}">${sessionScope.MySession.memo.title}</a></div>
	          <div class="am-fr "><small>&nbsp;&nbsp;<fmt:formatDate value="${sessionScope.MySession.memo.date }"  type="date" dateStyle="default"/></small></div>
	          <p>${sessionScope.MySession.memo.content}</p>
	        </div>
	      </div>
  <!-- sidebar end -->
  <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
  <script>
  	$(function(){
  		$(".admin-sidebar-sub a,.admin-sidebar-list>li:first>a").click(function(){
			$(this).css("color","red");
			$.get("${ctx}/pages/Userinfo/setAccessUrl.do?curUrl="+this.href);
		});
		$.get("${ctx}/pages/Userinfo/getAccessUrl.do",function(data){
			data= data.replace("<%=basePath%>","");
			var clickLink = $("a[href*='"+data+"']");
			clickLink.css("color","red");
			clickLink.parents("ul.admin-sidebar-sub").siblings("a.am-cf").click();
		});
  	})
  </script>
</div>
