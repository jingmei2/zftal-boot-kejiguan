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
	<link href="${request.contextPath}/assets/css/datetimepicker.css" rel="stylesheet" type="text/css"/>

	<!--<link href="${request.contextPath}/assets/css/datetimepicker.css"/>-->
	<!-- 该页面单独样式 -->

</head>
<body style="background: #fff;">
<div class="main-content">
	<div class="sssj-xssj">
		<div class="sssj-xssj-data">
			<div class="cz" style="padding:15px;position: fixed;width: 100%;height: 130px;background: #fff;z-index: 10000;">
				<span class="glyphicon glyphicon-option-vertical" aria-hidden="true" style="display:block;font-size: 16px;display: block;line-height: 50px;">详细数据</span>
				<button class="xinzeng" type="button" style="border:0;background:#0269f5;color:#fff;padding:10px 15px;border-radius:6px;font-size:16px;margin-right:10px;">新增活动</button>
				<button class="xiugai" type="button" style="border:0;background:#0269f5;color:#fff;padding:10px 15px;border-radius:6px;font-size:16px;margin-right:10px;">修改活动</button>
				<button class="shanchu" type="button" style="border:0;background:#0269f5;color:#fff;padding:10px 15px;border-radius:6px;font-size:16px;margin-right:10px;">删除活动</button>
				<div class="form-group" style="position:relative;width:240px;display:inline-block;">
					<input name="title" type="text" id="search-input" class="form-control" placeholder="请输入关键词" style="border-radius: 4px;background: #fff;line-height:40px;height:40px;">
					<i class="fa fa-search icon-right search-icon" aria-hidden="true"></i>
				</div>
				<div class="mask" style="display:none;background: #000;width: 100%;height: 100%;position: fixed;z-index: 100;opacity: 0.68;top: 0;left: 0;"></div>
				<div class="add" style="display:none;width: 400px;position: fixed;top: 10%;left: 50%;box-shadow: 1px 0px 1px 1px #ccc;background: #fff;transform: translateX(-80%);z-index: 100;border-radius: 6px;padding: 20px;">
				    <form id="form" enctype="multipart/form-data">
					    <label class="formTitle" style="font-size: 20px;font-weight: bold;color: #0269f5;display: block;border-bottom: 1px solid #f5f5f5;line-height: 45px;">新增活动</label>
					    <div style="margin: 20px 0;">
				            <label style="width: 100px;font-size: 16px;color: #000;display: inline-block;line-height: 40px;">活动标题</label>
				            <input name="titles" type="text" placeholder="请输入活动标题" style="border: 1px solid #cccccc;border-radius: 4px;line-height: 40px;width: 245px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);padding-left: 15px;">
				        </div>

						<div style="margin-bottom: 20px;">
				            <label style="width: 100px;font-size: 16px;color: #000;display: inline-block;line-height: 40px;">预定时间</label>
<!--				            <input size="16" type="text" value="2012-06-15 14:45" readonly class="form_datetime">-->
				            <input size="16" value="" class="form_datetime" name="createTime" readonly="readonly" type="text" placeholder="请选择预定时间" style="border: 1px solid #cccccc;border-radius: 4px;line-height: 40px;width: 245px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);padding-left: 15px;">
				        </div>

						<div style="margin-bottom: 20px;">
				            <label style="width: 100px;font-size: 16px;color: #000;display: inline-block;line-height: 40px;">预定人数</label>
				            <input name="number" type="text" placeholder="请输入预定人数" style="border: 1px solid #cccccc;border-radius: 4px;line-height: 40px;width: 245px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);padding-left: 15px;">
				        </div>
						<div style="margin-bottom: 20px;">
				            <label style="width: 100px;font-size: 16px;color: #000;display: inline-block;line-height: 40px;vertical-align: bottom;">活动描述</label>
				            <textarea style="width: 245px;" name="desc" placeholder="请输入活动描述"></textarea>
				        </div>

				        <div style="margin-bottom: 20px;">
				            <label style="width: 100px;font-size: 16px;color: #000;display: inline-block;line-height: 40px;">上传图片</label>
				            <img id="showImg" src="" style="display:none;width: 160px;height: 120px;background: #000;vertical-align: bottom;">
				            <div style="width: 92px;height: 45px;position: relative;display: inline-block;">
				            	<input type="hidden" id="img" name="picPath"/>
					        	<input name="faceLegalFile" class="submit" style="color:#fff;border-radius:6px;font-size:16px;border: 0;display: inline-block;height: 45px;position: relative;z-index: 100;width: 92px;opacity: 0;" type="file" id="file" onchange="changepic(this)" accept="image/jpg,image/jpeg,image/png,image/PNG">
					        	<button class="xinzeng" type="button" style="border:0;background:#0269f5;color:#fff;padding: 0 15px;border-radius:6px;font-size: 18px;margin-right:10px;position: absolute;left: 0;line-height: 45px;width: 90px;top:0;">上传</button>
					        </div>
				        </div>
					    <button class="addAct" type="button" style="border:0;background:#0269f5;color:#fff;padding:10px 15px;border-radius:6px;font-size:16px;margin-right:10px;">新增活动</button>
					    <button class="back" type="button" style="border:1px solid #ccc;background:#fff;color:#000;padding:10px 30px;border-radius:6px;font-size:16px;margin-right:10px;">取消</button>
					</form>
				</div>
			</div>
			<div class="sssj-xssj-xxsj" style="padding-top: 130px;">
				<table id="monthDataTable" class="table table-bordered table-striped">
					<tr class="actName">
					    <th>
							<input class="allCheck" type="checkbox">
						</th>
						<th>预定活动名称</th>
						<th>预定时间</th>
						<th>预定人数</th>
						<th>活动描述</th>
						<th>背景图片</th>
					</tr>
				</table>
			</div>
		</div>

	</div>
</div>
<script src="https://malsup.github.io/jquery.form.js"></script>
<script type="text/javascript" src="${request.contextPath}/assets/js/yhfx/ssyhgj.js?ver=${versionUtil()}"></script>
<script type="text/javascript" src="${request.contextPath}/assets/js/datetimepicker.js"></script>
<!--<script type="text/javascript" src="${request.contextPath}/assets/js/datetimepicker.js"></script>-->
<script>
	var tip;
	actList();
	$(".form_datetime").datetimepicker({
		format: 'yyyy-mm-dd hh:ii',
	});
	//新增
	$('.xinzeng').click(function(){
		console.log($('body').find('.actId').attr('class')=='actId')
		if($('body').find('.actId').attr('class')=='actId'){
			$('body').find('.actId').remove();
		}
		if($('.add').is(':hidden')){
			tip=1;
			$('.add').show().find('input').val('');
			$('.mask').show();
			$('.add').find('textarea').val('');
			$('.formTitle').text('新增活动');
			$('.addAct').text('新增活动');
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
			$('.formTitle').text('修改活动');
			$('.addAct').text('修改活动');
            if($('body').find('.actId').attr('name')!='id'){
				$('.add form').append('<input class="actId" type="hidden" id="activityId" name="id"/>');
			}
			$.get("${request.contextPath}/kejiguan/selectActivityById",{
				id:$("input[type=checkbox]:checked").val()
				},function(res){
	           if(res.code==1){
	           	 $('.add').show();
	           	 $('.mask').show();
	           	 $('input[name="titles"]').val(res.data.titles);
	           	 $('input[name="createTime"]').val(res.data.createTime);
	           	 $('input[name="number"]').val(res.data.number);
	           	 $('textarea[name="desc"]').val(res.data.desc);
	           	 $('input[name="picPath"]').val(res.data.picPath);
	           	 $('input[name="id"]').val(res.data.id);
	           	 $('#showImg').attr('src',res.data.picPath).show();
	           }
			});
		}
	})

	//取消
	$('.add .back').click(function(){
		$('.add').hide();
		$('.mask').hide();
	})

    //添加/修改
	$('.addAct').click(function(){
		$('#form').ajaxSubmit({
			url: 'http://121.43.179.186:9097/kejiguan/saveorupdatePic',
            type: "Post",
            success:function(res){
            	alert(res.status);
            	$('.add').hide();
            	$('.mask').hide();
            	if($('body').find('.actId').attr('class')=='actId'){
					$('body').find('.actId').remove();
				}
            	$('body').find('#monthDataTable tr').not('.actName').remove();
            	actList();
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
			$.post("${request.contextPath}/kejiguan/deleteActivityById",{
				id:$("input[type=checkbox]:checked").val()
			},function(res){
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

	//显示图片
	function changepic(){
	   var image = '';
	   var base64;
	   var reader = new FileReader();//读取文件
	   //将文件已Data URL的形式读入页面
	   reader.readAsDataURL(document.getElementById('file').files[0]);
	   reader.onload = function(event){//文件读取完成的回调函数
		  image = document.getElementById('showImg');
		  image.style.display='inline-block';
		  image.src = event.target.result;//读入文件的base64数据(可直接作为src属性来显示图片)
	   }
	}
	//搜索活动
	$("input[name='title']").on('keypress', function(e) {
		var keycode = e.keyCode;
		if (keycode == '13') {
			e.preventDefault();
			$('body').find('#monthDataTable tr').not('.actName').remove();
			actList();
		}
	});
    //请求数据
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
					obj.titles=obj.titles==null?'':obj.titles;
					obj.number=obj.number==null?'':obj.number;
					obj.desc=obj.desc==null?'':obj.desc;
					htm += "<tr><td><input type='checkbox' name='id' value="+obj.id+"></td>";
					htm += "<td>"+ obj.titles +"</td>";
					htm += "<td>"+ obj.createTime +"</td>";
					htm += "<td>"+ obj.number +"</td>";
					htm += "<td>"+ obj.desc +"</td>";
					htm += "<td><img width='100px' src="+ obj.picPath +" ></td></tr>";
				}
				$("#monthDataTable").append(htm);

		});
	}

</script>
</body>
</html>
