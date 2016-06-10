$(function(){
	$("#tt a").click(function(){
		var url = this.href;
		var tabname = $(this).text();
		var tabId = this.id;
		addTab(tabId,tabname,url);
		return false;
	});
	 $("#centerTab").tabs({
	        onContextMenu : function (e, title) {
	            e.preventDefault();
	            $('#tabsMenu').menu('show', {
	                left : e.pageX,
	                top : e.pageY
	            }).data("tabTitle", title);
	        }
	    });
	     
	    //实例化menu的onClick事件
	    $("#tabsMenu").menu({
	        onClick : function (item) {
	        	
	        	if(item.name=='refresh'){
	        		
	        		refreshFrame($("#centerTab").tabs("getSelected"));
	        	}else{
	            	CloseTab(this, item.name);
	            }
	        }
	    });
});
function refreshFrame(curTab){
	var iframe = $(curTab.panel('options').content);
	var src = iframe.attr('src');		     
	$('#centerTab').tabs('update', {
		tab: curTab,
		options: {
				content: '<iframe name="'+iframe.attr("name")+'"id="'+iframe.attr("id")+'"src="'+src+'" width="100%" height="100%" frameborder="0" scrolling="auto" ></iframe>'
		}
	});
}
function addTab(tabId,title,url){  
    //如果当前id的tab不存在则创建一个tab  
    if($("#tab-"+tabId).html()==null){  
        var name = 'iframe_'+tabId;  
        $('#centerTab').tabs('add',{  
            title: title,           
            closable:true,  
            cache : false,   
            content : '<iframe name="'+name+'"id="tab-'+tabId+'"src="'+url+'" width="100%" height="100%" frameborder="0" scrolling="auto" ></iframe>'  
        });  
    }else{
    	$('#centerTab').tabs('select',title);
    	//refreshFrame($('#centerTab').tabs("getTab",title));
    }  
}  
	    function CloseTab(menu, type) {
	    	var onlyOpenTitle="";
	        var curTabTitle = $(menu).data("tabTitle");
	        var tabs = $("#centerTab");
	        var currentTab =tabs.tabs('getSelected');
	        var allTabs = tabs.tabs("tabs");
	        var allTabtitle = [];
		    $.each(allTabs,function(i,n){
		        allTabtitle.push($(n).panel('options').title);
		    });
	        if (type === "close") {
	            tabs.tabs("close", curTabTitle);
	            return;
	        }else if(type==="closeright"){
	        	var tabIndex = tabs.tabs('getTabIndex', currentTab);
	            if (tabIndex == allTabs.length - 1){
	                alert('亲，后边没有啦 ^@^!!');
	                return false;
	            }
	            $.each(allTabtitle, function (i, n) {
	                if (i > tabIndex) {
	                    if (n != onlyOpenTitle){
	                        tabs.tabs('close', n);
	                    }
	                }
	            });
	        	
	        }else if(type==="closeleft"){
	        	var tabIndex = tabs.tabs('getTabIndex', currentTab);
	        	console.log("tabIndex:"+tabIndex);
	            if (tabIndex == 0) {
	                alert('亲，前边那个上头有人，咱惹不起哦。 ^@^!!');
	                return false;
	            }
	            $.each(allTabtitle, function (i, n) {
	                if (i < tabIndex) {
	                    if (n != onlyOpenTitle){
	                        tabs.tabs('close', n);
	                    }
	                }
	            });
	        	
	        }else{
		        
		        var closeTabsTitle = [];
		        $.each(allTabs, function () {
		            var opt = $(this).panel("options");
		            if (opt.closable && opt.title != curTabTitle && type === "Other") {
		                closeTabsTitle.push(opt.title);
		            } else if (opt.closable && type === "All") {
		                closeTabsTitle.push(opt.title);
		            }
		        });
		         
		        for (var i = 0; i < closeTabsTitle.length; i++) {
		            tabs.tabs("close", closeTabsTitle[i]);
		        }
	        }
	    }