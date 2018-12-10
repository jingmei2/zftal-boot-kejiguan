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
							<a href="${request.contextPath}/yygl">应用概览</a>
						</li>
						<li class="active">
							<a href="${request.contextPath}/jcfx">基础分析</a>
						</li>
						<li>
							<a href="#">应用管理</a>
						</li>
					</ul>
				</div>
			</div>

		</header>
		<div class="boxed" id="boxed">
				<!--中间内容-->
				<div id="content-container" class="content-container">
					<div class="row">
						<div class="col-md-4">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">安全预警</h3>
								</div>
								<div class="panel-body">
								    <br/>
									<div id="first-echart"></div>
								</div>
							</div>
						</div>

						<div class="col-md-4 p-l0">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">分析报告</h3>
								</div>
								<div class="panel-body">
									<div id="fxbgPanel" class="newlist">
										<ul>

										</ul>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-4 p-l0">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">安全分析</h3>
								</div>
								<div class="panel-body">
									<div id="yqjkPanel" class="newlist">
										<ul>

										</ul>
									</div>
								</div>
							</div>
						</div>

					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">预警关键指标</h3>
								</div>
								<div class="panel-body" id="yjgjzb-list">
									<div class="col-md-3 text-center">
										<ul>
											<li>
												<p class="sm-title">消费预警</p>
											</li>
											<li><b class="num" id="totalCountXf">0</b></li>
											<li><span class="date">日</span><i class="fa fa-long-arrow-up"></i><span class="percentage red-bg" id="xfDateZzl">0.00%</span></li>
											<li><span class="date">月</span><i class="fa fa-long-arrow-up"></i><span class="percentage red-bg" id="xfMonthZzl">0.00%</span></li>
											<li><span class="date">年</span><i class="fa fa-long-arrow-up"></i><span class="percentage red-bg" id="xfYearZzl">0.00%</span></li>
										</ul>
									</div>
									<div class="col-md-3 text-center">
										<ul>
											<li>
												<p class="sm-title">晚归预警</p>
											</li>
											<li><b class="num" id="totalCountWg">0</b></li>
											<li><span class="date">日</span><i class="fa fa-long-arrow-up"></i><span class="percentage red-bg" id="wgDateZzl">0.00%</span></li>
											<li><span class="date">月</span><i class="fa fa-long-arrow-up"></i><span class="percentage red-bg" id="wgMonthZzl">0.00%</span></li>
											<li><span class="date">年</span><i class="fa fa-long-arrow-up"></i><span class="percentage red-bg" id="wgYearZzl">0.00%</span></li>
										</ul>
									</div>
									<div class="col-md-3 text-center">
										<ul>
											<li>
												<p class="sm-title">成绩预警</p>
											</li>
											<li><b class="num" id="totalCountCj">0</b></li>
											<li><span class="date">日</span><i class="fa fa-long-arrow-up"></i><span class="percentage red-bg" id="cjDateZzl">0.00%</span></li>
											<li><span class="date">月</span><i class="fa fa-long-arrow-up"></i><span class="percentage red-bg" id="cjMonthZzl">0.00%</span></li>
											<li><span class="date">年</span><i class="fa fa-long-arrow-up"></i><span class="percentage red-bg" id="cjYearZzl">0.00%</span></li>
										</ul>
									</div>
									<div class="col-md-3 text-center">
										<ul>
											<li>
												<p class="sm-title">借阅预警</p>
											</li>
											<li><b class="num" id="totalCountJy">0</b></li>
											<li><span class="date">日</span><i class="fa fa-long-arrow-up"></i><span class="percentage red-bg" id="jyDateZzl">0.00%</span></li>
											<li><span class="date">月</span><i class="fa fa-long-arrow-up"></i><span class="percentage red-bg" id="jyMonthZzl">0.00%</span></li>
											<li><span class="date">年</span><i class="fa fa-long-arrow-up"></i><span class="percentage red-bg" id="jyYearZzl">0.00%</span></li>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">晚归名单</h3>
									<a class="more pull-right" href="#">更多</a>
								</div>
								<div class="panel-body">
									<!--晚归名单-->
									<div id="wangmd" class="newlist">
										<ul>

										</ul>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6 p-l0">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">危险区域名单</h3>
									<a class="more pull-right" href="#">更多</a>
								</div>
								<div class="panel-body">
									<div id="weixqymd" class="newlist">
											<ul>

											</ul>
										</div>

								</div>
							</div>
						</div>
					</div>
				</div>
		</div>
	</div>
	<!-- 应用系统自定义函数 -->
	<script type="text/javascript" src="${request.contextPath}/assets/js/yygl/index.js?ver=${versionUtil()}"></script>
	<script type="text/javascript" src="${request.contextPath}/assets/js/zftal-ui-index.js?ver=${versionUtil()}"></script>
</body>
</html>