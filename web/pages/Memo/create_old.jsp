<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>添加备忘录</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link type="text/css" rel="stylesheet" href="../../styles/pure-min.css">
<link rel="stylesheet" href="../../kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="../../kindeditor/plugins/code/prettify.css" />
<script charset="utf-8" src="../../kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="../../kindeditor/lang/zh_CN.js"></script>
<script charset="utf-8" src="../../kindeditor/plugins/code/prettify.js"></script>
<script type="text/javascript" src="../../scripts/jquery.js"></script>
<script type="text/javascript" src="../../scripts/common.js"></script>
<script>
	KindEditor.ready(function(K) {

		var editor = K.create('textarea[name="content"]', {

			cssPath : '../../kindeditor/plugins/code/prettify.css',

			uploadJson : '../../kindeditor/jsp/upload_json.jsp',

			fileManagerJson : '../../kindeditor/jsp/file_manager_json.jsp',

			allowFileManager : true,

			afterCreate : function() {

				var self = this;

				K.ctrl(document, 13, function() {

					self.sync();

					document.forms['memo'].submit();

				});

				K.ctrl(self.edit.doc, 13, function() {

					self.sync();

					document.forms['memo'].submit();

				});

			}

		});

		prettyPrint();
	});
	
	function checkEmpty(){
        var content = document.getElementById("content").value;
		console.log(content);
        if(content.html()==""){
                alert("内容不能为空1！");
                return false;
        }
        if(content.replace(/\s/g,'')==''){
                alert("内容不能为空2！");
                return false;
        }
}
	$(document).ready(function(){
		//显示现在时间	
		document.getElementById('date').defaultValue=getDate();
	});
</script>
<style type="text/css">
	body{
		width:100%;
	}
	.main{
		width:960px;
		margin:2% auto;	
	}

</style>
</head>
<body>
	<div class="main">
	<form name="memo" method="post" action="${ctx}/pages/Memo/save.do" class="pure-form">
		<fieldset>
		<legend align="center" style="font-size:15pt;font-weight:bold">添加备忘录</legend>
			<table width="100%">
				<input type="hidden" name="userId" value="${sessionScope.loginUser.userId}"/>
				<tr>
					<td><label for="title">标题：</label> </td>
					<td>
						<input id="title" type="text" name="title" size=100 placeholder="必填" required>
						<input type="hidden" name="date" id="date"/>
						<input type="hidden" name="status" value="1"/> 
					</td>
				</tr>
				<tr>
					<td valign="top"><label for="content">内容：</label></td>
					<td>
						<textarea name="content" cols="108" rows="15" id="content"></textarea>			
					</td>
				</tr>
				<tr>
					<td></td>
					<td align="right">
						<input type="submit" class="pure-button pure-button-primary" value="提交"/>
						<input type="button" class="pure-button  pure-button-primary" value="返回"
							onclick="history.back()"></input>
					</td>
				</tr>
			</table>	
		</fieldset>
	</form>
</div>
</body>

</html>