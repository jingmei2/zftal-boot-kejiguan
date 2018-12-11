<!DOCTYPE html>
<html lang="zh_CN">
<head>
	[#include "/head/zftal-ui-meta.ftl" /]
	[#include "/head/zftal-ui-echarts3.ftl" /]
	[#include "/head/zftal-ui-required.ftl" /]
</head>
<body>
	<div id="container" class="container-yygl">
		<header class="navbar-zhjx">
			<div id="navbar-container">
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
				<div class="navbar-content clearfix">
					<ul id="navbar-content">
						<li class="active">
							<a href="${request.contextPath}/jcfx">预约列表</a>
						</li>
					</ul>
				</div>
			</div>

		</header>

	</div>
	<!-- 应用系统自定义函数 -->

	<script type="text/javascript" src="${request.contextPath}/assets/js/zftal-ui-index.js?ver=${versionUtil()}"></script>
</body>
</html>