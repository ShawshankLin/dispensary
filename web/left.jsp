<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'left.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet" href="styles/index/left.css" type="text/css" />
<script src="scripts/jquery.js" type="text/javascript"></script>
<script type="text/javascript">
	//等待dom元素加载完毕.
	$(document).ready(function() {
			$(".level1 > a").click(function() {
				console.log("asfasf");
				$(this).addClass("current").next().show().parent()
					.siblings().children("a").removeClass("current").next().hide();
							return false;
			});
			
	});
</script>

</head>

<body bgcolor="#eeeeee">
	<div class="mulu">
		<img src="images/menu.png" />
	</div>
	<div class="box">
		<ul class="menu">
			<li class="level1" ><a target="main" href="#"><img src="images/person.png" style="vertical-align:middle;width: 24;height: 21"/>   个人资料</a>
				<ul class="level2">
					<li><a target="main" href="${ctx}/pages/Dispensarystaff/edit.do?meStId=${loginUser.meStId}">查看个人信息</a></li>
					<li><a target="main" href="${ctx}/pages/Userinfo/password.jsp">修改密码</a></li>
				</ul>
			</li>
			<li class="level1" ><a target="main" href="#"><img src="images/zd.png" style="vertical-align:middle;width: 24;height: 21"/>   诊断</a>
				<ul class="level2">
					<li><a target="main" href="${ctx}/pages/Treatment/visit.jsp">处方</a></li>
				</ul></li>
			<li class="level1"><a target="main" href="#none"><img src="images/sj2.png" style="vertical-align:middle;width: 24;height: 21"/>   基本数据管理</a>
				<ul class="level2">
					<li><a target="main" href="${ctx}/pages/Student/list.do">学生信息管理</a></li>
					<li><a target="main" href="${ctx}/pages/DrugBasicInfo/list.do">药品信息管理</a></li>
					<li><a target="main" href="${ctx}/pages/Dispensarystaff/list.do">医务人员管理</a></li>
					<li><a target="main" href="${ctx}/pages/Supplier/list.do">供应商管理</a></li>
					<li><a target="main" href="${ctx}/pages/Drugunit/list.do">药品单位管理</a></li>
					<li><a target="main" href="${ctx}/pages/DrugPlace/list.do">药品存放位置管理</a></li>
					<li><a target="main" href="${ctx}/pages/Symptom/list.do">病症管理</a></li>
				</ul></li>
			<li class="level1"><a target="main" href="#none"><img src="images/yf.png" style="vertical-align:middle;width: 24;height: 21"/>   药房管理</a>
				<ul class="level2">
					<li><a target="main" href="${ctx}/pages/PrescriptionInfo/list.do"">处方划价</a></li>
					<li><a target="main" href="${ctx}/pages/Receipt/list.do">收益管理</a></li>
				</ul></li>
			<li class="level1"><a target="main" href="#none"><img src="images/yk.png" style="vertical-align:middle;width: 24;height: 21"/>   药库管理</a>
				<ul class="level2">
					<li>
						<a target="main" href="${ctx}/pages/DrugStockIn/list.do">入库管理</a>
					</li>
					<li>
						<a target="main" href="${ctx}/pages/DrugStockOut/list.do">出库管理</a>
					</li>
					<!--  <li><a target="main" href="${ctx}/pages/DrugBasicInfo/showDrugWarn.do">药品有效期管理</a></li>-->
					<li><a target="main" href="${ctx}/pages/DrugStockInfo/list.do">库存盘点</a></li>
				</ul></li>
			<li class="level1"><a target="main" href="#none"><img src="images/sz.png" style="vertical-align:middle;width: 24;height: 21"/>   系统管理</a>
				<ul class="level2">
					<li><a target="main" href="${ctx}/pages/Power/list.do">权限管理</a></li>
					<li><a target="main" href="${ctx}/pages/Role/list.do">角色管理</a></li>
					<li><a target="main" href="${ctx}/pages/Userinfo/list.do">用户管理</a></li>
					<li><a target="main" href="${ctx}/pages/Usertype/list.do">用户类型管理</a></li>
				</ul>
			</li>
			<li class="level1"><a target="main" href="#none"><img src="images/tj.png" style="vertical-align:middle;width: 24;height: 21"/>   统计分析</a>
				<ul class="level2">
					<li><a target="main" href="${ctx}/StatisticalTool">学生就诊统计</a></li>
				</ul></li>
			<li class="level1"><a target="main" href="#none"><img src="images/bz.png" style="vertical-align:middle;width: 24;height: 21"/>   帮助</a>
				<ul class="level2">
					<li><a target="main" href="#none">A</a></li>
					<li><a target="main" href="#none">B</a></li>
				</ul></li>
		</ul>
	</div>
</body>
</html>
