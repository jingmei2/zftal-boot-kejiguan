<script type="text/javascript">
	//全局变量
	var _path = "${request.contextPath}";
</script>
[#include "/head/zftal-ui-jquery.ftl" /]
[#include "/head/zftal-ui-bs.ftl" /]
[#include "/head/zftal-ui-ie8-fix.ftl" /]
[#include "/head/zftal-ui-v5.ftl" /]
<!--jQuery浏览器检测 -->
<script type="${request.contextPath}/text/javascript" src="${request.contextPath}/assets/plugins/browse/browse-judge.js?ver=${versionUtil()}"></script>
<!--基于Nifty样式库的新版界面前端依赖-->
<script src="${request.contextPath}/webjars/zftal-ui-v5/plugins/nifty/js/nifty.js?ver=${versionUtil()}"></script>
<link href="${request.contextPath}/webjars/zftal-ui-v5/plugins/nifty/css/nifty.min.css?ver=${versionUtil()}" rel="stylesheet" type="text/css"/>
<!--皮肤+字体-->
[#--<link href="${request.contextPath}/webjars/zftal-ui-v5/css/themes/zftal-ui-nifty-icons.css?ver=${versionUtil()}" rel="stylesheet" type="text/css"/>
<link href="${request.contextPath}/webjars/zftal-ui-v5/css/themes/theme-ocean.min.css?ver=${versionUtil()}" rel="stylesheet" type="text/css"/>--]
<link href="${request.contextPath}/webjars/zftal-ui-v5/css/zftal-ui-base.css?ver=${versionUtil()}" rel="stylesheet" type="text/css"/>
<!-- 应用系统自定义样式 -->
<link href="${request.contextPath}/assets/css/zftal-ui-app.css?ver=${versionUtil()}" rel="stylesheet" type="text/css" />
<!--业务框架jQuery全局设置和通用函数库-->
<script type="text/javascript" src="${request.contextPath}/assets/js/jquery.zftal.contact-min.js?ver=${versionUtil()}"></script>
<script type="text/javascript" src="${request.contextPath}/assets/js/zftal-ui-app.js?ver=${versionUtil()}"></script>
