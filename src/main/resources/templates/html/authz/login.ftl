<!DOCTYPE html>
<html>

	<head>
		[#include "/head/zftal-ui-meta.ftl" /]
		<link rel="stylesheet" type="text/css" href="${request.contextPath}/webjars/zftal-ui-v5/plugins/bootstrap/css/bootstrap.min.css?ver=${versionUtil()}" />
		<link rel="stylesheet" href="${request.contextPath}/assets/css/login.css?ver=${versionUtil()}" />
		<script type="text/javascript">
			//全局变量
			var _path = "${request.contextPath}";
		</script>	
	</head>

	<body>
		<div class="login-page">
			<div class="login-form-wrap col-lg-3 col-md-5 col-sm-6 col-xs-12 pull-right p-lr0">
				<div class="login-form">
					<div class="tabs-icon">
						<img class="active" src="${request.contextPath}/assets/images/tab_one.png">
						<img src="${request.contextPath}/assets/images/tab_two.png">
					</div>
					<div class="text-center title">
						<h3>用户登录</h3>
					</div>
					<div class="login-cont">
						 <form class="form-horizontal" role="form" action="${request.contextPath}/authz/login/slogin" method="post" autocomplete="off">
						  	<!-- 防止浏览器自动填充密码 -->
							<input type="text" style="display: none;" />
							<input type="password" style="display: none;" />
							<!-- 防止浏览器自动填充密码 end -->
							[#if message??]
						    <p id="tips" class="bg-danger sl_danger">
						    	<span class="glyphicon glyphicon-minus-sign"></span>${message}
						    </p>
						    [#else]
						    <div class="form-group"><p style="display: none;" id="tips" class="bg bg-warning"></p></div>
						    [/#if]
							<div class="form-group">
								<div class="input-group">
									<div class="input-group-addon"><img src="${request.contextPath}/assets/images/login_user.png"></div>
									<input type="text" class="form-control user-input" name="yhm" id="yhm" placeholder="用户名"  value="admin" autocomplete="off">
								</div>
							</div>
							<div class="form-group">
								<div class="input-group">
									<div class="input-group-addon"><img src="${request.contextPath}/assets/images/login_pwd.png"></div>
									<input type="password" class="form-control pwd-input" name="mm" id="mm" placeholder="密码" value="zfsoft123" autocomplete="off"/>
								</div>
							</div>
							[#if validateCaptcha() == "true"]
						  	<div class="form-group">
						      	<div class="input-group col-lg-7 col-md-7 col-sm-7 col-xs-7 pull-left p-lr0">   
						        	<input type="text" name="yzm" id="yzm" class="form-control" placeholder="验证码" autocomplete="off">
						      	</div>
						      	<div class="col-lg-5 col-md-5 col-sm-5 col-xs-5 p-lr0" id="yzmDiv" >
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right col-xs-offset-1">
								      	<img border="0" src="${request.contextPath}/kaptcha?time=${.now}" onclick="javascript:refreshCode();" align="absmiddle" id="yzmPic" style="cursor: pointer;" width="108" height="32" />
									</div>
								</div>
						    </div>
						    [/#if]
							<div class="form-group">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 p-lr0 forget-me">
									<input type="checkbox" name="RememberMe" /><span>记住我（一周内无需再次输入账号密码）</span>
								</div>
							</div>
							<div class="form-group login-btn-wrap">
								<button type="button" id="btn-login" class="btn btn-block btn-primary login-btn">登 录</button>
							</div>
						</form>
						
						<div class="form-group">
							<a href="#">忘记密码?</a>
							<a href="#" class="pull-right">创建新账号</a>
						</div>
					</div>
					<div class="qrcode-cont">
						<div class="scanning-info">
							<div class="form-group"><img id="qrcode" src="${request.contextPath}/assets/images/qrcode.png"></div>
							<div class="form-group"><span>手机扫描</span><span class="pull-right">安全登录</span></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		[#include "/head/zftal-ui-jquery.ftl" /]
		[#include "/head/zftal-ui-rsa.ftl" /]
		<script type="text/javascript" src="${request.contextPath}/assets/js/login.js?ver=${versionUtil()}"></script>
	</body>

</html>