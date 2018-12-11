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

</head>
<body>
<div class="main-content">
	<div class="sssj-xssj">
		<div class="sssj-xssj-data">
			<span class="glyphicon glyphicon-option-vertical" aria-hidden="true">详细数据</span>
			<div class="sssj-xssj-xxsj">
				<table id="monthDataTable" class="table table-bordered table-striped">
					<tr>
						<th>预定活动名称</th>
						<th>预定时间</th>
						<th>预定人数</th>
						<th>活动描述</th>
					</tr>
				</table>
			</div>
		</div>
		<br/><br/><br/>
	</div>
</div>

<script type="text/javascript" src="${request.contextPath}/assets/js/yhfx/ssyhgj.js?ver=${versionUtil()}"></script>
<script>
	$.post("${request.contextPath}/kejiguan/selectActivityListById",
		{
			title:""
		},
		function(data,status){
			//alert("Data: " + data + "\nStatus: " + status);
			var htm = "";
			var list = data.data;
			for(var i=0;i<list.length;i++){
				var obj = list[i];
				htm += "<tr><td>"+ obj.title +"</td>";
				htm += "<td>"+ obj.createTime +"</td>";
				htm += "<td>"+ obj.number +"</td>";
				htm += "<td>"+ obj.desc +"</td></tr>";
			}
			$("#monthDataTable").append(htm);

	});
</script>
</body>
</html>