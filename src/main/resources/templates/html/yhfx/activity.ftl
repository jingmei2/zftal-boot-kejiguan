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
			<span class="glyphicon glyphicon-option-vertical" aria-hidden="true" style="font-size: 16px;margin: 15px 0 0 15px;">详细数据</span>
			<div class="cz" style="padding:15px;">
				<button class="xinzeng" type="button" style="border:0;background:#0269f5;color:#fff;padding:10px 15px;border-radius:6px;font-size:16px;margin-right:10px;">新增活动</button>
				<button class="xiugai" type="button" style="border:0;background:#0269f5;color:#fff;padding:10px 15px;border-radius:6px;font-size:16px;margin-right:10px;">修改活动</button>
				<button class="shanchu" type="button" style="border:0;background:#0269f5;color:#fff;padding:10px 15px;border-radius:6px;font-size:16px;margin-right:10px;">删除活动</button>
				<div class="form-group" style="position:relative;width:240px;display:inline-block;">
					<input name="title" type="text" id="search-input" class="form-control" placeholder="请输入关键词" style="border-radius: 4px;background: #fff;line-height:40px;height:40px;">
					<i class="fa fa-search icon-right search-icon" aria-hidden="true"></i>
				</div>
				<div class="add" style="display:none;width: 400px;position: fixed;top: 10%;left: 50%;box-shadow: 1px 0px 1px 1px #ccc;background: #fff;transform: translateX(-80%);z-index: 100;border-radius: 6px;padding: 20px;">
				    <form>
					    <label style="font-size: 20px;font-weight: bold;color: #0269f5;display: block;border-bottom: 1px solid #f5f5f5;line-height: 45px;">新增活动</label>
					    <div style="margin: 20px 0;">
				            <label style="width: 100px;font-size: 16px;color: #000;">活动标题</label>
				            <input name="title" type="text" placeholder="请输入活动标题" style="border: 1px solid #cccccc;border-radius: 4px;line-height: 40px;width: 245px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);padding-left: 15px;">
				        </div>

						<div style="margin-bottom: 20px;">
				            <label style="width: 100px;font-size: 16px;color: #000;">预定时间</label>
				            <input name="createTime" type="text" placeholder="请选择预定时间" style="border: 1px solid #cccccc;border-radius: 4px;line-height: 40px;width: 245px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);padding-left: 15px;">
				        </div>
				        <div style="margin-bottom: 20px;">
				            <label style="width: 100px;font-size: 16px;color: #000;">上传图片</label>
				            <img id="showImg" src="" style="display:none;width: 160px;height: 120px;background: #000;vertical-align: bottom;">
				            <div style="width: 92px;height: 45px;position: relative;display: inline-block;">
				            	<input type="hidden" id="img" name="picPath"/>
					        	<input class="submit" style="color:#fff;border-radius:6px;font-size:16px;border: 0;display: inline-block;height: 45px;position: relative;z-index: 100;width: 92px;opacity: 0;" type="file" id="file" onchange="changepic(this)" accept="image/jpg,image/jpeg,image/png,image/PNG">
					        	<button class="xinzeng" type="button" style="border:0;background:#0269f5;color:#fff;padding: 0 15px;border-radius:6px;font-size: 18px;margin-right:10px;position: absolute;left: 0;line-height: 45px;width: 90px;top:0;">上传</button>
					        </div>
				        </div>
						<div style="margin-bottom: 20px;">
				            <label style="width: 100px;font-size: 16px;color: #000;">预定人数</label>
				            <input name="number" type="text" placeholder="请输入预定人数" style="border: 1px solid #cccccc;border-radius: 4px;line-height: 40px;width: 245px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);padding-left: 15px;">
				        </div>
						<div style="margin-bottom: 20px;">
				            <label style="width: 100px;font-size: 16px;color: #000;">活动描述</label>
				            <input name="desc" type="text" placeholder="请输入活动描述" style="border: 1px solid #cccccc;border-radius: 4px;line-height: 40px;width: 245px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);padding-left: 15px;">
				        </div>
					    <button class="addAct" type="button" style="border:0;background:#0269f5;color:#fff;padding:10px 15px;border-radius:6px;font-size:16px;margin-right:10px;">新增活动</button>
					    <button class="back" type="button" style="border:1px solid #ccc;background:#fff;color:#000;padding:10px 30px;border-radius:6px;font-size:16px;margin-right:10px;">取消</button>
					</form>
				</div>
			</div>
			<div class="sssj-xssj-xxsj">
				<table id="monthDataTable" class="table table-bordered table-striped">
					<tr>
					    <th>
							<input class="allCheck" type="checkbox">
						</th>
						<th>预定活动名称</th>
						<th>预定时间1111</th>
						<th>预定人数</th>
						<th>活动描述</th>
					</tr>
				</table>
			</div>
		</div>
		
	</div>
</div>

<script type="text/javascript" src="${request.contextPath}/assets/js/yhfx/ssyhgj.js?ver=${versionUtil()}"></script>
<script>
	var tip;
	actList();
	//新增
	$('.xinzeng').click(function(){
		if($('.add').is(':hidden')){
			tip=1;
			$('.add').show().find('input').val('');
			$('#showImg').attr('src','').hide();
		}
	})

	//修改
	$('.xiugai').click(function(){
		var len=$("input[type=checkbox]:checked").length;
		if(len>1){
			alert('只能勾选一个活动！');
		}else if(len==0){
			alert('请勾选活动！');
		}else{
			//根据id找活动赋值
			$.get("${request.contextPath}/kejiguan/selectActivityById?id="+$("input[type=checkbox]:checked").val()+"",function(res){
	           if(res.code==1){
	           	 tip=2;
	           	 $('.add').show();
	           	 $('input[name="title"]').val(res.data.title);
	           	 $('input[name="createTime"]').val(res.data.createTime);
	           	 $('input[name="number"]').val(res.data.number);
	           	 $('input[name="desc"]').val(res.data.desc);
	           	 $('input[name="picPath"]').val(res.data.picPath);
	           	 $('#showImg').attr('src',res.data.picPath).show();
	           }
			});
		}
	})
	
	//取消
	$('.add .back').click(function(){
		$('.add').hide();
	})
	
	//添加/修改
	$('.addAct').click(function(){
		var data=$('.add form').serialize();
		var webUrl;
		//判断添加还是修改
		if(tip==2){
			webUrl="${request.contextPath}/kejiguan/updateActivity";
		}else{
			webUrl="${request.contextPath}/kejiguan/addActivity";
		}
		$.post(webUrl,data,function(res){
           if(res.code==1){
//         	  $('.add').hide();
//         	  window.location.reload();
           }
		});
	})
	
	//删除
	$('.shanchu').click(function(){
		var len=$("input[type=checkbox]:checked").length;
		if(len>1){
			alert('只能勾选一个活动！');
		}else if(len==0){
			alert('请勾选活动！');
		}else{
			$.get("${request.contextPath}/kejiguan/deleteActivityById?id="+$("input[type=checkbox]:checked").val()+"",function(res){
	           if(res.code==1){
	           	  window.location.reload();
	           }
			});
		}
	})
	
	//全选
//	$('.allCheck').click(function(){
//		if(this.checked){
//			$('#monthDataTable').find("input[type='checkbox']").attr('checked',true);
//		}else{
//			$('#monthDataTable').find("input[type='checkbox']").attr('checked',false);
//		}
//	})
	
	//上传图片
	function changepic() {	 
	   var image = '';
	   var base64;
	   var reader = new FileReader();//读取文件
	   //将文件已Data URL的形式读入页面
	   reader.readAsDataURL(document.getElementById('file').files[0]);
	   reader.onload = function(event){//文件读取完成的回调函数
		  image = document.getElementById('showImg');
		  image.style.display='inline-block';
		  image.src = event.target.result;//读入文件的base64数据(可直接作为src属性来显示图片)
		  //图片读取完成的回调函数（必须加上否则数据读入不完整导致出错！）
		  image.onload = function(){
			   base64 = event.target.result; 
			   $.post("", //服务器接口(返回图片路径)
				{
					data:base64
				},
				function(res) {
					$('#img').val('');
				}, "json");
		  }
	   }
	}
	$("input[name='title']").on('keypress', function(e) {
		var keycode = e.keyCode;
		if (keycode == '13') {
			e.preventDefault();
			actList();
		}
	});
	
	function actList(){
		var title=$(".form-group").find("input[name='title']").val();
		console.log(title)
		$.post("${request.contextPath}/kejiguan/selectActivityListById",
			{
				title:title
			},
			function(data,status){
				//alert("Data: " + data + "\nStatus: " + status);
				var htm = "";
				var list = data.data;
				for(var i=0;i<list.length;i++){
					var obj = list[i];
					htm += "<tr><td><input type='checkbox' name='checkLine' value="+obj.id+"></td>";
					htm += "<td>"+ obj.title +"</td>";
					htm += "<td>"+ obj.createTime +"</td>";
					htm += "<td>"+ obj.number +"</td>";
					htm += "<td>"+ obj.desc +"</td></tr>";
				}
				$("#monthDataTable").append(htm);

		});
	}
	
</script>
</body>
</html>