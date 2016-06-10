<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.dispensary.project.model.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>请确定<%=PrescriptionInfo.TABLE_ALIAS %>单</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet" type="text/css" href="../../styles/common.css" />
<link rel="stylesheet" type="text/css" href="../../styles/treatment/prescription_view.css">
<link type="text/css" rel="stylesheet" href="../../styles/pure-min.css">
<script type="text/javascript" src="../../scripts/common.js"></script>
<script type="text/javascript" src="../../scripts/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		window.onload = function(){
			$("#box").fadeIn(60000);
		}
	});
	//打印处方
	function printdiv(print_content)  
	{  
		var headstr = "<html><head><title></title></head><body>";  
		var footstr = "</body>";  
		var printData = document.getElementById(print_content).innerHTML; //获得 div 里的所有 html 数据
		var oldstr = document.body.innerHTML;  
		document.body.innerHTML = headstr+printData+footstr;  
		window.print();  
		document.body.innerHTML = headstr+oldstr+footstr; 
		return false;  
	}  
</script>
</head>
<body>
	<!-- 表单提交 -->
	<form action="${ctx}/pages/PatiCaseHistory/save.do" method="post">
		<div id="box">
			<span class="title"><%=PrescriptionInfo.TABLE_ALIAS %></span>
			<div class="head">
				<table width=400px>
					<tr>
						<td class="th">处方单编号：</td>
						<td>
						<c:forEach items="${pch.prescriptionInfos}" var="item" varStatus="status">
							${item.presId}
							<!-- 处方主表 -->
							<c:set var="presId" value="${item.presId}"/>
							<c:set var="drugSum" value="${item.drugSum}"/>
							<input type="hidden" name="prescriptionInfos.makeNew[${status.index}].presId" value="${item.presId}"/>
							<input type="hidden" name="prescriptionInfos.makeNew[${status.index}].caseId" value="${item.caseId}"/>
							<input type="hidden" name="prescriptionInfos.makeNew[${status.index}].persDate" value="${item.persDate}"/>
							<input type="hidden" name="prescriptionInfos.makeNew[${status.index}].state" value="${item.state}"/>
							<input type="hidden" name="prescriptionInfos.makeNew[${status.index}].drugSum" value="${item.drugSum}"/>
						</c:forEach> 
						</td>
						<td class="th">开单时间：</td>
						<td>${pch.getVisitDateString()}<input type="hidden" name="visitDate" value="${pch.getVisitDateString()}"></td>
					</tr>
					<tr>
						<td class="th">学号：</td>
						<td>${pch.stuNum }<input type="hidden" name="stuNum" value="${pch.stuNum}"/></td>
						<td class="th">药品总价：</td>
						<%-- <td>${pch.stuNumModel.stuName}</td> --%>
						<td>${drugSum }元</td>
					</tr>
					<!-- 病历表单 -->
					<input type="hidden" name="caseId" value="${pch.caseId}"/>
					<input type="hidden" name="dispensaryRecord" value="${pch.dispensaryRecord}"/>
					<input type="hidden" name="allergy" value="${pch.allergy}"/>
					<input type="hidden" name="primaryCare" value="${pch.primaryCare}"/>
					<input type="hidden" name="reExamination" value="${pch.reExamination}"/>
					<input type="hidden" name="describes" value="${pch.describes}"/>
					<input type="hidden" name="examineStatus" value="${pch.examineStatus}"/>
					<input type="hidden" name="firstImpress" value="${pch.firstImpress}"/>
					<input type="hidden" name="meStId" value="${pch.meStId}"/>
				</table>
			</div>
			<div id="content">
				<table cellspacing="0" class="data_table">
					<thead>
					<tr>
						<th sortColumn="DrugID"><%=PrescriptionInfoDetail.ALIAS_DRUG_ID%></th>
						<th sortColumn="DrugName"><%=DrugBasicInfo.ALIAS_DRUG_NAME%></th>
						<th sortColumn="Amount"><%=PrescriptionInfoDetail.ALIAS_AMOUNT%></th>
						<th sortColumn="Times"><%=PrescriptionInfoDetail.ALIAS_TIMES%></th>
						<th sortColumn="Days"><%=PrescriptionInfoDetail.ALIAS_DAYS%></th>
						<th sortColumn="Note"><%=PrescriptionInfoDetail.ALIAS_NOTE%></th>
						<th sortColumn="DrugSum"><%=PrescriptionInfoDetail.ALIAS_DRUG_SUM%></th>
					</tr>
					</thead>
					<tbody>
					<!-- 处方明细-->
					<c:forEach items="${pch.prescriptionInfos}" var="item" varStatus="s">
						<c:forEach items="${item.prescriptionInfoDetails}" var="items" varStatus="status">
							<tr>
								<input type="hidden" name="prescriptionInfoDetails.makeNew[${status.index}].presId"  value="${presId}"/>
								<td><c:out value="${items.drugId}"/><input type="hidden" name="prescriptionInfoDetails.makeNew[${status.index}].drugId" value="${items.drugId}"/></td>
								<td><c:out value="${items.drugIdModel.drugName}"/></td>
								<td><c:out value="${items.amount}"/><input type="hidden" name="prescriptionInfoDetails.makeNew[${status.index}].amount" value="${items.amount}"/></td>
								<td><c:out value="${items.times}"/><input type="hidden" name="prescriptionInfoDetails.makeNew[${status.index}].times" value="${items.times}"/></td>
								<td><c:out value="${items.days}"/><input type="hidden" name="prescriptionInfoDetails.makeNew[${status.index}].days" value="${items.days}"/></td>
								<td><c:out value="${items.note}"/><input type="hidden" name="prescriptionInfoDetails.makeNew[${status.index}].note" value="${items.note}"/></td>
								<td><c:out value="${items.drugSum}"/><input type="hidden" name="prescriptionInfoDetails.makeNew[${status.index}].drugSum" value="${items.drugSum}"/></td>
							</tr>
						</c:forEach>
					</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="foot">
				<span>医生签名：</span>
				<span style="margin-left:100px;">开药人员签名：</span>
			</div>
		</div>
		<div class="div_but">
				<input type="submit" class="pure-button pure-button-primary" value="确定并打印" onClick="printdiv('box')"/> 
				<input type="reset" class="pure-button  pure-button-primary" value="取消" onclick="window.location.href='${ctx}/pages/Treatment/visit.jsp'" />
		</div>
	</form>
</body>
</html>
