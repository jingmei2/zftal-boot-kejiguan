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
	<link href="${request.contextPath}/assets/css/apfx/apyj.css?ver=${versionUtil()}" rel="stylesheet" type="text/css" />
</head>
    <body>
		 <div class="main-content">
		      <div class="sssj-header">
		          <span class="sssj-header-title">AP预警</span><br/>
		                              时间:&nbsp;<span id="todaySp" class="sssj-header-day">今天</span>&nbsp;
                  <span id="sevenDaySp" class="sssj-header-day">7天</span>&nbsp;
                  <span id="fourteenSp" class="sssj-header-day">14天</span>&nbsp;
                  <span id="thirtySp" class="sssj-header-day">30天</span>&nbsp;
                  <!--<span id="totalSp" class="sssj-header-day">所有</span>&nbsp;-->
                  <input type="text" name="searchDate" id="searchDate" style="width:82px;height:20px;text-align:center;"/>
		      </div>
		      <div style="clear:both;"></div>
		      <div class="sssj-xssj">
			      <div class="sssj-xssj-yhTitle">
		             <div class="sssj-xssj-subTitle">关键指标详解</div>
		             <div id="wxyj" class="sssj-xssj-type">危险预警</div>
		             <div id="sswgyj" class="sssj-xssj-type">宿舍晚归预警</div>
		             <div id="jqyj" class="sssj-xssj-type">假期预警</div>
				     <div id="" style="clear:both;"></div>
			      </div>
			      <div class="sssj-xssj-data">
				    <span class="glyphicon glyphicon-option-vertical" aria-hidden="true">趋势图</span>
			      	<div class="sssj-xssj-qst">
	                     <div id="qstBarChart" style="width:100%;height:300px;"></div>
			      	</div>
			      </div>
			      <div class="sssj-xssj-data">
			        <span class="glyphicon glyphicon-option-vertical" aria-hidden="true">详细数据</span>
			      	<div class="sssj-xssj-xxsj">
			      	   <table id="dataTable" class="table table-bordered table-striped">
			      	       
			      	   </table>
			      	</div>
			      </div>
			      <br/><br/><br/>
		      </div>
		 </div>
	
	<script type="text/javascript" src="${request.contextPath}/assets/js/apfx/apyj.js?ver=${versionUtil()}"></script>
	<script src="${request.contextPath}/assets/plugins/laydate/laydate.js?ver=${versionUtil()}"></script>
	</body>
</html>