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
	<link href="${request.contextPath}/assets/css/khdfx/zdxx.css?ver=${versionUtil()}" rel="stylesheet" type="text/css" />
</head>
    <body>
		 <div class="main-content">
		      <div class="sssj-header">
		        <div><span class="sssj-header-title">设备分析</span>&nbsp;&nbsp;&nbsp;&nbsp;日期:<input type="text" name="searchDate" id="searchDate" style="width:82px;height:20px;text-align:center;"/></div>              
		      </div>
		      <div class="sssj-xssj">
			     <div>
		             <div class="sssj-xssj-subTitle">关键指标详解</div>
		             <div id="czxtbb" class="sssj-xssj-type">操作系统版本</div>
		             <div id="fbl" class="sssj-xssj-type">分辨率</div>
		             <div id="wlhj" class="sssj-xssj-type">网络环境</div>
		             <div id="yys" class="sssj-xssj-type">运营商</div>
		             <div id="sbxh" class="sssj-xssj-type">设备型号</div>
				     <div id="" style="clear:both;"></div>
				  </div>
				  <!--
				  <div class="sssj-xssj-subTitle2">   
		             <div id="activeUserMonth" class="sssj-xssj-type2">新增用户</div>
		             <div id="sessionCountMonth" class="sssj-xssj-type2">活跃用户</div>
		             <div id="totalUserMonth" class="sssj-xssj-type2">启动次数</div>
		             <div id="" style="clear:both;"></div>
			      </div>
			      -->
			      <div class="sssj-xssj-data">
			      	<span class="glyphicon glyphicon-option-vertical" aria-hidden="true">趋势图</span>
			      	<div class="sssj-xssj-qst">
				      	 <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
	                     <div id="qstChart" style="width:100%;height:500px;"></div>
			      	</div>
			      </div>
			      <div class="sssj-xssj-data">
			        <span class="glyphicon glyphicon-option-vertical" aria-hidden="true">详细数据</span>
			      	<div class="sssj-xssj-xxsj">
			      	   <table id="dataTable" class="table table-bordered table-striped">
			      	       <tr><th id="dataTypeTableHeader">时间</th><th>新增用户数</th><th>活跃用户数</th><th>启动次数</th></tr>
			      	   </table>
			      	</div>
			      </div>
			      <br/><br/><br/>
		      </div>
		 </div>
	
	<script type="text/javascript" src="${request.contextPath}/assets/js/khdfx/zdxx.js?ver=${versionUtil()}"></script>
	<script src="${request.contextPath}/assets/plugins/laydate/laydate.js?ver=${versionUtil()}"></script>
	</body>
</html>