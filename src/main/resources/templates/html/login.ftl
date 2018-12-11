<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
    <title>科技馆  | 登录页</title>

    <link href="css/mui.min.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>

</head>
<body>
	<header class="mui-handle">
		<a class="mui-icon mui-icon-closeempty mui-pull-left"></a>
	</header>
	<div class="avatar">
		<img src="images/avatar.png"/>
	</div>
	<div class="mui-login">
		<form method="post" action="/baist/weixin/toQyxx">
		    <div class="mui-input-row">
		    	<input type="text" class="mui-input-clear mui-ipt-gray m-b-20" placeholder="请输入账号" id="userName" name="userName">
		    </div>
		    <div class="mui-input-row">
		        <input type="password" class="mui-input-password mui-ipt-gray m-b-20" placeholder="请输入密码" id="password" name="password">
		    </div>
		    <div class="mui-button-row">
		        <button type="button" class="mui-btn mui-btn-primary mui-btn-block" onclick="goCheck()">登录</button>
		    </div>
		</form>
		<div class="mui-tips">
			<a class="mui-pull-left" href="#" >忘记密码？</a>
			<a class="mui-pull-right" href="#" onclick="goAddUser()">新用户注册</a>
		</div>
	</div>

	<script src="js/mui.min.js"></script>
	<script src="js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" charset="utf-8">
      	mui.init();

      	function goForget(){
      		if($("#userName").val() == '' || $("#userName").val() == null){
      			alert("请输入用户名");
      		}else{
      			$.ajax({
      				type:"post",
      				url:"/baist/user/get",
      				data:{
      					kl:'zfsoft',
      					userName:$("#userName").val(),
      				},
      				success:function(data){
      					if(data.userResult.userName != null){
      						window.location.href = "/baist/weixin/toWjmm?mobile=" + data.userResult.phoneNumber + "&userName=" + data.userResult.userName;
      					}else{
      						alert("请输入正确的账号");
      					}
      				}
      			});
      		}
      	}

      	function goAddUser(){
      		window.location.href = "userregist.html";
      	}

      	function goCheck(){
      		if($("#userName").val() == '' || $("#userName").val() == null){
      			alert("请输入用户名");
      		}else if($("#password").val() == '' || $("#password").val() == null){
      			alert("请输入用户名");
      		}else{
      			$.ajax({
      				type:"post",
      				url:"/wx/userLogin",
      				data:{
      					userName:$("#userName").val(),
      					pass:$("#password").val(),
      				},
      				success:function(data){
      					if(data.code == 1){
      						window.location.href = "userinfo.html";
      					}else{
      						alert(data.status);
      					}
      				}
      			});
      		}
      	}


    </script>
</body>
</html>