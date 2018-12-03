jQuery(function($) {
	
	$("#navbar-content").find(">li").removeClass("active");
	$("#navbar-content").find(">li:eq(2)").addClass("active");
	
	//计算tabs高度
	var pageHeight = $(window).height() - $('#tabs').outerHeight(true) - $('#footer').outerHeight(true) - $('#navbar').outerHeight(true);
	var boxHeight = $(window).height() - $('#footer').outerHeight(true);
	
	$('#boxed').height(boxHeight);
	
	$(window).resize(function() {
		var boxHeight = $(window).height() - $('#footer').outerHeight(true);
		$('#boxed').height(boxHeight);
	});
	
	function buildURL(requestURL,data){
		data = data || {};
		requestURL = _path + requestURL;
		//在url上追加
		/*
		if(requestURL.indexOf("?") > -1){
			requestURL = requestURL + "&th=" + pageHeight + "&gnmkdmKey=" + data["gnmkdm"];
		}else{
			requestURL = requestURL + "?th=" + pageHeight + "&gnmkdmKey=" + data["gnmkdm"];
		}
		*/
		//alert("requestURL:" + requestURL);
		return requestURL;
	};

	$(document).off('menuReady').on('menuReady',function(){
		
	});
	
	//根据点击的一级菜单显示二级
	$.get( _path + "menu/getMenuData", {}, function(data) {
		if($.founded(data)){
			data.sort(function(a,b){
				return a.xssx-b.xssx;
			});
			var html = [];
			for(var i = 0; i < data.length; i++) {
				var item = data[i];
				var children = item["children"] || [];
				children.sort(function(a,b){
					return a.xssx-b.xssx;
				});
				if($.founded(children)){
					html.push('<li class="first">');
						html.push('<a href="javascript:void(0);">');
						html.push('<i class="'+(item["tblj"]  || "fa fa-th-list")+'"></i>');
						html.push('<span class="menu-title">' + item["gnmkmc"] + '</span>');
						html.push('<i class="arrow"></i>');
						html.push('</a>');
						html.push('<ul class="collapse">');
						for(var j = 0; j < children.length; j++) {
							var child = children[j];
							html.push('<li><a href="' + buildURL(child["dyym"], child) +'" target="jcfxFrame" data-tblj="'+child["tblj"]+'" title="'+child["gnmkmc"]+'" data-addtab=' + child["gnmkdm"] + '  data-src=' + buildURL(child["dyym"], child) + ' data-tab-layout="default"  data-blank-layout="default-tab"><i class="'+child["tblj"]+'"></i>' + child["gnmkmc"] + '</a></li>');
						}
						html.push('</ul>');
					html.push('</li>');
					
				} else {
					html.push('<li class="first">');
					html.push('<a href="javascript:void(0);" title="'+item["gnmkmc"]+'" data-addtab="' + item["gnmkdm"] + '"  data-src="' + buildURL(item["dyym"], item) + '" data-tab-layout="default"  data-blank-layout="default-tab">');
					html.push('<i class="' + (item["tblj"]  || "fa fa-th-list") + '"></i>');
					html.push('<span class="menu-title">' + item["gnmkmc"] + '</span>');
					html.push('</a>');
					html.push('</li>');
				}

			}
			
			try {
				if($('#mainnav-menu li').size() > 0) {
					$('#mainnav-menu').metisMenu('dispose');
				}
				$("#mainnav-menu").empty().html(html.join(""));
				$('#mainnav-menu').metisMenu({ toggle: true });
				
				//绑定导航相关事件
				$.niftyNav('bind');
				$.niftyAside('bind');
				
	        } catch(err) {
	            console.error(err.message);
	        }
		}
	}, "json");
	
	$(document).off('click','#mainnav-menu ul.collapse.in>li').on('click','#mainnav-menu ul.collapse.in>li',function(){
		$('#mainnav-menu li').removeClass('current');
	}).off('click','#mainnav-menu>li').on('click','#mainnav-menu>li',function(){
		var ul=$(this).find('>ul');
		var wh=parseInt($(window).height());
		var titleHeight=parseInt($('.brand-title').outerHeight());
		var secondHeight=parseInt($('#mainnav-menu>li').size() * 44);
		var thisLiHeight=ul.find('>li').size()*40;
		var placeholderHeight=wh-titleHeight-secondHeight-thisLiHeight;
		$('#mainnav-menu .placeholder').remove();
		if($(this).hasClass('active')){
			if(ul.find('.placeholder').size()==0){
				ul.append('<li class="placeholder"></li>');
			}
			$('#mainnav-menu .placeholder').height(placeholderHeight);
		}else{
			ul.find('.placeholder').remove();
		}
		
	}).off('click','.container-jcfx #mainnav-menu a').on('click','.container-jcfx #mainnav-menu a',function(){
		$(this).parents(".collapse").find("a").removeClass("on"); 
		$(this).parents("li").siblings("li").find("a").removeClass("on"); 
		$(this).addClass("on");
	});
	
	$(window).resize(function(){
		if($('#mainnav-menu').find('.placeholder').size()>0){
			var ul=$('.placeholder').closest('ul');
			var wh=parseInt($(window).height());
			var titleHeight=parseInt($('.brand-title').outerHeight());
			var secondHeight=parseInt($('#mainnav-menu>li').size() * 44);
			var thisLiHeight=(ul.find('>li').size()-1)*40;
			var placeholderHeight=wh-titleHeight-secondHeight-thisLiHeight;
			$('#mainnav-menu .placeholder').height(placeholderHeight);
		}
	});
	
	
	//初始化时button选中样式
	initBtnClass();
    //更改css样式
    $('button').click(function(){
    	var thisBtn = $(this);
    	//alert(123);
    	$.get(_path+"/mDevice/updateMtaDeviceType", {} ,function(result,status){
      		if(!!result){
    			//console.log(result);
    			if(parseInt(result.code)==1){
    				if(!!result.data){
    					if(parseInt(result.data)>0){
    						window.location.href=_path+"/jcfx";
    					}else{
    						alert("切换类型异常");
    					}
    				}
    			}
      		}
      	},"json");
    });
	
}); 

function initBtnClass(){
	//getMtaDeviceType
	$.get(_path+"/mDevice/getMtaDeviceType", {} ,function(result,status){
  		if(!!result){
			//console.log(result);
			if(parseInt(result.code)==1){
				if(!!result.data){
					if(result.data=='android'){
						$('button:first').addClass('btn-primary');
					}else if(result.data=='ios'){
						$("button:eq(1)").addClass('btn-primary');
					}
				}
			}
  		}
  	},"json");
}

function setIframeHeight(iframe) {
	if (iframe) {
		var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
		if (iframeWin.document.body) {
		    iframe.height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
		}
	}
}
