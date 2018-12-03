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
	<link href="${request.contextPath}/assets/css/ymfx/qdfx.css?ver=${versionUtil()}" rel="stylesheet" type="text/css" />
</head>
    <body>
		 <div class="main-content">
		      <div class="sssj-header">
		          <span class="sssj-header-title">签到</span><br/>
		                              开始时间:&nbsp;<span id="kssjSp" class="sssj-header-day"><input type="text" name="kssj" id="kssj" style="width:170px;height:20px;text-align:center;"/></span>&nbsp;
		                              结束时间:&nbsp;<span id="jssjSp" class="sssj-header-day"><input type="text" name="jssj" id="jssj" style="width:170px;height:20px;text-align:center;"/></span>&nbsp;
                                                      学院:&nbsp;<span id="bjSp" class="sssj-header-day">  
                  <select id="instituteId" name="instituteId" style="height:25px;" >
                    <option value="">--选择学院--</option>
                  </select>&nbsp;&nbsp;</span>&nbsp;
                                                      班级:&nbsp;<span id="xySp" class="sssj-header-day">
                  <select id="classId" name="classId" style="height:25px;">
	                 <option value="">--选择班级--</option>
	              </select>                            
                  </span>&nbsp;
                                                      账号:&nbsp;<span id="yhmSp" class="sssj-header-day">
                  <input type="text" name="yhm" id="yhm" style="width:150px;height:25px;"/>                       
                  </span>&nbsp;
                                                      地点:&nbsp;<span id="addrSp" class="sssj-header-day">
                  <select id="address" name="address" style="height:25px;">
                     <option value="">所有</option>
	                 <option value="学士苑01">学士苑01</option>
	                 <option value="学士苑02">学士苑02</option>
	                 <option value="学士苑03">学士苑03</option>
	                 <option value="学士苑04">学士苑04</option>
	              </select>                            
                  </span>&nbsp;
                  <span id="kssjSp" class="sssj-header-day">
                  <button id="searchBtn" class="btn-primary" onclick="getChartData();">查询</button>
                  </span>&nbsp;
		      </div>
		      <div style="clear:both;"></div>
		      <div class="sssj-xssj">
			      <div class="sssj-xssj-yhTitle">
		             <div class="sssj-xssj-subTitle">关键指标详解</div>
		             <div id="newUserMonth" class="sssj-xssj-type">签到</div>
				     <div id="" style="clear:both;"></div>
			      </div>
			      <div class="sssj-xssj-data">
			        <span class="glyphicon glyphicon-option-vertical" aria-hidden="true">详细数据</span>
			      	<div class="sssj-xssj-xxsj">
			      	   <table id="dataTable" class="table table-bordered table-striped">
			      	       <tr><th>账号</th><th>姓名</th><th>签到地点</th><th>所在班级</th><th>所在学院</th></tr>
			      	   </table>
			      	</div>
			      </div>
			      <br/><br/><br/>
		      </div>
		 </div>
	
	<script type="text/javascript" src="${request.contextPath}/assets/js/ymfx/qdfx.js?ver=${versionUtil()}"></script>
	<script src="${request.contextPath}/assets/plugins/laydate/laydate.js?ver=${versionUtil()}"></script>
	</body>
</html>