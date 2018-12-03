<!DOCTYPE html>
<html lang="zh_CN">
<head>
	[#include "/head/zftal-ui-meta.ftl" /]
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
</head>
    <body>
	    <div class="common-main-content">
		     <form action="${request.contextPath}/mDevice/submitDeviceInfo" method="post">
		       userName：<input type="text" name="userName" /><br/>
		       wifiIp：<input type="text" name="wifiIp" /><br/>
		       language：<input type="text" name="language" /><br/>
		       idfa：<input type="text" name="idfa" /><br/>
		       idfv：<input type="text" name="idfv" /><br/>
		       imei：<input type="text" name="imei" /><br/>
		       imsi：<input type="text" name="imsi" /><br/>
		       operaDesc：<input type="text" name="operaDesc" /><br/>
		       appVersion：<input type="text" name="appVersion" /><br/>
		       resolution：<input type="text" name="resolution" /><br/>
		       systemType：<input type="text" name="systemType" /><br/>
		       isRooted：<input type="text" name="isRooted" /><br/>
		       wifiName：<input type="text" name="wifiName" /><br/>
		       networkType：<input type="text" name="networkType" /><br/>
		       carrier：<input type="text" name="carrier" /><br/>
		       cellIp：<input type="text" name="cellIp" /><br/>
		       regId：<input type="text" name="regId" /><br/>
		       systemVersion：<input type="text" name="systemVersion" /><br/>
		       wifiList：<input type="text" name="wifiList" /><br/>
		       phoneNumber：<input type="text" name="phoneNumber" /><br/>
		       locInfo：<input type="text" name="locInfo" /><br/>
		       lastLoginTime：<input type="text" name="lastLoginTime" /><br/>
		       <input type="submit" value="提交" />
		     </form>    
		</div>       
	</body>
</html>