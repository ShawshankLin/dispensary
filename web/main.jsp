<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import= "com.dispensary.project.utils.SessionCounter"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/commons/taglibs.jsp"%>  
<div class="am-cf admin-main">
  <!-- content start -->
  <div class="admin-content">

    <div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">首页</strong></div>
    </div>

    <ul class="am-avg-sm-1 am-avg-md-4 am-margin am-padding am-text-center admin-content-list ">
      <li><a href="${ctx}/pages/DrugStockInfo/list.do" class="am-text-success"><span class="am-icon-btn am-icon-medkit"></span><br/>库存药品数<br/>${MySession.stockDrugNum }</a></li>
      <li><a href="${ctx}/pages/PatiCaseHistory/list.do" class="am-text-warning"><span class="am-icon-btn am-icon-history"></span><br/>历史就诊数<br/>${MySession.histVisitsNum }</a></li>
      <%
      	Date now=new Date();
      	now.setDate(now.getDate()-1);
      	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
      	request.setAttribute("now", format.format(now));
      %>
      <li><a href="${ctx}/pages/PatiCaseHistory/search.do?searchType=visitDate&searchInfo=${now}" class="am-text-danger"><span class="am-icon-btn am-icon-h-square"></span><br/>昨日就诊数<br/>${MySession.ydayVisitsNum}</a></li>
      <li><a href="${ctx}/pages/Dispensarystaff/getOnlineUser.do"  class="am-text-secondary"><span class="am-icon-btn am-icon-user-md"></span><br/>在线用户<br/><%=SessionCounter.getActiveSessions()%></a></li>
    </ul>

    <div class="am-g">
      <div class="am-u-sm-12">
        <table class="am-table am-table-bd am-table-striped admin-content-table">
          <thead>
          <tr>
            <th>ID</th><th>医生</th><th>最后就诊时间</th><th>就诊人数</th><th>操作</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach items="${MySession.topStaffs}" var="item" varStatus="status" >
          <tr>
          	<td>${status.count}</td>
          	<td><c:out value="${item.meStName }"></c:out></td>
          	<td>${item.lastVisitedTime}</td> 
          	<td>
          		<c:choose>
          			<c:when test="${status.count==1}">
          				<span class="am-badge am-badge-primary"><c:out value="${item.topVisits}"></c:out></span>
          			</c:when>
          			<c:when test="${status.count==2}">
          				<span class="am-badge am-badge-secondary"><c:out value="${item.topVisits}"></c:out></span>
          			</c:when>
          			<c:when test="${status.count==3}">
          				<span class="am-badge am-badge-success"><c:out value="${item.topVisits}"></c:out></span>
          			</c:when>
          			<c:when test="${status.count==4}">
          				<span class="am-badge am-badge-warning"><c:out value="${item.topVisits}"></c:out></span>
          			</c:when>
          			<c:otherwise>
          				<span class="am-badge am-badge-danger"><c:out value="${item.topVisits}"></c:out></span>
          			</c:otherwise>
          		</c:choose>
          	</td>
            <td>
              <div class="am-dropdown" data-am-dropdown>
                <button class="am-btn am-btn-default am-btn-xs am-dropdown-toggle" data-am-dropdown-toggle><span class="am-icon-cog"></span> <span class="am-icon-caret-down"></span></button>
                <ul class="am-dropdown-content">
                  <li><a href="${ctx}/pages/Dispensarystaff/search.do?searchType=meStId&searchInfo=${item.meStId }">1. 查看个人信息</a></li>
                  <li><a href="${ctx}/pages/PatiCaseHistory/search.do?searchType=meStId&searchInfo=${item.meStId }">2. 查看就诊记录</a></li>
                </ul>
              </div>
            </td>
          </tr>
           </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
    <div class="am-g">
      <div class="am-u-md-6">
      	<div class="admin-content">
			<div class="am-cf am-padding">
				<div class="am-panel am-panel-default">
		          <div class="am-panel-hd am-cf" data-am-collapse="{target: '#collapse-panel-1'}">每月就诊柱状图<span class="am-icon-chevron-down am-fr" ></span></div>
		          <div class="am-panel-bd am-collapse am-in" id="collapse-panel-1">
		            <iframe id="chart" src="${ctx}/pages/Charts/barChart.jsp"  width="100%" height="380px"  frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe> 
		          </div>
		        </div>
			</div>
		</div>
      </div>

      <div class="am-u-md-6">
      	<div class="admin-content">
			<div class="am-cf am-padding">
				<div class="am-panel am-panel-default">
		          <div class="am-panel-hd am-cf" data-am-collapse="{target: '#collapse-panel-2'}">每月就诊饼状图<span class="am-icon-chevron-down am-fr" ></span></div>
		          <div class="am-panel-bd am-collapse am-in" id="collapse-panel-2">
		            <iframe id="chart" src="${ctx}/pages/Charts/pieChart.jsp"  width="100%" height="380px"  frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe> 
		          </div>
		        </div>
			</div>
		</div>
      </div>
    </div>
  </div>
  <!-- content end -->

</div>
