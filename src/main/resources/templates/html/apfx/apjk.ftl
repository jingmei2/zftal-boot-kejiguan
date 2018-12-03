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
	<link href="${request.contextPath}/assets/css/apfx/apjk.css?ver=${versionUtil()}" rel="stylesheet" type="text/css" />
    
    <!-- 高德地图 -->
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
    <script src="http://webapi.amap.com/maps?v=1.4.3&key=2ef78f1c54780cc68a0f50dcb9ee3ea5&plugin=AMap.MouseTool"></script>
</head>
	<body>
	    <div class="apjk-header">
	       <div>
	           <b style="font-size:16px;">AP监控</b><br/>
	       </div>
	       <div>
	          <div style="width:40%;float:left;">
	            <font style="font-size:13px;">功能 :</font>
	            <input type="button" class="btn-primary" value="绘制区域" id="polygon"/>
			    <input type="button" class="btn-primary" value="保存区域" id="savePolygon"/>
			    <!--<input type="button" class="btn-primary" value="区域列表" id="getPolygonListForTable"/>-->
			      <span id="helpInfo" class="glyphicon glyphicon-question-sign" aria-hidden="true" style="cursor:pointer;"></span>
			  </div>
			  <div style="width:60%;float:left;"> 
			  	
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
		      <div class="sssj-zb selected-background"><a href="/apfx/apjk" target="jcfxFrame">实时监控</a></div>
		      <div class="sssj-zb"><a href="/apfx/yjqysz" target="jcfxFrame">预警区域设置</a></div>
		      <div style="clear:both;"></div>
	       </div>
		</div>
	    
	    <div id="container" style="margin-top:110px;"></div>
	    
	    <!-- bootstrap区域列表模态框开始 -->
	    <div id="myModal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
		  <div class="modal-dialog modal-lg" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title">区域列表</h4>
		      </div>
		      <div class="modal-body">
		         <table id="polygonArrTable" class='table table-striped table-bordered'>
		         </table>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		      </div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
		<!-- bootstrap区域列表模态框结束 -->
		
		<!-- bootstrap保存区域模态框开始 -->
	    <div id="editPolygonModal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
		  <div class="modal-dialog modal-sm" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title">编辑区域</h4>
		      </div>
		      <div class="modal-body">
		         <div style="text-align:center;">
		             <table>
			            <tr><td>预警标题</td><td><input type="text" id="title" name="title"/></td></tr>
			            <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
			            <tr><td>预警备注</td><td><input type="text" id="description" name="description"/></td></tr>
			            <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
			            <tr>
			               <td>预警登级</td>
			               <td style="text-align:left;">
		                       <select id="warnLevel" name="warnLevel" style="width:100px;height:25px;text-align:center;">
		                          <option value="V1">等级V1</option>
		                          <option value="V2">等级V2</option>
		                          <option value="V3">等级V3</option>
		                       </select>
			               </td>
			            </tr>
			            <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
			            <tr>
			               <td>选择类型</td>
			               <td style="text-align:left;">
		                       <select id="iscon" name="iscon" style="width:100px;height:25px;text-align:center;" onchange="changelddm();">
		                          <option value="0">预警区域</option>
		                          <option value="1">宿舍区域</option>
		                       </select>
			               </td>
			            </tr>
			            
			            <tr id="kkk"><td>&nbsp;</td><td>&nbsp;</td></tr>
			            <tr id="lddmtr" style="display: none;">
			               <td>选择楼栋</td>
			               <td style="text-align:left;">
		                       <select id="lddm" name="lddm" style="width:100px;height:25px;text-align:center;">
		                           <option value="">--选择楼栋--</option>
		                       </select>
			               </td>
			            </tr>
			            
			            <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
			            <tr><td>开始时间</td><td><input type="text" id="startTime" name="startTime"  onclick="laydate({istime:true,format:'YYYY-MM-DD hh:MM'})"/></td></tr>
			            <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
			            <tr><td>结束时间</td><td><input type="text" id="endTime" name="endTime"  onclick="laydate({istime:true,format:'YYYY-MM-DD hh:MM'})"/></td></tr>
			            <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
			            <tr><td>预警颜色</td><td style="text-align:left;"><input type="color" id="color" name="color"/></td></tr>
			         </table>                  
		         </div>
		      </div>
		      <div class="modal-footer">
		        <button id="savePolygonBtn" onclick="savePolygon();" type="button" class="btn btn-primary">保存</button>
		      </div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
		<!-- bootstrap保存区域模态框结束 -->
	  
	<script type="text/javascript" src="${request.contextPath}/assets/js/apfx/apjk2.js?ver=${versionUtil()}"></script>
	<script src="${request.contextPath}/assets/plugins/laydate/laydate.js?ver=${versionUtil()}"></script>
	</body>
</html>