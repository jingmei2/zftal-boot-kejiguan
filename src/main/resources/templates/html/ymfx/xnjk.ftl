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
	<link href="${request.contextPath}/assets/css/ymfx/xnjk.css?ver=${versionUtil()}" rel="stylesheet" type="text/css" />
</head>
    <body>
		 <div class="main-content">
		      <div class="sssj-header">
		          <span class="sssj-header-title">用户行为分析</span><br/>
		      </div>
		      <div style="clear:both;"></div>
		      <div class="sssj-xssj">
			      <div class="sssj-xssj-yhTitle">
		             <div class="sssj-xssj-subTitle">关键指标详解</div>
		             <div id="newUserMonth" class="sssj-xssj-type">上课时间</div>
				     <div id="" style="clear:both;"></div>
			      </div>
			      <div class="sssj-xssj-data">
				    <span class="glyphicon glyphicon-option-vertical" aria-hidden="true">趋势图</span>
			      	<div class="sssj-xssj-qst">
	                     <div id="qstPieChart" style="height:360px;"></div>
			      	</div>
			      </div>
		      </div>
		 </div>
	
	<script type="text/javascript" src="${request.contextPath}/assets/js/ymfx/xnjk.js?ver=${versionUtil()}"></script>
	<script src="${request.contextPath}/assets/plugins/laydate/laydate.js?ver=${versionUtil()}"></script>
	</body>
</html>