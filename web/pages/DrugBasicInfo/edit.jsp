<%@page import="com.dispensary.project.model.DrugBasicInfo"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html;charset=UTF-8" pageEncoding="utf-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!doctype html>
<html class="no-js">
<head>
	<meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>编辑药品页面</title>
  <meta name="description" content="编辑药品页面">
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
		    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg"><%=DrugBasicInfo.TABLE_ALIAS%></strong> / <small>编辑</small></div>
		  </div>
		
		  <div class="am-tabs am-margin" data-am-tabs="{noSwipe: 1}">
		    <ul class="am-tabs-nav am-nav am-nav-tabs">
		      <li><a href="#tab1">基本信息</a></li>
		    </ul>
		
		    <div class="am-tabs-bd">
		      <div class="am-tab-panel am-fade" id="tab1">
		        <form class="am-form" action="${ctx}/pages/DrugBasicInfo/update.do" method="post">
				  <div class="am-g am-margin-top">
		            <div class="am-u-sm-4 am-u-md-2 am-text-right am-padding-right">
		            	<%=DrugBasicInfo.ALIAS_DRUG_ID%>
		            </div>
		            <div class="am-u-sm-8 am-u-md-4 am-u-end">
		              <input type="text" class="am-input-sm" name="drugId" value="${drug.drugId }" readonly>
		            </div>
		          </div>
		          
		          <div class="am-g am-margin-top">
		            <div class="am-u-sm-4 am-u-md-2 am-text-right am-padding-right">
		            	<%=DrugBasicInfo.ALIAS_DRUG_NAME%>
		            </div>
		            <div class="am-u-sm-8 am-u-md-4 am-u-end">
		              <input type="text" class="am-input-sm" name="drugName" id="drugName"  value="${drug.drugName }">
		            </div>
		          </div>
		          
		          <div class="am-g am-margin-top">
		            <div class="am-u-sm-4 am-u-md-2 am-text-right am-padding-right">
		            	<%=DrugBasicInfo.ALIAS_DRUG_PINGYIN%>
		            </div>
		            <div class="am-u-sm-8 am-u-md-4 am-u-end">
		              <input type="text" class="am-input-sm" name="drugPingyin" id="drugPingyin" value="${drug.drugPingyin }">
		            </div>
		          </div>
				  
		          <div class="am-g am-margin-top">
		            <div class="am-u-sm-4 am-u-md-2 am-text-right am-padding-right">
		              	<%=DrugBasicInfo.ALIAS_DRUG_EFFECT%>
		            </div>
		            <div class="am-u-sm-8 am-u-md-4">
		              <select name="drugEffect">
			              <s:iterator value="#attr.drugunitList" var="item">
			              	<s:if test="#attr.drug.drugEffect==#item.drugUnitId">
			              		<option value='<s:property value="drugUnitId"/>' selected="selected"><s:property value="drugUnitName"/></option>
			              	</s:if>
			              	<s:else>
			              		<option value='<s:property value="drugUnitId"/>'><s:property value="drugUnitName"/></option>
			              	</s:else>
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
		              	<s:if test="#attr.drug.drugKickBack==#item.drugUnitId">
		              		<option value='<s:property value="drugUnitId"/>' selected="selected"><s:property value="drugUnitName"/></option>
		              	</s:if>
		              	<s:else>
		              		<option value='<s:property value="drugUnitId"/>'><s:property value="drugUnitName"/></option>
		              	</s:else>
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
		              	<s:if test="#attr.drug.quantityUnit==#item.drugUnitId">
		              		<option value='<s:property value="drugUnitId"/>' selected="selected"><s:property value="drugUnitName"/></option>
		              	</s:if>
		              	<s:else>
		              		<option value='<s:property value="drugUnitId"/>'><s:property value="drugUnitName"/></option>
		              	</s:else>
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
		              <input type="text" class="am-input-sm" name="drugNote" value="${drug.drugNote}">
		            </div>
		            <div class="am-u-sm-12 am-u-md-6 am-padding-left"></div>
		          </div>
				  -->
		          <div class="am-g am-margin-top-sm">
		            <div class="am-u-sm-12 am-u-md-2 am-text-right am-padding-right">
		              	<%=DrugBasicInfo.ALIAS_DRUG_USAGE%>
		            </div>
		            <div class="am-u-sm-12 am-u-md-4">
		              <input type="text" class="am-input-sm" name="drugUsage" value="${drug.drugUsage}">
		            </div>
		            <div class="am-u-sm-12 am-u-md-6 am-padding-left"></div>
		          </div>
		          
		          <div class="am-g am-margin-top-sm">
		            <div class="am-u-sm-12 am-u-md-2 am-text-right am-padding-right">
		              	<%=DrugBasicInfo.ALIAS_DRUG_SPEC%>
		            </div>
		            <div class="am-u-sm-12 am-u-md-4">
		              <input type="text" class="am-input-sm" name="drugSpec" value="${drug.drugSpec }">
		            </div>
		            <div class="am-u-sm-12 am-u-md-6 am-padding-left"></div>
		          </div>
		          
		          <%-- <div class="am-g am-margin-top-sm">
		            <div class="am-u-sm-12 am-u-md-2 am-text-right am-padding-right">
		              	<%=DrugBasicInfo.ALIAS_DRUG_TABU%>
		            </div>
		            <div class="am-u-sm-12 am-u-md-4">
		              <input type="text" class="am-input-sm" name="drugTabu" value="${drug.drugTabu}">
		            </div>
		            <div class="am-u-sm-12 am-u-md-6 am-padding-left"></div>
		          </div> --%>
		          
		          <div class="am-g am-margin-top-sm">
		            <div class="am-u-sm-12 am-u-md-2 am-text-right am-padding-right">
		              	<%=DrugBasicInfo.ALIAS_COST_PRICE%>
		            </div>
		            <div class="am-u-sm-12 am-u-md-4">
		              <input type="text" class="am-input-sm" name="costPrice" value="${drug.costPrice}">
		            </div>
		            <div class="am-u-sm-12 am-u-md-6 am-padding-left">元</div>
		          </div>
		          
		          <div class="am-g am-margin-top-sm">
		            <div class="am-u-sm-12 am-u-md-2 am-text-right am-padding-right">
		              	<%=DrugBasicInfo.ALIAS_RETAIL_PRICE%>
		            </div>
		            <div class="am-u-sm-12 am-u-md-4">
		              <input type="text" class="am-input-sm" name="retailPrice" value="${drug.retailPrice}">
		            </div>
		            <div class="am-u-sm-12 am-u-md-6 am-padding-left">元</div>
		          </div>
		          
		          <div class="am-g am-margin-top-sm">
		            <div class="am-u-sm-12 am-u-md-2 am-text-right am-padding-right">
		              	<%=DrugBasicInfo.ALIAS_PRODUCTION_DATE%>
		            </div>
		            <div class="am-u-sm-12 am-u-md-4 am-form-icon">
		            	<i class="am-icon-calendar"></i>
		              	<input type="text" class="am-form-field" name="productionDate" value="${drug.productionDateString}" placeholder="请选择日期" data-am-datepicker readonly/>
		            </div>
		            <div class="am-u-sm-12 am-u-md-6 am-padding-left"></div>
		          </div>
		          
		          <div class="am-g am-margin-top-sm">
		            <div class="am-u-sm-12 am-u-md-2 am-text-right am-padding-right">
		              	<%=DrugBasicInfo.ALIAS_PERIOD_OF_VALIDITY%>
		            </div>
		            <div class="am-u-sm-12 am-u-md-4">
		              <input type="text" class="am-input-sm" name="periodOfValidity"  value="${drug.periodOfValidity}">
		            </div>
		            <div class="am-u-sm-12 am-u-md-6 am-padding-left">年</div>
		          </div>
		          <!--  
		          <div class="am-g am-margin-top-sm">
		            <div class="am-u-sm-12 am-u-md-2 am-text-right am-padding-right">
		              	<%=DrugBasicInfo.ALIAS_UP_LIMIT%>
		            </div>
		            <div class="am-u-sm-12 am-u-md-4">
		              <input type="text" class="am-input-sm" name="upLimit" value="${drug.upLimit }">
		            </div>
		            <div class="am-u-sm-12 am-u-md-6 am-padding-left"></div>
		          </div>
		          
		          <div class="am-g am-margin-top-sm">
		            <div class="am-u-sm-12 am-u-md-2 am-text-right am-padding-right">
		              	<%=DrugBasicInfo.ALIAS_DOWN_LIMIT%>
		            </div>
		            <div class="am-u-sm-12 am-u-md-4">
		              <input type="text" class="am-input-sm" name="downLimit" value="${drug.downLimit}">
		            </div>
		            <div class="am-u-sm-12 am-u-md-6 am-padding-left"></div>
		          </div>
		          
		          <div class="am-g am-margin-top-sm">
		            <div class="am-u-sm-12 am-u-md-2 am-text-right am-padding-right">
		              	<%=DrugBasicInfo.ALIAS_DOWN_LIMIT%>
		            </div>
		            <div class="am-u-sm-12 am-u-md-4">
		              <input type="text" class="am-input-sm" name="upLimit1" value="${drug.upLimit1}">
		            </div>
		            <div class="am-u-sm-12 am-u-md-6 am-padding-left"></div>
		          </div>
		          
		          <div class="am-g am-margin-top-sm">
		            <div class="am-u-sm-12 am-u-md-2 am-text-right am-padding-right">
		              	<%=DrugBasicInfo.ALIAS_UP_LIMIT1%>
		            </div>
		            <div class="am-u-sm-12 am-u-md-4">
		              <input type="text" class="am-input-sm" name="downLimit1" value="${drug.downLimit1}">
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
		              	<s:if test="#attr.drug.isDrug==1">
			              <label class="am-btn am-btn-default am-btn-xs am-active">
			                <input type=radio name="isDrug" value="1" checked="checked"> 是
			              </label>
			              <label class="am-btn am-btn-default am-btn-xs">
			                <input type="radio" name="isDrug" value="0"> 否
			              </label>
			           </s:if>
			           <s:else>
			           		<label class="am-btn am-btn-default am-btn-xs am-active">
			                <input type=radio name="isDrug" value="1" > 是
			              </label>
			              <label class="am-btn am-btn-default am-btn-xs">
			                <input type="radio" name="isDrug" value="0" checked="checked"> 否
			              </label>
			           </s:else>
		          	 </div>
		            </div>
		            <div class="am-u-sm-12 am-u-md-6 am-padding-left"></div>
		          </div>
		          
		          <div class="am-g am-margin-top-sm">
		            <div class="am-u-sm-12 am-u-md-2 am-text-right am-padding-right">
		              	适用症状
		            </div>
		            <div class="am-u-sm-12 am-u-md-4">
		            <select name="symptoms" id="symptoms" style="width:650px;height:10px" multiple  class="chosen-select-no-results" tabindex="1">
		            	<s:iterator value="#attr.symptomList" var="item">
		            		<option value="<s:property value="symptomId"/>"><s:property value="symptomName"/></option>
		            	</s:iterator>
		             </select>
		            </div>
		            <div class="am-u-sm-12 am-u-md-6 am-padding-left"></div>
		          </div>
		          
		          <div class="am-g am-margin-top-sm">
		            <div class="am-u-sm-12 am-u-md-2 am-text-right am-padding-right">
		              	<%=DrugBasicInfo.ALIAS_SUPPLIER_ID%>
		            </div>
		            <div class="am-u-sm-12 am-u-md-4">
		            <select name="supplierId">
		              <s:iterator value="#attr.supplierList" var="item">
		              	<s:if test="#attr.drug.supplierIdModel.modelTagValue!=null">
		              		<option value='<s:property value="supplierId"/>' selected="selected"><s:property value="supplierName"/></option>
		              	</s:if>
		              	<s:else>
		              		<option value='<s:property value="supplierId"/>'><s:property value="supplierName"/></option>
		              	</s:else>
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
    $("#symptoms").chosen();
  	//获取药品症状
    $.get("${ctx}/pages/DrugSymptomRelation/getDrugSymptomJSON.do?drugId="+ ${drug.drugId }+ "&nocache="+ new Date().getTime(),function(data){
		var hasSymptoms=JSON.parse(data);
		var arr=new Array();
		var symptoms=$("#symptoms option");
		$.each(hasSymptoms,function(i,item1){
			$.each(symptoms,function(j,item2){
				if(item2.value==item1.symptomId){
					$(item2).attr("selected","selected");
					$("#symptoms").trigger('chosen:updated');
					return true;
				}
			});
		});
	});
  })
</script>
</body>
</html>
  
