<%@page import="com.dispensary.project.model.DrugBasicInfo"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html;charset=UTF-8" pageEncoding="utf-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!doctype html>
<html class="no-js">
<head>
	<meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title><%=DrugBasicInfo.TABLE_ALIAS %></title>
  <meta name="description" content="添加药品页面">
  <meta name="keywords" content="form">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="icon" type="image/png" href="assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <link rel="stylesheet" href="${ctx}/assets/css/amazeui.css"/>
  <link rel="stylesheet" href="${ctx}/assets/css/admin.css">
  <link rel="stylesheet" href="${ctx}/assets/css/amazeui.chosen.css"/>
  <script type="text/javascript" src="../../scripts/common.js"></script>
  <script type="text/javascript" src="../../scripts/jquery.js"></script>
  <script type="text/javascript" src="../../scripts/json2.js"></script>
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
		    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg"><%=DrugBasicInfo.TABLE_ALIAS%></strong> / <small>添加</small></div>
		  </div>
		
		  <div class="am-tabs am-margin" data-am-tabs="{noSwipe: 1}">
		    <ul class="am-tabs-nav am-nav am-nav-tabs">
		      <li><a href="#tab1">基本信息</a></li>
		    </ul>
		
		    <div class="am-tabs-bd">
		      <div class="am-tab-panel am-fade" id="tab1">
		        <form class="am-form" action="${ctx}/pages/DrugBasicInfo/save.do" method="post">
		
		          <div class="am-g am-margin-top">
		            <div class="am-u-sm-4 am-u-md-2 am-text-right am-padding-right">
		            	<%=DrugBasicInfo.ALIAS_DRUG_NAME%>
		            </div>
		            <div class="am-u-sm-8 am-u-md-4 am-u-end">
		              <input type="text" class="am-input-sm" name="drugName" id="drugName" required="required">
		            </div>
		          </div>
		          
		          <div class="am-g am-margin-top">
		            <div class="am-u-sm-4 am-u-md-2 am-text-right am-padding-right">
		              	<%=DrugBasicInfo.ALIAS_DRUG_PINGYIN%>
		            </div>
		            <div class="am-u-sm-8 am-u-md-4">
		              <input type="text" class="am-input-sm" name="drugPingyin" id="drugPingyin" required="required"/>
		            </div>
		            <div class="am-hide-sm-only am-u-md-6 am-padding-left">多音字请自行更正</div>
		          </div>
				  
		          <div class="am-g am-margin-top">
		            <div class="am-u-sm-4 am-u-md-2 am-text-right am-padding-right">
		              	<%=DrugBasicInfo.ALIAS_DRUG_EFFECT%>
		            </div>
		            <div class="am-u-sm-8 am-u-md-4">
		              <select name="drugEffect">
		              <s:iterator value="#attr.drugunitList" var="item">
		              	<option value='<s:property value="drugUnitId"/>'><s:property value="drugUnitName"/></option>
		              </s:iterator>
		             </select>
		            </div>
		            <div class="am-hide-sm-only am-u-md-6 am-padding-left"></div>
		          </div>
		
		          <div class="am-g am-margin-top">
		            <div class="am-u-sm-4 am-u-md-2 am-text-right am-padding-right">
		              	<%=DrugBasicInfo.ALIAS_DRUG_KICK_BACK%>
		            </div>
		            <div class="am-u-sm-8 am-u-md-4">
		              <select name="drugKickBack">
		              <s:iterator value="#attr.drugunitList" var="item">
		              	<option value='<s:property value="drugUnitId"/>'><s:property value="drugUnitName"/></option>
		              </s:iterator>
		             </select>
		            </div>
		            <div class="am-u-sm-12 am-u-md-6 am-padding-left"></div>
		          </div>
		          
		          <div class="am-g am-margin-top-sm">
		            <div class="am-u-sm-12 am-u-md-2 am-text-right am-padding-right">
		              	<%=DrugBasicInfo.ALIAS_QUANTITY_UNIT%>
		            </div>
		            <div class="am-u-sm-12 am-u-md-4">
		              <select name="quantityUnit">
		              <s:iterator value="#attr.drugunitList" var="item">
		              	<option value='<s:property value="drugUnitId"/>'><s:property value="drugUnitName"/></option>
		              </s:iterator>
		             </select>
		            </div>
		            <div class="am-u-sm-12 am-u-md-6 am-padding-left"></div>
		          </div>
					<!--  
		          <div class="am-g am-margin-top-sm">
		            <div class="am-u-sm-12 am-u-md-2 am-text-right am-padding-right">
		              	<%=DrugBasicInfo.ALIAS_DRUG_NOTE%>
		            </div>
		            <div class="am-u-sm-12 am-u-md-4">
		              <input type="text" class="am-input-sm" name="drugNote">
		            </div>
		            <div class="am-u-sm-12 am-u-md-6 am-padding-left"></div>
		          </div>
				  -->
				  
		          <div class="am-g am-margin-top-sm">
		            <div class="am-u-sm-12 am-u-md-2 am-text-right am-padding-right">
		              	<%=DrugBasicInfo.ALIAS_DRUG_USAGE%>
		            </div>
		            <div class="am-u-sm-12 am-u-md-4">
		              <input type="text" class="am-input-sm" name="drugUsage">
		            </div>
		            <div class="am-u-sm-12 am-u-md-6 am-padding-left"></div>
		          </div>
		          <div class="am-g am-margin-top-sm">
		            <div class="am-u-sm-12 am-u-md-2 am-text-right am-padding-right">
		              	<%=DrugBasicInfo.ALIAS_DRUG_SPEC%>
		            </div>
		            <div class="am-u-sm-12 am-u-md-4">
		              <input type="text" class="am-input-sm" name="drugSpec">
		            </div>
		            <div class="am-u-sm-12 am-u-md-6 am-padding-left"></div>
		          </div>
		          <!--  
		          <div class="am-g am-margin-top-sm">
		            <div class="am-u-sm-12 am-u-md-2 am-text-right am-padding-right">
		              	<%=DrugBasicInfo.ALIAS_DRUG_TABU%>
		            </div>
		            <div class="am-u-sm-12 am-u-md-4">
		              <input type="text" class="am-input-sm" name="drugTabu">
		            </div>
		            <div class="am-u-sm-12 am-u-md-6 am-padding-left"></div>
		          </div>
		          -->
		          <div class="am-g am-margin-top-sm">
		            <div class="am-u-sm-12 am-u-md-2 am-text-right am-padding-right">
		              	<%=DrugBasicInfo.ALIAS_COST_PRICE%>
		            </div>
		            <div class="am-u-sm-12 am-u-md-4">
		              <input type="text" class="am-input-sm" name="costPrice">
		            </div>
		            <div class="am-u-sm-12 am-u-md-6 am-padding-left"></div>
		          </div>
		          
		          <div class="am-g am-margin-top-sm">
		            <div class="am-u-sm-12 am-u-md-2 am-text-right am-padding-right">
		              	<%=DrugBasicInfo.ALIAS_RETAIL_PRICE%>
		            </div>
		            <div class="am-u-sm-12 am-u-md-4">
		              <input type="text" class="am-input-sm" name="retailPrice">
		            </div>
		            <div class="am-u-sm-12 am-u-md-6 am-padding-left"></div>
		          </div>
		          
		          <div class="am-g am-margin-top-sm">
		            <div class="am-u-sm-12 am-u-md-2 am-text-right am-padding-right">
		              	<%=DrugBasicInfo.ALIAS_PRODUCTION_DATE%>
		            </div>
		            <div class="am-u-sm-12 am-u-md-4">
		              <input type="text" class="am-form-field" name="productionDate" placeholder="请选择日期" data-am-datepicker readonly/>
		            </div>
		            <div class="am-u-sm-12 am-u-md-6 am-padding-left"></div>
		          </div>
		          
		          <div class="am-g am-margin-top-sm">
		            <div class="am-u-sm-12 am-u-md-2 am-text-right am-padding-right">
		              	<%=DrugBasicInfo.ALIAS_PERIOD_OF_VALIDITY%>
		            </div>
		            <div class="am-u-sm-12 am-u-md-4">
		              <input type="text" class="am-input-sm" name="periodOfValidity"> 
		            </div>
		            <div class="am-u-sm-12 am-u-md-6 am-padding-left"></div>
		          </div>
		          <!--
		          <div class="am-g am-margin-top-sm">
		            <div class="am-u-sm-12 am-u-md-2 am-text-right am-padding-right">
		              	<%=DrugBasicInfo.ALIAS_UP_LIMIT%>
		            </div>
		            <div class="am-u-sm-12 am-u-md-4">
		              <input type="text" class="am-input-sm" name="upLimit">
		            </div>
		            <div class="am-u-sm-12 am-u-md-6 am-padding-left"></div>
		          </div>
		          
		          <div class="am-g am-margin-top-sm">
		            <div class="am-u-sm-12 am-u-md-2 am-text-right am-padding-right">
		              	<%=DrugBasicInfo.ALIAS_DOWN_LIMIT%>
		            </div>
		            <div class="am-u-sm-12 am-u-md-4">
		              <input type="text" class="am-input-sm" name="downLimit">
		            </div>
		            <div class="am-u-sm-12 am-u-md-6 am-padding-left"></div>
		          </div>
		          
		          <div class="am-g am-margin-top-sm">
		            <div class="am-u-sm-12 am-u-md-2 am-text-right am-padding-right">
		              	<%=DrugBasicInfo.ALIAS_UP_LIMIT1%>
		            </div>
		            <div class="am-u-sm-12 am-u-md-4">
		              <input type="text" class="am-input-sm" name="upLimit1">
		            </div>
		            <div class="am-u-sm-12 am-u-md-6 am-padding-left"></div>
		          </div>
		          
		          <div class="am-g am-margin-top-sm">
		            <div class="am-u-sm-12 am-u-md-2 am-text-right am-padding-right">
		              	<%=DrugBasicInfo.ALIAS_DOWN_LIMIT1%>
		            </div>
		            <div class="am-u-sm-12 am-u-md-4">
		              <input type="text" class="am-input-sm" name="downLimit1">
		            </div>
		            <div class="am-u-sm-12 am-u-md-6 am-padding-left"></div>
		          </div>
		          -->
		          <div class="am-g am-margin-top-sm">
		            <div class="am-u-sm-12 am-u-md-2 am-text-right am-padding-right">
		              	<%=DrugBasicInfo.ALIAS_IS_DRUG%>
		            </div>
		            <div class="am-u-sm-12 am-u-md-4">
		              <div class="am-btn-group" data-am-button>
			              <label class="am-btn am-btn-default am-btn-xs am-active">
			                <input type=radio name="isDrug" value="1" checked="checked"> 是
			              </label>
			              <label class="am-btn am-btn-default am-btn-xs">
			                <input type="radio" name="isDrug" value="0"> 否
			              </label>
		          	 </div>
		            </div>
		            <div class="am-u-sm-12 am-u-md-6 am-padding-left"></div>
		          </div>
		          
		          <div class="am-g am-margin-top-sm">
		            <div class="am-u-sm-12 am-u-md-2 am-text-right am-padding-right">
		              	<%=DrugBasicInfo.ALIAS_SYMPTOM_ID%>
		            </div>
		            <div class="am-u-sm-12 am-u-md-4">
		            <select name="symptoms" class="chosen-select" data-placeholder="请选择症状" style="width:350px;" multiple>
		              <s:iterator value="#attr.symptomList" var="item">
		              	<option value='<s:property value="symptomId"/>'><s:property value="symptomName"/></option>
		              </s:iterator>
		             </select>
		            </div>
		            <div class="am-u-sm-12 am-u-md-6 am-padding-left">可多选</div>
		          </div>
		          
		          <div class="am-g am-margin-top-sm">
		            <div class="am-u-sm-12 am-u-md-2 am-text-right am-padding-right">
		              	<%=DrugBasicInfo.ALIAS_SUPPLIER_ID%>
		            </div>
		            <div class="am-u-sm-12 am-u-md-4">
		            <select name="supplierId" class="chosen-select" data-placeholder="请选择供应商" style="width:350px;">
		              <s:iterator value="#attr.supplierList" var="item">
		              	<option value='<s:property value="supplierId"/>'><s:property value="supplierName"/></option>
		              </s:iterator>
		             </select>
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
<script src="${ctx}/assets/js/amazeui.chosen.min.js"></script>
<!--<![endif]-->
<script src="${ctx}/assets/js/app.js"></script>
<script>
  $(function() {
    var nowTemp = new Date();
    var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);
    var $birthDate = $('#birthDate');

    var checkin = $birthDate.datepicker({
      onRender: function(date) {
        return date.valueOf() > now.valueOf() ? 'am-disabled' : '';
      }
    }).on('changeDate.datepicker.amui', function(ev) {
        if (ev.date.valueOf() > checkout.date.valueOf()) {
          var newDate = new Date(ev.date)
          newDate.setDate(newDate.getDate() + 1);
          checkout.setValue(newDate);
        }
        checkin.close();
        $('#birthDate')[0].focus();
    }).data('amui.datepicker');
	//配置药品拼音
    $("#drugName").blur(function(){
		$("#drugPingyin").val(makePy($("#drugName").val()).join());
	});
	//激活select
    $(".chosen-select").chosen();
  })
</script>
</body>
</html>
