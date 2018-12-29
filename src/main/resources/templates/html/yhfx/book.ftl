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
<body style="background: #fff;">
<div class="main-content">
	<div class="sssj-xssj">
		<div class="sssj-xssj-data">
			<div class="cz" style="padding:15px;position: fixed;width: 100%;height: 130px;background: #fff;z-index: 10000;">
				<span class="glyphicon glyphicon-option-vertical" aria-hidden="true" style="display:block;font-size: 16px;display: block;line-height: 50px;">详细数据</span>
				<div class="form-group" style="position:relative;width:240px;display:inline-block;">
					<input name="name" type="text" id="search-input" class="form-control" placeholder="请输入关键词" style="border-radius: 4px;background: #fff;line-height:40px;height:40px;">
					<i class="fa fa-search icon-right search-icon" aria-hidden="true"></i>
				</div>
				<!--<button type="button">搜索</button>-->
			</div>
			<div class="sssj-xssj-xxsj" style="padding-top: 130px;">
				<table id="monthDataTable" class="table table-bordered table-striped">
					<tr class="actName">
						<th>预定活动名称</th>
						<th>预定时间</th>
						<th>活动类型</th>
						<th>身份证号码</th>
						<th>预定人数</th>
						<th>预定公司名字</th>
						<th>手机号码</th>
					</tr>
				</table>
			</div>
		</div>
		<br/><br/><br/>
	</div>
</div>

<script type="text/javascript" src="${request.contextPath}/assets/js/yhfx/ssyhgj.js?ver=${versionUtil()}"></script>
<script>
    list('');
	$("input[type='text']").on('keypress', function(e) {
		var keycode = e.keyCode;
		if (keycode == '13') {
			e.preventDefault();
			$('body').find('#monthDataTable tr').not('.actName').remove();
			list();
		}
	});
	
	function  list(){
		var name=$("input[name='name']").val();
		$.post("${request.contextPath}/kejiguan/selectBookListById",
			{
				name:name
			},
			function(data,status){
				//alert("Data: " + data + "\nStatus: " + status);
				var htm = "";
				
				var list = data.data;
				for(var i=0;i<list.length;i++){
					var obj = list[i];
					var nam =obj.name;
					obj.idcard=obj.idcard==null?'':obj.idcard;
					obj.companyNum=obj.companyNum==null?'':obj.companyNum;
					obj.companyName=obj.companyName==null?'':obj.companyName;
					obj.phone=obj.phone==null?'':obj.phone;
					if(obj.type=='2'||obj.type==2){
						console.log(obj.type)
						nam=obj.contact;
					}
					htm += "<tr><td>"+nam+"</td>";
					htm += "<td>"+ obj.createTime +"</td>";
					htm += "<td>"+ obj.type +"</td>";
					htm +="<td>"+ obj.idcard +"</td>";
					htm +="<td>"+ obj.companyNum +"</td>";
					htm +="<td>"+ obj.companyName +"</td>";
					htm +="<td>"+ obj.phone +"</td></tr>";
				
				}
				$("#monthDataTable").append(htm);

			});
	
	}
	
</script>
</body>
</html>