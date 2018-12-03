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
	
	<!-- 应用系统自定义样式 -->
	<link href="${request.contextPath}/assets/css/zftal-ui-app.css?ver=${versionUtil()}" rel="stylesheet" type="text/css" />
	<!-- 应用页面单独样式 -->
	<link href="${request.contextPath}/assets/css/apfx/yjqysz.css?ver=${versionUtil()}" rel="stylesheet" type="text/css" />
    
</head>
	<body>
	    <div class="yjqysz-header">
	       <div>
	           <b style="font-size:16px;">AP监控</b><br/>
	       </div>
	       <div>
	          <div style="width:40%;float:left;">
	            <font style="font-size:13px;">功能 :</font>
	            <input type="button" class="btn-primary" onclick="updatePolygonModal();" value="修改" id="updatePolygon"/>
			    <input type="button" class="btn-primary" onclick="deletePolygon();" value="删除" id="deletePolygon"/>
			  </div>
			  <div style="width:60%;float:left;"> 
			    <input id="title" type="text" placeholder="请输入预警区域名称"/>
			    <button id="searchBtn" onclick="getTableData();" class="btn-primary">查询</button>
			  </div> 
			  <div style="clear:both;"></div> 
	       </div>
		   <div class="sssj-gjzb">
		      <div class="sssj-zb"><a href="/apfx/apjk" target="jcfxFrame">实时监控</a></div>
		      <div class="sssj-zb selected-background"><a href="/apfx/yjqysz" target="jcfxFrame">预警区域设置</a></div>
		      <div style="clear:both;"></div>
	       </div>
		</div>
		<div class="yjqysz-content">
		   <div class="sssj-xssj-data">
		        <span class="glyphicon glyphicon-option-vertical" aria-hidden="true">详细数据</span>
		      	<div class="sssj-xssj-xxsj">
		      	   <table id="yjqyTable" class="table table-bordered table-striped">
		      	       <tr><th>&nbsp;</th><th>区域名称</th><th>预警等级</th><th>预警颜色</th><th>备注</th></tr>
		      	   </table>
		      	</div>
		    </div>		
		</div>
	    
	    
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
		                <tr><td>&nbsp;</td><td><input type="hidden" id="polygonId" name="polygonId"/></td></tr>
			            <tr><td>预警标题</td><td><input type="text" id="titleForUpdate" name="title"/></td></tr>
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
			            <tr><td>开始时间</td><td><input type="text" id="startTime" name="startTime"  onclick="laydate({istime:true,format:'YYYY-MM-DD hh:MM'})"/></td></tr>
			            <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
			            <tr><td>结束时间</td><td><input type="text" id="endTime" name="endTime"  onclick="laydate({istime:true,format:'YYYY-MM-DD hh:MM'})"/></td></tr>
			            <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
			            <tr><td>预警颜色</td><td style="text-align:left;"><input type="color" id="color" name="color"/></td></tr>
			         </table>                  
		         </div>
		      </div>
		      <div class="modal-footer">
		        <button id="updatePolygonBtn" onclick="updatePolygon();" type="button" class="btn btn-primary">保存</button>
		      </div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
		<!-- bootstrap保存区域模态框结束 -->
		
	<script type="text/javascript" src="${request.contextPath}/assets/js/apfx/yjqysz.js?ver=${versionUtil()}"></script>
	</body>
</html>