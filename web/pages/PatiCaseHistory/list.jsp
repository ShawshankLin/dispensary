<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.dispensary.project.model.*"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<!doctype html>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title><%=PatiCaseHistory.TABLE_ALIAS%>管理</title>
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
	//批量删除病历信息
	function delBatch(){
		if($(":checked[name]").length>0){
			$("#queryForm").attr("action","${ctx}/pages/PatiCaseHistory/delBatch.do").submit();
		}else{
			alert("请选择删除的行！");
		}
		return false;	
	}

	//搜索病历
	function search(){
		var searchInfo=$("#searchInfo").val().trim();
		var searchType= $("ul[id='searchType']").find("li[class='am-active']").attr("value");
		window.location.href=encodeURI(encodeURI("${ctx}/pages/PatiCaseHistory/search.do?searchType="+searchType+"&searchInfo="+searchInfo));
		return false;
	}
	//打印处方单
	function confirmPrint(id){
		$.get("${ctx}/pages/PatiCaseHistory/getCaseJSON.do?id="+id+"&nocache="+new Date().getTime(),function(data){
			var objs=JSON.parse(data);
			//显示学生信息
			var tr=$("table[id='printStu']>tbody tr");
			/* $(tr[0]).children("td").eq(1).text(objs[0].stuNum); */
			$(tr[0]).children("td").eq(1).text(objs[0].stuName);
			$(tr[1]).children("td").eq(1).text(objs[0].sex==1?"男":"女");
			$(tr[2]).children("td").eq(1).text(new Date().getYear()-objs[0].birthDate.year);
			$(tr[3]).children("td").eq(1).text(objs[0].stuClass);
			$(tr[4]).children("td").eq(1).text(objs[0].tel);
			$(tr[5]).children("td").eq(1).text();
			//显示处方明细
			var printDrugs=$("table[id='printDrugs']>tbody");
			var drugFee=$("#drugFee");
			var registerFee=$("#registerFee");
			var otherFee=$("#otherFee");
			printDrugs.empty();
			$.each(objs,function(i,item){
				if(i>1){
					var drugId=item.drugId;
					$.get("${ctx}/pages/DrugBasicInfo/getDrugJSON.do?drugId="+drugId+"&nocache="+new Date().getTime(),function(data){
						var drug=JSON.parse(data);
						$.each(drug,function(j,drug){
							/* printDrugs.append("<tr><td>"+drug.drugName+"</td><td>"+drug.drugSpec
									+"</td><td>"+getDrugunitName(drug.drugKickBack)+"</td><td>"+item.times
									+"</td><td>"+getDrugunitName(drug.quantityUnit)+"</td><td>"+item.days
									+"</td><td>"+item.amount+"</td><td>"+drug.retailPrice+"</td></tr>"); */
									//判断添加药品的费用类型，添加对应的div中
									if(drug.feeTypeId==1){
										drugFee.append("<tr><td>"+drug.drugName+"&nbsp;&nbsp;</td><td><div>"+drug.drugSpec+"<span style='margin-left:20px'></span></div><div>sig:"+item.times+getDrugunitName(drug.drugKickBack)+"</div></td><td>￥"+drug.retailPrice+"</td></tr>");
									}else if(drug.feeTypeId==2){
										registerFee.append("<tr><td>"+drug.drugName+"&nbsp;&nbsp;</td><td><div>"+drug.drugSpec+"<span style='margin-left:20px'></span></div><div>sig:"+item.times+getDrugunitName(drug.drugKickBack)+"</div></td><td>￥"+drug.retailPrice+"</td></tr>");
									}else{
										otherFee.append("<tr><td>"+drug.drugName+"&nbsp;&nbsp;</td><td><div>"+drug.drugSpec+"<span style='margin-left:20px'></span></div><div>sig:"+item.times+getDrugunitName(drug.drugKickBack)+"</div></td><td>￥"+drug.retailPrice+"</td></tr>");
									}
						});
						
					});
				}
			});
			$("#print_total_cost").text("总计："+objs[1].drugSum.toFixed(2)+"元");
			$("#confirm").modal({target: '#confirm', closeViaDimmer: 0, width: 960});
		});
		
	}
	function toPrint(){
		doPrint();
		window.location.href="${ctx}/pages/PatiCaseHistory/list.do";
	}
	//获取药品单位名称
	function getDrugunitName(drugUnitId){
		var drugUnitName;
		$.ajax({
			data: "get",
			url: "${ctx}/pages/Drugunit/getDrugunitJSON.do",
			data: "drugUnitId=" + drugUnitId,
			cache: false,
			async: false,//修改ajax为同步
			success: function (data) {
				var drugunit=JSON.parse(data);	
	 			drugUnitName=drugunit.drugUnitName   
			}
		})
		return drugUnitName;
	}
</script>
<style type="text/css">
	.printtable td{
		line-height: 1.6;
		padding: 0.5rem;
	}
	

</style>
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
							<strong class="am-text-primary am-text-lg"><%=PatiCaseHistory.TABLE_ALIAS%></strong>
							/ <small>管理</small>
						</div>
					</div>

					<div class="am-g">
						<div class="am-u-sm-12 am-u-md-6 am-u-lg-6">
							<div class="am-btn-toolbar">
								<div class="am-btn-group am-btn-group-xs">
									<a  class="am-btn am-btn-default am-btn-success" href="${ctx}/pages/Treatment/visit.jsp">
										<span class="am-icon-plus"></span> 新增
									</a>
									<a  class="am-btn am-btn-default am-btn-success" href="${ctx}/pages/PatiCaseHistory/getCaseReport.do">
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
					          	<input type="text" class="am-form-field" placeholder="请输入病历号" id="searchInfo" onkeydown="enterSearch()">
					          	<div class="am-btn-group">
								  	<button type="button" class="am-btn  am-btn-secondary" onclick="search()">搜索</button>
								  	<div class="am-dropdown" data-am-dropdown>
									    <button class="am-btn am-btn-secondary am-dropdown-toggle" data-am-dropdown-toggle> <span class="am-icon-caret-down"></span></button>
									    <ul class="am-dropdown-content" id="searchType">
									      <li class="am-active" value="caseId"><a href="javascript:void(0)"  onclick="setSearchType(this)">病历号</a></li>
									      <li value="stuNum"><a href="javascript:void(0)" onclick="setSearchType(this)">学生学号</a></li>
									      <li value="meStId"><a href="javascript:void(0)" onclick="setSearchType(this)">医务人员ID</a></li>
									      <li value="meStName"><a href="javascript:void(0)" onclick="setSearchType(this)">医务人员姓名</a></li>
									      <li value="visitDate" onclick="javascript:$('#searchInfo').datepicker('open');"><a href="javascript:void(0)" onclick="setSearchType(this)">就诊日期</a></li>
									    </ul>
								  	</div>
							  	</div>		      
						  	</div>
						</div>
					</div>

					<div class="am-g">
						<div class="am-u-sm-12">
							<form id="queryForm" name="queryForm" class="am-form" action="<c:url value="/pages/PatiCaseHistory/list.do"/>" method="post">
								<table class="am-table am-table-striped am-table-hover table-main">
									<thead>
										<tr>											
											<th><input type="checkbox" name="item" id="allitem"
												onclick="checkAllBox(this)" /></th>
											<th>ID</th>
											<!-- 排序时为th增加sortColumn即可,new SimpleTable('sortColumns')会为tableHeader自动增加排序功能; -->
											<th sortColumn="Case_ID"><%=PatiCaseHistory.ALIAS_CASE_ID%></th>
											<th>学生姓名</th>
											<th sortColumn="VisitDate"><%=PatiCaseHistory.ALIAS_VISIT_DATE%></th>
											<th sortColumn="primary_care">诊断类型</th>
											<th sortColumn="DispensaryRecord"><%=PatiCaseHistory.ALIAS_DISPENSARY_RECORD%></th>
											<th sortColumn="allergy"><%=PatiCaseHistory.ALIAS_ALLERGY%></th>
											
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${page.result}" var="item" varStatus="status">
											<tr>
												<td><input type="checkbox" name="items"
													value="id=${item.id}"></td>
												<td>${page.thisPageFirstElementNumber + status.index}</td>
												<td><c:out value='${item.caseId}' /></td>
												<td><a href="${ctx}/pages/Student/search.do?searchType=stuNum&searchInfo=${item.stuNum}"><c:out value='${item.stuNumModel.stuName}' /></a></td>
												<td><c:out value='${item.visitDateString}' /></td>
												<td><c:out value='${item.primaryCare}' /></td>
												<td><c:out value='${item.dispensaryRecord}' /></td>
												<td><c:out value='${item.allergy}' /></td>
												<td>
													<div class="am-btn-toolbar">
														<div class="am-btn-group am-btn-group-xs">
															<button type="button" class="am-btn am-btn-default am-text-success am-hide-sm-only" data-am-modal="{target: '#show${item.caseId}', closeViaDimmer: 0, width: 600, height: 425}">
																<span class="am-icon-search-plus"></span> 查看
															</button>
															<!-- 查看 -->
															<div class="am-modal am-modal-no-btn" tabindex="-1" id="show${item.caseId}">
															  <div class="am-modal-dialog">
															    <div class="am-modal-hd">病历基本信息
															      <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
															    </div>
															    <div class="am-modal-bd">
															    	<div style="overflow:auto;width:100%;height:300px">
															     	<table class="am-table am-table-striped am-table-hover">
																		<tr>
																			<td><%=PatiCaseHistory.ALIAS_CASE_ID%></td>
																			<td>${item.caseId}</td>
																		</tr>
																		<tr>
																			<td><%=PatiCaseHistory.ALIAS_STU_NUM%></td>
																			<td>${item.stuNum}</td>
																		</tr>
																		<tr>
																			<td><%=PatiCaseHistory.ALIAS_VISIT_DATE%></td>
																			<td>${item.visitDateString}</td>
																		</tr>
																		<tr>
																			<td><%=PatiCaseHistory.ALIAS_PRIMARY_CARE%></td>
																			<td>${item.primaryCare}</td>
																		</tr>
																		<tr>
																			<td><%=PatiCaseHistory.ALIAS_DISPENSARY_RECORD%></td>
																			<td>${item.dispensaryRecord}</td>
																		</tr>
																		<tr>
																			<td><%=PatiCaseHistory.ALIAS_ALLERGY%></td>
																			<td>${item.allergy}</td>
																		</tr>
																		
																		<%-- <tr>
																			<td><%=PatiCaseHistory.ALIAS_RE_EXAMINATION%></td>
																			<td>${item.reExamination}</td>
																		</tr>
																		<tr>
																			<td><%=PatiCaseHistory.ALIAS_DESCRIBES%></td>
																			<td>${item.describes}</td>
																		</tr>
																		<tr>
																			<td><%=PatiCaseHistory.ALIAS_EXAMINE_STATUS%></td>
																			<td>${item.examineStatus}</td>
																		</tr> --%>
																		<tr>
																			<td><%=PatiCaseHistory.ALIAS_FIRST_IMPRESS%></td>
																			<td>${item.firstImpress}</td>
																		</tr>
																		<tr>
																			<td><%=PatiCaseHistory.ALIAS_ME_ST_ID%></td>
																			<td>${item.meStIdModel.modelTagValue}</td>
																		</tr>
																	</table>
																	</div>
															    </div>
															  </div>
															</div>
															<a class="am-btn am-btn-default am-text-secondary" href="${ctx}/pages/PrescriptionInfo/search.do?searchType=caseId&searchInfo=${item.caseId}">
															  <span class="am-icon-search-plus"></span> 查看处方单
															</a>
															<a class="am-btn am-btn-default am-text-warning" href="javascript:void(0)" onclick="confirmPrint(${item.id})">
															  <span class="am-icon-print"></span> 打印处方单
															</a>
															<a class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only" onclick="return confirm('${ctx}/pages/PatiCaseHistory/delete.do?id=${item.id}')" href="javascript:void(0)">
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
									<div class="am-fr am-margin-bottom am-margin-right">
									<simpletable:pageToolbar page="${page}">
									</simpletable:pageToolbar>
									<!-- <ul class="am-pagination">
										<li class="am-disabled"><a href="#">«</a></li>
										<li class="am-active"><a href="#">1</a></li>
										<li><a href="#">2</a></li>
										<li><a href="#">3</a></li>
										<li><a href="#">4</a></li>
										<li><a href="#">5</a></li>
										<li><a href="#">»</a></li>
									</ul> -->
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
<!-- 打印处方单 -->

<div class="am-modal am-modal-no-btn" tabindex="-1" id="confirm" >	
  	<div class="am-modal-dialog" style="width:960px;margin:0 auto">
    	<div style="overflow:auto;width:100%;height:500px">
    	<!--startprint-->	
    	<div class="am-modal-hd">
    		<!-- 游览器可见，打印机不可见 -->
    		<div class="am-print-block" style="text-align: center;">东莞理工学院城市学院处方笺</div>
      		<!-- 打印机可见，游览器不可见 -->
      		<div class="am-print-hide">东莞理工学院城市学院处方笺<a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a></div>
    	</div>
   		<div class="am-g" style="border-top:1px solid #000000;">
      		<table>
      			<tr>
      			<td style="border-right:1px solid #000000;" valign="top">
      				<div style="text-align: left;font-weight:bold">&nbsp;&nbsp;初步印象：</div>
		  			<table class="printtable" id="printStu" style="width:200px">
	   					<tr><td>姓名：</td><td></td></tr>
	   					<tr><td>性别：</td><td></td></tr>
	   					<tr><td>年龄：</td><td></td></tr>
	   					<tr><td>类别：</td><td></td></tr>
	   					<tr><td>电话：</td><td></td></tr>
	   					<jsp:useBean id="now" class="java.util.Date"></jsp:useBean>
	   					<tr><td>时间（当天有效）：</td><td><fmt:formatDate value="${now}"  pattern="yyyy-MM-dd"/></td></tr>
	 				</table>
 				</td>
 				<td valign="top">
				  	<table class="printtable" id="printDrugs" style="width:760px">
				  		<thead>
							<tr>
								<th style="font-size:22px;font-weight:bold" colspan="3">&nbsp;&nbsp;℞</th>									
							</tr>
						</thead>
						<tbody id="drugFee">									
						</tbody>
						<tbody id="registerFee">									
						</tbody>
						<tbody id="otherFee">									
						</tbody>
				  	</table>
			  	  </td>
			  	</tr>
			 </table>
		</div>		
		<table width="100%">
			<tr>
				<td></td>
				<td></td>
				<td><h3 id="print_total_cost"></h3></td>
			</tr>
			<tr>
				<td><h3>医生签名：</h3></td>
				<td><h3>核对/发药：</h3></td>
				<td></td>
			</tr>
		</table>
		<!--endprint-->
	  </div>
	  <!-- 在打印预览中查看效果 -->
	  <div class="am-print-hide"><button type="button" class="am-btn am-btn-primary am-btn-block" onclick="toPrint()">确定打印</button></div>
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
