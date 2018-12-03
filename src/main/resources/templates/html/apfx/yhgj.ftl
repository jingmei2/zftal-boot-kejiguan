<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv ="proma" content = "no-cache"/>
    <meta http-equiv="cache-control" content="no cache" />
    <meta http-equiv="expires" content="0" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
	<meta charset="utf-8">
    <title>基本地图展示</title>
	<!--首页专用的额外引用-->
	<script type="text/javascript">
		//全局变量
		var _path = "${request.contextPath}";
		var _systemPath = "${request.contextPath}";
	</script>	
	
	[#include "/head/zftal-ui-meta.ftl" /]
	[#include "/head/zftal-ui-required.ftl" /]
	[#include "/head/zftal-ui-laydate.ftl" /]
	
	<!-- 应用系统自定义样式 -->
	<link href="${request.contextPath}/assets/css/zftal-ui-app.css?ver=${versionUtil()}" rel="stylesheet" type="text/css" />
	<!-- 应用页面单独样式 -->
	<link href="${request.contextPath}/assets/css/apfx/yhgj.css?ver=${versionUtil()}" rel="stylesheet" type="text/css" />
    
    <!-- 高德地图 -->
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
    <script src="http://webapi.amap.com/maps?v=1.4.3&key=2ef78f1c54780cc68a0f50dcb9ee3ea5&plugin=AMap.MouseTool"></script>
</head>
	<body>
	    <div class="yhgj-header">
	       <div>
	           <b style="font-size:16px;">AP监控</b><br/>
	       </div>
	       <div>
	          <div style="width:40%;float:left;">
	            <font style="font-size:13px;">功能 :</font>
	         
			    <!--<input type="button" class="btn-primary" value="区域列表" id="getPolygonListForTable"/>-->
			      <span id="helpInfo" class="glyphicon glyphicon-question-sign" aria-hidden="true" style="cursor:pointer;"></span>
			  </div>
			  <div style="width:80%;float:left;"> 
			  	
			  	<select id="nation" onchange="getzbd();">
			  		<option value="">---请选择---</option>
			  	</select>
			  	
			  	时间:&nbsp;
				<span id="todaySp" class="sssj-header-day">今天</span>&nbsp;
				<span id="sevenDaySp" class="sssj-header-day">7天</span>&nbsp;
				<span id="fourteenSp" class="sssj-header-day">14天</span>&nbsp;
				<span id="thirtySp" class="sssj-header-day">30天</span>&nbsp;
				<input type="text" name="searchDate" id="searchDate" style="width:82px;height:20px;text-align:center;" />
			  	
			    <input id="userName" type="text" placeholder="请输入工号或学号"/>
			    <button id="searchBtn" class="btn-primary" onclick="drawPoly();">查询</button>
			    &nbsp;&nbsp;&nbsp;
			    <!--<button id="switchCenterPointBtn" class="btn-primary">水博园</button>-->
			    <button id="delPoly" class="btn-primary" onclick="delPoly();">清除轨迹</button>
			  </div> 
			  <div style="clear:both;"></div> 
	       </div>
		   <div class="sssj-gjzb">
		      <div class="sssj-zb selected-background"><a href="/apfx/yhgj" target="jcfxFrame">实时监控</a></div>
		      <div class="sssj-zb"><a href="/apfx/yjqysz" target="jcfxFrame">预警区域设置</a></div>
		      <div style="clear:both;"></div>
	       </div>
		</div>
	    
	    <div id="container" style="margin-top:110px;"></div>
	    
		<!-- bootstrap保存区域模态框结束 -->
	  
	<script type="text/javascript" src="${request.contextPath}/assets/js/apfx/yhgj.js?ver=${versionUtil()}"></script>
	<script src="${request.contextPath}/assets/plugins/laydate/laydate.js?ver=${versionUtil()}"></script>
	</body>
</html>