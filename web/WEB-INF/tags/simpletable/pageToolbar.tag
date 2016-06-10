<%@ tag pageEncoding="UTF-8"%>
<%@ attribute name="page" required="true" type="cn.org.rapid_framework.page.Page" description="Page.java" %>
<%@ attribute name="pageSizeSelectList" type="java.lang.Number[]" required="false"  %>
<%@ attribute name="isShowPageSizeList" type="java.lang.Boolean" required="false"  %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	// set default values
	Integer[] defaultPageSizes = new Integer[]{10,50,100};
	if(jspContext.getAttribute("pageSizeSelectList") == null) {
		jspContext.setAttribute("pageSizeSelectList",defaultPageSizes); 
	}
	
	if(jspContext.getAttribute("isShowPageSizeList") == null) {
		jspContext.setAttribute("isShowPageSizeList",true); 
	} 
%>
<ul data-am-widget="pagination" class="am-pagination am-pagination-default">
  
  <c:choose>
	<c:when test="${page.firstPage}">
	</c:when>
	<c:otherwise>
		  <li class="am-pagination-first ">
		    <a href="javascript:simpleTable.togglePage(1);" class="">第一页</a>
		 </li>
	</c:otherwise>
  </c:choose>
  <c:choose>
<c:when test="${page.hasPreviousPage}">
<li class="am-pagination-prev ">
    <a href="javascript:simpleTable.togglePage(${page.previousPageNumber});">上一页</a>
  </li>
</c:when>
<c:otherwise>
</c:otherwise>
</c:choose>
	
<c:forEach var="item" items="${page.linkPageNumbers}">
<c:choose>
<c:when test="${item == page.thisPageNumber}">
<li class="am-active"><a href="javascript:void(0)">${item}</a></li>
</c:when>
<c:otherwise>
<li class="">
  <a href="javascript:simpleTable.togglePage(${item});" class="">${item}</a>
</li>
</c:otherwise>
</c:choose>
</c:forEach>

<c:choose>
<c:when test="${page.hasNextPage}">
<li class="am-pagination-next ">
    <a href="javascript:simpleTable.togglePage(${page.nextPageNumber});" class="">下一页</a>
</li>
</c:when>
<c:otherwise></c:otherwise>
</c:choose>  

<c:choose>
<c:when test="${page.lastPage}"></c:when>
<c:otherwise>
<li class="am-pagination-last ">
    <a href="javascript:simpleTable.togglePage(${page.lastPageNumber});" class="">最末页</a>
  </li>
</c:otherwise>
</c:choose>

<c:if test="${isShowPageSizeList}">
<select onChange="simpleTable.togglePageSize(this.value)">
<c:forEach var="item" items="${pageSizeSelectList}">
	<option value="${item}" ${page.pageSize == item ? 'selected' : '' }>${item}</option>
</c:forEach> 
</select>
</c:if> 
</ul>

