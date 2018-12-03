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
	<link href="${request.contextPath}/assets/css/yhfx/xlyh.css?ver=${versionUtil()}" rel="stylesheet" type="text/css" />
</head>
    <body>
		 <div class="main-content">
		      <div class="sssj-header">用户行为</div>
		      <div style="clear:both;"></div>
		      <div class="sssj-xssj">
			      <div>
		             <div class="sssj-xssj-subTitle">关键指标详解</div>
		             <div id="onlineTimePerUv" class="sssj-xssj-type">人均使用时长</div>
		             <div id="onlineTimePerSession" class="sssj-xssj-type">次均时长</div>
		             <div id="pageCountPerUv" class="sssj-xssj-type">人均访问页面数</div>
		             <div id="pageCountPerSession" class="sssj-xssj-type">次均页面数</div>
				     <div id="" style="clear:both;"></div>
			      </div>
			      <div class="sssj-xssj-data">
			      	<span class="glyphicon glyphicon-option-vertical" aria-hidden="true">趋势图</span>
			      	<div class="sssj-xssj-qst">
				      	 <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
	                     <div id="qstChart" style="width:100%;height:400px;"></div>
			      	</div>
			      </div>
		      </div>
		 </div>
	
	<script type="text/javascript" src="${request.contextPath}/assets/js/yhfx/xlyh.js?ver=${versionUtil()}"></script>
	</body>
</html>