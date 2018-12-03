
//给背景图片赋值高度（防止页面闪动，放在代码靠前的位置）
var h = $(window).height();
$(".login-page").css("height", h);
var a = '';

jQuery(function($){

	//窗口大小变化，调整背景高度
	$(window).resize(function() {
		var h = $(window).height();
		$(".login-page").css("height", h);
	});

	//二维码切换
	$(".login-page .login-form .tabs-icon img").click(function() {
		$(this).removeClass("active").siblings().addClass("active");
		$(this).parents(".login-form").find(".login-cont").toggle().next(".qrcode-cont").toggle();
	});

	$("#yzmPic").click(function(){
		$("#yzmPic").attr("src", _path + '/kaptcha?time=' + new Date().getTime());
	});

	var _loginFun = function (e) {
		var event = $.event.fix(e);
		//回车自动查询
		if( event.keyCode == 13){
			//取消浏览器默认行为
			event.preventDefault();
			//点击登录
			$('#dl').click();
		}
		//取消事件冒泡
		event.stopPropagation();
		//阻止剩余的事件处理函数执行并且防止事件冒泡到DOM树上
		event.stopImmediatePropagation();
	}
	if ($("#yzm").size() > 0){
		$('#yzm').bind("keydown", _loginFun);
	} else {
		$('#mm').bind("keydown", _loginFun);
	}

	var modulus,exponent;

	$.post(_path+"/authz/login/getPublicKey.zf?time="+new Date().getTime(),function(data){
		modulus = data["modulus"];
		exponent = data["exponent"];
	});

	$("#btn-login").click(function(){
		var ts = '<span class="glyphicon glyphicon-minus-sign"></span>';
		if(!$.founded($("#yhm").val())){
			$("#tips").empty().append(ts + "用户名不能为空！");
			$("#tips").show();
			return false;
		}
		if(!$.founded($("#mm").val())){
			///$("#tips").removeClass("alert-danger").addClass("alert-warning");
			$("#tips").empty().append(ts + "密码不能为空！");
			$("#tips").show();
			return false;
		}

		if($("#yzm").size() > 0 && !$.founded($("#yzm").val())){
			console.log(1)
			$("#tips").empty().append(ts + "验证码不能为空！");
			$("#tips").show();
			return false;
		}

		var rsaKey = new RSAKey();
		rsaKey.setPublic(b64tohex(modulus), b64tohex(exponent));
		var enPassword = hex2b64(rsaKey.encrypt($("#mm").val()));
		$("#mm").val(enPassword);
		document.forms[0].submit();
	});

	//getUuid();
	/*setTimeout(function(){
		keepPool();
	},200);*/

});

function getUuid(){
	$.post(_path+"/qrCode/getUuid", {} ,function(data){
		$("#qrcode").attr('src','/qrCode/qrcode/'+data);
		a = data;
	},"json");
}

function keepPool(){
	console.log('检查'+a+'是否登录');

	$.ajax({
		type:"post",
		url:"/qrCode/pool",
		data:{
			uuid : a,
		},
		success:function(data){
			console.log(data);
	        if(data=='success'){
	          	window.location.href = _path+"/authz/index";
	        }else if(data=='timeout'){
	            /*$("#result").html("登录超时，请刷新重试"); */
	        }else{
	            keepPool();
	        }
		}
	});
}

