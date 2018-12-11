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
	<link href="${request.contextPath}/assets/css/yyqs/sssj.css?ver=${versionUtil()}" rel="stylesheet" type="text/css" />
</head>
    <body>


	<script type="text/javascript" src="${request.contextPath}/assets/js/yyqs/sssj.js?ver=${versionUtil()}"></script>
	</body>
</html>