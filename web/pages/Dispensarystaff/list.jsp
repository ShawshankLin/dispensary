<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.dispensary.project.model.*"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<!doctype html>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title><%=Dispensarystaff.TABLE_ALIAS%>管理</title>
<meta name="description" content="这是一个form页面">
<meta name="keywords" content="form">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="icon" type="image/png" href="${ctx}/assets/i/favicon.png">
<link rel="apple-touch-icon-precomposed"
	href="${ctx}/sassets/i/app-icon72x72@2x.png">
<meta name="apple-mobile-web-app-title" content="Amaze UI" />
<link rel="stylesheet" href="${ctx}/assets/css/amazeui.css" />
<link rel="stylesheet" href="${ctx}/assets/css/admin.css">
<script type="text/javascript" src="<c:url value="/widgets/simpletable/simpletable.js"/>"></script>
<script type="text/javascript" src="../../scripts/common.js"></script>	
<script type="text/javascript" src="../../scripts/jquery.js"></script>
<script type="text/javascript" src="../../scripts/json2.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		// 分页需要依赖的初始化动作
		window.simpleTable = new SimpleTable('queryForm',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
	});
	//批量删除医务人员
	function delBatch(){
		if($(":checked[name]").length>0){
			$("#queryForm").attr("action","${ctx}/pages/Dispensarystaff/delBatch.do").submit();
		}else{
			alert("请选择删除的行！");
		}
		return false;	
	}
	//查询医务人员信息
	function searchStaff(value) {
		if(value!=null){
			$("#searchInfo").val(value);
		}
		$("#searchStaffBox").html("");
		$("#searchStaffBox").css("display","none");
		$.get("${ctx}/pages/Dispensarystaff/getStaffInfo.do?meStId="
				+ $("#searchInfo").val() + "&nocache="
				+ new Date().getTime(), function(data) {
			if(data){
				var obj = JSON.parse(data);
				var table=$(".data_table tbody");
				table.empty();
				table.append("<tr><td>1</td><td><input type='checkbox' name='items' value='meStId="+obj.meStId+"'></td><td>"+obj.meStId+"</td><td>"+obj.meStName+"</td><td>"+(obj.sex==1?'男':'女')+"</td><td>"+obj.age+"</td><td>"+obj.education+"</td><td>"+obj.address+"</td><td>"+obj.tel+"</td><td>"+obj.mobile+"</td><td>"+obj.email+"</td><td>"+obj.note+"</td><td>"+obj.workPlace+"</td></tr>");
				
			}else{
				$(".data_table tbody").empty();
			}
		});
	}
	//搜索医务人员
	function search(){
		var searchInfo=$("#searchInfo").val().trim();;
		var searchType= $("ul[id='searchType']").find("li[class='am-active']").attr("value");
		window.location.href=encodeURI(encodeURI("${ctx}/pages/Dispensarystaff/search.do?searchType="+searchType+"&searchInfo="+searchInfo));
		return false;
	}
	
</script>
</head>
<body>
	<div class="am-g am-g-collapse">
		<div class="am-u-lg-12"><jsp:include page="../head.jsp" /></div>
		<div class="am-u-lg-2"><jsp:include page="../menu.jsp" /></div>
		<div class="am-u-lg-10">
			<div class="am-cf admin-main">
				<!-- content start -->
				<div class="admin-content">
					<div class="am-cf am-padding">
						<div class="am-fl am-cf">
							<strong class="am-text-primary am-text-lg"><%=Dispensarystaff.TABLE_ALIAS%></strong>
							/ <small>管理</small>
						</div>
					</div>

					<div class="am-g">
						<div class="am-u-sm-12 am-u-md-6 am-u-lg-6">
							<div class="am-btn-toolbar">
								<div class="am-btn-group am-btn-group-xs">
									<a  class="am-btn am-btn-default am-btn-success" href="${ctx}/pages/Dispensarystaff/create.do">
										<span class="am-icon-plus"></span> 新增
									</a>
									<a  class="am-btn am-btn-default am-btn-success" href="${ctx}/pages/Dispensarystaff/getStaffReport.do">
										<span class="am-icon-arrow-circle-o-down"></span> 导出
									</a>
									<button type="button" class="am-btn am-btn-default am-btn-danger" onclick="delBatch()">
										<span class="am-icon-trash-o"></span> 批量删除
									</button>
								</div>
							</div>
						</div>
						<div class="am-u-sm-12 am-u-md-6 am-u-lg-6">
							<div class="am-topbar-form am-form-inline am-topbar-right">
					          	<input type="text" class="am-form-field" placeholder="请输入医务人员ID" id="searchInfo" onkeydown="enterSearch()">
					          	<div class="am-btn-group">
								  	<button type="button" class="am-btn  am-btn-secondary" onclick="search()">搜索</button>
								  	<div class="am-dropdown" data-am-dropdown>
									    <button class="am-btn am-btn-secondary am-dropdown-toggle" data-am-dropdown-toggle> <span class="am-icon-caret-down"></span></button>
									    <ul class="am-dropdown-content" id="searchType">
									      <li class="am-active" value="meStId"><a href="javascript:void(0)"  onclick="setSearchType(this)">医务人员ID</a></li>
									      <li value="meStName"><a href="javascript:void(0)" onclick="setSearchType(this)">医务人员姓名</a></li>
									    </ul>
								  	</div>
							  	</div>		      
						  	</div>
						</div>
					</div>

					<div class="am-g">
						<div class="am-u-sm-12">
							<form id="queryForm" name="queryForm" class="am-form" action="<c:url value="/pages/Dispensarystaff/list.do"/>" method="post">
								<table class="am-table am-table-striped am-table-hover table-main">
									<thead>
										<tr>											
											<th><input type="checkbox" name="item" id="allitem"
												onclick="checkAllBox(this)" /></th>
											<th>ID</th>
											<!-- 排序时为th增加sortColumn即可,new SimpleTable('sortColumns')会为tableHeader自动增加排序功能; -->
											<th sortColumn="MeStId"><%=Dispensarystaff.ALIAS_ME_ST_ID%></th>
											<th sortColumn="MeStName"><%=Dispensarystaff.ALIAS_ME_ST_NAME%></th>
											<th sortColumn="Sex"><%=Dispensarystaff.ALIAS_SEX%></th>
											<th sortColumn="Age"><%=Dispensarystaff.ALIAS_AGE%></th>
											<th sortColumn="Education"><%=Dispensarystaff.ALIAS_EDUCATION%></th>
											<th sortColumn="Address"><%=Dispensarystaff.ALIAS_ADDRESS%></th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${page.result}" var="item" varStatus="status">
											<tr>
												<td><input type="checkbox" name="items"
													value="meStId=${item.meStId}"></td>
												<td>${page.thisPageFirstElementNumber + status.index}</td>
												<td><c:out value='${item.meStId}' /></td>
												<td><a href="javascript:void(0)" data-am-modal="{target: '#show${item.meStId}', closeViaDimmer: 0, width: 600, height: 425}"><c:out value='${item.meStName}' /></a>&nbsp;</td>
												<td><c:out value='${item.sex==0?"女":"男"}' />&nbsp;</td>
												<td><c:out value='${item.age}' />&nbsp;</td>
												<td><c:out value='${item.education}' />&nbsp;</td>
												<td><c:out value='${item.address}' />&nbsp;</td>
												
												<td>
												<div class="am-btn-toolbar">
													<div class="am-btn-group am-btn-group-xs">
														<button type="button" class="am-btn am-btn-default am-text-success am-hide-sm-only" data-am-modal="{target: '#show${item.meStId}', closeViaDimmer: 0, width: 600, height: 425}">
															<span class="am-icon-search-plus"></span> 查看
														</button>
														<!-- 模拟窗口 -->
														<div class="am-modal am-modal-no-btn" tabindex="-1" id="show${item.meStId}">
														  <div class="am-modal-dialog">
														    <div class="am-modal-hd">医务人员基本信息
														      <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
														    </div>
														    <div class="am-modal-bd">
														    	<div style="overflow:auto;width:100%;height:300px">
														     	<table class="am-table am-table-striped am-table-hover">
																	<tr>
																		<td><%=Dispensarystaff.ALIAS_ME_ST_ID%></td>
																		<td>${item.meStId}</td>
																	</tr>
																	<tr>
																		<td><%=Dispensarystaff.ALIAS_ME_ST_NAME%></td>
																		<td>${item.meStName}</td>
																	</tr>
																	<tr>
																		<td><%=Dispensarystaff.ALIAS_SEX%></td>
																		<td>${item.sex==0?"女":"男"}</td>
																	</tr>
																	<tr>
																		<td><%=Dispensarystaff.ALIAS_AGE%></td>
																		<td>${item.age}</td>
																	</tr>
																	<tr>
																		<td><%=Dispensarystaff.ALIAS_EDUCATION%></td>
																		<td>${item.education}</td>
																	</tr>
																	<tr>
																		<td><%=Dispensarystaff.ALIAS_ADDRESS%></td>
																		<td>${item.address}</td>
																	</tr>
																	<tr>
																		<td><%=Dispensarystaff.ALIAS_TEL%></td>
																		<td>${item.tel}</td>
																	</tr>
																	<tr>
																		<td><%=Dispensarystaff.ALIAS_MOBILE%></td>
																		<td>${item.mobile}</td>
																	</tr>
																	<tr>
																		<td><%=Dispensarystaff.ALIAS_EMAIL%></td>
																		<td>${item.email}</td>
																	</tr>
																	<tr>
																		<td><%=Dispensarystaff.ALIAS_WORK_PLACE%></td>
																		<td>${item.workPlace}</td>
																	</tr>
																	<tr>
																		<td><%=Dispensarystaff.ALIAS_NOTE%></td>
																		<td>${item.note}</td>
																	</tr>
																</table>
																</div>
														    </div>
														  </div>
														</div>
														<a class="am-btn am-btn-default am-text-secondary" href="${ctx}/pages/Dispensarystaff/edit.do?meStId=${item.meStId}">
														  <span class="am-icon-pencil-square-o"></span> 编辑
														</a>
														<a class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only" onclick="return confirm('${ctx}/pages/Dispensarystaff/delete.do?meStId=${item.meStId}')" href="javascript:void(0)">
															<span class="am-icon-trash-o"></span> 删除
														</a>
													</div>
												</div>
											</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<div class="am-cf">
									<div class="am-fr am-margin-right am-margin-bottom">
										<simpletable:pageToolbar page="${page}">
									</simpletable:pageToolbar>
									</div>
								</div>
							</form>
						</div>

					</div>
				</div>
				<!-- content end -->
			</div>



		</div>
	</div>
<!-- message box -->
<div class="am-modal am-modal-confirm" tabindex="-1" id="delConfirm">
  <div class="am-modal-dialog">
    <div class="am-modal-hd">消息</div>
    <div class="am-modal-bd">
      	你，确定要删除这条记录吗？
    </div>
    <div class="am-modal-footer">
      <span class="am-modal-btn" data-am-modal-cancel>取消</span>
      <span class="am-modal-btn" data-am-modal-confirm>确定</span>
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
