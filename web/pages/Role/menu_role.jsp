<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.dispensary.project.model.*"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<!doctype html>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title><%=Role.TABLE_ALIAS%>管理</title>
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
<link rel="stylesheet" href="${ctx}/scripts/ztree/css/demo.css" type="text/css"/>
<script type="text/javascript" src="<c:url value="/widgets/simpletable/simpletable.js"/>"></script>
<script type="text/javascript" src="../../scripts/common.js"></script>	
<script type="text/javascript" src="../../scripts/jquery.js"></script>
<script type="text/javascript" src="../../scripts/json2.js"></script>
<script type="text/javascript">

	
	
	<!--  
    var setting = {  
        check: {  
            enable: true  
        },  
        data: {  
            simpleData: {  
                enable: true  
            }  
        },
        callback:{
            onCheck:onCheck
        }
    };  

    var zNodes ;  
 
    function fuzhi(data){  
        zNodes=eval("("+data+")");  
        $.fn.zTree.init($("#treeDemo"), setting, zNodes);
      
    }  
      
    $(document).ready(function(){
    	$('#roleId').val(getUrlParam('roleId'));
	   	$.get("${ctx}/pages/Role/loadMenu.do?roleId="+$('#roleId').val(),function(data){ 
	        fuzhi(data);  
	        
        });
		
	    $("#savebt").click(function(){
	    	$.ajax({
                type: "POST",
                url: "${ctx}/pages/MenuRole/save.do",
                data : {"menuIds":$('#menuId').val(),"roleId":$('#roleId').val()},
                error: function(request) {
                    alert("Connection error");
                },
                success: function(data) {
                    if ("ok" == data) {
                    	alert("保存成功！");
                    	history.back();
                    } else {
                        //customShowDialog("您未做任何操作，无法保存！");
                    }
                }
            });
	    });
	    
    });  
    
    function onCheck(e,treeId,treeNode){
    	
        var treeObj=$.fn.zTree.getZTreeObj("treeDemo");
        nodes=treeObj.getCheckedNodes(true);
        v="";
        for(var i=0;i<nodes.length;i++){
        	if(nodes[i].idt!=null&&nodes[i].idt!="0"){
        		v+=nodes[i].idt + ",";
        	}
        }
        document.getElementById('menuId').value=v;
    }
    
    //--> 
	   
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
							<strong class="am-text-primary am-text-lg">菜单-角色管理</strong>
							<!-- <small></small> -->
						</div>
					</div>
					<div class="am-g">
						<div class="am-u-sm-12">
							<div class="zTreeDemoBackground" style="margin-left: 30px;margin-top: 20px;">
				<ul id="treeDemo" class="ztree"></ul>
				</div>
				<div class="am-u-lg-6" style="margin-top: 20px;margin-left: 30px;">
					<input type="hidden" id="menuId">
					<input type="hidden" id="roleId">
					<button type="submit" class="am-btn am-btn-primary am-btn-xs" id="savebt">提交保存</button>
			    	<button type="button" class="am-btn am-btn-primary am-btn-xs" onclick="history.back()">放弃保存</button>
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

	
<!-- ztree -->
<script type="text/javascript" src="${ctx}/scripts/ztree/js/jquery.ztree.core-3.5.js" ></script>
<script type="text/javascript" src="${ctx}/scripts/ztree/js/jquery.ztree.excheck-3.5.min.js" ></script>	
<link rel="stylesheet" href="${ctx}/scripts/ztree/css/zTreeStyle.css" type="text/css"/>	
</body>
</html>