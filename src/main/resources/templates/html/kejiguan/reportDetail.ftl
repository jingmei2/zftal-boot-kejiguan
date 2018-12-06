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
    <body style="">
      <div style="background-color:#FFFFFF;margin:0px 100px 0px 100px;">
	    <div>
	      <div style="text-align:center;padding:5px;">
            <h3>${Request["reportModelTitle"]}</h3>
            <div style="text-align:center;">${Request["reportModelTime"]}</div>
	      </div>
	    </div>
	    <br/>
	    <div style="padding:20px;">  
		    <div style="text-align:center;">
			    <img src="${request.contextPath}/assets/reportImages/report1.png"/>
		    </div>
	        <div style="margin:auto;padding:20px;">
	           <p>${Request["reportModelContent"]}</p>   
	        </div>  
		</div> 
	  </div>	      
	</body>
</html>