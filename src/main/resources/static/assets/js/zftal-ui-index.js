jQuery(function($) {
	
	// 首页密码找回按钮事件
	$(document).off('click','#xgmm').on('click','#xgmm',function(){
		$.showDialog(_path + "/xtgl/yhgl/xgMm.zf", '修改密码', $.extend({}, modifyConfig, {
			"width": "500px",
			fullScreen: false
		}));
	});
	
	
	var h=$(window).height()-$(".container-jcfx .navbar-content").height()-40;
	$(".container-jcfx .zf-content").css("height",h);
	$(".container-jcfx #mainnav-menu-wrap").css("height",h);
	
	$(window).resize(function(){
		var h=$(window).height()-$(".container-jcfx .navbar-content").height()-40;
		$(".container-jcfx .zf-content").css("height",h);
		$(".container-jcfx #mainnav-menu-wrap").css("height",h);
	});
	
});