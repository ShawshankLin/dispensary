<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>就诊</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link type="text/css" rel="stylesheet" href="../../styles/pure-min.css">
<link type="text/css" rel="stylesheet" href="../../styles/font-awesome-4.2.0/css/font-awesome.min.css" />
<link type="text/css" rel="stylesheet" href="../../styles/treatment/visit.css">
<script type="text/javascript" src="../../scripts/jquery.js"></script>
<script type="text/javascript" src="../../scripts/json2.js"></script>
<script type="text/javascript" src="../../scripts/common.js"></script>
<script type="text/javascript">
	//json日期转换
	function changeDateFormat(jsondate){
		var year=jsondate.year.toString();
		var month=jsondate.month;
		if(month<10){month="0"+month;}
		var date=jsondate.date;
		if(date<10){date="0"+date;}
		return "19"+year+"-"+month+"-"+date;
	}
	//查找学生信息
	function searchStu(value) {
		if($("#searchStuInfo").val()==""){
			alert("请输入查询学生信息！");
			return false;
		}else if(value!=null){
			$("#searchStuInfo").val(value);
		}
		//查询学生信息
		$.get("${ctx}/pages/Student/getStuInfos.do?stuNum="
				+ $("#searchStuInfo").val() + "&nocache="
				+ new Date().getTime(), function(data) {
			var stus = JSON.parse(data);
			if(stus!=""){
				$.each(stus,function(i,item){
					$("#stuNum").val(item.stuNum);
					now=$("#stuNum").val();
					console.log("当前查询学生是："+now);
					$("#sno").val(item.stuNum);
					$("#stuName").val(item.stuName);
					$("#sex").val(item.sex ? "男" : "女");
					$("#birthDate").val(changeDateFormat(item.birthDate));
					$("#idcard").val(item.idcard);
					$("#tel").val(item.tel);
					$("#department").val(item.department);
					$("#major").val(item.major);
					$("#stuClass").val(item.stuClass);
					$("#address").val(item.address);
					$("#searchStuInfo").val("");
				});
			}	
		});
		$("#searchStuBox").html("");
		$("#searchStuBox").css("display","none");
	}
	
	//检查添加新病例的必要项
	function checkEmpty() {
		if ($(".new_medical_record input").val() == "") {
			alert("请填写病历的必要信息！");
			return false;
		}
		return true;
	}
	//增加药品行
	function addTr(obj){
		var win=window.open('${ctx}/pages/Treatment/selectDrug.jsp','药品选择', 'width=659, height=530, top=150, left=500, toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no');
		win.focus();
		//toUrl("${ctx}/pages/Treatment/selectDrug.jsp");
	}
	//删除行
	function removeTr(obj){
		$(obj).parent().parent().remove();
	}

	//即时计算药品价钱
	function countPrice(obj){
		var sum =$(obj).parent().next().find("input:text");
		var price=$(obj).parent().prev();
		sum.val($(obj).val()*price.text());
	}
	//初始化
	$(document).ready(function() {
		//显示现在时间	
		document.getElementById('visitDate').defaultValue=getDate();
		//新旧病历切换
		var old_flat = true;
		var new_flat = true;
		var done=true;
		var now;
		$("#add_record").click(function() {
			if($("#stuNum").val()==""){
				alert("请先查询学生信息");
				return;
			}
			if (new_flat == true) {
				$(".new_medical_record").slideDown(300);
				new_flat = false;
			} else {
				$(".new_medical_record").slideUp(300);
				new_flat = true;
			}
		});
		
		$("#show_record").click(function() {
			if($("#stuNum").val()==""){
				alert("请先查询学生信息");
				return;
			}else{
				
				if(done){
					//查询学生以往病历
					$.get("${ctx}/pages/PatiCaseHistory/searchStuPaitCard.do?stuNum="+$("#stuNum").val()+"&nocache="+new Date().getTime(),function(data){
						var pchs = JSON.parse(data);
						if(pchs!=""){
							$.each(pchs,function(i,item) {
								$(".flip").last().after("<div class='old_medical_record' id='old"+i+"'><form action='' method='post' class='pure-form'><legend>"+ChangeDateFormat(item.visitDate)+"</legend><table width='100%' style='text-align:center;'><tr><td><label>既往史：</label></td><td><input type='text' value='"+item.dispensaryRecord+"' name='dispensaryRecord' readonly></td><td><label>过敏史：</label></td><td><input type='text' value='"+item.allergy+"' name='allergy' readonly></td><td><label>初诊：</label></td><td><input type='text' value='"+item.primaryCare+"' name='primaryCare' readonly></td></tr><tr><td><label>复诊：</label></td><td><input type='text' value='"+item.reExamination+"' name='reExamination' readonly></td><td><label>主诉：</label></td><td><input type='text' value='"+item.describes+"' name='describes' readonly></td><td><label>检查情况 ：</label></td><td><input type='text' value='"+item.examineStatus+"' name='examineStatus' readonly></td></tr><tr><td><label>初步诊断：</label></td><td><input type='text' value='"+item.firstImpress+"' name='firstImpress' readonly></td><td><label>主治医师：</label></td><td><input type='text' value='"+item.meStId+"' name='meStId' readonly></td></tr></table></form></div>");
							});
						}else{
							alert("无以往病历！");
						}
					});
					done=false;
				}
				if (old_flat == true) {
					$(".old_medical_record").slideDown(300);
					old_flat = false;
					//$(".new_medical_record").scrollIntoView(true);
				} else {
					$(".old_medical_record").slideUp(300);
					old_flat = true;
				}
			}
			
		});	
		//计算总价钱
		setInterval(function () {
			var td=$("#drug_table tr").find("td:last").children(":input");
			var sum=0;
			$.each(td,function(i,item){
				sum+=Number($(item).val());
			});
			$("#total_cost").val(sum.toString());
	    }, 1000);
	});
	//获取学生数据
    $(document).ready(function(){
    	$("#searchStuInfo").keyup(function(event){
        	//获取当前文本框的值
 	        var seachText=$("#searchStuInfo").val();
        	$.post("${ctx}/pages/Student/getStuInfos.do",{"stuNum":seachText,"stuName":seachText},function(data){
        		 var stulist=JSON.parse(data);
	       		 if(stulist!=""){
	   	        	//构造显示页面
	   	        	var panel="<select style='width:300px' size='15' ondblclick='return searchStu(this.value)'><option disabled='disabled'>学号&nbsp;&nbsp;姓名&nbsp;&nbsp;</option>";
	   	        	//遍历解析json
	   	        	$.each(stulist,function(i, item){
	     	        	//如果包含则为option赋值
	   	        		panel+="<option value='"+item.stuNum+"'>"+item.stuNum+"&nbsp;&nbsp;"+item.stuName+"</option>";
	   	         	});
	   	        	panel+="</select>";
	   	         	$("#searchStuBox").html(panel);
	   	         	$("#searchStuBox").slideDown("2000").css("display","block");
	   	         }else{
	   	        	 $("#searchStuBox").html("");
	   	        	 $("#searchStuBox").css("display","none");
	   	         }
        	});
        });
    });
		
</script>
</head>

<body>
	<div class="content">
		<!-- 查找学生信息 -->
		<div class="search">
			<form class="pure-form">
				<input type="text" class="pure-input-rounded" id="searchStuInfo"
					placeholder="请输入学号/姓名">
				<button type="button" class="pure-button" onclick="searchStu()" style="background-color:#FFD700"><i class="fa fa-search">&nbsp;搜索</i></button>
				<div id="searchStuBox" class="resultbox"></div>
				<br>
				<table width="100%" style="text-align:center">
					<tr>
						<td><label for="stuNum">学号：</label></td>
						<td><input type="text" name="stuNum" id="stuNum"
							readonly></td>
						<td><label for="stuName">姓名：</label></td>
						<td><input type="text" name="stuName" id="stuName"
							readonly></td>
						<td><label for="sex">性别：</label></td>
						<td><input type="text" name="sex" id="sex" readonly></td>
					</tr>
					<tr>
						<td><label for="birthDate">出生日期：</label></td>
						<td><input type="date" name="birthDate" id="birthDate" readonly></td>
						<td><label for="idcard">身份证：</label></td>
						<td><input type="text" name="idcard" id="idcard"
							readonly></td>
						<td><label for="tel">学生电话：</label></td>
						<td><input type="text" name="tel" id="tel" readonly></td>
					</tr>
					<tr>
						<td><label for="department">系别：</label></td>
						<td><input type="text"  name="department"
							id="department" readonly></td>
						<td><label for="major">专业：</label></td>
						<td><input type="text"  name="major" id="major"
							readonly></td>
						<td><label for="address">学校住址：</label></td>
						<td><input type="text" name="address" id="address"
							readonly></td>
					</tr>
				</table>
			</form>
		</div>
		<span class="flip">
			<button type="button" class="pure-button blue" id="add_record">
				添加病例 <i class="fa fa-angle-double-down"></i>
			</button>
			<button type="button" class="pure-button green" id="show_record">
				查看病例 <i class="fa fa-angle-double-down"></i>
			</button>
		</span>
		<!-- 添加新病例 -->
		<div class="new_medical_record">
			<form action="${ctx}/pages/PatiCaseHistory/toConfirmView.do" method="post" class="pure-form">
				<legend>
					<a id="new">添加新病历</a>
				</legend>
				<table width="100%" style="text-align:center;">
					<tr>
						<td><label for="visitDate">当前时间：</label></td>
						<td><input type="date" name="visitDate" id="visitDate"  readonly/></td>
						<!--<td><input type="text" name="case_ID" id="caseID" style="display:none" /></td>-->
						<td><input type="hidden" name="stuNum"  id="sno"/></td>
					</tr>
					<tr>
						<td><label for="dispensaryRecord">既往史：</label></td>
						<td><input type="text" name="dispensaryRecord" id="dispensaryRecord" size="25"></td>
						<td><label for="allergy">过敏史：</label></td>
						<td><input type="text" name="allergy" id="allergy" size="25"></td>
						<td><label for="primaryCare">初诊：</label></td>
						<td><input type="text" name="primaryCare" id="primaryCare"></td>
					</tr>
					<tr>
						<td><label for="describes">主诉：</label></td>
						<td colspan="3"><input type="text" name="describes" id="describes" size="78"></td>
						<td><label for="re_examination">复诊：</label></td>
						<td><input type="text" name="re_examination" id="re_examination"></td>
					</tr>
					<tr>
						<td><label for="examineStatus">检查情况 ：</label></td>
						<td colspan="5"><input type="text" name="examineStatus" id="examineStatus" size="115"></td>
					</tr>
					<tr>
						<td><label for="firstImpress">初步诊断：</label></td>
						<td colspan="5"><input type="text" name="firstImpress"  id="firstImpress" size="115"></td>
					</tr>
					<tr>
						<td colspan="6">
							<!-- 处方表 -->
							<div class="pure-u-24-24">
								<div style="overflow:auto;height:500px;border-bottom:1px solid #d3d3d3;">
								<table class="data_table" cellspacing="0" id="drug_table">
									<thead>
										<tr align="center">
											<th>
												<a class="pure-button green" onclick="addTr(this)"><i class="fa fa-plus-square-o"></i></a>
											</th>
											<th>药品编号</th>
											<th>药品名称</th>									
											<th>次数</th>
											<th>天数</th>
											<th>备注</th>
											<th>单价/元</th>
											<th>数量</th>
											<th>金额</th>
										</tr>
									</thead>
									<tbody>						
									</tbody>
								</table>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td><label for="drugSum">药品总售价：</label></td>
						<input type="hidden" name="prescriptionInfos.makeNew[0].presId" value="0"/>
						<td><input type="text"  name="prescriptionInfos.makeNew[0].drugSum" id="total_cost" value="0" readonly>元</td>
						<!-- <td><label for="selfPay">实收费用：</label></td>
						<td><input type="text"  name="precriptIdModel.selfPay" id="selfPay">元</td> -->
						<td><label for="meStId">主治医师：</label></td>
						<td><input type="text"  name="meStId" id="meStId" readonly value="${sessionScope.loginUser.meStId}"/></td>
						<td></td>
						<td>
							<input type="submit" class="pure-button pure-button-primary" value="提交"/>
							<input type="reset"  class="pure-button  pure-button-primary" value="重置"/>
						</td>
					</tr>
				</table>
			</form>
		</div>

	</div>
</body>
</html>

