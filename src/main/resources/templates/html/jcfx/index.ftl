<!DOCTYPE html>
<html lang="zh_CN">
<head>
	[#include "/head/zftal-ui-meta.ftl" /]
	[#include "/head/zftal-ui-required.ftl" /]
	<!--首页专用的额外引用-->
	<script type="text/javascript">
		//全局变量
		var _path = "${request.contextPath}";
		var _systemPath = "${request.contextPath}";
	</script>
</head>
<body>
	<div id="container" class="container-jcfx effect aside-float aside-bright mainnav-lg mainnav-fixed navbar-fixed">
			<header id="navbar" class="navbar-zhjx">
				<div id="navbar-container" class="boxed">
					<div class="navbar-header">
						<div class="col-lg-4 col-md-4">
							<a href="#">
								<img src="assets/images/logo.png" alt="Logo" class="brand-icon">
								<span class="brand-text">科技馆</span>
							</a>
						</div>
						<div class="col-lg-4 col-md-3">
							<div class="form-group">
								<input type="text" id="search-input" class="form-control">
								<i class="fa fa-search icon-right" aria-hidden="true"></i>
							</div>
						</div>
						<div class="col-lg-4 col-md-5">
							<ul class="link-info pull-right">
								<li>
									<a href="#"><img src="assets/images/icon01.png"><span>数据分析</span></a>
								</li>
								<li>
									<a href="#"><img src="assets/images/icon02.png"><span>我的应用</span></a>
								</li>
								<li>
									<a href="#"><img class="img-circle head-portrait" src="assets/images/admin.png"><span>${Session.user.yhm}</span></a>&nbsp;&nbsp;
									<a href="${request.contextPath}/logout">
										注销
	                                    [#--[@spring.message code="home.logout"/]--]
	                                </a>
							    </li>
							</ul>
						</div>
					</div>

					<!--顶部导航-->
					<div class="navbar-content clearfix">
					<ul id="navbar-content">

						<li class="active">
							<a href="${request.contextPath}/jcfx">基础分析</a>
						</li>
					</ul>
				</div>
			</div>
			</header>

			<div class="boxed" id="boxed">
				<!--左侧侧边栏-->
				<nav id="mainnav-container">
					<div id="mainnav">
						<div id="mainnav-menu-wrap">
							<div class="nano">
								<div class="nano-content">
									<div style="text-align:center;">
									    <div class="btn-group" role="group">
											  <button type="button" class="btn btn-default" style="width:70px;">微信</button>
									    </div>
									</div>
									<!--个人快速链接，默认隐藏-->
									<div id="mainnav-shortcut" class="hidden">
										<ul class="list-unstyled">
											<li class="col-xs-3" data-content="My Profile">
												<a class="shortcut-grid" href="javascript:void(0);">
													<i class="demo-psi-male"></i>
												</a>
											</li>
											<li class="col-xs-3" data-content="Messages">
												<a class="shortcut-grid" href="javascript:void(0);">
													<i class="demo-psi-speech-bubble-3"></i>
												</a>
											</li>
											<li class="col-xs-3" data-content="Activity">
												<a class="shortcut-grid" href="javascript:void(0);">
													<i class="demo-psi-thunder"></i>
												</a>
											</li>
											<li class="col-xs-3" data-content="Lock Screen">
												<a class="shortcut-grid" href="javascript:void(0);">
													<i class="demo-psi-lock-2"></i>
												</a>
											</li>
										</ul>
									</div>
									<!--导航正式内容-->
									<ul id="mainnav-menu" class="list-group">
									</ul>
								</div>
							</div>
						</div>

					</div>
				</nav>

				<!--中间内容-->
				<div id="content-container" class="content-container">
					<div class="zf-content">
						<div class="page-content">
							<iframe id="jcfxFrame" name="jcfxFrame" src="${request.contextPath}/yhgx/book" style="width:100%;height:700px;" frameborder="0" scrolling="yes"></iframe>
						</div>
					</div>

				</div>

			</div>
		</div>
	<!-- 应用系统自定义函数 -->
	<script type="text/javascript" src="${request.contextPath}/assets/js/jcfx/index.js?ver=${versionUtil()}"></script>
	<script type="text/javascript" src="${request.contextPath}/assets/js/zftal-ui-index.js?ver=${versionUtil()}"></script>
</body>
</html>