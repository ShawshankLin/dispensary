<%@page import="com.dispensary.project.model.Student"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html;charset=UTF-8" pageEncoding="utf-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title><%=Student.TABLE_ALIAS %>编辑</title>
  <meta name="description" content="编辑学生页面">
  <meta name="keywords" content="form">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="icon" type="image/png" href="assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <link rel="stylesheet" href="${ctx}/assets/css/amazeui.css"/>
  <link rel="stylesheet" href="${ctx}/assets/css/admin.css">
  <script type="text/javascript" src="../../scripts/jquery.js"></script>
  <script type="text/javascript">
  $(document).ready(function(){
	  //选取系别
	  $.each($("#department option"),function(i,item){
		  if(item.value=='${stu.department}'){
			  $(item).attr("selected","selected");
		  }
	  });
	  //选取专业
	  var selectindex=$("#department").get(0).selectedIndex;
	  $(".major").hide();
	  var major=$(".major").eq(selectindex);
	  major.attr("name","major");
	  major.show();
	  $.each($(".major[name='major'] option"),function(i,item){
		  if(item.value=='${stu.major}'){
			  $(item).attr("selected","selected");
		  }
	  });
	  
	  
  });
  
  </script>
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
		    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg"><%=Student.TABLE_ALIAS%></strong> / <small>编辑</small></div>
		  </div>
		
		  <div class="am-tabs am-margin" data-am-tabs>
		    <ul class="am-tabs-nav am-nav am-nav-tabs">
		      <li><a href="#tab1">基本信息</a></li>
		    </ul>
		
		    <div class="am-tabs-bd">
		      <div class="am-tab-panel am-fade" id="tab1">
		        <form class="am-form" action="${ctx}/pages/Student/update.do" method="post">
		          <div class="am-g am-margin-top">
		            <div class="am-u-sm-4 am-u-md-2 am-text-right am-padding-right">
		              	<%=Student.ALIAS_STU_NUM%>
		            </div>
		            <div class="am-u-sm-8 am-u-md-4">
		              <input type="text" class="am-input-sm" name="stuNum" value="${stu.stuNum}" readonly>
		            </div>
		            <div class="am-hide-sm-only am-u-md-6 am-padding-left">*必填，不可重复</div>
		          </div>
		
		          <div class="am-g am-margin-top">
		            <div class="am-u-sm-4 am-u-md-2 am-text-right am-padding-right">
		            	<%=Student.ALIAS_STU_NAME%>
		            </div>
		            <div class="am-u-sm-8 am-u-md-4 am-u-end">
		              <input type="text" class="am-input-sm" name="stuName" value="${stu.stuName}">
		            </div>
		          </div>
				  <div class="am-g am-margin-top">
		            <div class="am-u-sm-4 am-u-md-2 am-text-right am-padding-right">
		            	<%=Student.ALIAS_SEX%>
		            </div>
		            <div class="am-u-sm-8 am-u-md-4 am-u-end">
		              <div class="am-btn-group" data-am-button>
			              
			              <c:choose>
			              	<c:when test="${stu.sex==1}">
				              	<label class="am-btn am-btn-default am-btn-xs am-active">
				                	<input type=radio name="sex" value="1" checked="checked"/> 男
				                </label>
			                	<label class="am-btn am-btn-default am-btn-xs">
			                		<input type="radio" name="sex" value="0"> 女
			               		</label>
			                </c:when>
			                <c:otherwise>
				               	<label class="am-btn am-btn-default am-btn-xs">
				                	<input type=radio name="sex" value="1"/> 男
				                </label>
				                <label class="am-btn am-btn-default am-btn-xs am-active">
					                	<input type="radio" name="sex" value="0" checked="checked"> 女
					             </label>
			                </c:otherwise>
			               </c:choose>
		          		</div>
		            </div>
		            <div class="am-hide-sm-only am-u-md-6 am-padding-left">选填</div>
		          </div>
				  
		          <div class="am-g am-margin-top">
		            <div class="am-u-sm-4 am-u-md-2 am-text-right am-padding-right">
		              	<%=Student.ALIAS_BIRTH_DATE%>
		            </div>
		            <div class="am-u-sm-8 am-u-md-4 am-form-icon">
			            <i class="am-icon-calendar"></i>
			            <input type="text" name="birthDate" value="${stu.birthDateString }" class="am-form-field" placeholder="请选择日期" data-am-datepicker readonly/>
		            </div>
		            <div class="am-hide-sm-only am-u-md-6 am-padding-left">选填</div>
		          </div>
		
		          <div class="am-g am-margin-top">
		            <div class="am-u-sm-4 am-u-md-2 am-text-right am-padding-right">
		              	<%=Student.ALIAS_IDCARD%>
		            </div>
		            <div class="am-u-sm-8 am-u-md-4">
		              <input type="text" class="am-input-sm" name="idcard" value="${stu.idcard }">
		            </div>
		            <div class="am-u-sm-12 am-u-md-6 am-padding-left"></div>
		          </div>
		
		          <div class="am-g am-margin-top-sm">
		            <div class="am-u-sm-12 am-u-md-2 am-text-right am-padding-right">
		              	<%=Student.ALIAS_TEL%>
		            </div>
		            <div class="am-u-sm-12 am-u-md-4">
		              <input type="text" name="tel" class="am-input-sm" value="${stu.tel }">
		            </div>
		            <div class="am-u-sm-12 am-u-md-6 am-padding-left"></div>
		          </div>
				  
				  <div class="am-g am-margin-top-sm">
		            <div class="am-u-sm-12 am-u-md-2 am-text-right am-padding-right">
		              	<%=Student.ALIAS_DEPARTMENT%>
		            </div>
		            <div class="am-u-sm-12 am-u-md-4">
		              <select name="department" id="department">
		              	<option disabled="disabled" selected="selected">----请选择系别---- </option>
		              	<option value="财经系">财经系</option>
		              	<option value="城市与环境科学系">城市与环境科学系</option>
		              	<option value="管理系">管理系</option>
		              	<option value="金融与贸易系">金融与贸易系</option>
		              	<option value="计算机与信息科学系">计算机与信息科学系</option>
		              	<option value="机电工程系">机电工程系</option>
		              	<option value="文学与传媒系">文学与传媒系</option>
		              	<option value="艺术系">艺术系</option>
		              	<option value="政法系">政法系</option>
		              </select>
		            </div>
		            <div class="am-u-sm-12 am-u-md-6 am-padding-left"></div>
		          </div>
		          <div class="am-g am-margin-top-sm">
		            <div class="am-u-sm-12 am-u-md-2 am-text-right am-padding-right">
		              	<%=Student.ALIAS_MAJOR%>
		            </div>
		            <div class="am-u-sm-12 am-u-md-4">
					   <select class="major"> 
					  	<option disabled="disabled" selected="selected">----请选择专业----<option> 
					  </select>
		              <select class="major">
		              	<option value="会计学">会计学</option>
		              	<option value="会计学（国际会计方向）">会计学（国际会计方向）</option>
		              	<option value="财务管理">财务管理</option>
		              </select>
		              <select class="major">
		              	<option value="土木工程">土木工程</option>
		              	<option value="安全工程">安全工程</option>
		              	<option value="环境工程">环境工程</option>
		              	<option value="人文地理与城乡规划">人文地理与城乡规划</option>
		              	<option value="自然地理与资源环境">自然地理与资源环境</option>
		              </select>
		              <select class="major">
		              	<option value="工商管理">工商管理</option>
		              	<option value="物流管理">物流管理</option>
		              	<option value="人力资源管理">人力资源管理</option>
		              </select>
		              <select class="major">
		              	<option value="国际经济与贸易">国际经济与贸易</option>
		              	<option value="保险">保险</option>
		              	<option value="金融学">金融学</option>
		              </select>
		              <select class="major">
		              	<option value="计算机科学与技术">计算机科学与技术</option>
		              	<option value="软件工程">软件工程</option>
		              	<option value="电子信息工程">电子信息工程</option>
		              	<option value="物联网工程">物联网工程</option>
		              	<option value="建筑电气与智能化">建筑电气与智能化</option>
		              </select>
		              <select class="major">
		              	<option value="机械设计制造及其自动化">机械设计制造及其自动化</option>
		              	<option value="印刷工程">印刷工程</option>
		              </select>
		               <select class="major">
		              	<option value="汉语言文学">汉语言文学</option>
		              	<option value="英语">英语</option>
		              	<option value="数字媒体艺术">数字媒体艺术</option>
		              </select>
		              <select class="major">
		              	<option value="音乐表演">音乐表演</option>
		              	<option value="影视表演">影视表演</option>
		              	<option value="舞蹈表演">舞蹈表演</option>
		              </select>
		              <select class="major">
		              	<option value="法学">法学</option>
		              	<option value="社会工作">社会工作</option>
		              	<option value="行政管理">行政管理</option>
		              </select>
		            </div>
		            <div class="am-u-sm-12 am-u-md-6 am-padding-left"></div>
		          </div>
		          <div class="am-g am-margin-top-sm">
		            <div class="am-u-sm-12 am-u-md-2 am-text-right am-padding-right">
		              	<%=Student.ALIAS_STU_CLASS%>
		            </div>
		            <div class="am-u-sm-12 am-u-md-4">
		              <input type="text" name="stuClass" class="am-input-sm" value="${stu.stuClass }">
		            </div>
		            <div class="am-u-sm-12 am-u-md-6 am-padding-left"></div>
		          </div>
		          <div class="am-g am-margin-top-sm">
		            <div class="am-u-sm-12 am-u-md-2 am-text-right am-padding-right">
		              	<%=Student.ALIAS_ADDRESS%>
		            </div>
		            <div class="am-u-sm-12 am-u-md-4">
		              <input type="text" name="address" class="am-input-sm" value="${stu.address }">
		            </div>
		            <div class="am-u-sm-12 am-u-md-6 am-padding-left"></div>
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
</body>
</html>

