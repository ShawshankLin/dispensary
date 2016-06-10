<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.io.FilenameUtils"%>
<%@page import="java.lang.reflect.Field"%>
<%@ include file="/commons/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<title></title>
<link rel="stylesheet"
	href="${ctx }/scripts/jquery-easyui/themes/default/easyui.css" />
<script type="text/javascript" src="${ctx }/scripts/jquery.js"></script>
<script type="text/javascript"
	src="${ctx }/scripts/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx }/scripts/adminindex.js"></script>
<style type="">
.tree-file {
	background: url('${ctx}/images/top_arr.jpg') no-repeat;
	background-position-y: center;
	width: 12px;
	height: 9px;
}

.easyui-tree a {
	text-decoration: none;
	color: black;
}
</style>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',split:true"
		style="height:86px;background:url('${ctx }/images/top_bg.jpg') repeat-x">
		<img src="${ctx }/images/crm_logo.jpg" />
	</div>
	<div data-options="region:'south',split:true" style="height:50px;"></div>
	<div data-options="region:'west',title:'菜单树',split:false" title="菜单树"
		style="width:210px;background-color:#DFE8F6">
		<ul id="tt" class="easyui-tree">
			<li><span>菜单</span>
				<ul>
					<%
						String dir = config.getServletContext().getRealPath("/WEB-INF/classes/com/dispensary/project/model");
									                	System.out.println(dir);
									                	File tarDir = new File(dir);
									                	if(tarDir.exists()){
									                		File files[] = tarDir.listFiles();
									                		int i=0;
									                		String modelName = null;
									                		for(File file:files){
									                			modelName = FilenameUtils.removeExtension(file.getName());
									                			Class model = Class.forName("com.dispensary.project.model."+modelName);
									                			Field field = model.getField("TABLE_ALIAS");
									                			Object tableAlias = field.get(null);
									                			System.out.println(tableAlias);
					%>
					<li><span><a id="menu<%=i++ %>"
							href="${ctx }/pages/<%=modelName %>/list.do"><%=tableAlias%>管理</a></span>
					</li>
					<%
						}
					%>
					<li><span><a id="menu<%=i++ %>"
							href="${ctx }/pages/StudentAction/list.do">开处方</a></span>
					</li>
					<%
						}
					%>
				</ul></li>
		</ul>
	</div>
	<div id="main" data-options="region:'center'" style="overflow:hidden">
		<div class="easyui-tabs" id="centerTab" data-options="fit:true"
			style="overflow:hidden"></div>

	</div>
	<div id="tabsMenu" class="easyui-menu" style="width:120px;">

		<div name="refresh">刷新</div>
		<div name="close">关闭</div>
		<div name="closeright">关闭右边</div>
		<div name="closeleft">关闭左边</div>

		<div name="Other">关闭其他</div>
		<div name="All">关闭所有</div>
	</div>
</body>
<script type="text/javascript">
	
</script>
</html>