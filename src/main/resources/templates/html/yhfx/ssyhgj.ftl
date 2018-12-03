<!DOCTYPE html>
<html lang="zh_CN">
<head>
	[#include "/head/zftal-ui-meta.ftl" /]
	[#include "/head/zftal-ui-echarts3.ftl" /]
	[#include "/head/zftal-ui-required.ftl" /]
	<!--首页专用的额外引用-->
	<script type="text/javascript">
		//全局变量
		var _path = "${request.contextPath}";
		var _systemPath = "${request.contextPath}";
	</script>	
	
	[#include "/head/zftal-ui-meta.ftl" /]
	[#include "/head/zftal-ui-required.ftl" /]
	
	<!-- 应用系统自定义样式 -->
	<link href="${request.contextPath}/assets/css/zftal-ui-app.css?ver=${versionUtil()}" rel="stylesheet" type="text/css" />
	<!-- 该页面单独样式 -->
	<link href="${request.contextPath}/assets/css/yhfx/ssyhgj.css?ver=${versionUtil()}" rel="stylesheet" type="text/css" />
</head>
    <body>
		 <div class="main-content">
		      <div class="sssj-header">活跃度</div>
		      <div class="sssj-gjzb">
			      <div class="sssj-gjzb-title">昨日关键指标</div>
			      <div class="sssj-gjzb-data">
			      	<div id="lastDateDAUCount" class="sssj-zb">DAU(日活跃用户数)</div>
			      	<div id="lastDateWAUCount" class="sssj-zb">WAU(周活跃用户数)</div>
			      	<div id="lastDateMAUCount" class="sssj-zb">MAU(月活跃用户数)</div>
			      	<div id="DAUMAUPercent" class="sssj-zb">DAU/MAU</div>
			      </div>
		      </div>
		      <div style="clear:both;"></div>
		      <div class="sssj-xssj">
			      <div>
		             <div class="sssj-xssj-subTitle">关键指标详解</div>
		             <div id="dauMonth" class="sssj-xssj-type">DAU(日活跃用户)</div>
		             <div id="wauMonth" class="sssj-xssj-type">WAU(周活跃用户)</div>
		             <div id="dauwauMonth" class="sssj-xssj-type">DAU/WAU</div>
		             <div id="mauMonth" class="sssj-xssj-type">MAU(月活跃用户)</div>
		             <div id="daumauMonth" class="sssj-xssj-type">DAU/MAU</div>
				     <div id="" style="clear:both;"></div>
			      </div>
			      <div class="sssj-xssj-data">
			      	<span class="glyphicon glyphicon-option-vertical" aria-hidden="true">趋势图</span>
			      	<div class="sssj-xssj-qst">
				      	 <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
	                     <div id="qstChart" style="width:100%;height:300px;"></div>
			      	</div>
			      </div>
			      <div class="sssj-xssj-data">
			        <span class="glyphicon glyphicon-option-vertical" aria-hidden="true">详细数据</span>
			      	<div class="sssj-xssj-xxsj">
			      	   <table id="monthDataTable" class="table table-bordered table-striped">
			      	       <tr><th>时间</th><th>新增用户数</th><th>活跃用户数</th><th>启动次数</th><th>累计次数</th><th>活跃账号数</th></tr>
			      	   </table>
			      	</div>
			      </div>
			      <br/><br/><br/>
		      </div>
		 </div>
	
	<script type="text/javascript" src="${request.contextPath}/assets/js/yhfx/ssyhgj.js?ver=${versionUtil()}"></script>
	</body>
</html>